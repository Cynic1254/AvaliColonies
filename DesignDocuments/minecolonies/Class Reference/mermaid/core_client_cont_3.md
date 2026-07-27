# core.client (cont. 3)

43 classes, 0 internal relationships shown.

```mermaid
classDiagram
direction BT

class FemaleAlchemistModel {
  + FemaleAlchemistModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleApiaryModel {
  + FemaleApiaryModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleArcherModel {
  + FemaleArcherModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleAristocratModel {
  + FemaleAristocratModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + renderToBuffer(PoseStack, VertexConsumer, int, int, float, float, float, float) void
}
class FemaleBakerModel {
  + FemaleBakerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleBlacksmithModel {
  + FemaleBlacksmithModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleBuilderModel {
  + FemaleBuilderModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleCarpenterModel {
  + FemaleCarpenterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleChickenHerderModel {
  + FemaleChickenHerderModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleChildModel {
  + FemaleChildModel(ModelPart) 
  + createMesh() LayerDefinition
}
class FemaleCitizenModel {
  + FemaleCitizenModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleComposterModel {
  + FemaleComposterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleConcreteMixerModel {
  + FemaleConcreteMixerModel(ModelPart) 
  + createMesh() LayerDefinition
}
class FemaleCookModel {
  + FemaleCookModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleCourierModel {
  + FemaleCourierModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + getActualRotation(AbstractEntityCitizen) float
}
class FemaleCowHerderModel {
  + FemaleCowHerderModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleCrafterModel {
  + FemaleCrafterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleDruidModel {
  + FemaleDruidModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleDyerModel {
  + FemaleDyerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleEnchanterModel {
  + FemaleEnchanterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleFarmerModel {
  + FemaleFarmerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleFisherModel {
  + FemaleFisherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleFletcherModel {
  + FemaleFletcherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleFloristModel {
  + FemaleFloristModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleForesterModel {
  + FemaleForesterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleGlassblowerModel {
  + FemaleGlassblowerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleHealerModel {
  + FemaleHealerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleKnightModel {
  + FemaleKnightModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleMechanistModel {
  + FemaleMechanistModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleMinerModel {
  + FemaleMinerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleNetherWorkerModel {
  + FemaleNetherWorkerModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleNobleModle {
  + FemaleNobleModle(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemalePlanterModel {
  + FemalePlanterModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleRabbitHerderModel {
  + FemaleRabbitHerderModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleSettlerModel {
  + FemaleSettlerModel(ModelPart) 
  + createMesh() LayerDefinition
}
class FemaleShepherdModel {
  + FemaleShepherdModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleSmelterModel {
  + FemaleSmelterModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleStudentModel {
  + FemaleStudentModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleSwineHerderModel {
  + FemaleSwineHerderModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
class FemaleTeacherModel {
  + FemaleTeacherModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class FemaleUndertakerModel {
  + FemaleUndertakerModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleAlchemistModel {
  + MaleAlchemistModel(ModelPart) 
  + createMesh() LayerDefinition
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
}
class MaleApiaryModel {
  + MaleApiaryModel(ModelPart) 
  + setupAnim(AbstractEntityCitizen, float, float, float, float, float) void
  + createMesh() LayerDefinition
}
```
