### Function
Raises adult citizens' Intelligence skill via a Researcher-equivalent "study" mechanic using bookshelves found in its own schematic; Study Items (Book/Paper) can speed this up.

- Building: [`BuildingLibrary`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingLibrary.java) — deliberately minimal; the actual studying AI is [`EntityAIStudy`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/education/EntityAIStudy.java).
- Datapack sources: `study_items/book.json` (Book, `skill_increase_chance: 60`, `break_chance: 10`), `study_items/paper.json` (Paper, `skill_increase_chance: 40`, `break_chance: 100`) — confirmed exact values. `EntityAIStudy` rolls a level-up chance of `ONE_IN_X_CHANCE (8) × (10 / skillIncreaseChance)` once per study session when holding a study item, versus a flat `ONE_IN_X_CHANCE (8)` with no item at all — so a Book (60) roughly sextuples the level-up odds versus studying bare-handed, and is only consumed 10% of the time it's used; Paper (40) is a smaller but still real boost and is consumed every single time (100% break chance), making Book the more efficient long-run choice per item but Paper usable as an always-available single-use booster.

### Levels
Max building level: 5. Confirmed capacity formula `2 × buildingLevel` (via `STUDENT_WORK`'s `WorkerBuildingModule` sizing): 2/4/6/8/10 max citizens for levels 1–5.

### Research
[`keen.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/keen.json) ("Keen") requires a **Residence at level 3**, costs 3 Books, and its effect (`blockhutlibrary`) unlocks the Library for placement. Keen is also the parent research of "Outpost" (first population-cap increase — see the Residence doc), an easy-to-miss dependency since nothing about a library obviously suggests population capacity.

### Skills
- Primary: Intelligence
- Secondary: Intelligence (yes, the same skill twice — confirmed from source, not a documentation gap)

(`STUDENT_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java), sized at `2 × buildingLevel` — matching the confirmed 2/4/6/8/10 progression above exactly.)

### Limits
- **Study spots are schematic-detected**: `registerBlockPosition()` records every block tagged `#forge:bookshelves` in the blueprint (`bookCases`) — `getRandomBookShelf()` picks one at random each time a citizen needs a study location, falling back to the building's own position if the list is empty or a previously-recorded shelf turns out to no longer exist.
- **Reserved inventory** (`keepX`): up to 64 units of anything recognized as a "study item" by `StudyItemListener` (the Book/Paper datapack-defined list above) — held back so the speed-up items aren't shipped elsewhere by the request system.
