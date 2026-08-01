package com.cynic1254.avalitheme.client.rendering;

import com.cynic1254.avalitheme.client.rendering.layers.ItemGeoCitizenLayer;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.apiimp.initializer.ModModelTypeInitializer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class GeoCitizenRenderer extends GeoReplacedEntityRenderer<AbstractEntityCitizen, GeoCitizenAnimatable> {
    private static final ResourceLocation PLACEHOLDER_MODEL =
            ResourceLocation.fromNamespaceAndPath(GeckoLib.MOD_ID, "bat");

    public GeoCitizenRenderer(EntityRendererProvider.Context context, GeoCitizenAnimatable animatable) {
        super(context, new DefaultedEntityGeoModel<>(PLACEHOLDER_MODEL), animatable);
        ModModelTypeInitializer.init(context);

        addRenderLayer(new ItemGeoCitizenLayer(this));
    }
}
