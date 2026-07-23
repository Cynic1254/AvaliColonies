### Function
Sifts gravel (and other materials) for a chance at bonus items — flint, ores, gems — with the odds and possible outputs controlled by a **real vanilla-format loot table**, not just a fixed recipe output. Uses the `.Custom` crafting policy — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)**.

- Building: [`BuildingSifter`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingSifter.java)

> Possible finds per material scale up with mesh tier rather than being fixed. Dirt: the String and Flint tiers yield a smaller sapling/seed set; the Iron and Diamond tiers both expand to the full 13-item list (Wheat/Pumpkin/Melon/Beetroot Seeds, Oak/Birch/Spruce/Jungle/Dark Oak/Acacia Saplings, Carrot, Potato). Gravel: Coal/Diamonds/Lapis/Emeralds/Flint/Iron & Gold Ingots/Iron Nuggets/Redstone, with higher tiers adding the rarer ore-tier drops (see the real odds below). Sand: Cactus/Cocoa Beans/Gold Nuggets/Sugarcane. Soul Sand: Blaze Powder/Glowstone Dust/Magma Cream/Nether Wart/Quartz (the "human skulls" find sometimes mentioned for this material was not found in any confirmed recipe file in this checkout).
- AI: [`EntityAIWorkSifter`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkSifter.java)
- Crafting module: `BuildingSifter.CraftingModule extends AbstractCraftingBuildingModule.Custom`
- Datapack sources: `crafterrecipes/sifter/{string,flint,iron,diamond}/*.json`, `loot_tables/recipes/{string,flint,iron,diamond}/*.json`, `tags/items/meshes.json`

### Levels
Max building level: 5. Daily production cap (same pattern as Crusher, different multiplier):
```
maxDailyQuantity = buildingLevel² × 64   (levels 1–4)
maxDailyQuantity = unlimited              (level 5)
```
1. 64/day
2. 256/day
3. 576/day
4. 1024/day
5. Unlimited

Separately, **which sifting recipes are even available scales with building level** via `min-building-level` on each mesh tier's recipe (string tier: level 1; flint: level 3; iron: level 4; diamond: level 5) — see Limits.

### Research
### Research
The Sifter itself is gated behind research: [`sieving.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/sieving.json) ("Sieving") requires a **Fisherman's Hut at level 3**, costs 64 String, chains off "Woodworking" (see the Sawmill doc), and its effect (`blockhutsifter`) unlocks the Sifter for placement. The mesh-tier system beyond that is research-gated too — see Limits for the full chain.

### Skills
- Primary: Focus
- Secondary: Strength
- Crafting speed skill: Focus, Recipe improvement skill: Strength (no swap)
- Sifting speed specifically uses the **secondary** skill (Strength) directly in the AI's own progress formula (`MAX_LEVEL(50) - effectiveSecondarySkillLevel/2` hits needed), which is a different formula from the generic crafting-loop's `craftSpeedSkill`-based one in the shared doc — the Sifter has its own bespoke AI rather than using the plain generic loop unmodified.

(`SIFTER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**The mesh is a reusable tool, not a consumable ingredient**: every sifting recipe lists the mesh item (e.g. `minecolonies:sifter_mesh_iron`) as both an input *and* an `additional-output`, so it's returned to the worker's offhand after each sift rather than used up — functionally identical to the netherite smithing template and the Sifter's own mesh-crafting recipes at the Blacksmith/Stonemason/Fletcher/Mechanic. `BuildingSifter`'s constructor also reserves up to 4 mesh items in the building's inventory via `keepX` so they don't get shipped off by the request system.

**Four mesh tiers, each unlocking a level of the building and a whole new set of sifting recipes** (`data/minecolonies/crafterrecipes/sifter/<tier>/{dirt,gravel,sand,soul_sand}.json`), gated by `min-building-level` per tier:
| Tier | Mesh source | `min-building-level` | Unlock research |
|---|---|---|---|
| String | Fletcher (`sifter_mesh_string`) | 1 | `sifterstringunlock`, auto-unlocked once Sifter reaches level 1 |
| Flint | Stonemason (`sifter_mesh_flint`) | 3 | `sifterflintunlock`, chained off the string research, requires Sifter level 4 to unlock (note: the *research* requires level 4 even though the resulting recipes only require building level 3 — the research just needs to have completed once) |
| Iron | Blacksmith (`sifter_mesh_iron`) | 4 | `sifterironunlock`, requires Sifter level 4 |
| Diamond | Mechanic (`sifter_mesh_diamond`) | 5 | `sifterdiamondunlock`, requires Sifter level 5 |

Each tier's recipe set (e.g. `iron/gravel.json`) lists a fixed set of `additional-output` candidate items (the mesh itself, plus tier-appropriate materials — string tier gets iron nugget/flint/coal; iron and diamond tiers additionally get redstone/lapis/gold/emerald/diamond) **but whether you actually get any of them, and how many, is controlled by the `loot-table` field** (e.g. `minecolonies:recipes/iron/gravel`), pointing at a real vanilla-schema loot table under `data/minecolonies/loot_tables/recipes/`.

**This is a genuinely different mechanic from the JEI-only `LootTableAnalyzer` documented in the crafting shared doc**: when a custom recipe has a `loot-table` field, `RecipeStorage.fullfillRecipe()` actually rolls that loot table for real at craft time — using the same `LootParams` (with the crafter's `LUCK` parameter set from their skill level, per `AbstractCraftingBuildingModule.getCraftingLuck()`) built for every crafting action. Confirmed odds for all three inspected tiers' gravel table:
- **String tier**: 1 pool, 1 roll, no bonus rolls — 85% nothing, 5% each Iron Nugget / Flint / Coal.
- **Flint tier**: 1 pool, 1 roll, no bonus rolls — 60% nothing, 10% each Iron Nugget / Flint / Coal / Redstone.
- **Iron tier**: 1 pool, 1 roll, plus **`bonus_rolls: 0.025`** (an extra fractional roll scaled by the crafter's luck stat, per vanilla loot table semantics) — 46% nothing, 15% each Redstone / Iron Nugget / Coal, 5% Lapis Lazuli, and Iron Ingot / Gold Ingot / Emerald / Diamond each at the minimum weight of 1 (roughly 1% each of the remaining share).
- **Diamond tier**: 1 pool, 1 roll, plus **`bonus_rolls: 0.035`** — 40% nothing, 20% each Redstone / Iron Nugget / Coal, 10% Lapis Lazuli, 2% each Iron Ingot / Gold Ingot / Emerald / Diamond.

Higher tiers therefore improve odds in two ways simultaneously: the "nothing" weight shrinks (60% → 46% → 40%) and rarer items go from entirely absent to present at growing weight, while `bonus_rolls` on the Iron/Diamond tiers means a skilled worker can occasionally get more than one roll per sift. **Net effect: sifting one gravel with a given mesh always consumes the gravel and always keeps the mesh, but the bonus item is a genuine weighted random roll, boosted by the worker's skill via loot luck** — this is the real, gameplay-affecting use of the `loot-table` recipe field, distinct from its purely-cosmetic JEI-display use elsewhere (e.g. `AnimalHerdingModule`/`CustomRecipe.getLootTable()` used only for display when no real recipe execution is involved).

**Daily cap enforcement**: same hard-cap-with-manual-underride pattern as the Crusher (`onWakeUp()` resets the counter once per day; `hasWorkToDo()` and `sift()` both check `currentDailyQuantity < maxDailyQuantity` and go `IDLE` once hit).

**No-mesh handling**: if the building has no mesh in stock (checked via the generic `ModTags.meshes` item tag, which presumably includes all four tier items) and the worker isn't already holding one, the AI raises a chat interaction (`SIFTER_NO_MESH`) and backs off for 100 ticks rather than repeatedly failing — this is Worker AI code responding to a Building inventory state, not a hard block on the job.
