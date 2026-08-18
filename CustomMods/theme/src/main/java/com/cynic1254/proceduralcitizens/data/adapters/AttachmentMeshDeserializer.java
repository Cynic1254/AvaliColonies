package com.cynic1254.proceduralcitizens.data.adapters;

import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.records.GeoCitizenDefinition.AttachmentMesh;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class AttachmentMeshDeserializer implements JsonDeserializer<AttachmentMesh> {
    @Override
    public AttachmentMesh deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            ResourceLocation location = ResourceLocation.tryParse(json.getAsString());
            return new AttachmentMesh(location != null ? location : CitizenDefaults.MISSING_MODEL_ID, 1.0f);
        }

        if (json.isJsonObject()) {
            JsonObject obj = json.getAsJsonObject();
            ResourceLocation location = obj.has("mesh")
                    ? ResourceLocation.tryParse(obj.get("mesh").getAsString())
                    : CitizenDefaults.MISSING_MODEL_ID;

            float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1.0f;
            return new AttachmentMesh(location, weight);
        }

        return new AttachmentMesh(CitizenDefaults.MISSING_MODEL_ID, 1.0f);
    }
}
