### Function
Crafts glass and glass-based items, with its own furnace (smelting) module for raw glass production and a Domum Ornamentum cutter module for glass decoratives. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingGlassblower`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingGlassblower.java)
- AI: [`EntityAIWorkGlassblower`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkGlassblower.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingGlassblower.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Smelting module: `BuildingGlassblower.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — a **separate** tag family (`glassblower_smelting`) from the crafting module's own tags
- Domum Ornamentum module: `BuildingGlassblower.DOCraftingModule extends AbstractDOCraftingBuildingModule`
- Datapack sources: `tags/items/glassblower_*.json`, `tags/items/glassblower_smelting_product.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`), tracked independently per module (crafting vs. smelting each have their own `getMaxRecipes()`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Glassblower's Hut itself is gated behind research: [`thoselungs.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/thoselungs.json) ("Those Lungs") requires a **Smelter at level 3**, costs 64 Glass, chains off a research called "Hot," and its effect (`blockhutglassblower`) unlocks the Glassblower's Hut for placement.

The Glassblower's AI (`EntityAIWorkGlassblower`) extends `AbstractEntityAIRequestSmelter`, which genuinely caps simultaneous furnace use at `min((primarySkillLevel / 10) + 1, fueled furnace count)` — this matches the older "usable furnace count = PrimarySkill / 10 + 1" framing exactly, since for this class it really is a hard cap, not just acceleration. A separate mechanism accelerates whichever furnaces are actively burning, based on *secondary* skill. See the shared crafting doc's furnace-mechanics section for the general pattern (also shared by Baker, Chef, Dyer, and Stone Smeltery) and how it differs from the plain-acceleration-only class used by the ore Smelter.

### Skills
- Primary: Creativity
- Secondary: Focus
- Crafting speed skill: **Focus**, Recipe improvement skill: **Creativity** — swapped relative to primary/secondary (see shared doc and Fletcher's doc for the same pattern).

(`GLASSBLOWER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
Both crafting-type modules are purely tag-based, no hand-written fallback (`isRecipeCompatible()` on both just defers to `CraftingUtils.isRecipeCompatibleBasedOnTags`, defaulting to `false`). Confirmed tag contents are notably sparse compared to other crafters:
- `glassblower_product` / `glassblower_product_excluded`: both **empty** in this repo — meaning the crafting-module recipe list is currently governed entirely by the ingredient tag below (an output is accepted if its input passes, since the output tags never resolve a decision) plus whatever custom recipes exist (none found for this building — no `crafterrecipes/glassblower/` folder exists).
- `glassblower_ingredient`: `#forge:glass`, `#forge:glass_panes`
- `glassblower_ingredient_excluded`: `#forge:dyes`
- `glassblower_smelting_product` (separate tag, used only by the Smelting module): `#forge:glass` — i.e. the furnace module is scoped to "produces something glass," a much simpler check than the crafting module's ingredient-based one.

No building-specific settings beyond the standard `RECIPE_MODE`.
