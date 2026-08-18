package com.cynic1254.proceduralcitizens.client.rendering.renderers;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.Layers.CitizenAttachmentLayer;
import com.cynic1254.proceduralcitizens.client.rendering.Layers.CitizenItemGeoLayer;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenModel;
import com.cynic1254.proceduralcitizens.cache.CitizenTextureCache;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.apiimp.initializer.ModModelTypeInitializer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class GeoCitizenRenderer extends GeoReplacedEntityRenderer<AbstractEntityCitizen, GeoCitizenAnimatable> {
    public GeoCitizenRenderer(EntityRendererProvider.Context context, GeoCitizenAnimatable animatable) {
        super(context, new DefaultedEntityGeoModel<>(CitizenDefaults.MISSING_MODEL_ID, "Head"), animatable);
        ModModelTypeInitializer.init(context);

        addRenderLayer(new CitizenItemGeoLayer(this));
        addRenderLayer(new CitizenAttachmentLayer(this));
    }

    @Override
    public GeoModel<GeoCitizenAnimatable> getGeoModel() {
        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen)getCurrentEntity();
        return GeoCitizenModel.getOrCreateModel(entity.getModelId());
    }

    public GeoCitizenModel getCitizenModel() {
        return (GeoCitizenModel) getGeoModel();
    }

    @Override
    public ResourceLocation getTextureLocation(GeoCitizenAnimatable animatable) {
        return CitizenTextureCache.getTextureResource(getCurrentEntity());
    }
}
