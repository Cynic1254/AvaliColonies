# core.entity (cont. 2)

75 classes, 83 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractAISkeleton~J~ {
  # AbstractAISkeleton(J) 
  # onException(RuntimeException) void
  # registerTargets(TickingTransition~IAIState~[]) void
  + tick() void
  + resetAI() void
  + canBeInterrupted() boolean
  + registerGroupTarget(List~IAIState~, TickingTransition~IAIState~) void
  + registerTarget(TickingTransition~IAIState~) void
  + onRemoval() void
   ITickRateStateMachine~IAIState~ stateAI
   IAIState state
   int tickRate
   int currentDelay
}
class AbstractEntityAIBasic~J, B~ {
  # AbstractEntityAIBasic(J) 
  - int exceptionTimer
  - int delay
  # walkToBuilding(IBuilding) boolean
  - dumpOneMoreSlot() boolean
  + incrementActionsDoneAndDecSaturation() void
  + checkIfRequestForItemExistOrCreateAsync(ItemStack, int, int) boolean
  # walkToTaggedWorkPos() boolean
  + checkIfRequestForItemExistOrCreate(ItemStack, int, int, boolean, boolean) boolean
  # resetActionsDone() void
  # checkForToolOrWeapon(EquipmentTypeEntry, int) boolean
  + canGoIdle() boolean
  # walkToBuilding() boolean
  - lookForRequests() IAIState
  - dumpInventory() IAIState
  + retrieveToolInTileEntity(BlockEntity, EquipmentTypeEntry, int, int) boolean
  + checkIfRequestForItemExistOrCreateAsync(ItemStack[]) boolean
  # walkToUnSafePos(BlockPos) boolean
  - getPositionInDirection(Direction, int, BlockPos) BlockPos
  # checkForToolOrWeaponAsync(EquipmentTypeEntry, int, int) void
  - checkForNeededTool(EquipmentTypeEntry, int) boolean
  + checkIfRequestForItemExistOrCreate(Collection~ItemStack~) boolean
  - bePaused() IAIState?
  # hasNotDelayed(int) boolean
  - updateVisualState() IAIState?
  # walkWithProxy(BlockPos) boolean
  + afterRequestPickUp() IAIState
  + checkAndTransferFromHut(ItemStack?) boolean
  # incrementActionsDone(int) void
  + retrieveToolInHut(EquipmentTypeEntry, int) boolean
  - restart() IAIState
  # cancelAsynchRequestForArmor(EquipmentTypeEntry) void
  - cleanAsync() boolean
  + getWorkingPosition(BlockPos) BlockPos
  # waitForRequests() IAIState
  + getTotalRequiredAmount(ItemStack) int
  - waitingForSomething() boolean
  - workOnBlock(BlockPos?, int) void
  - updateToolFlag(EquipmentTypeEntry, int) void
  - clearWorkTarget() void
  # getMostEfficientTool(BlockState, BlockPos) int
  + checkIfRequestForItemExistOrCreateAsync(Collection~ItemStack~) boolean
  + afterDump() IAIState
  # walkToWorkPos(BlockPos) boolean
  # walkToSafePos(BlockPos) boolean
  # inventoryNeedsDump() boolean
  - initSafetyChecks() IAIState?
  # itemsNiceToHave() List~ItemStorage~
  - shouldRestart() boolean
  # wantInventoryDumped() boolean
  + checkIfRequestForTagExistOrCreateAsync(TagKey~Item~, int) boolean
  # updateRenderMetaData() void
  + holdEfficientTool(BlockState, BlockPos) boolean
  - hasOpenToolRequest(EquipmentTypeEntry) boolean
  # getInvertedEffectiveSkillLevel(int) int
  + checkIfRequestForItemExistOrCreate(ItemStack) boolean
  + checkIfRequestForItemExistOrCreateAsync(ItemStack, int, int, boolean) boolean
  # walkToUnSafePos(BlockPos, int) boolean
  + incrementActionsDone() void
  # onException(RuntimeException) void
  - tryTransferFromPosToWorkerIfNeeded(BlockPos, Tuple~Predicate~ItemStack~, Integer~) boolean
  # getEffectiveSkillLevel(int) int
  + checkForToolOrWeapon(EquipmentTypeEntry) boolean
  - clearActionsDone() void
  + checkIfRequestForItemExistOrCreate(IDeliverable) boolean
  - getOwnBuilding(Class~B~) B
  - requestTool(BlockState, BlockPos) void
  + checkIfRequestForItemExistOrCreateAsync(ItemStack) boolean
  + checkIfRequestForItemExistOrCreate(ItemStack[]) boolean
  # checkIfNeedsItem() boolean
  + checkIfRequestForItemExistOrCreate(ItemStack, int, int) boolean
  + takeItemStackFromProvider(ICapabilityProvider, int) void
  # walkWithProxy(BlockPos, int) boolean
  + getWorkingPosition(int, BlockPos, int) BlockPos
   int primarySkillLevel
   int delay
   int secondarySkillLevel
   InventoryCitizen inventory
   Class~B~ expectedBuildingClass
   boolean startingPaused
   FakePlayer fakePlayer
   int exceptionTimer
   boolean afterDumpPickupAllowed
   int actionsDoneUntilDumping
   IAIState stateAfterPickUp
   boolean paused
   WorkerBuildingModule moduleForJob
   IAIState neededItem
   IBuilding buildingToDump
}
class AbstractEntityAICrafting~J, B~ {
  + AbstractEntityAICrafting(J) 
  + hitBlockWithToolInHand(BlockPos?) void
  + resetValues() void
  # getLootContext(boolean) LootParams
  # decide() IAIState
  # checkForItems(IRecipeStorage) IAIState
  + afterDump() IAIState
  # getExtendedCount(ItemStack) int
  + executeCraftingAction(int) IAIState
  + finalizeCraftingTask() IAIState
  - queryItems() IAIState
  # idle() IAIState
  + hasWorkToDo() boolean
  # craft() IAIState
  # recordCraftingBuildingStats(IRequest~?~, IRecipeStorage) void
  # updateRenderMetaData() void
   boolean afterDumpPickupAllowed
   int actionsDoneUntilDumping
   IAIState nextCraftingState
   IAIState stateAfterPickUp
   LootParams lootContext
   IAIState recipe
   int actionRewardForCraftingSuccess
   int requiredProgressForMakingRawMaterial
   String craftingStatName
}
class AbstractEntityAIFight~J, B~ {
  + AbstractEntityAIFight(J) 
  + equipInventoryArmor() void
  # startWorkingAtOwnBuilding() IAIState
  + afterRequestPickUp() IAIState
  + afterDump() IAIState
  # atBuildingActions() void
  + cleanVisibleSlots() void
  - prepare() IAIState
   IAIState stateAfterPickUp
}
class AbstractEntityAIGuard~J, B~ {
  + AbstractEntityAIGuard(J) 
  # BlockPos currentPatrolPoint
  # decide() IAIState
  # startWorkingAtOwnBuilding() IAIState
  # sleep() IAIState
  - shouldFlee() boolean
  + hasTool() boolean
  - guard() IAIState
  - inCombat() IAIState?
  + canBeInterrupted() boolean
  - shouldSleep() boolean
  + guardMovement() void
  - onCombatEnter() void
  + startHelpCitizen(LivingEntity) void
  - wakeUpGuard() IAIState
  - follow() IAIState?
  + canHelp(BlockPos) boolean
  + patrolMine() IAIState
  + isWithinPersecutionDistance(BlockPos, double) boolean
  - regen() IAIState
  - flee() IAIState
  # stopSleeping() void
  # randomPatrolPoint() BlockPos
  - rally(ILocation) IAIState?
  - onCombatLeave() void
  + patrol() IAIState
  - sleepParticles() IAIState?
  + isAttackableTarget(AbstractEntityCitizen, LivingEntity) boolean
   int actionsDoneUntilDumping
   EntityCitizen wakeCitizen
   BlockPos currentPatrolPoint
   Class~B~ expectedBuildingClass
   BlockPos taskReferencePoint
   BlockPos nextPatrolTarget
   int persecutionDistance
}
class AbstractEntityAIHerder~J, B~ {
  + AbstractEntityAIHerder(J) 
  # int BUTCHERING_ATTACK_DAMAGE
  # feedAnimal() IAIState
  - prepareForHerding() IAIState
  # butcherAnimals() IAIState
  - pickupItems() IAIState
  + searchForItemsInArea() List~ItemEntity~
  + getRequestBreedingItems(AnimalHerdingModule) List~ItemStorage~
  + chanceToButcher(List~Animal~) double
  # butcherAnimal(Animal?) void
  - breedTwoAnimals() boolean
  # isFeedAble(Animal) boolean
  # canBreedChildren() boolean
  + equipItem(InteractionHand, List~ItemStorage~) boolean
  + searchForAnimals(Predicate~Animal~) List~Animal~
  + getItemSlot(Item) int
  # breedAnimals() IAIState
  - startWorkingAtOwnBuilding() IAIState
  - canMate(Animal, Animal) boolean
  # itemsNiceToHave() List~ItemStorage~
  - getCenterOfHerd(List~Animal~) BlockPos
  # butcherSwing(FakePlayer, Animal) void
  + decideWhatToDo() IAIState
  - getToolSlot(EquipmentTypeEntry) int
  - ensureLootingI(ItemStack) void
  + equipTool(InteractionHand, EquipmentTypeEntry) boolean
  + walkingToAnimal(Animal) boolean
  # isBreedAble(Animal) boolean
   List~ItemStorage~ extraItemsNeeded
   int maxAnimalMultiplier
   double BUTCHERING_ATTACK_DAMAGE
   int actionsDoneUntilDumping
   List~EquipmentTypeEntry~ extraToolsNeeded
}
class AbstractEntityAIInteract~J, B~ {
  + AbstractEntityAIInteract(J) 
  # increaseBlockDrops(List~ItemStack~) List~ItemStack~
  # triggerMinedBlock(BlockPos, BlockState) void
  # mineBlock(BlockPos, BlockPos?) boolean
  + shouldSilkTouchBlock(BlockState) boolean
  + searchForItems(AABB) void
  # mineBlock(BlockPos) boolean
  + resetGatheringItems() void
  + fillItemsList() void
  + gatherItems() void
  + onBlockDropReception(List~ItemStack~) void
  - calculateWorkerMiningDelay(BlockState, BlockPos) int
  - checkMiningLocation(BlockPos, BlockPos?) boolean
  + getBlockMiningTime(BlockState, BlockPos) int
  # isItemWorthPickingUp(ItemStack) boolean
  # mineBlock(BlockPos, BlockPos?, boolean, boolean, Runnable) boolean
   int breakSpeedLevel
   BlockPos andRemoveClosestItemPosition
   List~BlockPos~? itemsForPickUp
}
class AbstractEntityAIRequestSmelter~J, B~ {
  + AbstractEntityAIRequestSmelter(J) 
  - retrieveUnrelatedProductFromFurnace() IAIState
  - fillUpFurnace() IAIState
  - countOfBurningFurnaces() int
  # recordSmeltingBuildingStats(Component, int) void
  - addFuelToFurnace() IAIState
  - extractFromFurnaceSlot(FurnaceBlockEntity, int) boolean
  + executeCraftingAction(int) IAIState
  - checkForLeftOvers() boolean
  - retrieveProductFromFurnace() IAIState
  - accelerateFurnaces() boolean
  - checkFurnaceFuel() boolean
  - isCorrectFuel(List~ItemStack~) Predicate~ItemStack~
  - areFurnacesLoaded() boolean
  # getExtendedCount(ItemStack) int
   String smeltingStatName
   BlockPos? furnaceToRetrieveOutputFrom
   List~ItemStack~ activePossibleFuels
   BlockPos? furnaceWithoutFuel
   BlockPos? emptyFurnaceWithFuel
   BlockPos? furnaceToRetrieveUnrelatedInputFrom
   List~ItemStack~ allowedFuel
   int maxUsableFurnaces
}
class AbstractEntityAISkill~J, B~ {
  # AbstractEntityAISkill(J) 
}
class AbstractEntityAIStructure~J, B~ {
  # AbstractEntityAIStructure(J) 
  # Tuple~StructurePlacer, BuildingStructureHandler~J, B~~ structurePlacer
  + getWorkingPosition(BlockPos) BlockPos
  # executeSpecificCompleteActions() void
  # completeBuild() IAIState
  + loadStructure(IBuilderWorkOrder, BlockPos, boolean) void
  # structureStep() IAIState
  + handleSpecificCancelActions() void
  + doMining() IAIState
  + afterDump() IAIState
  + pickUpMaterial() IAIState
  + afterStructureLoading() IAIState
  + walkToConstructionSite(BlockPos) boolean
  + hasListOfResInInvOrRequest(AbstractEntityAIStructure~J, B~, List~ItemStack~, boolean) ItemCheckResult
  # startWorkingAtOwnBuilding() IAIState
  # startBuilding() IAIState
  + loadRequirements() IAIState
  + registerBlockAsNeeded(ItemStack) void
  + getSolidSubstitution(BlockPos) BlockState
  + afterRequestPickUp() IAIState
  + checkForExtraBuildingActions() void
  # skipClearing(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  + requestMaterials() boolean
  + storeProgressPos(BlockPos, BuildingProgressStage) void
  # skipBuilding(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  # checkIfCanceled() boolean
  + reduceNeededResources(ItemStack) void
  - skipDecorate(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  - skipRemoval(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  + resetCurrentStructure() void
  # isDecoItem(Block) boolean
  + shallReplaceSolidSubstitutionBlock(Block, BlockState) boolean
  # goToNextStage(StructurePhasePlacementResult) boolean
  + isBlockFree(BlockState?) boolean
  + getTotalAmount(ItemStack?) ItemStack?
   BlockPos posToWorkAt
   BlockPos currentWorkingPosition
   IAIState stateAfterPickUp
   AbstractEntityCitizen worker
   boolean alreadyCleared
   BuildingStructureHandler~J, B~ structurePlacer
   boolean thereAStructureToBuild
   BlockPos? currentBuildingPosition
   Tuple~BlockPos, BuildingProgressStage~ progressPos
   int placeSpeedLevel
}
class AbstractEntityAIStructureWithWorkOrder~J, B~ {
  + AbstractEntityAIStructureWithWorkOrder(J) 
  + executeSpecificCompleteActions() void
  # checkIfNeedsItem() boolean
  + registerBlockAsNeeded(ItemStack) void
  + loadRequirements() IAIState
  - requestMaterialsState() void
  + requestMaterials() boolean
  + reduceNeededResources(ItemStack) void
  + handleSpecificCancelActions() void
  + getTotalRequiredAmount(ItemStack) int
  + storeProgressPos(BlockPos, BuildingProgressStage) void
  # sendCompletionMessage(IWorkOrder) void
  # checkIfCanceled() boolean
  + getTotalAmount(ItemStack?) ItemStack?
  - loadStructure() void
   Tuple~BlockPos, BuildingProgressStage~ progressPos
   boolean alreadyCleared
}
class AbstractEntityAITraining~J, B~ {
  + AbstractEntityAITraining(J) 
  - pathToTarget() IAIState
  - wander() IAIState
  # reduceAttackDelay() void
  + decide() IAIState
   boolean setup
}
class AbstractEntityAIUsesFurnace~J, B~ {
  # AbstractEntityAIUsesFurnace(J) 
  + requestSmeltable() void
  # checkForAdditionalJobs() IAIState
  # reachedMaxToKeep() boolean
  - accelerateFurnaces() IAIState?
  + startWorking() IAIState
  # checkForImportantJobs() IAIState
  - retrieveSmeltableFromFurnace() IAIState
  - retrieveUsedFuel() IAIState
  - extractFuelFromFurnace(FurnaceBlockEntity) void
  - fillUpFurnace() IAIState
  # isSmeltable(ItemStack) boolean
  - checkIfAbleToSmelt(int, int) IAIState
  # extractFromFurnace(FurnaceBlockEntity) void
   int actionsDoneUntilDumping
   BlockPos positionOfOvenToRetrieveFuelFrom
   IRequestable smeltAbleClass
   List~ItemStack~ allowedFuel
   BlockPos positionOfOvenToRetrieveFrom
}
class BehaviourStateGroup {
  + BehaviourStateGroup() 
}
class BuildingProgressStage {
<<enumeration>>
  + BuildingProgressStage() 
  + valueOf(String) BuildingProgressStage
  + values() BuildingProgressStage[]
}
class BuildingStructureHandler~J, B~ {
  + BuildingStructureHandler(Level, IWorkOrder, AbstractEntityAIStructure~J, B~, BuildingProgressStage[]) 
  - int stage
  + replaceWithSolidBlock(BlockState) boolean
  + getSolidBlockForPos(BlockPos, Function~BlockPos, BlockState~?) BlockState
  + triggerEntitySuccess(BlockPos, List~ItemStack~, boolean) void
  + hasRequiredItems(List~ItemStack~) boolean
  + shouldBlocksBeConsideredEqual(BlockState, BlockState) boolean
  + getSolidBlockForPos(BlockPos) BlockState
  + nextStage() boolean
  + allowReplace() boolean
  + isStackFree(ItemStack?) boolean
  - setupBuilding() void
  + consume(List~ItemStack~) void
  + prePlacementLogic(BlockPos, BlockState, List~ItemStack~) void
  + triggerSuccess(BlockPos, List~ItemStack~, boolean) void
  + fancyPlacement() boolean
   ItemStack heldItem
   int stepsPerCall
   int maxBlocksCheckedPerCall
   BuildingProgressStage? stage
   IItemHandler? inventory
   boolean creative
}
class CavalryCombatAI {
  + CavalryCombatAI(EntityCitizen, ITickRateStateMachine~?~, AbstractEntityAIGuard~?, ?~) 
   VisibleCitizenStatus combatStatus
   double attackDamage
   double attackDistance
   EquipmentTypeEntry weaponType
}
class CitizenAI {
  + CitizenAI(EntityCitizen) 
  - registerWorkAI() void
  - calculateNextState() IState
  - decideAiTask() IState?
  + shouldEat() boolean
  - shouldWorkWhileRaining() boolean
}
class ConstructionTapeHelper {
  - ConstructionTapeHelper() 
  + removeConstructionTape(Tuple~BlockPos, BlockPos~, Level) void
  + firstValidPosition(BlockPos, Level, int) BlockPos?
  + placeConstructionTape(Tuple~BlockPos, BlockPos~, IColony) void
  + placeConstructionTape(IWorkOrder, Level, IColony) void
  + placeConstructionTape(IBuilding) void
  + removeConstructionTape(IWorkOrder, Level) void
  + removeTapeIfNecessary(Level, BlockPos, Block, int, int) void
}
class DruidCombatAI {
  + DruidCombatAI(EntityCitizen, ITickRateStateMachine, AbstractEntityAIGuard) 
  # searchNearbyTarget() boolean
  # skipSearch(LivingEntity) boolean
  # doAttack(LivingEntity) void
  - wasAffectedByDruid(LivingEntity) boolean
  # moveInAttackPosition(LivingEntity) PathResult
  # onTargetDied(LivingEntity) void
  # isAttackableTarget(LivingEntity) boolean
  # isWithinPersecutionDistance(LivingEntity) boolean
   int YSearchRange
   double attackDistance
   double combatMovementSpeed
   int attackDelay
}
class EntityAIArcherTraining {
  + EntityAIArcherTraining(JobArcherTraining) 
  - findShootingStandPosition() IAIState
  - checkShot() IAIState
  # shoot() IAIState
  - selectTarget() IAIState
   Class~BuildingArchery~ expectedBuildingClass
   boolean setup
}
class EntityAICavalry {
  + EntityAICavalry(JobCavalry) 
  # findStable() IAIState
  - isAvailableFor(CavalryHorseEntity, UUID?) boolean
  + patrol() IAIState
  # findNearestHorse() CavalryHorseEntity?
  # decide() IAIState
  # itemsNiceToHave() List~ItemStorage~
  - validateMountTarget(CavalryHorseEntity) boolean
  - isAvailable(CavalryHorseEntity) boolean
  # sleep() IAIState
  # findMount() IAIState
  + canHelp(BlockPos) boolean
   BlockPos stableRestCenter
}
class EntityAICombatTraining {
  + EntityAICombatTraining(JobCombatTraining) 
  - decideOnTrainingType() IAIState
  + decide() IAIState
  - findDummyPartner() IAIState
  - attackDummy() IAIState
  - trainWithPartner() IAIState
  - findTrainingPartner() IAIState
  - attack() IAIState
   Class~BuildingCombatAcademy~ expectedBuildingClass
   boolean setup
}
class EntityAIConcreteMixer {
  + EntityAIConcreteMixer(JobConcreteMixer) 
  + executeCraftingAction(int) IAIState
  - placePowder() IAIState
  - harvestConcrete() IAIState
  # getExtendedCount(ItemStack) int
  - performMixingWork() IAIState
   int slotWithPowder
   Class~BuildingConcreteMixer~ expectedBuildingClass
   int actionsDoneUntilDumping
}
class EntityAIDruid {
  + EntityAIDruid(JobDruid) 
  # updateRenderMetaData() void
  + guardMovement() void
  # atBuildingActions() void
}
class EntityAIKnight {
  + EntityAIKnight(JobKnight) 
  # itemsNiceToHave() List~ItemStorage~
}
class EntityAIQuarrier {
  + EntityAIQuarrier(JobQuarrier) 
  + loadRequirements() IAIState
  # skipClearing(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  + getSolidSubstitution(BlockPos) BlockState
  + onBlockDropReception(List~ItemStack~) void
  + requestMaterials() boolean
  # startWorkingAtOwnBuilding() IAIState
  + executeSpecificCompleteActions() void
  - setBlockFromInventory(BlockPos, Block) void
  - getShaftPath(IBuilding) Tuple~String, String~
  + afterStructureLoading() IAIState
  # skipBuilding(BlueprintPositionInfo, BlockPos, IStructureHandler) boolean
  + doMining() IAIState
  # triggerMinedBlock(BlockPos, BlockState) void
  # checkIfCanceled() boolean
  # goToNextStage(StructurePhasePlacementResult) boolean
  # updateRenderMetaData() void
  + getTotalAmount(ItemStack) ItemStack?
  + loadStructure(IBuilderWorkOrder, BlockPos, boolean) void
  + walkToConstructionSite(BlockPos) boolean
  + shallReplaceSolidSubstitutionBlock(Block, BlockState) boolean
  # structureStep() IAIState
   BlockPos posToWorkAt
   int actionsDoneUntilDumping
   BuildingStructureHandler~JobQuarrier, BuildingMiner~ structurePlacer
   IBuilding buildingToDump
   Class~BuildingMiner~ expectedBuildingClass
   Block mainFillBlock
   int breakSpeedLevel
   int placeSpeedLevel
}
class EntityAIRanger {
  + EntityAIRanger(JobRanger) 
  + guardMovement() void
  # updateRenderMetaData() void
  # atBuildingActions() void
}
class EntityAIStructureBuilder {
  + EntityAIStructureBuilder(JobBuilder) 
  - killMobs() void
  + getBlockMiningTime(BlockState, BlockPos) int
  # sendCompletionMessage(IWorkOrder) void
  # startWorkingAtOwnBuilding() IAIState
  + checkForExtraBuildingActions() void
  + shallReplaceSolidSubstitutionBlock(Block, BlockState) boolean
  + afterRequestPickUp() IAIState
  + afterDump() IAIState
  + canGoIdle() boolean
  # mineBlock(BlockPos, BlockPos) boolean
  - checkForWorkOrder() boolean
  + walkToConstructionSite(BlockPos) boolean
   boolean afterDumpPickupAllowed
   Class~BuildingBuilder~ expectedBuildingClass
   int actionsDoneUntilDumping
   int breakSpeedLevel
   int placeSpeedLevel
   BuildingStructureHandler~JobBuilder, BuildingBuilder~ structurePlacer
}
class EntityAIStructureMiner {
  + EntityAIStructureMiner(JobMiner) 
  - ladderDamaged() boolean
  # updateRenderMetaData() void
  + walkToConstructionSite(BlockPos) boolean
  - advanceLadder(IAIState) IAIState
  - searchANodeToMine(MinerLevel) IAIState
  + onBlockDropReception(List~ItemStack~) void
  - repairLadder() IAIState
  - setBlockFromInventory(BlockPos, Block) void
  - executeStructurePlacement(MineNode, BlockPos, int) IAIState
  - secureBlock(BlockPos, BlockPos) boolean
  - goToLadder() IAIState
  + shouldSilkTouchBlock(BlockState) boolean
  + doMining() IAIState
  - getBlock(BlockPos) Block
  + executeSpecificCompleteActions() void
  - setBlockFromInventory(BlockPos, Block, BlockState) void
  - walkToLadder() boolean
  - getBlockState(BlockPos) BlockState
  - executeNodeMining() IAIState
  # checkIfCanceled() boolean
  + getTotalAmount(ItemStack) ItemStack?
  - getSurroundingOreOrDefault(BlockPos) BlockPos
  # startWorkingAtOwnBuilding() IAIState
  + getWorkingPosition(BlockPos) BlockPos
  - checkMineShaft() IAIState
  # triggerMinedBlock(BlockPos, BlockState) void
  + afterStructureLoading() IAIState
  - doShaftBuilding() IAIState
  - doShaftMining() IAIState
  + shallReplaceSolidSubstitutionBlock(Block, BlockState) boolean
  - getNodeMiningPosition(BlockPos) BlockPos
   Block ladderBackFillBlock
   int actionsDoneUntilDumping
   BlockPos? nextBlockInShaftToMine
   Class~BuildingMiner~ expectedBuildingClass
   Block mainFillBlock
   int breakSpeedLevel
   int placeSpeedLevel
}
class EntityAIStudy {
  + EntityAIStudy(JobStudent) 
  - startWorkingAtOwnBuilding() IAIState
  # updateRenderMetaData() void
  - study() IAIState
   Class~BuildingLibrary~ expectedBuildingClass
}
class EntityAIWorkAlchemist {
  + EntityAIWorkAlchemist(JobAlchemist) 
  - harvestNetherWart() IAIState
  - retrieveUsedFuel() IAIState
  - countOfBubblingBrewingStands() int
  - checkIfAbleToSmelt() IAIState
  - retrieveBrewableFromBrewingStand() IAIState
  - harvestMistleToe() IAIState
  - getNetherwartDrops(BlockPos) List~ItemStack~
  - addFuelToBrewingStand() IAIState
  # decide() IAIState
  - fillUpBrewingStand() IAIState
  - extractFromBrewingStandSlot(BrewingStandBlockEntity, int) void
  + hasWorkToDo() boolean
  # getExtendedCount(ItemStack) int
  # craft() IAIState
  # checkForItems(IRecipeStorage) IAIState
  - checkBrewingStandFuel() IAIState
  - accelerateBrewingStand() boolean
   Class~BuildingAlchemist~ expectedBuildingClass
   IAIState recipe
   boolean fuelNeeded
   int maxUsableBrewingStands
   BlockPos? positionOfBrewingStandToRetrieveFrom
}
class EntityAIWorkBaker {
  + EntityAIWorkBaker(JobBaker) 
  # craft() IAIState
   String smeltingStatName
   boolean afterDumpPickupAllowed
   Class~BuildingBaker~ expectedBuildingClass
   AbstractEntityCitizen? citizen
}
class EntityAIWorkBeekeeper {
  + EntityAIWorkBeekeeper(JobBeekeeper) 
  - harvestHoney() IAIState
  - breedTwoAnimals(Animal, Animal) void
  - getToolSlot(EquipmentTypeEntry) int
  - prepareForHerding() IAIState
  + walkingToAnimal(Animal) boolean
  + equipItem(InteractionHand, ItemStack) boolean
  + equipBreedItem(InteractionHand) boolean
  + searchForAnimals(Level, BuildingBeekeeper) List~Bee~
  - hasMaxAnimals(List~Bee~) boolean
  + equipTool(InteractionHand, EquipmentTypeEntry) boolean
  + getItemSlot(Item) int
  - breedAnimals() IAIState
  - decideWhatToDo() IAIState
  - startWorkingAtOwnBuilding() IAIState
   int honeyBottlesPerHarvest
   int actionsDoneUntilDumping
   BlockPos? hiveToHarvest
   int beesInHives
   Class~BuildingBeekeeper~ expectedBuildingClass
   int honeycombsPerHarvest
   boolean readyForBreeding
}
class EntityAIWorkBlacksmith {
  + EntityAIWorkBlacksmith(JobBlacksmith) 
   Class~BuildingBlacksmith~ expectedBuildingClass
}
class EntityAIWorkChef {
  + EntityAIWorkChef(JobChef) 
   String smeltingStatName
   Class~BuildingKitchen~ expectedBuildingClass
   String craftingStatName
}
class EntityAIWorkChickenHerder {
  + EntityAIWorkChickenHerder(JobChickenHerder) 
  # breedAnimals() IAIState
  # butcherAnimal(Animal?) void
  # butcherAnimals() IAIState
   Class~BuildingChickenHerder~ expectedBuildingClass
}
class EntityAIWorkComposter {
  + EntityAIWorkComposter(JobComposter) 
  - fillBarrels() IAIState
  - complain() void
  - harvestBarrels() IAIState
  - getLootMultiplier(RandomSource) double
  - decideWhatToDo() IAIState
  - accelerateBarrels() IAIState?
   Class~BuildingComposter~ expectedBuildingClass
   int actionsDoneUntilDumping
   IAIState materials
}
class EntityAIWorkCook {
  + EntityAIWorkCook(JobCook) 
  # extractFromFurnace(FurnaceBlockEntity) void
  # isSmeltable(ItemStack) boolean
  - canEat(ItemStack, AbstractEntityCitizen) boolean
  - serveFoodToPlayer() IAIState
  - shouldBeFed(AbstractEntityCitizen) boolean
  - serveFoodToCitizen() IAIState
  + requestSmeltable() void
  # reachedMaxToKeep() boolean
  + startWorking() IAIState
  # checkForImportantJobs() IAIState
   IRequestable smeltAbleClass
   Class~BuildingCook~ expectedBuildingClass
   int actionsDoneUntilDumping
}
class EntityAIWorkCowboy {
  + EntityAIWorkCowboy(JobCowboy) 
  # updateRenderMetaData() void
  + decideWhatToDo() IAIState
  # breedAnimals() IAIState
  - milkMooshrooms() IAIState
  - milkCows() IAIState
  # butcherAnimals() IAIState
   List~ItemStorage~ extraItemsNeeded
   Class~BuildingCowboy~ expectedBuildingClass
   double butcheringAttackDamage
}
class EntityAIWorkCrusher {
  + EntityAIWorkCrusher(JobCrusher) 
  + hasWorkToDo() boolean
  # crush() IAIState
  # craft() IAIState
  # decide() IAIState
   Class~BuildingCrusher~ expectedBuildingClass
}
class EntityAIWorkDeliveryman {
  + EntityAIWorkDeliveryman(JobDeliveryman) 
  - pickup() IAIState
  - dump() IAIState
  - prepareDelivery() IAIState
  - decide() IAIState
  + workerRequiresItem(IBuilding, ItemStack, List~ItemStorage~) int
  - checkIfExecute() boolean
  - deliver() IAIState
  # updateRenderMetaData() void
  + gatherIfInTileEntity(BlockEntity, ItemStack) boolean
  - pickupFromBuilding(IBuilding) boolean
  - cannotHoldMoreItems() boolean
   Class~BuildingDeliveryman~ expectedBuildingClass
   IWareHouse? andCheckWareHouse
}
class EntityAIWorkDyer {
  + EntityAIWorkDyer(JobDyer) 
   Class~BuildingDyer~ expectedBuildingClass
}
class EntityAIWorkEnchanter {
  + EntityAIWorkEnchanter(JobEnchanter) 
  + recordEnchantmentStats(List~ItemStack~) void
  - resetDraining() void
  - enchant() IAIState
  - gatherAndDrain() IAIState
  # decide() IAIState
  + hasWorkToDo() boolean
  - getEnchantedBookLevel(ItemStack) int
   Class~BuildingEnchanter~ expectedBuildingClass
   String craftingStatName
   int actionsDoneUntilDumping
}
class EntityAIWorkFarmer {
  + EntityAIWorkFarmer(JobFarmer) 
  # getLargestCell(FarmField) int
  - checkIfShouldExecute(FarmField, Predicate~BlockPos~) boolean
  - createCorrectFarmlandForSeed(ItemStack, BlockPos) void
  - hoeIfAble(BlockPos, FarmField) boolean
  - isCompost(ItemStack) boolean
  + onBlockDropReception(List~ItemStack~) void
  - tryToPlant(FarmField, BlockPos) boolean
  + canGoIdle() boolean
  # updateRenderMetaData() void
  # decide() IAIState
  # nextValidCell(FarmField) BlockPos
  - harvestIfAble(BlockPos) boolean
  # wantInventoryDumped() boolean
  + hasWorkToDo() boolean
  - equipHoe() void
  - findPlantableSurface(BlockPos, FarmField) BlockPos?
  - isRightFarmLandForCrop(FarmField, BlockState) boolean
  - findHoeableSurface(BlockPos, FarmField) BlockPos?
  - getSurfacePos(BlockPos, Integer) BlockPos?
  - prepareForFarming() IAIState
  - getSurfacePos(BlockPos) BlockPos
  # increaseBlockDrops(List~ItemStack~) List~ItemStack~
  - workAtField() IAIState
  - plantCrop(ItemStack, BlockPos) boolean
  - findHarvestableSurface(BlockPos) BlockPos?
  - canGoPlanting(FarmField) IAIState
   int actionsDoneUntilDumping
   int hoeSlot
   int levelDelay
   Class~BuildingFarmer~ expectedBuildingClass
   AbstractEntityCitizen? citizen
   int actionRewardForCraftingSuccess
   int breakSpeedLevel
}
class EntityAIWorkFisherman {
  + EntityAIWorkFisherman(JobFisherman) 
  - equipRod() void
  - generateBonusLoot() void
  - setRandomWater() IAIState
  - playCaughtFishSound() void
  - findNewWater() IAIState
  - testRandomChance() boolean
  - isReadyToFish() IAIState?
  - tryDifferentAngles() IAIState
  - findWater() IAIState
  - hasRodButNotEquipped() boolean
  - throwRod() void
  - walkToWater() boolean
  - throwOrRetrieveHook() IAIState
  - startWorkingAtOwnBuilding() IAIState
  + searchWater(int, double, List~Tuple~BlockPos, BlockPos~~) WaterPathResult
  - retrieveRod() void
  - prepareForFishing() IAIState
  - hasFish() boolean
  - doFishing() IAIState?
  # updateRenderMetaData() void
  - caughtFish() boolean
  - playNeedRodSound() void
   Class~BuildingFisherman~ expectedBuildingClass
   int rodSlot
   int actionsDoneUntilDumping
   AbstractEntityCitizen? citizen
   boolean fishHookStuck
   IAIState toWater
}
class EntityAIWorkFletcher {
  + EntityAIWorkFletcher(JobFletcher) 
   Class~BuildingFletcher~ expectedBuildingClass
}
class EntityAIWorkFlorist {
  + EntityAIWorkFlorist(JobFlorist) 
  - compost() IAIState
  + holdEfficientTool(BlockState, BlockPos) boolean
  # getFlowerDropAtPos(Level, BlockPos) List~String~
  - harvest() IAIState
  - areThereFlowersToGather() BlockPos?
  - decide() IAIState
  # updateRenderMetaData() void
  + getBlockMiningTime(BlockState, BlockPos) int
  - checkOrEquipShears() boolean
   BlockPos? firstNotCompostedLand
   Class~BuildingFlorist~ expectedBuildingClass
   int actionsDoneUntilDumping
}
class EntityAIWorkGlassblower {
  + EntityAIWorkGlassblower(JobGlassblower) 
   Class~BuildingGlassblower~ expectedBuildingClass
}
class EntityAIWorkHealer {
  + EntityAIWorkHealer(JobHealer) 
  - freeCure() IAIState
  - wander() IAIState
  - testRandomCureChance() boolean
  - recordTreatmentStats(EntityCitizen) void
  - curePlayer() IAIState
  - requestCure() IAIState
  - cure() IAIState
  - decide() IAIState
  - hasCureInInventory(Disease, IItemHandler) boolean
   Class~BuildingHospital~ expectedBuildingClass
   IAIState stateAfterPickUp
}
class EntityAIWorkLumberjack {
  + EntityAIWorkLumberjack(JobLumberjack) 
  - plantSapling() void
  - startWorkingAtOwnBuilding() IAIState
  - setNewTree(BuildingLumberjack) IAIState
  - hasLogs() boolean
  - isStackLog(ItemStack?) boolean
  - isCorrectSapling(ItemStack) boolean
  # decide() IAIState
  - chopWood() IAIState
  - chopTree() IAIState
  - plantSapling(BlockPos) boolean
  # isItemWorthPickingUp(ItemStack) boolean
  - findTrees() IAIState
  - gathering2() IAIState
  + walkToTree(BlockPos) boolean
  - placeSaplings(int, ItemStack, Block) void
  - findSaplingSlot() int
  - checkIfStuck() boolean
  + hasWorkToDo() boolean
  + onBlockDropReception(List~ItemStack~) void
  - waitBeforeCheckingAgain() IAIState
  + getWorkingPosition(BlockPos) BlockPos
  - prepareForWoodcutting() IAIState
  - mineIfEqualsBlockTag(List~BlockPos~, TagKey~Block~) boolean
  - gathering() IAIState
  # updateRenderMetaData() void
  - tryUnstuck() void
  + fillItemsList() void
  # increaseBlockDrops(List~ItemStack~) List~ItemStack~
  - isPassable(BlockState) Boolean
  - findTree() IAIState
   boolean onSapling
   Class~BuildingLumberjack~ expectedBuildingClass
   int actionsDoneUntilDumping
   int actionRewardForCraftingSuccess
}
class EntityAIWorkMechanic {
  + EntityAIWorkMechanic(JobMechanic) 
   Class~BuildingMechanic~ expectedBuildingClass
}
class EntityAIWorkNether {
  + EntityAIWorkNether(JobNetherWorker) 
  - findTool(BlockState, BlockPos) ItemStack
  # checkAndRequestArmor() void
  # updateRenderMetaData() void
  # attemptToEat() void
  - findTool(EquipmentTypeEntry) ItemStack
  # openPortal() IAIState
  # closePortal() IAIState
  + hasWorkToDo() boolean
  - equipArmor(boolean) void
  - checkHeal(AbstractEntityCitizen) float
  - goToVault() void
  - setEquipSlot(EquipmentSlot, boolean) void
  - useFlintAndSteel() void
  # decide() IAIState
  - findItem(Predicate~ItemStack~) ItemStack
  # stayInNether() IAIState
  - logAllEquipment(ExpeditionLog, boolean) void
  # leaveForNether() IAIState
  - xpOnDrop(Block) int
  # checkAndRequestFood() IAIState
  # returnFromNether() IAIState
  + canBeInterrupted() boolean
   Class~BuildingNetherWorker~ expectedBuildingClass
   List~ItemStack~ ediblesList
   IAIState stateAfterPickUp
}
class EntityAIWorkPlanter {
  + EntityAIWorkPlanter(JobPlanter) 
  - checkIfItemsUnavailable(IConcreteDeliverable) boolean
  - handleMiningAction(boolean) ActionHandlerResult
  + canGoIdle() boolean
  - pickField() IAIState
  - decideFieldWork() IAIState
  # updateRenderMetaData() void
  - prepare() IAIState
  - handleBonemealAction() ActionHandlerResult
  - workField() IAIState
  + hasWorkToDo() boolean
  - returnToBuilding() IAIState
  - moveToField() IAIState
  - handlePlantingAction() ActionHandlerResult
  # decide() IAIState
  + onBlockDropReception(List~ItemStack~) void
  - resetActiveField() void
   Class~BuildingPlantation~ expectedBuildingClass
   int actionsDoneUntilDumping
   IAIState stateAfterPickUp
   PlantationField? currentField
}
class EntityAIWorkPupil {
  + EntityAIWorkPupil(JobPupil) 
  - decide() IAIState
  - study() IAIState?
  - startWorkingAtOwnBuilding() IAIState
  - recess() IAIState
   Class~BuildingSchool~ expectedBuildingClass
}
class EntityAIWorkRabbitHerder {
  + EntityAIWorkRabbitHerder(JobRabbitHerder) 
  # updateRenderMetaData() void
  # butcherAnimal(Animal?) void
   Class~BuildingRabbitHutch~ expectedBuildingClass
}
class EntityAIWorkResearcher {
  + EntityAIWorkResearcher(JobResearch) 
  - startWorkingAtOwnBuilding() IAIState
  - study() IAIState
   Class~BuildingUniversity~ expectedBuildingClass
}
class EntityAIWorkSawmill {
  + EntityAIWorkSawmill(JobSawmill) 
  # craft() IAIState
   Class~BuildingSawmill~ expectedBuildingClass
}
class EntityAIWorkShepherd {
  + EntityAIWorkShepherd(JobShepherd) 
  - findShearableSheep() Sheep?
  + decideWhatToDo() IAIState
  - dyeSheepChance(Sheep) void
  - shearSheep() IAIState
   Class~BuildingShepherd~ expectedBuildingClass
   double butcheringAttackDamage
   List~EquipmentTypeEntry~ extraToolsNeeded
}
class EntityAIWorkSifter {
  + EntityAIWorkSifter(JobSifter) 
  # decide() IAIState
  + hasWorkToDo() boolean
  # sift() IAIState
   Class~BuildingSifter~ expectedBuildingClass
   int actionsDoneUntilDumping
}
class EntityAIWorkSmelter {
  + EntityAIWorkSmelter(JobSmelter) 
  - breakOres() IAIState
  # checkForImportantJobs() IAIState
  # extractFromFurnace(FurnaceBlockEntity) void
  # isSmeltable(ItemStack) boolean
  + requestSmeltable() void
   IRequestable smeltAbleClass
   Class~BuildingSmeltery~ expectedBuildingClass
}
class EntityAIWorkStablemaster {
  + EntityAIWorkStablemaster(JobStablemaster) 
  # convertMount() IAIState
  # readyMountForCombat() IAIState
  # countCurrentMounts() int
  # canBreedChildren() boolean
  # effectsAtHorse(CavalryHorseEntity) void
  + checkForToolOrWeapon(EquipmentTypeEntry) boolean
  + tick() void
  # attachHorse(AbstractHorse) boolean
  + decideWhatToDo() IAIState
  # breedAnimals() IAIState
  + detachHorse(AbstractHorse) void
  + chanceToButcher(List~Animal~) double
  + readyMount(MountMaintenance, CavalryHorseEntity) boolean
  + findNearbyUnstabledHorses() List~AbstractHorse~
  + gatherMounts() IAIState
  + feedHorse(AbstractHorse) boolean
   Class~BuildingStable~ expectedBuildingClass
   List~EquipmentTypeEntry~ extraToolsNeeded
}
class EntityAIWorkStoneSmeltery {
  + EntityAIWorkStoneSmeltery(JobStoneSmeltery) 
   Class~BuildingStoneSmeltery~ expectedBuildingClass
}
class EntityAIWorkStonemason {
  + EntityAIWorkStonemason(JobStonemason) 
   Class~BuildingStonemason~ expectedBuildingClass
}
class EntityAIWorkSwineHerder {
  + EntityAIWorkSwineHerder(JobSwineHerder) 
  # updateRenderMetaData() void
   Class~BuildingSwineHerder~ expectedBuildingClass
   double butcheringAttackDamage
}
class EntityAIWorkTeacher {
  + EntityAIWorkTeacher(JobTeacher) 
  - decide() IAIState
  - teach() IAIState
  # teachPupil(AbstractEntityCitizen, double) void
  - requestPaper() void
  - startWorkingAtOwnBuilding() IAIState
   Class~BuildingSchool~ expectedBuildingClass
   int actionsDoneUntilDumping
}
class EntityAIWorkUndertaker {
  + EntityAIWorkUndertaker(JobUndertaker) 
  - getResurrectChance(BuildingGraveyard) double
  - wander() IAIState
  - equipShovel() void
  - digGrave() IAIState
  - emptyGrave() IAIState
  - tryResurrect() IAIState
  - unequip() void
  - buryCitizen() IAIState
  # wantInventoryDumped() boolean
  - startWorking() IAIState
  - digIfAble(BlockPos, BlockEntity) boolean
   double totemResurrectChance
   int shovelSlot
   Class~BuildingGraveyard~ expectedBuildingClass
   AbstractEntityCitizen? citizen
}
class KnightCombatAI {
  + KnightCombatAI(EntityCitizen, ITickRateStateMachine, AbstractEntityAIGuard) 
  # isAttackableTarget(LivingEntity) boolean
  + canAttack() boolean
  # moveInAttackPosition(LivingEntity) PathResult
  # doAttack(LivingEntity) void
  - doAoeAttack(DamageSource, double) void
  # attackProtect() IAIState
  # skipSearch(LivingEntity) boolean
  # onTargetDied(LivingEntity) void
  # isWithinPersecutionDistance(LivingEntity) boolean
  # onTargetChange(LivingEntity) void
   VisibleCitizenStatus combatStatus
   double attackDamage
   double attackDistance
   int attackDelay
   int searchRange
   double combatMovementSpeed
   EquipmentTypeEntry weaponType
}
class LayerBlueprintIterator {
  - LayerBlueprintIterator(String, StructureHandlerWrapper, IStructureHandler) 
  + LayerBlueprintIterator(String, IStructureHandler) 
  - int layer
  + getBluePrintPositionInfo(BlockPos) BlueprintPositionInfo
   BlockPos progressPos
   BlockPos prevProgressPos
   IStructureHandler structureHandler
   BlockPos size
   int layer
}
class MineNode {
  + MineNode(int, int, Vec2i?) 
  - NodeStatus status
  - int z
  - NodeType style
  - int x
  - Optional~Integer~ rot
  - Vec2i? parent
  + createFromNBT(CompoundTag) MineNode
  + write(CompoundTag) void
  + toString() String
  + getRandomNextNode(MinerLevel, int) MineNode?
  + hashCode() int
  + equals(Object) boolean
   Vec2i? parent
   Optional~Integer~ rot
   Vec2i southNodeCenter
   int x
   NodeStatus status
   int z
   NodeType style
   Vec2i westNodeCenter
   Vec2i northNodeCenter
   Vec2i eastNodeCenter
}
class MinerLevel {
  + MinerLevel(BuildingMiner, int, BlockPos) 
  + MinerLevel(CompoundTag) 
  - BlockPos? levelSign
  - int depth
  - Map~Vec2i, MineNode~ nodes
  - MineNode ladderNode
  + getRandomNode(MineNode?) MineNode
  - getNextNodePositionFromNodeWithRotation(MineNode, int, int) Vec2i
  + toString() String
  + write(CompoundTag) void
  + closeNextNode(int, MineNode, Level) void
  + getRandomCompletedNode(BuildingMiner) BlockPos
  + getNode(Vec2i) MineNode
  + getOpenNode(Vec2i) MineNode
   BlockPos levelSign
   int numberOfNodes
   Map~Vec2i, MineNode~ nodes
   int numberOfBuiltNodes
   int depth
   MineNode ladderNode
}
class Patient {
  + Patient(CompoundTag) 
  + Patient(int) 
  - int id
  - PatientState state
  + write(CompoundTag) void
   int id
   PatientState state
}
class RangerCombatAI {
  + RangerCombatAI(EntityCitizen, ITickRateStateMachine, AbstractEntityAIGuard) 
  # isWithinPersecutionDistance(LivingEntity) boolean
  # isAttackableTarget(LivingEntity) boolean
  # onTargetChange(LivingEntity) void
  # skipSearch(LivingEntity) boolean
  # moveInAttackPosition(LivingEntity) PathResult
  # checkForTarget() boolean
  + canAttack() boolean
  - calculateDamage(AbstractArrow) double
  # onTargetDied(LivingEntity) void
  # doAttack(LivingEntity) void
   int YSearchRange
   double attackDistance
   double combatMovementSpeed
   int attackDelay
}
class Tree {
  + Tree(Level, BlockPos, IColony?) 
  - Tree() 
  - BlockPos location
  - Property~?~ variant
  - ArrayList~BlockPos~ stumpLocations
  - boolean netherTree
  - boolean isTree
  - ItemStack sapling
  - boolean dynamicTree
  - boolean slimeTree
  + getSaplingsForLeaf(ServerLevel, BlockPos) List~ItemStack~
  - hasEnoughLeavesAndIsSupposedToCut(LevelReader, BlockPos, List~ItemStorage~) boolean
  - supposedToCut(LevelReader, List~ItemStorage~, BlockPos) boolean
  - getFirstLeaf(LevelAccessor) BlockPos
  - isBlockPartOfSameTree(BlockState, BlockState) boolean
  + read(CompoundTag) Tree
  + checkTree(LevelReader, BlockPos, List~ItemStorage~, int) boolean
  + removeStump(BlockPos) void
  + pollNextLog() BlockPos
  + write(CompoundTag) void
  - logPrefix(BlockState) String
  + peekNextLeaf() BlockPos
  - addAndSearch(Level, BlockPos, IColony?) void
  + pollNextLeaf() BlockPos
  - getBottomAndTopLog(LevelReader, BlockPos, LinkedList~BlockPos~, BlockPos, BlockPos) Tuple~BlockPos, BlockPos~
  - calcSapling(Level) ItemStack
  - calcSaplingForPos(Level, BlockPos, boolean) ItemStack?
  + fillTreeStumps(int) void
  + checkIfInColony(BlockPos, IColony, LevelReader, boolean) boolean
  + findLogs(Level, IColony?) void
  + peekNextLog() BlockPos
  + hashCode() int
  + equals(Object?) boolean
  + hasLeaves() boolean
  + hasLogs() boolean
  - addAndSearch(Level) void
  - checkTree(Level, BlockPos) void
   boolean isTree
   boolean dynamicTree
   ItemStack sapling
   BlockPos location
   boolean netherTree
   boolean slimeTree
   List~BlockPos~ stumpLocations
   Property~?~ variant
}
class WorkerLoadOnlyStructureHandler~J, B~ {
  + WorkerLoadOnlyStructureHandler(Level, BlockPos, Blueprint, PlacementSettings, AbstractEntityAIStructure~J, B~) 
  + getSolidBlockForPos(BlockPos) BlockState
  + getSolidBlockForPos(BlockPos, Function~BlockPos, BlockState~?) BlockState
}

AbstractEntityAIBasic~J, B~  -->  AbstractAISkeleton~J~ 
AbstractEntityAICrafting~J, B~  -->  AbstractEntityAIInteract~J, B~ 
AbstractEntityAIFight~J, B~  -->  AbstractEntityAIInteract~J, B~ 
AbstractEntityAIGuard~J, B~  -->  AbstractEntityAIFight~J, B~ 
AbstractEntityAIHerder~J, B~  -->  AbstractEntityAIInteract~J, B~ 
AbstractEntityAIInteract~J, B~  -->  AbstractEntityAISkill~J, B~ 
AbstractEntityAIRequestSmelter~J, B~  -->  AbstractEntityAICrafting~J, B~ 
AbstractEntityAISkill~J, B~  -->  AbstractEntityAIBasic~J, B~ 
AbstractEntityAIStructureWithWorkOrder~J, B~  -->  AbstractEntityAIStructure~J, B~ 
AbstractEntityAIStructureWithWorkOrder~J, B~  ..>  WorkerLoadOnlyStructureHandler~J, B~ : «create»
AbstractEntityAIStructure~J, B~  -->  AbstractEntityAIInteract~J, B~ 
AbstractEntityAIStructure~J, B~  ..>  BuildingProgressStage : «create»
AbstractEntityAIStructure~J, B~  ..>  BuildingStructureHandler~J, B~ : «create»
AbstractEntityAITraining~J, B~  -->  AbstractEntityAIInteract~J, B~ 
AbstractEntityAIUsesFurnace~J, B~  -->  AbstractEntityAISkill~J, B~ 
BuildingStructureHandler~J, B~ "1" *--> "stages *" BuildingProgressStage 
BuildingStructureHandler~J, B~ "1" *--> "structureAI 1" AbstractEntityAIStructure~J, B~ 
CavalryCombatAI  -->  KnightCombatAI 
DruidCombatAI "1" *--> "parentAI 1" AbstractEntityAIGuard~J, B~ 
EntityAIArcherTraining  -->  AbstractEntityAITraining~J, B~ 
EntityAICavalry  -->  AbstractEntityAIGuard~J, B~ 
EntityAICavalry  ..>  CavalryCombatAI : «create»
EntityAICombatTraining  -->  AbstractEntityAITraining~J, B~ 
EntityAIConcreteMixer  -->  AbstractEntityAICrafting~J, B~ 
EntityAIDruid  -->  AbstractEntityAIGuard~J, B~ 
EntityAIDruid  ..>  DruidCombatAI : «create»
EntityAIKnight  -->  AbstractEntityAIGuard~J, B~ 
EntityAIKnight  ..>  KnightCombatAI : «create»
EntityAIQuarrier  -->  AbstractEntityAIStructureWithWorkOrder~J, B~ 
EntityAIQuarrier  ..>  BuildingProgressStage : «create»
EntityAIQuarrier  ..>  BuildingStructureHandler~J, B~ : «create»
EntityAIQuarrier  ..>  LayerBlueprintIterator : «create»
EntityAIQuarrier  ..>  WorkerLoadOnlyStructureHandler~J, B~ : «create»
EntityAIRanger  -->  AbstractEntityAIGuard~J, B~ 
EntityAIRanger  ..>  RangerCombatAI : «create»
EntityAIStructureBuilder  -->  AbstractEntityAIStructureWithWorkOrder~J, B~ 
EntityAIStructureMiner  -->  AbstractEntityAIStructureWithWorkOrder~J, B~ 
EntityAIStructureMiner  ..>  MinerLevel : «create»
EntityAIStructureMiner "1" *--> "workingNode 1" MineNode 
EntityAIStudy  -->  AbstractEntityAISkill~J, B~ 
EntityAIWorkAlchemist  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkBaker  -->  AbstractEntityAIRequestSmelter~J, B~ 
EntityAIWorkBeekeeper  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkBlacksmith  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkChef  -->  AbstractEntityAIRequestSmelter~J, B~ 
EntityAIWorkChickenHerder  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkComposter  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkCook  -->  AbstractEntityAIUsesFurnace~J, B~ 
EntityAIWorkCowboy  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkCrusher  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkDeliveryman  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkDyer  -->  AbstractEntityAIRequestSmelter~J, B~ 
EntityAIWorkEnchanter  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkFarmer  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkFisherman  -->  AbstractEntityAISkill~J, B~ 
EntityAIWorkFletcher  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkFlorist  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkGlassblower  -->  AbstractEntityAIRequestSmelter~J, B~ 
EntityAIWorkHealer  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkHealer "1" *--> "currentPatient 1" Patient 
EntityAIWorkLumberjack  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkLumberjack  ..>  Tree : «create»
EntityAIWorkMechanic  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkNether  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkPlanter  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkPupil  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkRabbitHerder  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkResearcher  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkSawmill  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkShepherd  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkSifter  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkSmelter  -->  AbstractEntityAIUsesFurnace~J, B~ 
EntityAIWorkStablemaster  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkStoneSmeltery  -->  AbstractEntityAIRequestSmelter~J, B~ 
EntityAIWorkStonemason  -->  AbstractEntityAICrafting~J, B~ 
EntityAIWorkSwineHerder  -->  AbstractEntityAIHerder~J, B~ 
EntityAIWorkTeacher  -->  AbstractEntityAIInteract~J, B~ 
EntityAIWorkUndertaker  -->  AbstractEntityAIInteract~J, B~ 
KnightCombatAI "1" *--> "parentAI 1" AbstractEntityAIGuard~J, B~ 
MinerLevel  ..>  MineNode : «create»
MinerLevel "1" *--> "nodes *" MineNode 
RangerCombatAI "1" *--> "parentAI 1" AbstractEntityAIGuard~J, B~ 
WorkerLoadOnlyStructureHandler~J, B~ "1" *--> "structureAI 1" AbstractEntityAIStructure~J, B~ 
```
