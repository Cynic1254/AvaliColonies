# external.dependencies (part 1)

80 classes, 20 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractBoolSettingFactory~T~ {
  + AbstractBoolSettingFactory() 
  + serialize(IFactoryController, T, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) T
  + serialize(IFactoryController, T) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) T
   TypeToken~FactoryVoidInput~ factoryInputType
}
class AbstractCraftingRequest~C~ {
  # AbstractCraftingRequest(IRequester, IToken~?~, RequestState, C) 
  # AbstractCraftingRequest(IRequester, IToken~?~, C) 
  + getResolverToolTip(IColonyView) List~MutableComponent~
   ItemStack taskStack
   MutableComponent displayPrefix
   ResourceLocation displayIcon
   List~ItemStack~ displayStacks
   Component shortDisplayString
   int displayCount
   String translationKey
   String displayIconFile
}
class AbstractCraftingRequestFactory~C, R~ {
  # AbstractCraftingRequestFactory(IObjectConstructor~C, R~, Class~C~, Class~R~, IObjectToNBTConverter~C~, INBTToObjectConverter~C~, IObjectToPackBufferWriter~C~, IFriendlyByteBufToObjectReader~C~) 
  + getNewInstance(C, IRequester, IToken~?~, RequestState) R
  + deserialize(IFactoryController, FriendlyByteBuf) R
  + serialize(IFactoryController, R, FriendlyByteBuf) void
  + serialize(IFactoryController, R) CompoundTag
  + deserialize(IFactoryController, CompoundTag) R
   TypeToken~R~ factoryOutputType
   TypeToken~C~ factoryInputType
}
class AbstractIntSettingFactory~T~ {
  + AbstractIntSettingFactory() 
  + serialize(IFactoryController, IntSetting) CompoundTag
  + deserialize(IFactoryController, CompoundTag) T
  + deserialize(IFactoryController, FriendlyByteBuf) T
  + serialize(IFactoryController, IntSetting, FriendlyByteBuf) void
   TypeToken~FactoryVoidInput~ factoryInputType
}
class AbstractStringSettingsFactory~T~ {
  + AbstractStringSettingsFactory() 
  + serialize(IFactoryController, StringSetting) CompoundTag
  + serialize(IFactoryController, StringSetting, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) T
  + deserialize(IFactoryController, FriendlyByteBuf) T
   TypeToken~FactoryVoidInput~ factoryInputType
}
class ActionHandlerResult {
<<enumeration>>
  + ActionHandlerResult() 
  + values() ActionHandlerResult[]
  + valueOf(String) ActionHandlerResult
}
class ActionToPerform {
<<enumeration>>
  - ActionToPerform(boolean) 
  + valueOf(String) ActionToPerform
  + increasesActionCount() boolean
  + values() ActionToPerform[]
}
class AddPlayer {
  + AddPlayer() 
  + AddPlayer(IColonyView, String) 
  + fromBytes(FriendlyByteBuf) void
  + toBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
   LogicalSide? executionSide
}
class AddPlayerOrFakePlayer {
  + AddPlayerOrFakePlayer() 
  + AddPlayerOrFakePlayer(IColonyView, String, UUID) 
  + fromBytes(FriendlyByteBuf) void
  + toBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
   LogicalSide? executionSide
}
class AddRank {
  + AddRank(IColonyView, String) 
  + AddRank() 
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
  + toBytes(FriendlyByteBuf) void
}
class AllOption {
  + AllOption() 
  + resolveValue(CommandSourceStack, String) List~Integer~
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
  + matches(String) boolean
}
class AlwaysDepthTestStateShard {
  - AlwaysDepthTestStateShard() 
}
class AnswerElement {
  + AnswerElement(Component, IQuestDialogueAnswer) 
  + parse(JsonObject) AnswerElement
}
class ArgumentOption~TValue~ {
<<Interface>>
  + resolveValue(CommandSourceStack, String) TValue
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
  + matches(String) boolean
}
class AssistantHammerPreview {
  + AssistantHammerPreview() 
  + isEnabled(WorldEventContext) boolean
  + getDesiredBlueprints(WorldEventContext) Map~BlockPos, PendingRenderData~
}
class BeekeeperCollectionSettingsFactory {
  + BeekeeperCollectionSettingsFactory() 
  + getNewInstance(List~String~, int) BeekeeperCollectionSetting
   TypeToken~BeekeeperCollectionSetting~ factoryOutputType
   short serializationId
}
class BiomeFilter {
  - BiomeFilter(Predicate~Holder~Biome~~) 
  + test(Holder~Biome~) boolean
  + filter() Predicate~Holder~Biome~~
  + or(BiomeFilter) BiomeFilter
  + parse(String) BiomeFilter
}
class BlockMiningProgressInstance {
  + BlockMiningProgressInstance(IQuestObjectiveTemplate) 
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
   boolean fulfilled
   int missingQuantity
}
class BlockPlacementProgressInstance {
  + BlockPlacementProgressInstance(PlaceBlockObjectiveTemplate) 
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   boolean fulfilled
   int missingQuantity
}
class BlockSettingFactory {
  + BlockSettingFactory() 
  + deserialize(IFactoryController, CompoundTag) BlockSetting
  + serialize(IFactoryController, BlockSetting, FriendlyByteBuf) void
  + serialize(IFactoryController, BlockSetting) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) BlockSetting
  + getNewInstance(BlockItem, BlockItem) BlockSetting
   TypeToken~BlockSetting~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class BlueprintCacheKey {
  - BlueprintCacheKey(String, String, RotationMirror) 
  + packName() String
  + path() String
  + orientation() RotationMirror
}
class BoolSettingFactory {
  + BoolSettingFactory() 
  + getNewInstance(boolean, boolean) BoolSetting
   TypeToken~BoolSetting~ factoryOutputType
   short serializationId
}
class BorderStyle {
<<enumeration>>
  - BorderStyle(String) 
  - String key
  + valueOf(String) BorderStyle
  + values() BorderStyle[]
   String key
}
class Box {
  - Box(BlockPos?, Tuple~BlockPos, BlockPos~?) 
  + anchor() BlockPos?
  + corners() Tuple~BlockPos, BlockPos~?
}
class BoxRenderData {
  - BoxRenderData(BoxPreviewData?, int) 
  + box() BoxPreviewData?
  + builder() int
}
class Brewing {
  + Brewing(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   String id
   Set~CraftingType~ supportedCraftingTypes
}
class BrewingModule {
  + BrewingModule(JobEntry) 
}
class BuildAttemptResult {
  - BuildAttemptResult(boolean, boolean) 
  + areBlocksToBuildNearby() boolean
  + didTryBuilding() boolean
}
class BuildGoggles {
  + BuildGoggles() 
  + isEnabled(WorldEventContext) boolean
  + getDesiredBlueprints(WorldEventContext) Map~BlockPos, PendingRenderData~
  - getBuilderId(IColonyView, BlockPos) int
}
class Builder {
  + Builder() 
  + clear(BlockPos) Builder
  + pickNewField() Builder
  + plant(BlockPos) Builder
  + bonemeal(BlockPos) Builder
  + pickNewPosition() Builder
  + harvest(BlockPos) Builder
  + build(IPlantationModule, BlockPos) PlantationModuleResult
}
class BuilderModeSettingFactory {
  + BuilderModeSettingFactory() 
  + getNewInstance(List~String~, int) BuilderModeSetting
   TypeToken~BuilderModeSetting~ factoryOutputType
   short serializationId
}
class BuildingExtensionEntry {
  - BuildingExtensionEntry(ResourceLocation, BiFunction~BuildingExtensionEntry, BlockPos, IBuildingExtension~, List~Function~IBuildingExtension, IBuildingExtensionModule~~) 
  - List~Function~IBuildingExtension, IBuildingExtensionModule~~ extensionModuleProducers
  - ResourceLocation registryName
  + produceExtension(BlockPos) IBuildingExtension
  + hashCode() int
  + equals(Object) boolean
   ResourceLocation registryName
   List~Function~IBuildingExtension, IBuildingExtensionModule~~ extensionModuleProducers
}
class BuildingInfo {
  - BuildingInfo(StructurePackMeta, String, Set~Integer~, BlockPos, boolean, boolean) 
  - boolean isParent
  - boolean isInvisible
  + levels() Set~Integer~
  - isInvisible(Blueprint) boolean
  + size() BlockPos
  + flattenLevels(List~BuildingInfo~) List~BuildingInfo~
  - getFlattened(List~BuildingInfo~) BuildingInfo
  + create(StructurePackMeta, Blueprint, boolean) BuildingInfo
  + path() String
  + pack() StructurePackMeta
   boolean isInvisible
   boolean isParent
}
class BuildingProgressInstance {
  + BuildingProgressInstance(BuildBuildingObjectiveTemplate, int) 
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   boolean fulfilled
   int missingQuantity
}
class BurnableRequest {
  ~ BurnableRequest(IRequester, IToken~?~, RequestState, Burnable) 
  ~ BurnableRequest(IRequester, IToken~?~, Burnable) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class BurnableRequestFactory {
  + BurnableRequestFactory() 
  + serialize(IFactoryController, BurnableRequest, FriendlyByteBuf) void
  + serialize(IFactoryController, BurnableRequest) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) BurnableRequest
  + deserialize(IFactoryController, CompoundTag) BurnableRequest
  + getNewInstance(Burnable, IRequester, IToken~?~, RequestState) BurnableRequest
   TypeToken~BurnableRequest~ factoryOutputType
   short serializationId
   TypeToken~Burnable~ factoryInputType
}
class ChangeEvent {
<<Interface>>
  + onChange(DyeColor) void
}
class ChangePlayerRank {
  + ChangePlayerRank() 
  + ChangePlayerRank(IColonyView, UUID, Rank) 
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
}
class ChildLootTableProvider {
  + ChildLootTableProvider(PackOutput) 
  # registerTables(LootTableRegistrar) void
   String name
}
class ChildRecipeProvider {
  + ChildRecipeProvider(PackOutput) 
  # registerRecipes(Consumer~FinishedRecipe~) void
   String name
}
class CitizenAddedSource {
<<enumeration>>
  + CitizenAddedSource() 
  + valueOf(String) CitizenAddedSource
  + values() CitizenAddedSource[]
}
class CitizenFoodStats {
  + CitizenFoodStats(int, int) 
  + diversity() int
  + quality() int
}
class CitizenRequestTreeWindowModule {
  - CitizenRequestTreeWindowModule(AbstractWindowSkeleton, ICitizenDataView) 
  + onFulfill(IRequest~?~) void
   Collection~IRequest~?~~ openRequests
}
class ClientEvents {
  + ClientEvents() 
  + onRecipesLoaded(RecipesUpdatedEvent) void
}
class ClientRegistration {
  + ClientRegistration() 
  + registerParticleFactories(RegisterParticleProvidersEvent) void
}
class ClientSuggester {
  + ClientSuggester() 
  - loadClientSuggestions(Object, SuggestionsBuilder, List~ArgumentOption~TValue~~) void
}
class ClipboardRequestTreeWindowModule {
  + ClipboardRequestTreeWindowModule(AbstractWindowSkeleton, Options) 
   Collection~IRequest~?~~ openRequests
}
class CloseUIDialogueAnswer {
  + CloseUIDialogueAnswer() 
  + applyToQuest(Player, IQuestInstance) void
}
class ColonyBorderOverlay {
  - ColonyBorderOverlay(ResourceKey~Level~, int, String, int, boolean, Set~ChunkPos~) 
  + ColonyBorderOverlay(ResourceKey~Level~, int) 
  + updateInfo(IColonyView?, boolean) boolean
  + updateChunks(Set~ChunkPos~, Set~ChunkPos~) boolean
  - updateInfo(String?, int, boolean, boolean) boolean
  + updatePending(Journeymap, ResourceKey~Level~, int, IColonyManager) void
  + unload(Journeymap) void
}
class ColonyIdOption {
  + ColonyIdOption() 
  + matches(String) boolean
  + resolveValue(CommandSourceStack, String) Integer
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
}
class ColonyInfo {
  + ColonyInfo(int) 
  - BlockPos center
  - int citizencount
  - String owner
  - String name
  - int id
  - int prestige
   String name
   int prestige
   String owner
   int id
   int citizencount
   BlockPos center
}
class CommonRegistration {
  + CommonRegistration() 
  + registerParticles(RegisterEvent) void
}
class Comparator {
  + Comparator() 
  + compare(EquipmentTypeEntry, EquipmentTypeEntry) int
}
class CrafterRecipeSettingFactory {
  + CrafterRecipeSettingFactory() 
  + getNewInstance(List~String~, int) CrafterRecipeSetting
   TypeToken~CrafterRecipeSetting~ factoryOutputType
   short serializationId
}
class Crafting {
  + Crafting(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   String id
   Set~CraftingType~ supportedCraftingTypes
}
class CraftingModule {
  + CraftingModule(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
   Set~CraftingType~ supportedCraftingTypes
   OptionalPredicate~ItemStack~ ingredientValidator
}
class CropRecipe {
  + CropRecipe(Block) 
  + source() Block
}
class Custom {
  + Custom(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   String id
   Set~CraftingType~ supportedCraftingTypes
}
class CustomRecipeBuilder {
  - CustomRecipeBuilder(String, String, String) 
  + showTooltip(boolean) CustomRecipeBuilder
  - storageAsJson(List~ItemStorage~) JsonArray
  + inputs(List~ItemStorage~) CustomRecipeBuilder
  + intermediate(Block) CustomRecipeBuilder
  + minResearchId(ResourceLocation) CustomRecipeBuilder
  + requiredTool(EquipmentTypeEntry) CustomRecipeBuilder
  - stackAsJson(List~ItemStack~) JsonArray
  + build(Consumer~FinishedRecipe~) void
  + alternateOutputs(List~ItemStack~) CustomRecipeBuilder
  + maxResearchId(ResourceLocation) CustomRecipeBuilder
  + minBuildingLevel(int) CustomRecipeBuilder
  + secondaryOutputs(List~ItemStack~) CustomRecipeBuilder
  + lootTable(ResourceLocation) CustomRecipeBuilder
  + maxBuildingLevel(int) CustomRecipeBuilder
  + result(ItemStack) CustomRecipeBuilder
  + mustExist(boolean) CustomRecipeBuilder
  + create(String, String, String) CustomRecipeBuilder
  - stackAsJson(ItemStack) JsonObject
}
class CustomVisitorData {
  + CustomVisitorData() 
  + applyToVisitor(IVisitorData) void
}
class DOCraftingModule {
  + DOCraftingModule(JobEntry) 
   OptionalPredicate~ItemStack~ staticIngredientValidator
   OptionalPredicate~ItemStack~ ingredientValidator
}
class DeleteBuildingsArgumentType {
  + DeleteBuildingsArgumentType() 
  + listSuggestions(CommandContext, SuggestionsBuilder) CompletableFuture~Suggestions~
  + parse(StringReader) Boolean
  + argument() DeleteBuildingsArgumentType
}
class DeliveryRequest {
  + DeliveryRequest(IRequester, IToken~?~, RequestState, Delivery) 
  + getResolverToolTip(IColonyView) List~MutableComponent~
   ItemStack taskStack
   MutableComponent displayPrefix
   ResourceLocation displayIcon
   List~ItemStack~ displayStacks
   Component shortDisplayString
   int displayCount
   ImmutableList~ItemStack~ deliveries
}
class DeliveryRequestFactory {
  + DeliveryRequestFactory() 
  + deserialize(IFactoryController, CompoundTag) DeliveryRequest
  + serialize(IFactoryController, DeliveryRequest) CompoundTag
  + serialize(IFactoryController, DeliveryRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) DeliveryRequest
  + getNewInstance(Delivery, IRequester, IToken~?~, RequestState) DeliveryRequest
   TypeToken~DeliveryRequest~ factoryOutputType
   short serializationId
   TypeToken~Delivery~ factoryInputType
}
class DialogueAnswerEntry {
  + DialogueAnswerEntry(Function~JsonObject, IQuestDialogueAnswer~) 
  + produce(JsonObject) IQuestDialogueAnswer
}
class DialogueElement {
  + DialogueElement(Component, List~AnswerElement~) 
  - Component text
  + getOptionResult(int) IQuestDialogueAnswer?
  + parse(JsonObject) DialogueElement
   List~Component~ options
   Component text
}
class DirectionResult {
<<enumeration>>
  - DirectionResult(List~String~, List~String~) 
  - DirectionResult(String, String) 
  - Component longText
  - Component shortText
  + valueOf(String) DirectionResult
  + values() DirectionResult[]
   Component longText
   Component shortText
}
class DiseaseState {
<<enumeration>>
  + DiseaseState() 
  + values() DiseaseState[]
  + valueOf(String) DiseaseState
}
class DoorToggle {
  + DoorToggle() 
  + toggleBlock(Entity, BlockState, Level, BlockPos) void
  + toggleBlockClosed(Entity, BlockState, Level, BlockPos) void
  + isBlockToggleAble(BlockState) boolean
}
class DynamicTreesSettingFactory {
  + DynamicTreesSettingFactory() 
  + getNewInstance(int, int) DynamicTreesSetting
   TypeToken~DynamicTreesSetting~ factoryOutputType
   short serializationId
}
class EatingState {
<<enumeration>>
  + EatingState() 
  + values() EatingState[]
  + valueOf(String) EatingState
}
class EditRankType {
  + EditRankType() 
  + EditRankType(IColonyView, Rank, int) 
  + toBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
  + fromBytes(FriendlyByteBuf) void
}
class EntityKillProgressInstance {
  + EntityKillProgressInstance(KillEntityObjectiveTemplateTemplate) 
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
   boolean fulfilled
   int missingQuantity
}
class ExecutionHandler {
<<Interface>>
  + apply(CommandContext~CommandSourceStack~) Integer
}
class ExpressionNode {
  + ExpressionNode(String) 
  + append(String) ExpressionNode
}
class ExtensionId {
  + ExtensionId(BlockPos, BuildingExtensionEntry) 
  + entry() BuildingExtensionEntry
  + deserializeNBT(CompoundTag) ExtensionId
  + pos() BlockPos
  + serializeNBT() Tag
}
class Factory {
  + Factory() 
  + deserialize(IFactoryController, FriendlyByteBuf) StandardRequestSystemBuildingDataStore
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) StandardRequestSystemBuildingDataStore
  + deserialize(IFactoryController, CompoundTag) StandardRequestSystemBuildingDataStore
  + serialize(IFactoryController, StandardRequestSystemBuildingDataStore) CompoundTag
  + serialize(IFactoryController, StandardRequestSystemBuildingDataStore, FriendlyByteBuf) void
   TypeToken~StandardRequestSystemBuildingDataStore~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class FarmerFieldsModule {
  + FarmerFieldsModule() 
  + getMatchingExtension(Predicate~IBuildingExtension~) List~IBuildingExtension~
  + canAssignExtensionOverride(IBuildingExtension) boolean
   int maxExtensionCount
   Class~?~ expectedExtensionType
}
class FarmerFieldsModuleView {
  + FarmerFieldsModuleView() 
  + canAssignFieldOverride(IBuildingExtension) boolean
  + getFieldWarningTooltip(IBuildingExtension) MutableComponent?
   List~IBuildingExtension~ fieldsInColony
   BOWindow window
}
class FenceToggle {
  + FenceToggle() 
  + toggleBlockClosed(Entity, BlockState, Level, BlockPos) void
  + isBlockToggleAble(BlockState) boolean
  + toggleBlock(Entity, BlockState, Level, BlockPos) void
}

AbstractCraftingRequestFactory~C, R~  ..>  AbstractCraftingRequest~C~ 
AllOption  ..>  ArgumentOption~TValue~ 
AssistantHammerPreview  ..>  BlueprintCacheKey : «create»
BeekeeperCollectionSettingsFactory  -->  AbstractStringSettingsFactory~T~ 
BoolSettingFactory  -->  AbstractBoolSettingFactory~T~ 
BrewingModule  -->  Brewing 
BuildGoggles  ..>  BlueprintCacheKey : «create»
Builder  ..>  BuildingExtensionEntry : «create»
Builder "1" *--> "action 1" ActionToPerform 
BuilderModeSettingFactory  -->  AbstractStringSettingsFactory~T~ 
BuildingExtensionEntry  -->  Builder 
BurnableRequestFactory  ..>  BurnableRequest : «create»
ColonyBorderOverlay "1" *--> "fullscreenStyle 1" BorderStyle 
ColonyIdOption  ..>  ArgumentOption~TValue~ 
CrafterRecipeSettingFactory  -->  AbstractStringSettingsFactory~T~ 
CraftingModule  -->  Crafting 
CraftingModule  -->  Custom 
DeliveryRequestFactory  ..>  DeliveryRequest : «create»
DialogueElement "1" *--> "answers *" AnswerElement 
DynamicTreesSettingFactory  -->  AbstractIntSettingFactory~T~ 
```
