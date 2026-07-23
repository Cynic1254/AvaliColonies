### Function
The Stable is a hybrid building: it's both an animal-herding hut (via the Stablemaster job, tending horses) **and** a guard building (it extends `AbstractBuildingGuards` and houses/deploys Cavalry guards). It doesn't fit cleanly into either family, so most of its mechanics are documented here rather than split across shared docs — but see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** and **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for the general frameworks each half builds on.

- Building: [`BuildingStable`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingStable.java) — extends `AbstractBuildingGuards`, not the usual `AbstractBuilding`.
- Stablemaster job: `stablemaster` — [`JobStablemaster`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobStablemaster.java)
- Stablemaster AI: [`EntityAIWorkStablemaster`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkStablemaster.java) — extends the shared [`AbstractEntityAIHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/AbstractEntityAIHerder.java) (see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the inherited state machine), but overrides most of its interesting behaviour.
- Herding module: `BuildingStable.HerdingModule` (compatible animal: `Horse`; breeding item: 2× Golden Apple), registered as `STABLEMASTER_HERDING`.
- Cavalry guard module: `StableCavalryBuildingModule` (`CAVALRY_STABLE_WORK`), tied to `ModGuardTypes.cavalry`.
- Cavalry mount entity: [`CavalryHorseEntity`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/other/cavalry/CavalryHorseEntity.java)

### Levels
Max building level: 5 (inherited default). Both the horse-herding cap and the cavalry-mount cap use the same formula, `building level × 2` — but they are tracked completely separately (see Limits):
1. 2 horses / 2 cavalry mounts
2. 4 / 4
3. 6 / 6
4. 8 / 8
5. 10 / 10

Cavalry guard patrol distance also scales like a normal Guard Tower, boosted by `CAVALRY_PATROL_RANGE_BOOST = 1.5×` (see Limits).

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Stablemaster: Primary Athletics, Secondary Dexterity (`STABLEMASTER_WORK`)
- Cavalry guards use the standard guard-type skill setup (`ModGuardTypes.cavalry`) rather than the Stablemaster's skills — cavalry guard combat stats are a separate system from the Stablemaster job.

### Limits
**Two separate populations, two separate caps, both Worker/Building code (no schematic-side limit beyond stall count):**

- **Regular horses** (bred/butchered/fed like any herder animal): capped by the standard shared soft-cap curve (`chanceToButcher`), **except** `EntityAIWorkStablemaster.chanceToButcher()` is overridden to always return `0` — Stablemaster horses are *never* butchered automatically. Breeding is instead throttled by `canBreedChildren()`, overridden to also check `countCurrentMounts() >= building level × 2` and refuse to breed more once at cap (a **hard** cap here, unlike the usual soft curve).
- **Cavalry mounts** (`CavalryHorseEntity`, converted from regular horses): `convertMount()` enforces `current >= building level × 2` as a hard cap before picking a horse to train via `HERDER_TRAIN`. Conversion itself (chance-gated: `TRAINING_CHANCE = 25%` per decision tick) picks the first untrained `AbstractHorse` found and hands it to `CavalryHorseEntity.createFromVanilla(...)`.
- **Stall positions come from the schematic**, not code: `stallPositions()` reads all blocks tagged `stall` in the building blueprint (`STALL_STRUCTURE_TAG`) via `getLocationsFromTag`. If a Stable's blueprint has no `stall`-tagged blocks, `getNextStallPosition()` returns null and a warning is logged — **this is the one place schematic authoring directly bounds behaviour** (no stall tags → no defined parking spot for retrieved/gathered horses, though the cap on numbers is still enforced purely in code).
- **Feeding/readying cavalry mounts for combat** (`readyMountForCombat()` / `HERDER_READY_FOR_COMBAT`, rolled at `READY_MOUNT_FOR_COMBAT_CHANCE = 40%` per decision tick) consumes items from two datapack-controlled item tags rather than hardcoded items:
  - Feeding (restoring health) needs an item in the `minecolonies:feed` tag (`ModTags.feed`) — confirmed contents (from `src/datagen/generated/minecolonies/data/minecolonies/tags/items/feed.json`): `minecraft:wheat`, `minecolonies:durum`, `#forge:crops/wheat`.
  - Combat-readiness recovery needs an item in the `minecolonies:leather` tag (`ModTags.leather`) — confirmed contents (`leather.json`): `minecraft:saddle`, `minecraft:leather` — unless the item is specifically `Items.SADDLE`, which instantly resets combat cooldown to 0 instead of a partial `recovery = 4.0f × (primarySkillLevel / 20)` amount. Since Saddle is itself in the `leather` tag, using a saddle to "ready" a mount both resets cooldown to 0 *and* satisfies the tag check in one step.
- **Roundup of wild horses** (`gatherMounts()` / `HERDER_GATHER_MOUNTS`, rolled at `ROUND_UP_CHANCE = 15%`, throttled to at most once per 100 ticks via `roundupCooldown`): scans a hardcoded 30-block radius (`findNearbyUnstabledHorses()`) around the building position — **not** the building's claimed schematic area like normal herder animal searches — for any `AbstractHorse` that isn't already a cavalry mount with a rider/reservation, leashes it, and walks it to the next free stall position.
- **Patrol distance**: `getPatrolDistance()` overrides the normal guard formula by multiplying it by `CAVALRY_PATROL_RANGE_BOOST = 1.5`. Patrol frequency is separately throttled by the `PATROL_INTERVAL` setting (minutes between patrols, persisted via `lastPatrolTime`/`minutesSinceLastPatrol()`), and patrol targets prefer another building's `patrol_point`-tagged schematic locations, falling back to that building's ground-level corners — see `patrolPointForBuilding()`. Only other `BuildingStable`s and `BuildingGateHouse`s are eligible patrol destinations (`cavalryPatrolFilter()`).
- General guard settings (task/retreat/hire-trainee/patrol-and-follow-mode) are exposed through `STABLE_SETTINGS`, reusing the same `AbstractBuildingGuards` setting keys that Guard Tower/Barracks use — see **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for what those settings do in general; Stable doesn't add any guard-setting behaviour beyond the extra `PATROL_INTERVAL`.
