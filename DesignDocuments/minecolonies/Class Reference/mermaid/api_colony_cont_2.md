# api.colony (cont. 2)

47 classes, 10 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractInteractionResponseHandler {
  + AbstractInteractionResponseHandler() 
  + AbstractInteractionResponseHandler(Component, boolean, IChatPriority, Tuple~Component, Component~[]) 
  - boolean primary
  - IChatPriority priority
  - Component inquiry
  + isVisible(Level) boolean
  + isValid(ICitizenData) boolean
  + serializeNBT() CompoundTag
  + getResponseResult(Component) Component?
  + deserializeNBT(CompoundTag) void
   Component inquiry
   List~Component~ possibleResponses
   IChatPriority priority
   boolean primary
}
class Action {
<<enumeration>>
  - Action(int) 
  - long flag
  + valueOf(String) Action
  + values() Action[]
   long flag
}
class ChatPriority {
<<enumeration>>
  + ChatPriority() 
  + valueOf(String) ChatPriority
  + values() ChatPriority[]
   int priority
}
class ColonyPlayer {
  + ColonyPlayer(UUID, String, Rank) 
  - Rank rank
  - String name
   String name
   UUID ID
   Rank rank
}
class Explosions {
<<enumeration>>
  + Explosions() 
  + valueOf(String) Explosions
  + values() Explosions[]
}
class GuardType {
  + GuardType(Supplier~JobEntry~, String, String, Skill, Skill, String, Class~IJob~?~~, ResourceLocation) 
  - String buttonTranslationKey
  - Skill primarySkill
  - String jobTranslationKey
  - String workerSoundName
  - Supplier~JobEntry~ jobEntry
  - Skill secondarySkill
  + isInstance(IJob~?~) boolean
   Skill primarySkill
   String buttonTranslationKey
   Supplier~JobEntry~ jobEntry
   String jobTranslationKey
   String workerSoundName
   Skill secondarySkill
}
class IAnimalDataView {
<<Interface>>
  + deserialize(FriendlyByteBuf) void
   float combatCooldown
   int id
   BlockPos? homeBuilding
}
class IAnimalManager {
<<Interface>>
  + onColonyTick(IColony) void
  + getAnimal(int) IAnimalData
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + getAnimalsOfClassByHome(Class~Animal~, IBuilding?) List~IAnimalData~
  + read(CompoundTag) void
  + markDirty() void
  + tickAnimalData(int) boolean
  + createAndRegisterAnimalData(IManagedAnimal~Animal~) IAnimalData
  + write(CompoundTag) void
  + registerAnimal(IManagedAnimal~Animal~) void
  + clearDirty() void
   int currentAnimalCount
   List~IAnimalData~ animals
}
class IBuildingModuleContainer {
<<Interface>>
  + registerModule(IBuildingModule) void
  + hasModule(ModuleProducer~?, ?~) boolean
  + getModule(ModuleProducer~M, V~) M
  + getModule(int) IBuildingModule
}
class IChatPriority {
<<Interface>>
   int priority
}
class ICitizenManager {
<<Interface>>
  + tickCitizenData(int) boolean
  + maxCitizensFromResearch() double
  + getCivilian(int) ICitizenData
  + onFlagChange() void
  + createAndRegisterCivilianData() ICitizenData
  + checkCitizensForHappiness() void
  + calculateMaxCitizens() void
  + updateCitizenSleep(boolean) void
  + spawnOrCreateCitizen() void
  + resurrectCivilianData(CompoundTag, boolean, Level, BlockPos) ICitizenData
  + onWakeUp() void
  + injectModifier(IHappinessModifier) void
  + spawnOrCreateCitizen(ICitizenData, Level) ICitizenData
  + updateCitizenMourn(ICitizenData, boolean) void
  + spawnOrCreateCitizen(ICitizenData, Level, BlockPos) ICitizenData
  + onCitizenSleep() void
  + afterBuildingLoad() void
   ICitizenData? joblessCitizen
   ICitizenData randomCitizen
   int potentialMaxCitizens
   int maxCitizens
   int currentCitizenCount
   List~ICitizenData~ citizens
}
class IColonyPackageManager {
<<Interface>>
  + setDirty() void
  + updateAwayTime() void
  + addImportantColonyPlayer(ServerPlayer) void
  + sendPermissionsPackets() void
  + removeImportantColonyPlayer(ServerPlayer) void
  + addCloseSubscriber(ServerPlayer) void
  + removeCloseSubscriber(ServerPlayer) void
  + updateSubscribers() void
  + sendWorkOrderPackets() void
  + sendColonyViewPackets() void
   Set~ServerPlayer~ closeSubscribers
   int lastContactInHours
   Set~ServerPlayer~ importantColonyPlayers
}
class ICommonRegisteredStructureManager~B, T~ {
<<Interface>>
  + getBuildingExtensions(Predicate~IBuildingExtension~) List~IBuildingExtension~
  + getBuilding(BlockPos, Class~BB~) BB?
  + getBestBuilding(AbstractEntityCitizen, Class~B~) BlockPos
  + getRandomBuilding(Predicate~B~) BlockPos
  + getBuilding(BlockPos) B
  + hasTownHall() boolean
  + hasWarehouse() boolean
  + getFirstBuildingMatching(Predicate~B~) B?
  + getBestBuilding(BlockPos, Class~B~) BlockPos
  + getBestBuilding(AbstractEntityCitizen, Class~BB~, Predicate~BB~) BlockPos
  + getBestBuilding(BlockPos, Class~BB~, Predicate~BB~) BlockPos
  + hasBuilding(ResourceLocation, int, boolean) boolean
   Map~BlockPos, B~ buildings
   IColony colony
   T townHall
}
class IEntityManager {
<<Interface>>
  + registerCivilian(AbstractCivilianEntity) void
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + unregisterCivilian(AbstractCivilianEntity) void
  + clearDirty() void
  + markDirty() void
  + write(CompoundTag) void
  + removeCivilian(ICivilianData) void
  + read(CompoundTag) void
  + spawnOrCreateCivilian(T, Level, List~BlockPos~, boolean) T
  + onColonyTick(IColony) void
  + getCivilian(int) T
  + createAndRegisterCivilianData() ICivilianData
   Map~Integer, ICivilianData~ civilianDataMap
}
class IEventDescriptionManager {
<<Interface>>
  + computeNews() void
  + addEventDescription(IColonyEventDescription) void
  + serialize(FriendlyByteBuf) void
}
class IEventManager {
<<Interface>>
  + addEvent(IColonyEvent) void
  + onColonyTick(IColony) void
  + getEventByID(int) IColonyEvent
  + writeToNBT(CompoundTag) void
  + onNightFall() void
  + onTileEntityBreak(int, BlockEntity) void
  + readFromNBT(CompoundTag) void
  + registerEntity(Entity, int) void
  + unregisterEntity(Entity, int) void
  + onEntityDeath(LivingEntity, int) void
   int andTakeNextEventID
   Map~Integer, IColonyEvent~ events
   IEventStructureManager structureManager
}
class IEventStructureManager {
<<Interface>>
  + writeToNBT(CompoundTag) void
  + readFromNBT(CompoundTag) void
  + spawnTemporaryStructure(Blueprint, BlockPos, int) boolean
  + loadBackupForEvent(int) void
}
class IGraveManager {
<<Interface>>
  + reserveGrave(BlockPos) boolean
  + write(CompoundTag) void
  + onColonyTick(IColony) void
  + unReserveGrave(BlockPos) void
  + removeGrave(BlockPos) void
  + reserveNextFreeGrave() BlockPos
  + read(CompoundTag) void
  + addNewGrave(BlockPos) boolean
  + createCitizenGrave(Level, BlockPos, ICitizenData) BlockPos
   Map~BlockPos, Boolean~ graves
}
class IGuardTypeDataManager {
<<Interface>>
  + getFrom(ResourceLocation) GuardType
   IGuardTypeDataManager instance
}
class IGuardTypeRegistry {
<<Interface>>
   IForgeRegistry~GuardType~ instance
}
class IInteractionResponseHandler {
<<Interface>>
  + getResponseResult(Component) Component?
  + isVisible(Level) boolean
  + removeParent(Component) void
  + isValid(ICitizenData) boolean
  + genChildInteractions() List~IInteractionResponseHandler~
  + onServerResponseTriggered(int, Player, ICitizenData) void
  + onOpened(Player) void
  + onClosed() void
  + onClientResponseTriggered(int, Player, ICitizenDataView, BOWindow) boolean
  + onWindowOpened(BOWindow, ICitizenDataView) void
  + getInquiry(Player) Component
   Component id
   IChatPriority priority
   String type
   boolean primary
   Component inquiry
   List~Component~ possibleResponses
   ResourceLocation interactionIcon
}
class IInteractionResponseHandlerDataManager {
<<Interface>>
  + createFrom(ICitizen, CompoundTag) IInteractionResponseHandler?
   IInteractionResponseHandlerDataManager instance
}
class IJob~AI~ {
<<Interface>>
  + ignoresDamage(DamageSource) boolean
  + generateAI() AI
  + markRequestSync(IToken~?~) void
  + hasCheckedForFoodToday() boolean
  + initEntityValues(AbstractEntityCitizen) void
  + clearActionsDone() void
  + triggerDeathAchievement(DamageSource, AbstractEntityCitizen) void
  + serializeToView(FriendlyByteBuf) void
  + onStackPickUp(ItemStack) boolean
  + incrementActionsDone(int) void
  + setCheckedForFood() void
  + onLevelUp() void
  + createAI() void
  + getIdleSeverity(boolean) int
  + onWakeUp() void
  + triggerActivityChangeAction(boolean) void
  + incrementActionsDone() void
  + pickupSuccess(ItemStack) boolean
  + allowsAvoidance() boolean
  + processOfflineTime(long) void
  + canAIBeInterrupted() boolean
  + assignTo(IAssignsJob) boolean
  + onRemoval() void
  + resetAI() void
   ResourceLocation model
   boolean idling
   double saturationFactor
   IColony colony
   BlockPos buildingPos
   JobEntry jobRegistryEntry
   double diseaseModifier
   String nameTag
   boolean guard
   int actionsDone
   AI workerAI
   int inactivityLimit
   IBuilding workBuilding
   IAssignsJob workModule
   ICitizenData citizen
   String nameTagDescription
   Set~IToken~?~~ asyncRequests
   JobEntry registryEntry
}
class IJobDataManager {
<<Interface>>
  + createViewFrom(IColonyView, ICitizenDataView, FriendlyByteBuf) IJobView
  + createFrom(ICitizenData, CompoundTag) IJob~?~?
   IJobDataManager instance
}
class IJobRegistry {
<<Interface>>
   IForgeRegistry~JobEntry~ instance
}
class IJobView {
<<Interface>>
  + deserialize(FriendlyByteBuf) void
   JobEntry entry
   String name
   Set~IToken~?~~ asyncRequests
}
class IJobWithColonyFlag {
<<Interface>>
  + onColonyFlagChanged() void
}
class IJobWithExternalWorkStations {
<<Interface>>
   List~IBuilding~ workStations
}
class IManagedAnimal~T~ {
<<Interface>>
   IAnimalData animalData
   int managedAnimalId
   EntityDataAccessor~Integer~ animalIdAccessor
   int colonyId
   EntityDataAccessor~Integer~ colonyIdAccessor
   int offsetTicks
   IAnimalDataView animalDataView
   T entity
}
class IModuleContainer~T~ {
<<Interface>>
  + getModules(Class~T2~) List~T2~
  + hasModule(Predicate~T~) boolean
  + getModule(Class~T2~, Predicate~T2~) T2?
  + getModuleMatching(Class~T2~, Predicate~T2~) T2
  + getModules(Class~T2~, Predicate~T2~) List~T2~
  + getModule(Predicate~T~) T?
  + getModulesByType(Class~T2~) List~T2~
  + hasModule(Class~T2~) boolean
  + getModule(Class~T2~) T2?
  + getFirstModuleOccurance(Class~T2~) T2
  + getModules(Predicate~T~) List~T~
  + hasModule(Class~T2~, Predicate~T2~) boolean
   List~T~ modules
   Class~T~ classType
}
class IPermissions {
<<Interface>>
  + hasPermission(Rank, Action) boolean
  + alterPermission(Rank, Rank, Action, boolean) boolean
  + getPlayersByRank(Set~Rank~) Set~ColonyPlayer~
  + getRank(Player) Rank
  + addPlayer(UUID, String, Rank) boolean
  + restoreOwnerIfNull() void
  + getFilteredPlayers(Predicate~Rank~) Set~ColonyPlayer~
  + removeRank(Rank) void
  + getPlayersByRank(Rank) Set~ColonyPlayer~
  + setPermission(Rank, Action, boolean) boolean
  + addRank(String) void
  + getRank(int) Rank
  + getRank(UUID) Rank
  + addPlayer(String, Rank, Level) boolean
  + isColonyMember(Player) boolean
  + hasPermission(Player, Action) boolean
  + setOwner(Player) boolean
  + removePlayer(UUID) boolean
  + setPlayerRank(UUID, Rank, Level) boolean
  + canAlterPermission(Rank, Rank, Action) boolean
  + setOwnerAbandoned() void
  + addPlayer(GameProfile, Rank) boolean
   Map~Integer, Rank~ ranks
   Rank rankOwner
   Rank rankOfficer
   Rank rankHostile
   Rank rankNeutral
   Entry~UUID, ColonyPlayer~? ownerEntry
   Rank rankFriend
   UUID owner
   String ownerName
   Map~UUID, ColonyPlayer~ players
}
class IRaiderManager {
<<Interface>>
  + write(CompoundTag) void
  + raiderEvent(RaidSettings) RaidSpawnResult
  + onRaiderDeath(AbstractEntityMinecoloniesRaider) void
  + setPassThroughRaid() void
  + onLostCitizen(ICitizenData) void
  + canRaid() boolean
  + calculateRaiderAmount(int) int
  + canHaveRaiderEvents() boolean
  + onRaidEventFinished(IColonyRaidEvent) void
  + onNightFall() void
  + read(CompoundTag) void
  + areSpiesEnabled() boolean
  + calculateSpawnLocation() BlockPos
  + willRaidTonight() boolean
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
}
class IRegisteredStructureManager {
<<Interface>>
  + clearPendingPrestigeCalc(IBuilding) void
  + cleanUpBuildings(IColony) void
  + markBuildingsDirty() void
  + getMatchingBuildingExtension(ExtensionId) IBuildingExtension?
  + addBuildingExtensionIfMissing(BuildingExtensionEntry, BlockPos, Player) void
  + onColonyTick(IColony) void
  + guardBuildingChangedAt(IBuilding, int) void
  + markBuildingExtensionsDirty() void
  + removeBuilding(IBuilding, Set~ServerPlayer~) void
  + keepChunkColonyLoaded(LevelChunk) boolean
  + getClosestWarehouseInColony(BlockPos) IWareHouse?
  + read(CompoundTag) void
  + removeWareHouse(IWareHouse) void
  + sendPackets(Set~ServerPlayer~, Set~ServerPlayer~) void
  + clearDirty() void
  + removeLeisureSite(BlockPos) void
  + addBuildingExtension(IBuildingExtension) boolean
  + canPlaceAt(Block, BlockPos, Player) boolean
  + write(CompoundTag) void
  + removeMysticalSite(IMysticalSite) void
  + removeBuildingExtension(Predicate~IBuildingExtension~) void
  + onBuildingUpgradeComplete(IBuilding?, int) void
  + addLeisureSite(BlockPos) void
  + hasMysticalSite() boolean
  + addNewBuilding(AbstractTileEntityColonyBuilding, Level) IBuilding?
  + hasGuardBuildingNear(IBuilding) boolean
  + getMatchingBuildingExtension(Predicate~IBuildingExtension~) Optional~IBuildingExtension~
   int colonyPrestige
   List~IWareHouse~ wareHouses
   ITownHall? townHall
   BlockPos randomLeisureSite
   int mysticalSiteMaxBuildingLevel
   List~BlockPos~ leisureSites
   IBuilding houseWithSpareBed
   List~IMysticalSite~ mysticalSites
}
class IRegisteredStructureManagerView {
<<Interface>>
  + handleColonyBuildingViewMessage(BlockPos, FriendlyByteBuf) IMessage?
  + deserializeFromView(boolean, FriendlyByteBuf) void
  + handleColonyBuildingExtensionViewUpdateMessage(Set~IBuildingExtension~) void
  + handleColonyViewRemoveBuildingMessage(BlockPos) IMessage?
}
class IReproductionManager {
<<Interface>>
  + onColonyTick(IColony) void
}
class IStatisticsManager {
<<Interface>>
  + readFromNBT(CompoundTag) void
  + aggregateStats(IStatisticsManager, IStatisticsManager) void
  + serialize(FriendlyByteBuf, boolean) void
  + getStatsInPeriod(String, int, int) int
  + deserialize(FriendlyByteBuf) void
  + increment(String, int) void
  + incrementBy(String, int, int) void
  + clear() void
  + getStatTotal(String) int
  + writeToNBT(CompoundTag) void
   Set~Entry~String, Int2IntLinkedOpenHashMap~~ statEntries
   Set~String~ statTypes
}
class ITravellingManager {
<<Interface>>
  + isTravelling(int) boolean
  + startTravellingTo(ICitizenData, BlockPos, int) void
  + isTravelling(ICitizenDataView) boolean
  + getTravellingTargetFor(int) Optional~BlockPos~
  + finishTravellingFor(ICitizenData) void
  + startTravellingTo(int, BlockPos, int) void
  + finishTravellingFor(int) void
  + getTravellingTargetFor(ICitizenData) Optional~BlockPos~
  + recallAllTravellingCitizens() void
  + isTravelling(ICitizenData) boolean
}
class IVisitorManager {
<<Interface>>
  + getVisitor(int) T
}
class InteractionResponseHandlerEntry {
  - InteractionResponseHandlerEntry(Function~ICitizen, IInteractionResponseHandler~, ResourceLocation) 
   Function~ICitizen, IInteractionResponseHandler~ producer
}
class InteractionValidatorRegistry {
  - InteractionValidatorRegistry() 
  + registerStandardPredicate(Component, Predicate~ICitizenData~) void
  + getTokenBasedInteractionValidatorPredicate(Component) BiPredicate~ICitizenData, IToken~?~~
  + registerPosBasedPredicate(Component, BiPredicate~ICitizenData, BlockPos~) void
  + getStandardInteractionValidatorPredicate(Component) Predicate~ICitizenData~
  + getPosBasedInteractionValidatorPredicate(Component) BiPredicate~ICitizenData, BlockPos~
  + hasValidator(MutableComponent) boolean
  + registerTokenBasedPredicate(Component, BiPredicate~ICitizenData, IToken~?~~) void
}
class JobEntry {
  - JobEntry(Function~ICitizenData, IJob~?~~, Supplier~BiFunction~IColonyView, ICitizenDataView, IJobView~~, ResourceLocation) 
  - String translationKey
  - ResourceLocation key
  - Supplier~BiFunction~IColonyView, ICitizenDataView, IJobView~~ jobViewProducer
  + hashCode() int
  + produceJob(ICitizenData) IJob~?~
  + equals(Object) boolean
   ResourceLocation key
   Supplier~BiFunction~IColonyView, ICitizenDataView, IJobView~~ jobViewProducer
   String translationKey
   Function~ICitizenData, IJob~?~~ handlerProducer
}
class ModGuardTypes {
  - ModGuardTypes() 
}
class ModInteractionResponseHandlers {
  - ModInteractionResponseHandlers() 
}
class ModJobs {
  - ModJobs() 
  + List~ResourceLocation~ jobs
   List~ResourceLocation~ jobs
}
class OldRank {
<<enumeration>>
  + OldRank() 
  + values() OldRank[]
  + valueOf(String) OldRank
}
class PermissionEvent {
  + PermissionEvent(FriendlyByteBuf) 
  + PermissionEvent(UUID, String, Action, BlockPos) 
  - BlockPos position
  - String name
  - Action action
  - UUID? id
  + hashCode() int
  + serialize(FriendlyByteBuf) void
  + equals(Object) boolean
   String name
   UUID? id
   BlockPos position
   Action action
}
class Rank {
  + Rank(int, String, boolean) 
  + Rank(int, long, String, boolean, boolean, boolean) 
  - boolean isInitial
  - boolean isColonyManager
  - String name
  - int id
  - boolean isHostile
  + addPermission(Action) boolean
  + hashCode() int
  + compareTo(Rank) int
  + equals(Object) boolean
  + removePermission(Action) boolean
   String name
   long permissions
   int id
   boolean isInitial
   boolean isHostile
   boolean isColonyManager
}

AbstractInteractionResponseHandler  ..>  IInteractionResponseHandler 
AbstractInteractionResponseHandler "1" *--> "priority 1" IChatPriority 
ChatPriority  ..>  IChatPriority 
ColonyPlayer "1" *--> "rank 1" Rank 
IBuildingModuleContainer  -->  IModuleContainer~T~ 
ICitizenManager  -->  IEntityManager 
IRegisteredStructureManager  -->  ICommonRegisteredStructureManager~B, T~ 
IRegisteredStructureManagerView  -->  ICommonRegisteredStructureManager~B, T~ 
IVisitorManager  -->  IEntityManager 
PermissionEvent "1" *--> "action 1" Action 
```
