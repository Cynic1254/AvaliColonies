### Function
The central hub of a multi-tower defense complex — the Barracks itself hires no guards and isn't a guard building at all (it extends plain `AbstractBuilding`, not `AbstractBuildingGuards`); its job is to host up to several **Barracks Towers** (see that doc), which are the actual guard buildings, and to auto-fund an anti-spy defense during raids.

- Building: [`BuildingBarracks`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBarracks.java)
- Related: [`BuildingBarracksTower`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBarracksTower.java) (documented separately), [_Shared - Guard System](_Shared%20-%20Guard%20System.md)

### Levels
Max building level: 5. Claim radius depends on the towers' state, not just the Barracks' own level (see Limits).

### Research
The Barracks itself is gated behind research: [`tactictraining.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/combat/tactictraining.json) ("Tactic Training") requires a **Guard Tower at level 3**, costs 3 Iron Blocks, and its effect (`blockhutbarracks`) unlocks the Barracks for placement. This also starts a chain: reaching **Barracks level 3** in turn unlocks "Improved Swords" (Combat Academy) and "Improved Bows" (Archery) — see those buildings' own docs.

### Skills
N/A — the Barracks has no worker of its own.

### Limits
- **Towers are discovered from the schematic, not configured**: whenever a `blockHutBarracksTower` block is found while the Barracks registers its structure (`registerBlockPosition()`), the corresponding `BuildingBarracksTower` at that position has its structure pack copied from the parent Barracks and is added to this building's internal `towers` list (persisted to NBT). How many towers a Barracks has is therefore entirely a function of how many are physically present in its blueprint — this is a **schematic-authoring-controlled** limit, not a setting or formula.
- **Claim radius depends on tower levels, not just the Barracks' own level**: `getClaimRadius()` returns a radius of 3 only if **every** registered tower is at building level 4 or higher; otherwise it's 2 (and 0 if the Barracks itself is unbuilt). Upgrading the Barracks alone doesn't expand its claim — every tower has to be upgraded too.
- **Auto-funded spy defense**: on every colony tick, if the colony is currently being raided and spies aren't already enabled, the Barracks automatically tries to spend `SPIES_GOLD_COST` (5) Gold Ingots from its own inventory to enable `colony.getRaiderManager().setSpiesEnabled(true)` — spies are turned back off automatically once the raid ends. This is **Building code** reacting to colony raid state, not a worker action; it happens whether or not the Barracks has any towers built yet. A `keepX` reservation holds a full stack of Gold Ingots so this doesn't get starved by the request system.
- **Destruction clears its towers' blocks directly**: `onDestroyed()` iterates the `towers` list and sets each tower's block to air in the world — destroying the Barracks demolishes its towers as a side effect, not just administratively unlinking them.
- No exposed settings (settings live on the individual Barracks Towers, not the hub).
