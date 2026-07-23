### Function
Grows and harvests flowers on Composted Dirt patches found in its own schematic, feeding from Compost supplied by (or produced alongside) the Composter's Hut economy. Which flower species are even growable is tiered by building level, separately from where the florist is physically allowed to plant.

- Building: [`BuildingFlorist`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingFlorist.java)
- Item-allow-list module: `FLORIST_ITEMS` (an `ItemListModule` keyed to `BUILDING_FLOWER_LIST`) — lets the player explicitly restrict which of the level-available flowers the Florist actually grows.

### Levels
Max building level: 5. Confirmed exact species tiering, `BuildingFlorist.getPlantablesForBuildingLevel()`:
- **Levels 0–1**: only Poppy and Dandelion.
- **Level 2**: any item in the `#minecraft:small_flowers` tag (broadens considerably).
- **Levels 3–5**: every plantable flower the compatibility manager knows about, with no further restriction — reaching level 3 is effectively "all flowers unlocked," not a level 3/4/5 progressive widening.

### Research
[`flowerpower.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/flowerpower.json) ("Flower Power") requires a **Composter at level 3**, costs 64 Compost, chains off "Biodegradable" (see the Composter's Hut doc), and its effect (`blockhutflorist`) unlocks the Florist for placement.

### Skills
- Primary: Dexterity
- Secondary: Agility

(`FLORIST_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Plantable ground is schematic-detected, independent of the species-tier system above**: `registerBlockPosition()` records every `ModBlocks.blockCompostedDirt` position found in the building's own blueprint as valid plant-ground (`plantGround`). This governs *how many flowers can be growing at once* (one per composted-dirt tile), entirely separate from *which species* are available (governed by building level, above). A larger/smaller composted-dirt footprint in the schematic directly scales planting capacity regardless of level.
- **`getFlowerToGrow()`** picks a random species from the current level's allowed set, filtered by whatever the player has excluded via the `FLORIST_ITEMS` allow-list — so the settings list only ever *restricts* the level-determined set, it can't add species the level hasn't unlocked yet.
- **Reserved inventory** (`keepX`): a full stack of Compost (the growing medium/fertilizer) and 1 shears (any tier, for harvesting).
