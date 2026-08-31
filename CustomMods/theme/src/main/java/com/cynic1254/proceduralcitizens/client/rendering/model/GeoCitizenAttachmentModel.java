package com.cynic1254.proceduralcitizens.client.rendering.model;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.HashMap;
import java.util.Map;

/// GeoModel representing an attachment model onto a citizen model
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

    /// No animations are supported for attachments, we just use the citizen models for movement
    @Override
    public ResourceLocation getAnimationResource(GeoCitizenAnimatable animatable) {
        return null;
    }

    /// we use the same texture as the citizen
    @Override
    public ResourceLocation getTextureResource(GeoCitizenAnimatable animatable, @Nullable GeoRenderer<GeoCitizenAnimatable> renderer) {
        if (renderer == null)
            return null;

        return renderer.getTextureLocation(renderer.getAnimatable());
    }

    /// attachment models should be stored in geo/attachment
    @Override
    protected String subtype() {
        return "attachment";
    }

    public static void clearCache() {
        attachmentCache.clear();
    }
}
