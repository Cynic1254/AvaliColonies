### Function
Converts organic materials into Compost (or, optionally, Dirt) using Barrels found in its own schematic. Root of a small unlock chain: reaching Composter level 3 gates the Florist's own unlock research.

- Building: [`BuildingComposter`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingComposter.java)
- Item-allow-list module: `ITEMLIST_COMPOSTABLE` (an `ItemListModule` keyed to `COMPOSTABLE_LIST`) — governs which organic items the Composter will actually accept as compost input.

### Levels
Max building level: 5. No level-scaling formula for barrel throughput exists — barrel *count* is purely schematic-determined (see Limits), with no numeric per-level progression beyond the standard recipe-adjacent mechanics most buildings share.

### Research
[`biodegradable.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/biodegradable.json) ("Biodegradable") requires a **Farmer's Hut at level 3**, costs 64 Bone Meal, and its effect (`blockhutcomposter`) unlocks the Composter's Hut for placement. It's also the direct parent of both "Let it Grow" (Plantation) and "Flower Power" (Florist) — the Composter's own unlock research sits at the root of two other buildings' unlock chains, even though neither of those buildings is a Composter upgrade prerequisite themselves (both actually gate off Farmer/Composter *levels*, not the Composter's placement directly — see those buildings' docs for the precise chains).

### Skills
- Primary: Stamina
- Secondary: Athletics

(`COMPOSTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Barrels are schematic-detected, no cap**: `registerBlockPosition()` records every `ModBlocks.blockBarrel` found in the building's blueprint (`barrels` list). There are no limits on how many composters a single worker can use — nothing in `BuildingComposter` caps the barrel list length or throttles how many the worker can service, unlike e.g. the Alchemist's skill-based cap on simultaneous brewing stands. More barrels in the schematic directly means more parallel composting, with no level or skill gate in between.
- **`PRODUCE_DIRT`** setting: toggles whether the Composter's output is Dirt instead of Compost.
- **Reserved inventory** (`keepX`): unlimited (`Integer.MAX_VALUE`) reservation of anything on the `COMPOSTABLE_LIST` — i.e. the Composter never gives away compostable materials to the request system regardless of quantity, the same "never ship this away" pattern used for the Smelter's ore reservation.
