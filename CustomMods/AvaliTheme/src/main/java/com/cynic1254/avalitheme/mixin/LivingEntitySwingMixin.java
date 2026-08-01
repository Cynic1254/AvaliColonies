package com.cynic1254.avalitheme.mixin;

import com.cynic1254.avalitheme.client.rendering.GeoCitizenAnimatable;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntitySwingMixin {

    @Inject(
            method = "swing(Lnet/minecraft/world/InteractionHand;Z)V",
            at = @At("HEAD")
    )
    private void avali$onSwing(InteractionHand hand, boolean updateSelf, CallbackInfo ci) {
        if ((Object) this instanceof AbstractEntityCitizen citizen) {
            GeoCitizenAnimatable.VISITOR.triggerAnim(citizen, "action", "swing");
            GeoCitizenAnimatable.CITIZEN.triggerAnim(citizen, "action", "swing");
        }
    }
}