package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.renderers.GeoCitizenRenderer;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenAttachmentModel;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenModel;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.RenderUtils;

/// Render layer for rendering various attachments onto citizens
public class CitizenAttachmentLayer extends GeoRenderLayer<GeoCitizenAnimatable> {
    public CitizenAttachmentLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    GeoCitizenRenderer getCitizenRenderer() {return (GeoCitizenRenderer) getRenderer();}

    @Override
    public void render(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        GeoCitizenRenderer citizenRenderer = getCitizenRenderer();
        GeoCitizenModel baseModel = citizenRenderer.getCitizenModel();

        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen)citizenRenderer.getCurrentEntity();

        ResourceLocation textureLocation = citizenRenderer.getTextureLocation(entity);
        RenderType modelRenderType = RenderType.entityCutoutNoCull(textureLocation);
        VertexConsumer modelBuffer = bufferSource.getBuffer(modelRenderType);

        entity.getAttachments().forEach(
                (boneName, attachmentId) -> baseModel.getBone(boneName).ifPresent(targetBone -> {
            poseStack.pushPose();

            RenderUtils.prepMatrixForBone(poseStack, targetBone);

            GeoCitizenAttachmentModel attachmentModel = GeoCitizenAttachmentModel.getOrCreateAttachment(attachmentId);
            BakedGeoModel bakedAttachmentModel = attachmentModel.getBakedModel(attachmentId);

            renderer.reRender(
                    bakedAttachmentModel,
                    poseStack,
                    bufferSource,
                    renderer.getAnimatable(),
                    modelRenderType,
                    modelBuffer,
                    partialTick,
                    packedLight,
                    packedOverlay,
                    1.0f, 1.0f, 1.0f, 1.0f);

            poseStack.popPose();
        }));
    }
}
