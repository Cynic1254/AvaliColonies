### Function
Digs a vertical shaft mine beneath the building and branches out mining nodes at fixed world-height tiers. Also hosts the Quarrier job, which redirects the assigned worker to dig a separate [Quarry](Quarry.md) building instead — see **[_Shared - Structure Building System](_Shared%20-%20Structure%20Building%20System.md)** for that relationship and the resource-bucket framework this building shares with the Builder's Hut.

- Building: [`BuildingMiner`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingMiner.java), extends `AbstractBuildingStructureBuilder`
- Miner assignment module: `MINER_WORK` (`MinerBuildingModule`) in `BuildingModules`
- Quarrier assignment module: `QUARRIER_WORK` (`MinerBuildingModule`), **on the same building**
- **Only one worker total, of either job**: `MinerBuildingModule.isFull()` checks `building.getAllAssignedCitizen().size() >= getModuleMax()` — i.e. against *every* citizen assigned to the building across both modules combined, not each module's own count — and both `MINER_WORK` and `QUARRIER_WORK` cap at 1. A Mine building therefore hosts exactly one worker, and which job they hold (Miner or Quarrier) determines whether it digs its own shaft or works an external Quarry.

### Levels
Max building level: 5. Mining depth is tiered by real world Y-coordinate, not a simple formula — confirmed from `BuildingMiner.MINING_LEVELS = [48, 16, -16, -100]` (commented in source as roughly Copper/Iron/Gold/Diamond height bands): `getDepthLimit()` walks down that list from the mine's own starting height, consuming one building level per tier crossed, until building levels run out. **The miner descends one tier per building level, and which real-world Y-tiers are available depends on where the mine was physically placed** — a mine placed below Y48 starts its first level at Y16, etc. A `MAX_DEPTH` setting can additionally clamp how deep the miner is allowed to go, bounded by the world's actual minimum build height + 5.

### Research
No building-unlock research found for the Mine.

### Skills
- Primary: Strength
- Secondary: Stamina

(`MINER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java); the Quarrier uses the same skill pair via its own `QUARRIER_WORK` module registration.)

### Limits
- **Resource requests are doubled when working alone**: `getResourceBatchMultiplier()` returns 4 (vs. the default 1 from the shared base class) whenever no Quarrier is currently assigned to this building — a Miner without a Quarrier sibling stocks up four nodes/levels ahead at once, presumably to reduce courier trips since a solo Miner has no one else competing for the building's resource stream.
- **Ladder/cobblestone shaft anchor points come from schematic tags**: `getLadderLocation()`/`getCobbleLocation()` read the `ladder`/`cobble` schematic tags from the building's own blueprint on first access (caching afterward) — these positions anchor where the vertical shaft actually starts and which direction it's rotated, computed from the vector between the two tagged points (`getRotationFromVector()`). A blueprint missing either tag will silently fail to resolve a shaft start.
- **Reserved inventory** (`keepX`): a full stack each of Ladders, Torches, and Cobblestone, plus pickaxe/shovel/axe/shears (any tier), plus a full stack of whatever block the `FILL_BLOCK` setting currently specifies (used to backfill unwanted spaces while excavating, default Cobblestone per `MINER_SETTINGS`' registration).
- Additional modules registered: `MINER_LEVELS` (`MinerLevelManagementModule` — tracks which mining "levels"/nodes exist and which is currently active; referenced by the Stable's cavalry-guard `patrolMine()` logic to find completed nodes to patrol), `MINER_GUARD_ASSIGN` — confirmed in `BuildingModules` to have **no server-side module class at all** (`null` producer, only a client-side `MinerGuardAssignModuleView`), the same pattern used by `WORKORDER_VIEW`/`CRAFT_TASK_VIEW`/`UNIVERSITY_RESEARCH` — i.e. it's a GUI-only element (likely for picking which guard escorts the miner) with no persistent server state of its own; whatever escort behavior exists must be driven from the Guard AI side by checking which Mine it's linked to, not from a dedicated module on the Mine.
