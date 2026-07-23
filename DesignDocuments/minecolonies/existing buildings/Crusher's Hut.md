### Function
Crushes stone-family materials down a fixed chain (Tuff → Cobblestone → Gravel → Sand, plus separate bonemeal and clay lines), with a daily production cap and a research-unlockable efficiency upgrade. Uses the `.Custom` crafting policy — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)** — so, like the Concrete Mixer, everything it can make comes from datapack-authored custom recipes rather than tag-discovered vanilla ones.

- Building: [`BuildingCrusher`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingCrusher.java)
- AI: [`EntityAIWorkCrusher`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIWorkCrusher.java) — generic crafting loop, see shared doc
- Crafting module: `BuildingCrusher.CraftingModule extends AbstractCraftingBuildingModule.Custom`
- Datapack sources: `crafterrecipes/crusher/*.json` (14 files)

### Levels
Max building level: 5. Daily production cap (not a recipe-slot cap — see Limits):
```
maxDailyQuantity = buildingLevel² × 16   (levels 1–4)
maxDailyQuantity = unlimited              (level 5)
```
1. 16/day
2. 64/day
3. 144/day
4. 256/day
5. Unlimited

### Research
The Crusher itself is gated behind research: [`rockingroll.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/rockingroll.json) ("Rocking Roll") requires a **Stonemason at level 1**, costs 64 Stone, and its effect (`blockhutcrusher`) unlocks the Crusher for placement. The 2:1→1:1 ratio upgrade research named in Limits is [`gildedhammer.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/gildedhammer.json) ("Gilded Hammer"), which requires a **Crusher at level 3** and costs 64 each of Gravel/Sand/Clay.

### Skills
- Primary: Stamina
- Secondary: Strength
- Crafting speed skill: Stamina, Recipe improvement skill: Strength (no swap)

(`CRUSHER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Daily quantity cap is Building code, ticked in real time**: `currentDailyQuantity` resets to 0 on `onWakeUp()` (once per in-game day, same "day boundary" hook used by Cowboy's milking cycle), and is compared against `getMaxDailyQuantity()` (formula above) — a **hard** cap, not a soft probability curve. The `DAILY_LIMIT` GUI setting lets the player additionally self-impose a lower cap than the level would otherwise allow.
- **Crushing ratio is entirely research-gated, not level-gated**: every crushing recipe (Tuff→Cobblestone, Cobblestone→Gravel, Gravel→Sand — plus a separate `sand2`/`gravel2`/`cobble2`/`tuff2` variant of each) exists in **two versions**:
  - The default versions (`cobble.json`, `gravel1.json`, `sand1.json`, etc.) consume **2 input items per 1 output** and are only valid `"not-research-id": "minecolonies:effects/crushing11unlock"` (i.e. while that research is *not yet* completed).
  - The upgraded versions (`cobble2.json`, `gravel2.json`, `sand2.json`, etc.) consume **1 input per 1 output** and require `"research-id": [...]` including `crushing11unlock` (i.e. "Gilded Hammer," see Research above) — once that research completes, the colony-wide crushing ratio silently improves from 2:1 to 1:1 for every tier of the chain simultaneously. `cobble.json`/`cobble2.json` additionally both require `knowledgeofthedepthsunlock` regardless of the ratio research.
  - `sand1.json` also carries a `loot-table` field (`minecolonies:recipes/gravel`) for a chance at bonus output — see the Sifter doc for a full explanation of how the `loot-table` recipe field is a **real gameplay mechanic** (not just JEI display) when present on a custom recipe.
- **Bonemeal and clay lines are also affected by the Gilded Hammer ratio research**, contrary to what a first read of the recipe folder might suggest: `bonemeal1.json` (1 Bone → 3 Bone Meal, valid while `crushing11unlock` is *not* researched) is superseded by `bonemeal2.json` (1 Bone → 5 Bone Meal, valid once researched); `clay1.json` (2 Sand → 1 Clay, unresearched) is superseded by `clay2.json` (1 Sand → 1 Clay, researched) the same way. Only `bonemeal3.json` (1 Bone Block → 9 Bone Meal, always available, no gate) and `clay_ball.json` (1 Clay → 4 Clay Ball, always available, no gate) sit outside the research-gated pattern.
- No settings beyond `MODE` (a `RecipeSetting`, purpose not fully explored here — likely toggles which specific crushing chain item the worker prioritizes) and `DAILY_LIMIT`.
