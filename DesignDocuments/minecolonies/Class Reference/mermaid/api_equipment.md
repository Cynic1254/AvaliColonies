# api.equipment

2 classes, 0 internal relationships shown.

```mermaid
classDiagram
direction BT

class EquipmentTypeEntry {
  - EquipmentTypeEntry(Component, BiPredicate~ItemStack, EquipmentTypeEntry~, BiFunction~ItemStack, EquipmentTypeEntry, Integer~, ResourceLocation) 
  - Component displayName
  - ResourceLocation registryName
  + parseResourceLocation(ResourceLocation) ResourceLocation
  + getMiningLevel(ItemStack) int
  + checkIsEquipment(ItemStack) boolean
  + parseResourceLocation(String) ResourceLocation
   Component displayName
   ResourceLocation registryName
}
class ModEquipmentTypes {
  + ModEquipmentTypes() 
  + vanillaToolLevel(ItemStack, EquipmentTypeEntry) int
  + durabilityBasedLevel(ItemStack, int) int
  - register(String, Consumer~Builder~) RegistryObject~EquipmentTypeEntry~
  + canPerformDefaultActions(ItemStack, Set~ToolAction~) boolean
   IForgeRegistry~EquipmentTypeEntry~ registry
}
```
