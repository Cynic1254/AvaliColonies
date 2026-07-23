### Function
Raises rabbits for meat and hide. Shares its core behaviour with all other herders — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the full state machine.

- Job: `rabbitHerder` — [`JobRabbitHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobRabbitHerder.java)
- Building: [`BuildingRabbitHutch`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingRabbitHutch.java)
- AI: [`EntityAIWorkRabbitHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkRabbitHerder.java)
- Herding module: unlike most herders, Rabbit Hutch does **not** define its own `HerdingModule` subclass — it's registered as a bare `AnimalHerdingModule` instance directly inline in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java) under `RABBITHERDER_HERDING` (compatible animal: `Rabbit`; breeding item: 2× Carrot). This is the simplest possible herder registration — a good template if your new animal doesn't need custom JEI recipe display (compare to Chicken/Cow/Sheep, which subclass `AnimalHerdingModule` for that reason).

### Levels
Max building level: 5. Max rabbits housed = `building level × 2` (default multiplier, soft cap — see shared doc):
1. 2 housed
2. 4 housed
3. 6 housed
4. 8 housed
5. 10 housed

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Primary: Agility
- Secondary: Athletics

(`RABBITHERDER_WORK` in `BuildingModules`.)

### Limits
- **Housing cap** — shared herder mechanism, see above.
- **Butchering** does not scale damage; instead each swing has a **miss chance** of `(100 − primarySkillLevel) / 5` out of 100 (`EntityAIWorkRabbitHerder.butcherAnimal()` override) — higher Agility (primary skill) means more consistent kills, same pattern as Chicken Herder but keyed off the *primary* skill instead of secondary.
- `BuildingRabbitHutch`'s constructor calls `keepX.put(stack -> Items.CARROT == stack.getItem(), new Tuple<>(STACKSIZE, true))` — this reserves up to a full stack of carrots in the building's inventory so they aren't hauled off/consumed by other systems before the herder needs them for breeding. `keepX` is a general `AbstractBuilding` mechanism (inherited by every building) for "don't let the request/delivery system give this item away."
- `canEat()` is overridden to block the worker from eating carrots for the same reason Cowboy/Swineherd block their breeding items — reserved stock, not colony food.
- Breeding on/off is exposed via the `RABBITHERDER_SETTINGS` module (`AbstractBuilding.BREEDING`).
