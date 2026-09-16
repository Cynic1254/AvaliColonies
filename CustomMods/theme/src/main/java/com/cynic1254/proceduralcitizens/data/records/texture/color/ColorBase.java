package com.cynic1254.proceduralcitizens.data.records.texture.color;

import com.cynic1254.proceduralcitizens.helper.WeightedRandom;

import java.util.Random;

public sealed interface ColorBase extends WeightedRandom
        permits HSLRange, HSLValue, RangeHex, SingleHex {
    int rollColor(Random random);
}
