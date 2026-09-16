package com.cynic1254.proceduralcitizens.data.records.texture.color;

import net.minecraft.util.Mth;

import java.util.Objects;
import java.util.Random;

public record HSLRange(
        RangeValue hue,
        RangeValue saturation,
        RangeValue lightness,
        Float weight
) implements ColorBase{

    @Override
    public int rollColor(Random random) {
        return ColorUtils.parseHSL(hue.getValue(random), saturation.getValue(random), lightness.getValue(random));
    }

    public record RangeValue(
            Float min,
            Float max
    ) {
        public RangeValue {
            min = Objects.requireNonNullElse(min, 0.0f);
            max = Objects.requireNonNullElse(max, 0.0f);
        }

        public float getValue(Random random) {
            return Mth.lerp(random.nextFloat(), min, max);
        }
    }
}
