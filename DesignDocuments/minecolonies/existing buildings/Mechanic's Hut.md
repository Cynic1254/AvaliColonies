### Function
Crafts redstone/mechanical items (rails, pistons, minecarts, hoppers, lighting, storage blocks, etc.), acts as the Domum Ornamentum "catch-all" cutter (see Limits), and crafts the top (diamond) tier of the Sifter's mesh chain. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingMechanic`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingMechanic.java)
- AI: [`EntityAIWorkMechanic`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkMechanic.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingMechanic.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Domum Ornamentum module: `BuildingMechanic.DOCraftingModule extends AbstractDOCraftingBuildingModule`
- Datapack sources: `tags/items/mechanic_*.json`, `crafterrecipes/mechanic/sifter_mesh_diamond.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Mechanic's Hut itself is gated behind research: [`whatyaneed.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/whatyaneed.json) ("What ya need") requires a **Blacksmith at level 3**, costs 64 Redstone, chains off "Hitting Iron!" (see the Blacksmith doc), and its effect (`blockhutmechanic`) unlocks the Mechanic's Hut for placement. The Sifter's diamond mesh recipe is separately research-gated — see Limits.

### Skills
- Primary: Knowledge
- Secondary: Agility
- Crafting speed skill: Knowledge, Recipe improvement skill: Agility (no swap)

(`MECHANIC_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility**, `BuildingMechanic.CraftingModule.isRecipeCompatible()`: tag check first, and if undecided, falls back to accepting any Minecart output or any Hopper block output. Confirmed tag contents:
- `mechanic_product`: a large "redstone-and-utility" grab-bag — `#minecolonies:storage_blocks`, `#minecraft:rails`/`buttons`/`wooden_pressure_plates`, all vanilla pressure plates, blue/packed ice, nether wart block, daylight detector, comparator, lever, piston, sticky piston, tripwire hook, enchanting table, jack o'lantern, lantern, sea lantern, soul lantern/torch, end rod, torch, ender/trapped chest, fire charge, conduit, respawn anchor, shulker box, slime ball, glow item frame, spyglass
- `mechanic_product_excluded`: spectral arrow, hay block, lead
- `mechanic_ingredient`: `#forge:dusts/redstone`, `#forge:ores/redstone`, `#forge:storage_blocks/redstone`, `#minecolonies:storage_blocks`, blaze rod, slime ball, gunpowder, ender pearl, ender eye, redstone torch, glowstone dust, dried kelp block, amethyst shard
- `mechanic_ingredient_excluded`: empty

**The Mechanic is the Domum Ornamentum "catch-all" cutter**: unlike every other DO-capable building (Sawmill, Stonemason, Fletcher, Glassblower), which each validate DO ingredients against their *own* tag, `BuildingMechanic.DOCraftingModule.getStaticIngredientValidator()` explicitly **accepts any ingredient not already claimed by the Sawmill, Fletcher, Stonemason, or Glassblower's own DO-ingredient checks** (`.or(...)` chained across all four, then negated). In other words, the Mechanic is the fallback that picks up every Domum Ornamentum recipe none of the material-specific crafters wanted — a deliberate design choice worth knowing if you're adding a new DO-integrated material and wondering which building will end up teaching it by default.

**Sifter's diamond-tier mesh** (`sifter_mesh_diamond.json`, in `crafterrecipes/mechanic/`) is crafted here, not by the Sifter itself — completing the full mesh chain documented in the Sifter's own doc (String → Fletcher, Flint → Stonemason, Iron → Blacksmith, **Diamond → Mechanic**). Gated by the `minecolonies:effects/sifterdiamondunlock` research effect, which auto-unlocks once the colony's Sifter reaches building level 5.

No building-specific settings beyond the standard `RECIPE_MODE`.
