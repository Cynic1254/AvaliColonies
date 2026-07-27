# core.colony (cont. 3)

75 classes, 73 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractBuilding {
  # AbstractBuilding(IColony, BlockPos) 
  - boolean isBuilt
  - boolean dirty
  - boolean guardBuildingNear
  # List~IBuildingModule~ modules
  - IRequester requester
  - int prestige
  - String customName
  + canDeconstruct() boolean
  + getCraftingModuleForRecipe(IToken~?~) ICraftingBuildingModule
  + onPlayerEnterNearby(Player) void
  + serializeToView(FriendlyByteBuf, boolean) void
  + processOfflineTime(long) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onUpgradeComplete(Blueprint?, int) void
  + resetGuardBuildingNear() void
  + getCompletedRequestsOfType(ICitizenData, TypeToken~R~) ImmutableList~IRequest~R~~
  + reservedStacksExcluding(IRequest~IDeliverable~) Map~ItemStorage, Integer~
  + calculatePrestige(Blueprint) void
  + hasWorkerOpenRequestsOfType(int, TypeToken~R~) boolean
  + deconstruct() void
  + getSettingValueOrDefault(ISettingKey~T~, S) S
  + registerBlockPosition(BlockState, BlockPos, Level) void
  # keepFood() boolean
  + createRequest(R, boolean) IToken~?~
  + getSetting(ISettingKey~T~) T
  + removeWorkOrder() void
  # setupRsDataStore() void
  + createPickupRequest(int) boolean
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean, JobEntry) int
  + requestRepair(BlockPos) void
  + isMatchingBlock(Block) boolean
  + markDirty() void
  - forceItemStackToProvider(ICapabilityProvider, ItemStack) ItemStack?
  + clearDirty() void
  + forceTransferStack(ItemStack, Level) ItemStack?
  - loadRequestSystemFromNBT(CompoundTag) void
  + registerModule(IBuildingModule) void
  + onWakeUp() void
  # writeRequestSystemToNBT(CompoundTag) void
  + getCompletedRequestsOfCitizenOrBuilding(ICitizenData?) Collection~IRequest~?~~
  + onColonyTick(IColony) void
  + hasWorkerOpenRequests(int) boolean
  + canBeGathered() boolean
  + overruleNextOpenRequestOfCitizenWithStack(ICitizenData, ItemStack) boolean
  + createRequest(ICitizenData, R, boolean) IToken~?~
  + canBeBuiltByBuilder(int) boolean
  + getOpenRequestsOfCitizenOrBuilding(int, Predicate~IRequest~?~~) List~IRequest~?~~
  + cancelAllRequestsOfCitizenOrBuilding(ICitizenData?) void
  + calculateCorners() void
  + onPlacement() void
  + moveToSyncCitizen(ICitizenData, IRequest~?~) void
  + requestUpgrade(Player, BlockPos) void
  + deserializeNBT(CompoundTag) void
  - addRequestToMaps(int, IToken~?~, TypeToken~?~) void
  + pickUp(Player) void
  + hasWorkerOpenRequestsFiltered(int, Predicate~IRequest~?~~) boolean
  + onPlayerEnterBuilding(Player) void
  + getOpenRequestsOfType(int, TypeToken~R~) ImmutableList~IRequest~R~~
  + hasOpenSyncRequest(ICitizenData) boolean
  + hasCitizenCompletedRequestsToPickup(ICitizenData) boolean
  + onRestart(ICitizenData) void
  + onDestroyed() void
  + serializeNBT() CompoundTag
  + getModule(int) IBuildingModule
  + getCitizenForRequest(IToken~?~) Optional~ICitizenData~
  + requestRemoval(Player, BlockPos) void
  + markRequestAsAccepted(ICitizenData, IToken~?~) void
  + destroy() void
  + getClaimRadius(int) int
  - isRequestStuck(IRequest~?~, List~IToken~?~~, List~IToken~?~~) boolean
  # requestWorkOrder(WorkOrderType, BlockPos) void
  + getCompletedRequestsOfCitizenOrBuilding(ICitizenData?, Predicate~IRequest~?~~) List~IRequest~?~~
  + hasCitizenCompletedRequests(ICitizenData) boolean
  + hasModule(ModuleProducer~?, ?~) boolean
  + getCompletedRequestsOfTypeFiltered(ICitizenData, TypeToken~R~, Predicate~IRequest~R~~) ImmutableList~IRequest~R~~
  + isItemStackInRequest(ItemStack?) boolean
  + getModule(ModuleProducer~M, V~) M
  + getOpenRequests(int) Collection~IRequest~?~~
  + overruleNextOpenRequestWithStack(ItemStack) void
  - getFirstOverullingRequestFromInputList(Collection~IRequest~IDeliverable~~, ItemStack) IRequest~IDeliverable~?
  + getOpenRequestsOfTypeFiltered(ICitizenData, TypeToken~R~, Predicate~IRequest~R~~) ImmutableList~IRequest~R~~
  + onCleanUp(ICitizenData) void
  + createResolvers() ImmutableCollection~IRequestResolver~?~~
   IRequester requester
   List~IItemHandler~ handlers
   IRequestSystemBuildingDataStore dataStore
   String customBuildingName
   Set~ICitizenData~ allAssignedCitizen
   List~IBuildingModule~ modules
   Map~Integer, Collection~IToken~?~~~ openRequestsByCitizen
   Map~Integer, Collection~IToken~?~~~ completedRequestsByCitizen
   IToken~?~ id
   AbstractTileEntityColonyBuilding tileEntity
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   boolean guardBuildingNear
   boolean isBuilt
   Map~TypeToken~?~, Collection~IToken~?~~~ openRequestsByRequestableType
   String customName
   ILocation location
   String buildingDisplayName
   boolean dirty
   boolean pendingConstruction
   int prestige
   int currentWorkOrderLevel
   ImmutableCollection~IRequestResolver~?~~ resolvers
   Map~IToken~?~, Integer~ citizensByRequest
   Class~IBuildingModule~ classType
   int maxBuildingLevel
}
class AbstractBuildingContainer {
  + AbstractBuildingContainer(BlockPos, IColony) 
  # AbstractTileEntityColonyBuilding tileEntity
  # getFirstLocationFromTag(String) BlockPos?
  + registerBlockPosition(BlockState, BlockPos, Level) void
  + registerBlockPosition(Block, BlockPos, Level) void
  + removeContainerPosition(BlockPos) void
  + getCapability(Capability~T~, Direction?) LazyOptional~T~
  + deserializeNBT(CompoundTag) void
  + addContainerPosition(BlockPos) void
  + alterPickUpPriority(int) void
  + serializeNBT() CompoundTag
  + getLocationsFromTag(String) List~BlockPos~
   List~BlockPos~ containers
   int pickUpPriority
   AbstractTileEntityColonyBuilding tileEntity
}
class AbstractBuildingGuards {
  + AbstractBuildingGuards(IColony, BlockPos) 
  # BlockPos tempNextPatrolPoint
  - ILocation rallyLocation
  - int patrolTimer
  - BlockPos minePos
  - BlockPos guardPos
  + deserializeNBT(CompoundTag) void
  + arrivedAtPatrolPoint(AbstractEntityCitizen) void
  + startPatrolNext() void
  + addPatrolTarget(BlockPos) void
  + requiresManualTarget() boolean
  + onUpgradeComplete(Blueprint?, int) void
  + serializeNBT() CompoundTag
  + getGuardPos(AbstractEntityCitizen) BlockPos
  + onColonyTick(IColony) void
  + calculateMobs() void
  + shallPatrolManually() boolean
  + getNextPatrolTarget(boolean) BlockPos?
  + serializeToView(FriendlyByteBuf, boolean) void
  + shallRetrieveOnLowHealth() boolean
  + resetPatrolTargets() void
   BlockPos guardPos
   int bonusHealth
   String task
   int patrolTimer
   int patrolDistance
   Player playerToFollow
   boolean tightGrouping
   ILocation? rallyLocation
   BlockPos minePos
   BlockPos positionToFollow
   BlockPos tempNextPatrolPoint
   Player? playerToFollowOrRally
   int bonusVision
   BlockPos? randomPatrolTarget
}
class AbstractBuildingStructureBuilder {
  + AbstractBuildingStructureBuilder(IColony, BlockPos) 
  - int workOrderId
  + int MAX_BUILDING_LEVEL
  - Map~Integer, List~BlockPos~~ fluidsToRemove
  + addNeededResource(ItemStack?, int) void
  - checkIfShouldKeepEquipment(EquipmentTypeEntry, ItemStack, List~ItemStorage~) boolean
  + nextStage() void
  + onWorkOrderCancellation(IWorkOrder) void
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean, JobEntry) int
  + checkOrRequestBucket(BuilderBucket?, ICitizenData) void
  + setProgressPos(BlockPos, BuildingProgressStage) void
  + hasWorkOrder() boolean
  + hasResourceInBucket(ItemStack) boolean
  + serializeToView(FriendlyByteBuf, boolean) void
  + resetNeededResources() void
  + complete(ICitizenData) void
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
  + reduceNeededResource(ItemStack, int) void
  + requiresResourceForBuilding(ItemStack) boolean
  + forceTransferStack(ItemStack, Level) ItemStack?
   Map~Integer, List~BlockPos~~ fluidsToRemove
   int MAX_BUILDING_LEVEL
   Tuple~BlockPos, BuildingProgressStage~? progress
   BuilderBucket? nextBucket
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   int totalStages
   IBuilderWorkOrder workOrder
   BuilderBucket? requiredResources
   int resourceBatchMultiplier
   Map~String, BuildingBuilderResource~ neededResources
   int workOrderId
}
class AbstractPlantationModule {
  # AbstractPlantationModule(IBuildingExtension, String, String, Item) 
  - String fieldTag
  - Item item
  - String workTag
  + applyBonemeal(AbstractEntityCitizen, BlockPos, ItemStack, Player) void
  + getPlantingBlockState(Level, BlockPos, BlockState) BlockState
  + hashCode() int
  + getValidWorkingPositions(Level, List~BlockPos~) List~BlockPos~
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  + equals(Object) boolean
   Item item
   String fieldTag
   String workTag
   ResourceLocation requiredResearchEffect
   int plantsToRequest
   List~Item~ validBonemeal
   int maxWorkingPositions
   List~BlockPos~ workingPositions
}
class AbstractSchematicProvider {
  + AbstractSchematicProvider(BlockPos, IColony) 
  - BuildingEntry buildingType
  - String structurePack
  - boolean isDeconstructed
  # IColony colony
  - int buildingLevel
  + hashCode() int
  + hasParent() boolean
  + onUpgradeSchematicTo(String, String, IBlueprintDataProviderBE) void
  - deserializerStructureInformationFrom(CompoundTag) void
  + safeUpdateTEDataFromSchematic() void
  + onColonyTick(IColony) void
  + isInBuilding(BlockPos) boolean
  - isParentValid(BlockPos) boolean
  + asyncPrestigeRecalc() void
  + setCorners(BlockPos, BlockPos) void
  + equals(Object) boolean
  + upgradeBuildingLevelToSchematicData() void
  - unsafeUpdateTEDataFromSchematic(TileEntityColonyBuilding) void
  + setDeconstructed() void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   int rotation
   Set~BlockPos~ children
   BlockPos position
   boolean isMirrored
   String structurePack
   String blueprintPath
   IColony colony
   boolean mirrored
   BlockPos parent
   boolean isDeconstructed
   Tuple~BlockPos, BlockPos~ corners
   BlockPos ID
   BuildingEntry buildingType
   int buildingLevel
}
class BambooPlantModule {
  + BambooPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   Integer? maximumPlantLength
   int minimumPlantLength
   EquipmentTypeEntry requiredTool
}
class BoneMealedPlantModule {
  # BoneMealedPlantModule(IBuildingExtension, String, String, Item) 
  + decideFieldWork(Level, BlockPos) Builder
  - getPositionToHarvest(Level) BlockPos?
  + getNextWorkingPosition(Level) BlockPos?
  # isValidBonemealLocation(BlockState) boolean
  - decideWorkAction(Level, BlockPos) ActionToPerform
  # isValidHarvestBlock(BlockState) boolean
   List~Item~ validBonemeal
   int percentageChance
   int actionLimit
   int maxWorkingPositions
   List~ItemStack~ requiredItemsForOperation
}
class BuildingAlchemist {
  + BuildingAlchemist(IColony, BlockPos) 
  + serializeNBT() CompoundTag
  + removeBrewingStand(BlockPos) void
  + deserializeNBT(CompoundTag) void
  + registerBlockPosition(BlockState, BlockPos, Level) void
  + removeSoilPosition(BlockPos) void
  + removeLeafPosition(BlockPos) void
   List~BlockPos~ allSoilPositions
   int maxBuildingLevel
   List~BlockPos~ allLeavePositions
   String schematicName
   List~BlockPos~ allBrewingStandPositions
}
class BuildingArchery {
  + BuildingArchery(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + getRandomShootingTarget(RandomSource) BlockPos
  + registerBlockPosition(Block, BlockPos, Level) void
  + getRandomShootingStandPosition(RandomSource) BlockPos
   String SCHEMATIC_NAME
}
class BuildingBaker {
  + BuildingBaker(IColony, BlockPos) 
  # keepFood() boolean
  + canEat(ItemStack) boolean
   int maxBuildingLevel
   String schematicName
}
class BuildingBarracks {
  + BuildingBarracks(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  - List~BlockPos~ towers
  + getClaimRadius(int) int
  + onUpgradeComplete(Blueprint?, int) void
  + serializeNBT() CompoundTag
  + onDestroyed() void
  + deserializeNBT(CompoundTag) void
  + registerBlockPosition(BlockState, BlockPos, Level) void
  + onColonyTick(IColony) void
   int maxBuildingLevel
   List~BlockPos~ towers
   String SCHEMATIC_NAME
}
class BuildingBarracksTower {
  + BuildingBarracksTower(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  + canDeconstruct() boolean
  + addBarracks(BlockPos) void
  + serializeNBT() CompoundTag
  + onUpgradeComplete(Blueprint?, int) void
  + deserializeNBT(CompoundTag) void
  + getClaimRadius(int) int
  + requestUpgrade(Player, BlockPos) void
   int maxBuildingLevel
   String SCHEMATIC_NAME
}
class BuildingBeekeeper {
  + BuildingBeekeeper(IColony, BlockPos) 
  - Set~BlockPos~ hives
  + serializeNBT() CompoundTag
  + addHive(BlockPos) void
  + serializeToView(FriendlyByteBuf, boolean) void
  + removeHive(BlockPos) void
  + deserializeNBT(CompoundTag) void
  + canEat(ItemStack) boolean
   int maximumHives
   int maxBuildingLevel
   String harvestTypes
   String schematicName
   Set~BlockPos~ hives
}
class BuildingBlacksmith {
  + BuildingBlacksmith(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingBuilder {
  + BuildingBuilder(IColony, BlockPos) 
  - boolean purgedMobsToday
  + canAssignCitizens() boolean
  + hasPurgedMobsToday() boolean
  + setWorkOrder(int, Context) void
  + canBeBuiltByBuilder(int) boolean
  + serializeNBT() CompoundTag
  + canEat(ItemStack) boolean
  + onWakeUp() void
  + deserializeNBT(CompoundTag) void
   boolean purgedMobsToday
   boolean manualMode
   String schematicName
}
class BuildingChickenHerder {
  + BuildingChickenHerder(IColony, BlockPos) 
   String schematicName
}
class BuildingCombatAcademy {
  + BuildingCombatAcademy(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  + deserializeNBT(CompoundTag) void
  - writePartnerTupleToNBT(Entry~Integer, Integer~) CompoundTag
  + serializeNBT() CompoundTag
  + getRandomCombatPartner(AbstractEntityCitizen) AbstractEntityCitizen
  + getCombatPartner(AbstractEntityCitizen) AbstractEntityCitizen
  + resetPartner(AbstractEntityCitizen) void
  + hasCombatPartner(AbstractEntityCitizen) boolean
  + registerBlockPosition(Block, BlockPos, Level) void
  + getRandomCombatTarget(RandomSource) BlockPos
   int maxBuildingLevel
   String SCHEMATIC_NAME
}
class BuildingComposter {
  + BuildingComposter(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  - List~BlockPos~ barrels
  + serializeNBT() CompoundTag
  + registerBlockPosition(Block, BlockPos, Level) void
  + deserializeNBT(CompoundTag) void
   int MAX_BUILDING_LEVEL
   String schematicName
   List~BlockPos~ barrels
}
class BuildingConcreteMixer {
  + BuildingConcreteMixer(IColony, BlockPos) 
  + registerBlockPosition(BlockState, BlockPos, Level) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + outputBlockCountInWorld(ItemStack) int
   int maxConcretePlaced
   BlockPos? blockToPlace
   int maxBuildingLevel
   BlockPos? blockToMine
   String schematicName
}
class BuildingCook {
  + BuildingCook(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean, JobEntry) int
  # keepFood() boolean
   int MAX_BUILDING_LEVEL
   BlockPos nextSittingPosition
   String schematicName
}
class BuildingCowboy {
  + BuildingCowboy(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + canEat(ItemStack) boolean
   int MAX_BUILDING_LEVEL
   ItemStack milkOutputItem
   ItemStack milkInputItem
   String schematicName
}
class BuildingCrusher {
  + BuildingCrusher(IColony, BlockPos) 
  - int currentDailyQuantity
  + deserializeNBT(CompoundTag) void
  + onWakeUp() void
  + serializeNBT() CompoundTag
   int maxDailyQuantity
   String schematicName
   int currentDailyQuantity
}
class BuildingDeliveryman {
  + BuildingDeliveryman(IColony, BlockPos) 
  + canEat(ItemStack) boolean
   int maxBuildingLevel
   String schematicName
}
class BuildingDyer {
  + BuildingDyer(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingEnchanter {
  + BuildingEnchanter(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingFarmer {
  + BuildingFarmer(IColony, BlockPos) 
  - BlockPos? workingOffset
  - BlockPos? prevPos
  - int MAX_BUILDING_LEVEL
  - int cell
  + requestFertilizer() boolean
  + setCell(int) int
  + deserializeNBT(CompoundTag) void
  + canEat(ItemStack) boolean
  + canBeGathered() boolean
  + serializeNBT() CompoundTag
   int MAX_BUILDING_LEVEL
   int cell
   BlockPos prevPos
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   BlockPos workingOffset
   String schematicName
}
class BuildingFisherman {
  + BuildingFisherman(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingFletcher {
  + BuildingFletcher(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingFlorist {
  + BuildingFlorist(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  - List~BlockPos~ plantGround
  + removePlantableGround(BlockPos) void
  + getPlantablesForBuildingLevel(int) Set~ItemStorage~
  + registerBlockPosition(Block, BlockPos, Level) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   int MAX_BUILDING_LEVEL
   ItemStack? flowerToGrow
   List~BlockPos~ plantGround
   String schematicName
}
class BuildingGateHouse {
  + BuildingGateHouse(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  + requiresManualTarget() boolean
  + onDestroyed() void
  + destroy() void
  + onUpgradeComplete(Blueprint?, int) void
  + getClaimRadius(int) int
  + getGuardPos(AbstractEntityCitizen) BlockPos
   int buildingLevelEquivalent
   String SCHEMATIC_NAME
   int bonusHealth
   int maxEquipmentLevel
   String task
   int maxBuildingLevel
   int bonusVision
   int buildingLevel
}
class BuildingGlassblower {
  + BuildingGlassblower(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingGraveyard {
  + BuildingGraveyard(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
  + ClearCurrentGrave() void
  + registerBlockPosition(BlockState, BlockPos, Level) void
   int MAX_BUILDING_LEVEL
   BlockPos? graveToWorkOn
   Tuple~BlockPos, Direction~ randomFreeVisualGravePos
   Set~Tuple~BlockPos, Direction~~ gravePositions
   String schematicName
}
class BuildingGuardTower {
  + BuildingGuardTower(IColony, BlockPos) 
  - String SCHEMATIC_NAME
  + onUpgradeComplete(Blueprint?, int) void
  + requiresManualTarget() boolean
  + onDestroyed() void
  + getClaimRadius(int) int
   int maxBuildingLevel
   String SCHEMATIC_NAME
   int bonusHealth
}
class BuildingHospital {
  + BuildingHospital(IColony, BlockPos) 
  - Map~Integer, Patient~ patients
  - int MAX_BUILDING_LEVEL
  - setBedOccupation(BlockPos, boolean) void
  + deserializeNBT(CompoundTag) void
  - isCureItem(ItemStack) boolean
  + registerPatient(BlockPos, int) void
  + onWakeUp() void
  + removePatientFile(Patient) void
  + canEat(ItemStack) boolean
  + serializeNBT() CompoundTag
  + registerBlockPosition(BlockState, BlockPos, Level) void
  + checkOrCreatePatientFile(int) void
   int MAX_BUILDING_LEVEL
   List~Patient~ patients
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   String schematicName
   List~BlockPos~ bedList
}
class BuildingKitchen {
  + BuildingKitchen(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  # keepFood() boolean
  + canEat(ItemStack) boolean
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingLibrary {
  + BuildingLibrary(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + registerBlockPosition(Block, BlockPos, Level) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   int MAX_BUILDING_LEVEL
   String schematicName
   BlockPos randomBookShelf
}
class BuildingLumberjack {
  + BuildingLumberjack(IColony, BlockPos) 
  - BlockPos endRestriction
  - BlockPos startRestriction
  - Set~BlockPos~ netherTrees
  - int MAX_BUILDING_LEVEL
  + setRestrictedArea(BlockPos, BlockPos) void
  + serializeNBT() CompoundTag
  + shouldDefoliate() boolean
  + deserializeNBT(CompoundTag) void
  + removeNetherTree(BlockPos) void
  + serializeToView(FriendlyByteBuf, boolean) void
  + canBeGathered() boolean
  + shouldReplant() boolean
  + shouldRestrict() boolean
  + addNetherTree(BlockPos) void
  + onColonyTick(IColony) void
  - bonemealFungi() void
   int MAX_BUILDING_LEVEL
   BlockPos startRestriction
   Set~BlockPos~ netherTrees
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   BlockPos endRestriction
   String schematicName
}
class BuildingMechanic {
  + BuildingMechanic(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingMiner {
  + BuildingMiner(IColony, BlockPos) 
  - BlockPos ladderLocation
  - BlockPos cobbleLocation
  - loadLadderPos() void
  + getDepthLimit(Level) int
  - getRotationFromVector(BuildingMiner) int
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
  + normalizeMaxDepth(int, Level) int
  + initStructure(MineNode, int, BlockPos, BuildingMiner, Level, JobMiner) void
   BlockPos ladderLocation
   int resourceBatchMultiplier
   BlockPos cobbleLocation
   int maxBuildingLevel
   String schematicName
}
class BuildingMysticalSite {
  + BuildingMysticalSite(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingNetherWorker {
  + BuildingNetherWorker(IColony, BlockPos) 
  - int MAX_PER_PERIOD
  - int PERIOD_DAYS
  + shallClosePortalOnReturn() boolean
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean, JobEntry) int
  + onWakeUp() void
  + recordTrip() void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + onPlacement() void
   int maxBuildingLevel
   boolean readyForTrip
   BlockPos portalLocation
   String schematicName
   int PERIOD_DAYS
   int MAX_PER_PERIOD
}
class BuildingPlantation {
  + BuildingPlantation(IColony, BlockPos) 
  - updateField(BuildingExtensionEntry) void
  + onPlacement() void
  + onUpgradeComplete(Blueprint?, int) void
  + canEat(ItemStack) boolean
  - updateFields() void
  + deserializeNBT(CompoundTag) void
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   String schematicName
   AbstractTileEntityColonyBuilding tileEntity
}
class BuildingRabbitHutch {
  + BuildingRabbitHutch(IColony, BlockPos) 
  + canEat(ItemStack) boolean
   String schematicName
}
class BuildingSawmill {
  + BuildingSawmill(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingSchool {
  + BuildingSchool(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + registerBlockPosition(Block, BlockPos, Level) void
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
   int MAX_BUILDING_LEVEL
   String schematicName
   BlockPos? randomPlaceToSit
}
class BuildingShepherd {
  + BuildingShepherd(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + canEat(ItemStack) boolean
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingSifter {
  + BuildingSifter(IColony, BlockPos) 
  - int currentDailyQuantity
  - int MAX_BUILDING_LEVEL
  + deserializeNBT(CompoundTag) void
  + onWakeUp() void
  + serializeNBT() CompoundTag
   int MAX_BUILDING_LEVEL
   int maxDailyQuantity
   String schematicName
   int currentDailyQuantity
}
class BuildingSmeltery {
  + BuildingSmeltery(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingStable {
  + BuildingStable(IColony, BlockPos) 
  - long lastPatrolTime
  + serializeNBT() CompoundTag
  + cavalryPatrolFilter() Predicate~IBuilding~
  + stallPositions() List~BlockPos~
  + patrolPointForBuilding(BlockPos) BlockPos
  + minutesSinceLastPatrol() int
  + startPatrolNext() void
  + deserializeNBT(CompoundTag) void
   int patrolDistance
   String task
   String schematicName
   long lastPatrolTime
   BlockPos nextStallPosition
   BlockPos? randomPatrolTarget
}
class BuildingStoneSmeltery {
  + BuildingStoneSmeltery(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingStonemason {
  + BuildingStonemason(IColony, BlockPos) 
   int maxBuildingLevel
   String schematicName
}
class BuildingSwineHerder {
  + BuildingSwineHerder(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + canEat(ItemStack) boolean
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingTownHall {
  + BuildingTownHall(IColony, BlockPos) 
  - int MAX_BUILDING_LEVEL
  + addPermissionEvent(PermissionEvent) void
  + canBeGathered() boolean
  + removePermissionEvents(UUID) void
  + getClaimRadius(int) int
  + serializeToView(FriendlyByteBuf, boolean) void
   int MAX_BUILDING_LEVEL
   String schematicName
}
class BuildingUniversity {
  + BuildingUniversity(IColony, BlockPos) 
  + onSuccess(ILocalResearch) void
  + serializeNBT() CompoundTag
  + processOfflineTime(long) void
  + onColonyTick(IColony) void
  + deserializeNBT(CompoundTag) void
  + registerBlockPosition(Block, BlockPos, Level) void
   String schematicName
   BlockPos randomBookShelf
}
class BuildingWareHouse {
  + BuildingWareHouse(IColony, BlockPos) 
  + hasContainerPosition(BlockPos) boolean
  + upgradeContainers(Level) void
  + createResolvers() ImmutableCollection~IRequestResolver~?~~
  + registerBlockPosition(Block, BlockPos, Level) void
  + canBeGathered() boolean
  + requestRepair(BlockPos) void
  + canSort() boolean
  + canAccessWareHouse(ICitizenData) boolean
   AbstractTileEntityWareHouse tileEntity
   int maxBuildingLevel
   String schematicName
}
class CactusPlantModule {
  + CactusPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   EquipmentTypeEntry requiredTool
}
class CocoaPlantModule {
  + CocoaPlantModule(IBuildingExtension, String, String, Item) 
  # isValidClearingBlock(BlockState) boolean
  + getPlantingBlockState(Level, BlockPos, BlockState) BlockState
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   EquipmentTypeEntry requiredTool
}
class CrimsonPlantsPlantModule {
  + CrimsonPlantsPlantModule(IBuildingExtension, String, String, Item) 
   ResourceLocation requiredResearchEffect
   int percentageChance
   EquipmentTypeEntry requiredTool
}
class DefaultBuildingInstance {
  + DefaultBuildingInstance(IColony, BlockPos, String, int) 
  + String schematicName
  - int maxBuildingLevel
   int maxBuildingLevel
   String schematicName
}
class DownwardsGrowingPlantModule {
  # DownwardsGrowingPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
  # isValidPlantingBlock(BlockState) boolean
  - canHarvest(Level, BlockPos, boolean) boolean
  + getNextWorkingPosition(Level) BlockPos?
  + decideFieldWork(Level, BlockPos) Builder
  - decideWorkAction(Level, BlockPos, boolean) ActionToPerform
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  # isValidClearingBlock(BlockState) boolean
   Integer? maximumPlantLength
   int actionLimit
   int minimumPlantLength
   List~ItemStack~ requiredItemsForOperation
}
class GlowBerriesPlantModule {
  + GlowBerriesPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   EquipmentTypeEntry requiredTool
}
class KelpPlantModule {
  + KelpPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
  # isValidPlantingBlock(BlockState) boolean
  + getPositionToWalkTo(Level, BlockPos) BlockPos
   ResourceLocation requiredResearchEffect
   Integer maximumPlantLength
   int minimumPlantLength
   EquipmentTypeEntry requiredTool
}
class PercentageHarvestPlantModule {
  # PercentageHarvestPlantModule(IBuildingExtension, String, String, Item) 
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  - decideWorkAction(Level, BlockPos) ActionToPerform
  # isValidClearingBlock(BlockState) boolean
  # isValidPlantingBlock(BlockState) boolean
  # isValidHarvestBlock(BlockState) boolean
  + getNextWorkingPosition(Level) BlockPos?
  + decideFieldWork(Level, BlockPos) Builder
   List~ItemStack~ requiredItemsForOperation
   int actionLimit
   int minimumPlantPercentage
}
class PostBox {
  + PostBox(IColony, BlockPos) 
  + createResolvers() ImmutableCollection~IRequestResolver~?~~
  + canBeGathered() boolean
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
   int rotation
   int maxBuildingLevel
   Tuple~BlockPos, BlockPos~ corners
   String schematicName
   int buildingLevel
}
class SeagrassPlantModule {
  + SeagrassPlantModule(IBuildingExtension, String, String, Item) 
  # isValidBonemealLocation(BlockState) boolean
  + applyBonemeal(AbstractEntityCitizen, BlockPos, ItemStack, Player) void
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   List~Item~ validBonemeal
   EquipmentTypeEntry requiredTool
}
class SeapicklePlantModule {
  + SeapicklePlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
  + applyBonemeal(AbstractEntityCitizen, BlockPos, ItemStack, Player) void
  # isValidBonemealLocation(BlockState) boolean
  + decideFieldWork(Level, BlockPos) Builder
   List~Item~ validBonemeal
   EquipmentTypeEntry requiredTool
   int maxWorkingPositions
}
class Stash {
  + Stash(IColony, BlockPos) 
  + createResolvers() ImmutableCollection~IRequestResolver~?~~
   int rotation
   int maxBuildingLevel
   Tuple~BlockPos, BlockPos~ corners
   String schematicName
}
class SugarCanePlantModule {
  + SugarCanePlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   EquipmentTypeEntry requiredTool
}
class TreeSidePlantModule {
  # TreeSidePlantModule(IBuildingExtension, String, String, Item) 
  # isValidClearingBlock(BlockState) boolean
  + decideFieldWork(Level, BlockPos) Builder
  - decideWorkAction(Level, BlockPos) ActionToPerform
  # isValidHarvestBlock(BlockState) boolean
  + getNextWorkingPosition(Level) BlockPos?
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  + getValidWorkingPositions(Level, List~BlockPos~) List~BlockPos~
  # isValidPlantingBlock(BlockState) boolean
   List~ItemStack~ requiredItemsForOperation
   int actionLimit
}
class TwistingVinesPlantModule {
  + TwistingVinesPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   Integer? maximumPlantLength
   int minimumPlantLength
   EquipmentTypeEntry requiredTool
}
class UpwardsGrowingPlantModule {
  # UpwardsGrowingPlantModule(IBuildingExtension, String, String, Item) 
  + getNextWorkingPosition(Level) BlockPos?
  - decideWorkAction(Level, BlockPos, boolean) ActionToPerform
  # isValidHarvestBlock(BlockState) boolean
  # isValidPlantingBlock(BlockState) boolean
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  - canHarvest(Level, BlockPos, boolean) boolean
  # isValidClearingBlock(BlockState) boolean
  + decideFieldWork(Level, BlockPos) Builder
   Integer? maximumPlantLength
   int actionLimit
   int minimumPlantLength
   List~ItemStack~ requiredItemsForOperation
}
class VinePlantModule {
  + VinePlantModule(IBuildingExtension, String, String, Item) 
  + getPlantingBlockState(Level, BlockPos, BlockState) BlockState
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   int minimumPlantPercentage
   EquipmentTypeEntry requiredTool
}
class WarpedPlantsPlantModule {
  + WarpedPlantsPlantModule(IBuildingExtension, String, String, Item) 
   ResourceLocation requiredResearchEffect
   int percentageChance
   EquipmentTypeEntry requiredTool
}
class WeepingVinesPlantModule {
  + WeepingVinesPlantModule(IBuildingExtension, String, String, Item) 
  # isValidHarvestBlock(BlockState) boolean
   ResourceLocation requiredResearchEffect
   Integer? maximumPlantLength
   int minimumPlantLength
   EquipmentTypeEntry requiredTool
}

AbstractBuilding  -->  AbstractBuildingContainer 
AbstractBuildingContainer  -->  AbstractSchematicProvider 
AbstractBuildingGuards  -->  AbstractBuilding 
AbstractBuildingStructureBuilder  -->  AbstractBuilding 
BambooPlantModule  -->  UpwardsGrowingPlantModule 
BoneMealedPlantModule  -->  AbstractPlantationModule 
BuildingAlchemist  -->  AbstractBuilding 
BuildingArchery  -->  AbstractBuilding 
BuildingBaker  -->  AbstractBuilding 
BuildingBarracks  -->  AbstractBuilding 
BuildingBarracksTower  -->  AbstractBuildingGuards 
BuildingBeekeeper  -->  AbstractBuilding 
BuildingBlacksmith  -->  AbstractBuilding 
BuildingBuilder  -->  AbstractBuildingStructureBuilder 
BuildingChickenHerder  -->  AbstractBuilding 
BuildingCombatAcademy  -->  AbstractBuilding 
BuildingComposter  -->  AbstractBuilding 
BuildingConcreteMixer  -->  AbstractBuilding 
BuildingCook  -->  AbstractBuilding 
BuildingCowboy  -->  AbstractBuilding 
BuildingCrusher  -->  AbstractBuilding 
BuildingDeliveryman  -->  AbstractBuilding 
BuildingDyer  -->  AbstractBuilding 
BuildingEnchanter  -->  AbstractBuilding 
BuildingFarmer  -->  AbstractBuilding 
BuildingFisherman  -->  AbstractBuilding 
BuildingFletcher  -->  AbstractBuilding 
BuildingFlorist  -->  AbstractBuilding 
BuildingGateHouse  -->  AbstractBuildingGuards 
BuildingGlassblower  -->  AbstractBuilding 
BuildingGraveyard  -->  AbstractBuilding 
BuildingGuardTower  -->  AbstractBuildingGuards 
BuildingHospital  -->  AbstractBuilding 
BuildingKitchen  -->  AbstractBuilding 
BuildingLibrary  -->  AbstractBuilding 
BuildingLumberjack  -->  AbstractBuilding 
BuildingMechanic  -->  AbstractBuilding 
BuildingMiner  -->  AbstractBuildingStructureBuilder 
BuildingMysticalSite  -->  AbstractBuilding 
BuildingNetherWorker  -->  AbstractBuilding 
BuildingPlantation  -->  AbstractBuilding 
BuildingRabbitHutch  -->  AbstractBuilding 
BuildingSawmill  -->  AbstractBuilding 
BuildingSchool  -->  AbstractBuilding 
BuildingShepherd  -->  AbstractBuilding 
BuildingSifter  -->  AbstractBuilding 
BuildingSmeltery  -->  AbstractBuilding 
BuildingStable  -->  AbstractBuildingGuards 
BuildingStoneSmeltery  -->  AbstractBuilding 
BuildingStonemason  -->  AbstractBuilding 
BuildingSwineHerder  -->  AbstractBuilding 
BuildingTownHall  -->  AbstractBuilding 
BuildingUniversity  -->  AbstractBuilding 
BuildingWareHouse  -->  AbstractBuilding 
CactusPlantModule  -->  UpwardsGrowingPlantModule 
CocoaPlantModule  -->  TreeSidePlantModule 
CrimsonPlantsPlantModule  -->  BoneMealedPlantModule 
DefaultBuildingInstance  -->  AbstractBuilding 
DownwardsGrowingPlantModule  -->  AbstractPlantationModule 
GlowBerriesPlantModule  -->  DownwardsGrowingPlantModule 
KelpPlantModule  -->  UpwardsGrowingPlantModule 
PercentageHarvestPlantModule  -->  AbstractPlantationModule 
PostBox  -->  AbstractBuilding 
SeagrassPlantModule  -->  BoneMealedPlantModule 
SeapicklePlantModule  -->  BoneMealedPlantModule 
Stash  -->  AbstractBuilding 
SugarCanePlantModule  -->  UpwardsGrowingPlantModule 
TreeSidePlantModule  -->  AbstractPlantationModule 
TwistingVinesPlantModule  -->  UpwardsGrowingPlantModule 
UpwardsGrowingPlantModule  -->  AbstractPlantationModule 
VinePlantModule  -->  PercentageHarvestPlantModule 
WarpedPlantsPlantModule  -->  BoneMealedPlantModule 
WeepingVinesPlantModule  -->  DownwardsGrowingPlantModule 
```
