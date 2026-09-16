package com.cynic1254.proceduralcitizens.helper;

import net.minecraft.world.entity.EquipmentSlot;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class EquipmentSlotHelper {
    private static final Map<String, EquipmentSlot> BY_NAME = Arrays.stream(EquipmentSlot.values())
            .collect(Collectors.toMap(EquipmentSlot::getName, slot -> slot));

    public static EquipmentSlot getSlotFromName(String name) {
        return BY_NAME.get(name); // Returns null safely if not found
    }
}
