# api.colony

80 classes, 54 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractBuildingModule {
  + AbstractBuildingModule() 
  - ModuleProducer producer
  # IBuilding building
  + checkDirty() boolean
  + setProducer(ModuleProducer) IBuildingModule
  + setBuilding(IBuilding) IBuildingModule
  + markDirty() void
  + clearDirty() void
   IBuilding building
   ModuleProducer producer
}
class AbstractBuildingModuleView {
  + AbstractBuildingModuleView() 
  # IBuildingView buildingView
  - ModuleProducer producer
  + setProducer(ModuleProducer~M, V~) IBuildingModuleView
  + canBeHiredAs(JobEntry) boolean
  + setBuildingView(IBuildingView) IBuildingModuleView
  + setColonyView(IColonyView) IBuildingModuleView
   IColonyView colony
   IBuildingView buildingView
   ModuleProducer~M, V~ producer
}
class BuildingEntry {
  - BuildingEntry(ResourceLocation, AbstractColonyBlock~?~, BiFunction~IColony, BlockPos, IBuilding~, Supplier~BiFunction~IColonyView, BlockPos, IBuildingView~~, List~ModuleProducer~) 
  - AbstractColonyBlock~?~ buildingBlock
  - ResourceLocation registryName
  + getProducer(int) ModuleProducer?
  + produceBuilding(BlockPos, IColony) IBuilding
  + produceBuildingView(BlockPos, IColonyView) IBuildingView
  + produceModuleWithoutBuilding(String) IBuildingModule?
  + produceViewWithoutBuilding(String, IColonyView) IBuildingModuleView?
  + getProducer(String) ModuleProducer
   AbstractColonyBlock~?~ buildingBlock
   Map~String, ModuleProducer~ ALlModuleProducers
   List~ModuleProducer~ moduleProducers
   ResourceLocation registryName
   String translationKey
}
class BuildingExtensionRegistries {
  - BuildingExtensionRegistries() 
   IForgeRegistry~BuildingExtensionEntry~ buildingExtensionRegistry
}
class ColonyConnection {
  + ColonyConnection(int, String, BlockPos, DiplomacyStatus) 
  + ColonyConnection() 
  + deserializeByteBuf(FriendlyByteBuf) ColonyConnection
  + serializeByteBuf(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) ColonyConnection
}
class ColonyConnectionNode {
  + ColonyConnectionNode(BlockPos) 
  - int targetColonyId
  - BlockPos nextNode
  - BlockPos position
  - BlockPos previousNode
  + hasNextNode() boolean
  + write() CompoundTag
  + read(CompoundTag) void
  + alterNextNode(BlockPos) void
  + alterPreviousNode(BlockPos) void
   BlockPos nextNode
   int targetColonyId
   BlockPos position
   BlockPos previousNode
}
class ColonyEventDescriptionTypeRegistryEntry {
  + ColonyEventDescriptionTypeRegistryEntry(Function~CompoundTag, IColonyEventDescription~, Function~FriendlyByteBuf, IColonyEventDescription~, ResourceLocation) 
  - ResourceLocation registryName
  + deserializeEventDescriptionFromNBT(CompoundTag) IColonyEventDescription
  + deserializeEventDescriptionFromFriendlyByteBuf(FriendlyByteBuf) IColonyEventDescription
   ResourceLocation registryName
}
class ColonyEventTypeRegistryEntry {
  + ColonyEventTypeRegistryEntry(BiFunction~IColony, CompoundTag, IColonyEvent~, ResourceLocation, boolean) 
  + ColonyEventTypeRegistryEntry(BiFunction~IColony, CompoundTag, IColonyEvent~, ResourceLocation) 
  - ResourceLocation registryName
  - boolean isRaidEvent
  + deserializeEvent(IColony, CompoundTag) IColonyEvent
   ResourceLocation registryName
   boolean isRaidEvent
}
class ConnectionEvent {
  + ConnectionEvent(int, String, ConnectionEventType) 
  + deserializeNBT(CompoundTag) ConnectionEvent
  + serializeNBT() CompoundTag
  + serializeByteBuf(FriendlyByteBuf) void
  + id() int
  + name() String
  + connectionEventType() ConnectionEventType
  + deserializeByteBuf(FriendlyByteBuf) ConnectionEvent
}
class ConnectionEventType {
<<enumeration>>
  + ConnectionEventType() 
  + valueOf(String) ConnectionEventType
  + translationKey() String
  + values() ConnectionEventType[]
}
class DiplomacyStatus {
<<enumeration>>
  + DiplomacyStatus() 
  + translationKey() String
  + values() DiplomacyStatus[]
  + valueOf(String) DiplomacyStatus
}
class EventStatus {
<<enumeration>>
  + EventStatus() 
  + values() EventStatus[]
  + valueOf(String) EventStatus
}
class HiringMode {
<<enumeration>>
  - HiringMode(String) 
  - String translationKey
  + valueOf(String) HiringMode
  + values() HiringMode[]
   String translationKey
}
class IAltersBuildingFootprint {
<<Interface>>
   Tuple~BlockPos, BlockPos~ additionalCorners
}
class IAltersRequiredItems {
<<Interface>>
  + alterItemsToBeKept(TriConsumer~Predicate~ItemStack~, Integer, Boolean~) void
}
class IAssignmentModuleView {
<<Interface>>
  + canAssign(ICitizenDataView) boolean
  + removeCitizen(ICitizenDataView) void
  + addCitizen(ICitizenDataView) void
   int maxInhabitants
   HiringMode hiringMode
   JobEntry jobEntry
   boolean full
   List~Integer~ assignedCitizens
}
class IAssignsCitizen {
<<Interface>>
  + hasAssignedCitizen() boolean
  + assignCitizen(ICitizenData) boolean
  + hasAssignedCitizen(ICitizenData) boolean
  + removeCitizen(ICitizenData) boolean
   List~ICitizenData~ assignedCitizen
   int moduleMax
   HiringMode hiringMode
   List~Optional~AbstractEntityCitizen~~ assignedEntities
   boolean full
}
class IAssignsJob {
<<Interface>>
  + hasAssignedCitizen() boolean
   JobEntry jobEntry
}
class IBlockSettingFactory~T~ {
<<Interface>>
  + getNewInstance(BlockItem, BlockItem) T
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) T
}
class IBoolSettingFactory~T~ {
<<Interface>>
  + getNewInstance(boolean, boolean) T
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) T
}
class IBuilding {
<<Interface>>
  + getClaimRadius(int) int
  + getOpenRequests(int) Collection~IRequest~?~~
  + overruleNextOpenRequestWithStack(ItemStack) void
  + getSetting(ISettingKey~T~) T
  + hasWorkerOpenRequestsFiltered(int, Predicate~IRequest~?~~) boolean
  + getOpenRequestsOfTypeFiltered(ICitizenData, TypeToken~R~, Predicate~IRequest~R~~) ImmutableList~IRequest~R~~
  + upgradeBuildingLevelToSchematicData() void
  + onPlayerEnterNearby(Player) void
  + onCleanUp(ICitizenData) void
  + onRestart(ICitizenData) void
  + requestUpgrade(Player, BlockPos) void
  + isItemStackInRequest(ItemStack?) boolean
  + resetGuardBuildingNear() void
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean, JobEntry?) int
  + createRequest(ICitizenData, R, boolean) IToken~?~
  + hasCitizenCompletedRequests(ICitizenData) boolean
  + hasWorkerOpenRequestsOfType(int, TypeToken~R~) boolean
  + getOpenRequestsOfType(int, TypeToken~R~) ImmutableList~IRequest~R~~
  + getCompletedRequestsOfType(ICitizenData, TypeToken~R~) ImmutableList~IRequest~R~~
  + processOfflineTime(long) void
  + hasOpenSyncRequest(ICitizenData) boolean
  + hasCitizenCompletedRequestsToPickup(ICitizenData) boolean
  + createRequest(R, boolean) IToken~?~
  + canAssignCitizens() boolean
  + isMatchingBlock(Block) boolean
  + getLocationsFromTag(String) List~BlockPos~
  + calculatePrestige(Blueprint) void
  + onDestroyed() void
  + onColonyTick(IColony) void
  + hasWorkerOpenRequests(int) boolean
  + removeWorkOrder() void
  + canBeBuiltByBuilder(int) boolean
  + asyncPrestigeRecalc() void
  + isInBuilding(BlockPos) boolean
  + calculateCorners() void
  + markDirty() void
  + buildingRequiresCertainAmountOfItem(ItemStack, List~ItemStorage~, boolean) int
  + overruleNextOpenRequestOfCitizenWithStack(ICitizenData, ItemStack) boolean
  + onPlacement() void
  + createResolvers() ImmutableCollection~IRequestResolver~?~~
  + getCitizenForRequest(IToken~?~) Optional~ICitizenData~
  + requestRemoval(Player, BlockPos) void
  + sort(CombinedItemHandler) void
  + canEat(ItemStack) boolean
  + deconstruct() void
  + canBeGathered() boolean
  + forceTransferStack(ItemStack, Level) ItemStack?
  + canSort() boolean
  + getCompletedRequestsOfTypeFiltered(ICitizenData, TypeToken~R~, Predicate~IRequest~R~~) ImmutableList~IRequest~R~~
  + destroy() void
  + reservedStacksExcluding(IRequest~IDeliverable~) Map~ItemStorage, Integer~
  + pickUp(Player) void
  + onWakeUp() void
  + createPickupRequest(int) boolean
  + markRequestAsAccepted(ICitizenData, IToken~?~) void
  + getSettingValueOrDefault(ISettingKey~T~, S) S
  + getCompletedRequestsOfCitizenOrBuilding(ICitizenData?) Collection~IRequest~?~~
  + cancelAllRequestsOfCitizenOrBuilding(ICitizenData?) void
  + onPlayerEnterBuilding(Player) void
  + serializeToView(FriendlyByteBuf, boolean) void
  + requestRepair(BlockPos) void
  + onUpgradeComplete(Blueprint?, int) void
   IRequester requester
   boolean pendingConstruction
   List~IItemHandler~ handlers
   String customBuildingName
   Set~ICitizenData~ allAssignedCitizen
   boolean built
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   int maxEquipmentLevel
   boolean guardBuildingNear
   ImmutableCollection~IRequestResolver~?~~ resolvers
   String customName
   Map~TypeToken~?~, Collection~IToken~?~~~ openRequestsByRequestableType
   BuildingEntry buildingType
   String buildingDisplayName
}
class IBuildingContainer {
<<Interface>>
  + deserializeNBT(CompoundTag) void
  + alterPickUpPriority(int) void
  + removeContainerPosition(BlockPos) void
  + registerBlockPosition(Block, BlockPos, Level) void
  + getCapability(Capability~T~, Direction) LazyOptional~T~
  + serializeNBT() CompoundTag
  + addContainerPosition(BlockPos) void
  + registerBlockPosition(BlockState, BlockPos, Level) void
   List~BlockPos~ containers
   int pickUpPriority
   AbstractTileEntityColonyBuilding tileEntity
}
class IBuildingDataManager {
<<Interface>>
  + createFrom(IColony, AbstractTileEntityColonyBuilding) IBuilding
  + createFrom(IColony, CompoundTag) IBuilding
  + createViewFrom(IColonyView, BlockPos, FriendlyByteBuf) IBuildingView
  + openBuildingBrowser(Block) void
  + createFrom(IColony, BlockPos, ResourceLocation) IBuilding
   IBuildingDataManager instance
}
class IBuildingDeliveryman {
<<Interface>>

}
class IBuildingEventDescription {
<<Interface>>
  + toDisplayString() String
   int level
   String buildingName
}
class IBuildingEventsModule {
<<Interface>>
  + onUpgradeComplete(int) void
  + onDestroyed() void
  + onPlayerEnterBuilding(Player) void
  + onWakeUp() void
}
class IBuildingExtension {
<<Interface>>
  + equals(Object) boolean
  + getSqDistance(IBuildingView) int
  + resetOwningBuilding() void
  + deserialize(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + registerModule(IBuildingExtensionModule) void
  + isValidPlacement(IColony) boolean
  + hashCode() int
  + serialize(FriendlyByteBuf) void
   BlockPos position
   ExtensionId id
   BlockPos? buildingId
   BuildingExtensionEntry buildingExtensionType
   boolean taken
   BlockPos building
}
class IBuildingExtensionModule {
<<Interface>>
   IBuildingExtension buildingExtension
}
class IBuildingModule {
<<Interface>>
  + serializeToView(FriendlyByteBuf, boolean) void
  + serializeToView(FriendlyByteBuf) void
   IBuilding building
   ModuleProducer producer
}
class IBuildingModuleView {
<<Interface>>
  + deserialize(FriendlyByteBuf) void
   IColonyView colony
   ResourceLocation iconResourceLocation
   ModuleProducer~M, V~ producer
   BOWindow window
   String icon
   IBuildingView buildingView
   IColonyView colonyView
   boolean pageVisible
   Component? desc
}
class IBuildingRegistry {
<<Interface>>
   IForgeRegistry~BuildingEntry~ instance
}
class IBuildingView {
<<Interface>>
  + getOpenRequestsOfTypeFiltered(ICitizenDataView, Class~R~, Predicate~IRequest~R~~) ImmutableList~IRequest~R~~
  + hasWorkOrder() boolean
  + getBuildingMaxLevel() int
  + openGui(boolean) void
  + getOpenRequestsOfType(ICitizenDataView, Class~R~) ImmutableList~IRequest~R~~
  + allowsAssignment() boolean
  + deserialize(FriendlyByteBuf) void
  + getOpenRequests(ICitizenDataView) ImmutableList~IRequest~?~~
   ImmutableList~IRequest~?~~ openRequestsOfBuilding
   boolean repairing
   int range
   Map~Integer, Collection~IToken~?~~~ openRequestsByCitizen
   int claimRadius
   BlockPos parent
   BlockPos ID
   boolean building
   boolean buildingMaxLevel
   boolean deconstructing
   BOWindow window
   String structurePath
   String customName
   Set~Integer~ allAssignedCitizens
   String buildingDisplayName
   IColonyView colony
   int rotation
   int buildingDmPrio
   String hoverWarningForLevel
   String structurePack
   int currentWorkOrderLevel
   boolean mirrored
   ImmutableCollection~IToken~?~~ resolverIds
   boolean deconstructed
   BuildingEntry buildingType
}
class IBuildingWorker {
<<Interface>>
  + assignCitizen(ICitizenData) boolean
  + canWorkDuringTheRain() boolean
  + canEat(ItemStack) boolean
  + createJob(ICitizenData) IJob~?~
  + isItemStackInRequest(ItemStack?) boolean
   Skill primarySkill
   Skill recipeImprovementSkill
   HiringMode hiringMode
   List~IItemHandler~ handlers
   int maxEquipmentLevel
   String jobName
   Skill secondarySkill
}
class IBuildingWorkerModule {
<<Interface>>
  + canWorkDuringTheRain() boolean
  + createJob(ICitizenData) IJob~?~
   Skill primarySkill
   Skill secondarySkill
   JobEntry jobEntry
}
class IBuildingWorkerView {
<<Interface>>
  + removeWorkerId(int) void
  + addWorkerId(int) void
  + hasEnoughWorkers() boolean
   Skill primarySkill
   String jobDisplayName
   HiringMode hiringMode
   List~Integer~ workerId
   Skill secondarySkill
   String jobName
}
class ICitizenEventDescription {
<<Interface>>
  + toDisplayString() String
   String citizenName
}
class IColonyCampFireRaidEvent {
<<Interface>>
   int campFireTime
}
class IColonyConnectionManager {
<<Interface>>
  + addNewConnectionNode(BlockPos) boolean
  + triggerConnectionEvent(ConnectionEvent) void
  + tick() void
  + addNewGateHouse(BlockPos) void
  + attemptEstablishConnection(BlockPos, IColony) boolean
  + getColonyDiplomacyStatus(int) DiplomacyStatus
  + removeConnectionNode(BlockPos) void
  + getNode(BlockPos) ColonyConnectionNode
  + removeGateHouse(BlockPos) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeFromView(FriendlyByteBuf) void
   List~ConnectionEvent~ connectionEvents
   TreeMap~Integer, ColonyConnection~ indirectlyConnectedColonies
   TreeMap~Integer, ColonyConnection~ directlyConnectedColonies
}
class IColonyEntitySpawnEvent {
<<Interface>>
  + unregisterEntity(Entity) void
  + registerEntity(Entity) void
  + onEntityDeath(LivingEntity) void
   List~Entity~ entities
}
class IColonyEvent {
<<Interface>>
  + onStart() void
  + onNightFall() void
  + onUpdate() void
  + onFinish() void
  + onTileEntityBreak(BlockEntity) void
   ResourceLocation eventTypeID
   IColony colony
   EventStatus status
   int ID
}
class IColonyEventDescription {
<<Interface>>
  + toDisplayString() String
  + serialize(FriendlyByteBuf) void
  + deserialize(FriendlyByteBuf) void
  + includeInSummary() boolean
   String name
   ResourceLocation eventTypeId
   String summaryTranslationKey
   int day
   BlockPos eventPos
}
class IColonyRaidEvent {
<<Interface>>
  + addSpawner(BlockPos) void
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   boolean raidActive
   EntityType~?~ bossRaiderType
   List~BlockPos~ wayPoints
}
class IColonySpawnEvent {
<<Interface>>
   BlockPos spawnPoint
   BlockPos spawnPos
}
class IColonyStructureSpawnEvent {
<<Interface>>
   int maxRaiderCount
   String shipDesc
   List~Tuple~String, BlockPos~~ schematicSpawns
}
class ICommonBuilding {
<<Interface>>
   int buildingLevelEquivalent
   BlockPos position
   List~BlockPos~ containers
   int prestige
   IColony colony
   BuildingEntry buildingType
   int buildingLevel
}
class ICommonSettingsModule {
<<Interface>>
  + getSetting(ISettingKey~T~) T?
  + trigger(ISettingKey~?~) void
  + updateSetting(ISettingKey~?~, ISetting~?~, ServerPlayer) void
}
class ICraftingBuildingModule {
<<Interface>>
  + holdsRecipe(IToken~?~) boolean
  + canRecipeBeAdded(IToken~?~) boolean
  + getFirstRecipe(ItemStack) IRecipeStorage?
  + getFirstRecipe(Predicate~ItemStack~) IRecipeStorage?
  + checkForWorkerSpecificRecipes() void
  + replaceRecipe(IToken~?~, IToken~?~) void
  + updateWorkerAvailableForRecipes() void
  + getCraftingTool(AbstractEntityCitizen) ItemStack
  + getAdditionalRecipesForDisplayPurposesOnly(Level) List~IGenericRecipe~
  + getFirstFulfillableRecipe(Predicate~ItemStack~, int, boolean) IRecipeStorage
  + toggle(int) void
  + canLearn(CraftingType) boolean
  + improveRecipe(IRecipeStorage, int, ICitizenData) void
  + isRecipeCompatible(IGenericRecipe) boolean
  + switchOrder(int, int, boolean) void
  + clearRecipes() void
  + addRecipeToList(IToken~?~, boolean) void
  + addRecipe(IToken~?~) boolean
  + getCraftingLuck(AbstractEntityCitizen) float
  + getUid(JobEntry, String) ResourceLocation
  + fullFillRecipe(IRecipeStorage) boolean
  + removeRecipe(IToken~?~) void
  + isDisabled(IToken~?~) boolean
  + canLearnManyRecipes() boolean
   List~IToken~?~~ recipes
   IJob~?~? craftingJob
   ResourceLocation? uid
   List~ResourceLocation~ additionalLootTables
   boolean visible
   String customRecipeKey
   String id
   Set~CraftingType~ supportedCraftingTypes
   OptionalPredicate~ItemStack~ ingredientValidator
}
class ICraftingSetting {
<<Interface>>
  + getValue(IBuildingView) IRecipeStorage
  + set(IRecipeStorage) void
  + getSettings(IBuildingView) List~ItemStack~
  + getValue(IBuilding) IRecipeStorage
  + getSettings(IBuilding) List~ItemStack~
}
class ICreatesResolversModule {
<<Interface>>
  + createResolvers() List~IRequestResolver~?~~
}
class IDefinesCoreBuildingStatsModule {
<<Interface>>
   IStat~Integer~ maxInhabitants
}
class IEntityListModule {
<<Interface>>
  + isEntityInList(ResourceLocation) boolean
  + addEntity(ResourceLocation) void
  + removeEntity(ResourceLocation) void
   String id
   String listIdentifier
   List~ResourceLocation~ list
}
class IEntityListModuleView {
<<Interface>>
  + isAllowedEntity(ResourceLocation) boolean
  + removeEntity(ResourceLocation) void
  + clearEntities() void
  + addEntity(ResourceLocation) void
   int size
   String id
   boolean inverted
}
class IGuardBuilding {
<<Interface>>
  + int PATROL_DISTANCE
  + getGuardPos(AbstractEntityCitizen) BlockPos
  + shallPatrolManually() boolean
  + getNextPatrolTarget(boolean) BlockPos?
  + addPatrolTarget(BlockPos) void
  + resetPatrolTargets() void
  + requiresManualTarget() boolean
  + calculateMobs() void
  + arrivedAtPatrolPoint(AbstractEntityCitizen) void
  + checkIfGuardShouldTakeDamage(AbstractEntityCitizen, Player) boolean
  + shallRetrieveOnLowHealth() boolean
   BlockPos minePos
   BlockPos positionToFollow
   BlockPos guardPos
   int PATROL_DISTANCE
   String task
   BlockPos tempNextPatrolPoint
   Player playerToFollowOrRally
   Player playerToFollow
   boolean tightGrouping
   int bonusVision
   ILocation rallyLocation
}
class IHasRequiredItemsModule {
<<Interface>>
  + reservedStacks() Map~ItemStorage, Integer~
  + reservedStacksExcluding(IRequest~IDeliverable~?) Map~ItemStorage, Integer~
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
}
class IIntSettingFactory~T~ {
<<Interface>>
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) T
  + getNewInstance(int, int) T
}
class IItemListModule {
<<Interface>>
  + resetToDefaults() void
  + removeItem(ItemStorage) void
  + isItemInList(ItemStorage) boolean
  + clearItems() void
  + addItem(ItemStorage) void
   String id
   String listIdentifier
   List~ItemStorage~ list
}
class IItemListModuleView {
<<Interface>>
  + isAllowedItem(ItemStorage) boolean
  + addItem(ItemStorage) void
  + removeItem(ItemStorage) void
  + clearItems() void
   int size
   Function~IBuildingView, Set~ItemStorage~~ allItems
   String id
   boolean inverted
}
class IMinimumStockModule {
<<Interface>>
  + removeMinimumStock(ItemStack) void
  + isStocked(ItemStack) boolean
  + addMinimumStock(ItemStack, int) void
}
class IMinimumStockModuleView {
<<Interface>>
  + hasReachedLimit() boolean
   List~Tuple~ItemStorage, Integer~~ stock
}
class IModuleContainerView {
<<Interface>>
  + getModuleViews(Class~T~) List~T~
  + hasModuleView(ModuleProducer) boolean
  + registerModule(IBuildingModuleView) void
  + getModuleViewByType(Class~T~) T
  + getModuleView(ModuleProducer~M, V~) V
  + getModuleViewMatching(Class~T~, Predicate~T~) T?
  + getModuleView(int) IBuildingModuleView
   List~IBuildingModuleView~ allModuleViews
}
class IModuleWithExternalBlocks {
<<Interface>>
  + onBlockPlacedInBuilding(BlockState, BlockPos, Level) void
   List~BlockPos~ registeredBlocks
}
class IMysticalSite {
<<Interface>>

}
class IPersistentModule {
<<Interface>>
  + deserializeNBT(CompoundTag) void
  + serializeNBT(CompoundTag) void
}
class IPlantationModule {
<<Interface>>
  + getNextWorkingPosition(Level) BlockPos?
  + equals(Object) boolean
  + getValidWorkingPositions(Level, List~BlockPos~) List~BlockPos~
  + decideFieldWork(Level, BlockPos) Builder
  + applyBonemeal(AbstractEntityCitizen, BlockPos, ItemStack, Player) void
  + getPlantingBlockState(Level, BlockPos, BlockState) BlockState
  + getPositionToWalkTo(Level, BlockPos) BlockPos
  + hashCode() int
   Item item
   String fieldTag
   int actionLimit
   String workTag
   ResourceLocation requiredResearchEffect
   int plantsToRequest
   List~Item~ validBonemeal
   EquipmentTypeEntry requiredTool
   List~ItemStack~ requiredItemsForOperation
}
class IRSComponent {
<<Interface>>

}
class IRecipeSettingFactory~T~ {
<<Interface>>
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) T
  + getNewInstance(IToken~?~, String) T
}
class ISchematicProvider {
<<Interface>>
  + setCorners(BlockPos, BlockPos) void
  + clearDirty() void
  + setDeconstructed() void
  + onUpgradeSchematicTo(String, String, IBlueprintDataProviderBE) void
  + hasParent() boolean
  + markDirty() void
   int rotation
   Set~BlockPos~ children
   boolean isMirrored
   String schematicName
   String structurePack
   String blueprintPath
   boolean mirrored
   BlockPos parent
   Tuple~BlockPos, BlockPos~ corners
   BlockPos ID
   boolean deconstructed
   boolean dirty
   int maxBuildingLevel
   int buildingLevel
}
class ISetting~S~ {
<<Interface>>
  + isActive(ISettingsModuleView) boolean
  + trigger() void
  + shouldHideWhenInactive() boolean
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + updateSetting(ISetting~?~) void
  + isActive(ISettingsModule) boolean
  + onUpdate(IBuilding, ServerPlayer) void
  + copyValue(ISetting~?~) void
  + setHoverPane(ISettingKey~?~, Pane, ICommonSettingsModule) void
   ResourceLocation layoutItem
   Component? inactiveReason
   Component? toolTipText
   S value
}
class ISettingKey~T~ {
<<Interface>>
   Class~T~ type
   ResourceLocation uniqueId
}
class ISettingsModule {
<<Interface>>
  + getOptionalSetting(ISettingKey~T~) Optional~T~
  + getSettingValueOrDefault(ISettingKey~T~, S) S
  + with(ISettingKey~?~, ISetting~?~) ISettingsModule
}
class ISettingsModuleView {
<<Interface>>

}
class IStat~N~ {
<<Interface>>
  + apply(N) N
}
class IStringSetting~S~ {
<<Interface>>
  + set(S) void
   List~String~ settings
   S default
   int currentIndex
   S value
}
class IStringSettingFactory~T~ {
<<Interface>>
  + getNewInstance(List~String~, int) T
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) T
}
class ITickingModule {
<<Interface>>
  + onColonyTick(IColony) void
}
class ITownHall {
<<Interface>>
  + addPermissionEvent(PermissionEvent) void
  + removePermissionEvents(UUID) void
}
class ITownHallView {
<<Interface>>
  + canPlayerUseTP() boolean
   List~PermissionEvent~ permissionEvents
   List~IColonyEventDescription~ colonyEvents
}
class IWareHouse {
<<Interface>>
  + hasContainerPosition(BlockPos) boolean
  + canAccessWareHouse(ICitizenData) boolean
  + upgradeContainers(Level) void
   AbstractTileEntityWareHouse tileEntity
}
class ModBuildings {
  - ModBuildings() 
}
class PendingConnectionNode {
  + PendingConnectionNode(BlockPos) 
  + PendingConnectionNode(BlockPos, PathResult~PathJobMoveToLocation~, PendingConnectionType) 
  - PathResult~PathJobMoveToLocation~ cachedPathResult
  + write() CompoundTag
  + read(CompoundTag) void
   PendingConnectionType pendingConnectionType
   PathResult~PathJobMoveToLocation~? cachedPathResult
}

AbstractBuildingModule  ..>  IBuildingModule 
AbstractBuildingModule "1" *--> "building 1" IBuilding 
AbstractBuildingModuleView  ..>  IBuildingModuleView 
AbstractBuildingModuleView "1" *--> "buildingView 1" IBuildingView 
ColonyConnection "1" *--> "diplomacyStatus 1" DiplomacyStatus 
IAltersBuildingFootprint  -->  IAssignsCitizen 
IAltersRequiredItems  -->  IBuildingModule 
IAssignmentModuleView  -->  IBuildingModuleView 
IAssignsCitizen  -->  IBuildingModule 
IAssignsJob  -->  IAssignsCitizen 
IBlockSettingFactory~T~  ..>  ISetting~S~ 
IBoolSettingFactory~T~  ..>  ISetting~S~ 
IBuilding  -->  IBuildingContainer 
IBuilding  -->  ICommonBuilding 
IBuildingContainer  -->  ISchematicProvider 
IBuildingDeliveryman  -->  IBuilding 
IBuildingEventDescription  -->  IColonyEventDescription 
IBuildingEventsModule  -->  IBuildingModule 
IBuildingView  -->  ICommonBuilding 
IBuildingView  -->  IModuleContainerView 
IBuildingWorker  -->  IBuilding 
IBuildingWorkerView  -->  IBuildingView 
ICitizenEventDescription  -->  IColonyEventDescription 
IColonyEntitySpawnEvent  -->  IColonySpawnEvent 
IColonyRaidEvent  -->  IColonyEntitySpawnEvent 
IColonySpawnEvent  -->  IColonyEvent 
IColonyStructureSpawnEvent  -->  IColonyEvent 
ICraftingBuildingModule  -->  IBuildingModule 
ICraftingSetting  -->  ISetting~S~ 
ICreatesResolversModule  -->  IBuildingModule 
IDefinesCoreBuildingStatsModule  -->  IBuildingModule 
IEntityListModuleView  -->  IBuildingModuleView 
IGuardBuilding  -->  IBuilding 
IHasRequiredItemsModule  -->  IBuildingModule 
IIntSettingFactory~T~  ..>  ISetting~S~ 
IItemListModuleView  -->  IBuildingModuleView 
IMinimumStockModule  -->  IBuildingModule 
IMinimumStockModuleView  -->  IBuildingModuleView 
IModuleWithExternalBlocks  -->  IBuildingModule 
IMysticalSite  -->  IBuilding 
IPersistentModule  -->  IBuildingModule 
IPlantationModule  -->  IBuildingExtensionModule 
IRecipeSettingFactory~T~  ..>  ISetting~S~ 
ISettingsModule  -->  IBuildingModule 
ISettingsModule  -->  ICommonSettingsModule 
ISettingsModuleView  -->  IBuildingModuleView 
ISettingsModuleView  -->  ICommonSettingsModule 
IStringSettingFactory~T~  ..>  ISetting~S~ 
IStringSetting~S~  -->  ISetting~S~ 
ITickingModule  -->  IBuildingModule 
ITownHall  -->  IBuilding 
ITownHallView  -->  IBuildingView 
IWareHouse  -->  IBuilding 
PendingConnectionNode  -->  ColonyConnectionNode 
```
