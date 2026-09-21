package com.cynic1254.proceduralcitizens.data.records;

import com.cynic1254.proceduralcitizens.helper.EquipmentSlotHelper;
import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.*;

public record VisibilityRules(
        @SerializedName("hide_on_armor_slots") List<String> hideOnArmorSlots,
        @SerializedName("hide_on_jobs") List<String> hideOnJobs,
        @SerializedName("show_on_jobs") List<String> showOnJobs
) {
    public VisibilityRules {
        hideOnArmorSlots = Objects.requireNonNullElse(hideOnArmorSlots, List.of());
        hideOnJobs = Objects.requireNonNullElse(hideOnJobs, List.of());
        showOnJobs = Objects.requireNonNullElse(showOnJobs, List.of());
    }

    public static VisibilityRules merge(VisibilityRules parent, VisibilityRules child) {
        return new VisibilityRules(
                combineLists(parent.hideOnArmorSlots(), child.hideOnArmorSlots()),
                combineLists(parent.hideOnJobs(), child.hideOnJobs()),
                combineLists(parent.showOnJobs(), child.showOnJobs())
        );
    }

    private static <T> List<T> combineLists(List<T> first, List<T> second) {
        List<T> combined = new ArrayList<>(first.size() + second.size());
        combined.addAll(first);
        combined.addAll(second);
        return combined;
    }

    public void applyToBoneData(
            List<String> bones,
            Set<String> alwaysVisible,
            Map<EquipmentSlot, Set<String>> armorHidden,
            Map<ResourceLocation, Set<String>> jobHidden,
            Map<ResourceLocation, Set<String>> jobShown
    ) {
        // Apply Base Visibility
        if (showOnJobs.isEmpty() && hideOnArmorSlots.isEmpty() && hideOnJobs.isEmpty()) {
            alwaysVisible.addAll(bones);
        }

        for (String job : showOnJobs) {
            jobShown.computeIfAbsent(ResourceLocation.tryParse(job), k -> new HashSet<>()).addAll(bones);
        }

        // Apply Armor Hiding Rules
        for (String slotName : hideOnArmorSlots) {
            armorHidden.computeIfAbsent(EquipmentSlotHelper.getSlotFromName(slotName), k -> new HashSet<>()).addAll(bones);
        }

        // Apply Job Hiding Rules
        for (String job : hideOnJobs) {
            jobHidden.computeIfAbsent(ResourceLocation.tryParse(job), k -> new HashSet<>()).addAll(bones);
        }
    }
}
