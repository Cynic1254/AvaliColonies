### Function
The central administrative building of the colony — permissions, colony-wide settings, event/history tracking, and (via placed Filled Maps in its inventory) an in-game map display.

- Building: [`BuildingTownHall`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingTownHall.java), implements `ITownHall`

### Levels
Max building level: 5. Claim radius is a custom, non-linear table (confirmed in source) rather than a simple per-level formula: 0 (unbuilt) / 1 / 1 / 2 / 3 / **5** — note the jump from 3 chunks at level 4 straight to 5 chunks at level 5, skipping 4 entirely.
- Reaching **Town Hall level 2** unlocks the Graveyard's "Remembrance" research; reaching **level 4** unlocks "Village" (population cap tier 4); reaching **level 5** unlocks "City" (population cap tier 5, the last tier) — see the Graveyard and Residence docs.

### Research
No building-unlock research found (it's the colony's starter building, placed via the Supply Camp/Ship or a starting item rather than built up from nothing like most others).

### Skills
N/A — the Town Hall has no worker of its own.

### Limits
- **`MOVE_IN`** setting: toggles whether new citizens can spawn/move into the colony at all.
- **`ENTER_LEAVE_MESSAGES`**, **`AUTO_HIRING_MODE`**, **`AUTO_HOUSING_MODE`**, **`CONSTRUCTION_TAPE`** settings: chat notifications, automatic job/house assignment toggles, and whether unfinished construction sites get roped off with tape, respectively — plain boolean toggles read directly by their respective systems elsewhere (citizen job/housing auto-assignment, construction-tape placement helper), not building-specific logic worth expanding on further here.
- **Never "gathered from"** (`canBeGathered()` returns `false`, same as the Warehouse) — it holds colony-wide bookkeeping items (maps, etc.), not a worker's personal stock.
- **Tracks up to `MAX_COLONY_EVENTS` permission events** in a rolling window (oldest dropped once full) — a history log rather than a hard functional limit.
- **Ally teleport permission is a server config flag** (`canPlayerUseAllyTHTeleport`), synced to the client view rather than being a building setting — worth checking the config directly if teleport availability matters for your addon rather than assuming it's always on.
