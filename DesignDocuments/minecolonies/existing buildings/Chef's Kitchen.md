### Function
Cooks a large variety of hearty meals (stews, soups, ravioli, roasted meats, etc.) for the colony's restaurant system. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**. Not to be confused with the plain **Cook**/Restaurant building (`BuildingCook`, documented as **Dining Hall**) — that building has no crafting module at all and only serves/distributes food that's already been made; the Chef, in `BuildingKitchen`, is the one that actually cooks.

- Building: [`BuildingKitchen`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingKitchen.java)
- AI: [`EntityAIWorkChef`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkChef.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingKitchen.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Smelting module: `BuildingKitchen.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — shares the **same** `cook` tag family as the crafting module (like Baker, unlike Dyer/Glassblower)
- Datapack sources: `tags/items/cook_*.json`, **`crafterrecipes/chef/*.json`** (15 files) — this folder exists (an earlier pass of this doc incorrectly claimed otherwise; always open the folder, not just check for its existence). Unlike the Baker's two-stage dough→bake pipeline, these are plain single-stage `chef_crafting` recipes with no separate smelting/baking step.

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
No building-unlock or recipe-specific research found for the Chef.

### Skills
- Primary: Creativity
- Secondary: Knowledge
- Crafting speed skill: **Knowledge**, Recipe improvement skill: **Creativity** — swapped relative to primary/secondary (see shared doc).

(`CHEF_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Recipe eligibility**: tag check first (`cook_product`/`_excluded`, `cook_ingredient`/`_excluded`); if undecided, falls back to accepting any output that's edible (`FoodUtils.EDIBLE`) either directly or after being smelted (covers things like a raw-to-cooked furnace conversion the tag system didn't anticipate). This governs only what the Chef might learn *beyond* its own custom recipes — most of the interesting meals come from `crafterrecipes/chef/` directly, entirely bypassing the tag system.
- **Furnace use is capped by skill, not just accelerated**: the Chef's AI (`EntityAIWorkChef`) extends `AbstractEntityAIRequestSmelter`, which caps simultaneous furnace use at `min((primarySkillLevel / 10) + 1, fueled furnace count)` and separately accelerates whichever furnaces are burning based on secondary skill — see the shared crafting doc's furnace-mechanics section for the general pattern (also shared by Baker, Dyer, Glassblower, and Stone Smeltery).
- **Confirmed custom recipes** (`chef_crafting`, all single-stage, no baking, all gated at `min-building-level: 4`, no research gates): `lamb_stew.json` (Onion, Garlic, 2× Carrot, 2× Potato, Brown Mushroom, Cabbage, Mutton), `schnitzel.json` (2× Durum, Manchet Bread, Porkchop, Egg, Potato), `tacos.json` (Nether Pepper, Garlic, Tortillas, Beef, Tomato), `borscht.json` (Garlic, Onion, Chicken Broth, Potato, 2× Beetroot, output ×2), `eggplant_dolma.json` (Eggplant, Feta Cheese, Garlic, Tomato, Durum, Onion), `fish_dinner.json` (2× Garlic, Cabbage, Cod, Brown Mushroom), `hand_pie.json` (Durum, Garlic, Brown Mushroom, Onion, Mutton), `pita_hummus.json` (Flatbread, 2× Chickpea, Eggplant, Onion, Garlic), `ramen.json` (Kelp, Garlic, Onion, Raw Noodle, Soy Sauce), `spicy_eggplant.json` (2× Nether Pepper, 2× Eggplant, Garlic, Onion, Bowl), `steak_dinner.json` (2× Garlic, Onion, Beef, 2× Potato), `stew_trencher.json` (Manchet Bread, Tomato, Cabbage, Onion), `stuffed_pepper.json` (Cooked Rice, Bell Pepper, Tomato, Carrot, Garlic, Eggplant), `stuffed_pita.json` (Flatbread, Tomato, Onion, Eggplant, Garlic), `sushi_roll.json` (Cooked Rice, Salmon, Garlic, Dried Kelp, Onion, output ×2).
- **Confirmed `cook_product` contents** (governs tag-discovered recipes only) are extensive — essentially all of the mod's "proper meal" items not already covered by a custom recipe: baked salmon, butter, cabochis, cheddar/feta cheese, congee, cooked rice, eggplant dolma, pasta (plain/tomato), pepper hummus, pottage, rice ball, tofu, cream cheese, soy sauce, ravioli (cheese/meat/veggie), broths and soups (chicken, corn chowder, pea, potato, squash, veggie, eggdrop), spicy grilled chicken, kebab, mint jelly/tea, polenta, yogurt (plain and with berries), kimchi, pierogi, veggie quiche, mutton dinner, tortillas.
- **`cook_product_excluded`**: **Bread, Cake, Cookie, and Pumpkin Pie are excluded from the Chef**, alongside the Baker's own outputs (cheese pizza, cheesecake, apple pie, cornmeal). As confirmed on the Bakery's own doc, this is because those four go through the Baker's dedicated two-stage dough→bake custom-recipe pipeline (`crafterrecipes/baker/`) rather than the tag-discovery system at all — colonist-specific recipes like these live in `crafterrecipes`, not the `recipes` folder used for real player-facing vanilla recipes, so they were never going to show up via tags in the first place.
- `cook_ingredient` covers raw meats/fish, potato, kelp (+dried/block), egg, milk (vanilla bucket and the modded large-bottle variants). `cook_ingredient_excluded` is just `#forge:crops/wheat` — the mirror image of the Baker's ingredient tag, keeping wheat-based inputs firmly on the Baker's side of the line.
- **`BuildingKitchen.canEat()`** has the identical "don't snack on your own in-progress recipe" restriction documented for the Baker.
- No building-specific settings beyond the standard `RECIPE_MODE`.
