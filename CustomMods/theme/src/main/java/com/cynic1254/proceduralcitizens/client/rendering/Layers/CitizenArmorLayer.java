package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.cache.CitizenArmorCache;
import com.cynic1254.proceduralcitizens.cache.GeoCitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.renderers.GeoCitizenRenderer;
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

public class CitizenArmorLayer extends GeoRenderLayer<GeoCitizenAnimatable> {

    private CitizenArmorCache currentCache = null;

    public CitizenArmorLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void preRender(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        currentCache = CitizenArmorCache.getCacheForModel(getCitizenRenderer().getCitizenModel(), renderer.getAnimatable());

        setAllArmorBonesHidden(true);
    }

    @Override
    public void renderForBone(PoseStack poseStack, GeoCitizenAnimatable animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = GeoCitizenDefinitionCache.getDefinition(entity.getModelId());

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

            ResourceLocation armorTexture = definition.get().getTextureForMaterialAndSlot(stack, slot);
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

    private GeoCitizenRenderer getCitizenRenderer() {
        return (GeoCitizenRenderer) renderer;
    }

    private void setAllArmorBonesHidden(boolean hidden) {
        for (GeoBone bone : currentCache.GetAllBones()) {
            bone.setHidden(hidden);
        }
    }

    private GeoBone GetBoneForSlot(EquipmentSlot slot, GeoBone parent) {
        var slotBones = currentCache.GetBonesForSlot(slot);

        for (GeoBone slotBone : slotBones) {
            if (slotBone.getParent().equals(parent))
                return slotBone;
        }

        return null;
    }
}
