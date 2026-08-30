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
import software.bernie.geckolib.util.RenderUtils;

import java.util.Set;

public class CitizenArmorLayer extends GeoRenderLayer<GeoCitizenAnimatable> {

    public CitizenArmorLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void preRender(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        setAllArmorBonesHidden(true);
    }

    @Override
    public void render(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = GeoCitizenDefinitionCache.getDefinition(entity.getModelId());

        if (definition.isEmpty()) {
            return;
        }

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor()) continue;

            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.isEmpty()) continue;

            Set<GeoBone> bones = GetBonesForSlot(slot);
            if (bones.isEmpty())
                continue;

            ResourceLocation armorTexture = definition.get().getTextureForMaterialAndSlot(stack, slot);
            RenderType armorRenderType = RenderType.entityCutoutNoCull(armorTexture);
            VertexConsumer armorBuffer = bufferSource.getBuffer(armorRenderType);

            for (GeoBone slotBone : bones) {
                poseStack.pushPose();

                if (slotBone.getParent() != null) {
                    GeoBone parentBone = slotBone.getParent();
                    RenderUtils.prepMatrixForBone(poseStack, parentBone);
                }

                slotBone.setHidden(false);
                getRenderer().renderRecursively(
                        poseStack, animatable, slotBone,
                        armorRenderType, bufferSource, armorBuffer,
                        false, partialTick, packedLight, packedOverlay,
                        1.0f, 1.0f, 1.0f, 1.0f
                );
                slotBone.setHidden(true);

                poseStack.popPose();
            }
        }
    }

    private GeoCitizenRenderer getCitizenRenderer() {
        return (GeoCitizenRenderer) renderer;
    }

    private void setAllArmorBonesHidden(boolean hidden) {
        CitizenArmorCache armorCache = CitizenArmorCache.getCacheForModel(getCitizenRenderer().getCitizenModel(), renderer.getAnimatable());

        for (GeoBone bone : armorCache.GetAllBones()) {
            bone.setHidden(hidden);
        }
    }

    private Set<GeoBone> GetBonesForSlot(EquipmentSlot slot) {
        CitizenArmorCache armorCache = CitizenArmorCache.getCacheForModel(getCitizenRenderer().getCitizenModel(), renderer.getAnimatable());

        return armorCache.GetBonesForSlot(slot);
    }
}
