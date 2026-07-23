### Function
Converts logs/planks into wooden building materials (stairs, slabs, fences, doors, etc.) and, at the Domum Ornamentum "Architect's Cutter," wood-based decorative variants. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingSawmill`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingSawmill.java)
- AI: [`EntityAIWorkSawmill`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkSawmill.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingSawmill.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Domum Ornamentum module: `BuildingSawmill.DOCraftingModule extends AbstractDOCraftingBuildingModule`
- Datapack sources: `tags/items/sawmill_{product,ingredient}{,_excluded}.json`, `tags/items/sawmill_do_ingredient.json` — **no `crafterrecipes/sawmill/` folder exists**, so unlike Blacksmith/Stonemason the Sawmill has no bespoke datapack-authored recipes; everything it knows comes from tag-gated vanilla/modded recipes or the 75%-wood heuristic below.

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Sawmill itself is gated behind research: [`woodwork.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/woodwork.json) ("Woodworking") requires a **Lumberjack's Hut at level 3**, costs 64 Planks, and its effect (`blockhutsawmill`) unlocks the Sawmill for placement. This in turn is the parent research of "Stringwork" (see Fletcher's doc).

### Skills
- Primary: Knowledge
- Secondary: Dexterity
- Crafting speed skill: Knowledge, Recipe improvement skill: Dexterity (no swap)

(`SAWMILL_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Recipe eligibility is Building code with a distinctive three-tier fallback** (`BuildingSawmill.CraftingModule.isRecipeCompatible()`):
  1. Tag check first — confirmed contents:
     - `sawmill_product`: `#minecraft:planks`, `#minecraft:wooden_slabs`, `#minecraft:wooden_stairs`, bamboo mosaic block/slab/stairs, bamboo hanging sign, `#minecraft:boats`, `#minecraft:chest_boats`, `minecolonies:barrel_block`
     - `sawmill_product_excluded`: the Mechanic's product tag (so the Mechanic keeps priority on shared outputs), magma cream
     - `sawmill_ingredient`: `#minecraft:logs`, cactus
     - `sawmill_ingredient_excluded`: `#forge:ingots`, `#forge:stone`, `#forge:dusts/redstone`, `#forge:string`
  2. If the tags don't give a definite answer, falls back to a **percentage heuristic**: sums how many input slots are planks/logs (`ItemTags.PLANKS`/`LOGS`) or carry any tag whose path contains `"wood"`, versus total non-empty inputs. Accepted only if that fraction exceeds `MIN_PERCENTAGE_TO_CRAFT = 0.75` (75%) — this is how the Sawmill can pick up *un-tagged* wooden recipes (e.g. from other mods) automatically.
- **Domum Ornamentum ingredient validator** (`sawmill_do_ingredient`, confirmed contents: bamboo block, bamboo mosaic, bamboo planks, stripped bamboo block, crimson nylium, warped nylium) combines with the tag-based check and a hardcoded `PLANKS`/`LOGS` allowance — DO cutter recipes don't use the 75% heuristic at all.
- No building-specific settings beyond the standard `RECIPE_MODE`.
