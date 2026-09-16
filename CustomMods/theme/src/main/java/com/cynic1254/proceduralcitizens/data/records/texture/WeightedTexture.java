package com.cynic1254.proceduralcitizens.data.records.texture;

import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.helper.WeightedRandom;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public record WeightedTexture(
        ResourceLocation texture,
        Float weight
) implements WeightedRandom {
    public WeightedTexture {
        texture = Objects.requireNonNullElse(texture, CitizenDefaults.MISSINGNO_TEXTURE);
        weight = (weight == null || weight <= 0.0f) ? 1.0f : weight;
    }
}
