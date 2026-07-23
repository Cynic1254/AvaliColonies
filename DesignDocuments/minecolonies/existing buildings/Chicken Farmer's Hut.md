### Function
Raises chickens for eggs, feathers, and meat. Shares its core behaviour with all other herders — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the full state machine, and only the Chicken Herder–specific differences are documented here.

- Job: `chickenherder` — [`JobChickenHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobChickenHerder.java)
- Building: [`BuildingChickenHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingChickenHerder.java)
- AI: [`EntityAIWorkChickenHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkChickenHerder.java)
- Herding module: `BuildingChickenHerder.HerdingModule` (compatible animal: `Chicken`; breeding item: 2× Wheat Seeds)

### Levels
Max building level: 5. Max chickens housed = `building level × 2` (the default `ANIMAL_MULTIPLIER` in `AbstractEntityAIHerder`, not overridden here):
1. 2 chickens
2. 4 chickens
3. 6 chickens
4. 8 chickens
5. 10 chickens

This is a **soft cap** enforced by a butchering-probability curve, not a hard block — see the shared doc's enforcement table.

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Primary: Adaptability
- Secondary: Agility

(Defined by the `CHICKENHERDER_WORK` module producer in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Housed chicken cap** — Worker AI code, shared mechanism (see above).
- **Butchering damage** — Chicken Herder does *not* scale attack damage with skill like Cowboy/Swineherd; instead every swing has a **miss chance** of `(100 − secondarySkill) / 5` (out of 100), via an override of `butcherAnimal()` in `EntityAIWorkChickenHerder`. Higher Agility (secondary skill) → more reliable butchering, not harder hits.
- **Egg/feather pickup throttling** — `JobChickenHerder.pickupSuccess()` rolls `random.nextInt(primarySkillLevel) > 1` before letting a picked-up egg or feather actually count; low-skill Chicken Herders will sometimes "drop" the item they just picked up. This only applies to `Items.FEATHER` / `Items.EGG`, and is **Worker/Job code**, not a building setting.
- **Breeding toggle** — the `CHICKENHERDER_SETTINGS_BREEDING` settings module exposes the shared `AbstractBuilding.BREEDING` bool setting in the GUI.
- Chickens won't be cut off from spawning eggs/being bred by anything schematic-side; the pen area is whatever the building's claimed structure area covers (see shared doc — `WorldUtil.getEntitiesWithinBuilding`), so a larger blueprint footprint directly enlarges the effective herding pen.
