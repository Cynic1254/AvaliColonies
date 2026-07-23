### Function
Grows sugar cane, cactus, bamboo, cocoa beans, vines, kelp, seagrass, sea pickles, glow berries, weeping/twisted vines, and crimson/warped fungi/roots on specialized Plantation Fields (a different building-extension type per plant, built differently depending on the target plant), and crafts paper/books/sugar/bamboo goods.

- Building: [`BuildingPlantation`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingPlantation.java)
- Crafting module: `BuildingPlantation.CraftingModule extends AbstractCraftingBuildingModule.Crafting`, purely tag-based (`plantation_product`/`_excluded`, `plantation_ingredient`/`_excluded` — note the tag family key is `ModBuildings.PLANTATION_ID`, a building id, not a job path like every other crafter's tag family)
- Fields module: `BuildingPlantation.PlantationFieldsModule extends BuildingExtensionsModule`

### Levels
Max building level: 5. Two **independently capped** numbers, easy to conflate:
- **Max fields** (`getMaxExtensionCount()`): `ceil(level / 2)` fields, **+1 more if the "Crop Rotation" research is completed** — 1/1/2/2/3 for levels 1–5 (2/2/3/3/4 with Crop Rotation).
- **Max concurrent plant types** (`getMaxConcurrentPlants()`): `ceil(level / 2)` — 1/1/2/2/3 for levels 1–5, **with no research bonus at all**.

Both numbers are always identical per level, and Crop Rotation increases the max fields for any given level by 1 — but the two caps diverge once Crop Rotation is researched: a level-5 Plantation with that research allows **4 fields but still only 3 distinct plant types worked concurrently**, so one plant type could occupy two fields simultaneously while a would-be 4th distinct type is blocked (`canAssignExtensionOverride()` checks the distinct-plant-type count specifically, not just the raw field count, before allowing a new field assignment).

### Research
[`letitgrow.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/letitgrow.json) ("Let it Grow") requires a **Farmer's Hut at level 3**, costs 16 Compost, chains off "Biodegradable," and its effect (`blockhutplantation`) unlocks the Plantation for placement. [`croprotation.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/croprotation.json) ("Crop Rotation") requires a **Plantation at level 3**, costs 32 Sugar Cane + 32 Cactus, chains off "Let it Grow," and its effect (`plantationlarge`) grants the +1 max-fields bonus above. Confirmed exactly which plant types are separately research-gated via `getRequiredResearchEffect()`: only the **Crimson** and **Warped** plant modules require anything extra — both need the `PLANTATION_NETHER` research effect, granted by [`gargamel.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/gargamel.json), which requires **both a Plantation and a Nether Mine (Nether Worker) at level 3**, chains off "Crop Rotation," and costs 16 Crimson Fungus + 16 Warped Fungus. Every other plant type (Sugar Cane, Cactus, Bamboo, Cocoa Beans, Vines, Kelp, Seagrass, Sea Pickles, Glow Berries, Weeping Vines, Twisting Vines) has no research requirement beyond the Plantation's own unlock.

### Skills
- Primary: Agility
- Secondary: Dexterity

(`PLANTATION_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Recipe eligibility is purely tag-based**, deferring entirely to `CraftingUtils.isRecipeCompatibleBasedOnTags(recipe, "plantation")` with no hand-written fallback.
- **`canEat()`** blocks eating whatever item any owned field currently produces, in addition to the standard checks — a Planter won't snack on its own sugar cane/cactus/etc.
- **Legacy field migration**: `BuildingPlantation` carries dedicated (marked "TODO: future, legacy") code to auto-migrate old-format field data attached directly to the hut into the newer standalone `PlantationField` extension system on load — a sign the field-assignment mechanism changed at some point in this mod's history and old colonies get silently upgraded.
- The Plantation's `CraftingModule.getAdditionalRecipesForDisplayPurposesOnly()` synthesizes one JEI-only entry per registered plant-extension type (output item, required inputs, required tool) — informational only, not a real taught recipe.
