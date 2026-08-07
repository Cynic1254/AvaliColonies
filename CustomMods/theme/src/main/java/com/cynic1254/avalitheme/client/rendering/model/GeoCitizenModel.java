package com.cynic1254.avalitheme.client.rendering.model;

import com.cynic1254.avalitheme.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.avalitheme.data.GeoCitizenDefinitionLoader;
import com.cynic1254.avalitheme.data.records.GeoCitizenDefinition;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import java.util.HashMap;
import java.util.Map;

public class GeoCitizenModel extends DefaultedEntityGeoModel<GeoCitizenAnimatable> {

    private static final Map<ResourceLocation, GeoCitizenModel> modelRegistry = new HashMap<>();

    public GeoCitizenModel(GeoCitizenDefinition definition) {
        super(definition.modelData(), true);
    }

    public static GeoCitizenModel getOrCreateModel(ResourceLocation defId) {
        return modelRegistry.computeIfAbsent(defId,
                id -> GeoCitizenDefinitionLoader.loadDefinition(Minecraft.getInstance().getResourceManager(), id)
                        .map(GeoCitizenModel::new).orElse(null));
    }

    @Override
    protected String subtype() {
        return "citizen";
    }

    public static void clearCache() {
        modelRegistry.clear();
    }
}
