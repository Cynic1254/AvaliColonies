# core.network (cont. 2)

64 classes, 0 internal relationships shown.

```mermaid
classDiagram
direction BT

class AddMinimumStockToBuildingModuleMessage {
  + AddMinimumStockToBuildingModuleMessage() 
  + AddMinimumStockToBuildingModuleMessage(IBuildingView, ItemStack, int) 
  + onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
}
class AddRemoveRecipeMessage {
  + AddRemoveRecipeMessage() 
  + AddRemoveRecipeMessage(IBuildingView, List~ItemStorage~, int, ItemStack, List~ItemStack~, boolean, int) 
  + AddRemoveRecipeMessage(IBuildingView, List~ItemStorage~, int, ItemStack, boolean, Block, int) 
  + AddRemoveRecipeMessage(IBuildingView, boolean, IRecipeStorage, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
}
class AdjustSkillCitizenMessage {
  + AdjustSkillCitizenMessage() 
  + AdjustSkillCitizenMessage(IColony, ICitizenDataView, int, Skill) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class AlterRestaurantMenuItemMessage {
  + AlterRestaurantMenuItemMessage() 
  - AlterRestaurantMenuItemMessage(IBuildingView, ItemStack, int, boolean) 
  + toBytesOverride(FriendlyByteBuf) void
  + addMenuItem(IBuildingView, ItemStack, int) AlterRestaurantMenuItemMessage
  + removeMenuItem(IBuildingView, ItemStack, int) AlterRestaurantMenuItemMessage
  + onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class AssignFieldMessage {
  + AssignFieldMessage() 
  + AssignFieldMessage(IBuildingView, IBuildingExtension, boolean, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
}
class AssignFilterableEntityMessage {
  + AssignFilterableEntityMessage(IBuildingView, int, ResourceLocation, boolean) 
  + AssignFilterableEntityMessage() 
  + onExecute(Context, boolean, IColony, AbstractBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class AssignFilterableItemMessage {
  + AssignFilterableItemMessage() 
  + AssignFilterableItemMessage(IBuildingView, int, ItemStorage, boolean) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, AbstractBuilding) void
}
class AssignUnassignMessage {
  + AssignUnassignMessage() 
  + AssignUnassignMessage(IBuildingView, boolean, int, JobEntry) 
  + toBytesOverride(FriendlyByteBuf) void
  + errorIfCastFails() boolean
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, DefaultBuildingInstance) void
}
class AssignmentModeMessage {
  + AssignmentModeMessage() 
  + AssignmentModeMessage(IBuildingView, boolean, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
}
class BuildPickUpMessage {
  + BuildPickUpMessage() 
  + BuildPickUpMessage(IBuildingView) 
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
}
class BuildRequestMessage {
  + BuildRequestMessage(IBuildingView, Mode, BlockPos) 
  + BuildRequestMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
}
class BuilderSelectWorkOrderMessage {
  + BuilderSelectWorkOrderMessage() 
  + BuilderSelectWorkOrderMessage(IBuildingView, int) 
  # onExecute(Context, boolean, IColony, BuildingBuilder) void
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
}
class BuildingHiringModeMessage {
  + BuildingHiringModeMessage(IBuildingView, HiringMode, int) 
  + BuildingHiringModeMessage() 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
}
class BuildingSetStyleMessage {
  + BuildingSetStyleMessage(IBuildingView, String) 
  + BuildingSetStyleMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
}
class ChangeDeliveryPriorityMessage {
  + ChangeDeliveryPriorityMessage(IBuildingView, boolean) 
  + ChangeDeliveryPriorityMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class ChangeFreeToInteractBlockMessage {
  + ChangeFreeToInteractBlockMessage(IColonyView, Block, MessageType) 
  + ChangeFreeToInteractBlockMessage() 
  + ChangeFreeToInteractBlockMessage(IColonyView, BlockPos, MessageType) 
  # onExecute(Context, boolean, IColony) void
  + fromBytesOverride(FriendlyByteBuf) void
  + permissionNeeded() Action?
  + toBytesOverride(FriendlyByteBuf) void
}
class ChangeRecipePriorityMessage {
  + ChangeRecipePriorityMessage() 
  + ChangeRecipePriorityMessage(IBuildingView, int, boolean, int, boolean) 
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class ColonyAbandonOwnMessage {
  + ColonyAbandonOwnMessage() 
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
   LogicalSide? executionSide
}
class ColonyDeleteOwnMessage {
  + ColonyDeleteOwnMessage() 
  + onExecute(Context, boolean) void
  + toBytes(FriendlyByteBuf) void
  + fromBytes(FriendlyByteBuf) void
   LogicalSide? executionSide
}
class ColonyFlagChangeMessage {
  + ColonyFlagChangeMessage() 
  + ColonyFlagChangeMessage(IColony, ListTag) 
  # fromBytesOverride(FriendlyByteBuf) void
  # toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class ColonyNameStyleMessage {
  + ColonyNameStyleMessage(IColony, String) 
  + ColonyNameStyleMessage() 
  # fromBytesOverride(FriendlyByteBuf) void
  # toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class ColonyStructureStyleMessage {
  + ColonyStructureStyleMessage() 
  + ColonyStructureStyleMessage(IColony, String) 
  # onExecute(Context, boolean, IColony) void
  # fromBytesOverride(FriendlyByteBuf) void
  # toBytesOverride(FriendlyByteBuf) void
}
class ColonyTextureStyleMessage {
  + ColonyTextureStyleMessage() 
  + ColonyTextureStyleMessage(IColony, String) 
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
  # toBytesOverride(FriendlyByteBuf) void
}
class CourierHiringModeMessage {
  + CourierHiringModeMessage() 
  + CourierHiringModeMessage(IBuildingView, HiringMode, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
}
class EnchanterWorkerSetMessage {
  + EnchanterWorkerSetMessage(IBuildingView, BlockPos, boolean) 
  + EnchanterWorkerSetMessage() 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, BuildingEnchanter) void
}
class FarmFieldPlotResizeMessage {
  + FarmFieldPlotResizeMessage() 
  + FarmFieldPlotResizeMessage(int, Direction, BlockPos) 
  + fromBytes(FriendlyByteBuf) void
  + onExecute(Context, boolean) void
  + toBytes(FriendlyByteBuf) void
}
class FarmFieldRegistrationMessage {
  + FarmFieldRegistrationMessage(IColony, BlockPos) 
  + FarmFieldRegistrationMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony) void
}
class FarmFieldUpdateSeedMessage {
  + FarmFieldUpdateSeedMessage(IColony, ItemStack, BlockPos) 
  + FarmFieldUpdateSeedMessage() 
  # onExecute(Context, boolean, IColony) void
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
}
class ForcePickupMessage {
  + ForcePickupMessage(IBuildingView) 
  + ForcePickupMessage() 
  # onExecute(Context, boolean, IColony, IBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class GiveToolMessage {
  + GiveToolMessage(IBuildingView, Item) 
  + GiveToolMessage() 
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, AbstractBuilding) void
  # toBytesOverride(FriendlyByteBuf) void
}
class GuardSetMinePosMessage {
  + GuardSetMinePosMessage() 
  + GuardSetMinePosMessage(View, BlockPos) 
  + GuardSetMinePosMessage(View) 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, AbstractBuildingGuards) void
}
class HireFireMessage {
  + HireFireMessage() 
  + HireFireMessage(IBuildingView, boolean, int, int) 
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class HireMercenaryMessage {
  + HireMercenaryMessage() 
  + HireMercenaryMessage(IColony) 
  # onExecute(Context, boolean, IColony) void
  # toBytesOverride(FriendlyByteBuf) void
  + permissionNeeded() Action?
  # fromBytesOverride(FriendlyByteBuf) void
}
class HireSpiesMessage {
  + HireSpiesMessage(IColony) 
  + HireSpiesMessage() 
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
  # toBytesOverride(FriendlyByteBuf) void
}
class HutRenameMessage {
  + HutRenameMessage(IBuildingView, String) 
  + HutRenameMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class InteractionClose {
  + InteractionClose() 
  + InteractionClose(int, int, ResourceKey~Level~, Component) 
  # onExecute(Context, boolean, IColony) void
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
}
class InteractionResponse {
  + InteractionResponse() 
  + InteractionResponse(int, int, ResourceKey~Level~, Component, int) 
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class MarkBuildingDirtyMessage {
  + MarkBuildingDirtyMessage() 
  + MarkBuildingDirtyMessage(IBuildingView) 
  # toBytesOverride(FriendlyByteBuf) void
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class MinerRepairLevelMessage {
  + MinerRepairLevelMessage(IBuildingView, int) 
  + MinerRepairLevelMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, BuildingMiner) void
}
class MinerSetLevelMessage {
  + MinerSetLevelMessage() 
  + MinerSetLevelMessage(IBuildingView, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, BuildingMiner) void
  + toBytesOverride(FriendlyByteBuf) void
}
class OpenCraftingGUIMessage {
  + OpenCraftingGUIMessage(AbstractBuildingView, int) 
  + OpenCraftingGUIMessage() 
  # onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
}
class OpenInventoryMessage {
  + OpenInventoryMessage(IBuildingView) 
  + OpenInventoryMessage() 
  + OpenInventoryMessage(IColonyView, String, int) 
  - doHutInventory(ServerPlayer, IColony) void
  + toBytesOverride(FriendlyByteBuf) void
  - doCitizenInventory(ServerPlayer) void
  # onExecute(Context, boolean, IColony) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class PauseCitizenMessage {
  + PauseCitizenMessage() 
  + PauseCitizenMessage(AbstractBuildingView, int) 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class PostBoxRequestMessage {
  + PostBoxRequestMessage(IBuildingView, ItemStack, int, boolean) 
  + PostBoxRequestMessage() 
  # onExecute(Context, boolean, IColony, PostBox) void
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class QuarryHiringModeMessage {
  + QuarryHiringModeMessage() 
  + QuarryHiringModeMessage(IBuildingView, HiringMode, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, IBuilding) void
  + toBytesOverride(FriendlyByteBuf) void
}
class RecallCitizenHutMessage {
  + RecallCitizenHutMessage() 
  + RecallCitizenHutMessage(AbstractBuildingView) 
  # toBytesOverride(FriendlyByteBuf) void
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class RecallCitizenMessage {
  + RecallCitizenMessage(IBuildingView) 
  + RecallCitizenMessage() 
  # toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
  # fromBytesOverride(FriendlyByteBuf) void
}
class RecallSingleCitizenMessage {
  + RecallSingleCitizenMessage() 
  + RecallSingleCitizenMessage(IBuildingView, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class RemoveMinimumStockFromBuildingModuleMessage {
  + RemoveMinimumStockFromBuildingModuleMessage(IBuildingView, ItemStack, int) 
  + RemoveMinimumStockFromBuildingModuleMessage() 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class ResetFilterableItemMessage {
  + ResetFilterableItemMessage() 
  + ResetFilterableItemMessage(IBuildingView, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony, AbstractBuilding) void
}
class RestartCitizenMessage {
  + RestartCitizenMessage(AbstractBuildingView, int) 
  + RestartCitizenMessage() 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class SortBuildingMessage {
  + SortBuildingMessage(IBuildingView) 
  + SortBuildingMessage() 
  # toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
  # fromBytesOverride(FriendlyByteBuf) void
}
class TeamColonyColorChangeMessage {
  + TeamColonyColorChangeMessage() 
  + TeamColonyColorChangeMessage(int, IBuildingView) 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class TeleportToColonyMessage {
  + TeleportToColonyMessage(ResourceKey~Level~, int, BlockPos, int, int) 
  + TeleportToColonyMessage() 
  + permissionNeeded() Action?
  # onExecute(Context, boolean, IColony) void
  # fromBytesOverride(FriendlyByteBuf) void
  # toBytesOverride(FriendlyByteBuf) void
}
class ToggleRecipeMessage {
  + ToggleRecipeMessage(IBuildingView, int, int) 
  + ToggleRecipeMessage() 
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class TownHallRenameMessage {
  + TownHallRenameMessage(IColonyView, String) 
  + TownHallRenameMessage() 
  # onExecute(Context, boolean, IColony) void
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
}
class TransferItemsRequestMessage {
  + TransferItemsRequestMessage() 
  + TransferItemsRequestMessage(IBuildingView, ItemStack, int, boolean) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, IBuilding) void
}
class TransferItemsToCitizenRequestMessage {
  + TransferItemsToCitizenRequestMessage() 
  + TransferItemsToCitizenRequestMessage(IColony, ICitizenDataView, ItemStack, int) 
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
  + toBytesOverride(FriendlyByteBuf) void
}
class TriggerConnectionEventMessage {
  + TriggerConnectionEventMessage(IColony, ConnectionEvent, int) 
  + TriggerConnectionEventMessage() 
  # toBytesOverride(FriendlyByteBuf) void
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
class TriggerSettingMessage {
  + TriggerSettingMessage(IColony, ISettingKey~?~, ISetting, int, BlockPos) 
  + TriggerSettingMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  + onExecute(Context, boolean, IColony) void
}
class TryResearchMessage {
  + TryResearchMessage() 
  + TryResearchMessage(IBuildingView, ResourceLocation, ResourceLocation, boolean) 
  + fromBytesOverride(FriendlyByteBuf) void
  + toBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, BuildingUniversity) void
}
class UpdateRequestStateMessage {
  + UpdateRequestStateMessage(IColony, IToken~?~, RequestState, ItemStack) 
  + UpdateRequestStateMessage() 
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
  + toBytesOverride(FriendlyByteBuf) void
}
class UpgradeWarehouseMessage {
  + UpgradeWarehouseMessage() 
  + UpgradeWarehouseMessage(IBuildingView) 
  # fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony, BuildingWareHouse) void
  # toBytesOverride(FriendlyByteBuf) void
}
class WorkOrderChangeMessage {
  + WorkOrderChangeMessage() 
  + WorkOrderChangeMessage(IBuildingView, int, boolean, int) 
  + toBytesOverride(FriendlyByteBuf) void
  + fromBytesOverride(FriendlyByteBuf) void
  # onExecute(Context, boolean, IColony) void
}
```
