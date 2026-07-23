### Function
The Undertaker buries deceased citizens in graves within this building's schematic, first attempting to revive them — a real, tunable percentage chance rather than a fixed outcome.

- Building: [`BuildingGraveyard`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingGraveyard.java)
- Grave management: delegated to the colony's own `GraveManager` (`colony.getGraveManager()`), not tracked per-building beyond visual grave marker positions.
- Constants: [`UndertakerConstants`](../../../minecolonies/src/main/java/com/minecolonies/api/util/constant/UndertakerConstants.java) — every number below is confirmed directly from this file.

### Levels
Max building level: 5. Building level contributes `+0.5% per level` to the resurrection chance (see Limits); no other level-scaling exists.

### Research
[`remembrance.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/remembrance.json) requires a **Town Hall at level 2**, costs 8 Bone, and its effect (`blockhutgraveyard`) unlocks the Graveyard for placement. It's also the parent of a two-tier resurrection-chance research chain: [`resurrectchance1.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/resurrectchance1.json) (Graveyard level 3, 1 Ghast Tear) and [`resurrectchance2.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/resurrectchance2.json) (Graveyard level 5, 16 Chorus Fruit), both adding to the `resurrectchanceaddition` research effect.

### Skills
- Primary: Strength
- Secondary: Mana

(`GRAVEYARD_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
**The resurrection chance formula is fully confirmed from `UndertakerConstants`**, number for number:
- Base cap: **2.5%** (`MAX_RESURRECTION_CHANCE = 0.025`), before any totem bonus.
- **+0.5% per Graveyard building level** (`RESURRECT_BUILDING_LVL_WEIGHT = 0.005`).
- **+0.125% per point of the Undertaker's secondary skill** (Mana) (`RESURRECT_WORKER_MANA_LVL_WEIGHT = 0.00125`).
- **+0.5% per level of the colony's best [Mystical Site](Mystical%20Site.md)**, added to the *cap* itself rather than the roll (`MAX_RESURRECTION_CHANCE_MYSTICAL_LVL_BONUS = 0.005`) — see that building's doc.
- **Totems of Undying**: +5% for having one in stock, +7.5% for two (`SINGLE_TOTEM_RESURRECTION_CHANCE_BONUS = 0.05` / `MULTIPLE_TOTEMS_RESURRECTION_CHANCE_BONUS = 0.075`), with each resurrection attempt carrying a **1% chance that the totem used breaks** (`TOTEM_BREAK_CHANCE = 0.01`). A `keepX` reservation holds up to 2 Totems of Undying for this purpose.
- Two further research tiers ("resurrectchance1"/"2") add to a separate `resurrectchanceaddition` effect on top of all the above — the research chain and its building-level gates are confirmed, but the exact percentage per tier could not be pinned down: the effect's magnitude isn't stored in the research JSON itself (which only specifies an effect id and an integer "level"), and the effect-type registration (`ModResearchEffectInitializer`/`GlobalResearchEffect`) just takes a plain `double effect` value at construction without revealing where that number is sourced from for a given id/level pair. Genuinely unresolved after checking the research JSON, the effect registry, and the `GlobalResearchEffect` class itself.
- **Reserved inventory** (`keepX`): 1 shovel (any tier) plus the 2 Totems of Undying above.
- **Grave assignment is colony-wide, not per-building**: `getGraveToWorkOn()` reserves the next free grave from the colony's shared `GraveManager` rather than a list local to this specific Graveyard — relevant if a colony has more than one Graveyard.
