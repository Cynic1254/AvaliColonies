package com.cynic1254.proceduralcitizens.data.adapters;

import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.records.GeoCitizenDefinition.WeightedTexture;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class WeightedTextureDeserializer implements JsonDeserializer<WeightedTexture> {
    @Override
    public WeightedTexture deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Case A: Simplified String -> "texture_id"
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            ResourceLocation location = ResourceLocation.tryParse(json.getAsString());
            return new WeightedTexture(location != null ? location : CitizenDefaults.MISSINGNO_TEXTURE, 1.0f);
        }

        // Case B: Standard Object -> { "texture": "...", "weight": 2.0 }
        if (json.isJsonObject()) {
            JsonObject obj = json.getAsJsonObject();
            ResourceLocation location = obj.has("texture")
                    ? ResourceLocation.tryParse(obj.get("texture").getAsString())
                    : CitizenDefaults.MISSINGNO_TEXTURE;

            float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1.0f;
            return new WeightedTexture(location, weight);
        }

        return new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f);
    }
}
