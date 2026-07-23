### Function
Chops trees within range, optionally replants saplings, and can additionally cultivate Nether fungi (Crimson/Warped) by bonemealing them at a skill-dependent rate — a distinct secondary mechanic from ordinary tree-chopping.

- Building: [`BuildingLumberjack`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingLumberjack.java)
- Crafting module: `BuildingLumberjack.CraftingModule extends AbstractCraftingBuildingModule.Custom`, `canRecipeBeAdded()` hardcoded to `false` (same defensive-no-op pattern as several other `.Custom` modules — doesn't block the real custom-recipe population path; see the crafting shared doc). Custom recipes confirmed: `strip_logs.json`/`strip_stems.json` (recipe-template recipes teaching the Forester to strip every logged item, see the crafting shared doc for the full template mechanics).

### Levels
Max building level: 5. Tree search is not a flat radius: it starts at `SEARCH_RANGE = 50` blocks and, if no trees are found, expands in `SEARCH_INCREMENT = 5` steps up to a `SEARCH_LIMIT` of 150 blocks total — the "150 block radius" describes the *maximum* the search can expand to, not the default range. This search behavior only applies when no manual restriction area is set; with `RESTRICT` enabled, the Forester instead searches only within the player-defined rectangle regardless of these constants.

### Research
No building-unlock research found for the Forester's Hut — consistent with it being an early/starter building.

### Skills
- Primary: Strength
- Secondary: Focus

(`FORESTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Won't touch schematic-protected or "planted-on-cobblestone" trees, both confirmed in `Tree.java`**: `checkTree()` explicitly rejects any candidate tree whose base log sits directly on Cobblestone (`world.getBlockState(basePos.below()).getBlock() != Blocks.COBBLESTONE`) — a real, hardcoded exclusion, not just a player convention. Separately, `addAndSearch()` checks every colony building's `isInBuilding()` bounds and refuses to add any log position that falls inside one — so a tree physically overlapping a placed building's schematic footprint won't have those logs queued for chopping (checked live against loaded buildings, not a one-time schematic snapshot).
- **`REPLANT`** setting: whether saplings are replanted after chopping.
- **`RESTRICT`** setting + a player-defined rectangular area (`startRestriction`/`endRestriction`, settable via `setRestrictedArea()`): confines chopping to a specific region rather than the full radius. If enabled with no area actually defined yet, the setting auto-reverts itself (`shouldRestrict()` calls `.trigger()` to flip it back off) — a safety guard against silently doing nothing.
- **`DEFOLIATE`** setting: strips all leaves in the way, not just whichever block is directly blocking a path — useful for fully clearing a felled tree's canopy rather than leaving floating leaf clusters.
- **Nether fungus cultivation is a genuinely separate mechanic**: the Lumberjack tracks a set of planted Crimson/Warped fungus positions (`netherTrees`) and, once per colony tick, attempts to bonemeal-grow one of them. The success chance is `FUNGI_MODIFIER(10) + ceil(primarySkillLevel × (1 − FUNGI_MODIFIER/100))` out of 100 — i.e. a flat 10% baseline boosted further by Strength (primary skill) up to nearly guaranteed at very high skill. The `DYNAMIC_TREES_SIZE` setting is unrelated to fungi — it only matters if the "Dynamic Trees" compatibility mod is present: `Tree.checkTree()` refuses to mark a Dynamic Trees tree as harvestable unless its growth-radius blockstate property is at least this setting's value, so raising it makes the Forester wait for trees to grow further/larger before cutting them.
- **Reserved inventory** (`keepX`): axe and shears (any tier). Also reserves a full stack of every sapling type the compatibility manager knows about, *except* whichever ones are explicitly excluded via the `ITEMLIST_SAPLING` allow-list setting.
- **`canBeGathered()` always returns true**, the same override the Farmer uses and for the same reason — the Lumberjack both gathers and crafts (the log-stripping recipes), so it needs to remain interruptible/gatherable regardless of task state.
