package com.cynic1254.proceduralcitizens.data.adapters;

import com.cynic1254.proceduralcitizens.data.records.texture.color.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ColorBaseDeserializer implements JsonDeserializer<ColorBase> {
    @Override
    public ColorBase deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            return new SingleHex(json.getAsString(), 1.0f);
        }

        if (!json.isJsonObject()) {
            return new SingleHex("#FFFFFF", 1.0f);
        }

        JsonObject obj = json.getAsJsonObject();

        // SingleHex
        if (obj.has("color")) {
            return context.deserialize(json, SingleHex.class);
        }

        // HSLRange
        if (obj.has("hue") && obj.get("hue").isJsonObject()) {
            return context.deserialize(json, HSLRange.class);
        }

        // HSL Value
        if (obj.has("hue")) {
            return context.deserialize(json, HSLValue.class);
        }

        // HexRange
        if (obj.has("min") || obj.has("max")) {
            return context.deserialize(json, RangeHex.class);
        }

        return new SingleHex("#FFFFFF", 1.0f);
    }
}
