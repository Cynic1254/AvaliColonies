# core.colony (cont. 7)

14 classes, 11 internal relationships shown.

```mermaid
classDiagram
direction BT

class AnimalData {
  + AnimalData(int, IColony) 
  - UUID owner
  - int id
  - BlockPos lastPosition
  - boolean isDirty
  - IBuilding? homeBuilding
  - float combatCooldown
  + deserializeNBT(CompoundTag) void
  + markDirty() void
  + update(int) void
  + loadAnimalFromNBT(IColony, CompoundTag) IAnimalData
  + onRemoveBuilding(IBuilding) void
  + clearDirty() void
  + initEntityValues() void
  + serializeViewNetworkData(FriendlyByteBuf) void
  + serializeNBT() CompoundTag
   BlockPos lastPosition
   float combatCooldown
   boolean isDirty
   IBuilding? homeBuilding
   Optional~IManagedAnimal~Animal~~ managedAnimal
   UUID owner
   int id
   UUID UUID
}
class AnimalDataView {
  + AnimalDataView(int, ColonyView) 
  - float combatCooldown
  ~ int id
  - BlockPos? homeBuilding
  + deserialize(FriendlyByteBuf) void
   float combatCooldown
   int id
   BlockPos? homeBuilding
}
class CitizenData {
  + CitizenData(int, IColony) 
  - IBuilding? homeBuilding
  - Random random
  - boolean paused
  - CitizenDiseaseHandler citizenDiseaseHandler
  - CitizenMournHandler citizenMournHandler
  - int interactedRecently
  - int dirty
  - boolean female
  - VisibleCitizenStatus status
  - boolean isWorking
  - IColony colony
  - Integer partner
  - Tuple~String, String~ parents
  - IJob~?~? job
  - int id
  - CitizenSkillHandler citizenSkillHandler
  - boolean justAte
  - BlockPos? statusPosition
  - BlockPos bedPos
  - boolean isAsleep
  - JobStatus jobStatus
  - Set~Integer~ children
  - WeakReference~AbstractEntityCitizen~ entity
  - int leisureTime
  - int voiceProfile
  - CitizenHappinessHandler citizenHappinessHandler
  - String textureSuffix
  - int textureId
  - ICitizenFoodHandler citizenFoodHandler
  - String name
  # InventoryCitizen inventory
  - Set~Integer~ siblings
  - BlockPos lastPosition
  - double saturation
  + updateEntityIfNecessary() void
  + assignQuest(IQuestInstance) void
  + isRelatedTo(ICitizenData) boolean
  + deserializeNBT(CompoundTag) void
  - applyItemModifiers(AbstractEntityCitizen) void
  + onResurrect() void
  + addChildren(Integer[]) void
  + initForNewCivilian() void
  + onInteractionClosed(Component, ServerPlayer) void
  + update(int) void
  - getRandomElement(Random, List~String~) String
  + loadFromNBT(IColony, CompoundTag) CitizenData
  + onBuildingLoad() void
  + justAte() boolean
  + onRemoveBuilding(IBuilding) void
  + increaseSaturation(double) void
  + scheduleRestart(ServerPlayer) void
  + isParticipantOfQuest(ResourceLocation) boolean
  + shouldRestart() boolean
  + hashCode() int
  + onRequestCancelled(IToken~?~) void
  + onRequestCompleted(IToken~?~) void
  + equals(Object) boolean
  + generateName(Random, boolean, IColony, CitizenNameFile) String
  + setParents(String, String) void
  + hasCustomTexture() boolean
  + decreaseSaturation(double) void
  + markDirty(int) void
  + onQuestDeletion(ResourceLocation) void
  + isRequestAsync(IToken~?~) boolean
  + clearDirty() void
  + getJob(Class~J~) J?
  + serializeViewNetworkData(FriendlyByteBuf) void
  + onQuestCompletion(ResourceLocation) void
  + restartDone() void
  + serializeNBT() CompoundTag
  + triggerInteraction(IInteractionResponseHandler) void
  + hasQuestAssignment() boolean
  + addSiblings(Integer[]) void
  + addQuestParticipation(IQuestInstance) void
  - getRandomLetter(Random) char
  + onResponseTriggered(Component, int, Player) void
  + needsBetterFood() boolean
  + createRequestAsync(R) IToken~?~
  + isChild() boolean
  + onDeath(Integer) void
  + createRequest(R) IToken~?~
  + onGoSleep() void
  + initEntityValues() void
  + applyResearchEffects() void
  + openDialogue(IQuestInstance, int) void
  - setJob(IJob~?~, boolean) void
  + doesLiveWith(ICitizenData) boolean
  + generateName(Random, String, String, CitizenNameFile) void
   Random random
   IJob~?~? job
   UUID interactedRecently
   InventoryCitizen inventory
   IColony colony
   List~Integer~ siblings
   BlockPos nextRespawnPosition
   boolean gender
   VisibleCitizenStatus status
   IBuilding? workBuilding
   boolean idleAtJob
   BlockPos bedPos
   List~Integer~ children
   ICitizenDiseaseHandler citizenDiseaseHandler
   String suffix
   UUID UUID
   boolean dirty
   ICitizenFoodHandler citizenFoodHandler
   Tuple~String, String~ parents
   UUID customTexture
   int textureId
   boolean isWorking
   BlockPos lastPosition
   BlockPos? statusPosition
   boolean female
   VisibleCitizenStatus visibleStatus
   IBuilding? homeBuilding
   String textureSuffix
   CitizenMournHandler citizenMournHandler
   double diseaseModifier
   JobStatus jobStatus
   CitizenHappinessHandler citizenHappinessHandler
   String name
   boolean genderAndGenerateName
   int leisureTime
   boolean paused
   BlockPos? homePosition
   int voiceProfile
   int idleDays
   boolean isChild
   int id
   double saturation
   ICitizenSkillHandler citizenSkillHandler
   boolean isAsleep
   boolean justAte
   Optional~AbstractEntityCitizen~ entity
   ICitizenData? partner
}
class CitizenDataManager {
  + CitizenDataManager() 
  + createFromNBT(CompoundTag, IColony) ICitizenData
  + createFromNetworkData(int, FriendlyByteBuf, IColonyView) ICitizenDataView
}
class CitizenDataView {
  # CitizenDataView(int, IColonyView) 
  # int entityId
  - List~Integer~ children
  # boolean female
  - double saturation
  - BlockPos? statusPosition
  - Tuple~String, String~ parents
  # boolean isChild
  - InventoryCitizen inventory
  - Integer partner
  - boolean isSick
  - BlockPos position
  - BlockPos? workBuilding
  - BlockPos? homeBuilding
  - String job
  # String name
  - CitizenSkillHandler citizenSkillHandler
  - int id
  # boolean paused
  - VisibleCitizenStatus statusIcon
  # int colonyId
  - List~Integer~ siblings
  - IJobView jobView
  - double happiness
  + hasBlockingInteractions() boolean
  + hashCode() int
  + getSpecificInteraction(Component) IInteractionResponseHandler?
  + equals(Object) boolean
  + hasVisibleStatus() boolean
  + getDisplayArmor(EquipmentSlot) ItemStack
  + deserialize(FriendlyByteBuf) void
  + hasPendingInteractions() boolean
   IJobView? jobView
   BlockPos? homeBuilding
   InventoryCitizen inventory
   double happiness
   List~Integer~ siblings
   ICitizenHappinessHandler happinessHandler
   BlockPos? workBuilding
   UUID customTextureUUID
   List~Integer~ children
   Integer? partner
   Tuple~String, String~ parents
   IColonyView colony
   ResourceLocation customTexture
   BlockPos position
   BlockPos? statusPosition
   boolean female
   VisibleCitizenStatus visibleStatus
   int colonyId
   MutableComponent jobComponent
   int entityId
   ResourceLocation statusIcon
   String name
   boolean paused
   boolean isSick
   double maxHealth
   List~IInteractionResponseHandler~ orderedInteractions
   boolean isChild
   String job
   int id
   double saturation
   ICitizenSkillHandler citizenSkillHandler
   double health
}
class Colony {
  # Colony(int, Level?) 
  ~ Colony(int, Level?, BlockPos) 
  - List~Player~ visitingPlayers
  - TravellingManager travellingManager
  - ColonyConnectionManager connectionManager
  - WorkManager workManager
  - IRequestManager requestManager
  - Permissions permissions
  - boolean isDirty
  - BlockPos center
  - IColonyPackageManager packageManager
  - IQuestManager questManager
  - String name
  - ICitizenManager citizenManager
  - ImmutableSet~Block~ freeBlocks
  - IEventManager eventManager
  - IResearchManager researchManager
  - ListTag colonyFlag
  - ColonyPermissionEventHandler eventHandler
  - String nameStyle
  - IAnimalManager animalManager
  - IReproductionManager reproductionManager
  - Level? world
  - Map~BlockPos, BlockState~ wayPoints
  - int day
  - ConcurrentHashMap~Long, Long~ loadedChunks
  + Set~Long~ ticketedChunks
  - IGraveManager graveManager
  - CompoundTag colonyTag
  - ResourceKey~Level~ dimensionId
  - ImmutableSet~BlockPos~ freePositions
  - String textureStyle
  - IVisitorManager visitorManager
  - boolean ticketedChunksDirty
  + addFreeBlock(Block) void
  + removeLoadedChunk(long) void
  + read(CompoundTag) void
  + getDistanceSquared(BlockPos) long
  + removeWorkOrderInView(int) void
  - updateChunkLoadTimer() void
  - setRequestManager() void
  + addGuardToAttackers(AbstractEntityCitizen, Player) void
  - worldTickSlow() boolean
  + onWorldTick(LevelTickEvent) void
  + removeVisitingPlayer(Player) void
  + addLoadedChunk(long, LevelChunk) void
  + onWorldLoad(Level) void
  + addVisitingPlayer(Player) void
  + isValidAttackingGuard(AbstractEntityCitizen) boolean
  + updateAttackingPlayers() void
  - tickRequests() boolean
  + isCoordInColony(Level, BlockPos) boolean
  - updateSubscribers() boolean
  + useAdditionalChildTime(int) boolean
  + removeFreePosition(BlockPos) void
  - updateChildTime() void
  + loadColony(CompoundTag, Level?) Colony?
  + getRequesterBuildingForPosition(BlockPos) IRequester?
  - worldTickUnloaded() boolean
  + isDay() boolean
  + usedMercenaries() void
  + onServerTick(ServerTickEvent) void
  + markDirty() void
  + getCitizen(int) ICitizen
  + isValidAttackingPlayer(Player) boolean
  + updateHasChilds() void
  + canBeAutoDeleted() boolean
  - tickWorkManager() boolean
  + write(CompoundTag) CompoundTag
  + addFreePosition(BlockPos) void
  + removeFreeBlock(Block) void
  - updateState() ColonyState
  - tickTravellers() boolean
  + addWayPoint(BlockPos, BlockState) void
  + shallUpdate(Level, int) boolean
  - checkChunkAndRegisterTicket(long, LevelChunk) void
  - updateWayPoints() boolean
  + onWorldUnload(Level) void
  - checkDayTime() boolean
   IRegisteredStructureManager serverBuildingManager
   IReproductionManager reproductionManager
   IQuestManager questManager
   IAnimalManager animalManager
   ISettingsModule settings
   Set~BlockPos~ freePositions
   ChatFormatting colonyColor
   IStatisticsManager statisticsManager
   int lastContactInHours
   Map~BlockPos, BlockState~ wayPoints
   List~Player~ importantMessageEntityPlayers
   Set~Long~ ticketedChunks
   IVisitorManager visitorManager
   boolean isDirty
   BlockPos center
   ColonyState state
   boolean canBeAutoDeleted
   ResourceKey~Level~ dimension
   boolean remote
   String nameStyle
   int day
   ColonyPermissionEventHandler eventHandler
   String structurePack
   Permissions permissions
   boolean ticketedChunksDirty
   IResearchManager researchManager
   ICitizenManager citizenManager
   IRequestManager requestManager
   ImmutableList~Player~ visitingPlayers
   TravellingManager travellingManager
   ICommonRegisteredStructureManager commonBuildingManager
   long mercenaryUseTime
   ResourceKey~Level~ dimensionId
   double overallHappiness
   IGraveManager graveManager
   String textureStyle
   Level? world
   IColonyPackageManager packageManager
   int loadedChunkCount
   boolean active
   IWorkManager workManager
   ListTag colonyFlag
   IColonyConnectionManager connectionManager
   Set~Block~ freeBlocks
   boolean colonyUnderAttack
   Set~Long~ loadedChunks
   IEventManager eventManager
   String name
   CompoundTag colonyTag
   int ID
   ChatFormatting teamColonyColor
   CitizenNameFile citizenNameFile
   IRaiderManager raiderManager
   String textureStyleId
   IEventDescriptionManager eventDescriptionManager
   List~Player~ messagePlayerEntities
}
class ColonyList~T~ {
  + ColonyList() 
  - int size
  - int topID
  + remove(T) void
  + remove(int) void
  - expandList() void
  - getNextIndex(int) int
  + add(T) void
  + clear() void
  + stream() Stream~T~
  + iterator() Iterator~T~
  + create(Level, BlockPos) Colony?
  + get(int) T?
   int size
   boolean empty
   int nextColonyID
   int topID
   List~T~ copyAsList
}
class ColonyManager {
  + ColonyManager() 
  - ICompatibilityManager compatibilityManager
  - boolean schematicDownloaded
  - IRecipeManager recipeManager
  + openReactivationWindow(BlockPos) void
  + onWorldTick(LevelTickEvent) void
  + getIColony(Level, BlockPos) IColony?
  + handleColonyViewWorkOrderMessage(int, FriendlyByteBuf, ResourceKey~Level~) void
  + resetColonyViews() void
  + getClosestColonyView(Level?, BlockPos?) IColonyView?
  + getClosestColony(Level, BlockPos) IColony
  + getClosestIColony(Level, BlockPos) IColony?
  - deleteColony(IColony?, boolean) void
  + getColonyViews(Level) List~IColonyView~
  + handleColonyViewMessage(int, FriendlyByteBuf, Level, boolean, ResourceKey~Level~) void
  - getColonyViewByOwner(UUID, ResourceKey~Level~) IColony?
  + getColonyView(int, ResourceKey~Level~) IColonyView?
  + getBuilding(Level, BlockPos) IBuilding?
  + setCapLoaded() void
  + handleColonyViewCitizensMessage(int, int, FriendlyByteBuf, ResourceKey~Level~) void
  + removeColonyView(int, ResourceKey~Level~) void
  + handleColonyBuildingViewMessage(int, BlockPos, FriendlyByteBuf, ResourceKey~Level~) void
  + getColonyView(Level, BlockPos) IColonyView?
  + getIColonyByOwner(Level, UUID) IColony?
  + handleColonyViewRemoveCitizenMessage(int, int, ResourceKey~Level~) void
  + handleColonyViewRemoveWorkOrderMessage(int, int, ResourceKey~Level~) void
  + getColonyByDimension(int, ResourceKey~Level~) IColony?
  + deleteColonyByWorld(int, boolean, Level) void
  + getColonyByWorld(int, Level) IColony?
  - getColonyByOwner(UUID?) IColony?
  + isFarEnoughFromColonies(Level, BlockPos) boolean
  + getIColonies(Level) List~IColony~
  + getIColonyByOwner(Level, Player) IColony?
  + read(CompoundTag) void
  + deleteColonyByDimension(int, boolean, ResourceKey~Level~) void
  + onClientTick(ClientTickEvent) void
  + getColoniesAbandonedSince(int) List~IColony~
  + getColonyByPosFromWorld(Level?, BlockPos) IColony?
  + onServerTick(ServerTickEvent) void
  + write(CompoundTag) void
  + getColonyByPosFromDim(ResourceKey~Level~, BlockPos) IColony
  + isCoordinateInAnyColony(Level, BlockPos) boolean
  + handleColonyViewRemoveBuildingMessage(int, BlockPos, ResourceKey~Level~) void
  + getBuildingView(ResourceKey~Level~, BlockPos) IBuildingView?
  + onWorldLoad(Level) void
  + createColony(Level, BlockPos, Player, String, String) IColony?
  + handlePermissionsViewMessage(int, FriendlyByteBuf, ResourceKey~Level~) void
  + getColonies(Level) List~IColony~
  + onWorldUnload(Level) void
   List~IColony~ allColonies
   boolean schematicDownloaded
   int minimumDistanceBetweenTownHalls
   ICompatibilityManager compatibilityManager
   int topColonyId
   IRecipeManager recipeManager
}
class ColonyView {
  - ColonyView(int) 
  - int lastContactInHours
  - Level world
  - double overallHappiness
  - ChatFormatting teamColonyColor
  - Map~BlockPos, BlockState~ wayPoints
  - TravellingManager travellingManager
  - Set~Long~ ticketedChunks
  - IQuestManager questManager
  - IRequestManager requestManager
  - String name
  - String textureStyle
  - Map~Integer, ICitizenDataView~ citizens
  - Set~BlockPos~ freePositions
  - IColonyConnectionManager connectionManager
  - PermissionsView permissions
  - List~BlockPos~ lastSpawnPoints
  - IResearchManager researchManager
  - String nameStyle
  - ListTag colonyFlag
  - BlockPos center
  - List~String~ nameFileIds
  - int day
  - int citizenCount
  - Set~Block~ freeBlocks
  - IGraveManager graveManager
  - Map~Integer, IWorkOrderView~ workOrders
  + handleColonyViewRemoveWorkOrderMessage(int) IMessage?
  + handleColonyViewResearchManagerUpdate(CompoundTag) void
  + canBeAutoDeleted() boolean
  + createFromNetwork(int) ColonyView
  + addVisitingPlayer(Player) void
  + getWorkOrder(int) IWorkOrderView
  + isValidAttackingPlayer(Player) boolean
  + handleColonyViewRemoveCitizenMessage(int) IMessage?
  + serializeNetworkData(Colony, FriendlyByteBuf, boolean) void
  + getDistanceSquared(BlockPos) long
  + areSpiesEnabled() boolean
  + handleColonyViewAnimalMessage(FriendlyByteBuf, boolean) void
  + isCoordInColony(Level, BlockPos) boolean
  + write(CompoundTag) CompoundTag
  + addPlayer(String) void
  + updateHasChilds() void
  + handleColonyViewMessage(FriendlyByteBuf, Level, boolean) IMessage?
  + getCitizen(int) ICitizenDataView
  + onWorldTick(LevelTickEvent) void
  + usedMercenaries() void
  + removeFreePosition(BlockPos) void
  + getAnimal(int) IAnimalDataView
  + addFreePosition(BlockPos) void
  + addFreeBlock(Block) void
  + addGuardToAttackers(AbstractEntityCitizen, Player) void
  + onServerTick(ServerTickEvent) void
  + read(CompoundTag) void
  + removeVisitingPlayer(Player) void
  + handlePermissionsViewMessage(FriendlyByteBuf) IMessage?
  + getRequesterBuildingForPosition(BlockPos) IRequester?
  + getVisitor(int) ICitizenDataView
  + handleColonyViewCitizensMessage(int, FriendlyByteBuf) IMessage?
  + onWorldLoad(Level) void
  + removePlayer(UUID) void
  + isDay() boolean
  + isValidAttackingGuard(AbstractEntityCitizen) boolean
  + onWorldUnload(Level) void
  + removeFreeBlock(Block) void
  + removeLoadedChunk(long) void
  + addWayPoint(BlockPos, BlockState) void
  + useAdditionalChildTime(int) boolean
  + addLoadedChunk(long, LevelChunk) void
  + handleColonyViewWorkOrderMessage(FriendlyByteBuf) IMessage?
  + handleColonyViewVisitorMessage(FriendlyByteBuf, boolean) void
  + markDirty() void
   IRegisteredStructureManager? serverBuildingManager
   IReproductionManager? reproductionManager
   IQuestManager questManager
   IAnimalManager? animalManager
   IPermissions permissions
   ChatFormatting colonyColor
   IStatisticsManager statisticsManager
   int lastContactInHours
   Map~BlockPos, BlockState~ wayPoints
   List~Player~ importantMessageEntityPlayers
   Set~Long~ ticketedChunks
   IVisitorManager? visitorManager
   BlockPos center
   ColonyState? state
   boolean canBeAutoDeleted
   ResourceKey~Level~ dimension
   int citizenCountLimit
   boolean remote
   String nameStyle
   Collection~IWorkOrderView~ workOrders
   int day
   String structurePack
   Map~Integer, ICitizenDataView~ citizens
   List~String~ nameFileIds
   ITravellingManager travellingManager
   IResearchManager researchManager
   ICitizenManager? citizenManager
   IRequestManager requestManager
   List~BlockPos~ lastSpawnPoints
   ICommonRegisteredStructureManager commonBuildingManager
   Map~UUID, ColonyPlayer~ players
   long mercenaryUseTime
   double overallHappiness
   IGraveManager graveManager
   String textureStyle
   boolean raiding
   List~Block~ freeBlocks
   Level world
   int citizenCount
   IColonyPackageManager? packageManager
   int loadedChunkCount
   boolean active
   ListTag colonyFlag
   IWorkManager workManager
   IColonyConnectionManager connectionManager
   boolean colonyUnderAttack
   Set~Long~? loadedChunks
   IEventManager? eventManager
   List~BlockPos~ freePositions
   String name
   IRegisteredStructureManagerView clientBuildingManager
   CompoundTag? colonyTag
   int ID
   ChatFormatting teamColonyColor
   CitizenNameFile citizenNameFile
   IRaiderManager? raiderManager
   String textureStyleId
   IEventDescriptionManager? eventDescriptionManager
   ICommonSettingsModule settings
   List~Player~ messagePlayerEntities
}
class FieldDataModifier {
  + FieldDataModifier() 
  - boolean canFarm
  + isCanFarm(boolean) void
  + increaseInactiveDays() void
   boolean canFarm
   int inactiveDays
}
class GraveManagerView {
  + GraveManagerView() 
  - Map~BlockPos, Boolean~ graves
  + read(CompoundTag) void
  + unReserveGrave(BlockPos) void
  + reserveNextFreeGrave() BlockPos
  + createCitizenGrave(Level, BlockPos, ICitizenData) BlockPos
  + addNewGrave(BlockPos) boolean
  + onColonyTick(IColony) void
  + reserveGrave(BlockPos) boolean
  + write(CompoundTag) void
  + removeGrave(BlockPos) void
   Map~BlockPos, Boolean~ graves
}
class IColonyManagerCapability {
<<Interface>>
  + createColony(Level, BlockPos) IColony
  + deleteColony(int) void
  + addColony(IColony) void
  + getColony(int) IColony?
   int topID
   List~IColony~ colonies
}
class VisitorData {
  + VisitorData(int, IColony) 
  - BlockPos sittingPosition
  - ItemStack recruitCost
  + serializeNBT() CompoundTag
  + updateEntityIfNecessary() void
  + serializeViewNetworkData(FriendlyByteBuf) void
  + loadVisitorFromNBT(IColony, CompoundTag) IVisitorData
  + applyResearchEffects() void
  + deserializeNBT(CompoundTag) void
   ItemStack recruitCosts
   ItemStack recruitCost
   BlockPos sittingPosition
}
class VisitorDataView {
  + VisitorDataView(int, IColonyView) 
  + deserialize(FriendlyByteBuf) void
   ItemStack recruitCost
   ResourceLocation customTexture
}

AnimalDataView "1" *--> "colonyView 1" ColonyView 
CitizenDataManager  ..>  CitizenData : «create»
CitizenDataManager  ..>  CitizenDataView : «create»
ColonyList~T~  ..>  Colony : «create»
ColonyManager  ..>  ColonyList~T~ : «create»
ColonyManager "1" *--> "colonyViews *" ColonyList~T~ 
ColonyView  ..>  AnimalDataView : «create»
ColonyView  ..>  GraveManagerView : «create»
ColonyView  ..>  VisitorDataView : «create»
VisitorData  -->  CitizenData 
VisitorDataView  -->  CitizenDataView 
```
