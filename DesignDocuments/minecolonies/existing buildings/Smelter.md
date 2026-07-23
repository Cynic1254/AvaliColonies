### Function
Smelts ores into ingots and separately "breaks" raw ore blocks for bonus drops. Unusually among crafters, the Smelter's furnace-smelting side is **not** taught/learned recipes at all — it's handled directly by the AI. Shares foundational concepts with **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)** but deviates from the generic pattern in both of its modules.

- Building: [`BuildingSmeltery`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingSmeltery.java) (schematic name `smeltery`; job/skills are still "Smelter")
- AI: [`EntityAIWorkSmelter`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkSmelter.java) — extends [`AbstractEntityAIUsesFurnace`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/AbstractEntityAIUsesFurnace.java), **not** the generic `AbstractEntityAICrafting` loop described in the shared doc
- Smelting module: `BuildingSmeltery.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — deliberately inert, see Limits
- Ore-breaking module: `BuildingSmeltery.OreBreakingModule extends AbstractCraftingBuildingModule.Custom` — dynamically synthesizes its own recipes at runtime, see Limits
- Datapack sources: `tags/items/breakable_ore.json`, `tags/items/raw_ore.json`

### Levels
Max building level: 5. No recipe-slot cap applies to smelting (see Limits — it isn't a taught-recipe system at all). Ore-breaking Fortune level scales directly with building level (see Limits).

### Research
The Smelter itself is gated behind research: [`hot.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/hot.json) ("Hot!") requires a **Miner's Hut at level 2**, costs 4 Lava Buckets, and its effect (`blockhutsmeltery`) unlocks the Smelter for placement. This is also the parent research of "Those Lungs" (see the Glassblower doc).

### Skills
- Primary: Athletics
- Secondary: Strength
- Crafting speed skill: Athletics, Recipe improvement skill: Strength (no swap)

(`SMELTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Smelting is deliberately outside the recipe/tag system entirely**: `SmeltingModule.isRecipeCompatible()` **always returns `false`** and `isVisible()` **always returns `false`**, with the source comment "all 'recipes' are handled by the AI, and queried via the job." Concretely:
- `EntityAIWorkSmelter.requestSmeltable()` requests a generic `SmeltableOre` (or, if the `ores` `ItemListModule` setting has restricted the allowed list, a specific `StackList` of only the allowed ore items) directly from the request system — driven by `ICompatibilityManager.isOre()`/`getSmeltableOres()` (a config/compatibility-driven "is this an ore" check across all loaded mods), not by any `blacksmith`/`sawmill`-style product/ingredient tag pair.
- `isSmeltable()` additionally excludes anything tagged as a "breakable ore" (handled by the other module instead, see below) and anything the player has explicitly filtered out via the `ores` item list.
- The `MIN` setting (warehouse minimum reserve) and furnace count both feed into how large a smeltable-ore request batch is requested at once (`STACKSIZE × number of furnaces`).
- `extractFromFurnace()` gives a flat `+5 XP` per furnace collection, separate from the generic crafting loop's count-based XP.

**Ore-breaking is a second, entirely separate mechanic** — `OreBreakingModule` (a `.Custom` module) doesn't hold any static recipes either; instead, on every colony tick (`checkForWorkerSpecificRecipes()`) it dynamically builds one temporary `RecipeStorage` per item in the `minecolonies:breakable_ore` tag (confirmed contents: `#minecraft:coal_ores`, `iron_ores`, `copper_ores`, `gold_ores`, `redstone_ores`, `emerald_ores`, `lapis_ores`, `diamond_ores`, and `minecraft:nether_quartz_ore`) that the compatibility manager currently recognizes as breakable (`isBreakableOre`):
- Each synthesized recipe has **no primary output at all** — instead it carries the ore block's own real vanilla loot table (`Block.getLootTable()`) via the `loot-table` field, which is genuinely rolled at "craft" time (see the crafting shared doc's "two distinct loot-table uses" section — this is a **real** mechanic, not JEI display).
- `getCraftingTool()` is overridden to hand the worker a **virtual Diamond Pickaxe enchanted with Block Fortune at level `buildingLevel − 1`** purely for the loot roll (this tool never actually exists in the worker's inventory or gets damaged) — so a level 5 Smelter breaks ore blocks as if wielding Fortune IV, while a level 1 Smelter gets no Fortune bonus at all. This is a clean, generalizable example of "building level → virtual tool enchant level" if you want to add a similar mechanic elsewhere.
- `EntityAIWorkSmelter.breakOres()`/`checkForImportantJobs()` prioritize ore-breaking over furnace-collection whenever a fulfillable breaking "recipe" exists (i.e. the building actually has a breakable ore block item in stock).

**Reserved inventory** (Building code, `keepX`): unlimited ore items (`Integer.MAX_VALUE` — effectively "never give away ore"), plus up to 10 swords/tools/armor pieces held for... (not fully explored why the Smelter specifically reserves weapons/armor; possibly leftover/shared logic, worth double-checking if it matters for your addon).
