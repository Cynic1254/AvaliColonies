package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.renderers.GeoCitizenRenderer;
import com.cynic1254.proceduralcitizens.data.BoneData;
import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.HashSet;
import java.util.Set;

/// Render layer for rendering various attachments onto citizens
public class CitizenAttachmentLayer extends GeoRenderLayer<GeoCitizenAnimatable> {
    public CitizenAttachmentLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void preRender(PoseStack poseStack, GeoCitizenAnimatable animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        hideAllAttachments(bakedModel);

        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        BoneData boneData = entity.getAttachments();

        // show attachment bones that are always visible
        setVisibilityOnBones(bakedModel, boneData.getAlwaysVisibleBones(), true);

        // show equipment bones when the slot *DOESN'T* have an item
        var equipmentBones = boneData.getArmorHiddenBones();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor())
                continue;

            ItemStack stack = entity.getItemBySlot(slot);
            if (!stack.isEmpty()) continue;

            setVisibilityOnBones(bakedModel, equipmentBones.getOrDefault(slot, new HashSet<>()), true);
        }

        IJob<?> job = entity.getCitizenJobHandler().getColonyJob();

        if (job == null)
            return;

        ResourceLocation jobKey = job.getJobRegistryEntry().getKey();

        // Show all job bones except for the ones keyed with the job
        var jobHiddenBones = boneData.getJobHiddenBones();
        for (Set<String> bones : jobHiddenBones.values()) {
            setVisibilityOnBones(bakedModel, bones, true);
        }
        setVisibilityOnBones(bakedModel, jobHiddenBones.getOrDefault(jobKey, new HashSet<>()), false);

        // show the bones for the specific job
        setVisibilityOnBones(bakedModel, boneData.getJobShownBones().getOrDefault(jobKey, new HashSet<>()), true);
    }

    private void hideAllAttachments(BakedGeoModel bakedModel) {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) ((GeoCitizenRenderer) renderer).getCurrentEntity();
        var definition = CitizenDefinitionCache.getDefinition(entity.getModelId());

        if (definition.isEmpty()) {
            return;
        }

        setVisibilityOnBones(bakedModel, definition.get().getAllAttachmentBones(), false);
    }

    private void setVisibilityOnBones(BakedGeoModel bakedModel, Set<String> bones, boolean visible) {
        for (String bone : bones) {
            bakedModel.getBone(bone).ifPresent(geoBone -> geoBone.setHidden(!visible));
        }
    }
}
