package com.cynic1254.proceduralcitizens.client.rendering.model;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.GeoCitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.data.records.GeoCitizenDefinition;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import java.util.HashMap;
import java.util.Map;

public class GeoCitizenModel extends DefaultedEntityGeoModel<GeoCitizenAnimatable> {

    private static final Map<ResourceLocation, GeoCitizenModel> modelRegistry = new HashMap<>();
    private static final GeoCitizenModel FALLBACK_MODEL = new GeoCitizenModel(CitizenDefaults.PLACEHOLDER_MODEL_ID);

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
    protected String subtype() {
        return "citizen";
    }

    public static void clearCache() {
        modelRegistry.clear();
    }
}
