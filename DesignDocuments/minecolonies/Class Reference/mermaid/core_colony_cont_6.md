# core.colony (cont. 6)

76 classes, 80 internal relationships shown.

```mermaid
classDiagram
direction BT

class AbstractBuildingDependentRequestResolver~R~ {
  + AbstractBuildingDependentRequestResolver(ILocation, IToken~?~) 
  + canResolveRequest(IRequestManager, IRequest~R~) boolean
  + canResolveForBuilding(IRequestManager, IRequest~R~, AbstractBuilding) boolean
  + attemptResolveRequest(IRequestManager, IRequest~R~) List~IToken~?~~?
  + resolveRequest(IRequestManager, IRequest~R~) void
  + resolveForBuilding(IRequestManager, IRequest~R~, AbstractBuilding) void
  + attemptResolveForBuilding(IRequestManager, IRequest~R~, AbstractBuilding) List~IToken~?~~?
   boolean valid
}
class AbstractCraftingProductionResolver~C~ {
  + AbstractCraftingProductionResolver(ILocation, IToken~?~, JobEntry, Class~C~) 
  - JobEntry jobEntry
  + canResolveRequest(IRequestManager, IRequest~C~) boolean
  + attemptResolveRequest(IRequestManager, IRequest~C~) List~IToken~?~~?
  + resolveRequest(IRequestManager, IRequest~C~) void
  # attemptResolveForBuildingAndStack(IRequestManager, AbstractBuilding, ItemStack, int, int, IToken~?~) List~IToken~?~~?
  + getBuilding(IRequestManager, IToken~?~) Optional~IRequester~
  + attemptResolveForBuilding(IRequestManager, IRequest~C~, AbstractBuilding) List~IToken~?~~?
  # createNewRequestForStack(IRequestManager, ItemStack, int, int, boolean) IToken~?~?
  + resolveForBuilding(IRequestManager, IRequest~C~, AbstractBuilding) void
  + onRequestAssigned(IRequestManager, IRequest~C~, boolean) void
  # createRequestsForRecipe(IRequestManager, AbstractBuilding, int, int, IRecipeStorage) List~IToken~?~~?
  # onAssignedToThisResolverForBuilding(IRequestManager, IRequest~C~, boolean, AbstractBuilding) void
  # canBuildingCraftStack(IRequestManager, AbstractBuilding, ItemStack) boolean
   TypeToken~C~ requestType
   boolean valid
   JobEntry jobEntry
}
class AbstractCraftingRequestResolver {
  + AbstractCraftingRequestResolver(ILocation, IToken~?~, JobEntry, boolean) 
  - JobEntry jobEntry
  + getSuitabilityMetric(IRequestManager, IRequest~IDeliverable~) int
  + attemptResolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) List~IToken~?~~?
  + canResolveRequest(IRequestManager, IRequest~IDeliverable~) boolean
  + resolveRequest(IRequestManager, IRequest~IDeliverable~) void
  + canBuildingCraftRecipe(AbstractBuilding, IRecipeStorage) boolean
  # createRequestsForRecipe(IRequestManager, IRecipeStorage, int, int) List~IToken~?~~?
  + getBuilding(IRequestManager, IToken~?~) Optional~IRequester~
  + resolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  # createsCraftingCycle(IRequestManager, IRequest~?~, IDeliverable, IRequest~IDeliverable~?) boolean
  + attemptResolveRequest(IRequestManager, IRequest~IDeliverable~) List~IToken~?~~?
  - hasModuleForJob(AbstractBuilding, JobEntry) boolean
  # attemptResolveForBuildingAndStack(IRequestManager, AbstractBuilding, Predicate~ItemStack~, int, int) List~IToken~?~~?
  # createsCraftingCycle(IRequestManager, IRequest~?~, IDeliverable, IRequest~IDeliverable~?, int) boolean
  + canResolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) boolean
  # createNewRequestableForStack(ItemStack, int, int, IToken~?~) IRequestable
   TypeToken~IDeliverable~ requestType
   boolean valid
   JobEntry jobEntry
}
class AbstractRequest~R~ {
  # AbstractRequest(IRequester, IToken~?~, R) 
  # AbstractRequest(IRequester, IToken~?~, RequestState, R) 
  - IToken~?~? parent
  - List~IToken~?~~ children
  - RequestState state
  - IRequester requester
  - R? result
  - List~ItemStack~ deliveries
  + removeChildren(T[]) void
  + hasChildren() boolean
  + addDelivery(ItemStack) void
  + setState(IRequestManager, RequestState) void
  + overrideCurrentDeliveries(ImmutableList~ItemStack~) void
  + hashCode() int
  + addChild(T) void
  + addDelivery(List~ItemStack~) void
  + getResolverToolTip(IColonyView) List~MutableComponent~
  + hasResult() boolean
  + getRequestOfType(Class~T~) Optional~T~
  + removeChildren(Collection~T~) void
  + addChildren(T[]) void
  + canBeDelivered() boolean
  + resetDeliveries() void
  + addChildren(Collection~T~) void
  + removeChild(T) void
  + hasParent() boolean
  + equals(Object) boolean
  + childStateUpdated(IRequestManager, IToken~?~) void
   TypeToken~R~ type
   IRequester requester
   List~ItemStack~ displayStacks
   ImmutableCollection~IToken~?~~ children
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
class AbstractRequestResolver~R~ {
  + AbstractRequestResolver(ILocation, IToken~?~) 
  - ILocation location
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + getSuitabilityMetric(IRequestManager, IRequest~R~) int
   int priority
   IToken~?~ id
   ILocation location
}
class AbstractWarehouseRequestResolver {
  + AbstractWarehouseRequestResolver(ILocation, IToken~?~) 
  + isRequestChainValid(IRequestManager, IRequest~?~) boolean
  + resolveRequest(IRequestManager, IRequest~IDeliverable~) void
  + attemptResolveRequest(IRequestManager, IRequest~IDeliverable~) List~IToken~?~~?
  + getFollowupRequestForCompletion(IRequestManager, IRequest~IDeliverable~) List~IRequest~?~~?
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onAssignedRequestCancelled(IRequestManager, IRequest~IDeliverable~) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  # getWarehouseInternalCount(BuildingWareHouse, IRequest~IDeliverable~) int
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IDeliverable~) void
  + getSuitabilityMetric(IRequestManager, IRequest~IDeliverable~) int
  + canResolveRequest(IRequestManager, IRequest~IDeliverable~) boolean
   int priority
   TypeToken~IDeliverable~ requestType
   boolean valid
}
class AbstractWorkOrder {
  + AbstractWorkOrder() 
  # AbstractWorkOrder(String, String, String, WorkOrderType, BlockPos, int, boolean, int, int) 
  - BlockPos location
  - boolean isMirrored
  - BlockPos claimedBy
  - boolean requested
  - int priority
  - int amountOfResources
  - WorkOrderType workOrderType
  - int rotation
  - int currentLevel
  - String translationKey
  # IColony colony
  # Blueprint blueprint
  - String iteratorType
  - int targetLevel
  - boolean cleared
  - BuildingProgressStage stage
  - addMapping(String, Class~IWorkOrder~, Class~IWorkOrderView~) void
  + read(CompoundTag, IWorkManager) void
  + serializeViewNetworkData(FriendlyByteBuf) void
  + onRemoved(IColony) void
  + isValid(IColony) boolean
  + createFromNBT(CompoundTag, WorkManager) IServerWorkOrder?
  + canBeResolved(IColony, int) boolean
  + canBuild(IBuilding) boolean
  + setBlueprint(Blueprint, Level) void
  + onCompleted(IColony, ICitizenData) void
  + createWorkOrderView(FriendlyByteBuf) IWorkOrderView?
  + onAdded(IColony, boolean) void
  + clearBlueprint() void
  + resetChange() void
  + loadBlueprint(Level, Consumer~Blueprint~) void
  + write(CompoundTag) void
  + tooFarFromAnyBuilder(IColony, int) boolean
   Component displayName
   int rotation
   String mappingName
   int priority
   boolean isMirrored
   boolean requested
   BuildingProgressStage stage
   String structurePack
   Blueprint? blueprint
   boolean claimed
   IColony colony
   int targetLevel
   BlockPos location
   BlockPos claimedBy
   int ID
   int amountOfResources
   String iteratorType
   String structurePath
   int currentLevel
   AABB? boundingBox
   boolean dirty
   WorkOrderType workOrderType
   String translationKey
   boolean cleared
}
class AbstractWorkOrderView {
  + AbstractWorkOrderView() 
  - int targetLevel
  - BlockPos location
  - BuildingProgressStage stage
  - int priority
  - String structurePath
  - String translationKey
  # IColony colony
  - WorkOrderType workOrderType
  - int currentLevel
  - BlockPos claimedBy
  # Blueprint blueprint
  - boolean isMirrored
  - int rotation
  + canBuildIgnoringDistance(BlockPos, int) boolean
  + loadBlueprint(Level, Consumer~Blueprint~) void
  + setBlueprint(Blueprint, Level) void
  + deserialize(FriendlyByteBuf) void
  + clearBlueprint() void
   int rotation
   int priority
   boolean isMirrored
   BuildingProgressStage stage
   String structurePack
   Blueprint? blueprint
   boolean claimed
   IColony colony
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
class AbstractWrappedRequestManager {
  + AbstractWrappedRequestManager(IStandardRequestManager) 
  + assignRequest(IToken~?~) void
  + overruleRequest(IToken~?~, ItemStack?) void
  + onProviderRemovedFromColony(IRequestResolverProvider) void
  + reset() void
  + reassignRequest(IToken~?~, Collection~IToken~?~~) IToken~?~?
  + log(String) void
  + onRequesterRemovedFromColony(IRequester) void
  + createAndAssignRequest(IRequester, T) IToken~?~
  + markDirty() void
  + serializeNBT() CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) void
  + onColonyUpdate(Predicate~IRequest~?~~) void
  + deserializeNBT(CompoundTag) void
  + getResolverForToken(IToken~?~) IRequestResolver~?~
  + getResolverForRequest(IToken~?~) IRequestResolver~?~?
  + updateRequestState(IToken~?~, RequestState) void
  + getRequestForToken(IToken~?~) IRequest~?~?
  + serialize(IFactoryController, FriendlyByteBuf) void
  + createRequest(IRequester, T) IToken~?~
  + onProviderAddedToColony(IRequestResolverProvider) void
  + tick() void
   IRetryingRequestResolver retryingRequestResolver
   IDataStoreManager dataStoreManager
   IColony colony
   IFactoryController factoryController
   boolean dirty
   IPlayerRequestResolver playerResolver
}
class BuildingBasedRequester {
  + BuildingBasedRequester(ILocation, IToken~?~) 
  - ILocation location
  + deserialize(IFactoryController, FriendlyByteBuf) BuildingBasedRequester
  + getBuilding(IRequestManager, IToken~?~) Optional~IRequester~
  + serialize(IFactoryController, FriendlyByteBuf) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + serialize(IFactoryController) CompoundTag
  - updateBuilding(IColony) void
  + deserialize(IFactoryController, CompoundTag) BuildingBasedRequester
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
   IToken~?~ id
   ILocation location
}
class BuildingBasedRequesterFactory {
  + BuildingBasedRequesterFactory() 
  + getNewInstance(IFactoryController, AbstractBuilding, Object[]) BuildingBasedRequester
  + serialize(IFactoryController, BuildingBasedRequester, FriendlyByteBuf) void
  + deserialize(IFactoryController, FriendlyByteBuf) BuildingBasedRequester
  + deserialize(IFactoryController, CompoundTag) BuildingBasedRequester
  + serialize(IFactoryController, BuildingBasedRequester) CompoundTag
   TypeToken~BuildingBasedRequester~ factoryOutputType
   short serializationId
   TypeToken~AbstractBuilding~ factoryInputType
}
class BuildingRequestResolver {
  + BuildingRequestResolver(ILocation, IToken~?~) 
  + getBuilding(IRequestManager, IToken~?~) Optional~IRequester~
  + resolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + attemptResolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) List~IToken~?~~?
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IDeliverable~) void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~IDeliverable~) List~IRequest~?~~?
  + canResolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) boolean
  - getCapabilityProviders(IRequestManager, AbstractBuilding) Set~ICapabilityProvider~
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onAssignedRequestCancelled(IRequestManager, IRequest~IDeliverable~) void
   int priority
   TypeToken~IDeliverable~ requestType
}
class BuildingRequestResolverFactory {
  + BuildingRequestResolverFactory() 
  + serialize(IFactoryController, BuildingRequestResolver) CompoundTag
  + deserialize(IFactoryController, CompoundTag) BuildingRequestResolver
  + serialize(IFactoryController, BuildingRequestResolver, FriendlyByteBuf) void
  + getNewInstance(IFactoryController, ILocation, Object[]) BuildingRequestResolver
  + deserialize(IFactoryController, FriendlyByteBuf) BuildingRequestResolver
   TypeToken~BuildingRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class DeliveryRequestResolver {
  + DeliveryRequestResolver(ILocation, IToken~?~) 
  + canResolveRequest(IRequestManager, IRequest~Delivery~) boolean
   TypeToken~Delivery~ requestType
}
class DeliveryRequestResolverFactory {
  + DeliveryRequestResolverFactory() 
  + deserialize(IFactoryController, FriendlyByteBuf) DeliveryRequestResolver
  + deserialize(IFactoryController, CompoundTag) DeliveryRequestResolver
  + getNewInstance(IFactoryController, ILocation, Object[]) DeliveryRequestResolver
  + serialize(IFactoryController, DeliveryRequestResolver) CompoundTag
  + serialize(IFactoryController, DeliveryRequestResolver, FriendlyByteBuf) void
   TypeToken~DeliveryRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class DeliverymenRequestResolver~R~ {
  + DeliverymenRequestResolver(ILocation, IToken~?~) 
  + canResolveRequest(IRequestManager, IRequest~R~) boolean
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onAssignedRequestCancelled(IRequestManager, IRequest~R~) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + attemptResolveRequest(IRequestManager, IRequest~R~) List~IToken~?~~?
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~R~) void
  + getSuitabilityMetric(IRequestManager, IRequest~R~) int
  + resolveRequest(IRequestManager, IRequest~R~) void
  + hasCouriers(IRequestManager) boolean
  + getFollowupRequestForCompletion(IRequestManager, IRequest~R~) List~IRequest~?~~?
   boolean valid
}
class EntityLocation {
  + EntityLocation(UUID) 
  + serialize(FriendlyByteBuf, EntityLocation) void
  + deserialize(FriendlyByteBuf) EntityLocation
  - checkEntity() void
  + isReachableFromLocation(ILocation) boolean
   BlockPos inDimensionLocation
   Player playerEntity
   ResourceKey~Level~ dimension
}
class IBuildingBasedRequester {
<<Interface>>
  + getBuilding(IRequestManager, IToken~?~) Optional~IRequester~
}
class IStandardRequestManager {
<<Interface>>
   IRequestResolverIdentitiesDataStore requestResolverIdentitiesDataStore
   IResolverHandler resolverHandler
   IRequestResolverRequestAssignmentDataStore requestResolverRequestAssignmentDataStore
   ITokenHandler tokenHandler
   IProviderResolverAssignmentDataStore providerResolverAssignmentDataStore
   IRequestHandler requestHandler
   int currentVersion
   IUpdateHandler updateHandler
   IProviderHandler providerHandler
   IRequestableTypeRequestResolverAssignmentDataStore requestableTypeRequestResolverAssignmentDataStore
   IRequestIdentitiesDataStore requestIdentitiesDataStore
}
class IUpdateStep {
<<Interface>>
  + update(UpdateType, IStandardRequestManager) void
  + update(IStandardRequestManager) void
  + updatesToVersion() int
}
class InitialUpdate {
  + InitialUpdate() 
  + updatesToVersion() int
  + update(UpdateType, IStandardRequestManager) void
}
class PickupRequestResolver {
  + PickupRequestResolver(ILocation, IToken~?~) 
  + getSuitabilityMetric(IRequestManager, IRequest~Pickup~) int
  + canResolveRequest(IRequestManager, IRequest~Pickup~) boolean
   TypeToken~Pickup~ requestType
}
class PickupRequestResolverFactory {
  + PickupRequestResolverFactory() 
  + serialize(IFactoryController, PickupRequestResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PickupRequestResolver
  + getNewInstance(IFactoryController, ILocation, Object[]) PickupRequestResolver
  + serialize(IFactoryController, PickupRequestResolver, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) PickupRequestResolver
   TypeToken~PickupRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class PrivateWorkerCraftingProductionResolver {
  + PrivateWorkerCraftingProductionResolver(ILocation, IToken~?~, JobEntry) 
  + resolveForBuilding(IRequestManager, IRequest~PrivateCrafting~, AbstractBuilding) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onAssignedRequestCancelled(IRequestManager, IRequest~PrivateCrafting~) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~PrivateCrafting~) List~IRequest~?~~?
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~PrivateCrafting~) void
}
class PrivateWorkerCraftingProductionResolverFactory {
  + PrivateWorkerCraftingProductionResolverFactory() 
  + serialize(IFactoryController, PrivateWorkerCraftingProductionResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PrivateWorkerCraftingProductionResolver
  + getNewInstance(IFactoryController, ILocation, Object[]) PrivateWorkerCraftingProductionResolver
  + deserialize(IFactoryController, CompoundTag) PrivateWorkerCraftingProductionResolver
  + serialize(IFactoryController, PrivateWorkerCraftingProductionResolver, FriendlyByteBuf) void
   TypeToken~PrivateWorkerCraftingProductionResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class PrivateWorkerCraftingRequestResolver {
  + PrivateWorkerCraftingRequestResolver(ILocation, IToken~?~, JobEntry) 
  # createNewRequestableForStack(ItemStack, int, int, IToken~?~) IRequestable
  + canBuildingCraftRecipe(AbstractBuilding, IRecipeStorage) boolean
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IDeliverable~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~IDeliverable~) List~IRequest~?~~?
  + onAssignedRequestCancelled(IRequestManager, IRequest~IDeliverable~) void
   int priority
}
class PrivateWorkerCraftingRequestResolverFactory {
  + PrivateWorkerCraftingRequestResolverFactory() 
  + serialize(IFactoryController, PrivateWorkerCraftingRequestResolver, FriendlyByteBuf) void
  + getNewInstance(IFactoryController, ILocation, Object[]) PrivateWorkerCraftingRequestResolver
  + serialize(IFactoryController, PrivateWorkerCraftingRequestResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PrivateWorkerCraftingRequestResolver
  + deserialize(IFactoryController, CompoundTag) PrivateWorkerCraftingRequestResolver
   TypeToken~PrivateWorkerCraftingRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class ProviderHandler {
  + ProviderHandler(IStandardRequestManager) 
  - IStandardRequestManager manager
  + getRegisteredResolvers(IToken~?~) Collection~IToken~?~~
  + removeProvider(IRequestResolverProvider) void
  + registerProvider(IRequestResolverProvider) void
  + getRegisteredResolvers(IRequestResolverProvider) Collection~IToken~?~~
  ~ removeProviderInternal(IToken~?~) void
  + removeProvider(IToken~?~) void
  ~ processResolversForRemoval(Collection~IToken~?~~) void
   IRequestManager manager
}
class PublicWorkerCraftingProductionResolver {
  + PublicWorkerCraftingProductionResolver(ILocation, IToken~?~, JobEntry) 
  - removeRequestFromTaskList(IRequest~PublicCrafting~, IColony) void
  + resolveForBuilding(IRequestManager, IRequest~PublicCrafting~, AbstractBuilding) void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~PublicCrafting~) List~IRequest~?~~?
  # canBuildingCraftStack(IRequestManager, AbstractBuilding, ItemStack) boolean
  + onAssignedRequestCancelled(IRequestManager, IRequest~PublicCrafting~) void
  # onAssignedToThisResolverForBuilding(IRequestManager, IRequest~PublicCrafting~, boolean, AbstractBuilding) void
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~PublicCrafting~) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
}
class PublicWorkerCraftingProductionResolverFactory {
  + PublicWorkerCraftingProductionResolverFactory() 
  + serialize(IFactoryController, PublicWorkerCraftingProductionResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) PublicWorkerCraftingProductionResolver
  + serialize(IFactoryController, PublicWorkerCraftingProductionResolver, FriendlyByteBuf) void
  + getNewInstance(IFactoryController, ILocation, Object[]) PublicWorkerCraftingProductionResolver
  + deserialize(IFactoryController, CompoundTag) PublicWorkerCraftingProductionResolver
   TypeToken~PublicWorkerCraftingProductionResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class PublicWorkerCraftingRequestResolver {
  + PublicWorkerCraftingRequestResolver(ILocation, IToken~?~, JobEntry) 
  + getFollowupRequestForCompletion(IRequestManager, IRequest~IDeliverable~) List~IRequest~?~~?
  # createNewRequestableForStack(ItemStack, int, int, IToken~?~) IRequestable
  + canBuildingCraftRecipe(AbstractBuilding, IRecipeStorage) boolean
  + onAssignedRequestCancelled(IRequestManager, IRequest~IDeliverable~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IDeliverable~) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
   int priority
}
class PublicWorkerCraftingRequestResolverFactory {
  + PublicWorkerCraftingRequestResolverFactory() 
  + deserialize(IFactoryController, FriendlyByteBuf) PublicWorkerCraftingRequestResolver
  + deserialize(IFactoryController, CompoundTag) PublicWorkerCraftingRequestResolver
  + serialize(IFactoryController, PublicWorkerCraftingRequestResolver, FriendlyByteBuf) void
  + serialize(IFactoryController, PublicWorkerCraftingRequestResolver) CompoundTag
  + getNewInstance(IFactoryController, ILocation, Object[]) PublicWorkerCraftingRequestResolver
   TypeToken~PublicWorkerCraftingRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class RequestHandler {
  + RequestHandler(IStandardRequestManager) 
  - IStandardRequestManager manager
  + assignRequest(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + cleanRequestData(IToken~?~) void
  + removeRequester(IRequester) void
  + resolveRequest(IRequest~?~) void
  + createRequest(IRequester, Request) IRequest~Request~
  + registerRequest(IRequest~?~) void
  + reassignRequest(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + onRequestCompleted(IToken~?~) void
  + assignRequestDefault(IRequest~?~, Collection~IToken~?~~) IToken~?~
  + processDirectCancellationAndNotifyRequesterOf(IRequest~?~) void
  + getRequestOrNull(IToken~?~) IRequest~?~
  + onRequestResolved(IToken~?~) void
  + isAssigned(IToken~?~) boolean
  + getRequestsMadeByRequester(IRequester) Collection~IRequest~?~~
  + onRequestCancelledDirectly(IToken~?~) void
  + onRequestCancelled(IToken~?~) void
  - resolve(IRequest~?~, IRequestResolver, Collection~IToken~?~~, List~IToken~?~~?) IToken~?~
  + onChildRequestCancelled(IToken~?~) void
  + processDirectCancellationOf(IRequest~?~) void
  + getRequest(IToken~?~) IRequest~?~
  + onRequestOverruled(IToken~?~) void
  + assignRequest(IRequest~?~) void
   IRequestManager manager
}
class RequestSystemInitializer {
  + RequestSystemInitializer() 
  + onPostInit() void
}
class ResetRSToRemoveAssistantCookResolver {
  + ResetRSToRemoveAssistantCookResolver() 
  + updatesToVersion() int
  + update(UpdateType, IStandardRequestManager) void
}
class ResetRSToStoreJobInResolvers {
  + ResetRSToStoreJobInResolvers() 
  + update(UpdateType, IStandardRequestManager) void
  + updatesToVersion() int
}
class ResetRSToUpdateRestaurantResolver {
  + ResetRSToUpdateRestaurantResolver() 
  + update(UpdateType, IStandardRequestManager) void
  + updatesToVersion() int
}
class ResolverHandler {
  + ResolverHandler(IStandardRequestManager) 
  - IStandardRequestManager manager
  + registerResolvers(IRequestResolver~?~[]) Collection~IToken~?~~
  + removeResolvers(IRequestResolver~?~[]) void
  ~ removeResolverWithoutAssignedRequests(IToken~?~) void
  + removeResolvers(Iterable~IRequestResolver~?~~) void
  + removeResolver(IToken~?~) void
  + addRequestToResolver(IRequestResolver~?~, IRequest~?~) void
  + processResolverForRemoval(Collection~IToken~?~~, IToken~?~) void
  + isBeingRemoved(IToken~?~) boolean
  + removeResolverInternal(IRequestResolver~?~) void
  + getResolverForRequest(IRequest~?~) IRequestResolver~IRequestable~
  + getResolverForRequest(IToken~?~) IRequestResolver~IRequestable~
  + onColonyUpdate(Predicate~IRequest~?~~) void
  + removeRequestFromResolver(IRequestResolver~?~, IRequest~?~) void
  + removeResolver(IRequestResolver~?~) void
  + getResolver(IToken~?~) IRequestResolver~IRequestable~
  + registerResolvers(Collection~IRequestResolver~?~~) Collection~IToken~?~~
  + getRequestsAssignedToResolver(IRequestResolver~?~) Collection~IToken~?~~
  + registerResolver(IRequestResolver~IRequestable~) IToken~?~
  ~ removeResolverWithAssignedRequests(Collection~IToken~?~~, IToken~?~) void
   IRequestManager manager
}
class StandardDataStoreManager {
  + StandardDataStoreManager() 
  + StandardDataStoreManager(Map~IToken~?~, IDataStore~) 
  + get(IToken~?~, Supplier~T~) T
  + get(IToken~?~, TypeToken~T~) T
  + removeAll() void
  + remove(IToken~?~) void
}
class StandardFactoryControllerInitializer {
  - StandardFactoryControllerInitializer() 
  + onPreInit() void
}
class StandardPlayerRequestResolver {
  + StandardPlayerRequestResolver(ILocation, IToken~?~) 
  - ILocation location
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IRequestable~) void
  + onSystemReset() void
  + onColonyUpdate(IRequestManager, Predicate~IRequest~?~~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onAssignedRequestCancelled(IRequestManager, IRequest~IRequestable~) void
  + resolveRequest(IRequestManager, IRequest~?~) void
  + canResolveRequest(IRequestManager, IRequest~?~) boolean
  + attemptResolveRequest(IRequestManager, IRequest~?~) List~IToken~?~~?
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
   TypeToken~IRequestable~ requestType
   ImmutableList~IToken~?~~ allAssignedRequests
   IToken~?~ id
   boolean valid
   ILocation location
   int priority
}
class StandardPlayerRequestResolverFactory {
  + StandardPlayerRequestResolverFactory() 
  + deserialize(IFactoryController, CompoundTag) StandardPlayerRequestResolver
  + deserialize(IFactoryController, FriendlyByteBuf) StandardPlayerRequestResolver
  + getNewInstance(IFactoryController, IRequestManager, Object[]) StandardPlayerRequestResolver
  + serialize(IFactoryController, StandardPlayerRequestResolver, FriendlyByteBuf) void
  + serialize(IFactoryController, StandardPlayerRequestResolver) CompoundTag
   TypeToken~StandardPlayerRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~IRequestManager~ factoryInputType
}
class StandardProviderRequestResolverAssignmentDataStore {
  + StandardProviderRequestResolverAssignmentDataStore() 
  + StandardProviderRequestResolverAssignmentDataStore(IToken~?~, Map~IToken~?~, Collection~IToken~?~~~) 
  - Map~IToken~?~, Collection~IToken~?~~~ assignments
  - IToken~?~ id
   Map~IToken~?~, Collection~IToken~?~~~ assignments
   IToken~?~ id
}
class StandardRecipeManager {
  + StandardRecipeManager() 
  - BiMap~IToken~?~, IRecipeStorage~ recipes
  + getRecipeId(IRecipeStorage) IToken~?~
  + checkOrAddRecipe(IRecipeStorage) IToken~?~
  + registerUse(IToken~?~) void
  + read(CompoundTag) void
  + getRecipe(IToken~?~) IRecipeStorage
  + write(CompoundTag) void
  + addRecipe(IRecipeStorage) IToken~?~
  + reset() void
   ImmutableMap~IToken~?~, IRecipeStorage~ recipes
}
class StandardRequestFactories {
  - StandardRequestFactories() 
  + deserializeFromFriendlyByteBuf(IFactoryController, FriendlyByteBuf, IFriendlyByteBufToObjectReader~T~, IObjectConstructor~T, R~) R
  + serializeToFriendlyByteBuf(IFactoryController, IRequest~T~, FriendlyByteBuf, IObjectToPackBufferWriter~T~) void
  + deserializeFromNBT(IFactoryController, CompoundTag, INBTToObjectConverter~T~, IObjectConstructor~T, R~) R
  + serializeToNBT(IFactoryController, IRequest~T~, IObjectToNBTConverter~T~) CompoundTag
}
class StandardRequestIdentitiesDataStore {
  + StandardRequestIdentitiesDataStore(IToken~?~, BiMap~IToken~?~, IRequest~?~~) 
  + StandardRequestIdentitiesDataStore() 
  - IToken~?~ id
   BiMap~IToken~?~, IRequest~?~~ identities
   IToken~?~ id
}
class StandardRequestManager {
  + StandardRequestManager(IColony) 
  - IRequestHandler requestHandler
  - IResolverHandler resolverHandler
  - IProviderHandler providerHandler
  - IDataStoreManager dataStoreManager
  - boolean dirty
  - ITokenHandler tokenHandler
  - IUpdateHandler updateHandler
  - IColony colony
  + markDirty() void
  + log(String) void
  - reset(UpdateType) void
  + onProviderAddedToColony(IRequestResolverProvider) void
  - setup() void
  + createRequest(IRequester, T) IToken~?~
  + createAndAssignRequest(IRequester, T) IToken~?~
  + getResolverForRequest(IToken~?~) IRequestResolver~?~?
  + onProviderRemovedFromColony(IRequestResolverProvider) void
  + onRequesterRemovedFromColony(IRequester) void
  - executeDeserializationStepOrMarkForUpdate(CompoundTag, String, BiFunction~CompoundTag, String, T~, Consumer~T~) void
  + reassignRequest(IToken~?~, Collection~IToken~?~~) IToken~?~?
  + serializeNBT() CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) void
  + serialize(IFactoryController, FriendlyByteBuf) void
  + getRequestForToken(IToken~?~) IRequest~?~?
  + getResolverForToken(IToken~?~) IRequestResolver~?~
  - markForUpdate() void
  + tick() void
  + deserializeNBT(CompoundTag) void
  + overruleRequest(IToken~?~, ItemStack?) void
  - updateIfRequired() void
  - registerDataStore(TypeToken~IDataStore~) IToken~?~
  + onColonyUpdate(Predicate~IRequest~?~~) void
  + updateRequestState(IToken~?~, RequestState) void
  + assignRequest(IToken~?~) void
  + reset() void
   int currentVersion
   IRetryingRequestResolver retryingRequestResolver
   IColony colony
   IUpdateHandler updateHandler
   IProviderHandler providerHandler
   IDataStoreManager dataStoreManager
   IRequestResolverIdentitiesDataStore requestResolverIdentitiesDataStore
   IFactoryController factoryController
   IResolverHandler resolverHandler
   IRequestResolverRequestAssignmentDataStore requestResolverRequestAssignmentDataStore
   ITokenHandler tokenHandler
   IProviderResolverAssignmentDataStore providerResolverAssignmentDataStore
   IRequestHandler requestHandler
   boolean dirty
   IPlayerRequestResolver playerResolver
   IRequestableTypeRequestResolverAssignmentDataStore requestableTypeRequestResolverAssignmentDataStore
   IRequestIdentitiesDataStore requestIdentitiesDataStore
}
class StandardRequestResolverRequestAssignmentDataStore {
  + StandardRequestResolverRequestAssignmentDataStore(IToken~?~, Map~IToken~?~, Collection~IToken~?~~~) 
  + StandardRequestResolverRequestAssignmentDataStore() 
  - Map~IToken~?~, Collection~IToken~?~~~ assignments
  - IToken~?~ id
   Map~IToken~?~, Collection~IToken~?~~~ assignments
   IToken~?~ id
}
class StandardRequestResolversIdentitiesDataStore {
  + StandardRequestResolversIdentitiesDataStore() 
  + StandardRequestResolversIdentitiesDataStore(IToken~?~, BiMap~IToken~?~, IRequestResolver~?~~) 
  - IToken~?~ id
   BiMap~IToken~?~, IRequestResolver~?~~ identities
   IToken~?~ id
}
class StandardRequestSystemBuildingDataStore {
  + StandardRequestSystemBuildingDataStore() 
  + StandardRequestSystemBuildingDataStore(IToken~?~, Map~TypeToken~?~, Collection~IToken~?~~~, Int2ObjectMap~Collection~IToken~?~~~, Int2ObjectMap~Collection~IToken~?~~~, Object2IntMap~IToken~?~~) 
  - Map~TypeToken~?~, Collection~IToken~?~~~ openRequestsByRequestableType
  - Int2ObjectMap~Collection~IToken~?~~~ completedRequestsByCitizen
  - IToken~?~ id
  - Int2ObjectMap~Collection~IToken~?~~~ openRequestsByCitizen
  + moveToSyncCitizen(ICitizenData, IRequest~?~) void
   Map~Integer, Collection~IToken~?~~~ openRequestsByCitizen
   Map~Integer, Collection~IToken~?~~~ completedRequestsByCitizen
   Map~IToken~?~, Integer~ citizensByRequest
   Map~TypeToken~?~, Collection~IToken~?~~~ openRequestsByRequestableType
   IToken~?~ id
}
class StandardRequestSystemCrafterJobDataStore {
  + StandardRequestSystemCrafterJobDataStore() 
  + StandardRequestSystemCrafterJobDataStore(IToken~?~, LinkedList~IToken~?~~, List~IToken~?~~) 
  - IToken~?~ id
  - LinkedList~IToken~?~~ queue
   List~IToken~?~~ assignedTasks
   IToken~?~ id
   LinkedList~IToken~?~~ queue
}
class StandardRequestSystemDeliveryManJobDataStore {
  + StandardRequestSystemDeliveryManJobDataStore(IToken~?~, LinkedList~IToken~?~~, Set~IToken~?~~) 
  + StandardRequestSystemDeliveryManJobDataStore() 
  - IToken~?~ id
  - Set~IToken~?~~ ongoingDeliveries
  - LinkedList~IToken~?~~ queue
   Set~IToken~?~~ ongoingDeliveries
   IToken~?~ id
   LinkedList~IToken~?~~ queue
}
class StandardRequestableTypeRequestResolverAssignmentDataStore {
  + StandardRequestableTypeRequestResolverAssignmentDataStore(IToken~?~, Map~TypeToken~?~, Collection~IToken~?~~~) 
  + StandardRequestableTypeRequestResolverAssignmentDataStore() 
  - IToken~?~ id
  - Map~TypeToken~?~, Collection~IToken~?~~~ assignments
   Map~TypeToken~?~, Collection~IToken~?~~~ assignments
   IToken~?~ id
}
class StandardRequests {
  - StandardRequests() 
  - getPosInList(IColonyView, IBuildingView, IToken~?~) int
}
class StandardRetryingRequestResolver {
  + StandardRetryingRequestResolver(IToken~?~, ILocation) 
  + StandardRetryingRequestResolver(IFactoryController, IRequestManager) 
  - HashMap~IToken~?~, Integer~ delays
  - HashMap~IToken~?~, Integer~ assignedRequests
  - ILocation location
  - IToken~?~ current
  - IToken~?~ id
  + tick() void
  + onRequestedRequestCancelled(IRequestManager, IRequest~?~) void
  + onColonyUpdate(IRequestManager, Predicate~IRequest~?~~) void
  + canResolveRequest(IRequestManager, IRequest~IRetryable~) boolean
  + updateManager(IRequestManager) void
  + resolveRequest(IRequestManager, IRequest~IRetryable~) void
  + onRequestedRequestComplete(IRequestManager, IRequest~?~) void
  + onSystemReset() void
  + getFollowupRequestForCompletion(IRequestManager, IRequest~IRetryable~) List~IRequest~?~~?
  + attemptResolveRequest(IRequestManager, IRequest~IRetryable~) List~IToken~?~~?
  + onAssignedRequestCancelled(IRequestManager, IRequest~IRetryable~) void
  + getRequesterDisplayName(IRequestManager, IRequest~?~) MutableComponent
  + onAssignedRequestBeingCancelled(IRequestManager, IRequest~IRetryable~) void
  + updateData(Map~IToken~?~, Integer~, Map~IToken~?~, Integer~) void
   IToken~?~? currentlyBeingReassignedRequest
   int priority
   Map~IToken~?~, Integer~ delays
   IToken~?~? current
   TypeToken~IRetryable~ requestType
   int currentReassignmentAttempt
   int maximalDelayBetweenRetriesInTicks
   ImmutableList~IToken~?~~ allAssignedRequests
   IToken~?~ id
   Map~IToken~?~, Integer~ assignedRequests
   boolean valid
   ILocation location
   int maximalTries
}
class StandardRetryingRequestResolverFactory {
  + StandardRetryingRequestResolverFactory() 
  + serialize(IFactoryController, StandardRetryingRequestResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) StandardRetryingRequestResolver
  + serialize(IFactoryController, StandardRetryingRequestResolver, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) StandardRetryingRequestResolver
  + getNewInstance(IFactoryController, IRequestManager, Object[]) StandardRetryingRequestResolver
   TypeToken~StandardRetryingRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~IRequestManager~ factoryInputType
}
class StaticLocation {
  + StaticLocation(BlockPos, ResourceKey~Level~) 
  - ResourceKey~Level~ dimension
  + hashCode() int
  + serialize(FriendlyByteBuf, StaticLocation) void
  + isReachableFromLocation(ILocation) boolean
  + deserialize(FriendlyByteBuf) StaticLocation
  + equals(Object) boolean
  + toString() String
   BlockPos inDimensionLocation
   ResourceKey~Level~ dimension
}
class StationRequestResolver {
  + StationRequestResolver(ILocation, IToken~?~) 
  + canResolveForBuilding(IRequestManager, IRequest~IDeliverable~, AbstractBuilding) boolean
  + canResolveRequest(IRequestManager, IRequest~IDeliverable~) boolean
}
class StationRequestResolverFactory {
  + StationRequestResolverFactory() 
  + deserialize(IFactoryController, CompoundTag) StationRequestResolver
  + serialize(IFactoryController, StationRequestResolver) CompoundTag
  + serialize(IFactoryController, StationRequestResolver, FriendlyByteBuf) void
  + getNewInstance(IFactoryController, ILocation, Object[]) StationRequestResolver
  + deserialize(IFactoryController, FriendlyByteBuf) StationRequestResolver
   TypeToken~StationRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class TokenHandler {
  + TokenHandler(IStandardRequestManager) 
  - IStandardRequestManager manager
  + generateNewToken() IToken~?~
   IRequestManager manager
}
class UpdateHandler {
  + UpdateHandler(IStandardRequestManager) 
  - IStandardRequestManager manager
  + handleUpdate(UpdateType) void
   int currentVersion
   IRequestManager manager
}
class WarehouseConcreteRequestResolver {
  + WarehouseConcreteRequestResolver(ILocation, IToken~?~) 
  # getWarehouseInternalCount(BuildingWareHouse, IRequest~IDeliverable~) int
   boolean valid
}
class WarehouseConcreteRequestResolverFactory {
  + WarehouseConcreteRequestResolverFactory() 
  + getNewInstance(IFactoryController, ILocation, Object[]) WarehouseConcreteRequestResolver
  + deserialize(IFactoryController, FriendlyByteBuf) WarehouseConcreteRequestResolver
  + serialize(IFactoryController, WarehouseConcreteRequestResolver, FriendlyByteBuf) void
  + serialize(IFactoryController, WarehouseConcreteRequestResolver) CompoundTag
  + deserialize(IFactoryController, CompoundTag) WarehouseConcreteRequestResolver
   TypeToken~WarehouseConcreteRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class WarehouseRequestResolver {
  + WarehouseRequestResolver(ILocation, IToken~?~) 
  # getWarehouseInternalCount(BuildingWareHouse, IRequest~IDeliverable~) int
}
class WarehouseRequestResolverFactory {
  + WarehouseRequestResolverFactory() 
  + serialize(IFactoryController, WarehouseRequestResolver) CompoundTag
  + deserialize(IFactoryController, FriendlyByteBuf) WarehouseRequestResolver
  + getNewInstance(IFactoryController, ILocation, Object[]) WarehouseRequestResolver
  + serialize(IFactoryController, WarehouseRequestResolver, FriendlyByteBuf) void
  + deserialize(IFactoryController, CompoundTag) WarehouseRequestResolver
   TypeToken~WarehouseRequestResolver~ factoryOutputType
   short serializationId
   TypeToken~ILocation~ factoryInputType
}
class WorkManager {
  + WorkManager(Colony) 
  - Map~Integer, IServerWorkOrder~ workOrders
  - Colony colony
  - boolean dirty
  + getUnassignedWorkOrder(Class~W~) W?
  + read(CompoundTag) void
  + write(CompoundTag) void
  - isWorkOrderWithinColony(IWorkOrder) boolean
  + removeWorkOrder(IServerWorkOrder?) void
  + addWorkOrder(IServerWorkOrder, boolean) void
  + getOrderedList(Class~W~, BlockPos) List~W~
  + getOrderedList(Predicate~IServerWorkOrder~, BlockPos) List~IServerWorkOrder~
  + onColonyTick(IColony) void
  + getWorkOrdersOfType(Class~W~) List~W~
  - tryAssignWorkOrder(IServerWorkOrder, Predicate~IBuilding~) void
  + removeWorkOrder(int) void
  + clearWorkForCitizen(ICitizenData) void
  + getWorkOrder(int, Class~W~) W?
  + getWorkOrder(int) IServerWorkOrder
   boolean dirty
   IColony colony
   Map~Integer, IServerWorkOrder~ workOrders
}
class WorkOrderBuilding {
  + WorkOrderBuilding() 
  - WorkOrderBuilding(String, String, String, WorkOrderType, BlockPos, int, boolean, int, int) 
  - String parentTranslationKey
  - String customName
  - String customParentName
  + canBuildIgnoringDistance(IBuilding, BlockPos, int) boolean
  + read(CompoundTag, IWorkManager) void
  + onCompleted(IColony, ICitizenData) void
  + isValid(IColony) boolean
  + create(WorkOrderType, IBuilding) WorkOrderBuilding
  + serializeViewNetworkData(FriendlyByteBuf) void
  + onAdded(IColony, boolean) void
  + canBuild(IBuilding) boolean
  + onRemoved(IColony) void
  + tooFarFromAnyBuilder(IColony, int) boolean
  + write(CompoundTag) void
   Component displayName
   String parentTranslationKey
   String customName
   String customParentName
}
class WorkOrderBuildingView {
  + WorkOrderBuildingView() 
  - getOrderTypePrefix(Component) Component
  + shouldShowIn(IBuildingView) boolean
  + deserialize(FriendlyByteBuf) void
   Component displayName
}
class WorkOrderDecoration {
  - WorkOrderDecoration(String, String, String, WorkOrderType, BlockPos, int, boolean, int, int) 
  + WorkOrderDecoration() 
  + isValid(IColony) boolean
  + onRemoved(IColony) void
  + canBuildIgnoringDistance(IBuilding, BlockPos, int) boolean
  + canBuild(IBuilding) boolean
  + create(WorkOrderType, String, String, String, BlockPos, int, boolean, int) WorkOrderDecoration
  + onAdded(IColony, boolean) void
}
class WorkOrderDecorationView {
  + WorkOrderDecorationView() 
  - getOrderTypePrefix(Component) Component
  + shouldShowIn(IBuildingView) boolean
   Component displayName
}
class WorkOrderMiner {
  + WorkOrderMiner() 
  + WorkOrderMiner(String, String, String, int, BlockPos, boolean, BlockPos) 
  - BlockPos minerBuilding
  + loadBlueprint(Level, Consumer~Blueprint~) void
  + isValid(IColony) boolean
  + write(CompoundTag) void
  + canBuild(IBuilding) boolean
  + canBuildIgnoringDistance(IBuilding, BlockPos, int) boolean
  + read(CompoundTag, IWorkManager) void
   BlockPos minerBuilding
}
class WorkOrderMinerView {
  + WorkOrderMinerView() 
  + shouldShowIn(IBuildingView) boolean
   Component displayName
}
class WorkOrderPlantationField {
  + WorkOrderPlantationField() 
  - WorkOrderPlantationField(String, String, String, WorkOrderType, BlockPos, int, boolean, int, int) 
  + create(WorkOrderType, String, String, String, BlockPos, int, boolean, int) WorkOrderPlantationField
  + canBuildIgnoringDistance(IBuilding, BlockPos, int) boolean
  + canBuild(IBuilding) boolean
  + isValid(IColony) boolean
  + onAdded(IColony, boolean) void
  + onRemoved(IColony) void
}
class WorkOrderPlantationFieldView {
  + WorkOrderPlantationFieldView() 
  + shouldShowIn(IBuildingView) boolean
  - getOrderTypePrefix(Component) Component
   Component displayName
}
class WrappedBlacklistAssignmentRequestManager {
  + WrappedBlacklistAssignmentRequestManager(IStandardRequestManager, Collection~IToken~?~~) 
  + assignRequest(IToken~?~) void
}
class WrappedStaticStateRequestManager {
  + WrappedStaticStateRequestManager(IStandardRequestManager) 
  + updateRequestState(IToken~?~, RequestState) void
}

AbstractBuildingDependentRequestResolver~R~  -->  AbstractRequestResolver~R~ 
AbstractBuildingDependentRequestResolver~R~  ..>  IBuildingBasedRequester 
AbstractCraftingProductionResolver~C~  -->  AbstractRequestResolver~R~ 
AbstractCraftingProductionResolver~C~  ..>  IBuildingBasedRequester 
AbstractCraftingRequestResolver  -->  AbstractRequestResolver~R~ 
AbstractCraftingRequestResolver  ..>  IBuildingBasedRequester 
AbstractWarehouseRequestResolver  -->  AbstractRequestResolver~R~ 
AbstractWrappedRequestManager "1" *--> "wrappedManager 1" IStandardRequestManager 
BuildingBasedRequester  ..>  IBuildingBasedRequester 
BuildingBasedRequesterFactory  ..>  BuildingBasedRequester : «create»
BuildingRequestResolver  -->  AbstractBuildingDependentRequestResolver~R~ 
BuildingRequestResolverFactory  ..>  BuildingRequestResolver : «create»
DeliveryRequestResolver  -->  DeliverymenRequestResolver~R~ 
DeliveryRequestResolverFactory  ..>  DeliveryRequestResolver : «create»
DeliverymenRequestResolver~R~  -->  AbstractRequestResolver~R~ 
InitialUpdate  ..>  IUpdateStep 
PickupRequestResolver  -->  DeliverymenRequestResolver~R~ 
PickupRequestResolverFactory  ..>  PickupRequestResolver : «create»
PrivateWorkerCraftingProductionResolver  -->  AbstractCraftingProductionResolver~C~ 
PrivateWorkerCraftingProductionResolverFactory  ..>  PrivateWorkerCraftingProductionResolver : «create»
PrivateWorkerCraftingRequestResolver  -->  AbstractCraftingRequestResolver 
PrivateWorkerCraftingRequestResolverFactory  ..>  PrivateWorkerCraftingRequestResolver : «create»
ProviderHandler "1" *--> "manager 1" IStandardRequestManager 
PublicWorkerCraftingProductionResolver  -->  AbstractCraftingProductionResolver~C~ 
PublicWorkerCraftingProductionResolverFactory  ..>  PublicWorkerCraftingProductionResolver : «create»
PublicWorkerCraftingRequestResolver  -->  AbstractCraftingRequestResolver 
PublicWorkerCraftingRequestResolverFactory  ..>  PublicWorkerCraftingRequestResolver : «create»
RequestHandler  ..>  WrappedBlacklistAssignmentRequestManager : «create»
RequestHandler  ..>  WrappedStaticStateRequestManager : «create»
RequestHandler "1" *--> "manager 1" IStandardRequestManager 
ResetRSToRemoveAssistantCookResolver  ..>  IUpdateStep 
ResetRSToStoreJobInResolvers  ..>  IUpdateStep 
ResetRSToUpdateRestaurantResolver  ..>  IUpdateStep 
ResolverHandler  ..>  WrappedStaticStateRequestManager : «create»
ResolverHandler "1" *--> "manager 1" IStandardRequestManager 
StandardFactoryControllerInitializer  ..>  BuildingBasedRequesterFactory : «create»
StandardFactoryControllerInitializer  ..>  BuildingRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  DeliveryRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  PickupRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  PrivateWorkerCraftingProductionResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  PrivateWorkerCraftingRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  PublicWorkerCraftingProductionResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  PublicWorkerCraftingRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  StandardPlayerRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  StandardRetryingRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  StationRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  WarehouseConcreteRequestResolverFactory : «create»
StandardFactoryControllerInitializer  ..>  WarehouseRequestResolverFactory : «create»
StandardPlayerRequestResolverFactory  ..>  StandardPlayerRequestResolver : «create»
StandardRequestManager  ..>  IStandardRequestManager 
StandardRequestManager  ..>  ProviderHandler : «create»
StandardRequestManager  ..>  RequestHandler : «create»
StandardRequestManager  ..>  ResolverHandler : «create»
StandardRequestManager  ..>  TokenHandler : «create»
StandardRequestManager  ..>  UpdateHandler : «create»
StandardRequestManager  ..>  WrappedStaticStateRequestManager : «create»
StandardRetryingRequestResolverFactory  ..>  StandardRetryingRequestResolver : «create»
StationRequestResolver  -->  BuildingRequestResolver 
StationRequestResolverFactory  ..>  StationRequestResolver : «create»
TokenHandler "1" *--> "manager 1" IStandardRequestManager 
UpdateHandler  ..>  InitialUpdate : «create»
UpdateHandler  ..>  ResetRSToRemoveAssistantCookResolver : «create»
UpdateHandler  ..>  ResetRSToStoreJobInResolvers : «create»
UpdateHandler  ..>  ResetRSToUpdateRestaurantResolver : «create»
UpdateHandler "1" *--> "UPDATE_STEPS *" IUpdateStep 
UpdateHandler "1" *--> "manager 1" IStandardRequestManager 
WarehouseConcreteRequestResolver  -->  AbstractWarehouseRequestResolver 
WarehouseConcreteRequestResolverFactory  ..>  WarehouseConcreteRequestResolver : «create»
WarehouseRequestResolver  -->  AbstractWarehouseRequestResolver 
WarehouseRequestResolverFactory  ..>  WarehouseRequestResolver : «create»
WorkOrderBuilding  -->  AbstractWorkOrder 
WorkOrderBuildingView  -->  AbstractWorkOrderView 
WorkOrderDecoration  -->  AbstractWorkOrder 
WorkOrderDecorationView  -->  AbstractWorkOrderView 
WorkOrderMiner  -->  AbstractWorkOrder 
WorkOrderMinerView  -->  AbstractWorkOrderView 
WorkOrderPlantationField  -->  AbstractWorkOrder 
WorkOrderPlantationFieldView  -->  AbstractWorkOrderView 
WrappedBlacklistAssignmentRequestManager  -->  AbstractWrappedRequestManager 
WrappedStaticStateRequestManager  -->  AbstractWrappedRequestManager 
```
