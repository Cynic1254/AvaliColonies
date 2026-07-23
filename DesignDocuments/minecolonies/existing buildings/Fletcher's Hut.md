### Function
Crafts arrows, bows/crossbows-adjacent gear, string, and leather items; also supplies one tier of the Sifter's mesh upgrade chain. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingFletcher`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingFletcher.java)
- AI: [`EntityAIWorkFletcher`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkFletcher.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingFletcher.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Domum Ornamentum module: `BuildingFletcher.DOCraftingModule extends AbstractDOCraftingBuildingModule`
- Datapack sources: `tags/items/fletcher_*.json`, `crafterrecipes/fletcher/*.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Fletcher's Hut itself is gated behind research: [`stringwork.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/stringwork.json) ("Stringwork") requires a **Sawmill at level 1**, costs 16 String, chains directly off "Woodworking" (see the Sawmill doc), and its effect (`blockhutfletcher`) unlocks the Fletcher's Hut for placement. Two of its own custom recipes are additionally research-gated (see Limits).

### Skills
- Primary: Dexterity
- Secondary: Creativity
- Crafting speed skill: **Creativity**, Recipe improvement skill: **Dexterity** — this is one of the buildings where crafting-mechanics skills are swapped relative to primary/secondary (see shared doc). Leveling Dexterity (primary) makes the Fletcher hire/XP faster, but it's Creativity that actually speeds up crafting.

(`FLETCHER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility**, `BuildingFletcher.CraftingModule.isRecipeCompatible()`: tag check first, and if undecided, falls back to accepting any `ArrowItem` output or any leather-material `DyeableArmorItem` output (i.e. dyeable leather armor is presumptively the Fletcher's, not the Blacksmith's — consistent with the Blacksmith's own explicit leather exclusion). Confirmed tag contents:
- `fletcher_product`: `#forge:string`, moss carpet
- `fletcher_product_excluded`: book, item frame
- `fletcher_ingredient`: `#minecraft:leaves`, `#forge:string`, `#minecraft:wool`, rabbit hide, leather, fishing rod
- `fletcher_ingredient_excluded`: `#forge:dyes`

**Custom (datapack) recipes**, from `crafterrecipes/fletcher/`:
- `flint.json` — 3× Gravel → 1 Flint, no gates.
- `string.json` — 1 White Wool → 4 String, no gates.
- `sifter_mesh_string.json` — crafts the Sifter's **string**-tier mesh (the base tier, from a single String), gated by `minecolonies:effects/sifterstringunlock`. This is the lowest rung of the mesh chain: String (Fletcher) → Flint (Stonemason) → Iron (Blacksmith) → Diamond (Mechanic), each auto-unlocked by research tied to the Sifter's own building level (see the Sifter's own doc for the full chain and what each mesh tier actually changes about sifting output).

No building-specific settings beyond the standard `RECIPE_MODE`.
