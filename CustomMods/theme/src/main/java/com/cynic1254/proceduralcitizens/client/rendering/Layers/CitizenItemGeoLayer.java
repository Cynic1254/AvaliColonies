package com.cynic1254.proceduralcitizens.client.rendering.Layers;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenRenderer;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

import java.util.Objects;

public class CitizenItemGeoLayer extends BlockAndItemGeoLayer<GeoCitizenAnimatable> {
    static final private float itemScale = 0.625f;

    public CitizenItemGeoLayer(GeoCitizenRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    protected @Nullable ItemStack getStackForBone(GeoBone bone, GeoCitizenAnimatable animatable) {
        AbstractEntityCitizen citizen = ((GeoCitizenRenderer)renderer).getCurrentEntity();

        if (Objects.equals(bone.getName(), "RightHand")) {
            return citizen.getMainHandItem();
        }
        if (Objects.equals(bone.getName(), "LeftHand")) {
            return citizen.getOffhandItem();
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, GeoCitizenAnimatable animatable) {
        return Objects.equals(bone.getName(), "RightHand") ? ItemDisplayContext.THIRD_PERSON_RIGHT_HAND : ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
    }

    @Override
    protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, GeoCitizenAnimatable animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
        AbstractEntityCitizen citizen = ((GeoCitizenRenderer)renderer).getCurrentEntity();

        poseStack.pushPose();

        poseStack.scale(itemScale, itemScale, itemScale);

        Minecraft.getInstance().getItemRenderer().renderStatic(citizen, stack,
                getTransformTypeForStack(bone, stack, animatable), false, poseStack, bufferSource, citizen.level(),
                packedLight, packedOverlay, citizen.getId());

        poseStack.popPose();
    }
}
