### Function
Raises pigs for meat. Shares its core behaviour with all other herders — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the full state machine. This is the most minimal herder: no custom AI states or building settings beyond the shared ones.

- Job: `swineherder` — [`JobSwineHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobSwineHerder.java)
- Building: [`BuildingSwineHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingSwineHerder.java)
- AI: [`EntityAIWorkSwineHerder`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkSwineHerder.java)
- Herding module: like Rabbit Hutch, registered as a bare `AnimalHerdingModule` directly in `BuildingModules` under `SWINEHERDER_HERDING` (compatible animal: `Pig`; breeding item: 2× Carrot) rather than a dedicated subclass.

### Levels
Max building level: 5. Max pigs housed = `building level × 2` (default multiplier, soft cap — see shared doc):
1. 2 housed
2. 4 housed
3. 6 housed
4. 8 housed
5. 10 housed

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Primary: Strength
- Secondary: Athletics

(`SWINEHERDER_WORK` in `BuildingModules`.)

### Limits
- **Housing cap** — shared herder mechanism, see above.
- **Butchering damage** scales with Primary skill: `max(1.0, primarySkillLevel / 10.0)` — same formula as Cowboy.
- **Works during rain regardless of building level.** `SWINEHERDER_WORK`'s `WorkerBuildingModule` is constructed with `canWorkingDuringRain = true` — most other workers only get that perk once their building is fully upgraded (`canWorkDuringTheRain()` in [`WorkerBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/WorkerBuildingModule.java) is `building.getBuildingLevel() >= building.getMaxBuildingLevel() || canWorkingDuringRain`). This is a **Building/module registration** flag, not something the AI class decides.
- `BuildingSwineHerder.canEat()` blocks the worker from eating Carrots, reserved as the breeding item.
- `JobSwineHerder.getDiseaseModifier()` returns `2` — Swineherds are twice as likely to catch/spread whatever disease system uses this modifier, compared to the default of 1 for most other jobs. Worth checking `AbstractJob.getDiseaseModifier()` and the colony sickness manager if you're extending disease mechanics.
