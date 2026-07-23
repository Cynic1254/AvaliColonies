### Function
The colony's central storage — Couriers store and retrieve everything other workers harvest, craft, or need, in Racks placed throughout the building's schematic.

- Building: [`BuildingWareHouse`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingWareHouse.java), implements `IWareHouse`
- Courier assignment module: `WAREHOUSE_COURIERS` (`CourierAssignmentModule`)
- Options module: `WAREHOUSE_OPTIONS` (`WarehouseModule`) — tracks the storage-upgrade tier
- Request queue module: `WAREHOUSE_REQUEST_QUEUE` (`WarehouseRequestQueueModule`)

### Levels
Max building level: 5. Courier capacity confirmed exact in [`CourierAssignmentModule`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/CourierAssignmentModule.java): `getModuleMax() = buildingLevel × 2` — 2/4/6/8/10 for levels 1–5.

This module also mirrors the Mine/Quarry "adopt, don't hire" pattern documented in the structure-building shared doc: `onColonyTick()` doesn't hire a fresh jobless citizen directly — it scans the colony for any citizen already working as `JobDeliveryman` who isn't yet assigned to *any* warehouse (`findWareHouse() == null`) and assigns them here. The actual Courier hut (`BuildingDeliveryman`, via its own `COURIER_WORK`/`DeliverymanAssignmentModule`) is presumably where the underlying hiring happens, with the Warehouse only adopting couriers that don't yet have a home warehouse — the same two-step hire/adopt split seen with Miner→Quarrier→Quarry.
- **Sorting requires level 3+**: `canSort()` checks `getBuildingLevel() >= BuildingConstants.DEFAULT_REQUIRED_SORT_LEVEL` (3) — a Warehouse below level 3 can't auto-sort its inventory.

### Research
No building-unlock research found for the Warehouse.

### Skills
- Primary: Agility
- Secondary: Adaptability

(`WAREHOUSE_COURIERS` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).)

### Limits
- **Rack capacity is a separate, building-wide upgrade from building level**: `upgradeContainers()` increases every Rack's slot count by a fixed increment, up to `MAX_STORAGE_UPGRADE = 3` tiers total, tracked by `WarehouseModule.getStorageUpgrade()` — triggered manually/via GUI rather than automatically at certain building levels, and newly-placed Racks are immediately brought up to the current upgrade tier (`registerBlockPosition()`).
- **The Warehouse itself is never "gathered from" as a worker inventory** (`canBeGathered()` returns `false`, same as the Town Hall) — it's pure storage infrastructure, not a worker's personal stock.
- **Access is restricted to its own assigned Couriers**: `canAccessWareHouse()` checks the citizen against the `CourierAssignmentModule`'s own assignment list — a colony can have multiple warehouses, and a Courier can only pull from the one(s) they're actually assigned to.
- Provides several of its own request-resolver types (`WarehouseRequestResolver`, `WarehouseConcreteRequestResolver`, `DeliveryRequestResolver`, `PickupRequestResolver`) on top of the standard building resolvers — the Warehouse is a first-class participant in the request/delivery system, not just a passive container.
