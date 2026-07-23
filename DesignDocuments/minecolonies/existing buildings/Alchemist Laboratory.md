### Function
Brews potions at in-schematic brewing stands, crafts the Magic Potion, and passively grows Nether Wart (on Soul Sand) and Mistletoe (sheared from Oak Leaves) found within the building's structure. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingAlchemist`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingAlchemist.java)
- AI: [`EntityAIWorkAlchemist`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkAlchemist.java)
- Brewing module: `BuildingAlchemist.BrewingModule extends AbstractCraftingBuildingModule.Brewing` — real, tag-free vanilla brewing-stand recipes (potions), fully taught/learned like any `.Brewing` module
- Crafting module: `BuildingAlchemist.CraftingModule extends AbstractCraftingBuildingModule.Crafting`, but deliberately restricted to a single hardcoded output (see Limits)
- No datapack tag family exists for the Alchemist (no `alchemist_product`/`ingredient` tags) — brewing recipes come from vanilla's own brewing system, not a tag-gated pool. There **is** a `crafterrecipes/alchemist/` folder (a single file, `magicpotion.json`) — see Limits for what it actually teaches; an earlier pass of this doc missed it and incorrectly implied the Magic Potion's teaching mechanism was unresolved.

### Levels
Max building level: 5. The Brewing module's recipe-slot cap uses the standard formula (`2^level × 5`); the Crafting module effectively has only one real recipe regardless of level (the Magic Potion).
1. 10 max brewing recipes
2. 20 max brewing recipes
3. 40 max brewing recipes
4. 80 max brewing recipes
5. 160 max brewing recipes

### Research
The Alchemist Laboratory itself is gated behind research: [`alchemist.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/alchemist.json) ("Magic Potions") costs 16 Nether Wart, chains directly off "Open the Nether" (see the Nether Mine doc), has no additional building-level requirement of its own, and its effect (`blockhutalchemist`) unlocks the Alchemist Laboratory for placement. Separately, the Magic Potion recipe itself is gated by the `minecolonies:effects/consumepotions` research effect (see Limits).

### Skills
- Primary: Dexterity
- Secondary: Mana
- Crafting speed skill: Dexterity, Recipe improvement skill: Mana (no swap)

(`ALCHEMIST_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Which blocks the Alchemist can use are entirely schematic-driven**: `registerBlockPosition()` scans the building's structure for `Blocks.SOUL_SAND`, any `BlockTags.LEAVES` block, and `Blocks.BREWING_STAND`, recording every match's position (persisted to NBT). No soul sand in the blueprint means no Nether Wart farming; no leaves means no Mistletoe; no brewing stands means no potion brewing at all, regardless of building level. This is a **schematic-authoring-controlled limit**, not a level or setting.
- **The `CraftingModule` is deliberately crippled to a single item, taught via one custom recipe**: `getSupportedCraftingTypes()` returns an **empty set** (so it can never discover any vanilla recipe via the normal type/tag pipeline), and `isRecipeCompatible()` additionally hardcodes a check for `ModItems.magicpotion` as the *only* acceptable output. That output is taught via `crafterrecipes/alchemist/magicpotion.json`: 1 Mistletoe + 1 `minecolonies:large_water_bottle` → Magic Potion, gated by the `minecolonies:effects/consumepotions` research effect. This closes the loop with the JEI-only Mistletoe-gathering entry below — the Alchemist shears its own Mistletoe from schematic leaves, then feeds it into this one real recipe.
- **Nether Wart farming and Mistletoe gathering are JEI-display-only entries, not real taught recipes**: `getAdditionalRecipesForDisplayPurposesOnly()` adds two synthetic `IGenericRecipe` entries (Nether Wart grown on Soul Sand; Mistletoe sheared from Oak Leaves, requiring shears) purely so players can see what the Alchemist does in JEI — the actual growing/shearing behavior is Worker AI code interacting with the world directly (same pattern as the Forester's tree-chopping or the Concrete Mixer's world-placement harden step), not a request-system craft.
- **Simultaneous brewing is genuinely capped by skill — this is a real cap, not just acceleration like the furnace mechanic documented for other crafters**: `getMaxUsableBrewingStands() = min((primarySkillLevel / 10) + 1, number of brewing stands in the building)`. Separately, whichever stands *are* actively brewing get extra manual ticks via `accelerateBrewingStand()` — `(secondarySkillLevel / 10) × 2` bonus ticks per second — keyed off the *secondary* skill (Mana) rather than primary, unlike the furnace-acceleration mechanic elsewhere which always uses primary. So Dexterity (primary) controls *how many* stands can brew at once, and Mana (secondary) controls *how fast* each brewing stand actually goes.
- **Reserved inventory** (`keepX`): 1 shears, 1 axe (both any-tier via `TOOL_LEVEL_WOOD_OR_GOLD` minimum), and 16 Nether Wart held back as seed stock.
