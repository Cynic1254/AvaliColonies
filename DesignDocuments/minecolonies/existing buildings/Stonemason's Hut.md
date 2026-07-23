### Function
Crafts stone-based building materials (stairs, slabs, walls, bricks, etc.) and, at the Domum Ornamentum "Architect's Cutter," stone-based decorative variants. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingStonemason`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingStonemason.java)
- AI: [`EntityAIWorkStonemason`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkStonemason.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingStonemason.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Domum Ornamentum module: `BuildingStonemason.DOCraftingModule extends AbstractDOCraftingBuildingModule`
- Datapack sources: `tags/items/stonemason_*.json`, `crafterrecipes/stonemason/*.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
No building-unlock research found for the Stonemason itself, but several of its custom recipes are individually research-gated — see Limits below.

### Skills
- Primary: Creativity
- Secondary: Dexterity
- Crafting speed skill: Creativity, Recipe improvement skill: Dexterity (no swap)

(`STONEMASON_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility is purely tag-based, no hand-written fallback** (unlike Sawmill's 75%-wood heuristic): `BuildingStonemason.CraftingModule.isRecipeCompatible()` defers entirely to the tag check, defaulting to `false` if undecided. Confirmed tag contents:
- `stonemason_product`: a large list including polished deepslate/bricks/tiles (+cracked variants), regular bricks, polished blackstone bricks, nether bricks (+chiseled), dripstone, chiseled quartz block/pillar/bricks, the full cut-copper family (all oxidation stages + waxed), magma block, snow, `#domum_ornamentum:brick_items`/`extra_block_items`, `#forge:stone`/`cobblestone`/`sandstone`, `#minecraft:stone_bricks`/`slabs`/`stairs`/`walls`
- `stonemason_product_excluded`: the Mechanic/Dyer/Sawmill product tags, `#minecraft:trim_templates`, lectern, piston, the **netherite upgrade smithing template itself** (so the Stonemason can never learn to produce that, even though its ingredient list overlaps with stone items), prismarine/prismarine bricks (those come from a **stonemason custom recipe** instead, see below — the exclusion prevents the tag system from teaching a duplicate/conflicting version), and two Domum Ornamentum paper-extra items
- `stonemason_ingredient`: an even larger list — brick, stone brick family, nether brick family, deepslate brick/tile family, popped chorus fruit, purpur family, prismarine shard/crystals, smooth stone, obsidian/crying obsidian, the full deepslate/blackstone family, the full copper family, basalt family, tuff, `#minecraft:terracotta`, `#minecolonies:glazed_terracotta`, `#forge:stone`/`cobblestone`/`end_stones`/`sandstone`, `#minecolonies:concrete`, `#domum_ornamentum:brick_items`/`extra_block_items`, `#minecraft:stairs`/`slabs`/`walls`
- `stonemason_ingredient_excluded`: stick, `#minecraft:logs`/`planks`, Mechanic/Dyer ingredient tags
- `stonemason_do_ingredient` (Domum Ornamentum only): stone, calcite, mud bricks, the quartz family, netherrack, blackstone (+chiseled/cracked), the prismarine family, end stone bricks

**Custom (datapack) recipes**, from `crafterrecipes/stonemason/`:
- `sandstone.json` (Cobblestone + Sand → Sandstone, no gates) / `red_sandstone.json` (Cobblestone + Red Sand → Red Sandstone, no gates).
- `prismarine.json` — Cobblestone + Prismarine Shard → Prismarine, **no research or level gate**.
- `prismarine_bricks.json` — a similarly ungated conversion recipe.
- `end_stone.json` — 8× Sandstone + 1 Ender Pearl → 8× End Stone, gated by the `minecolonies:effects/knowledgeoftheendunlock` research effect.
- `sifter_mesh_flint.json` — like the Blacksmith's iron mesh recipe, the Stonemason crafts the Sifter building's **flint** mesh upgrade from a single Flint, gated by `minecolonies:effects/sifterflintunlock` — this is the tier *below* the Blacksmith's iron mesh in the Sifter's mesh-upgrade research chain (see `researches/unlockable/flintmesh.json` → `ironmesh.json` → presumably further tiers for diamond/string mesh).

No building-specific settings beyond the standard `RECIPE_MODE`.
