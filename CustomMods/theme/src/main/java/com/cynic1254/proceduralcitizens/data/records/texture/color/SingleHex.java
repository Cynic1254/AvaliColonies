package com.cynic1254.proceduralcitizens.data.records.texture.color;

import java.util.Objects;
import java.util.Random;

public record SingleHex(
        String color,
        Float weight
) implements ColorBase {
    public SingleHex {
        color = Objects.requireNonNullElse(color, "#FFFFFF");
        weight = (weight == null || weight <= 0.0f) ? 1.0f : weight;
    }

    @Override
    public int rollColor(Random random) {
        return ColorUtils.parseHexColor(color);
    }
}
