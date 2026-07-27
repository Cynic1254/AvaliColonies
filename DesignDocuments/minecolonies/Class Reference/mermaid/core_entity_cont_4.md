# core.entity (cont. 4)

41 classes, 74 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractAdvancedPathNavigate {
  + AbstractAdvancedPathNavigate(Mob, Level) 
  # Mob ourEntity
  - PathingOptions pathingOptions
  # walkToRandomPos(int, double, Tuple~BlockPos, BlockPos~) PathResult~IPathJob~
  + walkToTree(int, double, List~ItemStorage~, int, IColony) TreePathResult
  # moveAwayFromLivingEntity(Entity, double, double) PathResult~IPathJob~
  # walkTowards(BlockPos, double, double) PathResult~AbstractPathJob~?
  # walkTo(BlockPos, double, boolean) PathResult~IPathJob~
  # walkAwayFrom(BlockPos, double, double, boolean) PathResult~IPathJob~
  # walkToRandomPosAround(int, double, BlockPos) PathResult~IPathJob~
  + walkToTree(BlockPos, BlockPos, double, List~ItemStorage~, int, IColony) TreePathResult
  # walkToEntity(Entity, double) PathResult~IPathJob~
  # walkTo(BlockPos, double) boolean
  # walkToRandomPos(int, double, Tuple~BlockPos, BlockPos~, boolean) PathResult~PathJobRandomPos~
  # walkCloseToXNearY(BlockPos, BlockPos, int, double, boolean) PathResult~PathJobMoveCloseToXNearY~
  # walkToRandomPos(int, double) PathResult~IPathJob~
   IStuckHandler stuckHandler
   int pauseTicks
   double swimSpeedFactor
   PathResult pathResult
   PathingOptions pathingOptions
   Mob ourEntity
   BlockPos safeDestinationPos
}
class AbstractPathJob {
  # AbstractPathJob(Level, LevelReader, BlockPos, int, PathResult, Mob?) 
  + AbstractPathJob(Level, BlockPos, BlockPos, PathResult, Mob?) 
  + AbstractPathJob(Level, BlockPos, int, PathResult, Mob?) 
  # Mob? entity
  # PathResult result
  - PathingOptions pathingOptions
  - Level actualWorld
  # BlockPos start
  # getEndNodeScore(MNode) double
  - handleTargetNotPassable(MNode?, int, int, int, BlockState) int
  - canLeaveBlock(int, int, int, int, int, int, boolean) boolean
  - calculateSwimming(BlockState, BlockState, BlockState, MNode?) boolean
  - handleNotStanding(MNode?, int, int, int, BlockState) int
  - computeTurnPenalty(MNode, int, int, float) double
  - addNodeToDebug(MNode) void
  # stopOnNodeLimit(int, MNode, int) boolean
  # visitNode(MNode) void
  - addPathNodeToDebug(MNode) void
  # isAtDestination(MNode) boolean
  # getGroundHeight(MNode, int, int, int) int
  # handleDebugOptions(MNode) void
  - doDebugPrinting(Node[]) void
  # exploreInDirection(MNode, int, int, int) void
  # isPassable(int, int, int, boolean, MNode) boolean
  - handleInLiquid(int, int, int, BlockState, boolean) int
  + initDebug() void
  + call() Path?
  - checkDrop(MNode?, int, int, int, boolean) int
  - handleDebugExtraNode(MNode) void
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  - finalizePath(MNode) Path
  - reevaluteHeuristic(MNode, boolean) boolean
  - handleDebugPathReach(MNode) void
  + syncDebug(List~ServerPlayer~) void
  # isPassable(BlockState, int, int, int, MNode, boolean) boolean
  - createNode(MNode, int, int, int, double, double) MNode
  - canLeaveBlock(int, int, int, MNode, boolean) boolean
  - updateNode(MNode, MNode, double, double) void
  + toString() String
  - recalcHeuristic(MNode) void
  - getEndNodeScoreWithExtraCost(MNode) double
  # computeHeuristic(int, int, int) double
  # computeCost(MNode, int, int, int, boolean, boolean, boolean, boolean, boolean, boolean, boolean, BlockState, BlockState, int, int, int) double
  # search() Path?
  - checkHeadBlock(MNode?, int, int, int) boolean
   PathResult result
   Mob entity
   PathingOptions pathingOptions
   BlockPos start
   Level actualWorld
   MNode andSetupStartNode
}
class AbstractWalkToProxy {
  # AbstractWalkToProxy(Mob) 
  - Mob entity
  - List~BlockPos~ proxyList
  - BlockPos currentProxy
  # getProxy(BlockPos, BlockPos, double) BlockPos
  + walkToBlock(BlockPos, int, boolean) boolean
  + addToProxyList(BlockPos) void
  - fillProxyList(BlockPos, double) BlockPos
  + reset() void
  + walkToBlock(BlockPos, int) boolean
  - takeTheDirectPath(BlockPos, int, boolean) boolean
  + isLivingAtSiteWithMove(Mob, int, int, int, int) boolean
  - resetProxyList() void
   BlockPos currentProxy
   List~BlockPos~ proxyList
   Mob entity
}
class CachingBlockLookup {
  + CachingBlockLookup(BlockPos, LevelReader) 
  + getBlockEntity(BlockPos) BlockEntity?
  + getBlockState(int, int, int) BlockState
  + getFluidState(BlockPos) FluidState
  + resetToNextPos(int, int, int) void
  + getBlockState(BlockPos) BlockState
   int minBuildHeight
   int height
}
class ChunkCache {
  + ChunkCache(Level, BlockPos, BlockPos) 
  - int minBuildHeight
  # boolean empty
  - int maxBuildHeight
  + getEntityCollisions(Entity?, AABB) List~VoxelShape~
  + getShade(Direction, boolean) float
  + getChunk(int, int, ChunkStatus, boolean) ChunkAccess?
  + registryAccess() RegistryAccess
  + getBlockEntity(BlockPos) BlockEntity?
  + getHeightmapPos(Types, BlockPos) BlockPos
  + getUncachedNoiseBiome(int, int, int) Holder~Biome~
  + getBlockState(BlockPos) BlockState
  + enabledFeatures() FeatureFlagSet
  + getTileEntity(BlockPos, EntityCreationType) BlockEntity?
  + hasChunk(int, int) boolean
  + isEmptyBlock(BlockPos) boolean
  + getFluidState(BlockPos) FluidState
  + getHeight(Types, int, int) int
  + getDirectSignal(BlockPos, Direction) int
  + isUnobstructed(Entity?, VoxelShape) boolean
  + dimensionType() DimensionType
  - withinBounds(int, int) boolean
   boolean empty
   int maxBuildHeight
   BiomeManager biomeManager
   int minBuildHeight
   int seaLevel
   boolean clientSide
   LevelLightEngine lightEngine
   WorldBorder worldBorder
   int skyDarken
}
class EntityCitizenWalkToProxy {
  + EntityCitizenWalkToProxy(AbstractEntityCitizen) 
  + isWorkerAtSiteWithMove(AbstractEntityCitizen, int, int, int, int) boolean
  + careAboutY() boolean
  - getMinerProxy(BlockPos, double, BuildingMiner) BlockPos
  - calculateNodes(MinerLevel, int, BuildingMiner) void
  + isLivingAtSiteWithMove(Mob, int, int, int, int) boolean
  + getSpecializedProxy(BlockPos, double) BlockPos?
   Set~BlockPos~ wayPoints
}
class EntityNavigationUtils {
  + EntityNavigationUtils() 
  + walkToRandomPosWithin(AbstractFastMinecoloniesEntity, int, double, Tuple~BlockPos, BlockPos~, boolean) boolean
  + walkToPosInBuilding(AbstractFastMinecoloniesEntity, BlockPos, IBuilding, int) boolean
  + walkCloseToXNearY(AbstractFastMinecoloniesEntity, BlockPos, BlockPos, int, boolean, double) boolean
  + walkToRandomPosWithin(AbstractFastMinecoloniesEntity, int, double, Tuple~BlockPos, BlockPos~) boolean
  + walkToPos(AbstractFastMinecoloniesEntity, BlockPos, boolean) boolean
  + walkCloseToXNearY(AbstractFastMinecoloniesEntity, BlockPos, BlockPos, int, boolean) boolean
  + walkToRandomPosAround(AbstractFastMinecoloniesEntity, BlockPos, int, double) boolean
  + walkToBuilding(AbstractFastMinecoloniesEntity, IBuilding) boolean
  + walkToRandomPos(AbstractFastMinecoloniesEntity, int, double) boolean
  # walkToRandomPosHelper(MinecoloniesAdvancedPathNavigate, BlockPos, int, double) boolean
  + walkToPos(T, BlockPos, int, boolean, double) boolean
  + walkToPos(AbstractFastMinecoloniesEntity, BlockPos, int, boolean) boolean
  + walkAwayFrom(AbstractFastMinecoloniesEntity, BlockPos, int, double) boolean
  + walkToRandomPosAround(T, BlockPos, int, double) boolean
}
class GeneralEntityWalkToProxy {
  + GeneralEntityWalkToProxy(Mob) 
  + careAboutY() boolean
  + getSpecializedProxy(BlockPos, double) BlockPos?
   Set~BlockPos~ wayPoints
}
class IDestinationPathJob {
<<Interface>>
   BlockPos destination
}
class ISearchPathJob {
<<Interface>>
  + getEndNodeScore(MNode) double
}
class MNode {
  + MNode(FriendlyByteBuf) 
  + MNode(MNode?, int, int, int, double, double) 
  - boolean swimming
  - double heuristic
  - int counterAdded
  - int visitedCount
  - boolean ladder
  - boolean isCornerNode
  - boolean isReachedByWorker
  - double cost
  - boolean isOnRails
  + equals(Object?) boolean
  + setSwimming() void
  + setLadder() void
  + compareTo(MNode) int
  + computeNodeKey(int, int, int) int
  + toString() String
  + serializeToBuf(FriendlyByteBuf) void
  + hashCode() int
  + increaseVisited() void
   boolean isReachedByWorker
   double cost
   double score
   boolean isOnRails
   double heuristic
   boolean isCornerNode
   boolean ladder
   int visitedCount
   boolean visited
   boolean swimming
   int counterAdded
   int debugAddedIndex
}
class MinecoloniesAdvancedPathNavigate {
  + MinecoloniesAdvancedPathNavigate(Mob, Level) 
  - int pauseTicks
  - BlockPos safeDestinationPos
  - PathResult~AbstractPathJob~? pathResult
  - IStuckHandler~MinecoloniesAdvancedPathNavigate~ stuckHandler
  - double swimSpeedFactor
  # trimPath() void
  + tick() void
  - onPathFinish() void
  - handleRails() boolean
  # canMoveDirectly(Vec3, Vec3) boolean
  # followThePath() void
  - handleLadders(int) boolean
  + createPath(BlockPos, int) Path
  # walkToRandomPos(int, double, Tuple~BlockPos, BlockPos~) PathResult~PathJobRandomPos~
  # createPathFinder(int) PathFinder
  - handleEntityInWater(int, PathPointExtended) boolean
  + moveTo(double, double, double, double) boolean
  + setPathJob(AbstractPathJob, BlockPos, double, boolean) PathResult~T~?
  + moveAwayFromLivingEntity(Entity, double, double) PathResult~PathJobMoveAwayFromLocation~?
  # walkToRandomPos(int, double) PathResult~PathJobRandomPos~?
  + moveTo(Entity, double) boolean
  - convertPath(Path) Path
  # walkTo(BlockPos, double, boolean) PathResult~PathJobMoveToLocation~?
  + walkTo(BlockPos, double) boolean
  # walkTowards(BlockPos, double, double) PathResult~AbstractPathJob~?
  + stop() void
  # walkAwayFrom(BlockPos, double, double, boolean) PathResult~PathJobMoveAwayFromLocation~?
  + recalc() void
  # walkToRandomPosAround(int, double, BlockPos) PathResult~PathJobRandomPos~?
  # walkToRandomPos(int, double, Tuple~BlockPos, BlockPos~, boolean) PathResult~PathJobRandomPos~
  - handlePathOnRails(PathPointExtended, PathPointExtended) boolean
  + walkToTree(int, double, List~ItemStorage~, int, IColony) TreePathResult
  # canUpdatePath() boolean
  + walkToTree(BlockPos, BlockPos, double, List~ItemStorage~, int, IColony) TreePathResult
  - findBlockUnderEntity(Entity) BlockPos
  + recomputePath() void
  - processCompletedCalculationResult() void
  # walkCloseToXNearY(BlockPos, BlockPos, int, double, boolean) PathResult~PathJobMoveCloseToXNearY~
  + moveTo(Path?, double) boolean
  + walkToEntity(Entity, double) PathResult~PathJobMoveToLocation~?
  # doStuckDetection(Vec3) void
  + getSmartGroundY(BlockGetter, MutableBlockPos, double) double
  - handlePathPointOnLadder(PathPointExtended) boolean
   BlockPos safeDestination
   double speedModifier
   double swimSpeedFactor
   PathResult pathResult
   Vec3 tempMobPos
   boolean stuck
   boolean done
   double speedFactor
   IStuckHandler~MinecoloniesAdvancedPathNavigate~ stuckHandler
   double avgHeuristicModifier
   int pauseTicks
   boolean canFloat
   PathingOptions optionsForPathJob
   BlockPos safeDestinationPos
}
class MovementHandler {
  + MovementHandler(Mob) 
  + setWantedPosition(double, double, double, double) void
  + tick() void
}
class PathFindingStatus {
<<enumeration>>
  + PathFindingStatus() 
  + valueOf(String) PathFindingStatus
  + values() PathFindingStatus[]
}
class PathJobCanSee {
  + PathJobCanSee(Mob, LivingEntity, Level, BlockPos, int) 
  # computeHeuristic(int, int, int) double
  - canSeeTargetFromPos(BlockPos) boolean
  # isAtDestination(MNode) boolean
  + getEndNodeScore(MNode) double
}
class PathJobEscapeWater {
  + PathJobEscapeWater(Level, BlockPos, int, Mob) 
  # search() Path?
  # computeHeuristic(int, int, int) double
  # isAtDestination(MNode) boolean
   PathingOptions pathingOptions
   BlockPos destination
}
class PathJobFindTree {
  + PathJobFindTree(Level, BlockPos, BlockPos, BlockPos, BlockPos, List~ItemStorage~, int, IColony, Mob) 
  + PathJobFindTree(Level, BlockPos, BlockPos, int, List~ItemStorage~, int, IColony, Mob) 
  # isAtDestination(MNode) boolean
  - isTree(BlockPos) boolean
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  - isLeafLike(BlockState) boolean
  # computeHeuristic(int, int, int) double
  # isPassable(BlockState, int, int, int, MNode, boolean) boolean
  - isNearTree(MNode) boolean
  + getEndNodeScore(MNode) double
   TreePathResult result
}
class PathJobFindWater {
  + PathJobFindWater(Level, BlockPos, BlockPos, int, List~Tuple~BlockPos, BlockPos~~, Mob) 
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  # computeHeuristic(int, int, int) double
  # isAtDestination(MNode) boolean
  + getEndNodeScore(MNode) double
   PathingOptions pathingOptions
   WaterPathResult result
}
class PathJobMoveAwayFromLocation {
  + PathJobMoveAwayFromLocation(Level, BlockPos, BlockPos, int, int, Mob) 
  # isAtDestination(MNode) boolean
  # computeHeuristic(int, int, int) double
  # getEndNodeScore(MNode) double
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  + isJobFor(AbstractPathJob, int, BlockPos) boolean
   PathingOptions pathingOptions
   BlockPos destination
}
class PathJobMoveCloseToXNearY {
  + PathJobMoveCloseToXNearY(Level, BlockPos, BlockPos, int, Mob) 
  # computeHeuristic(int, int, int) double
  # getEndNodeScore(MNode) double
  # isAtDestination(MNode) boolean
  # stopOnNodeLimit(int, MNode, int) boolean
  + isJobFor(AbstractPathJob, BlockPos, BlockPos, int) boolean
   BlockPos destination
}
class PathJobMoveToLocation {
  + PathJobMoveToLocation(Level, BlockPos, BlockPos, int, Mob) 
  # BlockPos destination
  # getEndNodeScore(MNode) double
  # isAtDestination(MNode) boolean
  + isJobFor(AbstractPathJob, BlockPos) boolean
  # search() Path?
  + toString() String
  # stopOnNodeLimit(int, MNode, int) boolean
  # computeHeuristic(int, int, int) double
   BlockPos destination
}
class PathJobMoveToWithPassable {
  + PathJobMoveToWithPassable(Level, BlockPos, BlockPos, int, Mob, Function~BlockState, Boolean~) 
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  # isPassable(BlockState, int, int, int, MNode, boolean) boolean
}
class PathJobMoveTowards {
  + PathJobMoveTowards(Level, BlockPos, BlockPos, int, Mob) 
  # isAtDestination(MNode) boolean
  # computeHeuristic(int, int, int) double
   BlockPos destination
}
class PathJobPathway {
  + PathJobPathway(int, List~IBuilding~, Level, BlockPos, BlockPos, EntityCitizen) 
  # isAtDestination(MNode) boolean
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  # getEndNodeScore(MNode) double
  # isPassable(int, int, int, boolean, MNode) boolean
  # computeHeuristic(int, int, int) double
   BlockPos destination
}
class PathJobRaiderPathing {
  + PathJobRaiderPathing(List~IBuilding~, Level, BlockPos, BlockPos) 
  # computeHeuristic(int, int, int) double
  # isAtDestination(MNode) boolean
  # visitNode(MNode) void
  # isPassable(int, int, int, boolean, MNode) boolean
  # modifyCost(double, MNode, boolean, boolean, int, int, int, BlockState, BlockState) double
  # getGroundHeight(MNode, int, int, int) int
   BlockPos destination
}
class PathJobRandomPos {
  + PathJobRandomPos(Level, BlockPos, int, int, Mob, BlockPos, BlockPos) 
  + PathJobRandomPos(Level, BlockPos, int, int, Mob, BlockPos, BlockPos, boolean) 
  + PathJobRandomPos(Level, BlockPos, int, int, int, Mob, BlockPos) 
  + PathJobRandomPos(Level, BlockPos, int, int, Mob) 
  # BlockPos destination
  + isJobFor(AbstractPathJob, BlockPos, int) boolean
  - hasSpaceAbove(int, int, int) boolean
  # isAtDestination(MNode) boolean
  # getEndNodeScore(MNode) double
  # computeHeuristic(int, int, int) double
   PathingOptions pathingOptions
   BlockPos destination
}
class PathJobSignConnection {
  + PathJobSignConnection(Level, BlockPos, BlockPos, int) 
  # isAtDestination(MNode) boolean
}
class PathJobWalkRandomEdge {
  + PathJobWalkRandomEdge(Level, BlockPos, int, Mob) 
  # computeHeuristic(int, int, int) double
  # isAtDestination(MNode) boolean
  + getEndNodeScore(MNode) double
   PathingOptions pathingOptions
}
class PathNavigateRegistry {
  + PathNavigateRegistry() 
  + registerNewPathNavigate(Predicate~Mob~, Function~Mob, AbstractAdvancedPathNavigate~) IPathNavigateRegistry
  + getNavigateFor(Mob) AbstractAdvancedPathNavigate
}
class PathPointExtended {
  + PathPointExtended(BlockPos) 
  - boolean onLadder
  - Direction ladderFacing
  - boolean railsExit
  - boolean onRails
  - boolean railsEntry
  + setRailsExit() void
  + setRailsEntry() void
  + hashCode() int
  + equals(Object) boolean
   Direction ladderFacing
   boolean onRails
   boolean railsExit
   boolean railsEntry
   boolean onLadder
}
class PathResult~T~ {
  + PathResult() 
  - Path path
  - List~UUID~ debugWatchers
  # PathFindingStatus status
  - T job
  - boolean pathReachesDestination
  + processCalculationResults() void
  + addTrackingPlayer(UUID) void
  + startJob(ExecutorService) void
  + failedToReachDestination() boolean
  + hasPath() boolean
  - checkDebugging() void
  + cancel() void
   boolean pathReachingDestination
   boolean done
   int pathLength
   boolean calculatingPath
   boolean computing
   T job
   Path? path
   boolean inProgress
   boolean pathReachesDestination
   boolean cancelled
   PathFindingStatus status
   List~ServerPlayer~ debugWatchers
}
class Pathfinding {
  - Pathfinding() 
  - ThreadPoolExecutor executor
  + enqueue(AbstractPathJob) void
  + shutdown() void
   ThreadPoolExecutor executor
}
class PathfindingUtils {
  + PathfindingUtils() 
  + prepareStart(LivingEntity) BlockPos
  + isLiquid(BlockState) boolean
  + syncDebugReachedPositions(HashSet~BlockPos~, List~ServerPlayer~) void
  + isWater(BlockGetter, BlockPos, BlockState?, FluidState?) boolean
  + isLava(BlockGetter, BlockPos, BlockState?, FluidState?) boolean
  + isLadder(BlockState, PathingOptions, int, int, int, CachingBlockLookup) boolean
  + isWater(BlockGetter, BlockPos) boolean
  + setLadderFacing(LevelReader, BlockPos, PathPointExtended) void
  + hasAnyCollisionAlong(int, int, int, int, int, int, CachingBlockLookup) boolean
  + isLadder(BlockState, PathingOptions?) boolean
  + isDangerous(BlockState) boolean
  - doubleEquals(double, double) boolean
  - canStandInSolidBlock(BlockState) boolean
}
class PathingOptions {
  + PathingOptions() 
  - boolean walkUnderWater
  - boolean enterGates
  # float turnPenalty
  - boolean enterDoors
  - boolean canSwim
  - boolean canClimbAdvanced
  - boolean canOpenDoors
  - boolean canUseRails
  + withRailExitCost(double) PathingOptions
  + withWalkUnderWater(boolean) PathingOptions
  + withCanSwim(boolean) PathingOptions
  + canUseRails() boolean
  + withOnPathCost(double) PathingOptions
  + canEnterDoors() boolean
  + withDropCost(double) PathingOptions
  + withStartSwimCost(double) PathingOptions
  + withCanEnterDoors(boolean) PathingOptions
  + withOnRailCost(double) PathingOptions
  + importFrom(PathingOptions) void
  + withJumpCost(double) PathingOptions
  + canClimbAdvanced() boolean
  + withToggleCost(double) PathingOptions
  + canSwim() boolean
  + withNonLadderClimbableCost(double) PathingOptions
  + withCanEnterGates(boolean) PathingOptions
  + canOpenDoors() boolean
  + canEnterGates() boolean
  + canWalkUnderWater() boolean
  + withDivingCost(double) PathingOptions
  + withTurnPenalty(float) PathingOptions
  + withSwimCost(double) PathingOptions
  + canPassDanger() boolean
   boolean canClimbAdvanced
   boolean passDanger
   boolean canSwim
   boolean enterDoors
   boolean walkUnderWater
   boolean canUseRails
   float turnPenalty
   boolean canOpenDoors
   boolean enterGates
}
class PathingStuckHandler~NAV~ {
  - PathingStuckHandler() 
  - int stuckLevel
  - completeStuckAction(NAV) void
  + createStuckHandler() PathingStuckHandler~NAV~
  - tryPlaceLadderAt(Level, BlockPos) void
  + withTakeDamageOnStuck(float) PathingStuckHandler
  + withDelayBeforeStuckActions(int) PathingStuckHandler
  + withBuildLeafBridges() PathingStuckHandler
  - chanceStuckLevel(NAV) void
  + withBlockBreaks() PathingStuckHandler
  + checkStuck(NAV) void
  + withChanceToByPassMovingAway(double) PathingStuckHandler
  - setAirIfPossible(Level, BlockPos) void
  - breakBlocksAhead(Level, BlockPos, Direction) boolean
  + withTeleportSteps(int) PathingStuckHandler
  - tryPlaceLeaveOnPos(Level, BlockPos) boolean
  + withTeleportOnFullStuck() PathingStuckHandler
  + withCompleteStuckBlockBreak(int) PathingStuckHandler
  + withPlaceLadders() PathingStuckHandler
  - resetStuckTimers() void
  - placeLadders(NAV) void
  - tryUnstuck(NAV) void
  + resetGlobalStuckTimers() void
  - placeLeaves(NAV) void
  + withTimePerBlockDistance(int) PathingStuckHandler
  - breakBlocks(NAV) void
   int stuckLevel
}
class RecentTargetCache {
  + RecentTargetCache() 
  + add(BlockPos, double) void
  + getExtraCost(BlockPos) double
  - cleanup() void
}
class SurfaceType {
<<enumeration>>
  + SurfaceType() 
  + values() SurfaceType[]
  + getSurfaceType(BlockGetter, BlockState, BlockPos, PathingOptions?) SurfaceType
  + getSurfaceType(BlockGetter, BlockState, BlockPos) SurfaceType
  + valueOf(String) SurfaceType
}
class TreePathResult {
  + TreePathResult() 
}
class VisitorCitizen {
  + VisitorCitizen(EntityType~PathfinderMob~, Level) 
  - ICitizenInventoryHandler citizenInventoryHandler
  - ICitizenDataView citizenDataView
  - int citizenId
  - ICitizenJobHandler citizenJobHandler
  - ILocation location
  - ICitizenData? citizenData
  - ICitizenExperienceHandler citizenExperienceHandler
  - ICitizenColonyHandler citizenColonyHandler
  - ICitizenSleepHandler citizenSleepHandler
  # defineSynchedData() void
  - directPlayerInteraction(Player, InteractionHand) InteractionResult?
  # dropEquipment() void
  + decreaseSaturationForContinuousAction() void
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  - initTasks() void
  + decreaseSaturationForAction() void
  + hurt(DamageSource, float) boolean
  + queueSound(SoundEvent, BlockPos, int, int, float, float) void
  + markDirty(int) void
  + addAdditionalSaveData(CompoundTag) void
  + queueSound(SoundEvent, BlockPos, int, int) void
  + aiStep() void
  + die(DamageSource) void
  + playMoveAwaySound() void
  + createMenu(int, Inventory, Player) AbstractContainerMenu?
  + readAdditionalSaveData(CompoundTag) void
  + callForHelp(Entity, int) void
  + checkAndHandleImportantInteractions(Player, InteractionHand) InteractionResult
   ICitizenJobHandler citizenJobHandler
   float rotationYaw
   ICitizenDataView citizenDataView
   IItemHandler itemHandlerCitizen
   ICitizenData? citizenData
   ICitizenSleepHandler citizenSleepHandler
   int teamId
   float rotationPitch
   ICitizenExperienceHandler citizenExperienceHandler
   int citizenId
   RemovalReason removed
   ICitizenInventoryHandler citizenInventoryHandler
   InventoryCitizen inventoryCitizen
   ICivilianData civilianData
   boolean isChild
   int civilianID
   ICitizenColonyHandler citizenColonyHandler
   ILocation location
   boolean dead
}
class VisitorColonyHandler {
  + VisitorColonyHandler(AbstractEntityCitizen) 
  + onCitizenRemoved() void
  + registerWithColony(int, int) void
}
class WaterPathResult {
  + WaterPathResult() 
}

AbstractAdvancedPathNavigate  ..>  PathingOptions : «create»
AbstractAdvancedPathNavigate "1" *--> "pathingOptions 1" PathingOptions 
AbstractPathJob  ..>  CachingBlockLookup : «create»
AbstractPathJob  ..>  ChunkCache : «create»
AbstractPathJob  ..>  MNode : «create»
AbstractPathJob  ..>  PathPointExtended : «create»
AbstractPathJob  ..>  PathingOptions : «create»
AbstractPathJob "1" *--> "cachedBlockLookup 1" CachingBlockLookup 
AbstractPathJob "1" *--> "nodesToVisit *" MNode 
AbstractPathJob "1" *--> "pathingOptions 1" PathingOptions 
AbstractPathJob "1" *--> "result 1" PathResult~T~ 
EntityCitizenWalkToProxy  -->  AbstractWalkToProxy 
GeneralEntityWalkToProxy  -->  AbstractWalkToProxy 
MinecoloniesAdvancedPathNavigate  -->  AbstractAdvancedPathNavigate 
MinecoloniesAdvancedPathNavigate  ..>  MovementHandler : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobFindTree : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobMoveAwayFromLocation : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobMoveCloseToXNearY : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobMoveToLocation : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobMoveTowards : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathJobRandomPos : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathPointExtended : «create»
MinecoloniesAdvancedPathNavigate  ..>  PathingOptions : «create»
MinecoloniesAdvancedPathNavigate "1" *--> "pathResult 1" PathResult~T~ 
PathJobCanSee  -->  AbstractPathJob 
PathJobCanSee  ..>  ISearchPathJob 
PathJobCanSee  ..>  PathResult~T~ : «create»
PathJobEscapeWater  -->  AbstractPathJob 
PathJobEscapeWater  ..>  IDestinationPathJob 
PathJobEscapeWater  ..>  PathResult~T~ : «create»
PathJobFindTree  -->  AbstractPathJob 
PathJobFindTree  ..>  ISearchPathJob 
PathJobFindTree  ..>  TreePathResult : «create»
PathJobFindWater  -->  AbstractPathJob 
PathJobFindWater  ..>  ISearchPathJob 
PathJobFindWater  ..>  MNode : «create»
PathJobFindWater  ..>  WaterPathResult : «create»
PathJobMoveAwayFromLocation  -->  AbstractPathJob 
PathJobMoveAwayFromLocation  ..>  IDestinationPathJob 
PathJobMoveAwayFromLocation  ..>  PathResult~T~ : «create»
PathJobMoveCloseToXNearY  -->  AbstractPathJob 
PathJobMoveCloseToXNearY  ..>  IDestinationPathJob 
PathJobMoveCloseToXNearY  ..>  PathResult~T~ : «create»
PathJobMoveToLocation  -->  AbstractPathJob 
PathJobMoveToLocation  ..>  IDestinationPathJob 
PathJobMoveToLocation  ..>  PathResult~T~ : «create»
PathJobMoveToWithPassable  -->  PathJobMoveToLocation 
PathJobMoveTowards  -->  AbstractPathJob 
PathJobMoveTowards  ..>  IDestinationPathJob 
PathJobMoveTowards  ..>  PathResult~T~ : «create»
PathJobPathway  -->  AbstractPathJob 
PathJobPathway  ..>  IDestinationPathJob 
PathJobPathway  ..>  PathResult~T~ : «create»
PathJobPathway  ..>  PathingOptions : «create»
PathJobRaiderPathing  -->  AbstractPathJob 
PathJobRaiderPathing  ..>  IDestinationPathJob 
PathJobRaiderPathing  ..>  PathResult~T~ : «create»
PathJobRaiderPathing  ..>  PathingOptions : «create»
PathJobRandomPos  -->  AbstractPathJob 
PathJobRandomPos  ..>  IDestinationPathJob 
PathJobRandomPos  ..>  PathResult~T~ : «create»
PathJobSignConnection  -->  PathJobMoveToLocation 
PathJobSignConnection  ..>  IDestinationPathJob 
PathJobSignConnection  ..>  PathingOptions : «create»
PathJobWalkRandomEdge  -->  AbstractPathJob 
PathJobWalkRandomEdge  ..>  ISearchPathJob 
PathJobWalkRandomEdge  ..>  PathResult~T~ : «create»
PathNavigateRegistry  ..>  MinecoloniesAdvancedPathNavigate : «create»
PathResult~T~  ..>  AbstractPathJob 
PathResult~T~ "1" *--> "status 1" PathFindingStatus 
TreePathResult  -->  PathResult~T~ 
VisitorCitizen  ..>  MovementHandler : «create»
VisitorCitizen  ..>  VisitorColonyHandler : «create»
WaterPathResult  -->  PathResult~T~ 
```
