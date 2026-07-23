### Function
Houses a Courier, who fulfills the colony's delivery and pickup requests — moving items between the Warehouse and whichever building requested or needs to offload them. This is the "worker" half of the delivery system; the [Warehouse](Warehouse.md) is the storage/request-queue half.

- Building: [`BuildingDeliveryman`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/workerbuildings/BuildingDeliveryman.java) — deliberately minimal (its own class comment even mislabels it "warehouse building," a leftover copy-paste artifact); almost all of the interesting behavior lives in the AI and job classes.
- AI: [`EntityAIWorkDeliveryman`](../../../minecolonies/src/main/java/com/minecolonies/core/entity/ai/workers/service/EntityAIWorkDeliveryman.java)
- Job: [`JobDeliveryman`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/jobs/JobDeliveryman.java)
- Assignment module: `COURIER_WORK` (`DeliverymanAssignmentModule`), capped at exactly **1 Courier per hut regardless of building level** (`sizeLimit = (b) -> 1`).

### Levels
Max building level: 5. The Courier's own **inventory capacity scales with the hut's level**, confirmed exactly in `EntityAIWorkDeliveryman.cannotHoldMoreItems()`: max stacks carried = `2^(level−1) + 1`, except at the building's max level (5), where the cap is removed entirely and the Courier can carry as much as their inventory otherwise allows:
1. 2 stacks
2. 3 stacks
3. 5 stacks
4. 9 stacks
5. Unlimited

### Research
No building-unlock research found for the Courier's Hut — consistent with it being an early/starter building alongside the Warehouse.

### Skills
- Primary: Agility
- Secondary: Adaptability

(`COURIER_WORK` in [`BuildingModules`](../../../minecolonies/src/main/java/com/minecolonies/core/colony/buildings/modules/BuildingModules.java).) Primary skill (Agility) also drives a direct movement-speed bonus: `JobDeliveryman.onLevelUp()` adds a `+0.003 × Agility level` additive modifier to the citizen's movement speed attribute, removed again if the Courier is unassigned (`DeliverymanAssignmentModule.onRemoval()`).

### Limits
- **Hired here, but "adopted" by a Warehouse — the same two-step pattern as the Miner/Quarrier/Quarry relationship**: a Courier is hired at the Courier's Hut via `COURIER_WORK`, but `JobDeliveryman.findWareHouse()` only considers them properly staffed once some Warehouse's own `CourierAssignmentModule` has independently claimed them (that module scans the colony for `JobDeliveryman` citizens without a warehouse and assigns them — see the Warehouse doc). Until a Warehouse adopts them, the AI reports "no warehouse" and does nothing (`checkIfExecute()` blocks with a chat interaction).
- **Handles two distinct request types**: `Delivery` (carry items from the Warehouse — or another building's excess stock — to whichever building requested them) and `Pickup` (collect a building's unwanted/excess stock and bring it back to the Warehouse to free up local storage). Both are served from the same priority-ordered task queue.
- **Deliveries to the same destination are batched**: `prepareDelivery()` gathers multiple pending requests bound for the same target building into a single trip, capped at `1 + (secondarySkillLevel / 5)` parallel deliveries — higher Adaptability lets a Courier bundle more requests together before setting off, reducing trips.
- **Task ordering is a genuine scored priority queue, not FIFO**: `getScoreForDelivery()`/`getScoreOfRequestComparedTo()` score every candidate task by a "closeness" factor (how well a new request's source/destination line up with the Courier's current position or existing queued route), each request's own priority (which itself increases the longer a request waits, via `incrementPriorityDueToAging()`), and a bonus for alternating between pickups and deliveries rather than doing several of the same type in a row — lower score wins.
- **`canEat()`** blocks eating whatever item stack is the target of the Courier's current active delivery task — the standard "don't eat what you're carrying for someone else" pattern seen on several other buildings.
- **Higher saturation cost**: `getSaturationFactor()` returns 1.2, the same elevated hunger rate as the Quarrier — deliveries are hungry work.
- No exposed building-specific settings.
