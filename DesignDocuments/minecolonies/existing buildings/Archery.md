### Function
Trains **Archers-in-training** (squires) at shooting stands aimed at target blocks, before they graduate into a real Ranger role at a [Guard Tower](Guard%20Tower.md)/[Barracks Tower](Barracks%20Tower.md)/[Gatehouse](Gatehouse.md). Not a guard building itself — see **[_Shared - Guard System](_Shared%20-%20Guard%20System.md)** for the auto-promotion mechanic.

- Building: [`BuildingArchery`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingArchery.java)
- Trainee AI: [`EntityAIArcherTraining`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/training/EntityAIArcherTraining.java), extending `AbstractEntityAITraining`
- Assignment module: `ARCHERY_WORK_HOME` in `BuildingModules` — a `WorkAtHomeBuildingModule` for the `archerInTraining` job, sized to building level

### Levels
Max building level: 5 (`BuildingArchery` has no `getMaxBuildingLevel()` override, so it uses `AbstractBuilding`'s default `CONST_DEFAULT_MAX_BUILDING_LEVEL`). Trainee capacity scales with building level.

### Research
The Archery is gated behind research: [`improvedbows.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/combat/improvedbows.json) ("Improved Bows") requires a **Barracks at level 3**, costs 6 Iron Blocks, chains directly off "Tactic Training" (see the Barracks doc), and its effect (`blockhutarchery`) unlocks the Archery for placement.

### Skills
Primary Agility, Secondary Adaptability (`ARCHERY_WORK_HOME` in `BuildingModules`).

### Limits
- **Shooting infrastructure is schematic-detected**: `registerBlockPosition()` scans for vanilla `Blocks.TARGET` (recorded as `shootingTargets`) and `Blocks.GLOWSTONE` (recorded as `shootingStands`, matching the "glowstone where archers stand" convention also referenced in this project's building-guide notes) anywhere in the blueprint.
- **Stand selection prefers a schematic tag over the raw glowstone scan**: `getRandomShootingStandPosition()` checks for any position tagged `work` first, and only falls back to the recorded Glowstone-block positions if no `work`-tagged position exists. If you're authoring a custom Archery schematic, tagging the intended stand positions with `work` directly is the more reliable approach rather than relying on incidental Glowstone placement.
- A blueprint with no Target blocks has no valid shooting targets at all, regardless of level — purely schematic-authoring-controlled, same as the Combat Academy's dummy detection.
- **Graduation is entirely the guard building's responsibility**, exactly as documented for the Combat Academy — an Archery building never promotes anyone itself, it just accumulates `archerInTraining` workers for a `HIRE_TRAINEE`-enabled guard building to poach the highest-Agility one from.
- No exposed settings.
