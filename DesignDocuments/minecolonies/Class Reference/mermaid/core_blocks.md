# core.blocks

71 classes, 0 internal relationships shown.

```mermaid
classDiagram
direction BT

class BlockBarrel {
  + BlockBarrel() 
  + rotate(BlockState, Rotation) BlockState
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + mirror(BlockState, Mirror) BlockState
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
   ResourceLocation registryName
}
class BlockColonyFlagBanner {
  + BlockColonyFlagBanner() 
  + mirror(BlockState, Mirror) BlockState
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + rotate(BlockState, Rotation) BlockState
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  # createBlockStateDefinition(Builder~Block, BlockState~) void
   ResourceLocation registryName
}
class BlockColonyFlagWallBanner {
  + BlockColonyFlagWallBanner() 
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + rotate(BlockState, Rotation) BlockState
  + mirror(BlockState, Mirror) BlockState
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + getStateForPlacement(BlockPlaceContext) BlockState?
   ResourceLocation registryName
   String descriptionId
}
class BlockColonySign {
  + BlockColonySign() 
  + destroy(LevelAccessor, BlockPos, BlockState) void
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + onRemove(BlockState, Level, BlockPos, BlockState, boolean) void
  + registerBlockItem(IForgeRegistry~Item~, Properties) void
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity, ItemStack) void
  + getRenderShape(BlockState) RenderShape
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
   ResourceLocation registryName
}
class BlockCompostedDirt {
  + BlockCompostedDirt() 
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + canSustainPlant(BlockState, BlockGetter, BlockPos, Direction, IPlantable) boolean
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
   ResourceLocation registryName
}
class BlockConstructionTape {
  + BlockConstructionTape() 
  + getConnections(BlockGetter, BlockPos, Direction, boolean) List~Direction~
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + propagatesSkylightDown(BlockState, BlockGetter, BlockPos) boolean
  # canRemoveTStem(BlockGetter, BlockPos, Direction) boolean
  # canConnect(BlockGetter, BlockPos, Direction) boolean
  + getPlacementState(BlockState?, BlockGetter, BlockPos, Direction) BlockState
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + onLand(Level, BlockPos, BlockState, BlockState, FallingBlockEntity) void
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  # createBlockStateDefinition(Builder~Block, BlockState~) void
   ResourceLocation registryName
}
class BlockDecorationController {
  + BlockDecorationController() 
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity, ItemStack) void
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + rotate(BlockState, Rotation) BlockState
  + mirror(BlockState, Mirror) BlockState
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + getRenderShape(BlockState) RenderShape
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + getLevel(CompoundTag) int
  + getFluidState(BlockState) FluidState
   ResourceLocation registryName
}
class BlockGate {
  + BlockGate(String, float, int, int) 
}
class BlockHutAlchemist {
  + BlockHutAlchemist() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutArchery {
  + BlockHutArchery() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBaker {
  + BlockHutBaker() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBarracks {
  + BlockHutBarracks() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBarracksTower {
  + BlockHutBarracksTower() 
  + isVisible(CompoundTag?) boolean
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBeekeeper {
  + BlockHutBeekeeper() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBlacksmith {
  + BlockHutBlacksmith() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutBuilder {
  + BlockHutBuilder() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutChickenHerder {
  + BlockHutChickenHerder() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutCitizen {
  + BlockHutCitizen() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutCombatAcademy {
  + BlockHutCombatAcademy() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutComposter {
  + BlockHutComposter() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutConcreteMixer {
  + BlockHutConcreteMixer() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutCook {
  + BlockHutCook() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutCowboy {
  + BlockHutCowboy() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutCrusher {
  + BlockHutCrusher() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutDeliveryman {
  + BlockHutDeliveryman() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutDyer {
  + BlockHutDyer() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutEnchanter {
  + BlockHutEnchanter() 
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutFarmer {
  + BlockHutFarmer() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutFisherman {
  + BlockHutFisherman() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutFletcher {
  + BlockHutFletcher() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutFlorist {
  + BlockHutFlorist() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutGateHouse {
  + BlockHutGateHouse() 
  + canRightClickWithoutPermissions() boolean
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutGlassblower {
  + BlockHutGlassblower() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutGraveyard {
  + BlockHutGraveyard() 
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutGuardTower {
  + BlockHutGuardTower() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutHospital {
  + BlockHutHospital() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutKitchen {
  + BlockHutKitchen() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutLibrary {
  + BlockHutLibrary() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutLumberjack {
  + BlockHutLumberjack() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutMechanic {
  + BlockHutMechanic() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutMiner {
  + BlockHutMiner() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutMysticalSite {
  + BlockHutMysticalSite() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutNetherWorker {
  + BlockHutNetherWorker() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutPlantation {
  + BlockHutPlantation() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutRabbitHutch {
  + BlockHutRabbitHutch() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutSawmill {
  + BlockHutSawmill() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutSchool {
  + BlockHutSchool() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutShepherd {
  + BlockHutShepherd() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutSifter {
  + BlockHutSifter() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutSmeltery {
  + BlockHutSmeltery() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutStable {
  + BlockHutStable() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutStoneSmeltery {
  + BlockHutStoneSmeltery() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutStonemason {
  + BlockHutStonemason() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutSwineHerder {
  + BlockHutSwineHerder() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutTavern {
  + BlockHutTavern() 
  + canPlaceAt(BlockPos, Player) boolean
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutTownHall {
  + BlockHutTownHall() 
  + getDestroyProgress(BlockState, Player, BlockGetter, BlockPos) float
  + canPlaceAt(BlockPos, Player) boolean
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  + getRequirements(ClientLevel, BlockPos, LocalPlayer) List~MutableComponent~
   boolean validBreak
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutUniversity {
  + BlockHutUniversity() 
   BuildingEntry buildingEntry
   String hutName
}
class BlockHutWareHouse {
  + BlockHutWareHouse() 
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
   BuildingEntry buildingEntry
   String hutName
}
class BlockMinecoloniesGrave {
  + BlockMinecoloniesGrave() 
  + onRemove(BlockState, Level, BlockPos, BlockState, boolean) void
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + getPlacementState(BlockState, BlockPos) BlockState
  + getCollisionShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + propagatesSkylightDown(BlockState, BlockGetter, BlockPos) boolean
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + rotate(BlockState, Rotation) BlockState
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity?, ItemStack) void
  + mirror(BlockState, Mirror) BlockState
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
   ResourceLocation registryName
}
class BlockMinecoloniesNamedGrave {
  + BlockMinecoloniesNamedGrave() 
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity?, ItemStack) void
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + getPlacementState(BlockState, BlockEntity, BlockPos) BlockState
  + mirror(BlockState, Mirror) BlockState
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + rotate(BlockState, Rotation) BlockState
   ResourceLocation registryName
}
class BlockMinecoloniesRack {
  + BlockMinecoloniesRack() 
  + getCollisionShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + propagatesSkylightDown(BlockState, BlockGetter, BlockPos) boolean
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + rotate(BlockState, Rotation) BlockState
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + getDrops(BlockState, Builder) List~ItemStack~
  + onRemove(BlockState, Level, BlockPos, BlockState, boolean) void
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + mirror(BlockState, Mirror) BlockState
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
   Block block
   ResourceLocation registryName
   Collection~IMateriallyTexturedBlockComponent~ components
}
class BlockPlantationField {
  + BlockPlantationField() 
  + mirror(BlockState, Mirror) BlockState
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity, ItemStack) void
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + playerWillDestroy(Level, BlockPos, BlockState, Player) void
  + rotate(BlockState, Rotation) BlockState
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  - notifyColonyAboutDestruction(Level, BlockPos) void
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + wasExploded(Level, BlockPos, Explosion) void
   ResourceLocation registryName
}
class BlockPostBox {
  + BlockPostBox() 
  + getDestroyProgress(BlockState, Player, BlockGetter, BlockPos) float
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
   BuildingEntry buildingEntry
   String hutName
}
class BlockScarecrow {
  + BlockScarecrow() 
  + getRenderShape(BlockState) RenderShape
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  - getFieldBasePos(BlockState, BlockPos) BlockPos
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  - notifyColonyAboutDestruction(Level, BlockPos) void
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + playerWillDestroy(Level, BlockPos, BlockState, Player) void
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + wasExploded(Level, BlockPos, Explosion) void
  + setPlacedBy(Level, BlockPos, BlockState, LivingEntity, ItemStack) void
   ResourceLocation registryName
}
class BlockStash {
  + BlockStash() 
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + newBlockEntity(BlockPos, BlockState) BlockEntity?
  + getDestroyProgress(BlockState, Player, BlockGetter, BlockPos) float
   BuildingEntry buildingEntry
   String hutName
}
class BlockWaypoint {
  + BlockWaypoint() 
   ResourceLocation registryName
}
class LargeQuarry {
  + LargeQuarry() 
   BuildingEntry buildingEntry
   String hutName
}
class MediumQuarry {
  + MediumQuarry() 
   BuildingEntry buildingEntry
   String hutName
}
class MinecoloniesCropBlock {
  + MinecoloniesCropBlock(String, Block, List~Block~, TagKey~Biome~?) 
  - List~Block~ droppedFrom
  - Block preferredFarmland
  - TagKey~Biome~ preferredBiome
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + isMaxAge(BlockState) boolean
  + use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) InteractionResult
  # createBlockStateDefinition(Builder~Block, BlockState~) void
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + propagatesSkylightDown(BlockState, BlockGetter, BlockPos) boolean
  + attemptGrow(BlockState, ServerLevel, BlockPos) void
  + isPathfindable(BlockState, BlockGetter, BlockPos, PathComputationType) boolean
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + registerBlockItem(IForgeRegistry~Item~, Properties) void
   Block preferredFarmland
   List~Block~ droppedFrom
   TagKey~Biome~? preferredBiome
   ResourceLocation registryName
   int maxAge
}
class MinecoloniesFarmland {
  + MinecoloniesFarmland(String, boolean, double) 
  + getStateForPlacement(BlockPlaceContext) BlockState?
  + turnToDirt(Entity?, BlockState, Level, BlockPos) void
  - shouldMaintainFarmland(BlockGetter, BlockPos) boolean
  + canSurvive(BlockState, LevelReader, BlockPos) boolean
  + getFluidState(BlockState) FluidState
  + randomTick(BlockState, ServerLevel, BlockPos, RandomSource) void
  + getShape(BlockState, BlockGetter, BlockPos, CollisionContext) VoxelShape
  + useShapeForLightOcclusion(BlockState) boolean
  - isNearWater(LevelReader, BlockPos) boolean
  + fallOn(Level, BlockState, BlockPos, Entity, float) void
  + updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos) BlockState
  + animateTick(BlockState, Level, BlockPos, RandomSource) void
  # createBlockStateDefinition(Builder~Block, BlockState~) void
   ResourceLocation registryName
}
class SimpleQuarry {
  + SimpleQuarry() 
   BuildingEntry buildingEntry
   String hutName
}
```
