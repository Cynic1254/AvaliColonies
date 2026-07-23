### Function
Builds and upgrades other colony buildings incrementally from a staged resource "bucket," following work orders. See **[_Shared - Structure Building System](_Shared%20-%20Structure%20Building%20System.md)** for the resource-bucket/work-order framework this building shares with the Mine.

- Building: [`BuildingBuilder`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBuilder.java), extends `AbstractBuildingStructureBuilder`
- AI: [`EntityAIStructureBuilder`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/builder/EntityAIStructureBuilder.java). The shared doc's `AbstractEntityAIStructureWithWorkOrder` base is confirmed from `AbstractBuildingStructureBuilder`'s own imports.

### Levels
Max building level: 5. Which target level a given Builder's Hut may claim a work order for is governed by `canBeBuiltByBuilder(newLevel)`, confirmed in source as:
```
canBeBuiltByBuilder(newLevel) = (this building's own level + 1 == newLevel)
```
This is a **deliberate special case unique to the Builder's Hut itself**, not the general rule for what a builder can build — it exists to solve the bootstrap problem of *upgrading the Builder's Hut's own level*: you can't require a level-5 Builder's Hut to build a level-5 Builder's Hut, so this override is the one exception in the game letting a building be built to a level **one higher** than the builder's own current level, specifically for self-upgrades. For every other building in the colony, the normal rule applies — a builder needs a hut *at least* the target level to build it. The Builder's Hut is believed to be the only building with an override like this.

### Research
No building-unlock research found for the Builder's Hut.

### Skills
- Primary: Adaptability
- Secondary: Athletics

(`BUILDER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Manual vs. automatic work order acceptance**: the `MODE` setting (`AUTO_SETTING`/`MANUAL_SETTING`) controls `getManualMode()` — in manual mode the player must explicitly assign a work order to a specific Builder's Hut (`setWorkOrder(orderId, ...)`, with checks that the order isn't already claimed and that `canBuildIgnoringDistance` passes). Confirmed in [`WorkManager`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/workorders/WorkManager.java): in automatic mode, every colony tick iterates all unclaimed work orders against every structure-builder building with an assigned worker and no current work order, explicitly skipping any building whose `MODE` setting is `MANUAL_SETTING`, and assigns the first eligible AUTO-mode Builder's Hut that passes the order's own `canBuild()` check — this is "first eligible builder in building-iteration order," not necessarily "nearest," unless `canBuild()` itself factors in distance.
- **Daily mob purging**: `purgedMobsToday`/`hasPurgedMobsToday()`/`setPurgedMobsToday()` reset every in-game day (`onWakeUp()`). Confirmed exact trigger in `EntityAIStructureBuilder.killMobs()`: purging only happens if the Builder's Hut is at **building level 4 or higher** *and* the currently active work order is specifically a `BUILD` type (not an upgrade, repair, or removal) — every hostile `Monster` inside the target building's bounds is discarded outright, once per day, before construction proceeds.
- **`canEat()` is overridden** to block eating any item currently required for the in-progress structure (`requiresResourceForBuilding()`), the same reserved-stock pattern seen on several other buildings.
- **`BUILDING_MODE`** (a `BuilderModeSetting`) picks a structure-placement iteration order from `StructureIterators` (the Structurize library's set of placement patterns — e.g. different layer/fill orders), defaulting to the server config's `iteratorType`. This setting is **only selectable once the "Builder Modes" research has been completed** (`isActive()` checks the `BUILDER_MODE` research-effect strength) — otherwise it shows a "needs research" reason and stays locked to the default. **`USE_SHEARS`** is not actually a Builder-specific setting — it's defined once on `AbstractBuilding` itself and shared across several buildings (e.g. also read by the Lumberjack to decide whether to carry Shears or a Hoe as its secondary vegetation-clearing tool); for the Builder it likely governs the same shears-vs-hoe choice when clearing plants from a construction site. `FILL_BLOCK` defaults to Dirt per the module registration and controls what's used to backfill unwanted holes/caves encountered while building, mirroring the Miner's identically-named setting.
