### Function
Where Researchers conduct the colony's research tree. More Researchers let more *different* research projects run in parallel, but do **not** speed up any single research project.

- Building: [`BuildingUniversity`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingUniversity.java)
- Researcher module: `UNIVERSITY_WORK` (`WorkerBuildingModule`, sized `ICommonBuilding::getBuildingLevel` — i.e. 1 researcher per level)
- Research-progress view module: `UNIVERSITY_RESEARCH`

### Levels
Max building level: 5. Confirmed researcher capacity: 1/2/3/4/5 for levels 1–5.
- **Offline research catch-up requires level 3+**: `processOfflineTime()` only actually processes accumulated offline time if `getBuildingLevel() >= OFFLINE_PROCESSING_LEVEL_CAP` (3) — below that level, time the server was offline simply doesn't advance research at all, whereas a level 3+ University lets assigned Researchers' jobs process that elapsed time and even announces it to the colony ("researchers gained more knowledge...").

### Research
No building-unlock research found for the University.

### Skills
- Primary: Knowledge
- Secondary: Mana

(`UNIVERSITY_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **One research tick per assigned Researcher, confirmed precisely**: `onColonyTick()` iterates the colony's list of in-progress research items and advances at most one per currently-assigned Researcher (the loop bails out once its counter exceeds `module.getAssignedCitizen().size()`) — having two Researchers but only one research in progress does not decrease the time that single research takes.
- **A second, independent progression path exists at the individual worker level**: confirmed in [`EntityAIWorkResearcher`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/education/EntityAIWorkResearcher.java) — each assigned Researcher, once every `STUDY_DELAY` (1200 ticks / 60 seconds), walks to a random bookshelf and, **if they still have `Mana` left in their personal job resource pool** (`job.getCurrentMana()`, depleted by `reduceCurrentMana()` on each use), picks one random in-progress research and advances it directly, independent of the building-level tick described above. In other words, research speed isn't purely "one step per researcher per colony tick" — each individual Researcher's own Mana pool also gates how often *they specifically* can contribute, and the two mechanisms (building-level tick, worker-level study action) both feed into the same research tree rather than being mutually exclusive.
- **Study spots are schematic-detected**, the identical bookshelf mechanism as the Library (`#forge:bookshelves` tag, `getRandomBookShelf()`).
- On successfully concluding any research, **every citizen in the colony has their research effects reapplied** (`onSuccess()` calls `applyResearchEffects()` for the whole citizen roster) and the colony auto-checks whether a next research can auto-start.
