### Function
Houses and deploys a single Knight, Ranger, or Druid guard. The simplest true guard building — see **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for the full patrol/combat/hiring framework; this doc only covers what's specific to the Guard Tower.

- Building: [`BuildingGuardTower`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingGuardTower.java)
- Guard modules: `KNIGHT_TOWER_WORK`, `RANGER_TOWER_WORK`, `DRUID_TOWER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java) — each capped at exactly **1** assigned guard (`(b) -> 1`), so a Guard Tower only ever houses one guard total, of whichever type the player assigns.

### Levels
Max building level: 5.
- **Claim radius**: 2 / 3 / 3 / 4 / 5 chunks for levels 1–5 (a custom, non-linear per-level table, not the usual formula).
- Bonus health, vision, patrol distance, and armor tier all follow the shared per-level formulas from the shared doc (patrol distance: 80/110/140/170/200 blocks for levels 1–5), **plus a flat +20 bonus HP** on top of the standard `+2/level` (`BONUS_HP_SINGLE_GUARD`) — a deliberate compensation for only ever having one guard to defend the position, unlike Barracks Towers which can stack several.

### Research
No building-unlock research found. Combat-affecting research effects are colony-wide, not tower-specific — see the shared guard doc's combat table.

### Skills
Guard type skills come from the `GuardType` registry entry (`ModGuardTypes.knight/ranger/druid`), not a `BuildingModules` primary/secondary pair — see the shared guard doc.

### Limits
- **Only one guard, period** — all three guard-type modules cap at 1, so upgrading the tower's level never lets it house more guards; it only makes the existing guard tougher/see further/patrol wider, and unlocks better armor tiers.
- **Automatic vs. manual patrol** (`requiresManualTarget()`): the tower falls back to automatic patrol whenever there are no manual patrol targets set, a temporary next-patrol-point is pending, or the `PATROL_MODE` setting isn't `MANUAL` — i.e. manual patrol only actually takes effect once the player has both switched the setting *and* added at least one waypoint.
- **Destruction/upgrade hooks into colony-wide guard tracking**: both `onDestroyed()` and `onUpgradeComplete()` call `guardBuildingChangedAt()` on the colony's building manager. Confirmed in [`RegisteredStructureManager`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/managers/RegisteredStructureManager.java): this recomputes a chunk-aligned bounding box from the guard building's claim radius and, for every other building whose position falls inside it, calls `resetGuardBuildingNear()` to force that building to lazily recheck whether it's still covered by a nearby guard. The companion method `hasGuardBuildingNear()` is what other buildings actually query to know if they're protected — the exact downstream consumer (likely raid targeting/building vulnerability logic, given the naming) wasn't traced further, but the core "which buildings are covered by which guard's claim radius, recalculated whenever a guard building changes level or is destroyed" mechanism is confirmed.
- No settings beyond the standard guard settings (`GUARD_TASK`, `RETREAT`, `HIRE_TRAINEE`, `PATROL_MODE`, `FOLLOW_MODE`) from `AbstractBuildingGuards`.
