# external.dependencies (part 2)

80 classes, 12 internal relationships shown.

```mermaid
classDiagram
direction BT

class FieldsComparator {
  + FieldsComparator(IBuildingView) 
  + compare(IBuildingExtension, IBuildingExtension) int
}
class FishingRecipe {
  + FishingRecipe(ResourceLocation, int, List~LootDrop~) 
  - ResourceLocation id
  - int level
  - List~LootDrop~ drops
   List~LootDrop~ drops
   int level
   ResourceLocation id
}
class FleeStates {
<<enumeration>>
  + FleeStates() 
  + valueOf(String) FleeStates
  + values() FleeStates[]
}
class FloristRecipe {
  + FloristRecipe(int, List~List~ItemStack~~) 
  + level() int
  + flowers() List~List~ItemStack~~
}
class FoodRequest {
  ~ FoodRequest(IRequester, IToken~?~, RequestState, Food) 
  ~ FoodRequest(IRequester, IToken~?~, Food) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class FoodRequestFactory {
  + FoodRequestFactory() 
  + serialize(IFactoryController, FoodRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) FoodRequest
  + deserialize(IFactoryController, CompoundTag) FoodRequest
  + getNewInstance(Food, IRequester, IToken~?~, RequestState) FoodRequest
  + serialize(IFactoryController, FoodRequest) CompoundTag
   TypeToken~FoodRequest~ factoryOutputType
   short serializationId
   TypeToken~Food~ factoryInputType
}
class GuardFollowModeSettingFactory {
  + GuardFollowModeSettingFactory() 
  + getNewInstance(List~String~, int) GuardFollowModeSetting
   TypeToken~GuardFollowModeSetting~ factoryOutputType
   short serializationId
}
class GuardPatrolModeSettingFactory {
  + GuardPatrolModeSettingFactory() 
  + getNewInstance(List~String~, int) GuardPatrolModeSetting
   TypeToken~GuardPatrolModeSetting~ factoryOutputType
   short serializationId
}
class GuardTaskSettingFactory {
  + GuardTaskSettingFactory() 
  + getNewInstance(List~String~, int) GuardTaskSetting
   TypeToken~GuardTaskSetting~ factoryOutputType
   short serializationId
}
class HappinessFactorTypeEntry {
  + HappinessFactorTypeEntry(Supplier~IHappinessModifier~) 
  + create() IHappinessModifier
}
class HappinessFunctionEntry {
  + HappinessFunctionEntry(Function~ICitizenData, Double~) 
  - Function~ICitizenData, Double~ doubleSupplier
   Function~ICitizenData, Double~ doubleSupplier
}
class HeartsEnum {
<<enumeration>>
  - HeartsEnum(ResourceLocation, int, int, int, HeartsEnum, HeartsEnum) 
  + values() HeartsEnum[]
  + valueOf(String) HeartsEnum
}
class HerdingModule {
  + HerdingModule() 
  + serializeNBT(CompoundTag) void
  + canTryToMilk() boolean
  + canTryToStew() boolean
  + onMilked() void
  + onWakeUp() void
  + deserializeNBT(CompoundTag) void
  + getRecipesForDisplayPurposesOnly(Animal) List~IGenericRecipe~
  + onStewed() void
   Map~Predicate~ItemStack~, Tuple~Integer, Boolean~~ requiredItemsAndAmount
}
class HereOption {
  + HereOption() 
  + matches(String) boolean
  + resolveValue(CommandSourceStack, String) Integer
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
}
class HighlightRenderDataContainer {
  - HighlightRenderDataContainer(IHighlightRenderData) 
  - isExpired(long) boolean
  - attemptStart(WorldEventContext) void
}
class IFriendlyByteBufToObjectReader~O~ {
<<Interface>>
  + apply(IFactoryController, FriendlyByteBuf) O
}
class IMatchActionResult {
<<Interface>>
  + accept(ICapabilityProvider, int) void
}
class IMatchActionResultHandler {
<<Interface>>
  + accept(IItemHandler, int) void
}
class INBTToObjectConverter~O~ {
<<Interface>>
  + apply(IFactoryController, CompoundTag) O
}
class IObjectConstructor~T, O~ {
<<Interface>>
  + construct(T, IToken~?~, IRequester, RequestState) O
}
class IObjectToNBTConverter~O~ {
<<Interface>>
  + apply(IFactoryController, O) CompoundTag
}
class IObjectToPackBufferWriter~O~ {
<<Interface>>
  + apply(IFactoryController, FriendlyByteBuf, O) void
}
class IRenderBlueprintRule {
<<Interface>>
  + getDesiredBlueprints(WorldEventContext) Map~BlockPos, PendingRenderData~
  + isEnabled(WorldEventContext) boolean
}
class IRequestTreeSupportsFulfill {
<<Interface>>
  + onFulfill(IRequest~?~) void
}
class IWidgetAdder {
<<Interface>>
  + onBuild(Button) void
}
class Impl {
  + Impl() 
  - int owningColony
  + removeBuildingClaim(int, BlockPos, LevelChunk) void
  + setOwningColony(int, LevelChunk) void
  + addBuildingClaim(int, BlockPos, LevelChunk) void
  - readClaims(CompoundTag) void
  + reset(LevelChunk) void
  + readFromNBT(CompoundTag) void
  + removeColony(int, LevelChunk) void
  + addColony(int, LevelChunk) void
   List~Integer~ staticColonyClaim
   List~Integer~ staticClaimColonies
   Map~Integer, Set~BlockPos~~ allClaimingBuildings
   int owningColony
}
class InfoBlock {
  - InfoBlock(Component, Component?, Rect2i) 
  + text() Component
  + tip() Component?
  + bounds() Rect2i
}
class IngredientStacks {
  + IngredientStacks(List~ItemStack~) 
  - List~ItemStack~ stacks
  + hashCode() int
  + equals(Object) boolean
  + compareTo(IngredientStacks) int
  + merge(IngredientStacks) void
  + toString() String
   List~ItemStack~ stacks
   int count
}
class InnerRenderTypes {
  - InnerRenderTypes(String, VertexFormat, Mode, int, boolean, boolean, Runnable, Runnable) 
}
class InputItemHandler {
  + InputItemHandler(IItemHandler, int, int, int) 
  + mayPlace(ItemStack) boolean
  + remove(int) ItemStack
  + mayPickup(Player) boolean
   int maxStackSize
}
class IntSettingFactory {
  + IntSettingFactory() 
  + getNewInstance(int, int) IntSetting
   TypeToken~IntSetting~ factoryOutputType
   short serializationId
}
class InventoryType {
<<enumeration>>
  + InventoryType() 
  + values() InventoryType[]
  + valueOf(String) InventoryType
}
class ItemCheckResult {
<<enumeration>>
  + ItemCheckResult() 
  + valueOf(String) ItemCheckResult
  + values() ItemCheckResult[]
}
class ItemStackListRequest {
  + ItemStackListRequest(IRequester, IToken~?~, StackList) 
  + ItemStackListRequest(IRequester, IToken~?~, RequestState, StackList) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class ItemStackListRequestFactory {
  + ItemStackListRequestFactory() 
  + deserialize(IFactoryController, CompoundTag) ItemStackListRequest
  + getNewInstance(StackList, IRequester, IToken~?~, RequestState) ItemStackListRequest
  + serialize(IFactoryController, ItemStackListRequest) CompoundTag
  + serialize(IFactoryController, ItemStackListRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) ItemStackListRequest
   TypeToken~ItemStackListRequest~ factoryOutputType
   short serializationId
   TypeToken~StackList~ factoryInputType
}
class ItemStackRequest {
  + ItemStackRequest(IRequester, IToken~?~, Stack) 
  + ItemStackRequest(IRequester, IToken~?~, RequestState, Stack) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class ItemStackRequestFactory {
  + ItemStackRequestFactory() 
  + getNewInstance(Stack, IRequester, IToken~?~, RequestState) ItemStackRequest
  + serialize(IFactoryController, ItemStackRequest) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) ItemStackRequest
  + deserialize(IFactoryController, CompoundTag) ItemStackRequest
  + serialize(IFactoryController, ItemStackRequest, FriendlyByteBuf) void
   TypeToken~ItemStackRequest~ factoryOutputType
   short serializationId
   TypeToken~Stack~ factoryInputType
}
class ItemTagRequest {
  + ItemTagRequest(IRequester, IToken~?~, RequestTag) 
  + ItemTagRequest(IRequester, IToken~?~, RequestState, RequestTag) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class ItemTagRequestFactory {
  + ItemTagRequestFactory() 
  + serialize(IFactoryController, ItemTagRequest) CompoundTag
  + deserialize(IFactoryController, CompoundTag) ItemTagRequest
  + serialize(IFactoryController, ItemTagRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) ItemTagRequest
  + getNewInstance(RequestTag, IRequester, IToken~?~, RequestState) ItemTagRequest
   TypeToken~ItemTagRequest~ factoryOutputType
   short serializationId
   TypeToken~RequestTag~ factoryInputType
}
class LayerButton {
  + LayerButton(int, int, int, int, int) 
  + render(GuiGraphics, int, int, float) void
  + onPress() void
}
class LootDrop {
  + LootDrop(List~ItemStack~, float, float, boolean) 
  + LootDrop(List~LootDrop~) 
  - float probability
  - float quality
  - boolean conditional
  + deserialize(FriendlyByteBuf) LootDrop
  + hashCode() int
  + serialize(FriendlyByteBuf) void
   boolean conditional
   float probability
   float quality
   List~ItemStack~ itemStacks
}
class LootTableRegistrar {
<<Interface>>
  + register(ResourceLocation, LootContextParamSet, Builder) void
}
class LootTableTooltipCallback {
  + LootTableTooltipCallback(LootDrop, ResourceLocation) 
  + onTooltip(IRecipeSlotView, List~Component~) void
}
class MapDecoration {
  + MapDecoration(ItemIcon, Box, Box, Image) 
  + getRedFromRange(double) int
  + getGreenFromRange(double) int
}
class MessageBuilder {
  ~ MessageBuilder(Component) 
  + sendTo(IColony, boolean) MessageBuilderColonyPlayerSelector
  - getFormattableComponent(Component) MutableComponent
  + withPriority(MessagePriority) MessageBuilder
  + append(String, Object[]) MessageBuilder
  + create() MutableComponent
  + withClickEvent(ClickEvent) MessageBuilder
  + sendTo(IColony) MessageBuilderColonyPlayerSelector
  + append(Component) MessageBuilder
  + sendToClose(BlockPos, int, List~Player~) void
  + sendTo(Collection~Player~) void
  + sendTo(Player[]) void
}
class MessageBuilderColonyPlayerSelector {
  + MessageBuilderColonyPlayerSelector(MutableComponent, IColony, boolean) 
  + forManagers() void
  + forAllPlayers() void
  - sendInternal(Collection~Player~) void
}
class MessageMode {
<<enumeration>>
  + MessageMode() 
  + valueOf(String) MessageMode
  + values() MessageMode[]
}
class MessagePriority {
<<enumeration>>
  - MessagePriority(ChatFormatting) 
  + values() MessagePriority[]
  + valueOf(String) MessagePriority
}
class MessageType {
<<enumeration>>
  + MessageType() 
  + values() MessageType[]
  + valueOf(String) MessageType
}
class MinStackRequest {
  + MinStackRequest(IRequester, IToken~?~, RequestState, MinimumStack) 
  + MinStackRequest(IRequester, IToken~?~, MinimumStack) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class MineOption {
  + MineOption() 
  + resolveValue(CommandSourceStack, String) Integer
  + matches(String) boolean
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
}
class MinecoloniesThreadFactory {
  + MinecoloniesThreadFactory() 
  + newThread(Runnable) Thread
}
class MinimumStackRequestFactory {
  + MinimumStackRequestFactory() 
  + deserialize(IFactoryController, FriendlyByteBuf) MinStackRequest
  + serialize(IFactoryController, MinStackRequest) CompoundTag
  + deserialize(IFactoryController, CompoundTag) MinStackRequest
  + getNewInstance(MinimumStack, IRequester, IToken~?~, RequestState) MinStackRequest
  + serialize(IFactoryController, MinStackRequest, FriendlyByteBuf) void
   TypeToken~MinStackRequest~ factoryOutputType
   short serializationId
   TypeToken~MinimumStack~ factoryInputType
}
class Mode {
<<enumeration>>
  + Mode() 
  + valueOf(String) Mode
  + values() Mode[]
}
class ModuleProducer~MODULECLASS, VIEWCLASS~ {
  + ModuleProducer(String, Supplier~IBuildingModule~, Supplier~Supplier~IBuildingModuleView~~) 
  + hasServerModule() boolean
  + hasView() boolean
  + hashCode() int
  + equals(Object) boolean
   int runtimeID
}
class MountMaintenance {
<<enumeration>>
  + MountMaintenance() 
  + valueOf(String) MountMaintenance
  + values() MountMaintenance[]
}
class MourningState {
<<enumeration>>
  + MourningState() 
  + valueOf(String) MourningState
  + values() MourningState[]
}
class NameOrder {
<<enumeration>>
  + NameOrder() 
  + values() NameOrder[]
  + valueOf(String) NameOrder
}
class NearBuildPreview {
  + NearBuildPreview() 
  + isEnabled(WorldEventContext) boolean
  + getDesiredBlueprints(WorldEventContext) Map~BlockPos, PendingRenderData~
}
class NetworkingMessageEntry~MSG~ {
  - NetworkingMessageEntry(Supplier~MSG~) 
  - Supplier~MSG~ creator
  + onSplitting(int) void
   Supplier~MSG~ creator
}
class NextObjectiveDialogueAnswer {
  + NextObjectiveDialogueAnswer(int) 
  + NextObjectiveDialogueAnswer(JsonObject) 
  + applyToQuest(Player, IQuestInstance) void
}
class NodeStatus {
<<enumeration>>
  + NodeStatus() 
  + values() NodeStatus[]
  + valueOf(String) NodeStatus
}
class NodeType {
<<enumeration>>
  - NodeType(String) 
  + values() NodeType[]
  + valueOf(String) NodeType
   String schematicName
}
class NotifyingRackInventory {
  + NotifyingRackInventory(int) 
  # onContentsChanged(int) void
}
class ObjectiveEntry {
  + ObjectiveEntry(Function~JsonObject, IQuestObjectiveTemplate~) 
  + produce(JsonObject) IQuestObjectiveTemplate
}
class OnButtonPress {
  + OnButtonPress() 
  + onPress(Button) void
}
class OptionContainer~TValue~ {
  + OptionContainer(ArgumentOption~TValue~, String) 
  + value() String
  + option() ArgumentOption~TValue~
}
class Options {
  - Options(IColonyView, Supplier~Boolean~) 
  + colony() IColonyView
  + showImportant() Supplier~Boolean~
}
class OreBreakingModule {
  + OreBreakingModule(JobEntry) 
  + getCraftingTool(AbstractEntityCitizen) ItemStack
  # getLootTable(Item) ResourceLocation
  + getAdditionalRecipesForDisplayPurposesOnly(Level) List~IGenericRecipe~
  # isPreTaughtRecipe(IRecipeStorage, Map~ResourceLocation, CustomRecipe~) boolean
  + checkForWorkerSpecificRecipes() void
   List~ResourceLocation~ additionalLootTables
}
class OverlayBox {
  + OverlayBox(AABB, int, float, boolean) 
  + color() int
  + width() float
  + bounds() AABB
  + showThroughBlocks() boolean
}
class PaletteButton {
  + PaletteButton(int, int, int, DyeColor) 
  + onPress() void
  + renderWidget(GuiGraphics, int, int, float) void
  - brighten(int, float) int
  + render(GuiGraphics, int, int, float) void
  - fillButton(GuiGraphics, int, int, int, int, int) void
}
class PathJobFindFishingPos {
  + PathJobFindFishingPos(Level, LevelReader, BlockPos, BlockPos, int) 
  # handleDebugOptions(MNode) void
  - canSeeTargetFromPos(MNode) boolean
  + getEndNodeScore(MNode) double
  # computeHeuristic(int, int, int) double
  # isAtDestination(MNode) boolean
}
class PatientState {
<<enumeration>>
  + PatientState() 
  + values() PatientState[]
  + valueOf(String) PatientState
}
class PatternButton {
  + PatternButton(int, int, int, Holder~BannerPattern~) 
  + onPress() void
  + render(GuiGraphics, int, int, float) void
  + renderWidget(GuiGraphics, int, int, float) void
}
class PendingConnectionType {
<<enumeration>>
  + PendingConnectionType() 
  + valueOf(String) PendingConnectionType
  + values() PendingConnectionType[]
}
class PendingRenderData {
  - PendingRenderData(BlueprintCacheKey?, BlockPos, int, boolean, boolean) 
  + blueprint() BlueprintCacheKey?
  + pos() BlockPos
  + builder() int
  + hasAnchor() boolean
  + boxOnly() boolean
}
class Permission {
  + Permission(IColonyView, boolean, Rank, Action) 
  + Permission() 
  + toBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
  + fromBytes(FriendlyByteBuf) void
   LogicalSide? executionSide
}
class PickupRequest {
  + PickupRequest(IRequester, IToken~?~, RequestState, Pickup) 
  + getResolverToolTip(IColonyView) List~MutableComponent~
   ResourceLocation displayIcon
   List~ItemStack~ displayStacks
   Component shortDisplayString
   ImmutableList~ItemStack~ deliveries
}
class PickupRequestFactory {
  + PickupRequestFactory() 
  + serialize(IFactoryController, PickupRequest) CompoundTag
  + getNewInstance(Pickup, IRequester, IToken~?~, RequestState) PickupRequest
  + serialize(IFactoryController, PickupRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) PickupRequest
  + deserialize(IFactoryController, CompoundTag) PickupRequest
   TypeToken~PickupRequest~ factoryOutputType
   short serializationId
   TypeToken~Pickup~ factoryInputType
}
class PlantationFieldsModule {
  + PlantationFieldsModule() 
  - getCurrentPlantsPlusField(IBuildingExtension) int
  + serializeToView(FriendlyByteBuf) void
  + canAssignExtensionOverride(IBuildingExtension) boolean
  + getMatchingExtension(Predicate~IBuildingExtension~) List~IBuildingExtension~
  - hasRequiredResearchForField(IBuildingExtension) boolean
   int maxExtensionCount
   int maxConcurrentPlants
   Class~?~ expectedExtensionType
}

FishingRecipe "1" *--> "drops *" LootDrop 
FoodRequestFactory  ..>  FoodRequest : «create»
ItemStackListRequestFactory  ..>  ItemStackListRequest : «create»
ItemStackRequestFactory  ..>  ItemStackRequest : «create»
ItemTagRequestFactory  ..>  ItemTagRequest : «create»
LootTableTooltipCallback "1" *--> "drop 1" LootDrop 
MessageBuilder  ..>  MessageBuilderColonyPlayerSelector : «create»
MessageBuilder "1" *--> "priority 1" MessagePriority 
MinimumStackRequestFactory  ..>  MinStackRequest : «create»
NearBuildPreview  ..>  IRenderBlueprintRule 
NearBuildPreview  ..>  PendingRenderData : «create»
PickupRequestFactory  ..>  PickupRequest : «create»
```
