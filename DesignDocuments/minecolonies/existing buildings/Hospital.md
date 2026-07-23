### Function
Heals sick citizens and cures diseases (Influenza, Measles, Smallpox by default — data-driven, see `data/minecolonies/colony/diseases/`), using beds found in its own schematic and per-disease cure items.

- Building: [`BuildingHospital`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingHospital.java)
- Disease data: [`DiseasesListener`](../../../minecolonies/src/main/java/com/minecolonies/core/datalistener/DiseasesListener.java) loads `Disease` definitions (including their cure items) from datapack JSON.

### Levels
Max building level: 5. Bed count (see Limits) is the main schematic-driven scaling factor, but several healing mechanics also scale with building level, confirmed in [`EntityAIWorkHealer`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/service/EntityAIWorkHealer.java): the Healer will proactively heal any player in the building whose health has dropped below `maxHealth - 10 - (2 × buildingLevel)`, healing them up to `maxHealth - 5 - buildingLevel`; it will also wander out to heal a random low-health colony citizen (not just ones assigned as "patients") within `buildingLevel × 40` blocks of the hospital, using the same heal-amount formula.

### Research
[`stamina.json`](../../../minecolonies/src/datagen/generated/minecolonies/data/minecolonies/researches/civilian/stamina.json) ("Stamina" — a research name, not the skill of the same name) costs just 1 Carrot, has **no building-level prerequisite at all**, and its effect (`blockhuthospital`) unlocks the Hospital for placement — one of the cheapest and least-gated unlock researches in this reference.

### Skills
- Primary: Mana
- Secondary: Knowledge

(`HEALER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Bed capacity is fully schematic-controlled**: `registerBlockPosition()` records every bed block found in the blueprint (`bedMap`, keyed by the head-of-bed position), with no cap or level-scaling on the count anywhere in `BuildingHospital`.
- **Bed occupancy is actively synced with the real world state every wake-up** (`onWakeUp()`): the building reconciles its internal `bedMap` against whether the citizen it thinks is occupying a bed is actually asleep and physically near it, correcting the vanilla bed's `OCCUPIED` blockstate either way — including clearing stale assignments if the citizen record no longer exists.
- **Reserved inventory**: up to 10 units of anything recognized as a cure item for any currently-loaded disease (`isCureItem()`, checked against `DiseasesListener.getDiseases()`), and `canEat()` separately blocks citizens from eating cure items outright — the same reserved-medical-stock pattern as other buildings' reserved crafting stock.
- **Patient records persist independently of bed assignment**: a `Patient` file is created/tracked per sick citizen (`checkOrCreatePatientFile()`/`removePatientFile()`) separately from the bed map, tracking a `NEW → REQUESTED → TREATED` state machine. Confirmed in `EntityAIWorkHealer`: a `NEW` patient has their disease's cure items requested; once `REQUESTED`, the Healer either delivers physical cure items to the patient's own inventory (for the citizen to then self-administer) or, with a random chance, performs a "free cure" instead — a magical instant cure requiring no items at all, channeled over `MAX_PROGRESS_TICKS` (30) ticks with heart particle effects. The free-cure chance is `max(1, secondarySkillLevel / 20)` out of 3600 per decision cycle (roughly the worker's Knowledge-skill-level-over-10 chance per hour, per the code's own comment) — a genuine, skill-scaled shortcut around needing cure items at all, not just a display detail.
