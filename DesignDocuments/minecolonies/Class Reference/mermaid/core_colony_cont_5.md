# core.colony (cont. 5)

76 classes, 56 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractJob~AI, J~ {
  + AbstractJob(ICitizenData) 
  - Set~IToken~?~~ asyncRequests
  # IAssignsJob workModule
  - String nameTag
  - int actionsDone
  # IBuilding workBuilding
  - ICitizenData citizen
  + incrementActionsDone(int) void
  + onStackPickUp(ItemStack) boolean
  + hashCode() int
  + deserializeNBT(CompoundTag) void
  + onRemoval() void
  + setCheckedForFood() void
  + assignTo(IAssignsJob) boolean
  + triggerDeathAchievement(DamageSource, AbstractEntityCitizen) void
  + onWakeUp() void
  + processOfflineTime(long) void
  + serializeNBT() CompoundTag
  + clearActionsDone() void
  + markRequestSync(IToken~?~) void
  + pickupSuccess(ItemStack) boolean
  + incrementActionsDone() void
  + allowsAvoidance() boolean
  + ignoresDamage(DamageSource) boolean
  + equals(Object) boolean
  + hasCheckedForFoodToday() boolean
  + resetAI() void
  + serializeToView(FriendlyByteBuf) void
  + createAI() void
  + canAIBeInterrupted() boolean
   ResourceLocation model
   boolean idling
   IColony colony
   BlockPos buildingPos
   JobEntry jobRegistryEntry
   double diseaseModifier
   String nameTag
   int actionsDone
   AI workerAI
   IBuilding workBuilding
   IAssignsJob workModule
   ICitizenData citizen
   String nameTagDescription
   Set~IToken~?~~ asyncRequests
   JobEntry registryEntry
}
class AbstractJobCrafter~AI, J~ {
  + AbstractJobCrafter(ICitizenData) 
  - int craftCounter
  - Object2IntOpenHashMap~ItemStorage~ secondaryOutputs
  - int maxCraftingCount
  - int progress
  + hasTask() boolean
  - setupRsDataStore() void
  + onTaskDeletion(IToken~?~) void
  + onTaskBeingScheduled(IToken~?~) void
  + deserializeNBT(CompoundTag) void
  + finishRequest(boolean) void
  - cancelAssignedRequests() void
  + playSound(BlockPos, EntityCitizen) void
  + serializeNBT() CompoundTag
  + addRequest(IToken~?~) void
  + onTaskBeingResolved(IToken~?~) void
  + serializeToView(FriendlyByteBuf) void
  + onRemoval() void
   IRequestSystemCrafterJobDataStore dataStore
   int maxCraftingCount
   ResourceLocation model
   List~IToken~?~~ taskQueue
   List~IToken~?~~ assignedTasksFromDataStore
   LinkedList~IToken~?~~ taskQueueFromDataStore
   int craftCounter
   int progress
   Object2IntOpenHashMap~ItemStorage~ secondaryOutputs
   List~IToken~?~~ assignedTasks
   IRequest~R~ currentTask
}
class AbstractJobGuard~J~ {
  + AbstractJobGuard(ICitizenData) 
  + generateAI() AbstractEntityAIGuard~J, AbstractBuildingGuards~
  + initEntityValues(AbstractEntityCitizen) void
  + triggerDeathAchievement(DamageSource, AbstractEntityCitizen) void
  + allowsAvoidance() boolean
  # generateGuardAI() AbstractEntityAIGuard~J, AbstractBuildingGuards~
   boolean asleep
   double saturationFactor
   boolean guard
}
class AbstractJobStructure~AI, J~ {
  + AbstractJobStructure(ICitizenData) 
  + deserializeNBT(CompoundTag) void
}
class AnimalManager {
  + AnimalManager(IColony) 
  # matchesHome(IBuilding, IAnimalData) boolean
  + onColonyTick(IColony) void
  + registerAnimal(IManagedAnimal~Animal~) void
  + createAndRegisterAnimalData(IManagedAnimal~Animal~) IAnimalData
  + clearDirty() void
  + markDirty() void
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + getAnimal(int) IAnimalData
  + read(CompoundTag) void
  + tickAnimalData(int) boolean
  + write(CompoundTag) void
  + getAnimalsOfClassByHome(Class~Animal~, IBuilding?) List~IAnimalData~
   List~IAnimalData~ animals
   int currentAnimalCount
}
class AttackingPlayer {
  + AttackingPlayer(Player) 
  - Player player
  - List~AbstractEntityCitizen~ guards
  + isValidAttack(Colony) boolean
  + removeGuard(AbstractEntityCitizen) void
  + equals(Object) boolean
  + hashCode() int
  + isValidAttack(AbstractEntityCitizen, Colony) boolean
  + addGuard(AbstractEntityCitizen) boolean
  + refreshList(Colony) void
   Player player
   List~AbstractEntityCitizen~ guards
}
class CitizenManager {
  + CitizenManager(Colony) 
  - Map~Integer, ICitizenData~ citizens
  - int potentialMaxCitizens
  - int maxCitizens
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + resurrectCivilianData(CompoundTag, boolean, Level, BlockPos) ICitizenData
  + onWakeUp() void
  + calculateMaxCitizens() void
  + createAndRegisterCivilianData() ICitizenData
  + spawnOrCreateCitizen() void
  + checkCitizensForHappiness() void
  + onCitizenSleep() void
  + spawnOrCreateCivilian(T, Level, List~BlockPos~, boolean) T
  + injectModifier(IHappinessModifier) void
  + getCivilian(int) ICitizenData
  + maxCitizensFromResearch() double
  + tickCitizenData(int) boolean
  + read(CompoundTag) void
  + unregisterCivilian(AbstractCivilianEntity) void
  + removeCivilian(ICivilianData) void
  + write(CompoundTag) void
  + registerCivilian(AbstractCivilianEntity) void
  - spawnCitizenOnPosition(ICitizenData?, Level, boolean, BlockPos) ICitizenData
  - deserializeCitizen(CompoundTag) ICitizenData
  + updateCitizenSleep(boolean) void
  + onFlagChange() void
  + afterBuildingLoad() void
  + clearDirty() void
  + updateCitizenMourn(ICitizenData, boolean) void
  + onColonyTick(IColony) void
  + markDirty() void
   ICitizenData? joblessCitizen
   int maxCitizens
   int currentCitizenCount
   Map~Integer, ICivilianData~ civilianDataMap
   ICitizenData randomCitizen
   int potentialMaxCitizens
   List~ICitizenData~ citizens
}
class ColonyConnectionManager {
  + ColonyConnectionManager(IColony) 
  - TreeMap~Integer, ColonyConnection~ directlyConnectedColonies
  - Map~Integer, ConnectionEvent~ connectionEvents
  - updateConnectedColonies(TreeMap~Integer, ColonyConnection~) void
  + getNode(BlockPos) ColonyConnectionNode
  + getColonyDiplomacyStatus(int) DiplomacyStatus
  + tick() void
  - connectToColony(BlockPos, int, BlockPos) void
  + serializeToView(FriendlyByteBuf) void
  + deserializeNBT(CompoundTag) void
  + attemptEstablishConnection(BlockPos, IColony) boolean
  + removeConnectionNode(BlockPos) void
  - createSignPath(BlockPos, BlockPos) PathResult
  - findLowestPoint(BlockPos) BlockPos
  + serializeNBT() CompoundTag
  + addNewConnectionNode(BlockPos) boolean
  + deserializeFromView(FriendlyByteBuf) void
  + triggerConnectionEvent(ConnectionEvent) void
  + removeGateHouse(BlockPos) void
  - getClosestNodeWithOpenConnection(BlockPos, boolean) ColonyConnectionNode?
  + addNewGateHouse(BlockPos) void
   List~ConnectionEvent~ connectionEvents
   TreeMap~Integer, ColonyConnection~ indirectlyConnectedColonies
   TreeMap~Integer, ColonyConnection~ directlyConnectedColonies
}
class ColonyPackageManager {
  + ColonyPackageManager(Colony) 
  - Set~ServerPlayer~ closeSubscribers
  - Set~ServerPlayer~ importantColonyPlayers
  - int lastContactInHours
  + addCloseSubscriber(ServerPlayer) void
  + setDirty() void
  + updateColonyViews() void
  - updateClosePlayers() void
  + updateSubscribers() void
  + sendColonyViewPackets() void
  + removeImportantColonyPlayer(ServerPlayer) void
  + sendPermissionsPackets() void
  + updateAwayTime() void
  + addImportantColonyPlayer(ServerPlayer) void
  + removeCloseSubscriber(ServerPlayer) void
  + sendWorkOrderPackets() void
   Set~ServerPlayer~ closeSubscribers
   int lastContactInHours
   Set~ServerPlayer~ importantColonyPlayers
}
class ColonyPermissionEventHandler {
  + ColonyPermissionEventHandler(Colony) 
  + on(Start) void
  + on(ArrowLooseEvent) void
  + on(PlayerInteractEvent) void
  + on(ItemTossEvent) void
  + on(EntityInteractSpecific) void
  + on(EntityPlaceEvent) void
  + on(EntityItemPickupEvent) void
  + on(AttackEntityEvent) void
  + on(LivingHurtEvent) void
  - isFreeToInteractWith(Block?, BlockPos) boolean
  + on(FillBucketEvent) void
  + on(BreakEvent) void
  - checkEventCancelation(Action, Player, Level, Event, BlockPos?) boolean
  - cancelEvent(Event, Entity?, Colony, Action, BlockPos) void
  + on(Detonate) void
  - checkBlockEventDenied(LevelAccessor, BlockPos, Entity, BlockState, Action) boolean
  + on(EntityInteract) void
}
class CrafterJobView {
  + CrafterJobView(IColonyView, ICitizenDataView) 
  - IToken~?~ rsDataStoreToken
  + deserialize(FriendlyByteBuf) void
   IRequestSystemCrafterJobDataStore dataStore
   IToken~?~ rsDataStoreToken
}
class DefaultJobView {
  + DefaultJobView(IColonyView, ICitizenDataView) 
  - JobEntry entry
  - IColonyView colonyView
  - Set~IToken~?~~ asyncRequests
  + deserialize(FriendlyByteBuf) void
   JobEntry entry
   String name
   Set~IToken~?~~ asyncRequests
   IColonyView colonyView
}
class DmanJobView {
  + DmanJobView(IColonyView, ICitizenDataView) 
  - IToken~?~ rsDataStoreToken
  + deserialize(FriendlyByteBuf) void
   IToken~?~ rsDataStoreToken
   IRequestSystemDeliveryManJobDataStore dataStore
}
class EventDescriptionManager {
  + EventDescriptionManager(IColony) 
  + computeNews() void
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
  + addEventDescription(IColonyEventDescription) void
  + serialize(FriendlyByteBuf) void
}
class EventManager {
  + EventManager(IColony) 
  - Map~Integer, IColonyEvent~ events
  - EventStructureManager structureManager
  + onNightFall() void
  + writeToNBT(CompoundTag) void
  + readFromNBT(CompoundTag) void
  + onTileEntityBreak(int, BlockEntity) void
  + getEventByID(int) IColonyEvent
  + addEvent(IColonyEvent) void
  + registerEntity(Entity, int) void
  + unregisterEntity(Entity, int) void
  + onColonyTick(IColony) void
  + onEntityDeath(LivingEntity, int) void
   int andTakeNextEventID
   Map~Integer, IColonyEvent~ events
   IEventStructureManager structureManager
}
class EventStructureManager {
  + EventStructureManager(EventManager, IColony) 
  + readFromNBT(CompoundTag) void
  + spawnTemporaryStructure(Blueprint, BlockPos, int) boolean
  + loadBackupForEvent(int) void
  + writeToNBT(CompoundTag) void
}
class GraveManager {
  + GraveManager(Colony) 
  - Map~BlockPos, Boolean~ graves
  + removeGrave(BlockPos) void
  + unReserveGrave(BlockPos) void
  + createCitizenGrave(Level, BlockPos, ICitizenData) BlockPos
  + addNewGrave(BlockPos) boolean
  + reserveNextFreeGrave() BlockPos
  + write(CompoundTag) void
  + read(CompoundTag) void
  + onColonyTick(IColony) void
  + reserveGrave(BlockPos) boolean
   Map~BlockPos, Boolean~ graves
}
class JobAlchemist {
  + JobAlchemist(ICitizenData) 
  + generateAI() EntityAIWorkAlchemist
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobArcherTraining {
  + JobArcherTraining(ICitizenData) 
  + generateAI() EntityAIArcherTraining
   ResourceLocation model
}
class JobBaker {
  + JobBaker(ICitizenData) 
  + playSound(BlockPos, EntityCitizen) void
  + generateAI() EntityAIWorkBaker
   ResourceLocation model
}
class JobBeekeeper {
  + JobBeekeeper(ICitizenData) 
  + resetCounter() void
  + tickNoBees() void
  + generateAI() EntityAIWorkBeekeeper
  + checkForBeeInteraction() boolean
   ResourceLocation model
}
class JobBlacksmith {
  + JobBlacksmith(ICitizenData) 
  + playSound(BlockPos, EntityCitizen) void
  + generateAI() EntityAIWorkBlacksmith
   ResourceLocation model
}
class JobBuilder {
  + JobBuilder(ICitizenData) 
  + generateAI() EntityAIStructureBuilder
   ResourceLocation model
}
class JobCavalry {
  + JobCavalry(ICitizenData) 
  + float MOUNT_DAMAGE_SPLIT
  + generateGuardAI() EntityAICavalry
  + ignoresDamage(DamageSource) boolean
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + onLevelUp() void
   float MOUNT_DAMAGE_SPLIT
   boolean missingMount
   UUID mount
   ResourceLocation model
   EquipmentTypeEntry weaponType
}
class JobChef {
  + JobChef(ICitizenData) 
  + generateAI() EntityAIWorkChef
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobChickenHerder {
  + JobChickenHerder(ICitizenData) 
  + pickupSuccess(ItemStack) boolean
  + generateAI() EntityAIWorkChickenHerder?
  + onStackPickUp(ItemStack) boolean
   ResourceLocation model
}
class JobCombatTraining {
  + JobCombatTraining(ICitizenData) 
  + generateAI() EntityAICombatTraining
   ResourceLocation model
}
class JobComposter {
  + JobComposter(ICitizenData) 
  + generateAI() EntityAIWorkComposter
   ResourceLocation model
   double diseaseModifier
}
class JobConcreteMixer {
  + JobConcreteMixer(ICitizenData) 
  + generateAI() EntityAIConcreteMixer
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobCook {
  + JobCook(ICitizenData) 
  + generateAI() EntityAIWorkCook
   ResourceLocation model
}
class JobCowboy {
  + JobCowboy(ICitizenData) 
  + onStackPickUp(ItemStack) boolean
  + generateAI() EntityAIWorkCowboy?
   ResourceLocation model
}
class JobCrusher {
  + JobCrusher(ICitizenData) 
  + generateAI() EntityAIWorkCrusher
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
   double diseaseModifier
}
class JobDataManager {
  + JobDataManager() 
  + createFrom(ICitizenData, CompoundTag) IJob~?~?
  + createViewFrom(IColonyView, ICitizenDataView, FriendlyByteBuf) IJobView?
}
class JobDeliveryman {
  + JobDeliveryman(ICitizenData) 
  + serializeToView(FriendlyByteBuf) void
  + getTaskListWithSameDestination(IRequest~Delivery~) List~IRequest~Delivery~~
  + removeConcurrentDelivery(IToken~?~) void
  - getPickupOrRequestOffset(IRequest~?~, IRequest~?~) int
  + onLevelUp() void
  + hasSameDestinationDelivery(IRequest~Delivery~) boolean
  + getScoreForDelivery(IRequest~?~) Tuple~Double, Integer~
  + serializeNBT() CompoundTag
  + onRemoval() void
  + triggerActivityChangeAction(boolean) void
  + findWareHouse() IWareHouse
  + getScoreOfRequestComparedTo(IRequest~?~, IRequest~?~, int) double
  + addRequest(IToken~?~, int) void
  + finishRequest(boolean) void
  + getClosenessFactorTo(BlockPos, BlockPos, BlockPos, BlockPos) double
  + onTaskDeletion(IToken~?~) void
  - cancelAssignedRequests() void
  + addConcurrentDelivery(IToken~?~) void
  + generateAI() EntityAIWorkDeliveryman
  + deserializeNBT(CompoundTag) void
  - getPickUpRequestScore(IRequest~?~, IRequest~?~) int
  - getSource(IRequest~?~) BlockPos?
  - getTarget(IRequest~?~) BlockPos?
  - setupRsDataStore() void
  - haveTasksSameSourceAndDest(Delivery, Delivery) boolean
   int inactivityLimit
   ResourceLocation model
   double saturationFactor
   List~IToken~?~~ taskQueue
   LinkedList~IToken~?~~ taskQueueFromDataStore
   IRequestSystemDeliveryManJobDataStore dataStore
   IRequest~IDeliverymanRequestable~ currentTask
}
class JobDruid {
  + JobDruid(ICitizenData) 
  + generateGuardAI() EntityAIDruid
  + onLevelUp() void
   ResourceLocation model
}
class JobDyer {
  + JobDyer(ICitizenData) 
  + generateAI() EntityAIWorkDyer
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobEnchanter {
  + JobEnchanter(ICitizenData) 
  - BlockPos posToDrainFrom
  + deserializeNBT(CompoundTag) void
  + incrementWaitingTicks() boolean
  + generateAI() EntityAIWorkEnchanter
  + playSound(BlockPos, EntityCitizen) void
  + serializeNBT() CompoundTag
   ResourceLocation model
   BlockPos buildingToDrainFrom
   BlockPos posToDrainFrom
}
class JobFarmer {
  + JobFarmer(ICitizenData) 
  + generateAI() EntityAIWorkFarmer
   ResourceLocation model
   double saturationFactor
}
class JobFisherman {
  + JobFisherman(ICitizenData) 
  - Tuple~BlockPos, BlockPos~ water
  - ArrayList~Tuple~BlockPos, BlockPos~~ ponds
  + addToPonds(BlockPos, BlockPos) void
  + onStackPickUp(ItemStack) boolean
  + triggerDeathAchievement(DamageSource, AbstractEntityCitizen) void
  + serializeNBT() CompoundTag
  + deserializeNBT(CompoundTag) void
  + generateAI() EntityAIWorkFisherman
  + removeFromPonds(Tuple~BlockPos, BlockPos~) void
   ResourceLocation model
   Tuple~BlockPos, BlockPos~ water
   List~Tuple~BlockPos, BlockPos~~ ponds
}
class JobFletcher {
  + JobFletcher(ICitizenData) 
  + generateAI() EntityAIWorkFletcher
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobFlorist {
  + JobFlorist(ICitizenData) 
  + generateAI() EntityAIWorkFlorist
   ResourceLocation model
}
class JobGlassblower {
  + JobGlassblower(ICitizenData) 
  + playSound(BlockPos, EntityCitizen) void
  + generateAI() EntityAIWorkGlassblower
   ResourceLocation model
}
class JobHealer {
  + JobHealer(ICitizenData) 
  + generateAI() EntityAIWorkHealer
  + onLevelUp() void
   ResourceLocation model
   double diseaseModifier
}
class JobKnight {
  + JobKnight(ICitizenData) 
  + onLevelUp() void
  + generateGuardAI() EntityAIKnight
  + onColonyFlagChanged() void
  + ignoresDamage(DamageSource) boolean
   ResourceLocation model
}
class JobLumberjack {
  + JobLumberjack(ICitizenData) 
  - Tree? tree
  + deserializeNBT(CompoundTag) void
  + onLevelUp() void
  + generateAI() EntityAIWorkLumberjack
  + serializeNBT() CompoundTag
   ResourceLocation model
   double saturationFactor
   Tree? tree
}
class JobMechanic {
  + JobMechanic(ICitizenData) 
  + generateAI() EntityAIWorkMechanic
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobMiner {
  + JobMiner(ICitizenData) 
  + ignoresDamage(DamageSource) boolean
  + generateAI() EntityAIStructureMiner
   ResourceLocation model
   double saturationFactor
   double diseaseModifier
}
class JobNetherWorker {
  + JobNetherWorker(ICitizenData) 
  - Queue~ItemStack~ processedResults
  - Queue~ItemStack~ craftedResults
  + ignoresDamage(DamageSource) boolean
  + deserializeNBT(CompoundTag) void
  + addProcessedResultsList(Collection~ItemStack~) boolean
  + generateAI() EntityAIWorkNether
  + addCraftedResultsList(Collection~ItemStack~) boolean
  + getIdleSeverity(boolean) int
  + serializeNBT() CompoundTag
   Queue~ItemStack~ processedResults
   boolean inNether
   double diseaseModifier
   ResourceLocation model
   Queue~ItemStack~ craftedResults
}
class JobPlaceholder {
  + JobPlaceholder(ICitizenData) 
  + generateAI() AbstractAISkeleton~JobPlaceholder~?
}
class JobPlanter {
  + JobPlanter(ICitizenData) 
  + generateAI() EntityAIWorkPlanter
  + ignoresDamage(DamageSource) boolean
   ResourceLocation model
}
class JobPupil {
  + JobPupil(ICitizenData) 
  + generateAI() EntityAIWorkPupil
   ResourceLocation model
}
class JobQuarrier {
  + JobQuarrier(ICitizenData) 
  + assignTo(IAssignsJob) boolean
  + generateAI() EntityAIQuarrier
  + ignoresDamage(DamageSource) boolean
  + findQuarry() IBuilding
   List~IBuilding~ workStations
   double diseaseModifier
   ResourceLocation model
   double saturationFactor
}
class JobRabbitHerder {
  + JobRabbitHerder(ICitizenData) 
  + generateAI() EntityAIWorkRabbitHerder?
  + onStackPickUp(ItemStack) boolean
   ResourceLocation model
}
class JobRanger {
  + JobRanger(ICitizenData) 
  + generateGuardAI() EntityAIRanger
   ResourceLocation model
}
class JobResearch {
  + JobResearch(ICitizenData) 
  - int currentMana
  + reduceCurrentMana() void
  + serializeNBT() CompoundTag
  + generateAI() EntityAIWorkResearcher
  + processOfflineTime(long) void
  + deserializeNBT(CompoundTag) void
   ResourceLocation model
   int currentMana
}
class JobSawmill {
  + JobSawmill(ICitizenData) 
  + playSound(BlockPos, EntityCitizen) void
  + generateAI() EntityAIWorkSawmill
   ResourceLocation model
}
class JobShepherd {
  + JobShepherd(ICitizenData) 
  + onStackPickUp(ItemStack) boolean
  + generateAI() EntityAIWorkShepherd?
   ResourceLocation model
}
class JobSifter {
  + JobSifter(ICitizenData) 
  + generateAI() EntityAIWorkSifter
  + playSound(BlockPos, EntityCitizen) void
   ResourceLocation model
}
class JobSmelter {
  + JobSmelter(ICitizenData) 
  + generateAI() EntityAIWorkSmelter
   ResourceLocation model
}
class JobStablemaster {
  + JobStablemaster(ICitizenData) 
  + onStackPickUp(ItemStack) boolean
  + generateAI() EntityAIWorkStablemaster?
   ResourceLocation model
}
class JobStoneSmeltery {
  + JobStoneSmeltery(ICitizenData) 
  + generateAI() EntityAIWorkStoneSmeltery
  + playSound(BlockPos, EntityCitizen) void
}
class JobStonemason {
  + JobStonemason(ICitizenData) 
  + playSound(BlockPos, EntityCitizen) void
  + generateAI() EntityAIWorkStonemason
}
class JobStudent {
  + JobStudent(ICitizenData) 
  + generateAI() EntityAIStudy
   ResourceLocation model
}
class JobSwineHerder {
  + JobSwineHerder(ICitizenData) 
  + generateAI() EntityAIWorkSwineHerder?
  + onStackPickUp(ItemStack) boolean
   ResourceLocation model
   double diseaseModifier
}
class JobTeacher {
  + JobTeacher(ICitizenData) 
  + generateAI() EntityAIWorkTeacher
   ResourceLocation model
}
class JobUndertaker {
  + JobUndertaker(ICitizenData) 
  + generateAI() EntityAIWorkUndertaker
   ResourceLocation model
}
class Permissions {
  + Permissions(Colony) 
  - boolean dirty
  - String ownerName
  - Map~Integer, Rank~ ranks
  - Map~UUID, ColonyPlayer~ players
  - upgradePermissions(int, Rank) void
  + isColonyMember(Player) boolean
  + removeRank(Rank) void
  - loadRanks() void
  + hasPermission(Player, Action) boolean
  + setPlayerRank(UUID, Rank, Level) boolean
  + addPlayer(String, Rank, Level) boolean
  + setPermission(Rank, Action, boolean) boolean
  + removePlayer(UUID) boolean
  + savePermissions(CompoundTag) void
  + getFilteredPlayers(Predicate~Rank~) Set~ColonyPlayer~
  + addPlayer(GameProfile, Rank) boolean
  + getPlayersByRank(Rank) Set~ColonyPlayer~
  + setOwner(Player) boolean
  + getRank(UUID) Rank
  - markDirty() void
  + loadPermissions(CompoundTag) void
  + setOwnerAbandoned() void
  + addPlayer(UUID, String, Rank) boolean
  + restoreOwnerIfNull() void
  + clearDirty() void
  + getRank(Player) Rank
  + alterPermission(Rank, Rank, Action, boolean) boolean
  + serializeViewNetworkData(FriendlyByteBuf, Rank) void
  + addRank(String) void
  + getRank(int) Rank
  + canAlterPermission(Rank, Rank, Action) boolean
  + hasPermission(Rank, Action) boolean
  + getPlayersByRank(Set~Rank~) Set~ColonyPlayer~
  - checkFullyAbandoned() void
   Map~Integer, Rank~ ranks
   Rank rankOwner
   Rank rankOfficer
   Rank rankHostile
   Rank rankNeutral
   Entry~UUID, ColonyPlayer~? ownerEntry
   Rank rankFriend
   UUID owner
   String? ownerName
   boolean dirty
   Map~UUID, ColonyPlayer~ players
}
class PermissionsView {
  + PermissionsView() 
  - Map~UUID, ColonyPlayer~ players
  - Map~Integer, Rank~ ranks
  - String ownerName
  - Rank userRank
  + addPlayer(UUID, String, Rank) boolean
  + hasPermission(UUID, Action) boolean
  + canAlterPermission(Rank, Rank, Action) boolean
  + addRank(String) void
  + setOwnerAbandoned() void
  + setOwner(Player) boolean
  + hasPermission(Rank, Action) boolean
  + hasPermission(Player, Action) boolean
  + getRank(Player) Rank
  + removeRank(Rank) void
  + addPlayer(String, Rank, Level) boolean
  + isColonyMember(Player) boolean
  + addPlayer(GameProfile, Rank) boolean
  + restoreOwnerIfNull() void
  + getRank(UUID) Rank
  + setPlayerRank(UUID, Rank, Level) boolean
  + getPlayersByRank(Rank) Set~ColonyPlayer~
  + deserialize(FriendlyByteBuf) void
  + getPlayersByRank(Set~Rank~) Set~ColonyPlayer~
  + removePlayer(UUID) boolean
  + alterPermission(Rank, Rank, Action, boolean) boolean
  + getRank(int) Rank
  + setPermission(Rank, Action, boolean) boolean
  + getFilteredPlayers(Predicate~Rank~) Set~ColonyPlayer~
   Map~Integer, Rank~ ranks
   Rank rankOwner
   Rank rankOfficer
   Rank rankHostile
   Rank rankNeutral
   Rank userRank
   Entry~UUID, ColonyPlayer~? ownerEntry
   Rank rankFriend
   UUID owner
   String? ownerName
   Map~UUID, ColonyPlayer~ players
}
class RegisteredStructureManager {
  + RegisteredStructureManager(Colony) 
  - ImmutableList~BlockPos~ leisureSites
  - ITownHall? townHall
  - Colony colony
  - List~IMysticalSite~ mysticalSites
  - List~IWareHouse~ wareHouses
  - ImmutableMap~BlockPos, IBuilding~ buildings
  + guardBuildingChangedAt(IBuilding, int) void
  + hasMysticalSite() boolean
  + clearPendingPrestigeCalc(IBuilding) void
  + removeLeisureSite(BlockPos) void
  - sendBuildingExtensionPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + onColonyTick(IColony) void
  + keepChunkColonyLoaded(LevelChunk) boolean
  + hasTownHall() boolean
  + removeBuilding(IBuilding, Set~ServerPlayer~) void
  + removeWareHouse(IWareHouse) void
  + getMatchingBuildingExtension(ExtensionId) IBuildingExtension?
  + addBuildingExtension(IBuildingExtension) boolean
  + addNewBuilding(AbstractTileEntityColonyBuilding, Level) IBuilding?
  + markBuildingsDirty() void
  + hasGuardBuildingNear(IBuilding) boolean
  + hasWarehouse() boolean
  + markBuildingExtensionsDirty() void
  + removeBuildingExtension(Predicate~IBuildingExtension~) void
  + write(CompoundTag) void
  - addBuilding(IBuilding) void
  - sendBuildingPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + onBuildingUpgradeComplete(IBuilding?, int) void
  + cleanUpBuildings(IColony) void
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + getClosestWarehouseInColony(BlockPos) IWareHouse?
  + addBuildingExtensionIfMissing(BuildingExtensionEntry, BlockPos, Player) void
  + read(CompoundTag) void
  + getMatchingBuildingExtension(Predicate~IBuildingExtension~) Optional~IBuildingExtension~
  + removeMysticalSite(IMysticalSite) void
  + addLeisureSite(BlockPos) void
  + canPlaceAt(Block, BlockPos, Player) boolean
  + clearDirty() void
  + getBuildingExtensions(Predicate~IBuildingExtension~) List~IBuildingExtension~
   int colonyPrestige
   List~IWareHouse~ wareHouses
   ITownHall? townHall
   BlockPos randomLeisureSite
   int mysticalSiteMaxBuildingLevel
   List~BlockPos~ leisureSites
   IBuilding maxChunk
   IBuilding houseWithSpareBed
   Colony colony
   Map~BlockPos, IBuilding~ buildings
   List~IMysticalSite~ mysticalSites
}
class RegisteredStructureManagerView {
  + RegisteredStructureManagerView(ColonyView) 
  - Map~BlockPos, IBuildingView~ buildings
  - ITownHallView? townHall
  + handleColonyViewRemoveBuildingMessage(BlockPos) IMessage?
  + handleColonyBuildingExtensionViewUpdateMessage(Set~IBuildingExtension~) void
  + getBuildingExtensions(Predicate~IBuildingExtension~) List~IBuildingExtension~
  + hasWarehouse() boolean
  + deserializeFromView(boolean, FriendlyByteBuf) void
  + handleColonyBuildingViewMessage(BlockPos, FriendlyByteBuf) IMessage?
  + hasTownHall() boolean
   ITownHallView? townHall
   Map~BlockPos, IBuildingView~ buildings
   IColony colony
}
class ReproductionManager {
  + ReproductionManager(Colony) 
  + onColonyTick(IColony) void
  + trySpawnChild() void
  - checkForBioParents() boolean
}
class ResearchManager {
  + ResearchManager(IColony) 
  - boolean dirty
  - startCostlessResearch(IGlobalResearch) void
  + getResearchEffectIdFrom(Block) ResourceLocation
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + readFromNBT(CompoundTag) void
  + markDirty() void
  + writeToNBT(CompoundTag) void
  + clearDirty() void
  + checkAutoStartResearch() void
   LocalResearchTree researchTree
   boolean dirty
   IResearchEffectManager researchEffects
}
class SmeltableOre {
  + SmeltableOre(int) 
  + SmeltableOre(int, int) 
  + SmeltableOre(int, ItemStack) 
  - int leftOver
  - ItemStack result
  - int count
  + matches(ItemStack) boolean
  + deserialize(IFactoryController, FriendlyByteBuf) SmeltableOre
  + serialize(IFactoryController, FriendlyByteBuf, SmeltableOre) void
  + serialize(IFactoryController, SmeltableOre) CompoundTag
  + deserialize(IFactoryController, CompoundTag) SmeltableOre
  + copyWithCount(int) IDeliverable
   int leftOver
   Set~TypeToken~?~~ superClasses
   int minimumCount
   ItemStack result
   int count
}
class StatisticsManager {
  + StatisticsManager() 
  + deserialize(FriendlyByteBuf) void
  + writeToNBT(CompoundTag) void
  + incrementBy(String, int, int) void
  + clear() void
  + getStatsInPeriod(String, int, int) int
  + readFromNBT(CompoundTag) void
  + getStatTotal(String) int
  + serialize(FriendlyByteBuf, boolean) void
  + increment(String, int) void
   Set~Entry~String, Int2IntLinkedOpenHashMap~~ statEntries
   Set~String~ statTypes
}
class TravellingManager {
  + TravellingManager(IColony) 
  + startTravellingTo(int, BlockPos, int) void
  + finishTravellingFor(int) void
  + getTravellingTargetFor(int) Optional~BlockPos~
  + deserializeNBT(CompoundTag) void
  + isTravelling(int) boolean
  + recallAllTravellingCitizens() void
  + onTick() boolean
  + serializeNBT() CompoundTag
}
class VisitorManager {
  + VisitorManager(IColony) 
  + createAndRegisterCivilianData() IVisitorData
  + getVisitor(int) T
  + unregisterCivilian(AbstractCivilianEntity) void
  + clearDirty() void
  + getCivilian(int) IVisitorData
  + registerCivilian(AbstractCivilianEntity) void
  + onColonyTick(IColony) void
  + write(CompoundTag) void
  + read(CompoundTag) void
  + removeCivilian(ICivilianData) void
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + markDirty() void
  + spawnOrCreateCivilian(T, Level, List~BlockPos~, boolean) T
   Map~Integer, ICivilianData~ civilianDataMap
}

AbstractJobCrafter~AI, J~  -->  AbstractJob~AI, J~ 
AbstractJobGuard~J~  -->  AbstractJob~AI, J~ 
AbstractJobStructure~AI, J~  -->  AbstractJob~AI, J~ 
CrafterJobView  -->  DefaultJobView 
DmanJobView  -->  DefaultJobView 
EventManager  ..>  EventStructureManager : «create»
EventManager "1" *--> "structureManager 1" EventStructureManager 
EventStructureManager "1" *--> "eventManager 1" EventManager 
JobAlchemist  -->  AbstractJobCrafter~AI, J~ 
JobArcherTraining  -->  AbstractJob~AI, J~ 
JobBaker  -->  AbstractJobCrafter~AI, J~ 
JobBeekeeper  -->  AbstractJob~AI, J~ 
JobBlacksmith  -->  AbstractJobCrafter~AI, J~ 
JobBuilder  -->  AbstractJobStructure~AI, J~ 
JobCavalry  -->  AbstractJobGuard~J~ 
JobChef  -->  AbstractJobCrafter~AI, J~ 
JobChickenHerder  -->  AbstractJob~AI, J~ 
JobCombatTraining  -->  AbstractJob~AI, J~ 
JobComposter  -->  AbstractJob~AI, J~ 
JobConcreteMixer  -->  AbstractJobCrafter~AI, J~ 
JobCook  -->  AbstractJob~AI, J~ 
JobCowboy  -->  AbstractJob~AI, J~ 
JobCrusher  -->  AbstractJobCrafter~AI, J~ 
JobDeliveryman  -->  AbstractJob~AI, J~ 
JobDruid  -->  AbstractJobGuard~J~ 
JobDyer  -->  AbstractJobCrafter~AI, J~ 
JobEnchanter  -->  AbstractJobCrafter~AI, J~ 
JobFarmer  -->  AbstractJobCrafter~AI, J~ 
JobFisherman  -->  AbstractJob~AI, J~ 
JobFletcher  -->  AbstractJobCrafter~AI, J~ 
JobFlorist  -->  AbstractJob~AI, J~ 
JobGlassblower  -->  AbstractJobCrafter~AI, J~ 
JobHealer  -->  AbstractJob~AI, J~ 
JobKnight  -->  AbstractJobGuard~J~ 
JobLumberjack  -->  AbstractJobCrafter~AI, J~ 
JobMechanic  -->  AbstractJobCrafter~AI, J~ 
JobMiner  -->  AbstractJobStructure~AI, J~ 
JobNetherWorker  -->  AbstractJobCrafter~AI, J~ 
JobPlaceholder  -->  AbstractJob~AI, J~ 
JobPlanter  -->  AbstractJobCrafter~AI, J~ 
JobPupil  -->  AbstractJob~AI, J~ 
JobQuarrier  -->  AbstractJobStructure~AI, J~ 
JobRabbitHerder  -->  AbstractJob~AI, J~ 
JobRanger  -->  AbstractJobGuard~J~ 
JobResearch  -->  AbstractJob~AI, J~ 
JobSawmill  -->  AbstractJobCrafter~AI, J~ 
JobShepherd  -->  AbstractJob~AI, J~ 
JobSifter  -->  AbstractJobCrafter~AI, J~ 
JobSmelter  -->  AbstractJob~AI, J~ 
JobStablemaster  -->  AbstractJob~AI, J~ 
JobStoneSmeltery  -->  AbstractJobCrafter~AI, J~ 
JobStonemason  -->  AbstractJobCrafter~AI, J~ 
JobStudent  -->  AbstractJob~AI, J~ 
JobSwineHerder  -->  AbstractJob~AI, J~ 
JobTeacher  -->  AbstractJob~AI, J~ 
JobUndertaker  -->  AbstractJobCrafter~AI, J~ 
```
