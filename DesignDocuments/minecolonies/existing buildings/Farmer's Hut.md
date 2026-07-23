### Function
Farms crops on assigned Fields, and crafts a handful of farm-adjacent items (seeds, carved pumpkin, hay bales, coarse dirt/composted dirt, mud bricks). Requires Farm Fields (a `FarmField` building extension, placed separately from the hut) — the field's own block/schematic tells the Farmer what to plant and how big that plot is.

- Building: [`BuildingFarmer`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingFarmer.java)
- Crafting module: `BuildingFarmer.CraftingModule extends AbstractCraftingBuildingModule.Crafting`, purely tag-based (`farmer_product`/`_excluded`, `farmer_ingredient`/`_excluded`), no hand-written fallback
- Fields module: `BuildingFarmer.FarmerFieldsModule extends BuildingExtensionsModule`, capped at `building.getBuildingLevel()` fields
- Datapack sources: `tags/items/farmer_*.json`, `crafterrecipes/farmer/*.json` (2 files, confirmed — see Limits)

### Levels
Max building level: 5. Confirmed tag-driven recipe cap (`2^level × 5`) alongside a field cap equal to the building level:
1. 10 max recipes, 1 field
2. 20 max recipes, 2 fields
3. 40 max recipes, 3 fields
4. 80 max recipes, 4 fields
5. 160 max recipes, 5 fields

### Research
No building-unlock research found for the Farmer's Hut — consistent with it being an early/starter building. Reaching **Farmer level 3** is itself the trigger for two other buildings' unlock research: "Biodegradable" (Composter) and, transitively, "Let it Grow" (Plantation) — see those buildings' docs.

### Skills
- Primary: Stamina
- Secondary: Athletics

(`FARMER_CRAFT`/`FARMER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Fields are a separate placeable object, not part of the hut's own schematic**: `FarmField` is a `BuildingExtensionRegistries`-registered building extension, assigned/unassigned independently of the hut. `canAssignExtensionOverride()` requires the field to already have a seed chosen before the Farmer can be assigned to work it.
- **Reserved inventory**: the currently-assigned seed for every owned field is kept at up to 64 units each (`getRequiredItemsAndAmount()`), plus a hoe and an axe (any tier).
- **`canEat()`** blocks eating the seed type of any owned field, and separately blocks eating Wheat outright — the same reserved-crop pattern used elsewhere (e.g. Cowhand's wheat, Shepherd's wheat).
- **`canBeGathered()` always returns true**, overriding the normal "only gatherable while it has an active task" rule most crafters follow — explicitly because the Farmer both gathers (walks fields) and crafts, so it needs to be interruptible/gatherable at any time, the same override the Lumberjack uses for the same reason.
- The Farmer's `CraftingModule.getAdditionalRecipesForDisplayPurposesOnly()` synthesizes JEI-only entries for every discovered crop (both MineColonies' own `MinecoloniesCropBlock`s and vanilla `CropBlock`s), each tied to the crop's real loot table and preferred farmland/biome — informational only, not real taught recipes, mirroring the same JEI-loot-table-display pattern documented in the herding and crafting shared docs.
- **Confirmed custom recipes** (`crafterrecipes/farmer/`, real taught recipes, not JEI-only): `carved_pumpkin.json` (Pumpkin → Carved Pumpkin, requires Shears, no other gates) and `mud.json` (Dirt + `minecolonies:large_water_bottle` → Mud, with a `large_bottle` loot-table bonus roll — presumably returning the emptied bottle, the same pattern seen on several Baker recipes).
- **Confirmed `farmer_product` tag contents** (governs tag-discovered recipes beyond the two custom ones above): Hay Bale, `#forge:seeds`, Composted Dirt, Melon, Coarse Dirt, Fermented Spider Eye, Glistering Melon Slice, Mud Bricks, Packed Mud, Muddy Mangrove Roots — Carved Pumpkin actually comes from the custom recipe above rather than this tag. `farmer_product_excluded` is empty.
