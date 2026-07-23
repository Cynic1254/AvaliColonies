### Function
Dyes items (wool, concrete powder, Domum Ornamentum decoratives, firework stars, etc.) and can also **un-dye colored wool back to white**. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingDyer`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingDyer.java)
- AI: [`EntityAIWorkDyer`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkDyer.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingDyer.CraftingModule extends AbstractCraftingBuildingModule.Crafting` — this one has substantial hand-written logic beyond the usual tag check (see Limits)
- Smelting module: `BuildingDyer.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — separate `dyer_smelting` tag family
- Datapack sources: `tags/items/dyer_*.json`, `crafterrecipes/dyer/*.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Dyer's Hut itself is gated behind research: [`rainbowheaven.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/rainbowheaven.json) ("Rainbow Heaven") requires a **Composter at level 3**, costs 64 Poppies, chains off "Biodegradable," and its effect (`blockhutdyer`) unlocks the Dyer's Hut for placement.

The Dyer's AI (`EntityAIWorkDyer`) extends `AbstractEntityAIRequestSmelter`, which genuinely caps simultaneous furnace use at `min((primarySkillLevel / 10) + 1, fueled furnace count)` — this matches the older "usable furnace count = PrimarySkill / 10 + 1" framing exactly, since for this class it really is a hard cap, not just acceleration. A separate mechanism accelerates whichever furnaces are actively burning, based on *secondary* skill. See the shared crafting doc's furnace-mechanics section for the general pattern (also shared by Baker, Chef, Glassblower, and Stone Smeltery) and how it differs from the plain-acceleration-only class used by the ore Smelter.

### Skills
- Primary: Creativity
- Secondary: Dexterity
- Crafting speed skill: **Dexterity**, Recipe improvement skill: **Creativity** — swapped relative to primary/secondary (see shared doc).
- **Recipe improvement is explicitly disabled** for the Dyer: both `CraftingModule.improveRecipe()` and `SmeltingModule.improveRecipe()` are overridden to no-ops ("don't improve any dyeing recipes"). No amount of skill or repeated crafting will ever trigger the ingredient-reduction mechanic here, unlike every other crafter.

(`DYER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility is purely tag-based** for both modules (no hand-written fallback for *acceptance*, unlike Blacksmith/Fletcher). Confirmed tag contents:
- `dyer_product`: `#forge:dyes`, firework star, red nether bricks, and a long list of Domum Ornamentum colored brick/cobblestone/cactus/paper "extra" variants (one per dye color)
- `dyer_product_excluded`: the Concrete Mixer's product tag (`#minecolonies:concrete_powder`) — so the Dyer never competes with the Concrete Mixer for coloring concrete powder, even though both conceptually involve dyes+blocks
- `dyer_ingredient` / `dyer_smelting_product`: both just `#forge:dyes`
- `dyer_ingredient_excluded`: empty

**Hand-written "undye wool" mechanic** (`CraftingModule`, independent of both tags and the normal recipe-teaching flow): the Dyer building can always attempt to fulfill a request for White Wool even without ever having explicitly learned that recipe.
- `getFirstRecipe()`/`getFirstFulfillableRecipe()` are both overridden: if no taught recipe matches White Wool, the module looks at **every non-white wool color**, sums how much of each color is sitting across all colony Warehouses, and synthesizes a one-off recipe (`<that color> Wool + White Dye → White Wool`) for whichever color the colony currently has the **most** of — i.e. it automatically launders your largest excess of colored wool back to a fungible white supply, rather than asking the player to pick.
- This synthesized recipe is registered on the fly via `RecipeStorage.builder()...checkOrAddRecipe()` and is **not** persisted in the module's normal recipe list — it's recomputed fresh (and can pick a different color) every time it's needed.
- `getAdditionalRecipesForDisplayPurposesOnly()` also synthesizes JEI-only entries showing every `DyeableLeatherItem` in the game dyed with each of the 16 dye colors, purely so players can see "the Dyer can theoretically do this" without those recipes actually being teachable/learned recipes.

No building-specific settings beyond the standard `RECIPE_MODE`.
