package com.cynic1254.proceduralcitizens.client.rendering.model;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.data.records.CitizenDefinition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.*;

/// Represents a Citizen model defined in a CitizenDefinition
public class GeoCitizenModel extends DefaultedEntityGeoModel<GeoCitizenAnimatable> {

    private static final Map<ResourceLocation, GeoCitizenModel> modelRegistry = new HashMap<>();
    private static final GeoCitizenModel FALLBACK_MODEL = new GeoCitizenModel(CitizenDefaults.EMPTY_MODEL_ID);

    public GeoCitizenModel(CitizenDefinition definition) {
        super(definition.model(), definition.bones().head());
    }

    private GeoCitizenModel(ResourceLocation model) {
        super(model, true);
    }

    /// Gets a model based on the provided resource location, creates it if it doesn't exist yet
    /// @param defId The resource location of the CitizenDefinition
    /// @return the model corresponding to the ResourceLocation or a fully empty model if no model is present at the location
    public static GeoCitizenModel getOrCreateModel(ResourceLocation defId) {
        return modelRegistry.computeIfAbsent(defId, id ->
                    CitizenDefinitionCache.getDefinition(id)
                            .map(GeoCitizenModel::new)
                            .orElse(FALLBACK_MODEL)
        );
    }

    /// Textures are handles through the renderer, this function just calls `renderer.getTextureLocation(renderer.getAnimatable())`
    /// @implNote No guarantees are made that the texture being returned is of the current GeoModel, in fact the texture being returned explicitly belongs to the entity currently being rendered
    /// @return the resource location of the current entity being rendered
    @Override
    public ResourceLocation getTextureResource(GeoCitizenAnimatable animatable, @Nullable GeoRenderer<GeoCitizenAnimatable> renderer) {
        if (renderer == null)
            return null;

        return renderer.getTextureLocation(renderer.getAnimatable());
    }

    /// citizen models should be stored in geo/citizen
    @Override
    protected String subtype() {
        return "citizen";
    }

    public static void clearCache() {
        modelRegistry.clear();
    }
}
