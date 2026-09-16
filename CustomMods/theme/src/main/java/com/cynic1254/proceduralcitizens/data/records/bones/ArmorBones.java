package com.cynic1254.proceduralcitizens.data.records.bones;

import com.google.gson.annotations.SerializedName;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public record ArmorBones(
        @SerializedName("head") List<String> headBones,
        @SerializedName("chest") List<String> chestBones,
        @SerializedName("legs") List<String> legBones,
        @SerializedName("feet") List<String> footBones
) {
    public ArmorBones {
        headBones = Objects.requireNonNullElse(headBones, List.of());
        chestBones = Objects.requireNonNullElse(chestBones, List.of());
        legBones = Objects.requireNonNullElse(legBones, List.of());
        footBones = Objects.requireNonNullElse(footBones, List.of());
    }

    public Set<String> getAllBones() {
        Set<String> all = new HashSet<>(headBones);
        all.addAll(chestBones);
        all.addAll(legBones);
        all.addAll(footBones);

        return all;
    }

    public Set<String> getBonesForSlot(EquipmentSlot slot) {
        return new HashSet<>(switch (slot) {
            case HEAD -> headBones;
            case CHEST -> chestBones;
            case LEGS -> legBones;
            case FEET -> footBones;
            default -> new HashSet<String>();
        });
    }
}
