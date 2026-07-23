### Function
A fixed defensive checkpoint housing paired Knight+Ranger guards at schematic-defined positions, always in Guard-only mode (never patrols or follows), and integrates with the colony's road/connection network. See **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for the shared combat/hiring framework — the Gatehouse overrides an unusual number of the shared defaults, documented here.

- Building: [`BuildingGateHouse`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingGateHouse.java)
- Guard modules: `KNIGHT_GATE_WORK`, `RANGER_GATE_WORK` in `BuildingModules` — **no Druid option** at the Gatehouse, unlike Guard Tower/Barracks Tower; each capped at **2** guards (`(b) -> 2`).

### Levels
Max building level: **3**, not the usual 5 — but its effective combat power is stretched across that shorter range via `getBuildingLevelEquivalent()`, a bespoke override: level 1 → equivalent 1, level 2 → equivalent 3, level 3 → equivalent 5. Every per-level bonus formula (health, vision, equipment tier) uses this **equivalent** level rather than the raw building level, so a level 3 Gatehouse guard is exactly as strong as a level 5 guard at a normal Guard Tower — just reached in 3 upgrade steps instead of 5.
- Claim radius: 1 / 1 / 2 for levels 1–3 (custom table, not the standard formula).
- `getMaxEquipmentLevel()` is also custom: capped at the true maximum once the Gatehouse hits its own max level (3), otherwise derived from the equivalent level the same way armor tiers are elsewhere.

### Research
No building-unlock or recipe-specific research found. Combat-affecting research is colony-wide — see shared guard doc.

### Skills
Guard type skills come from the `GuardType` registry entry, same as other guard buildings.

### Limits
- **Always Guard-only, never configurable**: `getTask()` is hardcoded to return `GuardTaskSetting.GUARD` regardless of the `GUARD_TASK` setting's stored value — a Gatehouse guard can never be set to patrol, follow, or patrol-mine. This makes sense for a checkpoint but is worth knowing if a setting change silently has no effect here.
- **Guard positions are schematic-tag-defined and index-matched to hire order**: `getGuardPos()` requires at least 2 positions tagged `knight` and at least 2 tagged `archer` in the blueprint (logging an error if not) — each hired guard is assigned the tagged position matching their index in the assignment list (first Knight hired → first `knight`-tagged position, etc.), not a random or nearest one. A Gatehouse schematic with fewer than 2 of either tag will misbehave (guards fall back to standing at the building's own block).
- **Registers itself with the colony's road/connection network**: `setBuildingLevel()` (once level ≥ 1) calls `colony.getConnectionManager().addNewGateHouse()`, and `destroy()` removes it. Confirmed purpose in [`ColonyConnectionManager`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/managers/ColonyConnectionManager.java): this is a genuine **inter-colony road network** — placed Colony Signs form `ColonyConnectionNode`s linked by pathfinding (`PathJobSignConnection`) into road segments, and each colony's registered Gatehouses act as that colony's entry/exit points into the resulting graph, tracked as `directlyConnectedColonies`/`indirectlyConnectedColoniesCache`. In short: building signed roads between colonies and having a Gatehouse at each end is what lets the game recognize two colonies as connected. The `ColonyConnectionModule` on the Gatehouse building itself is a server-side no-op marker — all the actual graph logic lives in the manager, not the module.
- Same destruction/upgrade colony-tracking hooks (`guardBuildingChangedAt`) and automatic-vs-manual patrol fallback logic (`requiresManualTarget()`) as Guard Tower, even though patrol is never actually reachable here given the hardcoded `GUARD` task.
- No settings beyond the standard guard settings, though `GUARD_TASK` is effectively inert as noted above.
