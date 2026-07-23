### Function
Crafts tools, weapons, armor, and shields. Shares its core behaviour with all other crafters — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)** for the module hierarchy, recipe-slot cap formula, and the tag/datapack system that governs what a crafter can learn.

- Job: `blacksmith` — [`JobBlacksmith`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobBlacksmith.java)
- Building: [`BuildingBlacksmith`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBlacksmith.java)
- AI: [`EntityAIWorkBlacksmith`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkBlacksmith.java) — extends the shared [`AbstractEntityAICrafting`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/AbstractEntityAICrafting.java) generic crafting-loop AI used by most crafters
- Crafting module: `BuildingBlacksmith.CraftingModule extends AbstractCraftingBuildingModule.Crafting`
- Datapack sources (generated, under `src/datagen/generated/minecolonies/data/minecolonies/`): `tags/items/blacksmith_{product,ingredient}{,_excluded}.json`, `crafterrecipes/blacksmith/*.json`, `researches/technology/hittingiron.json`

### Levels
Max building level: 5. Recipe-slot cap uses the standard formula (`2^level × 5`, no completed research):
1. 10 max recipes
2. 20 max recipes
3. 40 max recipes
4. 80 max recipes
5. 160 max recipes

Netherite-tier gear is **not** a level-5 unlock as an earlier version of this doc claimed — see Limits below for the real gate (level 4, via custom recipes).

### Research
The Blacksmith Hut itself is gated behind research, not just placed freely: [`hittingiron.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/hittingiron.json) ("Hitting Iron!") requires a **Miner's Hut at level 3**, costs 1 Anvil to research, and its effect (`blockhutblacksmith`) is what actually unlocks the Blacksmith Hut for placement. This is the standard building-unlock research pattern — worth checking for other buildings that don't feel like "starter" huts.

### Skills
- Primary: Strength
- Secondary: Focus
- Crafting speed skill: Strength, Recipe improvement skill: Focus (no primary/secondary swap here — see shared doc for the buildings that do swap)

(`BLACKSMITH_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Recipe eligibility is Building code, hand-written on top of the tag system** (`BuildingBlacksmith.CraftingModule.isRecipeCompatible()`):
- Any recipe whose input includes `Items.LEATHER` is **explicitly rejected outright** — "that's the fletcher's responsibility," per the source comment. This overrides everything else, including the tag system.
- Any recipe whose output is a registered axe/pickaxe/shovel/hoe/shears/sword/shield/helmet/chestplate/leggings/boots (via `ModEquipmentTypes`) or a recognized Tinkers' Construct weapon is **explicitly accepted**, even if it would otherwise fail the tag/leather check — deliberately excluding fishing rods and flint & steel from this blanket allowance.
- Everything else falls through to the tag-based check for the `blacksmith` job name. Confirmed tag contents in this repo:
  - `blacksmith_product`: `minecraft:shears`, `minecraft:lightning_rod`, `#forge:nuggets`, `#forge:ingots`
  - `blacksmith_product_excluded`: everything in the Dyer/Mechanic/Sawmill/Stonemason product tags (so those crafters keep priority on their own outputs), plus firework stars, glistering melon slices, bows, and crossbows (those go to the Fletcher, see its doc)
  - `blacksmith_ingredient`: diamond block, emerald block, `#forge:nuggets`, `#forge:ingots`
  - `blacksmith_ingredient_excluded`: `#forge:crops`, the Dyer/Mechanic ingredient tags, bricks, nether bricks

**Custom (datapack) recipes taught regardless of the tag system**, from `crafterrecipes/blacksmith/`:
- **Netherite upgrades** (`netherite_{sword,axe,pickaxe,shovel,hoe,helmet,chestplate,leggings,boots}.json`) — gated at **`min-building-level: 4`**, not 5. Each consumes the matching diamond tool/armor piece, a Netherite Ingot, 7 Diamonds, and Netherrack, alongside a Netherite Upgrade Smithing Template — but the template is also listed as an `additional-output`, so it's effectively **returned to the worker rather than consumed**, unlike a player manually using a smithing table.
- **Plate armor** (`plate_armor_{helmet,chest,legs,boots}.json`) — gated at `min-building-level: 4` **and** requires the `minecolonies:effects/platearmorunlock` research effect to be completed; crafted from iron ingots + leather + coal.
- **Assistant hammers** (`assistanthammer_{iron,diamond}.json`) — gated purely by the `minecolonies:effects/assistanthammerunlock` research effect, no building-level requirement.
- **Sifter's iron mesh** (`sifter_mesh_iron.json`) — the Blacksmith, not the Sifter, crafts the Sifter building's iron mesh upgrade item, gated by `minecolonies:effects/sifterironunlock`. That research ([`unlockable/ironmesh.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/unlockable/ironmesh.json)) auto-starts once a colony has a **Sifter's Hut at level 4**, chaining off a flint-mesh research before it — a good example of one building's progression gating a recipe taught to a completely different building.

No building-specific settings beyond the standard `RECIPE_MODE` (Max Stock vs. priority order) inherited from the crafting module base.
