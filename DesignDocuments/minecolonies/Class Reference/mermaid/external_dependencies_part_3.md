# external.dependencies (part 3)

80 classes, 16 internal relationships shown.

```mermaid
classDiagram
direction BT

class PlantationFieldsModuleView {
  + PlantationFieldsModuleView() 
  - int maxConcurrentPlants
  + deserialize(FriendlyByteBuf) void
  + getFieldWarningTooltip(IBuildingExtension) MutableComponent?
  # canAssignFieldOverride(IBuildingExtension) boolean
  - getCurrentPlantsPlusField(IBuildingExtension) int
  - hasRequiredResearchForField(IBuildingExtension) boolean
   int maxConcurrentPlants
   List~IBuildingExtension~ fieldsInColony
   int currentPlants
   BOWindow window
}
class PlantationModuleResult {
  - PlantationModuleResult(IPlantationModule, BlockPos, ActionToPerform, BlockPos?, PlanterAIModuleResultResetState) 
  - BlockPos workingPosition
  - IPlantationModule module
  - ActionToPerform action
  - BlockPos? actionPosition
  + shouldResetCurrentField() boolean
  + shouldResetWorkingPosition() boolean
   ActionToPerform action
   BlockPos workingPosition
   BlockPos? actionPosition
   IPlantationModule module
}
class PlanterAIModuleResultResetState {
<<enumeration>>
  + PlanterAIModuleResultResetState() 
  + values() PlanterAIModuleResultResetState[]
  + valueOf(String) PlanterAIModuleResultResetState
}
class PlayerNameOption {
  + PlayerNameOption() 
  + matches(String) boolean
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
  + resolveValue(CommandSourceStack, String) Integer
}
class PlayerUuidOption {
  + PlayerUuidOption() 
  + resolveValue(CommandSourceStack, String) Integer
  + matches(String) boolean
  + createSuggestions(Level, SharedSuggestionProvider, SuggestionsBuilder) void
}
class PondState {
<<enumeration>>
  + PondState() 
  + values() PondState[]
  + valueOf(String) PondState
}
class PostBoxMinimumStockModuleView {
  + PostBoxMinimumStockModuleView() 
   BOWindow window
}
class PostBoxRequestTreeWindowModule {
  - PostBoxRequestTreeWindowModule(AbstractWindowSkeleton, View) 
  # onCancel(IRequest~?~) void
   Collection~IRequest~?~~ openRequests
}
class PrivateCraftingRequest {
  # PrivateCraftingRequest(IRequester, IToken~?~, RequestState, PrivateCrafting) 
  # PrivateCraftingRequest(IRequester, IToken~?~, PrivateCrafting) 
   String translationKey
   String displayIconFile
}
class PrivateCraftingRequestFactory {
  + PrivateCraftingRequestFactory() 
   short serializationId
}
class PublicCraftingRequest {
  # PublicCraftingRequest(IRequester, IToken~?~, RequestState, PublicCrafting) 
  # PublicCraftingRequest(IRequester, IToken~?~, PublicCrafting) 
   String translationKey
   String displayIconFile
}
class PublicCraftingRequestFactory {
  + PublicCraftingRequestFactory() 
   short serializationId
}
class QuestCancellationDialogueAnswer {
  + QuestCancellationDialogueAnswer() 
  + applyToQuest(Player, IQuestInstance) void
}
class QuestModuleContainer~T~ {
  + QuestModuleContainer(WindowQuestLogQuestModule~T~, IColonyView, SwitchView, String) 
  ~ trackQuest(Button) void
  ~ onUpdate() void
}
class RackInventory {
  + RackInventory(int) 
  + setStackInSlot(int, ItemStack) void
  # onContentsChanged(int) void
  + insertItem(int, ItemStack, boolean) ItemStack
}
class RaidHistory {
  + RaidHistory(int, long) 
  - write() CompoundTag
  - fromNBT(CompoundTag) RaidHistory
  + toString() String
}
class RaidSettings {
  + RaidSettings(boolean, String?, boolean, Integer?, BlockPos?) 
  + defaultRaidSettings() RaidSettings
  + raidType() String?
  + allowShips() boolean
  + location() BlockPos?
  + forcedSpawn() boolean
  + withExplicitType(String?) RaidSettings
  + raiderAmount() Integer?
}
class RaidSpawnInfo {
  + RaidSpawnInfo(ResourceLocation, BlockPos) 
  + fromNBT(CompoundTag) RaidSpawnInfo
  + toString() String
  - write() CompoundTag
}
class RaidSpawnResult {
<<enumeration>>
  + RaidSpawnResult() 
  + values() RaidSpawnResult[]
  + valueOf(String) RaidSpawnResult
}
class RaiderColor {
<<enumeration>>
  - RaiderColor(String, TextColor) 
  - String key
  - TextColor color
  + values() RaiderColor[]
  + valueOf(String) RaiderColor
   String key
   TextColor color
}
class RaiderSoundTypes {
<<enumeration>>
  + RaiderSoundTypes() 
  + values() RaiderSoundTypes[]
  + valueOf(String) RaiderSoundTypes
}
class ReadFromJsonFunction {
<<Interface>>
  + read(JsonObject) IResearchCost
}
class ReadFromNBTFunction {
<<Interface>>
  + read(CompoundTag) IResearchRequirement
}
class RecipeIdTooltipCallback {
  + RecipeIdTooltipCallback(ResourceLocation, IModIdHelper) 
  + onTooltip(IRecipeSlotView, List~Component~) void
}
class RecipeSettingFactory {
  + RecipeSettingFactory() 
  + deserialize(IFactoryController, CompoundTag) RecipeSetting
  + serialize(IFactoryController, RecipeSetting, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) RecipeSetting
  + serialize(IFactoryController, RecipeSetting) CompoundTag
  + getNewInstance(IToken~?~, String) RecipeSetting
   TypeToken~RecipeSetting~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class RecruitCost {
  + RecruitCost(ItemStack, int, ItemStack) 
  + recruitLevel() int
  + recruitItem() ItemStack
  + boots() ItemStack
}
class RemovePlayer {
  + RemovePlayer(IColonyView, UUID) 
  + RemovePlayer() 
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
   LogicalSide? executionSide
}
class RemoveRank {
  + RemoveRank(IColonyView, Rank) 
  + RemoveRank() 
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
}
class RequestStage {
<<enumeration>>
  + RequestStage() 
  + valueOf(String) RequestStage
  + values() RequestStage[]
}
class RequestWrapper {
  - RequestWrapper(IRequest~?~, int) 
  + request() IRequest~?~
  + depth() int
}
class Research {
  + Research(ResourceLocation, ResourceLocation) 
  + String translatedName
  + String translatedSubtitle
  + addAlternateBuildingRequirement(List~ResourceLocation~, Integer) Research
  + setAutostart() Research
  + addItemCost(List~Item~, int) Research
  + setHidden() Research
  + setIcon(ItemStack) Research
  + setIcon(Item) Research
  + addItemCost(TagKey~Item~, int) Research
  + addEffect(ResourceLocation, int) Research
  + setNoReset() Research
  + setInstant() Research
  + addEffect(AbstractColonyBlock~?~, int) Research
  + setIcon(Item, int) Research
  + addBuildingRequirement(ResourceLocation, int) Research
  + addSingleBuildingRequirement(ResourceLocation, int) Research
  + setRemove(ResourceLocation) Research
  + addToList(Collection~Research~) Research
  + addResearchRequirement(ResourceLocation) Research
  + setOnlyChild() Research
  + setRemove() Research
  + addItemCost(Item, int) Research
   int sortOrder
   String name
   ResourceLocation icon
   Research parentResearch
   Collection~ResourceLocation~ remove
   JsonArray requirementsArray
   String translatedName
   JsonArray costsArray
   String subtitle
   String translatedSubtitle
}
class ResearchBranch {
  + ResearchBranch(ResourceLocation) 
  + String translatedSubtitle
  + setRemove(ResourceLocation) ResearchBranch
  + setRemove() ResearchBranch
   String branchName
   Collection~ResourceLocation~ remove
   int branchSortOrder
   String translatedBranchName
   boolean hidden
   double branchTimeMultiplier
   ResearchBranchType branchType
   String subtitle
   String translatedSubtitle
}
class ResearchButtonState {
<<enumeration>>
  + ResearchButtonState() 
  + values() ResearchButtonState[]
  + valueOf(String) ResearchButtonState
}
class ResearchCostEntry {
  + ResearchCostEntry(ResourceLocation, ReadFromNBTFunction, ReadFromJsonFunction) 
  - ResourceLocation registryName
  + readFromJson(JsonObject) IResearchCost
  + readFromNBT(CompoundTag) IResearchCost
   ResourceLocation registryName
}
class ResearchEffect {
  + ResearchEffect(ResourceLocation, String) 
  + ResearchEffect(ResourceLocation) 
  + ResearchEffect(AbstractColonyBlock~?~) 
  + String translatedName
  + String translatedSubtitle
   String name
   String subtitle
   String translatedName
   String translatedSubtitle
   double[] levels
}
class ResearchEffectEntry {
  + ResearchEffectEntry(ResourceLocation, ReadFromNBTFunction) 
  - ResourceLocation registryName
  + readFromNBT(CompoundTag) IResearchEffect
   ResourceLocation registryName
}
class ResearchListProvider {
  ~ ResearchListProvider(List~ResourceLocation~, List~List~MutableComponent~~) 
  + updateElement(int, Pane) void
   int elementCount
}
class ResearchRequirementEntry {
  + ResearchRequirementEntry(ResourceLocation, ReadFromNBTFunction, ReadFromJsonFunction) 
  - ResourceLocation registryName
  + readFromNBT(CompoundTag) IResearchRequirement
  + readFromJson(JsonObject) IResearchRequirement
   ResourceLocation registryName
}
class ResourceComparator {
  + ResourceComparator(RessourceAvailability[]) 
  + compare(BuildingBuilderResource, BuildingBuilderResource) int
}
class RessourceAvailability {
<<enumeration>>
  + RessourceAvailability() 
  + values() RessourceAvailability[]
  + valueOf(String) RessourceAvailability
}
class Result {
  + Result(ResourceLocation, Ingredient, int) 
  - ResourceLocation id
  + serializeAdvancement() JsonObject?
  + serializeRecipeData(JsonObject) void
   ResourceLocation id
   ResourceLocation? advancementId
   RecipeSerializer~?~ type
}
class RewardEntry {
  + RewardEntry(Function~JsonObject, IQuestRewardTemplate~) 
  + produce(JsonObject) IQuestRewardTemplate
}
class Rule {
<<Interface>>

}
class SchematicAnalyzationResult {
  + SchematicAnalyzationResult(int, Set~ItemStorage~, int, Blueprint) 
  + hashCode() int
  + equals(Object) boolean
}
class Serializer {
  + Serializer() 
  + toNetwork(FriendlyByteBuf, CompostRecipe) void
  + fromNetwork(ResourceLocation, FriendlyByteBuf) CompostRecipe?
  + fromJson(ResourceLocation, JsonObject) CompostRecipe
}
class ServerEvents {
  + ServerEvents() 
  + onServerStarted(ServerStartedEvent) void
  - sendPackets(ServerPlayer, UpdateClientWithCompatibilityMessage) void
  - discoverCompatLists(MinecraftServer) void
  + onDataPackSync(OnDatapackSyncEvent) void
}
class SifterMeshDetails {
  + SifterMeshDetails(Item, int, Builder) 
  - String name
  - int minBuildingLevel
  - Builder lootTable
  - Item mesh
   Builder lootTable
   String name
   Item mesh
   int minBuildingLevel
}
class SingleStateBlockGetter {
  + SingleStateBlockGetter(BlockState) 
  + getFluidState(BlockPos) FluidState
  + getBlockEntity(BlockPos) BlockEntity?
  + getBlockState(BlockPos) BlockState
   int minBuildHeight
   int height
}
class SkillData {
  - SkillData(int, double) 
  - double experience
  - int level
   double experience
   int level
}
class SleepState {
<<enumeration>>
  + SleepState() 
  + valueOf(String) SleepState
  + values() SleepState[]
}
class SmeltAbleOreRequest {
  ~ SmeltAbleOreRequest(IRequester, IToken~?~, RequestState, SmeltableOre) 
  ~ SmeltAbleOreRequest(IRequester, IToken~?~, SmeltableOre) 
   List~ItemStack~ displayStacks
   Component shortDisplayString
}
class SmeltableOreRequestFactory {
  + SmeltableOreRequestFactory() 
  + deserialize(IFactoryController, FriendlyByteBuf) SmeltAbleOreRequest
  + deserialize(IFactoryController, CompoundTag) SmeltAbleOreRequest
  + getNewInstance(SmeltableOre, IRequester, IToken~?~, RequestState) SmeltAbleOreRequest
  + serialize(IFactoryController, SmeltAbleOreRequest) CompoundTag
  + serialize(IFactoryController, SmeltAbleOreRequest, FriendlyByteBuf) void
   TypeToken~SmeltAbleOreRequest~ factoryOutputType
   short serializationId
   TypeToken~SmeltableOre~ factoryInputType
}
class Smelting {
  + Smelting(JobEntry) 
  + isRecipeCompatible(IGenericRecipe) boolean
   String id
   Set~CraftingType~ supportedCraftingTypes
}
class SmeltingModule {
  + SmeltingModule(JobEntry) 
  + improveRecipe(IRecipeStorage, int, ICitizenData) void
  + isRecipeCompatible(IGenericRecipe) boolean
   OptionalPredicate~ItemStack~ ingredientValidator
}
class SmileyEnum {
<<enumeration>>
  - SmileyEnum(ResourceLocation, int, int, int, SmileyEnum, SmileyEnum) 
  + values() SmileyEnum[]
  + valueOf(String) SmileyEnum
}
class Stage {
<<enumeration>>
  - Stage(ResourceLocation) 
  # ResourceLocation stageIcon
  + valueOf(String) Stage
  + values() Stage[]
   ResourceLocation stageIcon
   Component stageText
   Stage nextStage
}
class StatType {
<<enumeration>>
  + StatType() 
  + values() StatType[]
  + valueOf(String) StatType
}
class State {
<<enumeration>>
  + State() 
  + valueOf(String) State
  + values() State[]
}
class Storage {
  + Storage() 
  + readNBT(Capability~IColonyManagerCapability~, IColonyManagerCapability, boolean, Tag) void
  + writeNBT(Capability~IColonyManagerCapability~, IColonyManagerCapability, boolean) Tag
}
class StoryBuilder {
  + StoryBuilder(ResourceLocation) 
  + addBiome(Holder~Biome~[]) StoryBuilder
  - addStringOrArray(String, String[]) StoryBuilder
  + addContents(String[]) StoryBuilder
  + build() JsonObject
  + addBiomeTag(TagKey~Biome~[]) StoryBuilder
}
class StoryText {
  + StoryText(BiomeFilter, String) 
  + allMatches(Collection~StoryText~, Holder~Biome~) List~String~
  + biomeFilter() BiomeFilter
  + content() String
  + matches(Holder~Biome~) boolean
}
class StringSettingsFactory {
  + StringSettingsFactory() 
  + getNewInstance(List~String~, int) StringSetting
   TypeToken~StringSetting~ factoryOutputType
   short serializationId
}
class StringWithDescSettingsFactory {
  + StringWithDescSettingsFactory() 
  + getNewInstance(List~String~, int) StringSettingWithDesc
   TypeToken~StringSettingWithDesc~ factoryOutputType
   short serializationId
}
class StructureHandlerWrapper {
  - StructureHandlerWrapper(IStructureHandler) 
  - LayerBlueprintIterator outer
  + fancyPlacement() boolean
  + allowReplace() boolean
  + shouldBlocksBeConsideredEqual(BlockState, BlockState) boolean
  + isStackFree(ItemStack?) boolean
  + triggerSuccess(BlockPos, List~ItemStack~, boolean) void
  + getSolidBlockForPos(BlockPos, Function~BlockPos, BlockState~?) BlockState
  + prePlacementLogic(BlockPos, BlockState, List~ItemStack~) void
  + hasBluePrint() boolean
  + hasRequiredItems(List~ItemStack~) boolean
  + triggerEntitySuccess(BlockPos, List~ItemStack~, boolean) void
  - setLayerBlueprint() void
  + replaceWithSolidBlock(BlockState) boolean
  + getSolidBlockForPos(BlockPos) BlockState
   boolean ready
   ItemStack heldItem
   PlacementSettings rotationMirror
   Blueprint blueprint
   Blueprint bluePrint
   int stepsPerCall
   int maxBlocksCheckedPerCall
   IItemHandler? inventory
   boolean creative
   LayerBlueprintIterator outer
   Level world
   BlockPos centerPos
   PlacementSettings settings
   int layer
   String md5
}
class StudyItem {
  + StudyItem(Item, int, int) 
  + item() Item
  + skillIncreaseChance() int
  + breakChance() int
}
class TabImageSide {
<<enumeration>>
  - TabImageSide(String, int, BiFunction~BOWindow, Integer, Integer~) 
  + getImage(Random) ResourceLocation
  + valueOf(String) TabImageSide
  + values() TabImageSide[]
}
class TagListIterator {
  - TagListIterator(ListTag) 
  + hasNext() boolean
  + next() Tag
}
class TargetInfo {
  + TargetInfo() 
}
class TaskInformationWrapper~M, G~ {
  ~ TaskInformationWrapper(int, Function~AbstractEntityMinecoloniesMonster, G~, Predicate~M~) 
  - Function~AbstractEntityMinecoloniesMonster, G~ aiTaskProducer
  - Predicate~M~ entityPredicate
  - int priority
   Function~AbstractEntityMinecoloniesMonster, G~ aiTaskProducer
   int priority
   Predicate~M~ entityPredicate
}
class TexturePacks {
  + TexturePacks() 
}
class TimedSound {
  + TimedSound(SoundEvent, SoundSource, int, int, BlockPos, float, float) 
}
class ToggleAble {
  + ToggleAble() 
  + toggleBlock(Entity, BlockState, Level, BlockPos) void
  + isBlockToggleAble(BlockState) boolean
  + canOpen(BlockState) boolean
  + onlyCloseYourOpens() boolean
  + toggleBlockClosed(Entity, BlockState, Level, BlockPos) void
}
class ToolRequest {
  + ToolRequest(IRequester, IToken~?~, RequestState, Tool) 
  + ToolRequest(IRequester, IToken~?~, Tool) 
   Component longDisplayString
   Component shortDisplayString
}
class ToolRequestFactory {
  + ToolRequestFactory() 
  + serialize(IFactoryController, ToolRequest) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) ToolRequest
  + getNewInstance(Tool, IRequester, IToken~?~, RequestState) ToolRequest
  + serialize(IFactoryController, ToolRequest, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) ToolRequest
   TypeToken~ToolRequest~ factoryOutputType
   short serializationId
   TypeToken~Tool~ factoryInputType
}
class TrapToggle {
  + TrapToggle() 
  + canOpen(BlockState) boolean
  + isBlockToggleAble(BlockState) boolean
  + onlyCloseYourOpens() boolean
  + toggleBlock(Entity, BlockState, Level, BlockPos) void
  + toggleBlockClosed(Entity, BlockState, Level, BlockPos) void
}
class TravelerData {
  + TravelerData(int, BlockPos, int) 
  + TravelerData(CompoundTag) 
  - int remainingTravelTime
  - int initialTravelTime
  - int citizenId
  - BlockPos target
  + hasReachedTarget() boolean
  + serializeNBT() CompoundTag
  + onTick() void
  + deserializeNBT(CompoundTag) void
   int initialTravelTime
   BlockPos target
   int citizenId
   int remainingTravelTime
   double travelPercentage
   boolean traveling
}
class TriggerEntry {
  + TriggerEntry(Function~JsonObject, IQuestTriggerTemplate~) 
  + produce(JsonObject) IQuestTriggerTemplate
}
class TypeTokenSubTypeOverrideHandler {
  + TypeTokenSubTypeOverrideHandler() 
  + matches(TypeToken~?~) boolean
   TypeToken~TypeToken~?~~ outputType
}
class View {
  + View(IColonyView, BlockPos) 
   BOWindow window
}
class VisitorState {
<<enumeration>>
  + VisitorState() 
  + valueOf(String) VisitorState
  + values() VisitorState[]
}

PlantationModuleResult "1" *--> "resetState 1" PlanterAIModuleResultResetState 
PostBoxRequestTreeWindowModule "1" *--> "buildingView 1" View 
PrivateCraftingRequestFactory  ..>  PrivateCraftingRequest : «create»
PublicCraftingRequestFactory  ..>  PublicCraftingRequest : «create»
RaidHistory "1" *--> "spawnData *" RaidSpawnInfo 
ResearchCostEntry "1" *--> "readFromJson 1" ReadFromJsonFunction 
ResearchCostEntry "1" *--> "readFromNBT 1" ReadFromNBTFunction 
ResearchEffectEntry "1" *--> "readFromNBT 1" ReadFromNBTFunction 
ResearchRequirementEntry "1" *--> "readFromJson 1" ReadFromJsonFunction 
ResearchRequirementEntry "1" *--> "readFromNBT 1" ReadFromNBTFunction 
ResourceComparator "1" *--> "order *" RessourceAvailability 
SmeltableOreRequestFactory  ..>  SmeltAbleOreRequest : «create»
SmeltingModule  -->  Smelting 
ToolRequestFactory  ..>  ToolRequest : «create»
TrapToggle  -->  ToggleAble 
View  -->  View 
```
