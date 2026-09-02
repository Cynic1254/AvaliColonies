package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.renderers.GeoCitizenRenderer;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CitizenClothingLayer extends GeoRenderLayer<GeoCitizenAnimatable> {
    public CitizenClothingLayer(GeoRenderer<GeoCitizenAnimatable> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = CitizenDefinitionCache.getDefinition(entity.getModelId());

        if (definition.isEmpty())
            return;

        ResourceLocation jobTexture = definition.get().getClothingTextureForJob(entity.getCitizenJobHandler().getColonyJob());

        if (jobTexture == null)
            return;

        RenderType outfitRenderType = RenderType.entityCutoutNoCull(jobTexture);
        VertexConsumer outfitBuffer = bufferSource.getBuffer(outfitRenderType);

        renderer.reRender(bakedModel, poseStack, bufferSource, animatable, outfitRenderType, outfitBuffer, partialTick, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
