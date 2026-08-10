package com.cynic1254.proceduralcitizens.client.rendering;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.Layers.CitizenAttachmentLayer;
import com.cynic1254.proceduralcitizens.client.rendering.Layers.CitizenItemGeoLayer;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenModel;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureManager;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.apiimp.initializer.ModModelTypeInitializer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class GeoCitizenRenderer extends GeoReplacedEntityRenderer<AbstractEntityCitizen, GeoCitizenAnimatable> {
    private static final ResourceLocation PLACEHOLDER_MODEL = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "default_citizen");

    public GeoCitizenRenderer(EntityRendererProvider.Context context, GeoCitizenAnimatable animatable) {
        super(context, new DefaultedEntityGeoModel<>(PLACEHOLDER_MODEL, "Head"), animatable);
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
        return TextureManager.getTextureResource(getCurrentEntity());
    }
}
