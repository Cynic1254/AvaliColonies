### Function
Produces dyed concrete powder (normal crafting) and then hardens it into full concrete using an actual in-world water mechanic, rather than a second crafting recipe. Uses the `.Custom` crafting policy — see **[_Shared - Crafting System](_Shared%20-%20Crafting%20System.md)** — meaning it never auto-discovers vanilla/tagged recipes; everything it knows comes from datapack-authored custom recipes.

- Building: [`BuildingConcreteMixer`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingConcreteMixer.java)
- AI: [`EntityAIConcreteMixer`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/EntityAIConcreteMixer.java) — extends the generic crafting AI but overrides the actual "craft" step for the hardening half of the job (see Limits)
- Crafting module: `BuildingConcreteMixer.CraftingModule extends AbstractCraftingBuildingModule.Custom`
- Datapack sources: `crafterrecipes/concretemixer/*.json` (30 files: a `_powder` recipe and a hardening recipe for each of the 16 vanilla concrete colors)

### Levels
Max building level: 5. No recipe-slot cap formula applies in the usual sense — recipe eligibility comes entirely from custom recipes (see Limits), not a learnable count. What *does* scale with level is how much concrete can be worked on at once, driven by how much shallow flowing water (≤5 depth, `WATER_DEPTH_SUPPORT`) the building's schematic provides — more water positions found in the blueprint means more concrete can be placed/harvested in parallel (`getMaxConcretePlaced()`), which is a **schematic-driven** limit, not a level formula.

### Research
The Concrete Mixer itself is gated behind research: [`pavetheroad.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/technology/pavetheroad.json) ("Pave the Road") requires a **Crusher at level 1**, costs 32 items from the `#minecolonies:concrete` tag, chains off "Rocking Roll" (see the Crusher doc), and its effect (`blockhutconcretemixer`) unlocks the Concrete Mixer for placement.

### Skills
- Primary: Stamina
- Secondary: Dexterity
- Crafting speed skill: Stamina, Recipe improvement skill: Dexterity (no swap)

(`CONCRETEMIXER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **`canRecipeBeAdded()` is hardcoded to always return `false`** in the crafting module — this looks like it would block everything, but it doesn't actually matter in practice: custom recipes are injected via `checkForWorkerSpecificRecipes()`, which calls the internal `addRecipeToList()` directly and never goes through the `canRecipeBeAdded()`-gated public `addRecipe()` path. In other words, this override is a defensive no-op given how `.Custom` modules are populated, not an active restriction — don't read it as "the Concrete Mixer can never learn anything."
- **Two-stage production, only the first stage is a "real" recipe craft**:
  1. **Powder crafting** (e.g. `white_concrete_powder.json`: 4 Sand + 4 Gravel + 1 White Dye → 8 Powder) goes through the normal hit-based crafting loop from the shared doc.
  2. **Hardening** (e.g. `white_concrete.json`: 1 Powder → 1 Concrete) is *not* actually executed as a craft — `EntityAIConcreteMixer.executeCraftingAction()` detects that the recipe's output isn't a `ConcretePowderBlock` and redirects to `performMixingWork()`, which is a **world-placement mechanic**: the worker places powder into a tagged shallow-water block (`getBlockToPlace()`), waits for it to become real concrete (vanilla water-contact behavior), then mines it back out (`getBlockToMine()`/`harvestConcrete()`) and delivers it as if it were the recipe's output. The building tracks candidate water positions itself via `registerBlockPosition()`, scanning for flowing water with a fluid amount ≤ 5 as the schematic is registered.
  - **Enforcement split**: which colors/recipes exist at all is **datapack/Building code** (the custom recipe files); *how much concrete can be worked at once* is **schematic-driven** (how many suitable shallow-water blocks the blueprint provides); the actual harden step is **Worker AI code** operating on the real world block state, not the request/recipe system.
- No exposed settings beyond the standard `RECIPE_MODE` (which has limited practical effect here given the `.Custom` policy).
