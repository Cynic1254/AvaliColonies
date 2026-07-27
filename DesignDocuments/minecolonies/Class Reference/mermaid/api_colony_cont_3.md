# api.colony (cont. 3)

77 classes, 67 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractCrafting {
  + AbstractCrafting(ItemStack, int, int, IToken~?~) 
  - int count
  - int minCount
  + equals(Object) boolean
  + hashCode() int
   int minCount
   ItemStack stack
   IToken~?~ recipeID
   int count
}
class AbstractDeliverymanRequestable {
  # AbstractDeliverymanRequestable(int) 
  # int priority
  + getDefaultDeliveryPriority(boolean) int
  + incrementPriorityDueToAging() void
  + scaledPriority(int) int
  + equals(Object) boolean
  + getPlayerActionPriority(boolean) int
  + getMaxBuildingPriority(boolean) int
  + getMaxAgingPriority(boolean) int
  + hashCode() int
   int priority
}
class AbstractTokenFactory~I~ {
  + AbstractTokenFactory() 
  + serialize(IFactoryController, StandardToken, FriendlyByteBuf) void
  + serialize(IFactoryController, StandardToken) CompoundTag
  + deserialize(IFactoryController, CompoundTag) StandardToken
  + deserialize(IFactoryController, FriendlyByteBuf) StandardToken
   TypeToken~StandardToken~ factoryOutputType
}
class AssigningStrategy {
<<enumeration>>
  + AssigningStrategy() 
  + values() AssigningStrategy[]
  + valueOf(String) AssigningStrategy
}
class Burnable {
  + Burnable(int, ItemStack) 
  + Burnable(int) 
  - ItemStack result
  - int count
  + deserialize(IFactoryController, FriendlyByteBuf) Burnable
  + equals(Object) boolean
  + serialize(IFactoryController, FriendlyByteBuf, Burnable) void
  + serialize(IFactoryController, Burnable) CompoundTag
  + copyWithCount(int) IDeliverable
  + deserialize(IFactoryController, CompoundTag) Burnable
  + hashCode() int
  + matches(ItemStack) boolean
   Set~TypeToken~?~~ superClasses
   int minimumCount
   ItemStack result
   int count
}
class Delivery {
  + Delivery(ILocation, ILocation, ItemStack, int) 
  - ItemStack stack
  - ILocation start
  - ILocation target
  + deserialize(IFactoryController, CompoundTag) Delivery
  + equals(Object) boolean
  + deserialize(IFactoryController, FriendlyByteBuf) Delivery
  + hashCode() int
  + serialize(IFactoryController, Delivery) CompoundTag
  + serialize(IFactoryController, FriendlyByteBuf, Delivery) void
  + toString() String
   ILocation target
   Set~TypeToken~?~~ superClasses
   ItemStack stack
   ILocation start
}
class FactoryVoidInput {
  - FactoryVoidInput() 
}
class Food {
  + Food(int, ItemStack, List~ItemStorage~, int) 
  + Food(int, List~ItemStorage~, int) 
  + Food(int, ItemStack, int) 
  + Food(int, int) 
  - int count
  - List~ItemStorage~ exclusionList
  - ItemStack result
  + serialize(IFactoryController, Food) CompoundTag
  + matches(ItemStack) boolean
  + deserialize(IFactoryController, FriendlyByteBuf) Food
  + copyWithCount(int) IDeliverable
  + hashCode() int
  + equals(Object) boolean
  + serialize(IFactoryController, FriendlyByteBuf, Food) void
  + deserialize(IFactoryController, CompoundTag) Food
  + canBeResolvedByBuilding() boolean
   Set~TypeToken~?~~ superClasses
   int minimumCount
   List~ItemStorage~ exclusionList
   ItemStack result
   int count
}
class IAssignmentDataStore~K, V~ {
<<Interface>>
  + getAssignmentForValue(V) K?
   Map~K, Collection~V~~ assignments
}
class IBuilderWorkOrder {
<<Interface>>
  + canBeResolved(IColony, int) boolean
  + onRemoved(IColony) void
  + onCompleted(IColony, ICitizenData) void
  + tooFarFromAnyBuilder(IColony, int) boolean
  + canBuildIgnoringDistance(IBuilding, BlockPos, int) boolean
   String iteratorType
   boolean requested
   BuildingProgressStage stage
   boolean cleared
   int amountOfResources
}
class IConcreteDeliverable {
<<Interface>>
   List~ItemStack~ requestedItems
}
class IDataStore {
<<Interface>>
   IToken~?~ id
}
class IDataStoreManager {
<<Interface>>
  + get(IToken~?~, Supplier~T~) T
  + get(IToken~?~, TypeToken~T~) T
  + removeAll() void
  + remove(IToken~?~) void
}
class IDeliverable {
<<Interface>>
  + matches(ItemStack) boolean
  + copyWithCount(int) IDeliverable
  + canBeResolvedByBuilding() boolean
   ItemStack result
   int minimumCount
   int count
}
class IDeliverymanRequestable {
<<Interface>>
  + incrementPriorityDueToAging() void
   int priority
}
class IFactory~Input, Output~ {
<<Interface>>
  + serialize(IFactoryController, Output) CompoundTag
  + getNewInstance(IFactoryController, Input, Object[]) Output
  + deserialize(IFactoryController, FriendlyByteBuf) Output
  + serialize(IFactoryController, Output, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) Output
   TypeToken~Output~ factoryOutputType
   short serializationId
   TypeToken~Input~ factoryInputType
}
class IFactoryController {
<<Interface>>
  + serialize(Output) CompoundTag
  + getFactoryForOutput(String) IFactory~?, Output~
  + getNewInstance(TypeToken~Output~) Output
  + getFactoryForSerializationId(short) IFactory~?, Output~
  + deserializeList(ListTag) Collection~Output~
  + serialize(FriendlyByteBuf, Output) void
  + deserialize(CompoundTag) Output
  + getFactoryForIO(TypeToken~Input~, TypeToken~Output~) IFactory~Input, Output~
  + getFactoryForInput(TypeToken~Input~) IFactory~Input, ?~
  + registerNewTypeOverrideHandler(ITypeOverrideHandler~Output~) void
  + getFactoryForOutput(short) IFactory~?, Output~
  + deserialize(FriendlyByteBuf) Output
  + getFactoryForOutput(TypeToken~Output~) IFactory~?, Output~
  + registerNewFactory(IFactory~Input, Output~) void
  + getFactoryForInput(String) IFactory~Input, ?~
  + serializeList(Collection~Output~) Tag
  + getNewInstance(TypeToken~Output~, Input, Object[]) Output
}
class IIdentitiesDataStore~K, V~ {
<<Interface>>
   BiMap~K, V~ identities
}
class ILocatable {
<<Interface>>
   ILocation location
}
class ILocation {
<<Interface>>
  + isReachableFromLocation(ILocation) boolean
   BlockPos inDimensionLocation
   ResourceKey~Level~ dimension
}
class ILocationFactory~T, L~ {
<<Interface>>
  + getNewInstance(IFactoryController, T) L
  + getNewInstance(IFactoryController, T, Object[]) L
}
class INonExhaustiveDeliverable {
<<Interface>>
   int leftOver
}
class IPlayerRequestResolver {
<<Interface>>

}
class IProviderHandler {
<<Interface>>
  + getRegisteredResolvers(IRequestResolverProvider) Collection~IToken~?~~
  + getRegisteredResolvers(IToken~?~) Collection~IToken~?~~
  + registerProvider(IRequestResolverProvider) void
  + removeProvider(IRequestResolverProvider) void
  + removeProvider(IToken~?~) void
   IRequestManager manager
}
class IProviderResolverAssignmentDataStore {
<<Interface>>

}
class IQueuedRequestResolver~R~ {
<<Interface>>
  + onSystemReset() void
   ImmutableList~IToken~?~~ allAssignedRequests
}
class IRequest~R~ {
<<Interface>>
  + removeChildren(Collection~T~) void
  + hasChildren() boolean
  + addChild(T) void
  + hasResult() boolean
  + canBeDelivered() boolean
  + addChildren(Collection~T~) void
  + overrideCurrentDeliveries(ImmutableList~ItemStack~) void
  + resetDeliveries() void
  + hasParent() boolean
  + removeChild(T) void
  + childStateUpdated(IRequestManager, IToken~?~) void
  + addDelivery(ItemStack) void
  + addDelivery(List~ItemStack~) void
  + getRequestOfType(Class~T~) Optional~T~
  + getResolverToolTip(IColonyView) List~MutableComponent~
  + setState(IRequestManager, RequestState) void
  + removeChildren(T[]) void
  + addChildren(T[]) void
   TypeToken~R~ type
   IRequester requester
   AssigningStrategy strategy
   List~ItemStack~ displayStacks
   ImmutableCollection~IToken~?~~ children
   Component shortDisplayString
   R request
   ImmutableList~ItemStack~ deliveries
   RequestState state
   Component longDisplayString
   ResourceLocation displayIcon
   T id
   Set~TypeToken~?~~ superClasses
   T? parent
   R? result
}
class IRequestFactory~T, R~ {
<<Interface>>
  + getNewInstance(T, IRequester, IToken~?~) R
  + getNewInstance(IFactoryController, T, Object[]) R
  + getNewInstance(T, IRequester, IToken~?~, RequestState) R
}
class IRequestHandler {
<<Interface>>
  + getRequestOrNull(IToken~?~) IRequest~?~
  + onRequestCancelledDirectly(IToken~?~) void
  + processDirectCancellationOf(IRequest~?~) void
  + onRequestOverruled(IToken~?~) void
  + registerRequest(IRequest~?~) void
  + createRequest(IRequester, Request) IRequest~Request~
  + getRequestsMadeByRequester(IRequester) Collection~IRequest~?~~
  + onRequestResolved(IToken~?~) void
  + onRequestCompleted(IToken~?~) void
  + processDirectCancellationAndNotifyRequesterOf(IRequest~?~) void
  + getRequest(IToken~?~) IRequest~?~
  + cleanRequestData(IToken~?~) void
  + assignRequest(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + onRequestCancelled(IToken~?~) void
  + reassignRequest(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + isAssigned(IToken~?~) boolean
  + assignRequestDefault(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + onChildRequestCancelled(IToken~?~) void
  + assignRequest(IRequest~?~) void
  + resolveRequest(IRequest~?~) void
  + removeRequester(IRequester) void
   IRequestManager manager
}
class IRequestIdentitiesDataStore {
<<Interface>>

}
class IRequestManager {
<<Interface>>
  + overruleRequest(IToken~?~, ItemStack?) void
  + updateRequestState(IToken~?~, RequestState) void
  + onProviderRemovedFromColony(IRequestResolverProvider) void
  + onRequesterRemovedFromColony(IRequester) void
  + createAndAssignRequest(IRequester, T) IToken~?~
  + reset() void
  + reassignRequest(IToken~?~, Collection~IToken~?~~) IToken~?~?
  + getRequestForToken(IToken~?~) IRequest~?~?
  + assignRequest(IToken~?~) void
  + serialize(IFactoryController, FriendlyByteBuf) void
  + getResolverForToken(IToken~?~) IRequestResolver~?~
  + getResolverForRequest(IToken~?~) IRequestResolver~?~?
  + onColonyUpdate(Predicate~IRequest~?~~) void
  + log(String) void
  + deserialize(IFactoryController, FriendlyByteBuf) void
  + onProviderAddedToColony(IRequestResolverProvider) void
  + markDirty() void
  + createRequest(IRequester, T) IToken~?~
   IRetryingRequestResolver retryingRequestResolver
   IDataStoreManager dataStoreManager
   IColony colony
   IFactoryController factoryController
   boolean dirty
   IPlayerRequestResolver playerResolver
}
class IRequestResolver~R~ {
<<Interface>>
  + attemptResolveRequest(IRequestManager, IRequest~R~) List~IToken~?~~?
  + onAssignedRequestCancelled(IRequestManager, IRequest~R~) void
  + getSuitabilityMetric(IRequestManager, IRequest~R~) int
  + onRequestAssigned(IRequestManager, IRequest~R~, boolean) void
  + canResolveRequest(IRequestManager, IRequest~R~) boolean
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~R~) void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~R~) List~IRequest~?~~?
  + onColonyUpdate(IRequestManager, Predicate~IRequest~?~~) void
  + resolveRequest(IRequestManager, IRequest~R~) void
   int priority
   TypeToken~R~ requestType
   boolean valid
}
class IRequestResolverFactory~Resolver~ {
<<Interface>>

}
class IRequestResolverIdentitiesDataStore {
<<Interface>>

}
class IRequestResolverProvider {
<<Interface>>
   ImmutableCollection~IRequestResolver~?~~ resolvers
   IToken~?~ id
}
class IRequestResolverRequestAssignmentDataStore {
<<Interface>>

}
class IRequestSystemBuildingDataStore {
<<Interface>>
  + moveToSyncCitizen(ICitizenData, IRequest~?~) void
   Map~Integer, Collection~IToken~?~~~ openRequestsByCitizen
   Map~Integer, Collection~IToken~?~~~ completedRequestsByCitizen
   Map~IToken~?~, Integer~ citizensByRequest
   Map~TypeToken~?~, Collection~IToken~?~~~ openRequestsByRequestableType
}
class IRequestSystemCrafterJobDataStore {
<<Interface>>
   List~IToken~?~~ assignedTasks
   LinkedList~IToken~?~~ queue
}
class IRequestSystemDeliveryManJobDataStore {
<<Interface>>
   Set~IToken~?~~ ongoingDeliveries
   LinkedList~IToken~?~~ queue
}
class IRequestable {
<<Interface>>
   Set~TypeToken~?~~ superClasses
}
class IRequestableTypeRequestResolverAssignmentDataStore {
<<Interface>>

}
class IRequester {
<<Interface>>
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
   IToken~?~ id
   ILocation location
}
class IRequesterFactory~Input, Output~ {
<<Interface>>

}
class IResolverHandler {
<<Interface>>
  + getResolverForRequest(IToken~?~) IRequestResolver~IRequestable~
  + getResolver(IToken~?~) IRequestResolver~IRequestable~
  + getResolverForRequest(IRequest~?~) IRequestResolver~IRequestable~
  + removeRequestFromResolver(IRequestResolver~?~, IRequest~?~) void
  + removeResolvers(Iterable~IRequestResolver~?~~) void
  + isBeingRemoved(IToken~?~) boolean
  + registerResolver(IRequestResolver~IRequestable~) IToken~?~
  + removeResolvers(IRequestResolver~?~[]) void
  + registerResolvers(IRequestResolver~?~[]) Collection~IToken~?~~
  + addRequestToResolver(IRequestResolver~?~, IRequest~?~) void
  + processResolverForRemoval(Collection~IToken~?~~, IToken~?~) void
  + removeResolver(IRequestResolver~?~) void
  + registerResolvers(Collection~IRequestResolver~?~~) Collection~IToken~?~~
  + removeResolver(IToken~?~) void
  + removeResolverInternal(IRequestResolver~?~) void
  + onColonyUpdate(Predicate~IRequest~?~~) void
  + getRequestsAssignedToResolver(IRequestResolver~?~) Collection~IToken~?~~
   IRequestManager manager
}
class IRetryable {
<<Interface>>

}
class IRetryingRequestResolver {
<<Interface>>
  + updateManager(IRequestManager) void
   boolean reassigning
   int currentReassignmentAttempt
   int maximalDelayBetweenRetriesInTicks
   IToken~?~? currentlyBeingReassignedRequest
   int maximalTries
}
class IServerWorkOrder {
<<Interface>>
  + write(CompoundTag) void
  + isValid(IColony) boolean
  + serializeViewNetworkData(FriendlyByteBuf) void
  + onAdded(IColony, boolean) void
  + canBuild(IBuilding) boolean
  + resetChange() void
  + read(CompoundTag, IWorkManager) void
   boolean dirty
}
class IStackBasedTask {
<<Interface>>
   ItemStack taskStack
   MutableComponent displayPrefix
   int displayCount
}
class IToken~T~ {
<<Interface>>
   T identifier
}
class ITokenFactory~T, RT~ {
<<Interface>>
  + getNewInstance(T) RT
  + getNewInstance(IFactoryController, T, Object[]) RT
}
class ITokenHandler {
<<Interface>>
  + generateNewToken() IToken~?~
   IRequestManager manager
}
class ITokenTokenAssignmentDataStore {
<<Interface>>

}
class ITypeOverrideHandler~O~ {
<<Interface>>
  + matches(TypeToken~?~) boolean
   TypeToken~O~ outputType
}
class IUpdateHandler {
<<Interface>>
  + handleUpdate(UpdateType) void
   int currentVersion
   IRequestManager manager
}
class IWorkManager {
<<Interface>>
  + getWorkOrder(int) IServerWorkOrder
  + clearWorkForCitizen(ICitizenData) void
  + read(CompoundTag) void
  + removeWorkOrder(int) void
  + getWorkOrdersOfType(Class~W~) List~W~
  + getWorkOrder(int, Class~W~) W?
  + addWorkOrder(IServerWorkOrder, boolean) void
  + getOrderedList(Class~W~, BlockPos) List~W~
  + removeWorkOrder(IServerWorkOrder) void
  + getUnassignedWorkOrder(Class~W~) W?
  + onColonyTick(IColony) void
  + write(CompoundTag) void
  + getOrderedList(Predicate~IServerWorkOrder~, BlockPos) List~IServerWorkOrder~
   boolean dirty
   IColony colony
   Map~Integer, IServerWorkOrder~ workOrders
}
class IWorkOrder {
<<Interface>>
  + clearBlueprint() void
  + setBlueprint(Blueprint, Level) void
  + loadBlueprint(Level, Consumer~Blueprint~) void
   Component displayName
   int rotation
   int priority
   BuildingProgressStage stage
   String structurePack
   String fileName
   Blueprint? blueprint
   boolean claimed
   IColony colony
   boolean mirrored
   int targetLevel
   BlockPos location
   BlockPos claimedBy
   int ID
   String structurePath
   int currentLevel
   AABB? boundingBox
   WorkOrderType workOrderType
   String translationKey
}
class IWorkOrderView {
<<Interface>>
  + shouldShowIn(IBuildingView) boolean
  + canBuildIgnoringDistance(BlockPos, int) boolean
  + deserialize(FriendlyByteBuf) void
}
class InitializedTokenFactory {
  + InitializedTokenFactory() 
  + getNewInstance(FactoryVoidInput) StandardToken
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class IntegerFactory {
  + IntegerFactory() 
  + deserialize(IFactoryController, FriendlyByteBuf) Integer
  + serialize(IFactoryController, Integer) CompoundTag
  + deserialize(IFactoryController, CompoundTag) Integer
  + getNewInstance(IFactoryController, FactoryVoidInput, Object[]) Integer
  + serialize(IFactoryController, Integer, FriendlyByteBuf) void
   TypeToken~Integer~ factoryOutputType
   short serializationId
   TypeToken~FactoryVoidInput~ factoryInputType
}
class MinimumStack {
  + MinimumStack(ItemStack, int, int, boolean) 
  + MinimumStack(ItemStack) 
  + MinimumStack(ItemStack, boolean, boolean, ItemStack, int, int, boolean) 
  + MinimumStack(ItemStack, boolean, boolean, ItemStack, int, int) 
  + MinimumStack(ItemStorage) 
  + MinimumStack(ItemStack, boolean) 
  + MinimumStack(ItemStack, int, int) 
  + deserialize(IFactoryController, FriendlyByteBuf) MinimumStack
  + equals(Object) boolean
  + deserialize(IFactoryController, CompoundTag) MinimumStack
   Set~TypeToken~?~~ superClasses
}
class Pickup {
  + Pickup(int) 
  + serialize(IFactoryController, FriendlyByteBuf, Pickup) void
  + equals(Object) boolean
  + deserialize(IFactoryController, FriendlyByteBuf) Pickup
  + toString() String
  + deserialize(IFactoryController, CompoundTag) Pickup
  + serialize(IFactoryController, Pickup) CompoundTag
   Set~TypeToken~?~~ superClasses
}
class PrivateCrafting {
  + PrivateCrafting(ItemStack, int, int, IToken~?~) 
  + deserialize(IFactoryController, CompoundTag) PrivateCrafting
  + serialize(IFactoryController, FriendlyByteBuf, PrivateCrafting) void
  + serialize(IFactoryController, PrivateCrafting) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PrivateCrafting
   Set~TypeToken~?~~ superClasses
}
class PublicCrafting {
  + PublicCrafting(ItemStack, int, IToken~?~) 
  + PublicCrafting(ItemStack, int, int, IToken~?~) 
  + serialize(IFactoryController, PublicCrafting) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PublicCrafting
  + serialize(IFactoryController, FriendlyByteBuf, PublicCrafting) void
  + deserialize(IFactoryController, CompoundTag) PublicCrafting
   Set~TypeToken~?~~ superClasses
}
class RandomSeededTokenFactory {
  + RandomSeededTokenFactory() 
  + getNewInstance(Integer) StandardToken
   short serializationId
   TypeToken~Integer~ factoryInputType
}
class RequestMappingHandler {
  + RequestMappingHandler() 
  - BiMap~Class~?~, Class~?~~ requestableMappings
  + registerRequestableTypeMapping(Class~?~, Class~?~) void
   BiMap~Class~?~, Class~?~~ requestableMappings
}
class RequestState {
<<enumeration>>
  - RequestState() 
  + deserialize(IntTag) RequestState
  + values() RequestState[]
  + serialize() IntTag
  + valueOf(String) RequestState
  + serialize(FriendlyByteBuf) void
  + deserialize(FriendlyByteBuf) RequestState
}
class RequestTag {
  + RequestTag(TagKey~Item~, ItemStack, int, int) 
  + RequestTag(TagKey~Item~, int) 
  + RequestTag(TagKey~Item~, int, int) 
  - int count
  - ItemStack result
  + hashCode() int
  + deserialize(IFactoryController, CompoundTag) RequestTag
  + serialize(IFactoryController, FriendlyByteBuf, RequestTag) void
  + matches(ItemStack) boolean
  + copyWithCount(int) IDeliverable
  + equals(Object) boolean
  + serialize(IFactoryController, RequestTag) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) RequestTag
   Set~TypeToken~?~~ superClasses
   int minimumCount
   ItemStack result
   int count
   TagKey~Item~ tag
}
class RequestUtils {
  - RequestUtils() 
  + requestChainNeedsPlayer(IToken~?~, IRequestManager) boolean
}
class Stack {
  + Stack(ItemStack, boolean) 
  + Stack(ItemStack, int, int, boolean) 
  + Stack(ItemStack, int, int) 
  + Stack(ItemStack, boolean, boolean, ItemStack, int, int, boolean) 
  + Stack(ItemStorage) 
  + Stack(ItemStack) 
  + Stack(ItemStack, boolean, boolean, ItemStack, int, int) 
  - ItemStack result
  - int count
  - boolean canBeResolvedByBuilding
  + copyWithCount(int) IDeliverable
  + matchNBT() boolean
  + canBeResolvedByBuilding() boolean
  + matchDamage() boolean
  + serialize(IFactoryController, Stack) CompoundTag
  + matches(ItemStack) boolean
  + deserialize(IFactoryController, CompoundTag) Stack
  + deserialize(IFactoryController, FriendlyByteBuf) Stack
  + hashCode() int
  + equals(Object) boolean
  + serialize(IFactoryController, FriendlyByteBuf, Stack) void
   int minimumCount
   boolean canBeResolvedByBuilding
   int count
   List~ItemStack~ requestedItems
   Set~TypeToken~?~~ superClasses
   ItemStack stack
   ItemStack result
}
class StackList {
  + StackList(List~ItemStack~, String, int) 
  + StackList(TagKey~Item~, ServerLevel, String, int, int, int) 
  + StackList(List~ItemStack~, String, int, int) 
  + StackList(List~ItemStack~, String, int, int, int) 
  + StackList(List~ItemStack~, boolean, boolean, boolean, ItemStack, String, int, int, int) 
  - int leftOver
  - ItemStack result
  - String description
  - int count
  - tagToStacks(TagKey~Item~, RegistryAccess, int) List~ItemStack~
  + deserialize(IFactoryController, CompoundTag) StackList
  + deserialize(IFactoryController, FriendlyByteBuf) StackList
  + serialize(IFactoryController, FriendlyByteBuf, StackList) void
  + serialize(IFactoryController, StackList) CompoundTag
  + copyWithCount(int) IDeliverable
  + matches(ItemStack) boolean
  + hashCode() int
  + equals(Object) boolean
   String description
   int minimumCount
   int count
   int leftOver
   List~ItemStack~ requestedItems
   Set~TypeToken~?~~ superClasses
   List~ItemStack~ stacks
   ItemStack result
}
class StandardFactoryController {
  - StandardFactoryController() 
  - StandardFactoryController INSTANCE
  + getFactoryForIO(TypeToken~INPUT~, TypeToken~OUTPUT~) IFactory~INPUT, OUTPUT~
  + getNewInstance(TypeToken~OUTPUT~, INPUT, Object[]) OUTPUT
  - getMatchingOverrideHandler(TypeToken~?~) ITypeOverrideHandler~?~?
  + serialize(FriendlyByteBuf, OUTPUT) void
  + getFactoryForSerializationId(short) IFactory~?, OUTPUT~
  + getFactoryForInput(TypeToken~INPUT~) IFactory~INPUT, ?~
  + registerNewTypeOverrideHandler(ITypeOverrideHandler~OUTPUT~) void
  + deserialize(CompoundTag) OUTPUT?
  + serialize(OUTPUT) CompoundTag
  + reset() void
  + getFactoryForOutput(TypeToken~OUTPUT~) IFactory~?, OUTPUT~
  + deserialize(FriendlyByteBuf) OUTPUT?
  + registerNewFactory(IFactory~INPUT, OUTPUT~) void
  + getNewInstance(TypeToken~OUTPUT~) OUTPUT
   StandardFactoryController INSTANCE
}
class StandardToken {
  + StandardToken() 
  + StandardToken(UUID) 
  + toString() String
  + hashCode() int
  + equals(Object) boolean
   UUID identifier
}
class StandardTokenFactory {
  + StandardTokenFactory() 
  + getNewInstance(UUID) StandardToken
   short serializationId
   TypeToken~UUID~ factoryInputType
}
class Tool {
  + Tool(EquipmentTypeEntry, Integer, Integer, ItemStack) 
  + Tool(EquipmentTypeEntry, Integer, Integer) 
  - Integer maxLevel
  - Integer minLevel
  - ItemStack result
  - EquipmentTypeEntry equipmentType
  + matches(ItemStack) boolean
  + copyWithCount(int) IDeliverable
  + equals(Object) boolean
  + deserialize(IFactoryController, FriendlyByteBuf) Tool
  + serialize(IFactoryController, FriendlyByteBuf, Tool) void
  + serialize(IFactoryController, Tool) CompoundTag
  + deserialize(IFactoryController, CompoundTag) Tool
  + hashCode() int
   int minimumCount
   int count
   Set~TypeToken~?~~ superClasses
   boolean armor
   EquipmentTypeEntry equipmentType
   Integer maxLevel
   Integer minLevel
   ItemStack result
}
class TypeTokenFactory {
  + TypeTokenFactory() 
  + serialize(IFactoryController, TypeToken~?~, FriendlyByteBuf) void
  + getNewInstance(IFactoryController, Class~?~, Object[]) TypeToken~?~
  + serialize(IFactoryController, TypeToken~?~) CompoundTag
  + deserialize(IFactoryController, CompoundTag) TypeToken~?~
  + deserialize(IFactoryController, FriendlyByteBuf) TypeToken~?~
   TypeToken~TypeToken~?~~ factoryOutputType
   short serializationId
   TypeToken~Class~?~~ factoryInputType
}
class UpdateType {
<<enumeration>>
  + UpdateType() 
  + valueOf(String) UpdateType
  + values() UpdateType[]
}
class WorkOrderType {
<<enumeration>>
  - WorkOrderType(String) 
  - String completionMessageID
  + values() WorkOrderType[]
  + valueOf(String) WorkOrderType
   String completionMessageID
}

AbstractCrafting  ..>  IRequestable 
AbstractCrafting "1" *--> "recipeToken 1" IToken~T~ 
AbstractDeliverymanRequestable  ..>  IDeliverymanRequestable 
AbstractTokenFactory~I~  ..>  ITokenFactory~T, RT~ 
AbstractTokenFactory~I~  ..>  StandardToken : «create»
Burnable  ..>  IDeliverable 
Delivery  -->  AbstractDeliverymanRequestable 
Delivery "1" *--> "start 1" ILocation 
Food  ..>  IDeliverable 
IAssignmentDataStore~K, V~  -->  IDataStore 
IBuilderWorkOrder  -->  IServerWorkOrder 
IConcreteDeliverable  -->  IDeliverable 
IDeliverable  -->  IRetryable 
IDeliverymanRequestable  -->  IRequestable 
IIdentitiesDataStore~K, V~  -->  IDataStore 
ILocationFactory~T, L~  -->  IFactory~Input, Output~ 
ILocationFactory~T, L~  ..>  ILocation 
INonExhaustiveDeliverable  -->  IDeliverable 
IPlayerRequestResolver  -->  IQueuedRequestResolver~R~ 
IProviderResolverAssignmentDataStore  -->  ITokenTokenAssignmentDataStore 
IQueuedRequestResolver~R~  -->  IRequestResolver~R~ 
IQueuedRequestResolver~R~  ..>  IRequestable 
IRequestFactory~T, R~  -->  IFactory~Input, Output~ 
IRequestFactory~T, R~  ..>  IRequestable 
IRequestFactory~T, R~  ..>  IRequest~R~ 
IRequestIdentitiesDataStore  -->  IIdentitiesDataStore~K, V~ 
IRequestResolverFactory~Resolver~  -->  IFactory~Input, Output~ 
IRequestResolverFactory~Resolver~  ..>  IRequestResolver~R~ 
IRequestResolverIdentitiesDataStore  -->  IIdentitiesDataStore~K, V~ 
IRequestResolverRequestAssignmentDataStore  -->  ITokenTokenAssignmentDataStore 
IRequestResolver~R~  -->  IRequester 
IRequestResolver~R~  ..>  IRequestable 
IRequestSystemBuildingDataStore  -->  IDataStore 
IRequestSystemCrafterJobDataStore  -->  IDataStore 
IRequestSystemDeliveryManJobDataStore  -->  IDataStore 
IRequestableTypeRequestResolverAssignmentDataStore  -->  IAssignmentDataStore~K, V~ 
IRequesterFactory~Input, Output~  -->  IFactory~Input, Output~ 
IRequesterFactory~Input, Output~  ..>  IRequester 
IRequest~R~  ..>  IRequestable 
IRetryable  -->  IRequestable 
IRetryingRequestResolver  -->  IQueuedRequestResolver~R~ 
IServerWorkOrder  -->  IWorkOrder 
ITokenFactory~T, RT~  -->  IFactory~Input, Output~ 
ITokenFactory~T, RT~  ..>  IToken~T~ 
ITokenTokenAssignmentDataStore  -->  IAssignmentDataStore~K, V~ 
IWorkOrderView  -->  IWorkOrder 
InitializedTokenFactory  -->  AbstractTokenFactory~I~ 
InitializedTokenFactory  ..>  StandardToken : «create»
IntegerFactory  ..>  IFactory~Input, Output~ 
MinimumStack  -->  Stack 
Pickup  -->  AbstractDeliverymanRequestable 
PrivateCrafting  -->  AbstractCrafting 
PublicCrafting  -->  AbstractCrafting 
RandomSeededTokenFactory  -->  AbstractTokenFactory~I~ 
RandomSeededTokenFactory  ..>  StandardToken : «create»
RequestTag  ..>  IDeliverable 
Stack  ..>  IConcreteDeliverable 
StackList  ..>  IConcreteDeliverable 
StackList  ..>  INonExhaustiveDeliverable 
StandardFactoryController  ..>  IFactoryController 
StandardFactoryController "1" *--> "serializationMappings *" IFactory~Input, Output~ 
StandardFactoryController "1" *--> "typeOverrideHandlers *" ITypeOverrideHandler~O~ 
StandardToken  ..>  IToken~T~ 
StandardTokenFactory  -->  AbstractTokenFactory~I~ 
StandardTokenFactory  ..>  StandardToken : «create»
Tool  ..>  IDeliverable 
TypeTokenFactory  ..>  IFactory~Input, Output~ 
```
