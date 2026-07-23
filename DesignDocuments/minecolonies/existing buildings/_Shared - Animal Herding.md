> Shared behaviour reference for all "herder" buildings: [Chicken Farmer's Hut](Chicken%20Farmer's%20Hut.md), [Cowhand's Hut](Cowhand's%20Hut.md), [Rabbit Hutch](Rabbit%20Hutch.md), [Shepherd's Hut](Shepherd's%20Hut.md), [Swineherd's Hut](Swineherd's%20Hut.md), and (partially) [Stable](Stable.md).
>
> Every herder job shares one AI base class and one building-module type. Per-building docs only list what's *different*; this file documents the common state machine, enforcement points, and extension hooks.

### Core classes
- AI base class: [`AbstractEntityAIHerder<J, B>`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/AbstractEntityAIHerder.java) — all herder AIs (`EntityAIWorkChickenHerder`, `EntityAIWorkCowboy`, `EntityAIWorkRabbitHerder`, `EntityAIWorkShepherd`, `EntityAIWorkSwineHerder`, `EntityAIWorkStablemaster`) extend this.
- Building module: [`AnimalHerdingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/AnimalHerdingModule.java) — declares which `Animal` subtype the herder cares about (`Predicate<Animal>`) and the breeding item. Registered per-building as a static `HerdingModule` inner class (e.g. `BuildingChickenHerder.HerdingModule`) or, for Rabbit Hutch and Swineherd's Hut, as an anonymous module instance directly in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java) (search `RABBITHERDER_HERDING` / `SWINEHERDER_HERDING`).
- Skills/registration wiring for every herder job lives in `BuildingModules` — search for the `*_WORK` and `*_HERDING` constants (e.g. `COWHERDER_WORK`, `COWHERDER_HERDING`). This is also the single best place to look up any worker's primary/secondary skill.

### AI state machine (shared)
States registered in the abstract class (`AIWorkerState` enum in `api/entity/ai/statemachine/states/`):

1. **`IDLE` → `START_WORKING`** — walk to the hut.
2. **`PREPARING`** — request tools (`getExtraToolsNeeded()`, always includes an axe) and breeding items (`getBreedingItems()` × 8, see `EXTRA_BREEDING_ITEMS_REQUEST`) via `checkIfRequestForItemExistOrCreateAsync`.
3. **`DECIDE`** (`decideWhatToDo()`, runs every 80 ticks) — for each `AnimalHerdingModule` on the building, scans nearby animals (`WorldUtil.getEntitiesWithinBuilding`, i.e. **the building's claimed area, not a fixed radius**) and randomly rolls, in this priority order:
   - 10% chance → **`HERDER_PICKUP`** if loose items (feathers, wool, etc.) are lying around.
   - `chanceToButcher(animals)` → **`HERDER_BUTCHER`**.
   - if ≥2 breedable animals + a breeding item + not on cooldown → **`HERDER_BREED`**.
   - 10% chance (`FEED_CHANCE`) → **`HERDER_FEED`** (grows up baby animals faster).
4. **`HERDER_BUTCHER`** — picks the animal *furthest* from the herd centroid, preferring ones not in direct sunlight, and swings an axe at it (`BUTCHERING_ATTACK_DAMAGE = 5`, scaled per-job — see below). If the [Looting research](../../../minecolonies/src/main/java/com/minecolonies/api/research/util/ResearchConstants.java) effect (`LOOTING`) is active, the swing is simulated through a `FakePlayer` holding a temporary Looting I copy of the worker's weapon so vanilla loot-table logic drops bonus items.
5. **`HERDER_BREED`** — walks two compatible, loved-up animals together and feeds them the breeding item (vanilla `Animal.canMate`/`setInLove`).
6. **`HERDER_FEED`** — ages up baby animals early if `getSecondarySkillLevel() >= LIMIT_TO_FEED_CHILDREN` (10).
7. **`HERDER_PICKUP`** — walks over and vacuums up nearby dropped items (`worker.setCanPickUpLoot(true)`).

### Enforcement points (who's actually in charge)
| Behaviour | Enforced by | Mechanism |
|---|---|---|
| Which animal species a hut cares about | **Building code** | `AnimalHerdingModule`'s `Predicate<Animal>` (e.g. `a -> a instanceof Chicken`) |
| Breeding item | **Building code** | `AnimalHerdingModule`'s `ItemStorage breedingItem` ctor arg |
| Max animals before butchering kicks in | **Worker AI code** | `chanceToButcher()`: `maxAnimals = building.getBuildingLevel() * getMaxAnimalMultiplier()` (multiplier is `2` by default, see `ANIMAL_MULTIPLIER`). This is a **soft cap** — a probability curve (`0.5 * (grownUp/maxAnimals)^4`), not a hard block; overcrowding just makes butchering near-certain rather than instantly triggered. The Breeding setting (`AbstractBuilding.BREEDING`) can force 0% butcher chance even over cap (until the animal count itself forces butchering back on for e.g. Stablemaster overrides — see below) |
| Whether butchering happens at all | **Building code (setting)** | `ISettingKey<BoolSetting> AbstractBuilding.BREEDING` — if disabled and under cap, `chanceToButcher()` returns 0 |
| Search radius for animals/items | **Building code (schematic-derived)** | `WorldUtil.getEntitiesWithinBuilding` — driven by the building's claimed area/corners, so a bigger schematic footprint literally means a bigger herding pen. Not a fixed block radius like the Forester. |
| Attack damage while butchering | **Worker AI code** | `getButcheringAttackDamage()`, overridden per job — Cowboy/Swineherd scale with `getPrimarySkillLevel()/10`, Shepherd scales with `getSecondarySkillLevel()/10`, Chicken/Rabbit Herder instead roll a **miss chance** based on skill (`(100 - skill)/5` chance to whiff per swing) rather than scaling damage |
| Tool requirement | **Worker AI code** | `getExtraToolsNeeded()` — axe is hard-coded for all herders; Shepherd conditionally adds shears if the Shearing setting is on; Stablemaster instead needs a Lead (`ModEquipmentTypes.lead`) |

### Extending: adding a new herder-type building
1. Add a `HerdingModule extends AnimalHerdingModule` inner class to your building (constructor: job entry, `Predicate<Animal>`, breeding `ItemStorage`) — or reuse the plain `AnimalHerdingModule` constructor directly like Rabbit Hutch/Swineherd's Hut do if you don't need custom JEI recipe display.
2. Register a `*_WORK` (`WorkerBuildingModule`) and `*_HERDING` (your module) pair in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java), and reference both from the `BuildingEntry.Builder` in [`ModBuildingsInitializer`](../../../minecolonies/src/main/java/com/minecolonies/core/apiimp/initializer/ModBuildingsInitializer.java).
3. Create an `EntityAIWork*` extending `AbstractEntityAIHerder<YourJob, YourBuilding>` in `core/entity/ai/workers/production/herders/`; override `getExpectedBuildingClass()`, and only the hooks you need to change (`getButcheringAttackDamage`, `getExtraToolsNeeded`, `chanceToButcher`, etc.) — the whole state machine is inherited for free.
4. If the animal needs interactions vanilla `Animal` doesn't have (Stablemaster's cavalry conversion, Cowboy's milking/stewing), add custom `AITarget`s in your subclass's constructor on top of the inherited ones, exactly as `EntityAIWorkCowboy` (`COWBOY_MILK`, `COWBOY_STEW`) and `EntityAIWorkStablemaster` (`HERDER_TRAIN`, `HERDER_READY_FOR_COMBAT`, `HERDER_GATHER_MOUNTS`) do.

### Datapack notes
There's no loot-table override specific to herding — animals drop through their normal vanilla/Forge loot tables (`Animal.getLootTable()`), which `AnimalHerdingModule.getLootTables()` merely surfaces for JEI display. To change what a butchered chicken/cow/etc. drops, edit that entity's loot table datapack-side (`data/minecraft/loot_table/entities/<animal>.json` or the relevant modded equivalent); MineColonies does not intercept or replace it.
