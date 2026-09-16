package com.cynic1254.proceduralcitizens.helper;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface WeightedRandom {
    Float weight();

    static <T extends WeightedRandom> Optional<T> roll(List<T> items, Random random) {
        if (items.isEmpty()) return Optional.empty();

        float totalWeight = 0.0f;
        for (T item : items) {
            totalWeight += item.weight();
        }

        float roll = random.nextFloat() * totalWeight;
        float cumulative = 0.0f;

        for (T item : items) {
            cumulative += item.weight();
            if (roll <= cumulative) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
