### Function
The Restaurant/Cook building — serves food to hungry citizens and can smelt raw food into its basic cooked form via a furnace, but **does not craft proper dishes**; those come from the [Chef's Kitchen](Chef's%20Kitchen.md). Notably, `BuildingCook` has **no crafting module at all** (confirmed in [`ModBuildingsInitializer`](../../../minecolonies/src/main/java/com/minecolonies/apiimp/initializer/ModBuildingsInitializer.java) — only `COOK_WORK`, `FURNACE`, `ITEMLIST_FUEL`, `RESTAURANT_MENU`, `STATS_MODULE`), so its "cooking" is limited to whatever a furnace can do, using the `NoPrivateCrafterWorkerModule` assignment type rather than a real `CraftingWorkerBuildingModule`.

- Building: [`BuildingCook`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingCook.java)
- Menu module: `RESTAURANT_MENU` (`RestaurantMenuModule`, scaled by building level)

### Levels
Max building level: 5. Confirmed exact formulas from [`RestaurantMenuModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/RestaurantMenuModule.java): the menu can hold at most `buildingLevel × STOCK_PER_LEVEL (5)` distinct food items, and the target stock kept in reserve for each menu item is `itemMaxStackSize × buildingLevel` (since the Dining Hall's `RESTAURANT_MENU` module is constructed with `expectedStock = ICommonBuilding::getBuildingLevel`) — both menu breadth and per-item stock scale directly with building level. A colony-wide "Min Order" research effect also lowers the auto-restock trigger threshold from the full target down to a quarter of it, meaning restocking kicks in earlier (more proactively) once researched.

### Research
No building-unlock research found for the Dining Hall.

### Skills
- Primary: Adaptability
- Secondary: Knowledge

(`COOK_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Random sitting-position selection with occupancy checking**: `getNextSittingPosition()` weights its random pick across three schematic tag categories (`sitting`, `sit_in`, `sit_out`), excluding outdoor (`sit_out`) seats entirely while it's raining, and retries up to 3 times to find an unoccupied spot before giving up — logging an error if the blueprint has no seating tags at all.
- **Fuel reservation is need-based, not skill-based**: `buildingRequiresCertainAmountOfItem()` is overridden to keep any item on the `FUEL_LIST` up to a full stack, on top of the standard reservation logic — ensuring the furnace never runs dry regardless of what else the building is holding onto.
- Because there's no crafting module, none of the tag-based recipe-eligibility system documented in the crafting shared doc applies here at all — whatever cooking capability this building has is entirely furnace-mediated (vanilla smelting recipes for raw→cooked food) via the same `FurnaceUserModule`/`ITEMLIST_FUEL` pattern used elsewhere, not a `.Smelting` crafting-module policy.
- **The menu module knows the Dining Hall can cook**: `RestaurantMenuModule` is constructed with a `canCook = true` flag here (the Nether Mine's parallel `NETHERMINER_MENU` module uses `false`) — for each menu item with a vanilla smelting recipe, it also tracks and requests the *raw* ingredient (e.g. raw meat for a cooked-meat menu item) as an alternative to the finished product, splitting requests between the two somewhat randomly. This is what actually lets the "smelts food into its cooked form" function happen: the building keeps both raw and cooked stock in its target reserve, not just the finished dish.
