package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.renderers.GeoCitizenRenderer;
import com.cynic1254.proceduralcitizens.data.records.CitizenDefinition;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

/// Render layer for rendering armor onto a citizen
public class CitizenArmorLayer extends GeoRenderLayer<GeoCitizenAnimatable> {

    private CitizenDefinition.ArmorBones armorBones = null;

    public CitizenArmorLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void preRender(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = CitizenDefinitionCache.getDefinition(entity.getModelId());

        if (definition.isEmpty()) {
            return;
        }

        armorBones = definition.get().bones().armor();

        setAllArmorBonesVisibility(bakedModel, false);
    }

    //TODO: we should be able to perform this logic on the render function to save having to traverse the entire hierarchy
    @Override
    public void renderForBone(PoseStack poseStack, GeoCitizenAnimatable animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = CitizenDefinitionCache.getDefinition(entity.getModelId());

        if (definition.isEmpty()) {
            return;
        }

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor()) continue;

            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.isEmpty()) continue;

            GeoBone armorBone = GetBoneForSlot(slot, bone);
            if (armorBone == null)
                continue;

            ResourceLocation armorTexture = definition.get().textures().getTextureForMaterialAndSlot(stack);
            RenderType armorRenderType = RenderType.entityCutoutNoCull(armorTexture);
            VertexConsumer armorBuffer = bufferSource.getBuffer(armorRenderType);

            armorBone.setHidden(false);
            getRenderer().renderRecursively(
                    poseStack, animatable, armorBone,
                    armorRenderType, bufferSource, armorBuffer,
                    false, partialTick, packedLight, packedOverlay,
                    1.0f, 1.0f, 1.0f, 1.0f
            );
            armorBone.setHidden(true);
        }
    }

    private void setAllArmorBonesVisibility(BakedGeoModel bakedModel, boolean visible) {
        for (String bone : armorBones.getAllBones()) {
            bakedModel.getBone(bone).ifPresent(geoBone -> geoBone.setHidden(!visible));
        }
    }

    private GeoBone GetBoneForSlot(EquipmentSlot slot, GeoBone parent) {
        var slotBones = armorBones.getBonesForSlot(slot);

        for (GeoBone slotBone : parent.getChildBones()) {
            if (slotBones.contains(slotBone.getName()))
                return slotBone;
        }

        return null;
    }
}
