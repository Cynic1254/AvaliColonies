### Function
Smelts stone-family materials (the furnace-based counterpart to the Stonemason's crafting-based stone processing). Despite the in-game name "Brick Yard," the underlying schematic/job/skill names are all "Stone Smeltery" (`stonesmeltery`). Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingStoneSmeltery`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingStoneSmeltery.java)
- AI: [`EntityAIWorkStoneSmeltery`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkStoneSmeltery.java) — generic crafting loop, see shared doc
- Smelting module: `BuildingStoneSmeltery.SmeltingModule extends AbstractCraftingBuildingModule.Smelting` — this building has **only** a smelting module, no plain `.Crafting` module at all, unlike most other crafters
- Datapack sources: `tags/items/stonesmeltery_*.json` — no `crafterrecipes/stonesmeltery/` (or similarly-named) folder exists, so it's entirely tag-gated, no bespoke custom recipes.

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

### Research
The Stone Smeltery itself is gated behind research: [`theflintstones.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/theflintstones.json) ("The Flintstones") requires a **Stonemason at level 1**, costs 64 Brick, chains off "Stonecake," and its effect (`blockhutstonesmeltery`) unlocks the Stone Smeltery (Brick Yard) for placement. This is a sibling research to "Rocking Roll" (Crusher) — both branch off the same "Stonecake" parent from a level-1 Stonemason.

### Skills
- Primary: Athletics
- Secondary: Dexterity
- Crafting speed skill: **Dexterity**, Recipe improvement skill: **Athletics** — swapped relative to primary/secondary (see shared doc; the same swap pattern as Baker/Dyer/Fletcher/Glassblower).

(`STONESMELTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility is purely tag-based**, no hand-written fallback: `isRecipeCompatible()` defers entirely to `CraftingUtils.isRecipeCompatibleBasedOnTags(recipe, "stonesmeltery")`, defaulting to `false` if undecided — the same strict pattern as the Stonemason (as opposed to the Sawmill's percentage-based heuristic fallback). Confirmed tag contents:
- `stonesmeltery_product`: brick, smooth basalt/quartz/sandstone/red sandstone/stone, popped chorus fruit, sponge, coal, charcoal, nether brick, deepslate, `#forge:stone`, `#minecraft:terracotta`/`stone_bricks`, `#minecolonies:glazed_terracotta`
- `stonesmeltery_product_excluded`: empty
- `stonesmeltery_ingredient`: just **`#minecolonies:stonemason_product`** — i.e. the Stone Smeltery's valid ingredients are defined as "whatever the Stonemason can output," a direct cross-building tag reference rather than its own hand-curated list
- `stonesmeltery_ingredient_excluded`: empty

The Stone Smeltery also smelts cobblestone into stone, stone bricks into cracked stone bricks, clay balls into bricks, clay blocks into terracotta, terracotta into glazed terracotta, all types of stone into all types of smooth stone, and chorus fruit into popped chorus fruit — all confirmed against the `stonesmeltery_product` tag. The commonly-cited "logs into charcoal" claim could **not** be confirmed after checking the `stonesmeltery_ingredient` tag directly (only references `#minecolonies:stonemason_product`, no logs), the crafting module's `isRecipeCompatible()` logic, and the `EntityAIWorkStoneSmeltery`/`AbstractEntityAIRequestSmelter` AI classes (neither contains special-case log handling). Genuinely unresolved — this may simply be an inaccurate claim rather than an implementation this reference failed to find; logs→charcoal smelting may belong to a different building (e.g. the ore Smelter) or not exist as a Stone Smeltery function at all in this checkout.

**Charcoal cannot be used as fuel to smelt more charcoal**, observed in-game and now confirmed exactly in code: the Stone Smeltery's AI (`EntityAIWorkStoneSmeltery`, via `AbstractEntityAIRequestSmelter`) builds its list of usable fuels through `getActivePossibleFuels()`, which starts from the full allowed-fuel list and then explicitly strips out whichever item is the *currently active recipe's own primary output* (and its input) before topping up a furnace's fuel slot: `possibleFuels.removeIf(stack -> compareIgnoreStackSize(stack, currentRecipeStorage.getPrimaryOutput()))`. So while a charcoal recipe is in progress, charcoal is temporarily struck from the list of things this worker will use as fuel — a real, deliberate exclusion built directly into the fuel-selection logic, not an emergent side effect of the request/reservation system. In effect, a recipe is simply never allowed to consume its own output as one of its own inputs, and fuel counts as an input for this purpose. This same `AbstractEntityAIRequestSmelter` base class (and therefore this same exclusion) is shared by Baker, Chef, Dyer, and Glassblower too — any of them smelting something that also happens to be valid fuel would hit the identical restriction.

No building-specific settings beyond the standard `RECIPE_MODE`. The Stone Smeltery's AI extends `AbstractEntityAIRequestSmelter`, the same base class as Baker/Chef/Dyer/Glassblower — simultaneous furnace use is genuinely capped at `min((primarySkillLevel / 10) + 1, fueled furnace count)`, with a separate secondary-skill-driven acceleration for whichever furnaces are burning. See the shared crafting doc's furnace-mechanics section.
