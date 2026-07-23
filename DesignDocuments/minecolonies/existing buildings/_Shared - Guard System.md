> Shared behaviour reference for all true guard buildings (buildings extending `AbstractBuildingGuards`, housing a hired Knight/Ranger/Druid/Cavalry): [Guard Tower](Guard%20Tower.md), [Barracks Tower](Barracks%20Tower.md), [Gatehouse](Gatehouse.md), and (for its cavalry half) [Stable](Stable.md). The plain [Barracks](Barracks.md) hub building, and the two trainee/squire buildings [Combat Academy](Combat%20Academy.md) and [Archery](Archery.md), are documented in their own files since they don't extend this base class.
>
> This system is considerably more custom than the crafting or herding systems — there's no tag-based datapack layer here at all; almost everything is hardcoded Java, with research effects providing the main external lever. Expect fewer "add a json file" extension points and more "override a Java method" ones.

### Core classes
- Building base: [`AbstractBuildingGuards`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/AbstractBuildingGuards.java) — settings (`GUARD_TASK`, `RETREAT`, `HIRE_TRAINEE`, `PATROL_MODE`, `FOLLOW_MODE`), patrol-point bookkeeping, rally-banner support, per-level bonuses.
- Assignment module: [`GuardBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/GuardBuildingModule.java) (extends `WorkAtHomeBuildingModule`) — constructed from a `GuardType` (`ModGuardTypes.knight/ranger/druid/cavalry`), which itself supplies the primary/secondary skill; see `BuildingModules` for the `*_BARRACKS_WORK`/`*_TOWER_WORK`/`*_GATE_WORK` constants (sizeLimit differs: Barracks scales with building level, Tower/Gatehouse are fixed small numbers).
- Shared AI base: [`AbstractEntityAIGuard<J, B>`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/AbstractEntityAIGuard.java) (itself extends `AbstractEntityAIFight`) — the task-decision state machine (sleep/wake, flee/regen, patrol/guard/follow/patrol-mine, rally).
- Per-guard-type combat AI (plugged into the shared state machine, handles the actual attack): [`KnightCombatAI`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/KnightCombatAI.java), [`RangerCombatAI`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/RangerCombatAI.java), [`DruidCombatAI`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/DruidCombatAI.java), [`CavalryCombatAI`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/guard/CavalryCombatAI.java) (see the Stable doc for cavalry specifics).
- Constants: [`GuardConstants`](../../../minecolonies/src/main/java/com/minecolonies/api/util/constant/GuardConstants.java) — nearly every numeric knob referenced below lives here.

### The task state machine (what a guard does when not fighting)
`AbstractEntityAIGuard.decide()` runs every 100 ticks and dispatches on the `GUARD_TASK` setting:
- **`PATROL`** — walks between patrol points. Two sub-modes controlled by `PATROL_MODE`: automatic (random building-to-building patrol, occasionally pathfinding to a nearby random position instead) or `MANUAL` (cycles through player-added `patrolTargets`, added/reset via in-game commands/GUI). Multiple guards patrolling together wait briefly for each other at each point (`arrivedAtPatrolPoint`) before all advancing together.
- **`GUARD`** — stays near a fixed `guardPos` (small random wander only), the tightest leash of the four tasks.
- **`FOLLOW`** — follows a specific player (`followPlayerUUID`), teleporting back if it strays too far; guards on `FOLLOW` also get a permanent glow effect so the player can spot them.
- **`PATROL_MINE`** — patrols between completed nodes of an assigned `BuildingMiner`'s current mining level; automatically falls back to `PATROL` if the assigned mine is missing or has no current level.
- **Rally overrides everything**: if a `rallyLocation` is set (via an `ItemBannerRallyGuards` banner, or a static admin-set point), guards actively glow and beeline there instead of their normal task, with movement speed scaling with Adaptability skill. Rally locations more than 500 blocks from the colony (or in another colony's claim) are rejected unless the colony has completed a research granting the `TELESCOPE` effect.
- **Persecution distance** — how far a guard will chase a fleeing target from its task's "home" reference point — depends on the task (patrol: 80 blocks; follow/patrol-mine: 30; guard: 10, +20 more specifically for Knights) and is a hard leash: beyond it the guard gives up the chase.

### Sleep, flee, and regen
- **Guards periodically fall asleep** if not currently fighting or recently hurt: roughly a 1-in-(20 + Adaptability/2) chance every ~10 seconds, further reduced by the `SLEEP_LESS` research effect. While asleep (`GUARD_SLEEP`), they sit, slowly regenerate HP, and can be explicitly woken by another guard walking over and striking them (`wakeUpGuard`) if that guard notices them nearby.
- **Fleeing** (`GUARD_FLEE`) triggers when HP drops below 20% of max **and** the `RETREAT` setting is on **and** the colony has completed a research granting the `RETREAT` effect (all three are required — the setting alone does nothing without the research). A fleeing guard gets a `MOVEMENT_SPEED` boost sized by the `FLEEING_SPEED` research effect and runs back to the building.
- **Regen** (`GUARD_REGEN`) kicks in below 75% HP (if `RETREAT` setting is on) — applies vanilla Regeneration and holds the guard back at the building until healed, but will instantly break off to fight again if a nearby target gets within 10 blocks.

### Per-level bonuses (Building code, `AbstractBuildingGuards`)
- **Bonus health**: `+2 × building level`, plus Guard Tower adds a flat `+20` on top for having only one guard slot at all (making a single Guard Tower guard noticeably tankier than a Barracks Tower one at the same level).
- **Bonus vision range**: `15 + 3 × building level`.
- **Patrol distance**: `50 + building level × 30` (`PATROL_BASE_DIST` + `IGuardBuilding.PATROL_DISTANCE`, both confirmed in source) — 80 / 110 / 140 / 170 / 200 blocks for levels 1–5.
- **Armor tier is gated by building level**, not chosen freely — confirmed ranges from `GuardConstants`: Leather is always allowed (levels 0–99), Gold only at levels 1–2, Chainmail at 2–3, Iron at 3–4, Diamond at 4–5. A level 1 guard building literally cannot equip a Diamond-armored guard. (Shields are allowed at any level 1–5.)
- **`getMaxEquipmentLevel()`** governs the tool/weapon tier ceiling the same way — most guard buildings use the default level-for-level mapping, but Gatehouse has a bespoke formula (see its own doc) since it caps at building level 3 instead of 5.

### Hiring and the trainee→guard pipeline
- Normal auto-hire (`WorkAtHomeBuildingModule`/`BuildingUtils.canAutoHire`) fills a guard slot from any jobless citizen, same as most jobs.
- **If `HIRE_TRAINEE` is enabled**, `GuardBuildingModule.onColonyTick()` instead first scans **every citizen in the colony** for one currently working as `archerInTraining` (at an [Archery](Archery.md)) or `knightInTraining` (at a [Combat Academy](Combat%20Academy.md)) matching this building's guard type, picks whichever has the **highest primary-skill level**, strips their trainee job, and directly assigns them as a real Knight/Ranger — a genuine "graduation" mechanic, not just a preference weighting. Only if no suitable trainee exists does it fall back to normal jobless-citizen hiring.
- **Un-hiring strips all equipment**: `onRemoval()` clears every armor slot and both hands and dumps the items back into the citizen's own inventory (not the building's) — worth knowing if you're scripting mass guard reassignment.

### Combat AI quick reference
Each guard type is a completely separate `AttackMoveAI` subclass with its own damage formula, range, and quirks — there is no shared "combat" tag/config layer to point at, only these classes:

| Type | Weapon check | Damage basis | Notable mechanics |
|---|---|---|---|
| **Knight** | Sword (or spear/Tinkers weapon) | Weapon damage + `MELEE_DAMAGE` research effect; **doubles below 20% HP** | Shield-blocking (needs `SHIELD_USAGE` research), AoE knockback "whirlwind" (`KNIGHT_WHIRLWIND` research, ~1-in-5 chance, 8s cooldown), can taunt mobs onto itself (`KNIGHT_TAUNT` research), attack speed scales with Adaptability |
| **Ranger** | Bow | Agility/5 + enchants + `ARCHER_DAMAGE` effect; **doubles below 20% HP** | Kites away from adjacent targets, can fire 2 arrows at once (`DOUBLE_ARROWS` research), can pierce (`ARROW_PIERCE`), can consume real Arrow items from inventory for +2 bonus damage (`ARCHER_USE_ARROWS` research — also what makes the building's 128-arrow `keepX` reservation actually matter), range scales with building level + Agility (capped at `MAX_DISTANCE_FOR_RANGED_ATTACK`) |
| **Druid** | Splash potions (needs `minecolonies:magicpotion`, i.e. the [Alchemist](Alchemist%20Laboratory.md)'s output, to throw *empowered* effects — `DRUID_USE_POTIONS` research) | N/A — support/debuff role, not raw damage | Throws **debuffs** (Slowness/Weakness) at valid combat targets, or **buffs** (Strength/Saturation/Instant Health/Resistance) at friendly citizens/players under attack — same throw mechanic, different effect pool depending on who's being targeted; effect duration and accuracy come from the Druid's own primary/secondary skill levels (`ModGuardTypes.druid.get().getPrimarySkill()/getSecondarySkill()`) |
| **Cavalry** | Mounted melee | See the [Stable](Stable.md) doc | Only obtainable via the Stablemaster's horse-conversion mechanic, not hired directly like the other three |

All four share: a **critical hit chance** driven by the `GUARD_CRIT` research effect (×1.5 damage), a global `guardDamageMultiplier` server config applied to every final damage number, and `EXP_PER_MOB_DEATH = 15` XP per kill.

### Extending: adding a new guard-capable building
1. Extend `AbstractBuildingGuards` (not plain `AbstractBuilding`) to get the settings/patrol/rally/armor-tier machinery for free.
2. Register a `GuardBuildingModule` per hireable `GuardType` in `BuildingModules`, choosing an appropriate `sizeLimit` function (flat constant vs. building-level-scaled).
3. If you need a genuinely new combat style rather than reusing Knight/Ranger/Druid, you'll be writing a new `AttackMoveAI` subclass — there's no data-driven shortcut here, unlike crafting recipes.
4. If the building should feed from a trainee pipeline like Combat Academy/Archery, look at `WorkAtHomeBuildingModule` and mirror how `archerInTraining`/`knightInTraining` are wired into `GuardBuildingModule.onColonyTick()`.
