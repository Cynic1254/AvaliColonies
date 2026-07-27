# api.entity

62 classes, 67 internal relationships shown.

```mermaid
classDiagram
direction BT

class AIBlockingEventType {
<<enumeration>>
  + AIBlockingEventType() 
  + values() AIBlockingEventType[]
  + valueOf(String) AIBlockingEventType
}
class AIEventTarget~S~ {
  + AIEventTarget(AIBlockingEventType, IBooleanConditionSupplier, S, int) 
  + AIEventTarget(AIBlockingEventType, IStateSupplier~S~, int) 
  + AIEventTarget(AIBlockingEventType, IBooleanConditionSupplier, IStateSupplier~S~, int) 
}
class AIOneTimeEventTarget~S~ {
  + AIOneTimeEventTarget(IBooleanConditionSupplier, IStateSupplier~S~) 
  + AIOneTimeEventTarget(S) 
  + AIOneTimeEventTarget(IBooleanConditionSupplier, S) 
  + AIOneTimeEventTarget(IStateSupplier~S~) 
}
class AITarget~S~ {
  + AITarget(S, IBooleanConditionSupplier, IStateSupplier~S~, int) 
  + AITarget(S, S?, int) 
  + AITarget(S, IStateSupplier~S~, int) 
  # AITarget(IBooleanConditionSupplier, IStateSupplier~S~, int) 
}
class AIWorkerState {
<<enumeration>>
  - AIWorkerState(boolean) 
  - boolean isOkayToEat
  + valueOf(String) AIWorkerState
  + values() AIWorkerState[]
   boolean isOkayToEat
}
class AbstractCivilianEntity {
  # AbstractCivilianEntity(EntityType~PathfinderMob~, Level) 
  + push(Entity) void
  + checkBedExists() boolean
  + onPlayerCollide(Player) void
  + queueSound(SoundEvent, BlockPos, int, int, float, float) void
  # getStandingEyeHeight(Pose, EntityDimensions) float
  + startRiding(Entity, boolean) boolean
  + toString() String
  + queueSound(SoundEvent, BlockPos, int, int) void
  + markDirty(int) void
   int citizenId
   ICivilianData civilianData
   int civilianID
}
class AbstractEntityCitizen {
  + AbstractEntityCitizen(EntityType~PathfinderMob~, Level) 
  - boolean female
  - ResourceLocation modelId
  # ITickRateStateMachine~IState~ entityStateController
  - ResourceLocation texture
  - int textureId
  - String renderMetadata
  + setItemSlot(EquipmentSlot, ItemStack) void
  + onArmorRemove(ItemStack, EquipmentSlot) void
  + onArmorAdd(ItemStack, EquipmentSlot) void
  + interactAt(Player, Vec3, InteractionHand) InteractionResult
  + markEquipmentDirty() void
  + setTexture() void
  + calculateDamageAfterAbsorbs(DamageSource, float) float
  + aiStep() void
  + onPlayerCollide(Player) void
  + detectEquipmentUpdates() void
  + decreaseSaturationForAction() void
  # defineSynchedData() void
  + push(Entity) void
  + checkCanDropLoot() boolean
  + setTextureDirty() void
  + playMoveAwaySound() void
  + canPickUpLoot() boolean
  + decreaseSaturationForContinuousAction() void
  + callForHelp(Entity, int) void
  + setOwnRotation(float, float) void
   Component displayName
   boolean noAi
   IItemHandler itemHandlerCitizen
   int offsetTicks
   Builder defaultAttributes
   boolean pushable
   ResourceLocation modelType
   ITickRateStateMachine~IState~ entityStateController
   int ticksExisted
   ResourceLocation texture
   ICitizenColonyHandler citizenColonyHandler
   ILocation location
   boolean dead
   int textureId
   boolean controlledByLocalInstance
   ICitizenJobHandler citizenJobHandler
   float rotationYaw
   GoalSelector tasks
   boolean pushedByFluid
   boolean female
   ICitizenDataView citizenDataView
   ICitizenData citizenData
   String renderMetadata
   ICitizenSleepHandler citizenSleepHandler
   int recentlyHit
   float rotationPitch
   ICitizenExperienceHandler citizenExperienceHandler
   int teamColor
   boolean sleeping
   ICitizenInventoryHandler citizenInventoryHandler
   ResourceLocation modelId
   InventoryCitizen inventoryCitizen
   boolean blocking
   boolean isChild
   AbstractAdvancedPathNavigate navigation
   RandomSource random
}
class AbstractHappinessModifier {
  + AbstractHappinessModifier() 
  + AbstractHappinessModifier(String, double, IHappinessSupplierWrapper) 
  + String id
  - double weight
  + read(CompoundTag, boolean) void
  + write(CompoundTag, boolean) void
  + getFactor(ICitizenData?) double
   double weight
   String id
}
class BasicEvent {
  + BasicEvent(IStateEventType, IBooleanConditionSupplier, IStateSupplier~IAIState~) 
  - IStateEventType eventType
   IStateEventType eventType
   IAIState? state
}
class BasicStateMachine~T, S~ {
  # BasicStateMachine(S, Consumer~RuntimeException~) 
  - S state
  + removeTransition(T) void
  + checkTransition(T) boolean
  + addTransition(T) void
  + addTransitionGroup(List~S~, T) void
  + reset() void
  # onException(RuntimeException) void
  + setHistoryEnabled(boolean, int) void
  + transitionToNext(T) boolean
  + tick() void
   Component history
   S state
}
class BasicTransition~S~ {
  + BasicTransition(S, IBooleanConditionSupplier, IStateSupplier~S~) 
  # BasicTransition(IBooleanConditionSupplier, IStateSupplier~S~) 
  - Component name
  - S? state
  - IStateSupplier~S~ nextState
  + withName(String) BasicTransition~S~
  + getMethodName(Serializable) String
  + checkCondition() boolean
   S nextState
   Component name
   S state
}
class CitizenAIState {
<<enumeration>>
  - CitizenAIState() 
  + valueOf(String) CitizenAIState
  + values() CitizenAIState[]
}
class CombatAIStates {
<<enumeration>>
  - CombatAIStates(boolean) 
  - boolean isOkayToEat
  + values() CombatAIStates[]
  + valueOf(String) CombatAIStates
   boolean isOkayToEat
}
class DesiredActivity {
<<enumeration>>
  + DesiredActivity() 
  + valueOf(String) DesiredActivity
  + values() DesiredActivity[]
}
class DynamicHappinessSupplier {
  + DynamicHappinessSupplier(ResourceLocation) 
  + DynamicHappinessSupplier() 
  + deserializeNBT(CompoundTag) void
  + getValue(ICitizenData) double
  + serializeNBT() CompoundTag
   double lastCachedValue
}
class EntityState {
<<enumeration>>
  + EntityState() 
  + values() EntityState[]
  + valueOf(String) EntityState
}
class ExpirationBasedHappinessModifier {
  + ExpirationBasedHappinessModifier(String, double, IHappinessSupplierWrapper, int) 
  + ExpirationBasedHappinessModifier() 
  - int days
  + dayEnd(ICitizenData) void
  + reset() void
  + write(CompoundTag, boolean) void
  + read(CompoundTag, boolean) void
  + getFactor(ICitizenData) double
   int days
}
class GuardGear {
  + GuardGear(EquipmentTypeEntry, EquipmentSlot, int, int, Tuple~Integer, Integer~, Tuple~Integer, Integer~) 
  - int minLevelRequired
  - int minArmorLevel
  - EquipmentTypeEntry itemNeeded
  - int minBuildingLevelRequired
  - int maxLevelRequired
  - int maxBuildingLevelRequired
  - int maxArmorLevel
  - EquipmentSlot type
  + test(ItemStack) boolean
   EquipmentTypeEntry itemNeeded
   int minLevelRequired
   EquipmentSlot type
   int minBuildingLevelRequired
   int minArmorLevel
   int maxBuildingLevelRequired
   int maxArmorLevel
   int maxLevelRequired
}
class GuardGearBuilder {
  - GuardGearBuilder() 
  + buildGearForLevel(int, int, Tuple~Integer, Integer~, Tuple~Integer, Integer~) List~GuardGear~
}
class HappinessRegistry {
  + HappinessRegistry() 
  + loadFrom(CompoundTag, boolean) IHappinessModifier?
   IForgeRegistry~HappinessFactorTypeEntry~ happinessTypeRegistry
   IForgeRegistry~HappinessFunctionEntry~ happinessFunctionRegistry
}
class IAIState {
<<Interface>>
   boolean okayToEat
}
class IBooleanConditionSupplier {
<<Interface>>
   boolean asBoolean
}
class IBuilderUndestroyable {
<<Interface>>

}
class ICitizenColonyHandler {
<<Interface>>
  + onCitizenRemoved() void
  + registered() boolean
  + registerWithColony(int, int) void
  + updateColonyClient() void
  + onSyncDataUpdate(EntityDataAccessor~?~) void
   int colonyId
   IColony colony
   IBuilding? workBuilding
   IColony? colonyOrRegister
   IBuilding? homeBuilding
}
class ICitizenDiseaseHandler {
<<Interface>>
  + onCollission(ICitizenData) void
  + sleepsAtHospital() boolean
  + update(int) void
  + write(CompoundTag) void
  + read(CompoundTag) void
  + cure() void
  + setDisease(Disease) boolean
   boolean hurt
   boolean sick
   Disease? disease
   boolean sleepsAtHospital
}
class ICitizenExperienceHandler {
<<Interface>>
  + gatherXp() void
  + updateLevel() void
  + dropExperience() void
  + addExperience(double) void
}
class ICitizenFoodHandler {
<<Interface>>
  + write(CompoundTag) void
  + read(CompoundTag) void
  + addLastEaten(Item) void
  + getDiseaseModifier(double) double
  + hasFullFoodHistory() boolean
  + checkLastEaten(Item) int
   CitizenFoodStats foodHappinessStats
   Item lastEaten
   ImmutableList~Item~ lastEatenFoods
}
class ICitizenHappinessHandler {
<<Interface>>
  + processDailyHappiness(ICitizenData) void
  + getHappiness(IColony, ICitizenData) double
  + read(CompoundTag, boolean) void
  + addModifier(IHappinessModifier) void
  + write(CompoundTag, boolean) void
  + resetModifier(String) void
  + getModifier(String) IHappinessModifier
   List~String~ modifiers
}
class ICitizenInventoryHandler {
<<Interface>>
  + findFirstSlotInInventoryWith(Item) int
  + getItemCountInInventory(Block) int
  + hasItemInInventory(Block) boolean
  + findFirstSlotInInventoryWith(Block) int
  + hasItemInInventory(Item) boolean
  + getItemCountInInventory(Item) int
   boolean inventoryFull
}
class ICitizenJobHandler {
<<Interface>>
  + getColonyJob(Class~J~) J?
  + onJobChanged(IJob~?~?) void
  + shouldRunAvoidance() boolean
   IJob~?~? colonyJob
   IJob~?~? modelDependingOnJob
   ITickingStateAI workAI
}
class ICitizenMournHandler {
<<Interface>>
  + write(CompoundTag) void
  + shouldMourn() boolean
  + removeDeceasedCitizen(String) void
  + addDeceasedCitizen(String) void
  + clearDeceasedCitizen() void
  + read(CompoundTag) void
   Set~String~ deceasedCitizens
   boolean mourning
}
class ICitizenSkillHandler {
<<Interface>>
  + addXpToSkill(Skill, double, ICitizenData) void
  + incrementLevel(Skill, int) void
  + removeXpFromSkill(Skill, double, ICitizenData) void
  + read(CompoundTag) void
  + init(int) void
  + getLevel(Skill) int
  + levelUp(ICitizenData) void
  + init(IColony, ICitizenData?, ICitizenData?, Random) void
  + tryLevelUpIntelligence(Random, double, ICitizenData) boolean
  + write() CompoundTag
   Map~Skill, SkillData~ skills
   double totalXP
}
class ICitizenSleepHandler {
<<Interface>>
  + onWakeUp() void
  + trySleep(BlockPos) boolean
  + shouldGoSleep() boolean
   boolean asleep
   BlockPos bedLocation
}
class IHappinessModifier {
<<Interface>>
  + getFactor(ICitizenData?) double
  + read(CompoundTag, boolean) void
  + write(CompoundTag, boolean) void
   double weight
   String id
}
class IHappinessSupplierWrapper {
<<Interface>>
  + getValue(ICitizenData) double
   double lastCachedValue
}
class IState {
<<Interface>>

}
class IStateAI {
<<Interface>>

}
class IStateEventType {
<<Interface>>

}
class IStateMachine~T, S~ {
<<Interface>>
  + checkTransition(T) boolean
  + removeTransition(T) void
  + transitionToNext(T) boolean
  + reset() void
  + addTransition(T) void
  + tick() void
  + addTransitionGroup(List~S~, T) void
  + setHistoryEnabled(boolean, int) void
   Component history
   S state
}
class IStateMachineEvent~S~ {
<<Interface>>
   IStateEventType eventType
}
class IStateMachineOneTimeEvent~S~ {
<<Interface>>
  + shouldRemove() boolean
}
class IStateMachineTransition~S~ {
<<Interface>>
  + withName(String) IStateMachineTransition
  + checkCondition() boolean
   S nextState
   Component name
   S state
}
class IStateSupplier~T~ {
<<Interface>>
  + get() T
}
class IThreatTableEntity {
<<Interface>>
   ThreatTable threatTable
}
class ITickRateStateMachine~S~ {
<<Interface>>
  + checkTransition(ITickingTransition~S~) boolean
  + tick() void
   int tickRate
   int currentDelay
}
class ITickingStateAI {
<<Interface>>
  + onRemoval() void
  + tick() void
  + resetAI() void
   ITickRateStateMachine~IAIState~ stateAI
   IState state
}
class ITickingTransition~S~ {
<<Interface>>
  + countdownTicksToUpdate(int) int
   int tickRate
   int ticksToUpdate
}
class ITimeBasedHappinessModifier {
<<Interface>>
  + dayEnd(ICitizenData) void
  + reset() void
   int days
}
class JobStatus {
<<enumeration>>
  + JobStatus() 
  + valueOf(String) JobStatus
  + values() JobStatus[]
}
class Skill {
<<enumeration>>
  + Skill() 
  + values() Skill[]
  + valueOf(String) Skill
   Skill? complimentary
   Skill? adverse
}
class StaticHappinessModifier {
  + StaticHappinessModifier() 
  + StaticHappinessModifier(String, double, IHappinessSupplierWrapper) 
  + write(CompoundTag, boolean) void
}
class StaticHappinessSupplier {
  + StaticHappinessSupplier(double) 
  + StaticHappinessSupplier() 
  + deserializeNBT(CompoundTag) void
  + serializeNBT() CompoundTag
  + getValue(ICitizenData) double
   double lastCachedValue
}
class Status {
<<enumeration>>
  + Status() 
  + values() Status[]
  + valueOf(String) Status
}
class ThreatTable~T~ {
  + ThreatTable(T) 
  + addThreat(LivingEntity, int) void
  - adaptTableToThreat(int) void
  + getThreatFor(LivingEntity) int
  + resetCurrentTargetThreat() void
  + markInvalidTarget() void
  + resetTable() void
  + removeCurrentTarget() void
   LivingEntity targetMob
   ThreatTableEntry target
}
class ThreatTableEntry {
  + ThreatTableEntry(LivingEntity) 
  - int threat
  - long lastSeen
  - LivingEntity entity
  # addThreat(int) void
   long lastSeen
   int threat
   LivingEntity entity
}
class TickRateConstants {
  + TickRateConstants() 
}
class TickRateStateMachine~S~ {
  + TickRateStateMachine(S, Consumer~RuntimeException~) 
  + TickRateStateMachine(S, Consumer~RuntimeException~, int) 
  - int tickRate
  + tick() void
  + checkTransition(ITickingTransition~S~) boolean
   int tickRate
   int currentDelay
}
class TickingEvent~S~ {
  # TickingEvent(IStateEventType, IBooleanConditionSupplier, IStateSupplier~S~, int) 
  - IStateEventType eventType
   IStateEventType eventType
}
class TickingOneTimeEvent~S~ {
  # TickingOneTimeEvent(IStateEventType, IBooleanConditionSupplier, IStateSupplier~S~, int) 
  + shouldRemove() boolean
}
class TickingTransition~S~ {
  + TickingTransition(S, IBooleanConditionSupplier, IStateSupplier~S~, int) 
  + TickingTransition(IBooleanConditionSupplier, IStateSupplier~S~, int) 
  - int tickRate
  - int ticksToUpdate
  + countdownTicksToUpdate(int) int
   int tickRate
   int ticksToUpdate
}
class TimeBasedHappinessModifier {
  + TimeBasedHappinessModifier(String, double, IHappinessSupplierWrapper, Tuple~Integer, Double~[]) 
  + TimeBasedHappinessModifier() 
  + TimeBasedHappinessModifier(String, double, IHappinessSupplierWrapper, BiPredicate~TimeBasedHappinessModifier, ICitizenData~, Tuple~Integer, Double~[]) 
  - int days
  + dayEnd(ICitizenData) void
  + write(CompoundTag, boolean) void
  + getFactor(ICitizenData) double
  + reset() void
  + read(CompoundTag, boolean) void
   int days
}
class VisibleCitizenStatus {
  + VisibleCitizenStatus(ResourceLocation, String, boolean) 
  + VisibleCitizenStatus(ResourceLocation, String) 
  - ResourceLocation icon
  - String translationKey
  - int id
  + shouldRender() boolean
  + getForId(int) VisibleCitizenStatus
   ResourceLocation icon
   int id
   Map~Integer, VisibleCitizenStatus~ visibleStatus
   String translationKey
}

AIBlockingEventType  ..>  IStateEventType 
AIEventTarget~S~  -->  TickingEvent~S~ 
AIEventTarget~S~  ..>  IState 
AIOneTimeEventTarget~S~  -->  TickingOneTimeEvent~S~ 
AIOneTimeEventTarget~S~  ..>  IState 
AITarget~S~  -->  TickingTransition~S~ 
AITarget~S~  ..>  IState 
AIWorkerState  ..>  IAIState 
AbstractEntityCitizen  -->  AbstractCivilianEntity 
AbstractEntityCitizen  ..>  TickRateStateMachine~S~ : «create»
AbstractEntityCitizen "1" *--> "entityStateController 1" ITickRateStateMachine~S~ 
AbstractHappinessModifier  ..>  DynamicHappinessSupplier : «create»
AbstractHappinessModifier  ..>  IHappinessModifier 
AbstractHappinessModifier  ..>  StaticHappinessSupplier : «create»
AbstractHappinessModifier "1" *--> "supplier 1" IHappinessSupplierWrapper 
BasicEvent  -->  BasicTransition~S~ 
BasicEvent  ..>  IStateMachineEvent~S~ 
BasicEvent "1" *--> "eventType 1" IStateEventType 
BasicStateMachine~T, S~  ..>  IState 
BasicStateMachine~T, S~  ..>  IStateMachineTransition~S~ 
BasicStateMachine~T, S~  ..>  IStateMachine~T, S~ 
BasicStateMachine~T, S~ "1" *--> "eventTransitionMap *" IStateEventType 
BasicTransition~S~  ..>  IState 
BasicTransition~S~  ..>  IStateMachineTransition~S~ 
BasicTransition~S~ "1" *--> "condition 1" IBooleanConditionSupplier 
BasicTransition~S~ "1" *--> "nextState 1" IStateSupplier~T~ 
CitizenAIState  ..>  IState 
CombatAIStates  ..>  IAIState 
DynamicHappinessSupplier  ..>  IHappinessSupplierWrapper 
EntityState  ..>  IState 
ExpirationBasedHappinessModifier  -->  AbstractHappinessModifier 
ExpirationBasedHappinessModifier  ..>  ITimeBasedHappinessModifier 
GuardGearBuilder  ..>  GuardGear : «create»
IAIState  -->  IState 
IStateMachineEvent~S~  -->  IStateMachineTransition~S~ 
IStateMachineEvent~S~  ..>  IState 
IStateMachineOneTimeEvent~S~  -->  IStateMachineEvent~S~ 
IStateMachineOneTimeEvent~S~  ..>  IState 
IStateMachineTransition~S~  ..>  IState 
IStateMachine~T, S~  ..>  IState 
IStateMachine~T, S~  ..>  IStateMachineTransition~S~ 
ITickRateStateMachine~S~  -->  IStateMachine~T, S~ 
ITickRateStateMachine~S~  ..>  IState 
ITickingTransition~S~  -->  IStateMachineTransition~S~ 
ITickingTransition~S~  ..>  IState 
ITimeBasedHappinessModifier  -->  IHappinessModifier 
StaticHappinessModifier  -->  AbstractHappinessModifier 
StaticHappinessSupplier  ..>  IHappinessSupplierWrapper 
ThreatTable~T~  ..>  IThreatTableEntity 
ThreatTable~T~  ..>  ThreatTableEntry : «create»
ThreatTable~T~ "1" *--> "threatList *" ThreatTableEntry 
TickRateStateMachine~S~  -->  BasicStateMachine~T, S~ 
TickRateStateMachine~S~  ..>  IState 
TickRateStateMachine~S~  ..>  ITickRateStateMachine~S~ 
TickRateStateMachine~S~ "1" *--> "aiBlockingTransitions *" ITickingTransition~S~ 
TickingEvent~S~  -->  TickingTransition~S~ 
TickingEvent~S~  ..>  IState 
TickingEvent~S~  ..>  IStateMachineEvent~S~ 
TickingEvent~S~ "1" *--> "eventType 1" IStateEventType 
TickingOneTimeEvent~S~  -->  TickingEvent~S~ 
TickingOneTimeEvent~S~  ..>  IState 
TickingOneTimeEvent~S~  ..>  IStateMachineOneTimeEvent~S~ 
TickingTransition~S~  -->  BasicTransition~S~ 
TickingTransition~S~  ..>  IState 
TickingTransition~S~  ..>  ITickingTransition~S~ 
TimeBasedHappinessModifier  -->  AbstractHappinessModifier 
TimeBasedHappinessModifier  ..>  ITimeBasedHappinessModifier 
```
