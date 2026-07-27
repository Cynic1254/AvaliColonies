# core.colony

61 classes, 62 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractAssignedCitizenModule {
  + AbstractAssignedCitizenModule() 
  - HiringMode hiringMode
  # List~ICitizenData~ assignedCitizen
  + removeCitizen(ICitizenData) boolean
  + onDestroyed() void
  + hasAssignedCitizen() boolean
  ~ onRemoval(ICitizenData) void
  + deserializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + assignCitizen(ICitizenData) boolean
  + hasAssignedCitizen(ICitizenData) boolean
  ~ onAssignment(ICitizenData) void
  + serializeNBT(CompoundTag) void
   List~ICitizenData~ assignedCitizen
   ICitizenData? firstCitizen
   HiringMode hiringMode
   List~Optional~AbstractEntityCitizen~~? assignedEntities
   String moduleSerializationIdentifier
   boolean full
}
class AbstractBuildingExtensionModule {
  # AbstractBuildingExtensionModule(BuildingExtensionEntry, BlockPos) 
  - List~IBuildingExtensionModule~ modules
  - BlockPos position
  - BlockPos? buildingId
  + deserializeNBT(CompoundTag) void
  + resetOwningBuilding() void
  + getSqDistance(IBuildingView) int
  + equals(Object) boolean
  + hashCode() int
  + serializeNBT() CompoundTag
  + deserialize(FriendlyByteBuf) void
  + serialize(FriendlyByteBuf) void
  + registerModule(IBuildingExtensionModule) void
   BlockPos position
   boolean taken
   BlockPos building
   List~IBuildingExtensionModule~ modules
   Class~IBuildingExtensionModule~ classType
   ExtensionId id
   BlockPos? buildingId
   BuildingExtensionEntry buildingExtensionType
}
class AbstractCraftingBuildingModule {
  + AbstractCraftingBuildingModule(JobEntry) 
  # List~IToken~?~~ recipes
  - getPendingRequestQueueExcluding(IRequest~IDeliverable~?) List~Tuple~IRecipeStorage, Integer~~
  + removeRecipe(IToken~?~) void
  + getFirstRecipe(ItemStack) IRecipeStorage?
  + deserializeNBT(CompoundTag) void
  + onColonyTick(IColony) void
  + addRecipeToList(IToken~?~, boolean) void
  + serializeNBT(CompoundTag) void
  + getFirstRecipe(Predicate~ItemStack~) IRecipeStorage?
  + getAdditionalRecipesForDisplayPurposesOnly(Level) List~IGenericRecipe~
  + toggle(int) void
  + getCraftingLuck(AbstractEntityCitizen) float
  + improveRecipe(IRecipeStorage, int, ICitizenData) void
  + canRecipeBeAdded(IToken~?~) boolean
  + handleRecipeUpdate(IToken~?~) void
  + getFirstFulfillableRecipe(Predicate~ItemStack~, int, boolean) IRecipeStorage
  # getWarehouseCount(ItemStorage) int
  + holdsRecipe(IToken~?~) boolean
  + reservedStacksExcluding(IRequest~IDeliverable~?) Map~ItemStorage, Integer~
  + switchOrder(int, int, boolean) void
  + canLearnManyRecipes() boolean
  # isPreTaughtRecipe(IRecipeStorage, Map~ResourceLocation, CustomRecipe~) boolean
  + getCraftingTool(AbstractEntityCitizen) ItemStack
  + fullFillRecipe(IRecipeStorage) boolean
  - anyChildRequestIs(IRequestManager, IRequest~?~, IRequest~?~) boolean
  + replaceRecipe(IToken~?~, IToken~?~) void
  + setBuilding(IBuilding) IBuildingModule
  # isRecipeCompatibleWithCraftingModule(IToken~?~) boolean
  + addRecipe(IToken~?~) boolean
  - hasSpaceForMoreRecipes() boolean
  + serializeToView(FriendlyByteBuf, boolean) void
  + isDisabled(IToken~?~) boolean
  + createResolvers() List~IRequestResolver~?~~
  + checkForWorkerSpecificRecipes() void
  + clearRecipes() void
  + updateWorkerAvailableForRecipes() void
   List~IToken~?~~ recipes
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   IJob~?~? craftingJob
   boolean visible
   int maxRecipes
   String customRecipeKey
   int activeRecipes
   String id
   OptionalPredicate~ItemStack~ ingredientValidator
}
class AbstractDOCraftingBuildingModule {
  # AbstractDOCraftingBuildingModule(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   Set~CraftingType~ supportedCraftingTypes
}
class AnimalHerdingModule {
  + AnimalHerdingModule(JobEntry, Predicate~Animal~, ItemStorage) 
  + getRecipesForDisplayPurposesOnly(Animal) List~IGenericRecipe~
  + isCompatible(Animal) boolean
  + getLootTables(Animal) List~ResourceLocation~
  + reservedStacksExcluding(IRequest~IDeliverable~?) Map~ItemStorage, Integer~
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   List~ItemStorage~ breedingItems
   IJob~?~ herdingJob
}
class BarracksStatisticsModule {
  + BarracksStatisticsModule() 
  + serializeToView(FriendlyByteBuf, boolean) void
}
class BedHandlingModule {
  + BedHandlingModule() 
  + onBlockPlacedInBuilding(BlockState, BlockPos, Level) void
  + serializeNBT(CompoundTag) void
  + onWakeUp() void
  + removeBed(BlockPos) void
  + deserializeNBT(CompoundTag) void
   List~BlockPos~ registeredBlocks
}
class BeekeeperCollectionSetting {
  + BeekeeperCollectionSetting(List~String~, int) 
  + BeekeeperCollectionSetting(String[]) 
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + isIndexAllowed(int) boolean
}
class BlockSetting {
  + BlockSetting(BlockItem) 
  + BlockSetting(BlockItem, BlockItem) 
  - BlockItem value
  + copyValue(ISetting~?~) void
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
   BlockItem value
   ResourceLocation layoutItem
   BlockItem default
}
class BoolSetting {
  + BoolSetting(boolean) 
  + BoolSetting(boolean, boolean) 
  - boolean value
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + copyValue(ISetting~?~) void
  + trigger() void
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
   ResourceLocation layoutItem
   Boolean value
   boolean default
}
class BuilderModeSetting {
  + BuilderModeSetting(List~String~, int) 
  + BuilderModeSetting() 
  + getActualValue(IBuilding) String
  + isActive(ISettingsModule) boolean
  + isActive(ISettingsModuleView) boolean
   Component displayText
   Component? inactiveReason
   Component? toolTipText
}
class BuildingExtensionDataManager {
  - BuildingExtensionDataManager() 
  + compoundToExtension(CompoundTag) IBuildingExtension
  + resourceLocationToExtension(ResourceLocation, BlockPos) IBuildingExtension?
  + extensionToBuffer(IBuildingExtension) FriendlyByteBuf
  + extensionToCompound(IBuildingExtension) CompoundTag
  + bufferToExtension(FriendlyByteBuf) IBuildingExtension
}
class BuildingExtensionsModule {
  + BuildingExtensionsModule() 
  + hasNoExtensions() boolean
  + getMatchingExtension(Predicate~IBuildingExtension~) List~IBuildingExtension~
  + canAssignExtension(IBuildingExtension) boolean
  + resetCurrentExtension() void
  # canAssignExtensionOverride(IBuildingExtension) boolean
  + freeExtension(IBuildingExtension) void
  + onColonyTick(IColony) void
  + claimExtensions() void
  + deserializeNBT(CompoundTag) void
  + markDirty() void
  + serializeToView(FriendlyByteBuf) void
  + serializeNBT(CompoundTag) void
  + assignExtension(IBuildingExtension) boolean
  + assignManually() boolean
   List~IBuildingExtension~ ownedExtensions
   boolean assignManually
   List~IBuildingExtension~ freeExtensions
   IBuildingExtension? currentExtension
   int maxExtensionCount
   Class~?~ expectedExtensionType
   IBuildingExtension? extensionToWorkOn
}
class BuildingModules {
  + BuildingModules() 
}
class BuildingResourcesModule {
  + BuildingResourcesModule() 
  - Map~String, BuildingBuilderResource~ neededResources
  - int totalStages
  + serializeToView(FriendlyByteBuf) void
  - updateAvailableResources() void
  + addNeededResource(ItemStack?, int) void
  + requiresResourceForBuilding(ItemStack) boolean
  + serializeNBT(CompoundTag) void
  + checkOrRequestBucket(BuilderBucket?, ICitizenData) void
  + reduceNeededResource(ItemStack, int) void
  + nextStage() void
  + deserializeNBT(CompoundTag) void
  + resetNeededResources() void
  + getResourceFromIdentifier(String) BuildingBuilderResource
   BuilderBucket? nextBucket
   Map~String, BuildingBuilderResource~ neededResources
   int totalStages
   BuilderBucket? requiredResources
}
class BuildingStatisticsModule {
  + BuildingStatisticsModule() 
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf, boolean) void
  + deserializeNBT(CompoundTag) void
  + increment(String) void
  + incrementBy(String, int) void
   IStatisticsManager buildingStatisticsManager
}
class ChildrenBuildingModule {
  + ChildrenBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  + onColonyTick(IColony) void
}
class ColonyConnectionModule {
  + ColonyConnectionModule() 
  + serializeToView(FriendlyByteBuf) void
}
class CourierAssignmentModule {
  + CourierAssignmentModule() 
  + onAssignment(ICitizenData) void
  + onRemoval(ICitizenData) void
  + onColonyTick(IColony) void
  + deserializeNBT(CompoundTag) void
  + serializeNBT(CompoundTag) void
   int moduleMax
   String moduleSerializationIdentifier
   JobEntry jobEntry
}
class CrafterRecipeSetting {
  + CrafterRecipeSetting() 
  + CrafterRecipeSetting(List~String~, int) 
  + isActive(ISettingsModule) boolean
  + isActive(ISettingsModuleView) boolean
   Component? inactiveReason
}
class CraftingWorkerBuildingModule {
  + CraftingWorkerBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~, Skill, Skill) 
  + CraftingWorkerBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  - Skill recipeImprovementSkill
   Skill recipeImprovementSkill
   Skill craftSpeedSkill
}
class DeliverymanAssignmentModule {
  + DeliverymanAssignmentModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  ~ onRemoval(ICitizenData) void
}
class DynamicTreesSetting {
  + DynamicTreesSetting() 
  + DynamicTreesSetting(int, int) 
  + isActive(ISettingsModuleView) boolean
  + isActive(ISettingsModule) boolean
  + shouldHideWhenInactive() boolean
}
class EnchanterStationsModule {
  + EnchanterStationsModule() 
  + serializeToView(FriendlyByteBuf) void
  + removeWorker(BlockPos) void
  + onWakeUp() void
  + serializeNBT(CompoundTag) void
  - serializeListElement(Entry~BlockPos, Boolean~) CompoundTag
  + deserializeNBT(CompoundTag) void
  + addWorker(BlockPos) void
  - deserializeListElement(CompoundTag) Tuple~BlockPos, Boolean~
   BlockPos asGathered
   BlockPos? randomBuildingToDrainFrom
   Set~BlockPos~ buildingsToGatherFrom
}
class EntityListModule {
  + EntityListModule(String) 
  - String id
  + addEntity(ResourceLocation) void
  + isEntityInList(ResourceLocation) boolean
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  + removeEntity(ResourceLocation) void
   ImmutableList~ResourceLocation~ list
   String id
   String listIdentifier
}
class ExpeditionLog {
  + ExpeditionLog() 
  - int id
  - Map~ItemStorage, ItemStorage~ loot
  - Status status
  - List~ItemStack~ equipment
  - Map~EntityType~?~, Integer~ mobs
  - String name
  + deserialize(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  + serialize(FriendlyByteBuf) void
  + setKilled() void
  + addMob(EntityType~?~) void
  + serializeNBT(CompoundTag) void
  + addLoot(List~ItemStack~) void
  + reset() void
  + getStat(StatType) double
   String? name
   List~Tuple~EntityType~?~, Integer~~ mobs
   List~ItemStack~ equipment
   int id
   AbstractEntityCitizen? citizen
   List~ItemStorage~ loot
   Status status
}
class ExpeditionLogModule {
  + ExpeditionLogModule(ResourceLocation?) 
  - ExpeditionLog log
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
   ExpeditionLog log
}
class FarmField {
  + FarmField(BuildingExtensionEntry, BlockPos) 
  - Stage fieldStage
  - ItemStack seed
  + create(BlockPos, Level) FarmField
  + getRadius(Direction) int
  + serializeNBT() CompoundTag
  + setRadius(Direction, int) void
  + isValidPlacement(IColony) boolean
  + deserializeNBT(CompoundTag) void
  - isValidDelimiter(Block) boolean
  + deserialize(FriendlyByteBuf) void
  + serialize(FriendlyByteBuf) void
  + nextState() void
  + isNoPartOfField(Level, BlockPos) boolean
   Stage fieldStage
   ItemStack seed
}
class FurnaceUserModule {
  + FurnaceUserModule() 
  - List~BlockPos~ furnaces
  + isAllowedFuel(ItemStack) boolean
  + onBlockPlacedInBuilding(BlockState, BlockPos, Level) void
  + deserializeNBT(CompoundTag) void
  + serializeNBT(CompoundTag) void
  + removeFromFurnaces(BlockPos) void
  + alterItemsToBeKept(TriConsumer~Predicate~ItemStack~, Integer, Boolean~) void
   List~BlockPos~ furnaces
   List~BlockPos~ registeredBlocks
}
class GraveyardManagementModule {
  + GraveyardManagementModule() 
  - GraveData? lastGraveData
  + deserializeNBT(CompoundTag) void
  + serializeNBT(CompoundTag) void
  + hasRestingCitizen(Set~String~) boolean
  + serializeToView(FriendlyByteBuf) void
  + buryCitizenHere(Tuple~BlockPos, Direction~, AbstractEntityCitizen) void
   GraveData lastGraveData
}
class GuardBuildingModule {
  + GuardBuildingModule(GuardType, boolean, Function~IBuilding, Integer~) 
  ~ onRemoval(ICitizenData) void
  + onColonyTick(IColony) void
  ~ onAssignment(ICitizenData) void
   boolean full
}
class GuardFollowModeSetting {
  + GuardFollowModeSetting() 
  + GuardFollowModeSetting(List~String~, int) 
  + shouldHideWhenInactive() boolean
  + isActive(ISettingsModule) boolean
  + isActive(ISettingsModuleView) boolean
}
class GuardPatrolModeSetting {
  + GuardPatrolModeSetting() 
  + GuardPatrolModeSetting(List~String~, int) 
  + isActive(ISettingsModule) boolean
  + shouldHideWhenInactive() boolean
  + isActive(ISettingsModuleView) boolean
}
class GuardTaskSetting {
  + GuardTaskSetting() 
  + GuardTaskSetting(String[]) 
  + GuardTaskSetting(List~String~, int) 
  - setPatrolMineHelpLabel(ButtonImage, View) void
  # getButtonWidth(ISettingsModuleView) int
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + onUpdate(IBuilding, ServerPlayer) void
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
   ResourceLocation layoutItem
}
class HomeBuildingModule {
  + HomeBuildingModule() 
   IStat~Integer~ maxInhabitants
}
class HospitalAssignmentModule {
  + HospitalAssignmentModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  ~ onRemoval(ICitizenData) void
}
class IntSetting {
  + IntSetting(int) 
  + IntSetting(int, int) 
  - int value
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + copyValue(ISetting~?~) void
   int default
   ResourceLocation layoutItem
   Integer value
}
class ItemListModule {
  + ItemListModule(String, ItemStorage[]) 
  + ItemListModule(String) 
  - String id
  + serializeNBT(CompoundTag) void
  + removeItem(ItemStorage) void
  + resetToDefaults() void
  + serializeToView(FriendlyByteBuf) void
  + isItemInList(ItemStorage) boolean
  + deserializeNBT(CompoundTag) void
  + addItem(ItemStorage) void
  + clearItems() void
   ImmutableList~ItemStorage~ list
   String id
   String listIdentifier
}
class LivingBuildingModule {
  + LivingBuildingModule() 
  + deserializeNBT(CompoundTag) void
  + onColonyTick(IColony) void
  + onUpgradeComplete(int) void
  + serializeNBT(CompoundTag) void
  ~ onRemoval(ICitizenData) void
  ~ onAssignment(ICitizenData) void
   int moduleMax
   String moduleSerializationIdentifier
}
class LumberjackAssignmentModule {
  + LumberjackAssignmentModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~, Skill, Skill) 
  ~ onRemoval(ICitizenData) void
}
class MinerBuildingModule {
  + MinerBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
   boolean full
}
class MinerLevelManagementModule {
  + MinerLevelManagementModule() 
  - MineNode? oldNode
  - int startingLevelShaft
  - int currentLevel
  - MineNode? activeNode
  - List~MinerLevel~ levels
  + getLevelId(MinerLevel) int
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + addLevel(MinerLevel) void
  + repairLevel(int) void
  + deserializeNBT(CompoundTag) void
   int numberOfLevels
   MinerLevel? currentLevel
   MineNode? activeNode
   List~MinerLevel~ levels
   MineNode? oldNode
   int startingLevelShaft
}
class MinimumStockModule {
  + MinimumStockModule() 
  - minimumStockSize() int
  + isStocked(ItemStack) boolean
  + deserializeNBT(CompoundTag) void
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + addMinimumStock(ItemStack, int) void
  + alterItemsToBeKept(TriConsumer~Predicate~ItemStack~, Integer, Boolean~) void
  + onColonyTick(IColony) void
  - getMatchingRequest(ItemStack, Collection~IToken~?~~) IToken~?~?
  + removeMinimumStock(ItemStack) void
}
class NoPrivateCrafterWorkerModule {
  + NoPrivateCrafterWorkerModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  + createResolvers() List~IRequestResolver~?~~
}
class PlantationField {
  + PlantationField(BuildingExtensionEntry, BlockPos) 
  - List~BlockPos~ workingPositions
  + serializeNBT() CompoundTag
  + create(BuildingExtensionEntry, BlockPos) PlantationField
  + deserializeNBT(CompoundTag) void
  + serialize(FriendlyByteBuf) void
  + isValidPlacement(IColony) boolean
  + deserialize(FriendlyByteBuf) void
   List~BlockPos~ workingPositions
   IPlantationModule module
}
class QuarryModule {
  + QuarryModule(int) 
  - boolean isFinished
  - resetProgress(ICitizenData) void
  + setFinished() void
  + onColonyTick(IColony) void
  + onAssignment(ICitizenData) void
  + serializeNBT(CompoundTag) void
  + onRemoval(ICitizenData) void
  + deserializeNBT(CompoundTag) void
  + createResolvers() List~IRequestResolver~?~~
   int moduleMax
   boolean isFinished
   JobEntry jobEntry
   Tuple~BlockPos, BlockPos~ additionalCorners
   String moduleSerializationIdentifier
}
class RecipeSetting {
  + RecipeSetting(String) 
  + RecipeSetting(IToken~?~, String) 
  + shouldHideWhenInactive() boolean
  + isActive(ISettingsModule) boolean
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + getValue(IBuildingView) IRecipeStorage
  + getSettings(IBuildingView) List~ItemStack~
  + set(IRecipeStorage) void
  + copyValue(ISetting~?~) void
  + getSettings(IBuilding) List~ItemStack~
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  + isActive(ISettingsModuleView) boolean
  + getValue(IBuilding) IRecipeStorage
   IToken~?~ value
   ResourceLocation layoutItem
}
class RestaurantMenuModule {
  + RestaurantMenuModule(boolean, Function~IBuilding, Integer~) 
  - Function~IBuilding, Integer~ expectedStock
  # Set~ItemStorage~ menu
  + addMenuItem(ItemStack) void
  + removeMenuItem(ItemStack) void
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  - getMatchingRequest(ItemStack, Collection~IToken~?~~) IToken~?~?
  + onColonyTick(IColony) void
  + alterItemsToBeKept(TriConsumer~Predicate~ItemStack~, Integer, Boolean~) void
   Set~ItemStorage~ menu
   int expectedStock
}
class SettingKey~T~ {
  + SettingKey(Class~T~, ResourceLocation) 
  - Class~T~ type
  + hashCode() int
  + equals(Object) boolean
   Class~T~ type
   ResourceLocation uniqueId
}
class SettingsFactories {
  + SettingsFactories() 
}
class SettingsModule {
  + SettingsModule() 
  + with(ISettingKey~?~, ISetting~?~) ISettingsModule
  + serializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + updateSetting(ISettingKey~?~, ISetting~?~, ServerPlayer) void
  + getSettingValueOrDefault(ISettingKey~T~, S) S
  + getOptionalSetting(ISettingKey~T~) Optional~T~
  + deserializeNBT(CompoundTag) void
  + getSetting(ISettingKey~T~) T?
}
class SimpleCraftingModule {
  + SimpleCraftingModule(JobEntry) 
  + canLearnManyRecipes() boolean
  + createResolvers() List~IRequestResolver~?~~
   IJob~?~? craftingJob
   Set~CraftingType~ supportedCraftingTypes
}
class StableCavalryBuildingModule {
  + StableCavalryBuildingModule(GuardType, boolean, Function~IBuilding, Integer~) 
   boolean full
}
class StringSetting {
  + StringSetting(List~String~, int) 
  + StringSetting(String[]) 
  - List~String~ settings
  # int currentIndex
  + copyValue(ISetting~?~) void
  + isIndexAllowed(int) boolean
  + trigger() void
  + setupHandler(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
  # getButtonWidth(ISettingsModuleView) int
  + updateSetting(ISetting~?~) void
  + set(String) void
  + render(ISettingKey~?~, Pane, ICommonSettingsModule, IBuildingView, BOWindow) void
   Component displayText
   String default
   ResourceLocation layoutItem
   List~String~ settings
   int currentIndex
   String value
}
class StringSettingWithDesc {
  + StringSettingWithDesc(List~String~, int) 
  + StringSettingWithDesc(String[]) 
}
class TavernBuildingModule {
  + TavernBuildingModule() 
  - List~Integer~ externalCitizens
  - List~BlockPos~ workPositions
  - List~BlockPos~ sitPositions
  - int noVisitorTime
  + initTagPositions() void
  + deserializeNBT(CompoundTag) void
  + removeCitizen(Integer) boolean
  + onPlayerEnterBuilding(Player) void
  - spawnVisitorInternal() void
  + onColonyTick(IColony) void
  + onDestroyed() void
  + serializeNBT(CompoundTag) void
  + spawnVisitor() IVisitorData?
  + onUpgradeComplete(int) void
   int noVisitorTime
   List~BlockPos~ sitPositions
   List~BlockPos~ workPositions
   List~Integer~ externalCitizens
   IStat~Integer~ maxInhabitants
   BlockPos workPos
   BlockPos freeSitPosition
}
class TavernLivingBuildingModule {
  + TavernLivingBuildingModule() 
   int moduleMax
}
class WarehouseModule {
  + WarehouseModule() 
  - int storageUpgrade
  + deserializeNBT(CompoundTag) void
  + serializeToView(FriendlyByteBuf) void
  + serializeNBT(CompoundTag) void
  + incrementStorageUpgrade() void
   int storageUpgrade
}
class WarehouseRequestQueueModule {
  + WarehouseRequestQueueModule() 
  + serializeNBT(CompoundTag) void
  + addRequest(IToken~?~) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
   List~IToken~?~~ mutableRequestList
}
class WorkAtHomeBuildingModule {
  + WorkAtHomeBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  + assignCitizen(ICitizenData) boolean
  ~ onRemoval(ICitizenData) void
}
class WorkerBuildingModule {
  + WorkerBuildingModule(JobEntry, Skill, Skill, boolean, Function~IBuilding, Integer~) 
  - JobEntry jobEntry
  + canWorkDuringTheRain() boolean
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  ~ onRemoval(ICitizenData) void
  + serializeNBT(CompoundTag) void
  + createJob(ICitizenData) IJob~?~
  ~ onAssignment(ICitizenData) void
  + onColonyTick(IColony) void
  + assignCitizen(ICitizenData) boolean
  + createResolvers() List~IRequestResolver~?~~
  + onUpgradeComplete(int) void
   int moduleMax
   Skill primarySkill
   String jobDisplayName
   JobEntry jobEntry
   Skill secondarySkill
   String moduleSerializationIdentifier
}

AbstractCraftingBuildingModule  ..>  SettingKey~T~ : «create»
BarracksStatisticsModule  -->  BuildingStatisticsModule 
BeekeeperCollectionSetting  -->  StringSetting 
BlockSetting  ..>  SettingKey~T~ : «create»
BuilderModeSetting  -->  StringSetting 
BuildingModules  ..>  AnimalHerdingModule : «create»
BuildingModules  ..>  BeekeeperCollectionSetting : «create»
BuildingModules  ..>  BlockSetting : «create»
BuildingModules  ..>  BoolSetting : «create»
BuildingModules  ..>  BuilderModeSetting : «create»
BuildingModules  ..>  ChildrenBuildingModule : «create»
BuildingModules  ..>  CrafterRecipeSetting : «create»
BuildingModules  ..>  CraftingWorkerBuildingModule : «create»
BuildingModules  ..>  DeliverymanAssignmentModule : «create»
BuildingModules  ..>  DynamicTreesSetting : «create»
BuildingModules  ..>  EntityListModule : «create»
BuildingModules  ..>  ExpeditionLogModule : «create»
BuildingModules  ..>  GuardBuildingModule : «create»
BuildingModules  ..>  GuardFollowModeSetting : «create»
BuildingModules  ..>  GuardPatrolModeSetting : «create»
BuildingModules  ..>  GuardTaskSetting : «create»
BuildingModules  ..>  HospitalAssignmentModule : «create»
BuildingModules  ..>  IntSetting : «create»
BuildingModules  ..>  ItemListModule : «create»
BuildingModules  ..>  LumberjackAssignmentModule : «create»
BuildingModules  ..>  MinerBuildingModule : «create»
BuildingModules  ..>  NoPrivateCrafterWorkerModule : «create»
BuildingModules  ..>  QuarryModule : «create»
BuildingModules  ..>  RecipeSetting : «create»
BuildingModules  ..>  RestaurantMenuModule : «create»
BuildingModules  ..>  SettingsModule : «create»
BuildingModules  ..>  SimpleCraftingModule : «create»
BuildingModules  ..>  StableCavalryBuildingModule : «create»
BuildingModules  ..>  StringSetting : «create»
BuildingModules  ..>  WorkAtHomeBuildingModule : «create»
BuildingModules  ..>  WorkerBuildingModule : «create»
ChildrenBuildingModule  -->  WorkerBuildingModule 
CourierAssignmentModule  -->  AbstractAssignedCitizenModule 
CrafterRecipeSetting  -->  StringSettingWithDesc 
CraftingWorkerBuildingModule  -->  WorkerBuildingModule 
DeliverymanAssignmentModule  -->  WorkerBuildingModule 
DynamicTreesSetting  -->  IntSetting 
ExpeditionLogModule  ..>  ExpeditionLog : «create»
ExpeditionLogModule "1" *--> "log 1" ExpeditionLog 
FarmField  -->  AbstractBuildingExtensionModule 
GuardBuildingModule  -->  WorkAtHomeBuildingModule 
GuardFollowModeSetting  -->  StringSettingWithDesc 
GuardPatrolModeSetting  -->  StringSettingWithDesc 
GuardTaskSetting  -->  StringSettingWithDesc 
HospitalAssignmentModule  -->  WorkerBuildingModule 
LivingBuildingModule  -->  AbstractAssignedCitizenModule 
LumberjackAssignmentModule  -->  CraftingWorkerBuildingModule 
MinerBuildingModule  -->  WorkerBuildingModule 
NoPrivateCrafterWorkerModule  -->  WorkerBuildingModule 
PlantationField  -->  AbstractBuildingExtensionModule 
QuarryModule  -->  AbstractAssignedCitizenModule 
SettingsModule  ..>  SettingKey~T~ : «create»
StableCavalryBuildingModule  -->  GuardBuildingModule 
StringSettingWithDesc  -->  StringSetting 
TavernLivingBuildingModule  -->  LivingBuildingModule 
WorkAtHomeBuildingModule  -->  WorkerBuildingModule 
WorkerBuildingModule  -->  AbstractAssignedCitizenModule 
```
