### Function
Fishes at nearby bodies of water discovered via pathfinding, remembering up to 20 "pond" locations to revisit and reporting when only substandard water is available.

- Building: [`BuildingFisherman`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingFisherman.java) — deliberately minimal; almost all of the Fisherman's behavior lives in the AI, not the building class.
- AI: [`EntityAIWorkFisherman`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/agriculture/EntityAIWorkFisherman.java)

### Levels
Max building level: 5. Bonus fishing loot is keyed by building level via `ModLootTables.FISHERMAN_BONUS` — a real, non-JEI loot table roll (same category of mechanic documented in the crafting shared doc's "two distinct loot-table uses" section) fired every time a fish is actually caught, with the loot roll's luck parameter set from the Fisherman's primary skill level. Confirmed exact contents: levels 1–2 have **no bonus loot at all** (empty tables); level 3 unlocks a small chance (2.5% each, out of 1000 total weight) at Prismarine Shard or Prismarine Crystals; levels 4–5 add Sponge on top (weight 1, roughly a 0.1% chance) alongside the same prismarine odds — level 4 and 5's tables are otherwise identical.

### Research
No building-unlock research found for the Fisher's Hut — consistent with it being an early/starter building. Reaching **Fisherman level 3** is itself the trigger for the Sifter's unlock research ("Sieving" — see the Sifter's Hut doc), an easy-to-miss cross-building dependency since nothing about fishing obviously suggests sifting.

### Skills
- Primary: Focus
- Secondary: Agility

(`FISHER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Water quality is assessed by the pathfinding system, not the building**: confirmed in [`Pond.java`](../../../minecolonies/src/main/java/com/minecolonies/api/util/Pond.java) — a candidate pond must be at least **5×5 blocks wide and 2 blocks deep** (`WATER_POOL_WIDTH_REQUIREMENT = 5`, `WATER_DEPTH_REQUIREMENT = 2`; checked via a spiral scan of radius `(5-1)/2 = 2` around the candidate position) to count as valid at all — this corrects an older, commonly-cited "7×7×2" figure, which does not match this checkout's source. Beyond the size check, `PondState` further distinguishes `VALID` (the water blocks are source blocks) from `SUBOPTIMAL` (flowing, non-source water) from `INVALID` (not enough water, or blocked). Arriving at a `SUBOPTIMAL` pond triggers a chat complaint and the Fisherman immediately goes looking for a different one instead of fishing there.
- **Remembers up to 20 ponds** (`MAX_PONDS`) within a 50-block search radius (expanded to 150 for a fresh search when no ponds are known yet), picking randomly among remembered ponds most of the time but with a 5% chance per catch to go scout an entirely new one.
- **Casting/reeling has real skill-based mechanics**: cast timing depends on a random chance gated by secondary skill (`FISHING_SKILL_CHANCE + secondarySkillLevel/5`), lure speed scales with primary skill (`primarySkillLevel / 25` bonus, stacking with any rod-enchant fishing-speed bonus), and the bonus-loot roll's luck is set from primary skill level — so primary and secondary skill affect different parts of the process (bite/lure speed and bonus loot vs. cast/catch timing reliability).
- **Reserved inventory** (`keepX`): 1 fishing rod (any tier).
