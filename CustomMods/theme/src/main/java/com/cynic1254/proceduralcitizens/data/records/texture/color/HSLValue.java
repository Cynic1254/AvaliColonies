package com.cynic1254.proceduralcitizens.data.records.texture.color;

import net.minecraft.util.Mth;

import java.util.Objects;
import java.util.Random;

public record HSLValue(
        Float hue,
        Float saturation,
        Float lightness,
        Float weight
) implements ColorBase {

    public HSLValue {
        hue         = Mth.clamp(Objects.requireNonNullElse(hue,         0.0f), 0.0f, 360.0f);
        saturation  = Mth.clamp(Objects.requireNonNullElse(saturation,  1.0f), 0.0f, 1.0f);
        lightness   = Mth.clamp(Objects.requireNonNullElse(lightness,   0.5f), 0.0f, 1.0f);
        weight      = (weight == null || weight <= 0.0f) ? 1.0f : weight;
    }

    @Override
    public int rollColor(Random random) {
        return ColorUtils.parseHSL(this);
    }
}
