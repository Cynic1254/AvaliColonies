package com.cynic1254.proceduralcitizens.client.rendering.model;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.cache.GeoCitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.data.records.GeoCitizenDefinition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.HashMap;
import java.util.Map;

public class GeoCitizenModel extends DefaultedEntityGeoModel<GeoCitizenAnimatable> {

    private static final Map<ResourceLocation, GeoCitizenModel> modelRegistry = new HashMap<>();
    private static final GeoCitizenModel FALLBACK_MODEL = new GeoCitizenModel(CitizenDefaults.EMPTY_MODEL_ID);

    public GeoCitizenModel(GeoCitizenDefinition definition) {
        super(definition.model(), true);
    }

    private GeoCitizenModel(ResourceLocation model) {
        super(model, true);
    }

    public static GeoCitizenModel getOrCreateModel(ResourceLocation defId) {
        return modelRegistry.computeIfAbsent(defId, id ->
                    GeoCitizenDefinitionCache.getDefinition(id)
                            .map(GeoCitizenModel::new)
                            .orElse(FALLBACK_MODEL)
        );
    }

    @Override
    public ResourceLocation getTextureResource(GeoCitizenAnimatable animatable, @Nullable GeoRenderer<GeoCitizenAnimatable> renderer) {
        if (renderer == null)
            return null;

        return renderer.getTextureLocation(renderer.getAnimatable());
    }

    @Override
    protected String subtype() {
        return "citizen";
    }

    public static void clearCache() {
        modelRegistry.clear();
    }
}
