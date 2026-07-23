### Function
Raises cows, goats, and mooshrooms: milks cows/goats, collects stew from mooshrooms, breeds, and butchers. Shares its core behaviour with all other herders — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the full state machine.

- Job: `cowboy` — [`JobCowboy`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobCowboy.java)
- Building: [`BuildingCowboy`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingCowboy.java)
- AI: [`EntityAIWorkCowboy`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/production/herders/EntityAIWorkCowboy.java)
- Herding module: `BuildingCowboy.HerdingModule` (compatible animals: `Cow` and `Goat`; breeding item: 2× Wheat)

### Levels
Max building level: 5. Max cows/goats housed = `building level × 2` (default multiplier, soft cap — see shared doc):
1. 2 housed
2. 4 housed
3. 6 housed
4. 8 housed
5. 10 housed

### Research
No research gate found for this building or its recipes in the current source tree.

### Skills
- Primary: Athletics
- Secondary: Stamina

(`COWHERDER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Housing cap** — shared herder mechanism (butcher-chance curve), see above.
- **Butchering damage** scales with Primary skill: `max(1.0, primarySkillLevel / 10.0)`.
- **Milking/stewing are rate-limited per "milking day" cycle**, independent of the animal cap:
  - Settings (all via the `COWHERDER_SETTINGS` module, GUI-editable): `MILKING_AMOUNT`, `STEWING_AMOUNT`, `MILKING_DAYS`, and `MILK_ITEM` (choice between vanilla Milk Bucket or the modded `large_milk_bottle`/`large_empty_bottle` pair).
  - The `HerdingModule` tracks `currentMilk`/`currentStew`/`currentMilkDays` counters (persisted to NBT), incremented in `onMilked()`/`onStewed()`, and reset to 0 every time `currentMilkDays` reaches the `MILKING_DAYS` setting (`onWakeUp()` — i.e. checked once per in-game day when the colony's citizens sleep/wake, not on a real tick timer).
  - `canTryToMilk()` / `canTryToStew()` gate whether the AI is even allowed to enter the `COWBOY_MILK` / `COWBOY_STEW` states in `decideWhatToDo()` — this is a **hard cap** per cycle, unlike the soft butchering cap.
  - This is entirely **Building code** (the module holds and persists the counters); the AI only reads `canTryToMilk()/canTryToStew()`.
- Cows/goats can't be milked and mooshrooms can't be milked for stew by the same interaction — `milkCows()` explicitly excludes `MushroomCow`, and `milkMooshrooms()` only targets `MushroomCow`. Both use a `FakePlayer`-mediated `mobInteract` for the mooshroom case so vanilla bowl→stew logic (including Suspicious Stew from flowers near the mooshroom) applies unmodified.
- `BuildingCowboy.canEat()` blocks the worker from eating Wheat — it's reserved as the breeding item, not colony food.
