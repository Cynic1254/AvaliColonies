### Function
Trains **Knights-in-training** (squires) by pairing them up with sparring partners and having them fight at target-dummy stations, so they build Adaptability/combat skill before graduating into a real Knight role at a [Guard Tower](Guard%20Tower.md)/[Barracks Tower](Barracks%20Tower.md)/[Gatehouse](Gatehouse.md). Not a guard building itself (extends plain `AbstractBuilding`) — see **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for how trainees get auto-promoted.

- Building: [`BuildingCombatAcademy`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingCombatAcademy.java)
- Trainee AI: [`EntityAICombatTraining`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/training/EntityAICombatTraining.java), extending [`AbstractEntityAITraining`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/training/AbstractEntityAITraining.java)
- Assignment module: `KNIGHT_TRAINING` in `BuildingModules` — a `WorkAtHomeBuildingModule` for the `knightInTraining` job, sized to the building level (`ICommonBuilding::getBuildingLevel`)

### Levels
Max building level: 5. Trainee capacity scales with building level (same `WorkAtHomeBuildingModule` sizing pattern as most jobs).

### Research
The Combat Academy is gated behind research: [`improvedswords.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/combat/improvedswords.json) ("Improved Swords") requires a **Barracks at level 3**, costs 6 Iron Blocks, chains directly off "Tactic Training" (see the Barracks doc), and its effect (`blockhutcombatacademy`) unlocks the Combat Academy for placement.

### Skills
Primary Adaptability, Secondary Stamina (`KNIGHT_TRAINING` in `BuildingModules`).

### Limits
- **Target dummies are schematic-detected, not counted/configured**: `registerBlockPosition()` looks for a Carved Pumpkin block sitting directly on top of a Hay Bale block anywhere in the structure, and records the Hay Bale's position as a valid `fightingPos`. No such pair in the blueprint means no solo-training target positions at all — purely a schematic-authoring limit.
- **Sparring partners are paired at runtime, not schematic-defined**: `getRandomCombatPartner()` picks any other currently-assigned trainee who isn't already paired with someone else (`trainingPartners`, a bidirectional map) and locks the pairing until reset — so with an odd number of trainees, one will always be left training solo against a dummy instead. Confirmed in `EntityAICombatTraining`: a pairing is reset (`resetPartner()`) once a trainee has landed `building.getBuildingLevel() × 5` attack actions (`ACTIONS_PER_BUILDING_LEVEL = 5`) against that partner — a higher-level academy runs longer sparring sessions before reshuffling pairs. There's also a flat 25% chance per decision cycle to seek a partner even when not currently paired, otherwise the trainee spars a dummy instead.
- **Training does deal real damage, confirmed in `EntityAICombatTraining.attack()`**: each strike calls `trainingPartner.hurt(..., 0.0F)` — a real damage event with 0 base damage, so it still applies i-frames/knockback-adjacent reactions without actually costing HP. Each attack attempt has a 50% chance to instead raise a Shield and block rather than swing. Every attack (whether against a partner or a dummy) grants `XP_BASE_RATE = 2` XP and is tracked for real skill level-ups (`LEVELS_GAINED` stat, checked via before/after primary and secondary skill level).
- **Graduation is entirely the guard building's responsibility, not this building's**: a Combat Academy never "promotes" anyone itself — it just holds `knightInTraining` workers and lets any Guard Tower/Barracks Tower/Gatehouse with `HIRE_TRAINEE` enabled poach the highest-skill trainee on its own tick, as documented in the shared guard doc. A Combat Academy with `HIRE_TRAINEE` disabled colony-wide (or no guard building wanting Knights) will simply accumulate trainees indefinitely.
- No exposed settings.
