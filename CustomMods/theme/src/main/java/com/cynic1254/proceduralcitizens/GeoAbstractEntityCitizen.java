package com.cynic1254.proceduralcitizens;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.BoneData;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import net.minecraft.resources.ResourceLocation;

public interface GeoAbstractEntityCitizen {
    default TextureIdentifierDefinition getTextureID() {return CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION;}
    default ResourceLocation getModelId() {return CitizenDefaults.MISSING_MODEL_ID;}
    default BoneData getAttachments() {return new BoneData();}
    default void setRenderData(String texture, String model, String attachments) {}
}
