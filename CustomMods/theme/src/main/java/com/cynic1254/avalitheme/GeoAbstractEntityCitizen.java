package com.cynic1254.avalitheme;

import com.cynic1254.avalitheme.client.rendering.textures.TextureIdentifierDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public interface GeoAbstractEntityCitizen {
    default TextureIdentifierDefinition getTextureID() {return new TextureIdentifierDefinition("");}
    default ResourceLocation getModelId() {return null;}
    default Map<String, ResourceLocation> getAttachments() {return new HashMap<>();}
    default void setRenderData(String texture, String model, String attachments) {}
}
