### Function
An individual guard post within a [Barracks](Barracks.md) complex — the actual guard building (unlike the Barracks hub itself). Houses Knight, Ranger, or Druid guards exactly like a [Guard Tower](Guard%20Tower.md), but its level is chained to its parent Barracks. See **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for the full patrol/combat/hiring framework.

- Building: [`BuildingBarracksTower`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBarracksTower.java)
- Guard modules: `KNIGHT_BARRACKS_WORK`, `RANGER_BARRACKS_WORK`, `DRUID_BARRACKS_WORK` in `BuildingModules` — sizeLimit scales **with the tower's own building level** (`ICommonBuilding::getBuildingLevel`), unlike Guard Tower/Gatehouse's fixed caps, so a higher-level Barracks Tower can house more than one guard of a given type.

### Levels
Max building level: 5, but **hard-chained to the parent Barracks' level**: `requestUpgrade()` explicitly refuses to queue a build/upgrade work order unless the tower's current level is still below both its own max *and* its parent Barracks' current level. In practice this means you must upgrade the Barracks hub first before any of its towers can follow — towers can never out-level their hub.
- **Cannot be individually deconstructed** (`canDeconstruct()` returns `false`) — since a tower is structurally part of the Barracks complex, not a freestanding building.
- **Claim radius contribution is 0 individually** (`getClaimRadius()` always returns 0) — as documented on the Barracks' own page, claim radius is computed by the *parent* Barracks based on all its towers' levels collectively, not per-tower.
- **All-towers-upgraded triggers an advancement**: when a tower completes an upgrade to its parent's max level, it checks whether every sibling tower is also at that max level, and if so fires the `ALL_TOWERS` advancement for the colony's players.

### Research
No building-unlock or mechanic-specific research found beyond the shared, colony-wide guard research effects (see shared doc).

### Skills
Guard type skills come from the `GuardType` registry entry, same as Guard Tower — see the shared guard doc.

### Limits
- **Level is entirely gated by the parent Barracks**, as above — this is the tower's single defining constraint and is enforced in Building code (`requestUpgrade()`), not by the normal work-order/builder system alone.
- **Guard capacity scales with the tower's own level** (not fixed at 1 like Guard Tower) — check the exact `sizeLimit` function passed to each `*_BARRACKS_WORK` module in `BuildingModules` if you need the precise per-level numbers; it's a direct function of `getBuildingLevel()`.
- Bonus health/vision/patrol distance/armor-tier all follow the standard `AbstractBuildingGuards` per-level formulas from the shared doc (patrol distance: 80/110/140/170/200 blocks for levels 1–5) — no Guard-Tower-style flat HP bonus here.
- Same destruction/upgrade colony-tracking hooks (`guardBuildingChangedAt`) as Guard Tower.
- No settings beyond the standard guard settings from `AbstractBuildingGuards`.
