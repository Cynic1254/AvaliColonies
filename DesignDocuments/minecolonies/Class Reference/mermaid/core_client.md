# core.client

72 classes, 47 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractWindowCitizen {
  + AbstractWindowCitizen(ICitizenDataView, ResourceLocation) 
}
class AbstractWindowTownHall {
  + AbstractWindowTownHall(View, String) 
  # shouldRenderDefaultSidebar() boolean
   String windowId
}
class CitizenWindowUtils {
  - CitizenWindowUtils() 
  + createHealthBar(ICitizenDataView, View) void
  + createSaturationBar(ICitizenDataView, View) void
  + createHappinessBar(ICitizenDataView, AbstractWindowSkeleton) void
  + updateHappiness(ICitizenDataView, AbstractWindowSkeleton) void
  + updateJobPage(ICitizenDataView, JobWindowCitizen, IColonyView) void
  - getXOffsetModifier(int) int
  + createSkillContent(ICitizenDataView, AbstractWindowSkeleton) void
  - addHeart(View, int, HeartsEnum) void
  - getYOffset(int) int
  + createHealthBar(int, View) void
  + createSaturationBar(double, View) void
  - addSmiley(View, int, SmileyEnum) void
  + createHappinessBar(ICitizenDataView, View) void
}
class ColonySize {
<<enumeration>>
  - ColonySize(ResourceLocation, int) 
  + getSizeByCount(int) ColonySize
  + values() ColonySize[]
  + createViewForInfo(ColonyInfo) View
  + valueOf(String) ColonySize
}
class ConnectionModuleWindow {
  + ConnectionModuleWindow(IBuildingView, boolean) 
  - getColonyDataFromPane(Button) ColonyConnection
  - teleportToColony(Button) void
  - updateConnections(ScrollingList, List~ColonyConnection~) void
}
class DOCraftingWindow {
  + DOCraftingWindow(DOCraftingModuleView) 
  - updateStockList() void
  - updateInputs(int, Pane) void
  - addRecipe() void
  - showRequests() void
  - matchingRequest(IRequest~?~) boolean
  + onOpened() void
  - reopenWithRequest(IRequest~?~?) void
}
class EnchanterStationModuleWindow {
  + EnchanterStationModuleWindow(EnchanterStationsModuleView) 
  + onOpened() void
  - switchClicked(Button) void
}
class EntityListModuleWindow {
  + EntityListModuleWindow(IEntityListModuleView) 
  + onUpdate() void
  - switchClicked(Button) void
  - updateResourceList() void
  - reset() void
  + onOpened() void
  + onButtonClicked(Button) void
  - updateResources() void
}
class ExpeditionLogModuleWindow {
  + ExpeditionLogModuleWindow(ExpeditionLogModuleView) 
  - clearChildren(View, int) void
  - createLootList(View, List~ItemStorage~) void
  - createMobList(View, List~Tuple~EntityType~?~, Integer~~) void
  + onUpdate() void
  - createEquipmentList(View, List~ItemStack~) void
  - refreshLog() void
}
class FamilyWindowCitizen {
  + FamilyWindowCitizen(ICitizenDataView) 
  + onOpened() void
}
class FarmFieldsModuleWindow {
  + FarmFieldsModuleWindow(FieldsModuleView) 
  - assignmentModeClicked(Button) void
  + onOpened() void
  - assignClicked(Button) void
  - setAssignButtonTexture(ButtonImage, boolean) void
  + onUpdate() void
  - updateUI() void
}
class GraveyardManagementWindow {
  + GraveyardManagementWindow(GraveyardManagementModuleView) 
  + onOpened() void
  + onUpdate() void
}
class HappinessWindowCitizen {
  + HappinessWindowCitizen(ICitizenDataView) 
  + onOpened() void
}
class IWindowModule {
<<Interface>>
  + onOpened() void
  + onButtonClicked(Button) void
  + onUpdate() void
  + onClosed() void
}
class IWindowWithLayoutModule {
<<Interface>>
  + onLayoutMounted(Pane) void
   ResourceLocation layout
}
class ItemListModuleWindow {
  + ItemListModuleWindow(IItemListModuleView, ResourceLocation) 
  # updateResourceList() void
  - switchClicked(Button) void
  - reset() void
  + onUpdate() void
  + onOpened() void
  - updateResources() void
  # applySorting(List~ItemStorage~) void
}
class JobWindowCitizen {
  + JobWindowCitizen(ICitizenDataView) 
}
class MainWindowCitizen {
  + MainWindowCitizen(ICitizenDataView) 
  + onButtonClicked(Button) void
  + onOpened() void
  + onUpdate() void
   ICitizenDataView citizen
}
class MinecraftMap {
  + MinecraftMap() 
  + MinecraftMap(PaneParams) 
  + drawSelf(BOGuiGraphics, double, double) void
  - freeTexture() void
  + close() void
   MapItemSavedData mapData
}
class MinimumStockModuleWindow {
  + MinimumStockModuleWindow(IMinimumStockModuleView) 
  + MinimumStockModuleWindow(IMinimumStockModuleView, ResourceLocation) 
  + onOpened() void
  - updateStockList() void
  - addStock() void
  - removeStock(Button) void
}
class PlantationFieldsModuleWindow {
  + PlantationFieldsModuleWindow(PlantationFieldsModuleView) 
  + onUpdate() void
  + onOpened() void
  - assignmentModeClicked(Button) void
  - assignClicked(Button) void
  - setAssignButtonTexture(ButtonImage, boolean) void
  - updateUI() void
}
class RequestTreeWindowModule {
  + RequestTreeWindowModule(AbstractWindowSkeleton, IColonyView) 
  - List~RequestWrapper~ cachedOpenRequests
  - detailedClicked(Button) void
  + isFulfillable(IRequest~?~) boolean
  + refreshOpenRequests() void
  + onLayoutMounted(Pane) void
  + onUpdate() void
  + openDetails(IRequest~?~) void
  # canDisplayChildRequests() boolean
  - onFulfill(Button) void
  + cancel(IRequest~?~) void
  - cancel(Button) void
  - constructTreeFromRequest(IRequest~?~, List~RequestWrapper~, int) void
  + isCancellable(IRequest~?~) boolean
  # onCancel(IRequest~?~) void
   ResourceLocation layout
   List~RequestWrapper~ cachedOpenRequests
   Collection~IRequest~?~~ openRequests
}
class RequestWindowCitizen {
  + RequestWindowCitizen(ICitizenDataView) 
  + RequestWindowCitizen(ICitizenDataView, IRequest~?~?) 
  + onOpened() void
}
class RestaurantMenuModuleWindow {
  + RestaurantMenuModuleWindow(RestaurantMenuModuleView) 
  # applySorting(List~ItemStorage~) void
  + onOpened() void
  - updateStockList() void
  - updateResources() void
  + onUpdate() void
  - removeStock(Button) void
  - switchClicked(Button) void
  # updateResourceList() void
}
class RotatingItemIcon {
  + RotatingItemIcon() 
  - List~ItemStack~ items
  - int duration
  + drawSelf(BOGuiGraphics, double, double) void
  - updateItem() void
  - resetState() void
   List~ItemStack~ items
   int duration
}
class SettingsModuleWindow {
  + SettingsModuleWindow(SettingsModuleView) 
  + onOpened() void
  - updateSettingsList() void
}
class SpecialAssignmentModuleWindow {
  + SpecialAssignmentModuleWindow(IAssignmentModuleView, ResourceLocation) 
  + onOpened() void
  # hireClicked(Button) void
  - recallClicked() void
}
class TabsWindowModule {
  + TabsWindowModule(AbstractWindowSkeleton, Random) 
  - int tabYOffset
  - int tabYSpacing
  - int tabXOffset
  + renderTabButton(int, TabImageSide, ResourceLocation, MutableComponent?, ButtonHandler) void
   int tabXOffset
   int tabYSpacing
   int tabYOffset
}
class ToolModuleWindow {
  + ToolModuleWindow(ToolModuleView) 
  - givePlayerScepter() void
}
class UniversityModuleWindow {
  + UniversityModuleWindow(UniversityResearchModuleView) 
  + getHidingRequirementDesc(ResourceLocation) List~MutableComponent~
  - inventoryClicked() void
  + onButtonClicked(Button) void
  + updateResearchCount(int) void
}
class WarehouseOptionsModuleWindow {
  + WarehouseOptionsModuleWindow(WarehouseOptionsModuleView) 
  + onOpened() void
  - updateResourcePane() void
  - sortWarehouse() void
  - transferItems() void
}
class WindowAlliancePage {
  + WindowAlliancePage(View) 
  - requestAlly(Button) void
  - updateEvents() void
  - startFeud(Button) void
  - updateConnections(ScrollingList, List~ColonyConnection~) void
  + onUpdate() void
  - getColonyDataFromPane(Button) ColonyConnection
  - acceptAlly(Button) void
   String windowId
   Button neutral
}
class WindowBarracksBuilding {
  + WindowBarracksBuilding(View) 
  - mountDistanceString(BlockPos) Component
  + onOpened() void
  - hireSpiesClicked(Button) void
}
class WindowBrewingstandCrafting {
  + WindowBrewingstandCrafting(ContainerCraftingBrewingstand, Inventory, Component) 
  # renderBg(GuiGraphics, float, int, int) void
  + render(GuiGraphics, int, int, float) void
  # init() void
   AbstractBuildingView buildingView
}
class WindowBuilderResModule {
  + WindowBuilderResModule(BuildingResourcesModuleView) 
  + onOpened() void
  + onUpdate() void
  - transferItems(Button) void
  - pullResourcesFromHut() void
  - updateResourcePane(int, Pane) void
}
class WindowBuildingInventory {
  + WindowBuildingInventory(ContainerBuildingInventory, Inventory, Component) 
  + render(GuiGraphics, int, int, float) void
  # renderBg(GuiGraphics, float, int, int) void
  # renderLabels(GuiGraphics, int, int) void
}
class WindowCitizenInventory {
  + WindowCitizenInventory(ContainerCitizenInventory, Inventory, Component) 
  - ICitizen citizenData
  # renderBg(GuiGraphics, float, int, int) void
  + renderEntityInInventoryFollowsAngle(GuiGraphics, int, int, int, float, float, LivingEntity) void
  + onClose() void
  + render(GuiGraphics, int, int, float) void
  + renderEntityInInventoryFollowsMouse(GuiGraphics, int, int, int, float, float, Optional~Entity~) void
  # renderLabels(GuiGraphics, int, int) void
  + renderEntityInInventory(GuiGraphics, int, int, int, Quaternionf, Quaternionf?, LivingEntity) void
   ICitizen citizenData
}
class WindowCitizenPage {
  + WindowCitizenPage(View) 
  - recallOneClicked(Button) void
  - citizenSelected(Button) void
  - fillCitizensList() void
  - fillHappinessList() void
  - updateCitizens() void
  - fillCitizenInfo() void
  + onUpdate() void
   String windowId
}
class WindowColonyMap {
  + WindowColonyMap(boolean, IBuildingView) 
  + List~ColonyInfo~ colonies
  - updateScale() void
  - updateColonyInfoImage(ColonyInfo) void
  - addCenterPos() void
  - updateBuildingView(IBuildingView) void
  - addCitizens(IColonyView) void
  + getPlayerResolvedRequestsForBuilding(IBuildingView) ImmutableList~IRequest~?~~
  - addMaps() boolean
  + onUpdate() void
  + onClosed() void
  - inventoryClicked() void
  - worldPosToUIPos(BlockPos) BlockPos
   List~ColonyInfo~ colonies
}
class WindowColonyPrestigeRanking {
  + WindowColonyPrestigeRanking(boolean, IBuildingView) 
}
class WindowCrafting {
  + WindowCrafting(ContainerCrafting, Inventory, Component) 
  - boolean completeCrafting
  # init() void
  + render(GuiGraphics, int, int, float) void
  # renderBg(GuiGraphics, float, int, int) void
  # containerTick() void
  - onDoneClicked(Button) void
  # renderLabels(GuiGraphics, int, int) void
  - matchingRequest(IRequest~?~) boolean
  - reopenWithRequest(IRequest~?~?) void
   boolean completeCrafting
   AbstractBuildingView buildingView
}
class WindowField {
  + WindowField(TileEntityScarecrow) 
  - updateElementStates() void
  - onDirectionalButtonClick(Button) void
  - updateSeed() void
  + onUpdate() void
  - updateAll() void
  - updateOwner() void
  - selectSeed() void
  - updateButtons() void
  - getDirectionalTranslationKey(Direction) String
  - updateFarmField() void
   ItemStack seed
   IColonyView? currentColony
}
class WindowFurnaceCrafting {
  + WindowFurnaceCrafting(ContainerCraftingFurnace, Inventory, Component) 
  + render(GuiGraphics, int, int, float) void
  - matchingRequest(IRequest~?~) boolean
  - reopenWithRequest(IRequest~?~?) void
  # init() void
  # renderBg(GuiGraphics, float, int, int) void
   AbstractBuildingView buildingView
}
class WindowGrave {
  + WindowGrave(ContainerGrave, Inventory, Component) 
  # renderLabels(GuiGraphics, int, int) void
  - getCorrectTextureForSlots(int) ResourceLocation
  # renderBg(GuiGraphics, float, int, int) void
  + render(GuiGraphics, int, int, float) void
}
class WindowHutBuilderModule {
  + WindowHutBuilderModule(View, boolean) 
  + WindowHutBuilderModule(View) 
  + onOpened() void
}
class WindowHutLiving {
  + WindowHutLiving(LivingBuildingView) 
  - assignClicked() void
  - recallClicked() void
  + onOpened() void
  - refreshView() void
}
class WindowHutMinerModule {
  + WindowHutMinerModule(MinerLevelManagementModuleView) 
  - repairClicked(Button) void
  - mineLevelClicked(Button) void
  + onOpened() void
}
class WindowHutRequestTaskModule {
  + WindowHutRequestTaskModule(RequestTaskModuleView) 
  + onOpened() void
}
class WindowHutWorkerModulePlaceholder~B~ {
  + WindowHutWorkerModulePlaceholder(B) 
}
class WindowInfoPage {
  + WindowInfoPage(View) 
  + onOpened() void
  - fillEventsList() void
  - fillWorkOrderList() void
  + onUpdate() void
  - onDropDownListChanged(DropDownList) void
  - updateWorkOrders() void
  - updatePriority(Button) void
  - deleteWorkOrder(Button) void
  - sortWorkOrders() void
   String windowId
}
class WindowListRecipes {
  + WindowListRecipes(CraftingModuleView) 
  - removeClicked(Button) void
  - toggleRecipe(Button) void
  + onUpdate() void
  - forwardClicked(Button) void
  + craftingClicked() void
  - getStackWithCount(ItemStorage) ItemStack
  + onOpened() void
  - backwardClicked(Button) void
}
class WindowMainPage {
  + WindowMainPage(View) 
  - openBannerPicker(Button) void
  - patreonClicked() void
  + onUpdate() void
  - toggleNameFile(DropDownList) void
  - switchPack() void
  + checkFeatureUnlock() void
  - renameClicked() void
  - mercenaryClicked() void
  - initDropDowns() void
  - resetTextureStyle() void
  - toggleTexture(DropDownList) void
  - onDropDownListChanged(DropDownList) void
  - mapButtonClicked() void
  + onOpened() void
   String windowId
}
class WindowMineGuardModule {
  + WindowMineGuardModule(MinerGuardAssignModuleView) 
  + onUpdate() void
  + onOpened() void
  - pullGuardsFromHut() void
  - assignGuardClicked(Button) void
   int maxGuards
}
class WindowPermissionsPage {
  + WindowPermissionsPage(View) 
  - changeRankMode(DropDownList) void
  - removeBlock(Button) void
  - isValidRankname(String) boolean
  - giveBlockTool(Button) void
  - addRank() void
  - addBlock() void
  - fillUserList() void
  - fillFreeBlockList() void
  - addPlayerToColonyClicked(Button) void
  - fillnonAddedPlayerList() void
  - updateRanks() void
  - onPickPlayer(Button) void
  - updateUsers() void
  + onUpdate() void
  - removePlayerClicked(Button) void
  - fillRanks() void
  - addPlayerCLicked() void
  - trigger(Button) void
  - onRemoveRankButtonClicked(Button) void
  - onRankButtonClicked(Button) void
  - onRankSelected(DropDownList) void
  - fillEventsList() void
  - fillPermissionList() void
  + onOpened() void
   String windowId
}
class WindowQuestLog {
  + WindowQuestLog(IColonyView) 
  + onUpdate() void
  - locateCitizenClickedInternal(Button) void
}
class WindowQuestLogAvailableQuestModule {
  + WindowQuestLogAvailableQuestModule() 
  - setText(Pane, String, Component) void
  + renderQuestItem(IQuestInstance, IColonyView, Pane) void
  + trackQuest(IQuestInstance) void
  + getQuestItems(IColonyView) List~IQuestInstance~
  - getQuestGiverName(IColonyView, IQuestInstance) Component
}
class WindowQuestLogFinishedQuestModule {
  + WindowQuestLogFinishedQuestModule() 
  - setText(Pane, String, Component) void
  + getQuestItems(IColonyView) List~FinishedQuest~
  + renderQuestItem(FinishedQuest, IColonyView, Pane) void
}
class WindowQuestLogInProgressQuestQuestModule {
  + WindowQuestLogInProgressQuestQuestModule() 
  + getQuestItems(IColonyView) List~IQuestInstance~
  + trackQuest(IQuestInstance) void
  - setText(Pane, String, Component) void
  + renderQuestItem(IQuestInstance, IColonyView, Pane) void
  - getQuestGiverName(IColonyView, IQuestInstance) Component
}
class WindowQuestLogQuestModule~T~ {
<<Interface>>
  + trackQuest(T) void
  + getQuestItems(IColonyView) List~T~
  + renderQuestItem(T, IColonyView, Pane) void
}
class WindowRack {
  + WindowRack(ContainerRack, Inventory, Component) 
  # renderBg(GuiGraphics, float, int, int) void
  - getCorrectTextureForSlots(int) ResourceLocation
  # renderLabels(GuiGraphics, int, int) void
  + render(GuiGraphics, int, int, float) void
}
class WindowSelectRequest {
  + WindowSelectRequest(CraftingModuleView, Predicate~IRequest~?~~, Consumer~IRequest~?~?~) 
  + onOpened() void
  - updateRequests() void
  - cancel() void
  + onUpdate() void
  - select(Button) void
   List~IRequest~?~~ openRequests
}
class WindowSettings {
  + WindowSettings(View) 
  + onUpdate() void
  + onOpened() void
   String windowId
}
class WindowStatsModule {
  + WindowStatsModule(BuildingStatisticsModuleView) 
  - updateStats() void
  + onOpened() void
  - onDropDownListChanged(DropDownList) void
  - hideZeroClicked(Button) void
}
class WindowStatsPage {
  + WindowStatsPage(View) 
  - updateStats() void
  - onDropDownListChanged(DropDownList) void
  + onOpened() void
  - createAndSetStatistics() void
   String windowId
}
class WindowTownHallCantCreateColony {
  + WindowTownHallCantCreateColony(BlockPos, MutableComponent, boolean) 
  - pickup() void
}
class WindowTownHallColonyManage {
  + WindowTownHallColonyManage(BlockPos, String, int, String, boolean) 
  + onCreate() void
}
class WindowTownHallColonyReactivate {
  + WindowTownHallColonyReactivate(BlockPos, String, int) 
  + onCreate() void
}
class WindowTownHallDeleteAbandonColony {
  + WindowTownHallDeleteAbandonColony(BlockPos, String, BlockPos) 
  - confirmDeleteColony() void
  - abandonColony() void
  - confirmAbandonColony() void
  - deleteColony() void
  - cancel() void
  - pickup() void
}
class WindowTownHallMercenary {
  + WindowTownHallMercenary(IColonyView) 
  + onButtonClicked(Button) void
}
class WindowTownHallNameEntry {
  + WindowTownHallNameEntry(IColonyView) 
  + onButtonClicked(Button) void
  + onOpened() void
}
class WorkOrderModuleWindow {
  + WorkOrderModuleWindow(WorkOrderListModuleView) 
  + onUpdate() void
  - sortWorkOrders() void
  - selectWorkOrder(Button) void
  + onOpened() void
  - updateWorkOrders() void
  - updateAvailableWorkOrders(int, Pane) void
}
class ZoomDragMap {
  + ZoomDragMap(PaneParams) 
  + ZoomDragMap() 
  + double scrollX
  - double scale
  + double scrollY
  + scrollInput(double, double, double) boolean
  # childIsVisible(Pane) boolean
  + mouseEventProcessor(double, double, MouseEventCallback, MouseEventCallback, MouseEventCallback) boolean
  + drawSelfLast(BOGuiGraphics, double, double) void
  + addChildFirst(Pane) void
  + onMouseDrag(double, double, int, double, double) boolean
  + addChild(Pane) void
  - calcRelativeY(double) double
  - calcInverseAbsoluteX(double) double
  + parseChildren(PaneParams) void
  - calcRelativeX(double) double
  - calcInverseAbsoluteY(double) double
  + drawSelf(BOGuiGraphics, double, double) void
  # computeContentSize() void
   double scrollX
   double scrollY
   double scale
}

AbstractWindowCitizen  ..>  FamilyWindowCitizen : «create»
AbstractWindowCitizen  ..>  HappinessWindowCitizen : «create»
AbstractWindowCitizen  ..>  JobWindowCitizen : «create»
AbstractWindowCitizen  ..>  MainWindowCitizen : «create»
AbstractWindowCitizen  ..>  RequestWindowCitizen : «create»
AbstractWindowTownHall  ..>  WindowAlliancePage : «create»
AbstractWindowTownHall  ..>  WindowCitizenPage : «create»
AbstractWindowTownHall  ..>  WindowInfoPage : «create»
AbstractWindowTownHall  ..>  WindowMainPage : «create»
AbstractWindowTownHall  ..>  WindowPermissionsPage : «create»
AbstractWindowTownHall  ..>  WindowSettings : «create»
AbstractWindowTownHall  ..>  WindowStatsPage : «create»
DOCraftingWindow  ..>  WindowSelectRequest : «create»
FamilyWindowCitizen  -->  AbstractWindowCitizen 
HappinessWindowCitizen  -->  AbstractWindowCitizen 
IWindowWithLayoutModule  -->  IWindowModule 
JobWindowCitizen  -->  AbstractWindowCitizen 
MainWindowCitizen  -->  AbstractWindowCitizen 
RequestTreeWindowModule  ..>  IWindowWithLayoutModule 
RequestWindowCitizen  -->  AbstractWindowCitizen 
RequestWindowCitizen "1" *--> "requestTreeModule 1" RequestTreeWindowModule 
TabsWindowModule  ..>  IWindowModule 
WindowAlliancePage  -->  AbstractWindowTownHall 
WindowCitizenPage  -->  AbstractWindowTownHall 
WindowColonyMap  ..>  MinecraftMap : «create»
WindowColonyMap  ..>  WindowColonyPrestigeRanking : «create»
WindowColonyMap  ..>  ZoomDragMap : «create»
WindowColonyMap "1" *--> "dragView 1" ZoomDragMap 
WindowColonyMap "1" *--> "maps *" MinecraftMap 
WindowColonyPrestigeRanking  ..>  WindowColonyMap : «create»
WindowCrafting  ..>  WindowSelectRequest : «create»
WindowFurnaceCrafting  ..>  WindowSelectRequest : «create»
WindowInfoPage  -->  AbstractWindowTownHall 
WindowMainPage  -->  AbstractWindowTownHall 
WindowMainPage  ..>  WindowColonyMap : «create»
WindowMainPage  ..>  WindowTownHallMercenary : «create»
WindowMainPage  ..>  WindowTownHallNameEntry : «create»
WindowPermissionsPage  -->  AbstractWindowTownHall 
WindowQuestLog  ..>  WindowQuestLogAvailableQuestModule : «create»
WindowQuestLog  ..>  WindowQuestLogFinishedQuestModule : «create»
WindowQuestLog  ..>  WindowQuestLogInProgressQuestQuestModule : «create»
WindowQuestLogAvailableQuestModule  ..>  WindowQuestLogQuestModule~T~ 
WindowQuestLogFinishedQuestModule  ..>  WindowQuestLogQuestModule~T~ 
WindowQuestLogInProgressQuestQuestModule  ..>  WindowQuestLogQuestModule~T~ 
WindowSettings  -->  AbstractWindowTownHall 
WindowStatsPage  -->  AbstractWindowTownHall 
WindowTownHallColonyReactivate  ..>  WindowTownHallColonyManage : «create»
```
