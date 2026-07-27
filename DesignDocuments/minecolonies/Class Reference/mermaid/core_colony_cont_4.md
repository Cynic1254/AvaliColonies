# core.colony (cont. 4)

48 classes, 38 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractBuildingEvent {
  + AbstractBuildingEvent() 
  + AbstractBuildingEvent(boolean, BlockPos, String, int) 
  - BlockPos eventPos
  - int level
  - String buildingName
  + toString() String
  + deserialize(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  + serialize(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
   int level
   String buildingName
   BlockPos eventPos
}
class AbstractCitizenEvent {
  + AbstractCitizenEvent(boolean, BlockPos, String) 
  + AbstractCitizenEvent() 
  - BlockPos eventPos
  - String citizenName
  + deserializeNBT(CompoundTag) void
  + toString() String
  + serialize(FriendlyByteBuf) void
  + deserialize(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
   String citizenName
   BlockPos eventPos
}
class AbstractEvent {
  + AbstractEvent(boolean) 
  + AbstractEvent() 
  - int day
  + includeInSummary() boolean
  + deserialize(FriendlyByteBuf) void
  + serialize(FriendlyByteBuf) void
  + toString() String
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
   int day
}
class AbstractShipRaidEvent {
  + AbstractShipRaidEvent(IColony) 
  # int maxRaiderCount
  # BlockPos spawnPoint
  # List~BlockPos~ wayPoints
  # EventStatus status
  # int shipRotation
  # IColony colony
  # ShipSize shipSize
  + serializeNBT() CompoundTag
  + registerEntity(Entity) void
  + onFinish() void
  + onUpdate() void
  + onTileEntityBreak(BlockEntity) void
  + addSpawner(BlockPos) void
  + onNightFall() void
  + unregisterEntity(Entity) void
  + onEntityDeath(LivingEntity) void
  - checkRaidEnd() void
  + deserializeNBT(CompoundTag) void
  # updateRaidBar() void
  + onStart() void
   BlockPos spawnPoint
   List~Tuple~String, BlockPos~~ schematicSpawns
   int shipRotation
   List~Entity~ entities
   IColony colony
   EventStatus status
   int maxRaiderCount
   boolean raidActive
   BlockPos spawnPos
   ShipSize shipSize
   int ID
   PathResult spawnPath
   List~BlockPos~ wayPoints
   MutableComponent displayName
   boolean underWater
   int maxRaiders
}
class AmazonRaidEvent {
  + AmazonRaidEvent(IColony) 
  + loadFromNBT(IColony, CompoundTag) AmazonRaidEvent
  # updateRaidBar() void
  + onEntityDeath(LivingEntity) void
  + onUpdate() void
  + registerEntity(Entity) void
   MutableComponent displayName
   ResourceLocation eventTypeID
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class BarbarianRaidEvent {
  + BarbarianRaidEvent(IColony) 
  + loadFromNBT(IColony, CompoundTag) BarbarianRaidEvent
  + registerEntity(Entity) void
  + onEntityDeath(LivingEntity) void
   MutableComponent displayName
   ResourceLocation eventTypeID
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class BuildingBuiltEvent {
  + BuildingBuiltEvent() 
  + BuildingBuiltEvent(BlockPos, String) 
  + loadFromFriendlyByteBuf(FriendlyByteBuf) BuildingBuiltEvent
  + loadFromNBT(CompoundTag) BuildingBuiltEvent
   String name
   ResourceLocation eventTypeId
}
class BuildingDeconstructedEvent {
  + BuildingDeconstructedEvent(BlockPos, String, int) 
  + BuildingDeconstructedEvent() 
  + loadFromNBT(CompoundTag) BuildingDeconstructedEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) BuildingDeconstructedEvent
   String name
   ResourceLocation eventTypeId
}
class BuildingRepairedEvent {
  + BuildingRepairedEvent(BlockPos, String, int) 
  + BuildingRepairedEvent() 
  + loadFromNBT(CompoundTag) BuildingRepairedEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) BuildingRepairedEvent
   String name
   ResourceLocation eventTypeId
}
class BuildingUpgradedEvent {
  + BuildingUpgradedEvent(BlockPos, String, int) 
  + BuildingUpgradedEvent() 
  + loadFromNBT(CompoundTag) BuildingUpgradedEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) BuildingUpgradedEvent
   String name
   ResourceLocation eventTypeId
}
class CitizenBornEvent {
  + CitizenBornEvent() 
  + CitizenBornEvent(BlockPos, String) 
  + loadFromFriendlyByteBuf(FriendlyByteBuf) CitizenBornEvent
  + loadFromNBT(CompoundTag) CitizenBornEvent
   String name
   ResourceLocation eventTypeId
   String summaryTranslationKey
}
class CitizenDiedEvent {
  + CitizenDiedEvent(BlockPos, String, String) 
  + CitizenDiedEvent() 
  - String deathCause
  + loadFromFriendlyByteBuf(FriendlyByteBuf) CitizenDiedEvent
  + serialize(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  + loadFromNBT(CompoundTag) CitizenDiedEvent
  + deserialize(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
   String name
   ResourceLocation eventTypeId
   String summaryTranslationKey
   String deathCause
}
class CitizenGrownUpEvent {
  + CitizenGrownUpEvent(BlockPos, String) 
  + CitizenGrownUpEvent() 
  + loadFromNBT(CompoundTag) CitizenGrownUpEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) CitizenGrownUpEvent
   String name
   ResourceLocation eventTypeId
   String summaryTranslationKey
}
class CitizenSpawnedEvent {
  + CitizenSpawnedEvent(BlockPos, String) 
  + CitizenSpawnedEvent() 
  + loadFromNBT(CompoundTag) CitizenSpawnedEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) CitizenSpawnedEvent
   String name
   ResourceLocation eventTypeId
}
class CustomRecipe {
  - CustomRecipe() 
  + CustomRecipe(String, int, int, boolean, boolean, ResourceLocation, Set~ResourceLocation~, Set~ResourceLocation~, ResourceLocation?, EquipmentTypeEntry, List~ItemStorage~, ItemStack, List~ItemStack~, List~ItemStack~, Block) 
  - String crafter
  - ResourceLocation lootTable
  - List~ItemStorage~ inputs
  - Block intermediate
  - boolean mustExist
  - List~ItemStack~ altOutputs
  - ResourceLocation recipeId
  - Set~ResourceLocation~ excludedResearchIds
  - EquipmentTypeEntry requiredTool
  - boolean showTooltip
  + isValidForBuilding(IBuilding) boolean
  - isUnlockEffectResearched(ResourceLocation, IColony) boolean
  - isPrecursorRecipeMissing(IBuilding) boolean
  + parseTemplate(ResourceLocation, JsonObject) List~CustomRecipe~
  + deserialize(FriendlyByteBuf) CustomRecipe
  - serializeIds(FriendlyByteBuf, Set~ResourceLocation~) void
  - parseArrayOrStringFilter(JsonElement?, boolean) Predicate~ResourceLocation~
  + serialize(FriendlyByteBuf) void
  - populateTemplateItem(JsonObject, String, ResourceLocation) Tuple~Boolean, String~
  + parse(ResourceLocation, JsonObject) CustomRecipe
  + equals(Object) boolean
  + hashCode() int
  - deserializeIds(FriendlyByteBuf) Set~ResourceLocation~
  - populateTemplate(ResourceLocation, JsonObject, ResourceLocation, boolean) JsonObject?
   IRecipeStorage recipeStorage
   List~ItemStack~ altOutputs
   List~ItemStack~ secondaryOutput
   ResourceLocation recipeId
   String crafter
   Set~ResourceLocation~ requiredResearchIds
   boolean mustExist
   EquipmentTypeEntry requiredTool
   boolean showTooltip
   ResourceLocation lootTable
   Set~ResourceLocation~ excludedResearchIds
   int minBuildingLevel
   List~ItemStorage~ inputs
   int maxBuildingLevel
   Block intermediate
   ItemStack primaryOutput
}
class CustomRecipeManager {
  - CustomRecipeManager() 
  - CustomRecipeManager instance
  + addRecipeTemplate(ResourceLocation, JsonObject) void
  + getRecipes(String) Set~CustomRecipe~
  + getRecipeByOutput(ItemStorage) List~CustomRecipe~
  + sendCustomRecipeManagerPackets(ServerPlayer) void
  + removeRecipe(ResourceLocation) void
  + reset() void
  - serializeNetworkData(FriendlyByteBuf) void
  + getRecipeByOutput(ItemStack) List~CustomRecipe~
  + getLootDrops(ResourceLocation?) List~LootDrop~
  + getRecipeByOutput(Item) List~CustomRecipe~
  + buildLootData(LootDataManager, Level) void
  + handleCustomRecipeManagerMessage(FriendlyByteBuf) void
  + addRecipe(CustomRecipe) void
  - removeRecipes() void
  + resolveTemplates() void
   CustomRecipeManager instance
   Map~String, Map~ResourceLocation, CustomRecipe~~ allRecipes
}
class CustomRecipeManagerMessage {
  + CustomRecipeManagerMessage(FriendlyByteBuf) 
  + CustomRecipeManagerMessage() 
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
   LogicalSide? executionSide
}
class DrownedPirateRaidEvent {
  + DrownedPirateRaidEvent(IColony) 
  + loadFromNBT(IColony, CompoundTag) IColonyEvent
  + onStart() void
  # updateRaidBar() void
   EntityType~?~ normalRaiderType
   MutableComponent displayName
   ResourceLocation eventTypeID
   String shipDesc
   boolean underWater
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class EgyptianRaidEvent {
  + EgyptianRaidEvent(IColony) 
  + onUpdate() void
  + loadFromNBT(IColony, CompoundTag) EgyptianRaidEvent
  + onEntityDeath(LivingEntity) void
  + onStart() void
  # updateRaidBar() void
  + registerEntity(Entity) void
   MutableComponent displayName
   ResourceLocation eventTypeID
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class GenericRecipeUtils {
  - GenericRecipeUtils() 
  - getResearchDisplayName(ResourceLocation) Component
  + create(CustomRecipe, IRecipeStorage) IGenericRecipe
  + filterInputs(IGenericRecipe, OptionalPredicate~ItemStack~) IGenericRecipe
  - isDomumRecipe(IGenericRecipe) boolean
  + calculateRestrictions(CustomRecipe) List~Component~
}
class Horde {
  + Horde(int) 
  + loadFromNbt(CompoundTag) Horde
  + writeToNbt(CompoundTag) void
   int messageID
}
class HordeRaidEvent {
  + HordeRaidEvent(IColony) 
  # EventStatus status
  - BlockPos spawnPoint
  - List~BlockPos~ wayPoints
  # Horde horde
  - IColony colony
  - int campFireTime
  # sendHordeMessage() void
  + onNightFall() void
  + serializeNBT() CompoundTag
  + onEntityDeath(LivingEntity) void
  + onUpdate() void
  + unregisterEntity(Entity) void
  # spawnHorde(BlockPos, IColony, int, int, int, int) void
  + onFinish() void
  - prepareEvent() void
  - announceWin() void
  + addSpawner(BlockPos) void
  + deserializeNBT(CompoundTag) void
  + onStart() void
  # updateRaidBar() void
  - spawnCampFires(BlockPos) void
   BlockPos spawnPoint
   BlockPos randomCampfire
   Horde horde
   List~Entity~ entities
   int ID
   PathResult spawnPath
   List~BlockPos~ wayPoints
   MutableComponent displayName
   IColony colony
   EventStatus status
   int campFireTime
   BlockPos spawnPos
}
class IInteractionResponseHandlerRegistry {
<<Interface>>
   IForgeRegistry~InteractionResponseHandlerEntry~ instance
}
class ImmutableItemStorageFactory {
  + ImmutableItemStorageFactory() 
  + deserialize(IFactoryController, CompoundTag) ImmutableItemStorage
  + getNewInstance(ItemStack, int) ImmutableItemStorage
  + serialize(IFactoryController, ImmutableItemStorage, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) ImmutableItemStorage
  + serialize(IFactoryController, ImmutableItemStorage) CompoundTag
   TypeToken~ImmutableItemStorage~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class InteractionResponseHandlerManager {
  + InteractionResponseHandlerManager() 
  + createFrom(ICitizen, CompoundTag) IInteractionResponseHandler?
}
class ItemStorageFactory {
  + ItemStorageFactory() 
  + getNewInstance(ItemStack, int, boolean, boolean) ItemStorage
  + deserialize(IFactoryController, CompoundTag) ItemStorage
  + serialize(IFactoryController, ItemStorage) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) ItemStorage
  + serialize(IFactoryController, ItemStorage, FriendlyByteBuf) void
   TypeToken~ItemStorage~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class LootTableAnalyzer {
  - LootTableAnalyzer() 
  - adjustModifier(float, JsonArray) float
  - conditionsSeemImpossible(JsonArray) boolean
  - processNumber(JsonElement?, float) float
  + toDrops(LootDataManager?, LootTable) List~LootDrop~
  + consolidate(List~LootDrop~) List~LootDrop~
  - entryToDrops(LootDataManager?, JsonObject) List~LootDrop~
  + toDrops(LootDataManager?, JsonObject) List~LootDrop~
  - processFunctions(ItemStack, JsonArray) Tuple~ItemStack, Float~
  - processCount(JsonElement?) Tuple~Integer, Float~
  + toDrops(LootDataManager, ResourceLocation) List~LootDrop~
  - expandAdventureToken(LootDataManager, ItemStack) List~LootDrop~
  - processNumber(JsonElement?, int) int
}
class NorsemenRaidEvent {
  + NorsemenRaidEvent(IColony) 
  # updateRaidBar() void
  + loadFromNBT(IColony, CompoundTag) NorsemenRaidEvent
  + registerEntity(Entity) void
  + onEntityDeath(LivingEntity) void
   MutableComponent displayName
   ResourceLocation eventTypeID
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class NorsemenShipRaidEvent {
  + NorsemenShipRaidEvent(IColony) 
  + loadFromNBT(IColony, CompoundTag) IColonyEvent
  # updateRaidBar() void
   MutableComponent displayName
   ResourceLocation eventTypeID
   String shipDesc
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class PirateGroundRaidEvent {
  + PirateGroundRaidEvent(IColony) 
  + onStart() void
  + onEntityDeath(LivingEntity) void
  + registerEntity(Entity) void
  + loadFromNBT(IColony, CompoundTag) PirateGroundRaidEvent
  + onUpdate() void
  # updateRaidBar() void
   MutableComponent displayName
   ResourceLocation eventTypeID
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class PirateRaidEvent {
  + PirateRaidEvent(IColony) 
  + loadFromNBT(IColony, CompoundTag) IColonyEvent
   MutableComponent displayName
   ResourceLocation eventTypeID
   String shipDesc
   EntityType~?~ normalRaiderType
   EntityType~?~ archerRaiderType
   EntityType~?~ bossRaiderType
}
class PosBasedInteraction {
  + PosBasedInteraction(Component, IChatPriority, Component, BlockPos) 
  + PosBasedInteraction(Component, IChatPriority, BlockPos) 
  + PosBasedInteraction(ICitizen) 
  # loadValidator() void
  + genChildInteractions() List~IInteractionResponseHandler~
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + isValid(ICitizenData) boolean
   String type
}
class QuestDeliveryInteraction {
  + QuestDeliveryInteraction(ICitizen) 
  + QuestDeliveryInteraction(Component, IChatPriority, ResourceLocation, int, ICitizenData) 
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + onServerResponseTriggered(int, Player, ICitizenData) void
  + onOpened(Player) void
  - triggerResponseState(Player, IQuestObjectiveTemplate) void
   ResourceLocation interactionIcon
   String type
}
class QuestDialogueInteraction {
  + QuestDialogueInteraction(ICitizen) 
  + QuestDialogueInteraction(Component, IChatPriority, ResourceLocation, int, ICitizenData) 
  - processText(Component) Component
  + onClosed() void
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + onOpened(Player) void
  + deserializeNBT(CompoundTag) void
  + isValid(ICitizenData) boolean
  + serializeNBT() CompoundTag
  + isVisible(Level) boolean
  + onServerResponseTriggered(int, Player, ICitizenData) void
   Component id
   Component inquiry
   List~Component~ possibleResponses
   String type
   ResourceLocation interactionIcon
}
class RaidManager {
  + RaidManager(Colony) 
  - boolean spiesEnabled
  - int nightsSinceLastRaid
  + onRaiderDeath(AbstractEntityMinecoloniesRaider) void
  + canHaveRaiderEvents() boolean
  - isOtherColony(int, int) boolean
  + isValidSpawnPoint(Collection~IBuilding~, BlockPos) boolean
  + write(CompoundTag) void
  - determineRaidForNextDay() void
  + setPassThroughRaid() void
  + calculateRaiderAmount(int) int
  + willRaidTonight() boolean
  + onRaidEventFinished(IColonyRaidEvent) void
  + calculateSpawnLocation() BlockPos
  + onNightFall() void
  + canRaid() boolean
  + onLostCitizen(ICitizenData) void
  + areSpiesEnabled() boolean
  - raidThisNight(Level, IColony) boolean
  + read(CompoundTag) void
  - createSpawnPath(BlockPos, boolean) PathResult~?~
  - findSpawnPointInDirections(BlockPos, BlockPos) BlockPos?
  + raiderEvent(RaidSettings) RaidSpawnResult
   List~RaidHistory~ allRaids
   List~BlockPos~ lastSpawnPoints
   boolean raided
   BlockPos randomBuilding
   RaidSettings raidNextNight
   int nightsSinceLastRaid
   int lostCitizen
   boolean canHaveRaiderEvents
   double raidDifficultyModifier
   int colonyRaidLevel
   boolean spiesEnabled
   RaidHistory lastRaid
}
class RaiderConstants {
  - RaiderConstants() 
}
class RecipeAnalyzer {
  - RecipeAnalyzer() 
  + createAnimals(Level) List~Animal~
  + findRecipes(Map~CraftingType, List~IGenericRecipe~~, ICraftingBuildingModule, Level) List~IGenericRecipe~
  + findRecipes(List~Animal~, AnimalHerdingModule) List~IGenericRecipe~
  + buildVanillaRecipesMap(RecipeManager, Level) Map~CraftingType, List~IGenericRecipe~~
}
class RecipeStorageFactory {
  + RecipeStorageFactory() 
  + serialize(IFactoryController, RecipeStorage, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) RecipeStorage
  + serialize(IFactoryController, RecipeStorage) CompoundTag
  + deserialize(IFactoryController, CompoundTag) RecipeStorage
   TypeToken~RecipeStorage~ factoryOutputType
   short serializationId
   TypeToken~IToken~?~~ factoryInputType
}
class RecruitmentInteraction {
  + RecruitmentInteraction(Component, IChatPriority) 
  + RecruitmentInteraction(ICitizen) 
  + genChildInteractions() List~IInteractionResponseHandler~
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + onServerResponseTriggered(int, Player, ICitizenData) void
  + onWindowOpened(BOWindow, ICitizenDataView) void
   ResourceLocation interactionIcon
   String type
}
class RequestBasedInteraction {
  + RequestBasedInteraction(Component, IChatPriority, Component, IToken~?~) 
  + RequestBasedInteraction(ICitizen) 
  + RequestBasedInteraction(Component, IChatPriority, IToken~?~) 
  + onServerResponseTriggered(int, Player, ICitizenData) void
  + isValid(ICitizenData) boolean
  + serializeNBT() CompoundTag
  + genChildInteractions() List~IInteractionResponseHandler~
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + deserializeNBT(CompoundTag) void
  # loadValidator() void
  + onWindowOpened(BOWindow, ICitizenDataView) void
   String type
}
class ServerCitizenInteraction {
  + ServerCitizenInteraction(Component, boolean, IChatPriority, Predicate~ICitizenData~, Component, Tuple~Component, Component~[]) 
  + ServerCitizenInteraction(ICitizen) 
  + isVisible(Level) boolean
  + removeParent(Component) void
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + isValid(ICitizenData) boolean
  + onServerResponseTriggered(int, Player, ICitizenData) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  # loadValidator() void
  + addParent(Component) void
  - tryHandleIgnoreResponse(Component, Player) void
   Component id
}
class ShipBasedRaiderUtils {
  - ShipBasedRaiderUtils() 
  + canPlaceShipAt(BlockPos, Blueprint, Level) boolean
  + isSurfaceAreaMostlyMaterial(List~Predicate~BlockState~~, Level, int, BlockPos, BlockPos, double) boolean
  + getLoadedPositionTowardsCenter(BlockPos, IColony, int, BlockPos, int, int, boolean) BlockPos?
  + findSpawnPosOnShip(BlockPos, Level, int) BlockPos
  + createWaypoints(Level, Path, int) List~BlockPos~
  + canSpawnShipAt(IColony, BlockPos, int, int, String, int) boolean
  + canSpawnShipAt(IColony, BlockPos, int, int, String) boolean
  + setupSpawner(BlockPos, Level, EntityType~?~, IColonyRaidEvent, int) void
  + getLoadedPositionTowardsCenter(BlockPos, IColony, int, BlockPos, int, int) BlockPos
  + chooseWaypointFor(List~BlockPos~, BlockPos, BlockPos) BlockPos
  + spawnPirateShip(BlockPos, IColony, Blueprint, IColonyRaidEvent) boolean
  + canPlaceShipAt(BlockPos, Blueprint, Level, int) boolean
}
class ShipSize {
<<enumeration>>
  - ShipSize(int, String, int, int, int, int, int) 
  + valueOf(String) ShipSize
  + getShipForRaiderAmount(int) ShipSize
  + values() ShipSize[]
}
class SimpleNotificationInteraction {
  + SimpleNotificationInteraction(Component, IChatPriority) 
  + onServerResponseTriggered(int, Player, ICitizenData) void
  - onResponse(int) void
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + isValid(ICitizenData) boolean
   String type
}
class StandardInteraction {
  + StandardInteraction(Component, Component, IChatPriority) 
  + StandardInteraction(Component, IChatPriority) 
  + StandardInteraction(ICitizen) 
  + genChildInteractions() List~IInteractionResponseHandler~
   String type
}
class ToolUsage {
  + ToolUsage(EquipmentTypeEntry, List~List~ItemStack~~, List~List~ItemStack~~) 
  + create(EquipmentTypeEntry) ToolUsage
  + tool() EquipmentTypeEntry
  + enchantedToolLevels() List~List~ItemStack~~
  + toolLevels() List~List~ItemStack~~
}
class ToolsAnalyzer {
  + ToolsAnalyzer() 
  - tryEnchantStack(ItemStack, Enchantment, int) void
  - tryAddingToolWithLevel(Map~EquipmentTypeEntry, ToolUsage~, EquipmentTypeEntry, ItemStack) void
  + findTools() List~ToolUsage~
  - tryAddingEnchantedTool(Map~EquipmentTypeEntry, ToolUsage~, EquipmentTypeEntry, ItemStack, int) void
}
class VisitorSpawnedEvent {
  + VisitorSpawnedEvent(BlockPos, String) 
  + VisitorSpawnedEvent() 
  + loadFromNBT(CompoundTag) VisitorSpawnedEvent
  + loadFromFriendlyByteBuf(FriendlyByteBuf) VisitorSpawnedEvent
   String name
   ResourceLocation eventTypeId
}

AbstractBuildingEvent  -->  AbstractEvent 
AbstractCitizenEvent  -->  AbstractEvent 
AbstractShipRaidEvent "1" *--> "shipSize 1" ShipSize 
AmazonRaidEvent  -->  HordeRaidEvent 
BarbarianRaidEvent  -->  HordeRaidEvent 
BuildingBuiltEvent  -->  AbstractBuildingEvent 
BuildingDeconstructedEvent  -->  AbstractBuildingEvent 
BuildingRepairedEvent  -->  AbstractBuildingEvent 
BuildingUpgradedEvent  -->  AbstractBuildingEvent 
CitizenBornEvent  -->  AbstractCitizenEvent 
CitizenDiedEvent  -->  AbstractCitizenEvent 
CitizenGrownUpEvent  -->  AbstractCitizenEvent 
CitizenSpawnedEvent  -->  AbstractCitizenEvent 
CustomRecipeManager  ..>  CustomRecipeManagerMessage : «create»
DrownedPirateRaidEvent  -->  AbstractShipRaidEvent 
EgyptianRaidEvent  -->  HordeRaidEvent 
HordeRaidEvent "1" *--> "horde 1" Horde 
NorsemenRaidEvent  -->  HordeRaidEvent 
NorsemenShipRaidEvent  -->  AbstractShipRaidEvent 
PirateGroundRaidEvent  -->  HordeRaidEvent 
PirateRaidEvent  -->  AbstractShipRaidEvent 
PosBasedInteraction  -->  ServerCitizenInteraction 
QuestDeliveryInteraction  -->  QuestDialogueInteraction 
QuestDialogueInteraction  -->  StandardInteraction 
RaidManager  ..>  AmazonRaidEvent : «create»
RaidManager  ..>  BarbarianRaidEvent : «create»
RaidManager  ..>  DrownedPirateRaidEvent : «create»
RaidManager  ..>  EgyptianRaidEvent : «create»
RaidManager  ..>  Horde : «create»
RaidManager  ..>  NorsemenRaidEvent : «create»
RaidManager  ..>  NorsemenShipRaidEvent : «create»
RaidManager  ..>  PirateGroundRaidEvent : «create»
RaidManager  ..>  PirateRaidEvent : «create»
RecruitmentInteraction  -->  ServerCitizenInteraction 
RequestBasedInteraction  -->  ServerCitizenInteraction 
SimpleNotificationInteraction  -->  StandardInteraction 
StandardInteraction  -->  ServerCitizenInteraction 
VisitorSpawnedEvent  -->  AbstractCitizenEvent 
```
