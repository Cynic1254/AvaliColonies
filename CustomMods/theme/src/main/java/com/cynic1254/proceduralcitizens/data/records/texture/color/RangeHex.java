package com.cynic1254.proceduralcitizens.data.records.texture.color;

import net.minecraft.util.Mth;

import java.util.Objects;
import java.util.Random;

public record RangeHex(
        String min,
        String max,
        Float weight
) implements ColorBase {
    public RangeHex {
        min = Objects.requireNonNullElse(min, "#000000");
        max = Objects.requireNonNullElse(max, "#FFFFFF");
        weight = (weight == null || weight <= 0.0f) ? 1.0f : weight;
    }

    @Override
    public int rollColor(Random random) {
        int minColor = ColorUtils.parseHexColor(min);
        int maxColor = ColorUtils.parseHexColor(max);

        int r = Mth.lerpInt(random.nextFloat(), (minColor >> 16) & 0xFF, (maxColor >> 16) & 0xFF);
        int g = Mth.lerpInt(random.nextFloat(), (minColor >>  8) & 0xFF, (maxColor >>  8) & 0xFF);
        int b = Mth.lerpInt(random.nextFloat(), minColor & 0xFF, maxColor & 0xFF);

        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }
}
