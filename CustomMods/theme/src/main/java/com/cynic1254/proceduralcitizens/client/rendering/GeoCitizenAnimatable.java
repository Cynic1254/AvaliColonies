package com.cynic1254.proceduralcitizens.client.rendering;

import com.cynic1254.proceduralcitizens.client.CitizenAnimations;
import com.minecolonies.api.entity.ModEntities;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GeoCitizenAnimatable implements GeoReplacedEntity {

    private final EntityType<? extends AbstractEntityCitizen> entityType;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final GeoCitizenAnimatable CITIZEN = new GeoCitizenAnimatable(ModEntities.CITIZEN);
    public static final GeoCitizenAnimatable VISITOR = new GeoCitizenAnimatable(ModEntities.VISITOR);

    public GeoCitizenAnimatable(EntityType<? extends AbstractEntityCitizen> entityType) {
        this.entityType = entityType;
    }

    @Override
    public EntityType<? extends AbstractEntityCitizen> getReplacingEntityType() {
        return this.entityType;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(CitizenAnimations.locomotionController(this));
        controllers.add(CitizenAnimations.actionController(this));
        controllers.add(CitizenAnimations.deathController(this));
    }
}