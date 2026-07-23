### Function
Harvests honeycombs and/or honey bottles from bee hives/nests found in its own schematic, breeding bees using whatever flowers the colony has available rather than a single fixed breeding item. Uniquely among herder-adjacent buildings, the Beekeeper **never butchers** its animals.

- Building: [`BuildingBeekeeper`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingBeekeeper.java)
- Herding module: `BuildingBeekeeper.HerdingModule extends AnimalHerdingModule` (compatible animal: `Bee`) — see **[_Shared - Animal Herding](_Shared%20-%20Animal%20Herding.md)** for the general herder framework this reuses, though the Beekeeper deliberately overrides its most consequential piece (butchering).

### Levels
Max building level: 5. Confirmed exact hive cap formula, `getMaximumHives() = 2^(level − 1)`:
1. 1 hive
2. 2 hives
3. 4 hives
4. 8 hives
5. 16 hives

### Research
No building-unlock research found for the Apiary.

### Skills
- Primary: Dexterity
- Secondary: Adaptability

(`BEEKEEPER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Hive positions are schematic-detected** (`hives`, a `Set<BlockPos>` populated as the building registers its structure) — how many hives actually exist is a schematic-authoring fact; `getMaximumHives()` above is the *cap* on how many the building is allowed to have credited to it at a given level. Confirmed in `BuildingModules`: `BEEKEEPER_TOOL` has **no server-side module class at all** (`null` producer, only a client-side `ToolModuleView` wrapping the Beekeeper's Scepter item) — the same GUI-only pattern used by `MINER_GUARD_ASSIGN`/`WORKORDER_VIEW`. So the cap isn't enforced by a building module; it must be checked directly wherever the scepter's use is handled (presumably the item's own use-code or the Beekeeper AI), by comparing against `getMaximumHives()` before allowing a new hive to be registered.
- **Breeding item is dynamic, not fixed**: unlike every other `AnimalHerdingModule` user, `HerdingModule.getBreedingItems()` doesn't return one hardcoded item — it returns *every* flower the colony's compatibility manager currently recognizes (`getImmutableFlowers()`), each usable at quantity 2. This means the Beekeeper's breeding-item request scales with however many distinct flower types exist in the loaded game, not a single fixed item like Wheat or Carrots.
- **Breeding cap follows the shared herder soft-cap formula**: `chanceToButcher()`-driven breeding throttling still applies at `buildingLevel × 2` bees (the default `ANIMAL_MULTIPLIER` from the shared herder AI) — one hive's worth of bees per level.
- **Never butchers, by design** — the `HerdingModule`'s own source comment notes bees "never over-breed and kill" because bees drop no useful loot on death anyway; `getRecipesForDisplayPurposesOnly()` is overridden to skip the shared class's default kill-based JEI recipe entirely, showing only the two real harvest actions (Honeycomb via shears, Honey Bottle via glass bottle).
- **Harvest type is player-selectable**: the `MODE` setting (`BeekeeperCollectionSetting`) chooses Honeycomb-only, Honey-only, or Both — `getHarvestTypes()`.
- **`canEat()`** blocks eating Honey Bottles specifically, the standard reserved-product pattern.
- **Reserved inventory** (`keepX`): 1 Shears, 4 Glass Bottles, and a full stack of anything tagged `#minecraft:flowers` (breeding stock).
