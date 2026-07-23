> Shared behaviour reference for all "crafter" buildings — anything with a `CraftingModule` inner class and a `*_WORK`/`*_CRAFT` pair in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java): Blacksmith, Sawmill, Stonemason, Baker, Cook/Chef, Fletcher, Glassblower, Dyer, Concrete Mixer, Crusher, Sifter, Smelter/Stone Smeltery, Alchemist, Enchanter, Mechanic, Nether Worker, and (partially) Farmer/Plantation/Florist/Lumberjack.
>
> **Where the datapack data actually lives:** the default researches, item tags, and custom crafter recipes are **not** under `src/main/resources` — they're generated sources under `src/datagen/generated/minecolonies/data/minecolonies/` (subfolders `researches/`, `tags/items/`, `crafterrecipes/`, `loot_tables/`, etc.), produced by this mod's datagen providers rather than hand-authored in `main`. All tag/recipe/research content cited below and in per-building docs is read from that folder unless stated otherwise.

### Core classes
- Assignment module: [`CraftingWorkerBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/CraftingWorkerBuildingModule.java) (extends the herder docs' `WorkerBuildingModule`) — adds `craftingSpeedSkill` and `recipeImprovementSkill`. If constructed with the short (5-arg) constructor these default to primary/secondary; several buildings pass them explicitly and **reversed** from primary/secondary (see Skills note below).
- Recipe-holding module: [`AbstractCraftingBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/AbstractCraftingBuildingModule.java), always subclassed per-building as a `CraftingModule` inner class. It ships four ready-made "policy" base classes to extend instead of the abstract class directly:
  - `.Crafting` — normal 3×3 workbench-style recipes (`ModCraftingTypes.SMALL_CRAFTING`/`LARGE_CRAFTING`), intermediate block must be `Blocks.AIR`.
  - `.Smelting` — furnace recipes, intermediate must be `Blocks.FURNACE`.
  - `.Brewing` — brewing-stand recipes, intermediate must be `Blocks.BREWING_STAND`.
  - `.Custom` — supports no vanilla-discovered crafting type at all; used for crafters who can *only* execute pre-authored `CustomRecipe`s (e.g. `SimpleCraftingModule` for Builder/Miner, and `AbstractDOCraftingBuildingModule` — see below).
- Domum Ornamentum integration: [`AbstractDOCraftingBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/AbstractDOCraftingBuildingModule.java) — a `.Custom` subtype used by Sawmill/Stonemason/Fletcher/Glassblower/Mechanic to also produce Domum Ornamentum's decorative block variants via the "Architect's Cutter" crafting type (`ModCraftingTypes.ARCHITECTS_CUTTER`). A recipe qualifies if its output item's namespace is `domum_ornamentum` and at least one input ingredient matches the building's ingredient tag (see below).
- Tag helper: [`CraftingUtils`](../../../minecolonies/src/main/java/com/minecolonies/api/util/CraftingUtils.java) — the actual tag-lookup logic used by every crafter's `isRecipeCompatible()`/`getIngredientValidator()`.
- Tag registry: [`ModTags`](../../../minecolonies/src/main/java/com/minecolonies/api/items/ModTags.java) / [`TagConstants`](../../../minecolonies/src/main/java/com/minecolonies/api/util/constant/TagConstants.java) — defines which job names have a tag family wired up at all (see table below).
- Custom/datapack recipes: [`CustomRecipe`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/crafting/CustomRecipe.java) + [`CustomRecipeManager`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/crafting/CustomRecipeManager.java), loaded from `data/<namespace>/crafterrecipes/<jobname>_custom/*.json` (or the DO variant's own key) — see the Datapack section below.

### How a crafter decides what it can learn
Every vanilla/modded recipe in the game gets wrapped as an `IGenericRecipe` and offered to each crafting module via `isRecipeCompatible()`. The decision chain (using Blacksmith as the concrete example — [`BuildingBlacksmith.CraftingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBlacksmith.java)) is:

1. **Crafting-type gate** (from the policy base class): is this even a small/large workbench recipe using no intermediate block? If not, reject immediately.
2. **Building-specific override** (hand-written per crafter, e.g. Blacksmith explicitly rejects any recipe using Leather — "that's the fletcher's responsibility" — and explicitly *accepts* any tool/armor/weapon recipe from `ModEquipmentTypes`, even one that would otherwise be tag-excluded).
3. **Tag-based fallback** — `CraftingUtils.isRecipeCompatibleBasedOnTags(recipe, jobName)`, which checks, in order: is the *output* on the `<job>_product_excluded` tag (reject) or `<job>_product` tag (accept)? If undecided, is any *input* on `<job>_ingredient_excluded` (reject) or `<job>_ingredient` (accept)? If still undecided → not compatible.

This means **the primary way an addon/datapack controls "what can this crafter learn" is by adding items to five per-job item tags**, all under `data/minecolonies/tags/items/`:

| Tag suffix | Meaning | Example path |
|---|---|---|
| `<job>_product` | Explicitly allow this item as a craftable output | `data/minecolonies/tags/items/blacksmith_product.json` |
| `<job>_product_excluded` | Explicitly forbid this item as an output (checked first, wins over inclusion) | `.../blacksmith_product_excluded.json` |
| `<job>_ingredient` | Explicitly allow this item as an ingredient | `.../blacksmith_ingredient.json` |
| `<job>_ingredient_excluded` | Explicitly forbid this item as an ingredient | `.../blacksmith_ingredient_excluded.json` |
| `<job>_do_ingredient` | Extra ingredient allowance *only* for Domum Ornamentum cutter recipes (`includeDoRules=true` path) | `.../blacksmith_do_ingredient.json` |

`<job>` is the job's registry path (`ModJobs.*_ID.getPath()`), **not** always the building name — check [`TagConstants`](../../../minecolonies/src/main/java/com/minecolonies/api/util/constant/TagConstants.java) for the exact string per job. As of this source tree, the job names wired up with a tag family are: `baker`, `blacksmith`, `cook`, `dyer` (+ `dyer_smelting`), `farmer`, `fletcher`, `glassblower` (+ `glassblower_smelting`), `mechanic`, the Plantation building (`ModBuildings.PLANTATION_ID`, not a job path), `sawmill`, `stonemason`, `stone_smeltery`, and the special pseudo-job `reduceable` (recipe-improvement exclusion, see below). Crafters *not* in that list (Crusher, Sifter, Smelter, Alchemist, Enchanter, Chef, Nether Worker, Concrete Mixer) either have no tag-based gate at all (falling through `isRecipeCompatible` to whatever the hand-written override decides) or use a bespoke non-tag check — check each building's own `isRecipeCompatible()` override to be sure.
Registering a brand-new job's tag family requires a code change (`TagConstants`/`ModTags.init()`), not just a datapack — the five-tag pattern only exists for jobs the mod authors explicitly wired up.

### Recipe-slot cap
Every crafting module has a maximum number of recipes it can simultaneously "know" (`getMaxRecipes()`):

```
increase = 1 + colonyResearchEffectStrength("recipes")
if module.canLearnManyRecipes():  // true by default; override to false to restrict
    increase *= 5
maxRecipes = floor(2 ^ buildingLevel * increase)
```

With no completed research and the default `canLearnManyRecipes() == true`, this gives the familiar **10 / 20 / 40 / 80 / 160** progression seen across most crafting huts (levels 1–5) — this is a **generic formula**, not something re-derived per building, so per-building docs just state the resulting numbers. A completed research that adds to the `RECIPES` effect increases every crafter's cap simultaneously, colony-wide.

### Recipe improvement (skill-based ingredient reduction)
On each successful craft, `improveRecipe()` rolls a chance (`min(5%, 0.0625% × craftCount + 0.0625% × recipeImprovementSkillLevel)`) to permanently reduce one ingredient's required count by 1 for that specific worker's copy of the recipe — but only if:
- the output isn't tagged `reduceable_product_excluded`, and
- the specific ingredient being reduced is tagged `reduceable_ingredient` (and has count > 1).

This is a fully **datapack-controlled** mechanic: without populating the `reduceable_ingredient`/`reduceable_product_excluded` tags, this never triggers for any custom recipe. A public in-colony chat message announces the improvement.

### Crafting speed vs. recipe improvement — the primary/secondary swap
Most crafters use the 5-arg `CraftingWorkerBuildingModule` constructor, where `craftingSpeedSkill = primary` and `recipeImprovementSkill = secondary` (no surprises). **Baker, Dyer, Fletcher, Glassblower, and Stone Smeltery** instead pass the 7-arg constructor with the last two skills **reversed** relative to primary/secondary — e.g. Dyer is hired/leveled on primary Creativity/secondary Dexterity, but crafting *speed* is driven by Dexterity and recipe *improvement* by Creativity. Worth double-checking per building rather than assuming primary always means "the crafting skill."

### Recipe priority when multiple recipes could fulfill a request
If more than one learned recipe could produce the requested item, and the building's `RECIPE_MODE` setting (`AbstractCraftingBuildingModule.RECIPE_MODE`, a `CrafterRecipeSetting`) is set to **Max Stock**, the crafter picks whichever recipe's largest-quantity ingredient currently has the most stock sitting in the colony's warehouse(s) — a simple "use up what we already have a lot of" heuristic (`getFirstRecipe()` in `AbstractCraftingBuildingModule`). Otherwise it just uses the first compatible recipe in learned order (which the player can manually reorder in the crafting GUI via `switchOrder()`).

### Datapack-authored recipes (`crafterrecipes`)
Beyond tag-gated vanilla recipes, a crafter can be taught bespoke recipes that don't correspond to any real vanilla/modded crafting recipe, via JSON files under `data/<namespace>/crafterrecipes/<crafter-key>/*.json` (loaded by `CustomRecipeManager`; `<crafter-key>` is `getCustomRecipeKey()` — usually `<job>_<moduleId>`, e.g. `lumberjack_custom`). Two JSON shapes are supported:

- **`"type": "recipe"`** — a single recipe, fields: `crafter` (the recipe-key string), `inputs` (list of `{"item":..., "count":...}`), `result` (output item id, optional `count` at top level), `secondary`/`alternate-output` (extra or alternative outputs), `intermediate` (block id, defaults to air), `tool` (required equipment type), `loot-table` (a loot table id purely for JEI display of possible bonus drops — see the animal-herding shared doc for the same JEI-only pattern), `research-id`/`not-research-id` (research(es) that must/mustn't be completed), `min-building-level`/`max-building-level` (per-recipe level gate, independent of the recipe-slot cap above), `must-exist` (only teach this recipe if a matching "precursor" recipe with the same output is already known — lets you layer an "improved" version on top of a base one), `show-tooltip` (surface the recipe requirements in the item's JEI/inventory tooltip).
- **`"type": "recipe-template"`** — generates one recipe per item in a given `tag`, with an `include`/`exclude` string-contains `filter`, and `[NS]`/`[PATH]`/`[PATH:find=replace]` placeholders substituted into the nested `recipe` object's item ids. See [`strip_logs.json`](../../../minecolonies/src/main/resources/data/minecolonies/crafterrecipes/lumberjack/strip_logs.json) for a concrete example (teaches the Lumberjack to strip every logged item in `minecraft:logs`, producing the stripped/wood-conversion variants as alternate outputs).
- **`"type": "remove"`** with `recipe-id-to-remove` deletes a previously-loaded custom recipe by id.

Custom recipes are re-checked against `isValidForBuilding()` continuously (research/level gates can turn on or off at runtime), and precisely one un-improved "classic" copy of a `must-exist`-chained recipe is kept per output once a matching improved one is confirmed present.

### The generic crafting AI loop
Most crafters (Blacksmith, Sawmill, Stonemason, Baker, Chef, Fletcher, Glassblower, Dyer, Concrete Mixer, Crusher, Sifter, Alchemist, Enchanter, Mechanic, Nether Worker — check each building's own `EntityAIWork*` to confirm) don't need a bespoke AI at all; they extend [`AbstractEntityAICrafting<J, B>`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/AbstractEntityAICrafting.java), which drives the whole "take a crafting request → gather items → hit the workstation N times → produce output" loop generically:

- **`IDLE`** — while there's no queued task, the worker wanders to/sits at any schematic-tagged `sitting`/`sit_in`/`sit_out`/`stand_in`/`stand_out` position (rain-aware: prefers indoor tags while raining) rather than idling in place.
- **`START_WORKING` → `GET_RECIPE`** — finds the first *fulfillable* recipe matching the current request (`ICraftingBuildingModule.getFirstFulfillableRecipe`), checks required tool, and computes how many iterations are actually possible given current stock.
- **`QUERY_ITEMS`** — verifies/gathers the exact ingredient amounts needed (`GATHERING_REQUIRED_MATERIALS` if short).
- **`CRAFT`** — repeatedly "hits" the workstation. The number of hits needed per craft iteration is **not fixed** — it's `10 / min(craftingSpeedSkillLevel/2 + 1, 50) × 3`, i.e. higher crafting-speed skill (see the primary/secondary swap note above) genuinely reduces the number of AI ticks per craft, capped at skill level 50 for this purpose (`MAX_LEVEL`). On the final hit, `executeCraftingAction()` actually consumes ingredients and produces output (`IRecipeStorage.fullfillRecipeAndCopy`), damages the held tool by 1 durability if one was required, and calls `improveRecipe()` (see the recipe-improvement mechanic above).
- Secondary/byproduct outputs that aren't the recipe's primary output are queued as an automatic internal delivery request to the nearest Warehouse rather than kept by the worker.

### Loot tables — two distinct uses, don't conflate them
There are actually **two separate mechanisms** in this codebase that involve loot tables, and it matters which one you're looking at:

1. **JEI-display-only approximation** — [`LootTableAnalyzer`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/crafting/LootTableAnalyzer.java) statically parses a loot table's JSON (pools, weights, a handful of functions/conditions) to *approximate* possible drops for display purposes only — used e.g. for animal loot tables in the herding shared doc, or for a `CustomRecipe`'s `loot-table` field when it's just informational. It explicitly never generates real loot and is never used to determine an actual crafting outcome.
2. **Real gameplay mechanic** — when a `CustomRecipe`'s `loot-table` field is actually wired into the recipe's execution, `RecipeStorage.fullfillRecipe()` genuinely rolls that loot table using real `LootParams` (including a `LUCK` parameter derived from the crafter's skill via `getCraftingLuck()`) to decide the actual bonus item(s) produced. This is a real vanilla-schema loot table (pools/rolls/entries/weights, exactly as used for mob drops or chest loot) living under `data/minecolonies/loot_tables/recipes/**` in the datagen output. Confirmed real (non-JEI) examples found so far, if you want a template to copy: the Sifter's gravel-sifting recipes (see its doc), the Smelter's dynamically-synthesized ore-breaking recipes (which instead point at the ore *block's own vanilla loot table*, not a MineColonies-authored one — see Smelter doc), the Enchanter's tome-to-book recipes (see its doc), and the Nether Worker's expedition-trip recipes (see its doc). If you want to add or rebalance a "chance of bonus item" mechanic for a crafter, this — not the animal-loot-table JEI display — is the pattern to copy.

### Furnace mechanics: two different base AI classes, easy to conflate
Furnace-using crafters split into two genuinely different implementations, not one shared mechanic:

- **[`AbstractEntityAIUsesFurnace`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/AbstractEntityAIUsesFurnace.java)** — used only by the ore **Smelter** among crafters covered in this reference. No cap on simultaneous furnace use: every lit furnace registered to the building gets extra manual ticks once a second, `accelerationTicks = (primarySkillLevel / 10) × 2` calls to `furnace.serverTick()`, on top of normal vanilla ticking. Pure tick-acceleration, keyed to primary skill.
- **[`AbstractEntityAIRequestSmelter`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/AbstractEntityAIRequestSmelter.java)** — used by **Baker, Chef, Dyer, Glassblower, and Stone Smeltery**. This one has a genuine cap: `getMaxUsableFurnaces() = min((primarySkillLevel / 10) + 1, number of fueled furnaces in the building)` limits how many furnaces can be actively smelting at once, while a *separate* `accelerateFurnaces()` gives whichever furnaces are burning extra ticks based on **secondary** skill: `(secondarySkillLevel / 10) × 2` per second. So for these five buildings, primary skill controls *how many* furnaces can run simultaneously and secondary skill controls *how fast* each one goes — mirroring the Alchemist's brewing-stand mechanic (see that building's doc) almost exactly, just applied to furnaces instead of brewing stands.

Both mechanisms exist in the same codebase and are easy to mix up when only skimming one building's AI class — always check which base class a specific crafter's AI actually extends before describing its furnace behavior.

**`AbstractEntityAIRequestSmelter` also has a confirmed self-output fuel exclusion**: `getActivePossibleFuels()` explicitly strips the currently active recipe's own primary output (and its input) from the list of items the worker will use as furnace fuel before topping up a furnace's fuel slot. So any of the five buildings above that smelts something which also happens to be a valid fuel (e.g. the Stone Smeltery's logs→charcoal — see the Brick Yard doc) will never burn its own in-progress output to make more of itself, a real and deliberate rule rather than an emergent side effect of the request system.

### Extending: adding a new crafter building
1. Create a `CraftingModule` inner class extending the closest matching policy base (`.Crafting`/`.Smelting`/`.Brewing`/`.Custom`), overriding `isRecipeCompatible()` and `getIngredientValidator()` — for a plain tag-gated crafter, just delegate to `CraftingUtils.isRecipeCompatibleBasedOnTags`/`getIngredientValidatorBasedOnTags` with your job's name, following Sawmill/Stonemason as templates; add any hand-written special-case logic (like Blacksmith's leather/tool-type overrides) on top.
2. Register the job's tag family in `TagConstants`/`ModTags.init()` if you want datapack-controlled recipe gating.
3. Register `*_WORK` (`CraftingWorkerBuildingModule`) and `*_CRAFT` (your `CraftingModule`) constants in `BuildingModules`, referenced together from the building's `BuildingEntry.Builder` in `ModBuildingsInitializer`.
4. If you want a generic worker-AI crafting loop rather than fully custom behaviour, check `core/entity/ai/workers/crafting/` for the closest existing `EntityAIWork*` to extend/mirror — most simple crafters use a shared generic crafting AI rather than one bespoke class per job.
