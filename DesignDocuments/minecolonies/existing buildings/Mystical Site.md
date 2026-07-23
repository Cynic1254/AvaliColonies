### Function
Passively increases the colony's overall happiness level, and separately raises the cap on the Graveyard's resurrection chance. Has essentially no building-specific code of its own — `BuildingMysticalSite` is one of the most minimal building classes in this reference (schematic name and max level only; no modules, no settings, no custom NBT).

- Building: [`BuildingMysticalSite`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/BuildingMysticalSite.java), implements `IMysticalSite` — a marker interface other systems (happiness calculation, the Graveyard's resurrection-chance cap) query directly rather than this building doing any work itself.

### Levels
Max building level: 5. No modules or per-level formulas exist on the building class itself — its effects are entirely computed by external systems querying `getMysticalSiteMaxBuildingLevel()` colony-wide (see Limits): happiness gets a factor of `max(1, maxMysticalSiteLevel / 2.0)` (1.0 at level 0 up to 2.5 at level 5), and the Graveyard's resurrection-chance cap gets `+0.5% per level` (`UndertakerConstants.MAX_RESURRECTION_CHANCE_MYSTICAL_LVL_BONUS` — see that doc).

### Research
[`ambition.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/ambition.json) ("Ambition") costs 1 Diamond, has **no building-level prerequisite**, and its effect (`blockhutmysticalsite`) unlocks the Mystical Site for placement.

### Skills
N/A — the Mystical Site has no worker of its own.

### Limits
- **Only the highest-level Mystical Site in the colony counts — confirmed exactly**: [`RegisteredStructureManager.getMysticalSiteMaxBuildingLevel()`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/managers/RegisteredStructureManager.java) iterates every registered `IMysticalSite` and returns the single highest level found (never a sum). This is a shared helper that different systems each call independently rather than one central aggregator: [`CitizenHappinessHandler.getMysticalSiteFactor()`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/citizen/citizenhandlers/CitizenHappinessHandler.java) calls it directly to compute the happiness bonus (`max(1, maxLevel / 2.0)` — note this doesn't match that method's own doc comment, which claims a range of "1 to 3.5" via `1 + level/2`; the actual code caps out at 2.5 at level 5, a real discrepancy between the comment and the implementation), and the Graveyard's resurrection-chance bonus (see that doc) almost certainly calls the same method rather than re-deriving the max itself.
