### Function
Disenchants Ancient Tomes into random Enchanted Books, and crafts several utility scrolls (teleport, area-teleport, guard-help, highlight). Its most distinctive mechanic: when its own Mana skill is too low to enchant, the worker physically **walks to another citizen's workplace and drains mana from them**, directly enchanting a random item in that citizen's inventory as a side effect. Uses the `.Custom` crafting policy — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)** — so it never auto-discovers vanilla recipes.

- Building: [`BuildingEnchanter`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingEnchanter.java)
- Job: [`JobEnchanter`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobEnchanter.java) — notably lives under `core/entity/ai/workers/**service**/`, not `crafting/`, reflecting that this job is as much about the draining mechanic as it is about crafting
- AI: [`EntityAIWorkEnchanter`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/service/EntityAIWorkEnchanter.java) — still extends the generic `AbstractEntityAICrafting`, but adds a whole second behavior tree for draining
- Crafting module: `BuildingEnchanter.CraftingModule extends AbstractCraftingBuildingModule.Custom`, `addRecipe()` hardcoded to `false` (only pre-taught custom recipes ever apply; doesn't block the real datapack-driven population path — see the crafting shared doc's note on the identical Concrete Mixer pattern)
- Stations module: `EnchanterStationsModule` (`ENCHANTER_STATIONS` in `BuildingModules`) — tracks which other buildings/workers are eligible to be drained from
- Datapack sources: `crafterrecipes/enchanter/*.json` (9 files), `loot_tables/recipes/enchanter{1-5}.json`

### Levels
Max building level: 5.
- **Mana requirement to enchant scales with level**: the worker needs `Mana skill level ≥ buildingLevel × 10` before it will attempt any actual enchanting; below that threshold, it goes drain a citizen for Mana XP instead (see Limits).
- **Enchanting speed scales inversely with level**: each enchant takes `(60 × 5) / buildingLevel` ticks of "channeling" (so a level 5 tower enchants 5× faster than level 1).
- **The tome-to-book recipe itself is tiered 1:1 with building level** — `tomeN.json` is valid *only* at building level exactly N (`min-building-level == max-building-level == N`), each pointing at its own `enchanterN` loot table. Upgrading the building swaps which tier's loot table is active rather than adding options.

### Research
No building-unlock research found for the Enchanter's Tower itself. However, [`morescrolls.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/morescrolls.json) ("More Scrolls") requires an **Enchanter at level 3**, costs 64 Paper + 1 Ancient Tome + 64 Lapis Lazuli, and its effect (`morescrollsunlock`) gates two of the Enchanter's own scroll recipes — see Limits. It's also the parent research of "Open the Nether" (see the Nether Mine doc), so the Enchanter's own progression is a prerequisite for unlocking the Nether Mine.

### Skills
- Primary: Mana
- Secondary: Knowledge
- Crafting speed skill: Mana, Recipe improvement skill: Knowledge (no swap)

(`ENCHANTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**Two entirely separate behaviors, gated by the worker's own Mana skill level**:

1. **Enchanting** (`ENCHANT` state, when `Mana ≥ buildingLevel × 10`): consumes one Ancient Tome, channels particle effects for `300 / buildingLevel` ticks, then rolls the current tier's loot table for a random Enchanted Book (see the crafting shared doc's real loot-table mechanic — confirmed `enchanter1.json` contents: 20 level-1 enchantments at flat equal weight, no rarity weighting at that tier). **The Mana cost is real and immediate**: the worker's own Mana skill level is decremented by the resulting enchantment's level right after a successful enchant (`incrementLevel(Skill.Mana, -enchantmentLevel)`) — high-level enchants measurably drain the enchanter's own skill, which is what eventually forces it back into draining mode.
2. **Draining** (`ENCHANTER_DRAIN` state, when Mana is too low): the worker picks a random *other* citizen from the buildings registered in [`EnchanterStationsModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/EnchanterStationsModule.java), walks to within 10 blocks of them, channels a stream particle effect between the two for 60 ticks, then: confirmed the module itself is just a plain `Map<BlockPos, Boolean>` of registered building positions to an "already gathered today" flag (reset every wake-up via `onWakeUp()`) — buildings are added/removed one at a time via explicit `addWorker()`/`removeWorker()` calls, not auto-populated by proximity or any other automatic rule. This is consistent with it being a manually curated list (e.g. via GUI selection) rather than something the Enchanter figures out on its own; the specific caller of `addWorker()`/`removeWorker()` (GUI/network message) wasn't traced further.
   - Consumes 1 plain Book from its own inventory.
   - Attempts (`secondarySkillLevel / 5` tries) to find an enchantable item in the *target citizen's* inventory and applies a real random vanilla enchantment to it directly (`EnchantmentHelper.enchantItem`, at effective level 2 if the enchanter's Knowledge/secondary skill > 50, else level 1) — **this actually enchants another citizen's held gear as a side effect of the drain**, not the enchanter's own equipment.
   - Grants the enchanter +1 Mana skill level and some XP, regardless of whether a matching enchantable item was actually found on the target.
- **Reserved inventory** (`keepX`): a full stack of Ancient Tomes.
- Scroll crafting (`scroll_tp`, `scroll_area_tp`, `scroll_guard_help`, `scroll_highlight`) is separate, fixed-output, not loot-table-based. Confirmed recipes: `scroll_tp.json` (3 Paper + 1 Compass + 1 Golden Scepter → 3 `minecolonies:scroll_tp`, no gates); `scroll_area_tp.json` (3× `scroll_tp` → 1 `scroll_area_tp`, `min-building-level: 2`, no research); `scroll_guard_help.json` (1 `scroll_tp` + 5 Lapis Lazuli + 1 Ender Pearl + 1 Paper → 2 `scroll_guard_help`, `min-building-level: 3`, gated by `morescrollsunlock`); `scroll_highlight.json` (3× `scroll_tp` + 6 Glowstone Dust + 2 Paper → 5 `scroll_highlight`, `min-building-level: 3`, also gated by `morescrollsunlock`). The "Spatial Guard Reinforcements Scroll" (`scroll_guard_help`) and "Worker-Where-Are-You Scroll" (`scroll_highlight`) specifically require "More Scrolls," while the basic and area teleport scrolls don't.
- No exposed settings beyond the standard `RECIPE_MODE`.
