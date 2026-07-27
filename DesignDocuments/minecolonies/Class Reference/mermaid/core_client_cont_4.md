# core.client (cont. 4)

43 classes, 0 internal relationships shown.

```mermaid
classDiagram
direction BT

class MaleArcherModel {
  + MaleArcherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleAristocratModel {
  + MaleAristocratModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleBakerModel {
  + MaleBakerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleBlacksmithModel {
  + MaleBlacksmithModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleBuilderModel {
  + MaleBuilderModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleCarpenterModel {
  + MaleCarpenterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleChickenHerderModel {
  + MaleChickenHerderModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleChildModel {
  + MaleChildModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleCitizenModel {
  + MaleCitizenModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleComposterModel {
  + MaleComposterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleConcreteMixerModel {
  + MaleConcreteMixerModel(ModelPart) 
  + createMesh() LayerDefinition
}
class MaleCookModel {
  + MaleCookModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleCourierModel {
  + MaleCourierModel(ModelPart) 
  + getActualRotation(AbstractEntityCitizen) float
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleCowHerderModel {
  + MaleCowHerderModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleCrafterModel {
  + MaleCrafterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleDruidModel {
  + MaleDruidModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleDyerModel {
  + MaleDyerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleEnchanterModel {
  + MaleEnchanterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleFarmerModel {
  + MaleFarmerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleFisherModel {
  + MaleFisherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleFletcherModel {
  + MaleFletcherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleFloristModel {
  + MaleFloristModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleForesterModel {
  + MaleForesterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleGlassblowerModel {
  + MaleGlassblowerModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleHealerModel {
  + MaleHealerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleKnightModel {
  + MaleKnightModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleMechanistModel {
  + MaleMechanistModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleMinerModel {
  + MaleMinerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleNetherWorkerModel {
  + MaleNetherWorkerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleNobleModel {
  + MaleNobleModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MalePlanterModel {
  + MalePlanterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleRabbitHerderModel {
  + MaleRabbitHerderModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleSettlerModel {
  + MaleSettlerModel(ModelPart) 
  + createMesh() LayerDefinition
}
class MaleShepherdModel {
  + MaleShepherdModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleSmelterModel {
  + MaleSmelterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleStudentModel {
  + MaleStudentModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleSwineHerderModel {
  + MaleSwineHerderModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class MaleTeacherModel {
  + MaleTeacherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleUndertakerModel {
  + MaleUndertakerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MercenaryModel {
  + MercenaryModel(ModelPart) 
  + createMesh() LayerDefinition
}
class ScarecrowModel {
  + ScarecrowModel(ModelPart) 
  + createMesh() LayerDefinition
  + renderToBuffer(PoseStack, VertexConsumer, int, int, float, float, float, float) void
}
class SleepingParticle {
  + SleepingParticle(SpriteSet, ClientLevel, double, double, double, double, double, double) 
  + tick() void
  + getLightColor(float) int
   ParticleRenderType renderType
}
class SpearModel {
  + SpearModel(ModelPart) 
  + renderToBuffer(PoseStack, VertexConsumer, int, int, float, float, float, float) void
  + createLayer() LayerDefinition
}
```
