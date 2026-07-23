### Function
Raises sheep: shears wool (optionally with a chance to re-dye the sheep a random color), breeds, and butchers. Shares its core behaviour with all other herders — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the full state machine.

- Job: `shepherd` — [`JobShepherd`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobShepherd.java)
- Building: [`BuildingShepherd`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingShepherd.java)
- AI: [`EntityAIWorkShepherd`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkShepherd.java)
- Herding module: `BuildingShepherd.HerdingModule` (compatible animal: `Sheep`; breeding item: 2× Wheat). Also contributes a synthetic "shears → wool" recipe to JEI/display (`getRecipesForDisplayPurposesOnly`), covering the whole `ItemTags.WOOL` tag as possible outputs, even though shearing isn't a request-system crafting recipe — see Limits.

### Levels
Max building level: 5. Max sheep housed = `building level × 2` (default multiplier, soft cap — see shared doc):
1. 2 housed
2. 4 housed
3. 6 housed
4. 8 housed
5. 10 housed

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Primary: Focus
- Secondary: Strength

(`SHEPERD_WORK` in `BuildingModules` — note the missing "h" in the constant/module id, `sheperd_work`, is in the source itself.)

### Limits
- **Housing cap** — shared herder mechanism, see above.
- **Butchering damage** scales with Secondary skill (Strength): `max(1.0, secondarySkillLevel / 10.0)`.
- **Shearing is opt-in and gated by an extra tool requirement**: the `SHEPERD_SETTINGS` module's `SHEARING` bool setting must be on, at which point `getExtraToolsNeeded()` additionally demands shears (`ModEquipmentTypes.shears`) before the AI enters its custom `SHEPHERD_SHEAR` state. If off, sheep only ever get butchered/bred for wool, never sheared.
  - `findShearableSheep()` filters to non-baby, not-already-sheared sheep.
  - Shear quantity: `1 + random.nextInt(fortuneLevel + 1)`, where `fortuneLevel = enchantmentLevel(Block Fortune on held shears) × max(1.0, primarySkillLevel / 5.0)` — i.e. **Focus (primary skill) multiplies the effective value of a Fortune-enchanted pair of shears**, but does nothing without Fortune already on the tool (`0 × anything = 0`).
- **Dyeing** is a second independent toggle (`DYEING` setting): if on, every successful shear rolls `random.nextInt(100) <= buildingLevel` to recolor the sheep to a random `DyeColor` (`dyeSheepChance()`) — so a level 5 hut has a 5% chance per shear to repaint the sheep, letting a colony bootstrap colored wool without ever buying dye. This is **Worker AI code** reading a **Building setting**; the chance itself scales off building level, not a skill.
- `BuildingShepherd.canEat()` blocks the worker from eating Wheat, same reserved-breeding-stock pattern as Cowboy.
