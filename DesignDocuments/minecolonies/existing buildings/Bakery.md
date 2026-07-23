### Function
Bakes bread, cake, cookies, pumpkin pie, and a wide range of modded baked goods, via a genuine **two-stage dough → bake** pipeline rather than a single recipe per item. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingBaker`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBaker.java)
- AI: [`EntityAIWorkBaker`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkBaker.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingBaker.CraftingModule extends AbstractCraftingBuildingModule.Crafting` — makes the raw dough/batter
- Smelting module: `BuildingBaker.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — bakes the dough in a furnace into the finished item; shares the **same** `baker` tag family as the crafting module (unlike Dyer/Glassblower, which split crafting/smelting into distinct tag families)
- Datapack sources: `tags/items/baker_*.json`, **`crafterrecipes/baker/*.json`** (25 files) — this folder absolutely exists and is where nearly everything the Baker actually makes comes from; don't rely on the tag files alone to understand this building.

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

**Both the crafting and smelting modules are entirely disabled below building level 3** — `getSupportedCraftingTypes()` on both returns an empty set unless `building.getBuildingLevel() >= 3`. This governs *tag-discovered* vanilla recipes; it does **not** block the custom dough/bake recipes below, which have their own independent per-recipe `min-building-level`/`max-building-level` gates (see Limits) — so, confusingly, a level 1–2 Bakery can still bake some things (Bread, Cookies) via its custom recipes while having zero tag-discovered recipes available.

### Research
No building-unlock or recipe-specific research found for the Baker.

### Skills
- Primary: Knowledge
- Secondary: Dexterity
- Crafting speed skill: **Dexterity**, Recipe improvement skill: **Knowledge** — swapped relative to primary/secondary (see shared doc).

(`BAKER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**The core mechanic is a two-stage custom recipe chain**, confirmed from `crafterrecipes/baker/`:
1. **Dough/batter stage** — a `baker_crafting` recipe (handled by the `.Crafting` module, normal hit-based crafting) turns raw ingredients into an intermediate dough/batter item. E.g.:
   - `bread_dough.json`: 3 Wheat → `minecolonies:bread_dough`, **capped at `max-building-level: 2`** (an unusual *upper* bound rather than a floor — Bread dough specifically stops being craftable once the building exceeds level 2, presumably because higher-tier goods are meant to replace it).
   - `cookie_dough.json`: 2 Wheat + 2 Cocoa Beans → 8× `cookie_dough`, `min-building-level: 2`.
   - `raw_pumpkin_pie.json`: Pumpkin + Sugar + Egg → `raw_pumpkin_pie`, `min-building-level: 3`.
   - `cake_batter.json`: 3 Wheat + 3 `large_milk_bottle` + 2 Sugar + 1 Egg → `cake_batter`, `min-building-level: 4`, and also carries a `loot-table` (`minecolonies:recipes/large_bottle`) for a bonus roll (presumably returning empty large bottles) alongside the batter.
   - `golden_bread.json` (level 5 only): 8× `minecolonies:durum` + 1 Gold Ingot → 4× Golden Bread — a modded wheat variant (`durum`) feeding a modded output, no baking stage needed for this one.
   - This is a clean per-level unlock progression: **level 3** unlocks `sugary_bread.json` ("sweet bread": 8× Durum + Honey Bottle, plus a `glass_bottle` loot-table bonus roll), **level 4** unlocks `milky_bread.json`/`smilky_bread.json` ("milk-infused bread": 8× Durum + a Large Milk/Soy-Milk Bottle, plus a `large_bottle` loot-table bonus roll), and **level 5** unlocks Golden Bread as above.
2. **Baking stage** — a matching `baker_smelting` recipe (handled by the `.Smelting` module, real furnace, `intermediate: minecraft:furnace`) turns that dough into the finished item, at the **same** `min-building-level` as its dough stage: `bread.json` (dough→Bread), `cookie.json` (dough→Cookie, level 2), `cake.json` (batter→Cake, level 4), `pumpkin_pie.json` (raw pie→Pumpkin Pie, level 3). The Baker's AI (`EntityAIWorkBaker`) extends `AbstractEntityAIRequestSmelter`, which caps simultaneous furnace use at `min((primarySkillLevel / 10) + 1, fueled furnace count)` and separately accelerates whichever furnaces are burning based on secondary skill — see the shared crafting doc's furnace-mechanics section.

This two-stage split is exactly *why* Bread/Cake/Cookie/Pumpkin Pie are absent from the `baker_product` tag and explicitly listed in `cook_product_excluded`/`baker_product_excluded` (see the Chef's Kitchen doc) — **they were never meant to be tag-discovered at all**; they're bespoke datapack recipes specific to the colonist pipeline, which is exactly what the `crafterrecipes` folder (as opposed to the `recipes` folder used for real player-facing vanilla recipes) is for.

**Other confirmed custom recipes**, all `baker_crafting` (no baking stage needed): `chorus_bread.json` (8× Durum + 1 Chorus Fruit → 4× Chorus Bread, gated by the `minecolonies:effects/knowledgeoftheendunlock` research effect — the same "Know the End" research that gates the Stonemason's End Stone recipe), `flatbread`, `lembas_scone` (Durum + Butter + Honey Bottle, with a `glass_bottle` loot-table roll for a returned empty bottle), `manchet`/`manchet_dough`, `milky_bread`/`smilky_bread`/`sugary_bread`, `mintchoco_cheesecake`, `muffin`/`muffin_dough`, `mushroom_pizza` (level 4, a 9-ingredient recipe: 3× Durum, 2× Tomato, Garlic, Cheddar Cheese, 2× Brown Mushroom), `water_bottle`/`water_jug` (level 3, Glass Bottle → Water Potion — filling water bottles from the Baker's own supply, not a Fisherman/Cook mechanic).

**Confirmed tag contents** (govern any *additional*, non-custom recipes the Baker might pick up from vanilla/other mods):
- `baker_product`: only `minecolonies:cornmeal`, `cheese_pizza`, `plain_cheesecake`, `apple_pie` — everything else the Baker makes comes from the custom recipes above, not this tag
- `baker_product_excluded`: Bread, Cake, Cookie, Pumpkin Pie, packed mud, `#minecolonies:cook_product` — reinforcing that those four are handled exclusively by the custom-recipe pipeline, never by tag-based auto-discovery
- `baker_ingredient`: `#forge:crops/wheat`
- `baker_ingredient_excluded`: empty

**Other Building-code behavior**:
- `CraftingModule.getRequiredItemsAndAmount()` is overridden to always request/hold **128 units (2 stacks)** of every ingredient across all currently-known recipes, a more generous buffer than the generic default (which only reserves what's needed for in-flight requests).
- `BuildingBaker.canEat()` blocks eating Wheat (reserved ingredient) and blocks eating whatever item is the input or output of the worker's current in-progress crafting request — a Baker mid-bake won't snack on its own dough or bread, but has no restriction while idle.
- No building-specific settings beyond the standard `RECIPE_MODE`.
