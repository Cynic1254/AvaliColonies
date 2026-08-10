package com.cynic1254.proceduralcitizens.client.rendering.model;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;

import java.util.HashMap;
import java.util.Map;

public class GeoCitizenAttachmentModel extends DefaultedGeoModel<GeoCitizenAnimatable> {

    private static final Map<String, GeoCitizenAttachmentModel> attachmentCache = new HashMap<>();

    public GeoCitizenAttachmentModel(ResourceLocation assetSubpath) {
        super(assetSubpath);
    }

    public static GeoCitizenAttachmentModel getOrCreateAttachment(ResourceLocation modelLocation) {
        return attachmentCache.computeIfAbsent(modelLocation.toString(),
                key -> new GeoCitizenAttachmentModel(ResourceLocation.parse(key))
        );
    }

    @Override
    public ResourceLocation getAnimationResource(GeoCitizenAnimatable animatable) {
        return null;
    }

    @Override
    protected String subtype() {
        return "attachment";
    }

    public static void clearCache() {
        attachmentCache.clear();
    }
}
