package com.cynic1254.avalitheme.client;

import com.cynic1254.avalitheme.client.rendering.GeoCitizenAnimatable;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.material.Fluids;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

public class AvaliAnimations {

    private static final double MIN_RUN_SPEED_SQ = 0.245 * 0.245;

    public static AnimationController<GeoCitizenAnimatable> deathController(GeoCitizenAnimatable animatable) {
        return new AnimationController<>(animatable, "Death", 0, state -> {
            AbstractEntityCitizen citizen = (AbstractEntityCitizen) state.getData(DataTickets.ENTITY);

            if (citizen != null && citizen.isDeadOrDying()) {
                return state.setAndContinue(DefaultAnimations.DIE);
            }

            return PlayState.STOP;
        });
    }

    /**
     * Single mutually-exclusive locomotion/pose state machine, checked in priority
     * order: a sleeping citizen is always sleeping regardless of velocity, a mounted/
     * seated citizen is always seated regardless of the vehicle's own motion, etc.
     */
    public static AnimationController<GeoCitizenAnimatable> locomotionController(GeoCitizenAnimatable animatable) {
        return new AnimationController<>(
                animatable,
                "locomotion",
                5,
                state -> {
                    // Retrieve the actual entity instance being rendered
                    AbstractEntityCitizen citizen = (AbstractEntityCitizen) state.getData(DataTickets.ENTITY);

                    if (citizen == null) {
                        return PlayState.STOP;
                    }

                    if (citizen.getPose() == Pose.SLEEPING) {
                        return state.setAndContinue(DefaultAnimations.REST);
                    }

                    if (citizen.isPassenger()) {
                        // Covers both real mounts (cavalry) and the SittingEntity dining hack -
                        // both are "riding a vehicle" from the entity's point of view.
                        return state.setAndContinue(DefaultAnimations.SIT);
                    }

                    if (isActuallySwimming(citizen)) {
                        return state.setAndContinue(DefaultAnimations.SWIM);
                    }

                    if (state.isMoving()) {
                        double horizontalSpeedSq = citizen.getDeltaMovement().horizontalDistanceSqr();
                        return horizontalSpeedSq > MIN_RUN_SPEED_SQ
                                ? state.setAndContinue(DefaultAnimations.RUN)
                                : state.setAndContinue(DefaultAnimations.WALK);
                    }

                    return state.setAndContinue(DefaultAnimations.IDLE);
                }
        );
    }

    /**
     * True only when the citizen is genuinely floating/swimming through open water, as
     * opposed to standing on the floor of a shallow puddle.
     */
    private static boolean isActuallySwimming(AbstractEntityCitizen entity) {
        return entity.isInWater()
                && !entity.isPassenger()
                && (!entity.onGround() || entity.getFluidTypeHeight(Fluids.WATER.getFluidType()) > entity.getFluidJumpThreshold());
    }

    /**
     * Trigger-only controller: does nothing by default (PlayState.STOP), and only ever
     * plays something when triggerAnim("action", "swing") is fired. See
     * LivingEntitySwingMixin - this replaces polling entity.swinging directly, which
     * was fighting the controller's own transition/reset logic every idle tick.
     * <p>
     * TODO: every job currently funnels through the same generic swing with no
     * semantic tag attached (mining, chopping, melee, eating, curing all just call
     * AbstractEntityCitizen#swing() - see AbstractEntityAIInteract#mineBlock,
     * CitizenItemUtils#hitBlockWithToolInHand, EntityAIEatTask#eat). Once we want those
     * to look different, thread an action-type through renderMetadata (or a new synced
     * field) and register additional triggerableAnim() entries here keyed off it.
     */
    public static AnimationController<GeoCitizenAnimatable> actionController(GeoCitizenAnimatable entity) {
        return new AnimationController<>(entity, "action", 2, state -> {
            AbstractEntityCitizen citizen = (AbstractEntityCitizen) state.getData(DataTickets.ENTITY);
            if (citizen.swinging) {
                return state.setAndContinue(DefaultAnimations.ATTACK_SWING);
            }
            return PlayState.STOP;
        });
    }
}