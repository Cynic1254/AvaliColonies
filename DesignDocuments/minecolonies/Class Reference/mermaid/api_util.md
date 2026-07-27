# api.util

65 classes, 4 internal relationships shown.

```mermaid
classDiagram
direction BT

class BaseGameTranslationConstants {
  + BaseGameTranslationConstants() 
}
class BlockPosUtil {
  - BlockPosUtil() 
  + getFurthestCorner(BlockPos, BlockPos, BlockPos) BlockPos
  + dist(BlockPos, int, int, int) double
  + chunkDistanceSquared(ChunkPos, ChunkPos) int
  + getBlockDrops(Level, BlockPos, int, ItemStack, LivingEntity) List~ItemStack~
  + getRandomPosition(Level, BlockPos, BlockPos, int, int) BlockPos
  + setBlock(Level, BlockPos, BlockState, int) boolean
  + getValidHeight(Vec3, Level) double
  + equals(int, int, int, int, int, int) boolean
  + getDistanceSquared(BlockPos, BlockPos) long
  + getRandomPosAround(BlockPos, int) BlockPos
  + set(MutableBlockPos, BlockPos) void
  + getRotationFromRotations(int) Rotation
  + read(CompoundTag, String) BlockPos
  + isInArea(BlockPos, BlockPos, BlockPos) boolean
  + getFloor(MutableBlockPos, int, Level) BlockPos?
  + distManhattan(BlockPos, BlockPos) int
  + writeToListNBT(ListTag, BlockPos) void
  + getBlockPosOfString(String) BlockPos?
  + dist(int, int, int, int, int, int) double
  + findAround(Level, BlockPos, int, int, BiPredicate~BlockGetter, BlockPos~) BlockPos?
  + findLand(BlockPos, Level) BlockPos
  + calcDirection(BlockPos, BlockPos) DirectionResult
  + getString(BlockPos) String
  + getFacing(BlockPos, BlockPos) Direction
  + getMaxDistance2D(BlockPos, BlockPos) int
  + writeOptional(CompoundTag, String, BlockPos?) CompoundTag
  + distSqr(int, int, int, int, int, int) int
  + readFromListNBT(ListTag, int) BlockPos
  + getTileEntity(Level, BlockPos) BlockEntity
  + directionFromDelta(int, int, int) Direction
  + writePosListToNBT(CompoundTag, String, List~BlockPos~) void
  + getBlockState(Level, BlockPos) BlockState
  + getXZFacing(int, int, int, int) Direction
  + readOrNull(CompoundTag, String) BlockPos?
  + getBlock(Level, BlockPos) Block
  + fromEntity(Entity) BlockPos
  + getDistanceSquared2D(BlockPos, BlockPos) long
  + getRandomPosition(Level, BlockPos, BlockPos, int, int, boolean) BlockPos
  + findSpawnPosAround(Level, BlockPos) BlockPos
  + distManhattan(int, int, int, int, int, int) int
  + readPosListFromNBT(CompoundTag, String) List~BlockPos~
  + write(CompoundTag, String, BlockPos) CompoundTag
  + getChunkAlignedBB(BlockPos, int) BoundingBox
  + getDistance2D(BlockPos, BlockPos) long
  + distSqr(BlockPos, BlockPos) int
  + getFloor(BlockPos, Level) BlockPos
  + getDistance(BlockPos, BlockPos) double
  + distManhattan(BlockPos, int, int, int) int
  + equals(BlockPos, int, int, int) boolean
  + getXZFacing(BlockPos, BlockPos) Direction
  + dist(BlockPos, BlockPos) double
  + equals(BlockPos, BlockPos) boolean
  + distSqr(BlockPos, int, int, int) int
}
class BlockStateStorage {
  + BlockStateStorage(BlockState, List~Property~?~~, boolean) 
  - BlockState state
  + equals(Object) boolean
  + hashCode() int
   List~Property~?~~ compareProperties
   BlockState state
}
class BlockStateUtils {
  - BlockStateUtils() 
  + stateEqualsStateByBlockAndProp(BlockState, BlockState, String) boolean
  + stateEqualsStateWithoutProp(BlockState, BlockState, Property~T~) boolean
  + stateEqualsStateInPropertyByName(BlockState, BlockState, String) boolean
  + getPropertyByName(Collection~Property~?~~, String) Property~?~?
  + stateEqualsStateInBlockAndProp(BlockState, BlockState) boolean
  + getPropertyByNameFromState(BlockState, String) Property~?~
}
class BuildingConstants {
  - BuildingConstants() 
}
class ChunkCapData {
  + ChunkCapData(int, int) 
  + ChunkCapData(int, int, int, List~Integer~, Map~Integer, Set~BlockPos~~) 
  - int owningColony
  - List~Integer~ staticColonyClaim
  + fromBytes(FriendlyByteBuf) ChunkCapData
  + toBytes(FriendlyByteBuf) void
   Map~Integer, Set~BlockPos~~ allClaimingBuildings
   List~Integer~ staticColonyClaim
   int owningColony
}
class ChunkLoadStorage {
  + ChunkLoadStorage(int, long, boolean, ResourceLocation, boolean) 
  + ChunkLoadStorage(int, long, ResourceLocation, BlockPos, boolean) 
  + ChunkLoadStorage(CompoundTag) 
  - long xz
  - ResourceLocation dimension
  + applyToCap(IColonyTagCapability, LevelChunk) void
  + hashCode() int
  + toNBT() CompoundTag
  + equals(Object) boolean
  - getCompoundOfColonyId(int) CompoundTag
  - readTupleFromNbt(CompoundTag) Tuple~Short, BlockPos~
  + merge(ChunkLoadStorage) void
  - writeTupleToNBT(Tuple~Short, BlockPos~) CompoundTag
   long xz
   boolean empty
   ResourceLocation dimension
}
class CitizenConstants {
  + CitizenConstants() 
}
class ColonyConstants {
  - ColonyConstants() 
}
class ColonyManagerConstants {
  - ColonyManagerConstants() 
}
class ColonyUtils {
  - ColonyUtils() 
  + queueBlueprintLoad(Level, String, String, Consumer~Blueprint~) CompletableFuture~Blueprint~
  + calculateCorners(AABB) Tuple~BlockPos, BlockPos~
  + getAllClaimingBuildings(LevelChunk) Map~Integer, Set~BlockPos~~
  + getStaticClaims(LevelChunk) List~Integer~
  + calculateCorners(BlockPos, Level, Blueprint, int, boolean) Tuple~BlockPos, BlockPos~
  + getChunkCapData(LevelChunk) ChunkCapData?
  + queueBlueprintLoad(Level, String, String, Consumer~Blueprint~, Consumer~String~) CompletableFuture~Blueprint~
  + getOwningColony(LevelChunk) int
}
class CommandTranslationConstants {
  + CommandTranslationConstants() 
}
class CompatibilityUtils {
  - CompatibilityUtils() 
  + getWorldFromEntity(Entity) Level
  + addEntity(Level, Entity) void
  + getWorldFromCitizen(AbstractEntityCitizen) Level
}
class Constants {
  - Constants() 
}
class CraftingUtils {
  - CraftingUtils() 
  + calculateMaxCraftingCount(ItemStack, IRecipeStorage) int
  + calculateMaxCraftingCount(int, IRecipeStorage) int
  + forEachCreativeTabItems(ItemDisplayParameters, BiConsumer~CreativeModeTab, Collection~ItemStack~~) void
  + getIngredientValidatorBasedOnTags(String, boolean) OptionalPredicate~ItemStack~
  + getProductValidatorBasedOnTags(String) OptionalPredicate~ItemStack~
  + getIngredientValidatorBasedOnTags(String) OptionalPredicate~ItemStack~
  - onCreativeModeTabBuildContents(CreativeModeTab, ResourceKey~CreativeModeTab~, DisplayItemsGenerator, ItemDisplayParameters, Output) void
  + isRecipeCompatibleBasedOnTags(IGenericRecipe, String) Optional~Boolean~
}
class CreativeBuildingStructureHandler {
  + CreativeBuildingStructureHandler(Level, BlockPos, Future~Blueprint~, PlacementSettings, boolean) 
  + CreativeBuildingStructureHandler(Level, BlockPos, Blueprint, PlacementSettings, boolean) 
  + triggerSuccess(BlockPos, List~ItemStack~, boolean) void
  + loadAndPlaceStructureWithRotation(Level, Future~Blueprint~, BlockPos, Rotation, Mirror, boolean, ServerPlayer?) Blueprint?
  + isStackFree(ItemStack?) boolean
  + onCompletion() void
  - setupBuilding() void
}
class DamageSourceKeys {
  + DamageSourceKeys() 
}
class DebugTranslationConstants {
  + DebugTranslationConstants() 
}
class EntityUtils {
  - EntityUtils() 
  + getPlayerOfFakePlayer(Player, Level) Player
  + isLivingAtSite(LivingEntity, int, int, int, int) boolean
  + checkForFreeSpace(Level, BlockPos) boolean
  - checkValidSpawn(BlockGetter, BlockPos, int) boolean
  + pushableBy() Predicate~Entity~
  + getEntitiesFromID(Level, List~Integer~) List~Entity~
  + isLivingAtSiteWithMove(LivingEntity, int, int, int, int) boolean
  + getSpawnPoint(Level, BlockPos) BlockPos?
  + tryMoveLivingToXYZ(Mob, int, int, int, double) boolean
  + solidOrLiquid(Level, BlockPos) boolean
  + getPlayerByUUID(Level, UUID) Entity
  + updateRotation(double, double, double) double
  + isFlying(LivingEntity) boolean
  + tryMoveLivingToXYZ(Mob, int, int, int) boolean
}
class EquipmentLevelConstants {
  - EquipmentLevelConstants() 
}
class FireworkUtils {
  - FireworkUtils() 
  + spawnFireworksAtAABBCorners(Tuple~BlockPos, BlockPos~, Level, int) void
  - fireRocket(Level, BlockPos, int) void
  - genFireworkItemStack(int) ItemStack
}
class FoodUtils {
  + FoodUtils() 
  + canEatLevel(ItemStack, int) boolean
  + checkForFoodInBuilding(ICitizenData, Set~ItemStorage~?, IBuilding) ItemStorage?
  + getBestFoodForCitizen(InventoryCitizen, ICitizenData, Set~ItemStorage~?) int
  + hasBestOptionInInv(InventoryCitizen, ICitizenData, Set~ItemStorage~?, IBuilding) boolean
  + getFoodValue(ItemStack, AbstractEntityCitizen) double
  + getBuildingLevelForFood(ItemStack) int
  + getMinFoodDiversityRequirement(int) int
  + canEat(ItemStack, IBuilding, IBuilding) boolean
  + getFoodTier(double) int
  + getMinFoodQualityRequirement(int) int
  + getFoodValue(ItemStack, FoodProperties?, double) double
}
class GuardConstants {
  - GuardConstants() 
}
class GuiTranslationConstants {
  + GuiTranslationConstants() 
}
class HappinessConstants {
  - HappinessConstants() 
}
class IHasDirty {
<<Interface>>
  + checkDirty() boolean
  + clearDirty() void
  + markDirty() void
}
class InventoryConstants {
  - InventoryConstants() 
}
class InventoryFunctions {
  - InventoryFunctions() 
  + matchFirstInProviderWithAction(ICapabilityProvider, Predicate~ItemStack~, IMatchActionResult) boolean
  + matchFirstInHandlerWithAction(IItemHandler, Predicate~ItemStack~, IMatchActionResultHandler) boolean
  + matchFirstInProvider(ICapabilityProvider, Predicate~ItemStack~) boolean
  + matchFirstInProviderWithSimpleAction(ICapabilityProvider, Predicate~ItemStack~, Consumer~Integer~) boolean
  - matchInHandler(IItemHandler?, Function~IItemHandler, Function~Integer, Predicate~ItemStack~~~) boolean
  + matchFirstInProvider(ICapabilityProvider, BiPredicate~Integer, ItemStack~) boolean
  - matchInProvider(ICapabilityProvider?, Function~ICapabilityProvider, Function~Integer, Predicate~ItemStack~~~, boolean) boolean
}
class InventoryUtils {
  - InventoryUtils() 
  + filterProvider(ICapabilityProvider, Predicate~ItemStack~) List~ItemStack~
  + isEquipmentInProviderForSide(ICapabilityProvider, Direction?, EquipmentTypeEntry, int, int) boolean
  + mergeItemStackIntoNextBestSlotInItemHandlers(IItemHandler, int, IItemHandler) void
  - findFirstSlotInItemHandlerNotEmptyWith(IItemHandler, List~Predicate~ItemStack~~) int
  + getAmountOfStacksInProvider(ICapabilityProvider) int
  + transferXOfFirstSlotInProviderWithIntoNextFreeSlotInProviderWithResult(ICapabilityProvider, Predicate~ItemStack~, int, ICapabilityProvider) int
  + clearItemHandler(IItemHandler) void
  + isProviderSided(ICapabilityProvider) boolean
  + areAllItemsInItemHandler(List~ItemStack~, IItemHandler) boolean
  + hasItemInProvider(ICapabilityProvider, Predicate~ItemStack~) boolean
  + putItemToHotbarAndSelectOrDrop(ItemStack, Player) boolean
  + getItemCountInProvider(ICapabilityProvider, Predicate~ItemStack~) int
  + transferXOfFirstSlotInItemHandlerWithIntoNextFreeSlotInItemHandlerWithResult(IItemHandler, Predicate~ItemStack~, int, IItemHandler) int
  - updateHeldItemFromServer(Player) void
  + reduceBucketAwareStackInItemHandler(IItemHandler, ItemStack) void
  + hasItemInProviderForSide(ICapabilityProvider, Direction?, Block) boolean
  + findFirstSlotInProviderNotEmptyWith(ICapabilityProvider, List~Predicate~ItemStack~~) int
  + isBuildingFull(IBuilding) boolean
  + reduceBucketAwareStackInItemHandler(IItemHandler, ItemStack, int) void
  + filterItemHandler(IItemHandler, Item) List~ItemStack~
  + transferItemStackIntoNextFreeSlotFromProvider(ICapabilityProvider, int, IItemHandler) boolean
  + addItemStackToProviderWithResult(ICapabilityProvider, ItemStack?) ItemStack
  + hasProviderIItemHandler(ICapabilityProvider) boolean
  + hasItemHandlerEquipmentWithLevel(IItemHandler, EquipmentTypeEntry, int, int) boolean
  + mergeItemStackIntoNextBestSlotInItemHandlers(ItemStack, IItemHandler) ItemStack
  + attemptReduceStackInItemHandler(IItemHandler, ItemStack, int, boolean, boolean) boolean
  + shrinkItemCountInItemHandler(IItemHandler, Predicate~ItemStack~) boolean
  + getOrCreateItemAndPutToHotbarAndSelectOrDrop(Item, Player, Supplier~ItemStack~, boolean) ItemStack
  + getMergedCountedStacksFromList(List~ItemStack~) Map~ItemStack, Integer~
  + transferItemStackIntoNextBestSlotFromProvider(ICapabilityProvider, int, IItemHandler) boolean
  + isProviderFull(ICapabilityProvider, Direction?) boolean
  + processItemStackListAndMerge(List~ItemStack~) List~ItemStack~
  + getItemCountInProviderForSide(ICapabilityProvider, Direction?, Block) int
  + transferItemStackIntoNextBestSlotInItemHandler(IItemHandler, int, IItemHandler) boolean
  + hasItemInProviderForSide(ICapabilityProvider, Direction?, Predicate~ItemStack~) boolean
  + transferXOfFirstSlotInItemHandlerWithIntoInItemHandler(IItemHandler, Predicate~ItemStack~, int, IItemHandler, int) int
  + filterProvider(ICapabilityProvider, Block) List~ItemStack~
  + getAmountOfStacksInItemHandler(IItemHandler) int
  + getItemHandlersFromProvider(ICapabilityProvider) Set~IItemHandler~
  + removeStacksFromProvider(ICapabilityProvider, List~ItemStack~) boolean
  + forceItemStackToProvider(ICapabilityProvider, ItemStack, Predicate~ItemStack~) ItemStack?
  + getFirstOpenSlotFromProviderForSide(ICapabilityProvider, Direction?) int
  + filterItemHandlerFromProviderForSide(ICapabilityProvider, Direction?, Block) List~ItemStack~
  + moveItemStacksWithPossibleSwap(IItemHandler, Collection~IItemHandler~, List~ItemStack~, Predicate~ItemStack~) boolean
  + filterItemHandler(IItemHandler, Block) List~ItemStack~
  + swapItemStacksInItemHandlers(IItemHandler, int, IItemHandler, int) boolean
  + hasItemInItemHandler(IItemHandler?, Item) boolean
  + splitMergedCountedStacksIntoMaxContentStacks(Map~ItemStack, Integer~) List~ItemStack~
  + getAllItemsForProviders(Set~IItemHandler~) Map~ItemStorage, ItemStorage~
  + getContainedFromItemHandler(List~ItemStack~, IItemHandler) List~ItemStack~
  + findFirstSlotInItemHandlerWith(IItemHandler, Block) int
  + reduceStackInItemHandler(IItemHandler, ItemStack) void
  + getItemCountInItemHandler(IItemHandler?, Block) int
  + findSlotInProviderNotFullWithItem(ICapabilityProvider, Item, int) int
  + hasItemInProvider(ICapabilityProvider, Item) boolean
  + transferItemStackIntoNextFreeSlotInProvider(IItemHandler, int, ICapabilityProvider) boolean
  + reduceBuildingThenPlayerInventory(IBuilding, Player, ItemStorage, Predicate~ItemStack~, Predicate~ItemStack~) ItemStorage
  + transferItemStackIntoNextBestSlotInItemHandler(IItemHandler, Predicate~ItemStack~, IItemHandler) boolean
  + reduceStackInItemHandler(IItemHandler, ItemStack, int) void
  + transferXOfFirstSlotInItemHandlerWithIntoNextFreeSlotInItemHandler(IItemHandler, Predicate~ItemStack~, int, IItemHandler) boolean
  + transferXInItemHandlerIntoSlotInItemHandler(IItemHandler, Predicate~ItemStack~, int, IItemHandler, int) int
  + getFirstMatch(List~ItemStack~, Predicate~ItemStack~) ItemStack?
  + transferXOfItemStackIntoNextFreeSlotInItemHandler(IItemHandler, int, int, IItemHandler) boolean
  + hasBuildingEnoughElseCount(ICommonBuilding, Predicate~ItemStack~, int) int
  + spawnItemStack(Level, double, double, double, ItemStack) void
  + getAllItemsForProviders(ICapabilityProvider, IItemHandler[]) Map~ItemStorage, ItemStorage~
  + openSlotCount(IItemHandler?) long
  + forceItemStackToItemHandler(IItemHandler, ItemStack, Predicate~ItemStack~) ItemStack?
  + findFirstSlotInProviderForSideWith(ICapabilityProvider, Direction?, Predicate~ItemStack~) int
  + transferXOfItemStackIntoNextFreeSlotFromProvider(ICapabilityProvider, int, int, IItemHandler) boolean
  + getFirstSlotOfItemHandlerContainingEquipment(IItemHandler, EquipmentTypeEntry, int, int) int
  + transferXOfFirstSlotInProviderWithIntoNextFreeSlotInItemHandler(ICapabilityProvider, Predicate~ItemStack~, int, IItemHandler) boolean
  + filterProvider(ICapabilityProvider, Item?) List~ItemStack~
  + getProviderAsList(ICapabilityProvider) List~ItemStack~
  + removeStacksFromItemHandler(IItemHandler, List~ItemStack~) boolean
  + addItemStackToItemHandlerWithResult(IItemHandler, ItemStack?) ItemStack
  + transferItemStackIntoNextFreeSlotFromItemHandler(IItemHandler, Predicate~ItemStack~, int, IItemHandler) boolean
  - getFromProviderForAllSides(ICapabilityProvider, Predicate~ItemStack~) List~ItemStack~
  + getCountFromBuilding(IBuilding, List~ItemStorage~) int
  + hasItemInItemHandler(IItemHandler?, Block) boolean
  + findFirstSlotInProviderWith(ICapabilityProvider, Item) int
  + transferItemStackIntoNextFreeSlotInItemHandler(IItemHandler, int, IItemHandler) boolean
  + transferXOfFirstSlotInProviderWithIntoNextFreeSlotInProvider(ICapabilityProvider, Predicate~ItemStack~, int, ICapabilityProvider) boolean
  + doStorageSetsMatch(Map~ItemStorage, ItemStorage~, Map~ItemStorage, ItemStorage~, boolean) boolean
  + findFirstSlotInItemHandlerNotEmptyWith(IItemHandler, Predicate~ItemStack~) int
  + attemptReduceStackInItemHandler(IItemHandler, ItemStack, int) boolean
  + findAllSlotsInProviderWith(ICapabilityProvider, Predicate~ItemStack~) Map~IItemHandler, List~Integer~~
  + findFirstSlotInProviderNotEmptyWith(ICapabilityProvider, Predicate~ItemStack~) int
  + dropItemHandler(IItemHandler, Level, int, int, int) void
  + findFirstSlotInProviderWith(ICapabilityProvider, Block) int
  + getItemCountInProviderForSide(ICapabilityProvider, Direction?, Predicate~ItemStack~) int
  + getItemCountInStackLick(List~ItemStack~, Predicate~ItemStack~) int
  + getItemCountInItemHandlers(Collection~IItemHandler~?, Predicate~ItemStack~) int
  + transferItemStackIntoNextBestSlotInItemHandler(IBuilding, ItemStorage, IItemHandler) boolean
  + isEquipmentInProvider(ICapabilityProvider, EquipmentTypeEntry, int, int) boolean
  + putItemToHotbarAndSelectOrDropMessage(ItemStack, Player) boolean
  + filterItemHandler(IItemHandler, Predicate~ItemStack~) List~ItemStack~
  + transferItemStackIntoNextBestSlotInItemHandler(ItemStack, IItemHandler) boolean
  + transferItemStackIntoNextBestSlotInItemHandler(IBuilding, ItemStorage, int, IItemHandler) boolean
  + addItemStackToItemHandler(IItemHandler, ItemStack?) boolean
  + findFirstSlotInProviderForSideWith(ICapabilityProvider, Direction?, Block, int) int
  + getItemCountInProvider(ICapabilityProvider, Block) int
  + transferItemStackIntoNextBestSlotInItemHandlerWithResult(ItemStack, IItemHandler) ItemStack
  - compareItems(ItemStack?, Item) boolean
  + tryRemoveStackFromItemHandler(IItemHandler, ItemStack) boolean
  + countEmptySlotsInBuilding(IBuilding) int
  + filterItemHandlerFromProviderForSide(ICapabilityProvider, Direction?, Item, int) List~ItemStack~
  + getItemCountInItemHandler(IItemHandler?, Item) int
  + findAllSlotsInItemHandlerWith(IItemHandler, Predicate~ItemStack~) List~Integer~
  + hasItemInItemHandler(IItemHandler?, Predicate~ItemStack~) boolean
  + isItemHandlerFull(IItemHandler?) boolean
  + isProviderFull(ICapabilityProvider) boolean
  + getAllItemsForProviders(IItemHandler[]) Map~ItemStorage, ItemStorage~
  + hasEnoughInProvider(BlockEntity, ItemStack, int) boolean
  + getDurabilityInProvider(ICapabilityProvider, Predicate~ItemStack~) int
  + transferAllItemHandler(IItemHandler, IItemHandler) boolean
  + hasBuildingEnoughElseCount(ICommonBuilding, ItemStorage, int) int
  + getBuildingInventory(IBuilding) List~ItemStack~
  + isEquipmentInItemHandler(IItemHandler, EquipmentTypeEntry, int, int) boolean
  + filterItemHandlerFromProviderForSide(ICapabilityProvider, Direction?, Predicate~ItemStack~) List~ItemStack~
  + removeStackFromItemHandler(IItemHandler, ItemStack, int) void
  + getCountFromBuilding(IBuilding, ItemStorage) int
  + transferXOfFirstSlotInProviderWithIntoNextFreeSlotInItemHandlerWithResult(ICapabilityProvider, Predicate~ItemStack~, int, IItemHandler) int
  + areAllItemsInItemHandlerList(List~ItemStack~, Collection~IItemHandler~) boolean
  + findFirstSlotInItemHandlerWith(IItemHandler, Item) int
  + getCountFromBuildingWithLimit(IBuilding, Predicate~ItemStack~, Function~ItemStack, Integer~) int
  + findSlotInItemHandlerNotFullWithItem(IItemHandler, ItemStack) boolean
  + hasItemInProvider(ICapabilityProvider, Block) boolean
  + getItemCountInProvider(ICapabilityProvider, Item) int
  + getFirstOpenSlotFromItemHandler(IItemHandler?) int
  + getItemHandlerAsList(IItemHandler) List~ItemStack~
  + getItemFromBlock(Block) Item
  + getItemCountInItemHandler(IItemHandler?, Predicate~ItemStack~) int
  + findFirstSlotInProviderForSideWith(ICapabilityProvider, Direction?, Item) int
  + findFirstSlotInItemHandlerWith(IItemHandler, Predicate~ItemStack~) int
  + addItemStackToProvider(ICapabilityProvider, ItemStack?) boolean
  + getFirstOpenSlotFromProvider(ICapabilityProvider) int
  + getCountFromBuilding(IBuilding, Predicate~ItemStack~) int
  + transferFoodUpToSaturation(ICapabilityProvider, IItemHandler, int, Predicate~ItemStack~) Object2IntMap~ItemStack~
  + findSlotInItemHandlerNotFullWithItem(IItemHandler, Predicate~ItemStack~, int) int
  + hasItemInProviderForSide(ICapabilityProvider, Direction?, Item) boolean
  + areAllItemsInProvider(List~ItemStack~, ICapabilityProvider) boolean
  + getInventoryAsListFromProviderForSide(ICapabilityProvider, Direction?) List~ItemStack~
  + getItemCountInProviderForSide(ICapabilityProvider, Direction?, Item) int
}
class ItemStackUtils {
  - ItemStackUtils() 
  + verifyEquipmentLevel(ItemStack, int, int, int) boolean
  + isBetterEquipment(ItemStack, ItemStack) boolean
  + hasTag(ItemStack) boolean
  + doesItemServeAsWeapon(ItemStack) boolean
  + deserializeFromNBT(CompoundTag) ItemStack
  + compareItemStacksIgnoreStackSize(ItemStack, ItemStack, boolean, boolean) boolean
  + getItemStackAttributeValue(ItemStack, Attribute) double
  + isEmpty(ItemStack?) boolean
  + getFortuneOf(ItemStack?) int
  + compareItemStackListIgnoreStackSize(List~ItemStack~, ItemStack, boolean, boolean) boolean
  + changeSize(ItemStack, int) void
  + compareItemStacksIgnoreStackSize(ItemStack, ItemStack, boolean, boolean, boolean) boolean
  + compareItemStacksIgnoreStackSize(ItemStack, ItemStack, boolean, boolean, boolean, boolean) boolean
  + hasFuelInFurnaceAndNoSmeltable(FurnaceBlockEntity) boolean
  + hasNeitherFuelNorBrewable(BrewingStandBlockEntity) boolean
  + hasFuelAndNoBrewable(BrewingStandBlockEntity) boolean
  + compareItemStackListIgnoreStackSize(List~ItemStack~, ItemStack) boolean
  + areItemStacksMergable(ItemStack, ItemStack) Boolean
  + setSize(ItemStack, int) void
  + swapToolGrade(int) MutableComponent
  + allItemsPlusInventory(Player) List~ItemStack~
  + getListOfStackForEntity(Entity, Entity) List~ItemStorage~
  + hasSmeltableInFurnaceAndNoFuel(FurnaceBlockEntity) boolean
  + consumeFood(ItemStack, AbstractEntityCitizen, Inventory) void
  + getSize(ItemStack) int
  + getDurability(ItemStack) int
  + getArmorLevel(ItemStack) int
  + hasBrewableAndNoFuel(BrewingStandBlockEntity) boolean
  + getMaxEnchantmentLevel(ItemStack) int
  + compareItemStorageListIgnoreStackSize(List~ItemStorage~, ItemStack) boolean
  + isStackSapling(ItemStack?) boolean
  + parseIdTemplate(String?, ResourceLocation) Tuple~Boolean, String~
  + isNotEmpty(ItemStack?) boolean
  + idToItemStack(String) ItemStack
  + hasEquipmentLevel(ItemStack?, EquipmentTypeEntry, int, int) boolean
  + swapArmorGrade(int) MutableComponent
  + compareItemStacksIgnoreStackSize(ItemStack, ItemStack) Boolean
  + hasNeitherFuelNorSmeltAble(FurnaceBlockEntity) boolean
  - getArmorValue(ItemStack) double
}
class JobTranslationConstants {
  + JobTranslationConstants() 
}
class LoadOnlyStructureHandler {
  + LoadOnlyStructureHandler(Level, BlockPos, Future~Blueprint~, PlacementSettings) 
  + LoadOnlyStructureHandler(Level, BlockPos, Blueprint, PlacementSettings) 
  + isStackFree(ItemStack?) boolean
  + triggerSuccess(BlockPos, List~ItemStack~, boolean) void
   boolean creative
}
class Log {
  - Log() 
  - Logger logger
   Logger logger
}
class LookHandler {
  + LookHandler(Mob) 
  + setLookAt(Entity, float, float) void
  + tick() void
   Entity lookAt
   int lookAtCooldown
}
class MathUtils {
  - MathUtils() 
  + square(double) double
  + twoDimDistance(BlockPos, BlockPos) double
  + clamp(int, int, int) int
}
class MessageUtils {
  + MessageUtils() 
  + forCitizen(AbstractEntityCitizen, String, Object[]) MessageBuilder
  + forCitizen(AbstractEntityCitizen, Component) MessageBuilder
  + format(String, Object[]) MessageBuilder
  + format(Component) MessageBuilder
}
class NBTUtils {
  + NBTUtils() 
  + toListNBT() Collector~CompoundTag, ?, ListTag~
  + streamBase(ListTag) Stream~Tag~
  + streamCompound(ListTag) Stream~CompoundTag~
}
class NameConstants {
  + NameConstants() 
}
class NbtTagConstants {
  - NbtTagConstants() 
}
class OptionalPredicate~T~ {
<<Interface>>
  + or(OptionalPredicate~T~) OptionalPredicate~T~
  + orElse(boolean) Predicate~T~
  + negate() OptionalPredicate~T~
  + empty() OptionalPredicate~T~
  + failIf(Predicate~T~) OptionalPredicate~T~
  + combine(OptionalPredicate~T~) OptionalPredicate~T~
  + and(OptionalPredicate~T~) OptionalPredicate~T~
  + combine(Optional~X~, Supplier~Optional~X~~) Optional~X~
  + test(T) Optional~Boolean~
  + passIf(Predicate~T~) OptionalPredicate~T~
  + of(Predicate~T~) OptionalPredicate~T~
}
class PathingConstants {
  - PathingConstants() 
}
class Pond {
  + Pond() 
  + checkPond(BlockGetter, BlockPos, MutableBlockPos?) PondState
  + checkWaterForFishing(BlockGetter, BlockPos) PondState
}
class ProgressTranslationConstants {
  + ProgressTranslationConstants() 
}
class RSConstants {
  - RSConstants() 
}
class ReflectionUtils {
  - ReflectionUtils() 
  + getSuperClasses(TypeToken~T~) Set~TypeToken~?~~
   AppenderControl FMLLoggingLevelOnConsoleToDebug
}
class RequestSystemTranslationConstants {
  + RequestSystemTranslationConstants() 
}
class SchematicTagConstants {
  + SchematicTagConstants() 
   String[] plantationTags
}
class SerializationIdentifierConstants {
  - SerializationIdentifierConstants() 
}
class ShapeUtil {
  + ShapeUtil() 
  + min(VoxelShape, Axis) double
  + hasCollision(BlockGetter, BlockPos, BlockState) boolean
  + isEmpty(VoxelShape) boolean
  + hasCollision(BlockGetter, int, int, int, BlockState) boolean
  + hasCollision(BlockState, VoxelShape) boolean
  + getEndY(VoxelShape, double) double
  + max(VoxelShape, Axis) double
  + getStartY(VoxelShape, double) double
}
class SoundUtils {
  - SoundUtils() 
  + playSoundAtCitizenWith(Level, BlockPos, EventType?, ICivilianData?, double, double) void
  + playSoundAtCitizenWith(Level, BlockPos, EventType?, ICivilianData?) void
  + playSuccessSound(Player, BlockPos) void
  + getRandomPentatonic(RandomSource) double
  + playSoundAtCitizen(Level, BlockPos, SoundEvent) void
  + playErrorSound(Player, BlockPos) void
  + playSoundForPlayer(ServerPlayer, SoundEvent, float, float) void
  + playRandomSound(Level, BlockPos, ICitizenData) void
  + playSoundAtCitizenWith(Level, BlockPos, EventType?, ICivilianData?, double) void
  + getRandomPitch(RandomSource) double
}
class StatisticsConstants {
  + StatisticsConstants() 
}
class StatsUtil {
  + StatsUtil() 
  + trackStatByName(IBuilding, String, Component, int) void
  + trackStat(IBuilding, String, int) void
  + trackStatByName(IBuilding, String, String, int) void
  + trackStatFromFurnace(IBuilding, String, FurnaceBlockEntity, int) void
  + trackStatByStackMap(IBuilding, String, Object2IntMap~ItemStack~) void
  + trackStatByStack(IBuilding, String, ItemStack, int) void
}
class Suppression {
  - Suppression() 
}
class TagConstants {
  - TagConstants() 
}
class TagUtils {
  - TagUtils() 
  + getItem(ResourceLocation) TagKey~Item~
  + getBlock(ResourceLocation) TagKey~Block~
}
class ToolTranslationConstants {
  + ToolTranslationConstants() 
}
class TranslationConstants {
  - TranslationConstants() 
}
class Tuple~A, B~ {
  + Tuple(A?, B?) 
  - A a
  - B b
  + equals(Object) boolean
  + hashCode() int
   A? a
   B? b
}
class TypeConstants {
  + TypeConstants() 
}
class UndertakerConstants {
  + UndertakerConstants() 
}
class Utils {
  - Utils() 
  + toggleFlag(long, long) long
  + isBlockInRange(Level, Block, int, int, int, int) boolean
  + unsetFlag(long, long) long
  + mask(long, long) long
  + setFlag(long, long) long
  + checkDirectory(File) void
  + splitPath(String) String[]
  + getBlueprintLevel(String) int
  + format(long) String
  + testFlag(long, long) boolean
  + resolvePath(Path, String) Path
}
class Vec2i {
  + Vec2i(int, int) 
  - int x
  - int z
  + distanceSq(Vec2i) long
  + hashCode() int
  + distanceSq(int, int) long
  + equals(Object) boolean
   int z
   int x
}
class Vec3Mutable {
  + Vec3Mutable(double, double, double) 
  ~ double y
  ~ double z
  ~ double x
  + asVec3() Vec3
  + createEmpty() Vec3Mutable
  + set(double, double, double) void
  + asBlockPos() BlockPos
  + empty() boolean
  + toString() String
  + setEmpty() void
   int xi
   int yi
   double x
   double y
   int zi
   double z
}
class WindowConstants {
  - WindowConstants() 
}
class WorldUtil {
  + WorldUtil() 
  + markChunkDirty(Level, BlockPos) void
  + isChunkLoaded(LevelAccessor, int, int) boolean
  + isBlockLoaded(LevelAccessor, BlockPos) boolean
  + getDimensionMinHeight(DimensionType) int
  + isPastTime(Level, int) boolean
  + getNearestEntity(List~T~, Mob?, int, int, int, double) T?
  + isEntityBlockLoaded(LevelAccessor, BlockPos) boolean
  + isAABBLoaded(Level, AABB) boolean
  + getDimensionMaxHeight(DimensionType) int
  + isInWorldHeight(int, Level) boolean
  + isPeaceful(Level) boolean
  + removeBlock(LevelAccessor, BlockPos, boolean) boolean
  + isNetherType(Level) boolean
  + isChunkLoaded(LevelAccessor, ChunkPos) boolean
  + isOfWorldType(Level, ResourceKey~DimensionType~) boolean
  + isOverworldType(Level) boolean
  + setBlockState(LevelAccessor, BlockPos, BlockState, int) boolean
  + isEntityChunkLoaded(LevelAccessor, int, int) boolean
  + getEntitiesWithinBuilding(Level, Class~T~, IBuilding, Predicate~T~?) List~T~
  + isDayTime(Level) boolean
  + isPastNoon(Level) boolean
  + setBlockState(LevelAccessor, BlockPos, BlockState) boolean
  + isEntityChunkLoaded(LevelAccessor, ChunkPos) boolean
  + getNearestPlayer(Mob, int, int, int, double) Player?
}

ChunkLoadStorage  ..>  Tuple~A, B~ : «create»
ChunkLoadStorage "1" *--> "claimingBuilding *" Tuple~A, B~ 
ColonyUtils  ..>  ChunkCapData : «create»
ItemStackUtils  ..>  Tuple~A, B~ : «create»
```
