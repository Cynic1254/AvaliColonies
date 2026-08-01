package com.cynic1254.avalitheme.client.rendering.layers;

import com.cynic1254.avalitheme.client.rendering.GeoCitizenAnimatable;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ItemGeoCitizenLayer extends GeoRenderLayer<GeoCitizenAnimatable> {

    public ItemGeoCitizenLayer(GeoReplacedEntityRenderer<AbstractEntityCitizen, GeoCitizenAnimatable> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        // Retrieve the actual rendering entity from renderer context
        if (getRenderer() instanceof GeoReplacedEntityRenderer<?, ?> replacedRenderer) {
            Object currentEntity = replacedRenderer.getCurrentEntity();
            if (currentEntity instanceof AbstractEntityCitizen citizen) {

                ItemStack mainHandItem = citizen.getMainHandItem();
                ItemStack offHandItem = citizen.getOffhandItem();

                if (!mainHandItem.isEmpty()) {
                    renderItemForArm(poseStack, citizen, mainHandItem, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, bufferSource, packedLight);
                }

                if (!offHandItem.isEmpty()) {
                    renderItemForArm(poseStack, citizen, offHandItem, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, bufferSource, packedLight);
                }
            }
        }
    }

    private void renderItemForArm(PoseStack poseStack, AbstractEntityCitizen entity, ItemStack stack, ItemDisplayContext displayContext, HumanoidArm arm, MultiBufferSource bufferSource, int packedLight) {
        String boneName = (arm == HumanoidArm.RIGHT) ? "bone3" : "bone6";

        this.getRenderer().getGeoModel().getBone(boneName).ifPresent(bone -> {
            poseStack.pushPose();

            // Item orientation offset
            poseStack.translate(0.0D, -0.25D, 0.0D);
            poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(-90.0F));

            Minecraft.getInstance().getItemRenderer().renderStatic(
                    entity,
                    stack,
                    displayContext,
                    false,
                    poseStack,
                    bufferSource,
                    entity.level(),
                    packedLight,
                    net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                    entity.getId()
            );

            poseStack.popPose();
        });
    }
}
