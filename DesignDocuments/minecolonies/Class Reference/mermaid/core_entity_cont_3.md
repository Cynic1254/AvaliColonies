# core.entity (cont. 3)

65 classes, 20 internal relationships shown.

```mermaid
classDiagram
direction BT

class AnimalColonyHandler {
  + AnimalColonyHandler(IManagedAnimal~Entity~) 
  # IColony? colony
  # int colonyId
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  + updateColonyClient() void
  + registerWithColony(int, int) void
   int colonyId
   IColony? colony
}
class CampWalkAI {
  + CampWalkAI(AbstractEntityMinecoloniesMonster, ITickRateStateMachine~IState~) 
  - walk() boolean
}
class CavalryHorseEntity {
  + CavalryHorseEntity(EntityType~Horse~, Level) 
  - IAnimalDataView animalDataView
  ~ IAnimalData animalData
  - long lastDismountTime
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  # getStandingEyeHeight(Pose, EntityDimensions) float
  # createNavigation(Level) PathNavigation
  + tick() void
  + readAdditionalSaveData(CompoundTag) void
  - approachYaw(float, float, float) float
  + shouldBeSaved() boolean
  - upcomingPathRequiresClimbing(Path?) boolean
  # isHorizontalCollisionMinor(Vec3) boolean
  + clearFor(Entity) boolean
  + canMate(Animal) boolean
  + addAdditionalSaveData(CompoundTag) void
  + setLeashedTo(Entity, boolean) void
  + dropLeash(boolean, boolean) void
  # removePassenger(Entity) void
  + prepareForCombat(float) void
  + logActiveGoals() void
  + aiStep() void
  + hurt(DamageSource, float) boolean
  + hasCavalryRider() boolean
  + hadHorizontalCollission() boolean
  + reserve(Entity) void
  + reservedBy() UUID?
  + createFromVanilla(IColony, Level, AbstractHorse) CavalryHorseEntity?
  # addPassenger(Entity) void
  + registerGoals() void
  # defineSynchedData() void
  + clearReservation() void
  + hasReservation() boolean
  + hasTrainer() boolean
   int managedAnimalId
   float combatCooldown
   int colonyId
   boolean readyForCombat
   IBuilding stableBuilding
   IAnimalData animalData
   EntityDataAccessor~Integer~ animalIdAccessor
   double passengersRidingOffset
   boolean inStable
   EntityDataAccessor~Integer~ colonyIdAccessor
   CavalryHorseEntity entity
   long lastDismountTime
   IAnimalDataView animalDataView
}
class CitizenColonyHandler {
  + CitizenColonyHandler(AbstractEntityCitizen) 
  # int colonyId
  # IColony? colony
  + onSyncDataUpdate(EntityDataAccessor~?~) void
  + updateColonyClient() void
  + registerWithColony(int, int) void
  + onCitizenRemoved() void
  + registered() boolean
   int colonyId
   IColony? colony
   IBuilding? workBuilding
   IColony? colonyOrRegister
   IBuilding? homeBuilding
}
class CitizenCombatTracker {
  + CitizenCombatTracker(EntityCitizen) 
   Component deathMessage
}
class CitizenDiseaseHandler {
  + CitizenDiseaseHandler(ICitizenData) 
  - Disease? disease
  - boolean sleepsAtHospital
  + write(CompoundTag) void
  + setDisease(Disease?) boolean
  + onCollission(ICitizenData) void
  + read(CompoundTag) void
  + cure() void
  + sleepsAtHospital() boolean
  + update(int) void
  - canBecomeSick() boolean
   boolean hurt
   boolean sick
   Disease? disease
   boolean sleepsAtHospital
}
class CitizenExperienceHandler {
  + CitizenExperienceHandler(AbstractEntityCitizen) 
  + updateLevel() void
  + addExperience(double) void
  + gatherXp() void
  + dropExperience() void
}
class CitizenFoodHandler {
  + CitizenFoodHandler(ICitizenData) 
  - EvictingQueue~Item~ lastEatenFoods
  + getDiseaseModifier(double) double
  + checkLastEaten(Item) int
  + hasFullFoodHistory() boolean
  + read(CompoundTag) void
  + addLastEaten(Item) void
  + write(CompoundTag) void
   CitizenFoodStats foodHappinessStats
   Item lastEaten
   ImmutableList~Item~ lastEatenFoods
}
class CitizenHappinessHandler {
  + CitizenHappinessHandler(ICitizenData) 
  + CitizenHappinessHandler() 
  + getHappiness(IColony, ICitizenData) double
  + read(CompoundTag, boolean) void
  + addModifier(IHappinessModifier) void
  + write(CompoundTag, boolean) void
  + getGuardFactor(IColony) double
  + resetModifier(String) void
  + processDailyHappiness(ICitizenData) void
  + getSocialModifier(IColony) double
  + getMysticalSiteFactor(IColony) double
  + getModifier(String) IHappinessModifier
  + getFoodFactor(ICitizenData) double
   List~String~ modifiers
}
class CitizenInventoryHandler {
  + CitizenInventoryHandler(AbstractEntityCitizen) 
  + findFirstSlotInInventoryWith(Block) int
  + getItemCountInInventory(Item) int
  + getItemCountInInventory(Block) int
  + hasItemInInventory(Block) boolean
  + hasItemInInventory(Item) boolean
  + findFirstSlotInInventoryWith(Item) int
   boolean inventoryFull
}
class CitizenJobHandler {
  + CitizenJobHandler(AbstractEntityCitizen) 
  - ITickingStateAI workAI
  + onJobChanged(IJob~?~?) void
  + getColonyJob(Class~J~) J?
  + shouldRunAvoidance() boolean
   IJob~?~? colonyJob
   IJob~?~? modelDependingOnJob
   ITickingStateAI workAI
}
class CitizenMournHandler {
  + CitizenMournHandler(ICitizenData) 
  - boolean isMourning
  - Set~String~ deceasedCitizens
  + write(CompoundTag) void
  + addDeceasedCitizen(String) void
  + clearDeceasedCitizen() void
  + read(CompoundTag) void
  + removeDeceasedCitizen(String) void
  + shouldMourn() boolean
   boolean isMourning
   Set~String~ deceasedCitizens
}
class CitizenSkillHandler {
  + CitizenSkillHandler() 
  + getLevel(Skill) int
  + addXpToSkill(Skill, double, ICitizenData) void
  + init(IColony, ICitizenData?, ICitizenData?, Random) void
  + levelUp(ICitizenData) void
  + incrementLevel(Skill, int) void
  + write() CompoundTag
  + tryLevelUpIntelligence(Random, double, ICitizenData) boolean
  + removeXpFromSkill(Skill, double, ICitizenData) void
  + init(int) void
  + read(CompoundTag) void
   Map~Skill, SkillData~ skills
   double totalXP
}
class CitizenSleepHandler {
  + CitizenSleepHandler(AbstractEntityCitizen) 
  - spawnCitizenFromBed() void
  + shouldGoSleep() boolean
  + trySleep(BlockPos) boolean
  + onWakeUp() void
  - notifyCitizenHandlersOfWakeUp() void
   boolean isAsleep
   boolean asleep
   BlockPos bedLocation
}
class CustomArrowEntity {
  + CustomArrowEntity(EntityType~Arrow~, Level) 
  - float waterInertia
  - Predicate~EntityHitResult~ onHitCallback
  # onHitEntity(EntityHitResult) void
  + save(CompoundTag) boolean
  + load(CompoundTag) void
  + tick() void
  # doPostHurtEffects(LivingEntity) void
  + setPlayerArmorPierce() void
  + shouldFall() boolean
   float waterInertia
   Predicate~EntityHitResult~ onHitCallback
   Packet~ClientGamePacketListener~ addEntityPacket
}
class DruidPotionEntity {
  + DruidPotionEntity(EntityType~ThrownPotion~, Level) 
  - BiPredicate~LivingEntity, MobEffect~? entitySelectionPredicate
  + throwPotionAt(ItemStack, LivingEntity, AbstractEntityCitizen, Level, float, float, BiPredicate~LivingEntity, MobEffect~) void
  + applySplash(List~MobEffectInstance~, Entity?) void
   AbstractEntityCitizen? owner
   BiPredicate~LivingEntity, MobEffect~? entitySelectionPredicate
   Packet~ClientGamePacketListener~ addEntityPacket
}
class EntityAIBreakDoor {
  + EntityAIBreakDoor(Mob) 
  + canContinueToUse() boolean
  + start() void
  + tick() void
  + stop() void
}
class EntityAmazonChief {
  + EntityAmazonChief(EntityType~EntityAmazonChief~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityAmazonChiefRaider {
  + EntityAmazonChiefRaider(EntityType~EntityAmazonChiefRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityAmazonSpearman {
  + EntityAmazonSpearman(EntityType~AbstractEntityAmazon~, Level) 
}
class EntityAmazonSpearmanRaider {
  + EntityAmazonSpearmanRaider(EntityType~AbstractEntityAmazonRaider~, Level) 
}
class EntityArcherAmazon {
  + EntityArcherAmazon(EntityType~EntityArcherAmazon~, Level) 
   double attackDelayModifier
}
class EntityArcherAmazonRaider {
  + EntityArcherAmazonRaider(EntityType~EntityArcherAmazonRaider~, Level) 
   double attackDelayModifier
}
class EntityArcherBarbarian {
  + EntityArcherBarbarian(EntityType~EntityArcherBarbarian~, Level) 
}
class EntityArcherBarbarianRaider {
  + EntityArcherBarbarianRaider(EntityType~EntityArcherBarbarianRaider~, Level) 
}
class EntityArcherMummy {
  + EntityArcherMummy(EntityType~EntityArcherMummy~, Level) 
}
class EntityArcherMummyRaider {
  + EntityArcherMummyRaider(EntityType~EntityArcherMummyRaider~, Level) 
}
class EntityArcherPirate {
  + EntityArcherPirate(EntityType~EntityArcherPirate~, Level) 
}
class EntityArcherPirateRaider {
  + EntityArcherPirateRaider(EntityType~EntityArcherPirateRaider~, Level) 
}
class EntityBarbarian {
  + EntityBarbarian(EntityType~EntityBarbarian~, Level) 
}
class EntityBarbarianRaider {
  + EntityBarbarianRaider(EntityType~EntityBarbarianRaider~, Level) 
}
class EntityCaptainPirate {
  + EntityCaptainPirate(EntityType~EntityCaptainPirate~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityCaptainPirateRaider {
  + EntityCaptainPirateRaider(EntityType~EntityCaptainPirateRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityChiefBarbarian {
  + EntityChiefBarbarian(EntityType~EntityChiefBarbarian~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityChiefBarbarianRaider {
  + EntityChiefBarbarianRaider(EntityType~EntityChiefBarbarianRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityCitizen {
  + EntityCitizen(EntityType~PathfinderMob~, Level) 
  - ThreatTable threatTable
  - ILocation location
  - ICitizenColonyHandler citizenColonyHandler
  - ICitizenDataView citizenDataView
  - ICitizenSleepHandler citizenSleepHandler
  - ICitizenExperienceHandler citizenExperienceHandler
  - ICitizenInventoryHandler citizenInventoryHandler
  - ITickRateStateMachine~IState~ citizenAI
  - CitizenCombatTracker combatTracker
  - int citizenId
  - boolean isGlowing
  - int maxAir
  - ICitizenJobHandler citizenJobHandler
  - ICitizenData citizenData
  + getCapability(Capability~T~, Direction) LazyOptional~T~
  - updateHealing() boolean
  - checkHeal() void
  # dropEquipment() void
  + canClimbVines() boolean
  + canPathOnRails() boolean
  + createMenu(int, Inventory, Player) AbstractContainerMenu?
  + die(DamageSource) void
  - decrementCallForHelpCooldown() void
  # doPush(Entity) void
  - onServerUpdateHandlers() boolean
  + refreshDimensions() void
  + playMoveAwaySound() void
  - checkIfValidDamageSource(DamageSource, float) boolean
  - triggerDeathAchievement(DamageSource, IJob~?~) void
  - shouldBeInactive() boolean
  - childFoodInteraction(ItemStack, Player, InteractionHand) void
  + readAdditionalSaveData(CompoundTag) void
  + onPlayerCollide(Player) void
  - performMoveAway(Entity?) void
  + aiStep() void
  - decreaseWalkingSaturation() void
  + hashCode() int
  - directPlayerInteraction(Player, InteractionHand) InteractionResult?
  - eatFoodInteraction(ItemStack, Player, InteractionHand) void
  - decreaseIdleSaturation() boolean
  + checkAndHandleImportantInteractions(Player, InteractionHand) InteractionResult
  + hurt(DamageSource, float) boolean
  + spawnAnim() void
  - initTasks() void
  + callForHelp(Entity, int) void
  + isInteractionItem(ItemStack) boolean
  # pickUpItem(ItemEntity) void
  - handleInWallDamage(DamageSource) boolean
  + queueSound(SoundEvent, BlockPos, int, int, float, float) void
  + equals(Object) boolean
  + decreaseSaturationForAction() void
  + refreshCitizenDataView() boolean
  + getItemBySlot(EquipmentSlot) ItemStack
  + addAdditionalSaveData(CompoundTag) void
  + markDirty(int) void
  + remove(RemovalReason) void
  + decreaseSaturationForContinuousAction() void
  # defineSynchedData() void
  + requiresCustomPersistence() boolean
  # hurtCurrentlyUsedShield(float) void
  - onTickDecrements() boolean
  + setTexture() void
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  - initialize() EntityState?
  - handleDamagePerformed(DamageSource, float, Entity) boolean
  + queueSound(SoundEvent, BlockPos, int, int) void
  - onLivingSoundUpdate() void
  - updateVisualData() boolean
   boolean baby
   int armorValue
   int maxAirSupply
   IItemHandler itemHandlerCitizen
   int citizenId
   ITickRateStateMachine~IState~ citizenAI
   RemovalReason removed
   Iterable~ItemStack~ allSlots
   ICivilianData civilianData
   Component? customName
   int civilianID
   ICitizenColonyHandler citizenColonyHandler
   ILocation location
   boolean dead
   boolean isGlowing
   float scale
   ICitizenJobHandler citizenJobHandler
   boolean active
   float rotationYaw
   float speed
   ICitizenDataView citizenDataView
   String scoreboardName
   ICitizenData citizenData
   String renderMetadata
   ICitizenSleepHandler citizenSleepHandler
   ThreatTable threatTable
   int teamId
   int maxAir
   float rotationPitch
   ICitizenExperienceHandler citizenExperienceHandler
   CombatTracker combatTracker
   boolean currentlyGlowing
   ICitizenInventoryHandler citizenInventoryHandler
   VisibleCitizenStatus visibleStatusIfNone
   InventoryCitizen inventoryCitizen
   boolean isChild
   boolean suppressingBounce
}
class EntityDrownedArcherPirate {
  + EntityDrownedArcherPirate(EntityType~EntityDrownedArcherPirate~, Level) 
  + penetrateFluids() boolean
  + initStatsFor(double, double, double) void
}
class EntityDrownedArcherPirateRaider {
  + EntityDrownedArcherPirateRaider(EntityType~EntityDrownedArcherPirateRaider~, Level) 
  + penetrateFluids() boolean
  + initStatsFor(double, double, double) void
}
class EntityDrownedCaptainPirate {
  + EntityDrownedCaptainPirate(EntityType~EntityDrownedCaptainPirate~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityDrownedCaptainPirateRaider {
  + EntityDrownedCaptainPirateRaider(EntityType~EntityDrownedCaptainPirateRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityDrownedPirate {
  + EntityDrownedPirate(EntityType~EntityDrownedPirate~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityDrownedPirateRaider {
  + EntityDrownedPirateRaider(EntityType~EntityDrownedPirateRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityMercenary {
  + EntityMercenary(EntityType~EntityMercenary~, Level) 
  - boolean isLeader
  - IColony colony
  - GeneralEntityWalkToProxy proxy
  + aiStep() void
  + hurt(DamageSource, float) boolean
  + addAdditionalSaveData(CompoundTag) void
  - findMercenarySpawnPos(IColony, int) BlockPos
  - isValidSpawnForMercenaries(LevelAccessor, BlockPos, int) boolean
  - spawnEvent() boolean
  + requiresCustomPersistence() boolean
  - shouldDespawn() boolean
  # playStepSound(BlockPos, BlockState) void
  + readAdditionalSaveData(CompoundTag) void
  # doPush(Entity) void
  - handleStateException(RuntimeException) void
  + spawnMercenariesInColony(IColony) void
  # getHurtSound(DamageSource) SoundEvent?
  + setDoSpawnEvent() void
  + registerWithColony() void
   Builder defaultAttributes
   IState state
   GeneralEntityWalkToProxy proxy
   boolean initialized
   AbstractAdvancedPathNavigate navigation
   int teamId
   IColony colony
   SoundEvent? ambientSound
   List~EntityMercenary~ isLeader
   SoundEvent? deathSound
   Component name
}
class EntityMercenaryAI {
  + EntityMercenaryAI(EntityMercenary) 
  - fighting() boolean
  + canContinueToUse() boolean
  + canUse() boolean
  - hasTarget() boolean
  - handleAIException(RuntimeException) void
  - patrol() boolean
  - initialize() boolean
}
class EntityMummy {
  + EntityMummy(EntityType~EntityMummy~, Level) 
}
class EntityMummyRaider {
  + EntityMummyRaider(EntityType~EntityMummyRaider~, Level) 
}
class EntityNorsemenArcher {
  + EntityNorsemenArcher(EntityType~EntityNorsemenArcher~, Level) 
}
class EntityNorsemenArcherRaider {
  + EntityNorsemenArcherRaider(EntityType~EntityNorsemenArcherRaider~, Level) 
}
class EntityNorsemenChief {
  + EntityNorsemenChief(EntityType~EntityNorsemenChief~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityNorsemenChiefRaider {
  + EntityNorsemenChiefRaider(EntityType~EntityNorsemenChiefRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityPharao {
  + EntityPharao(EntityType~EntityPharao~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityPharaoRaider {
  + EntityPharaoRaider(EntityType~EntityPharaoRaider~, Level) 
  + initStatsFor(double, double, double) void
}
class EntityPirate {
  + EntityPirate(EntityType~EntityPirate~, Level) 
}
class EntityPirateRaider {
  + EntityPirateRaider(EntityType~EntityPirateRaider~, Level) 
}
class EntityShieldmaiden {
  + EntityShieldmaiden(EntityType~EntityShieldmaiden~, Level) 
}
class EntityShieldmaidenRaider {
  + EntityShieldmaidenRaider(EntityType~EntityShieldmaidenRaider~, Level) 
}
class FireArrowEntity {
  + FireArrowEntity(EntityType~Arrow~, Level) 
   ItemStack pickupItem
   Entity owner
}
class IAnimalColonyHandler {
<<Interface>>
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  + updateColonyClient() void
  + registerWithColony(int, int) void
   int colonyId
   IColony? colony
}
class MobAIRegistry {
  + MobAIRegistry() 
  + registerNewAiTaskForMobs(int, Function~AbstractEntityMinecoloniesMonster, Goal~, Predicate~AbstractEntityMinecoloniesMonster~) IMobAIRegistry
  + getEntityAiTargetTasksForMobs(AbstractEntityMinecoloniesMonster) Multimap~Integer, Goal~
  + getEntityAiTasksForMobs(AbstractEntityMinecoloniesMonster) Multimap~Integer, Goal~
  + applyToMob(AbstractEntityMinecoloniesMonster) void
  - setupMobAiTasks(IMobAIRegistry) void
  + registerNewStateAI(Function~AbstractEntityMinecoloniesMonster, IStateAI~, Predicate~AbstractEntityMinecoloniesMonster~) IMobAIRegistry
  + registerNewAiTargetTaskForMobs(int, Function~AbstractEntityMinecoloniesMonster, Goal~, Predicate~AbstractEntityMinecoloniesMonster~) IMobAIRegistry
}
class NewBobberEntity {
  + NewBobberEntity(EntityType~Projectile~, Level) 
  + NewBobberEntity(SpawnEntity, Level) 
  - EntityCitizen angler
  - boolean readyToCatch
  - checkCollision() void
  + lerpTo(double, double, double, float, float, int, boolean) void
  + readSpawnData(FriendlyByteBuf) void
  + shouldRenderAtSqrDistance(double) boolean
  # addAdditionalSaveData(CompoundTag) void
  + onSyncedDataUpdated(EntityDataAccessor~?~) void
  + setInUse() void
  # bringInHookedEntity() void
  + tick() void
  + handleEntityEvent(byte) void
  # defineSynchedData() void
  - setHookedEntity() void
  # updateRotation() void
  + writeSpawnData(FriendlyByteBuf) void
  - catchingFish(BlockPos) void
  + canChangeDimensions() boolean
  + shouldStopFishing() boolean
  + setAngler(EntityCitizen, int, int) void
  # readAdditionalSaveData(CompoundTag) void
   boolean readyToCatch
   boolean movementNoisy
   EntityCitizen? angler
   int damage
   Packet~ClientGamePacketListener~ addEntityPacket
}
class RaiderMeleeAI~T~ {
  + RaiderMeleeAI(T, ITickRateStateMachine~IState~) 
  # moveInAttackPosition(LivingEntity) PathResult
  # isAttackableTarget(LivingEntity) boolean
  # doAttack(LivingEntity) void
  # isWithinPersecutionDistance(LivingEntity) boolean
   int attackDelay
   double attackDistance
   int searchRange
}
class RaiderRangedAI~T~ {
  + RaiderRangedAI(T, ITickRateStateMachine~IState~) 
  - int ATTACK_DELAY
  # moveInAttackPosition(LivingEntity) PathResult
  # doAttack(LivingEntity) void
  + canAttack() boolean
  # isWithinPersecutionDistance(LivingEntity) boolean
  # isInDistanceForAttack(LivingEntity) boolean
  # checkForTarget() boolean
  # isAttackableTarget(LivingEntity) boolean
   double randomPitch
   int ATTACK_DELAY
   double attackDistance
}
class RaiderWalkAI {
  + RaiderWalkAI(AbstractEntityMinecoloniesRaider, ITickRateStateMachine~IState~) 
  - walk() boolean
  - walkToCampFire() void
}
class SittingEntity {
  + SittingEntity(EntityType~?~, Level, double, double, double, int) 
  + SittingEntity(EntityType~?~, Level) 
  ~ int maxLifeTime
  + sitDown(BlockPos, Mob, int) boolean
  # addAdditionalSaveData(CompoundTag) void
  # defineSynchedData() void
  + getDismountLocationForPassenger(LivingEntity) Vec3
  + tick() void
  + remove(RemovalReason) void
  + hurt(DamageSource, float) boolean
  # addPassenger(Entity) void
  + isSittingPosOccupied(BlockPos, Level) boolean
  # readAdditionalSaveData(CompoundTag) void
  # removePassenger(Entity) void
   int maxLifeTime
   boolean pickable
   Packet~ClientGamePacketListener~ addEntityPacket
   BlockPos sittingPos
}
class SpearEntity {
  + SpearEntity(Level, LivingEntity, ItemStack) 
  + SpearEntity(EntityType~ThrownTrident~, Level) 
  # findHitEntity(Vec3, Vec3) EntityHitResult?
  + addAdditionalSaveData(CompoundTag) void
  + readAdditionalSaveData(CompoundTag) void
  + tickDespawn() void
  + load(CompoundTag) void
  + shouldRender(double, double, double) boolean
  + tick() void
  # onHitEntity(EntityHitResult) void
  + save(CompoundTag) boolean
   ItemStack pickupItem
   SoundEvent attackSound
   float waterInertia
   SoundEvent defaultHitGroundSoundEvent
   Packet~ClientGamePacketListener~ addEntityPacket
}

AnimalColonyHandler  ..>  IAnimalColonyHandler 
CavalryHorseEntity  ..>  AnimalColonyHandler : «create»
CavalryHorseEntity "1" *--> "animalColonyHandler 1" IAnimalColonyHandler 
CitizenCombatTracker "1" *--> "citizen 1" EntityCitizen 
EntityCitizen  ..>  CitizenColonyHandler : «create»
EntityCitizen  ..>  CitizenCombatTracker : «create»
EntityCitizen  ..>  CitizenExperienceHandler : «create»
EntityCitizen  ..>  CitizenInventoryHandler : «create»
EntityCitizen  ..>  CitizenJobHandler : «create»
EntityCitizen  ..>  CitizenSleepHandler : «create»
EntityCitizen "1" *--> "combatTracker 1" CitizenCombatTracker 
EntityMercenary  ..>  EntityMercenaryAI : «create»
EntityMercenaryAI "1" *--> "entity 1" EntityMercenary 
FireArrowEntity  -->  CustomArrowEntity 
MobAIRegistry  ..>  CampWalkAI : «create»
MobAIRegistry  ..>  EntityAIBreakDoor : «create»
MobAIRegistry  ..>  RaiderMeleeAI~T~ : «create»
MobAIRegistry  ..>  RaiderRangedAI~T~ : «create»
MobAIRegistry  ..>  RaiderWalkAI : «create»
NewBobberEntity "1" *--> "angler 1" EntityCitizen 
```
