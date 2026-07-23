### Function
Houses 4 citizens (twice a base Residence) and periodically attracts visiting Visitors who can be recruited (via items) into the colony. Like the Quarry, **this building has no dedicated Java class** — it's registered as a plain `DefaultBuildingInstance` (`"tavern"`, max level 3) carrying living/visitor modules, confirmed in [`ModBuildingsInitializer`](../../../minecolonies/src/main/java/com/minecolonies/apiimp/initializer/ModBuildingsInitializer.java).

- Living module: `TAVERN_LIVING` (`TavernLivingBuildingModule`)
- Visitor module: `TAVERN_VISITOR` (`TavernBuildingModule`)

### Levels
Max building level: **3** (hardcoded in the `DefaultBuildingInstance` registration), unlike almost every other building's max of 5. There is no numeric per-level progression for housing capacity — the Tavern's defining trait is a flat 4-citizen capacity that **cannot be increased by upgrading**, unlike a Residence's linear 1-per-level scaling.

### Research
No building-unlock research found for the Tavern.

### Skills
N/A — the Tavern has no worker of its own (visitors and residents aren't "hired" jobs).

### Limits
- **No colony-wide "one Tavern" enforcement was found in code**: `RegisteredStructureManager.addBuilding()` (the central place buildings get registered) explicitly limits the Town Hall to one instance (`if (building instanceof BuildingTownHall && townHall == null)`), but has no equivalent check for the Tavern — multiple Taverns appear to be buildable in the same colony as far as this checkout's registration logic goes. If a single-Tavern rule exists, it isn't in the building-registration path and may only be a soft convention (e.g. enforced by scarcity of the placement item) rather than a hard rule.
- **Visitor recruitment mechanics, confirmed in [`TavernBuildingModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/TavernBuildingModule.java)**: the Tavern can host up to `3 × building level` visitors at once; a new one spawns whenever below that cap, on a cooldown randomized as `random(0–3000) + (6000 / buildingLevel) × currentColonyCitizens / maxColonyCitizens` ticks — higher Tavern level shortens the cooldown, while a more-populated colony lengthens it. Each visitor's recruit cost, starting skill level, and free boots come from a level-gated table (`RecruitmentItemsListener.getRandomRecruitCost(buildingLevel)`, a datapack-driven cost list not individually inspected here). Visitors that gather in groups of 2+ trigger a tavern music theme, and each spawns with a randomized recruitment-story chat prompt unless a custom-visitor event overrides it.
