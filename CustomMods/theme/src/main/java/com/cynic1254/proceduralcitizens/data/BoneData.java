package com.cynic1254.proceduralcitizens.data;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.slf4j.Logger;

import java.util.*;

public class BoneData {
    private static final Logger LOGGER = LogUtils.getLogger();

    private final Set<String> alwaysVisibleBones = new HashSet<>();
    private final Map<EquipmentSlot, Set<String>> armorHiddenBones = new HashMap<>();
    private final Map<ResourceLocation, Set<String>> jobHiddenBones = new HashMap<>();
    private final Map<ResourceLocation, Set<String>> jobShownBones = new HashMap<>();

    public static final BoneData EMPTY_BONE_DATA = new BoneData();

    public BoneData() {}

    /**
     * Explicit constructor to initialize BoneData directly with pre-existing collections.
     * Uses Java Streams to safely handle null values, filter keys, and create defensive copies.
     */
    public BoneData(Set<String> alwaysVisibleBones,
                    Map<EquipmentSlot, Set<String>> armorHiddenBones,
                    Map<ResourceLocation, Set<String>> jobHiddenBones,
                    Map<ResourceLocation, Set<String>> jobShownBones) {

        Optional.ofNullable(alwaysVisibleBones).ifPresent(this.alwaysVisibleBones::addAll);

        this.armorHiddenBones.putAll(safeCopyMap(armorHiddenBones));
        this.jobHiddenBones.putAll(safeCopyMap(jobHiddenBones));
        this.jobShownBones.putAll(safeCopyMap(jobShownBones));
    }

    /**
     * Constructs bone data from an encoded string.
     *
     * @param encodedString Format: alwaysVisibleBones|armorHiddenBones|jobHiddenBones|jobShownBones
     *                      - alwaysVisibleBones: boneName,boneName,...
     *                      - Map formats: Key@boneName,boneName,... separated with '#'
     */
    public BoneData(String encodedString) {
        if (encodedString == null || encodedString.isBlank()) {
            return;
        }

        // Use a literal string split instead of regex for simple characters
        String[] maps = encodedString.split("\\|", -1);

        if (maps.length != 4) {
            LOGGER.error("Incorrect number of map sections in provided encoded string. Expected 4, got: {}. String: {}", maps.length, encodedString);
            return;
        }

        parseRegularBones(maps[0]);
        parseArmorHiddenBones(maps[1]);
        parseJobBones(maps[2], jobHiddenBones);
        parseJobBones(maps[3], jobShownBones);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // 1. Serialize alwaysVisibleBones
        appendBoneSet(builder, this.alwaysVisibleBones);
        builder.append("|");

        // 2. Serialize armorHiddenBones
        boolean firstArmor = true;
        for (Map.Entry<EquipmentSlot, Set<String>> entry : this.armorHiddenBones.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            if (!firstArmor) builder.append("#");

            builder.append(entry.getKey().getName()).append("@");
            appendBoneSet(builder, entry.getValue());
            firstArmor = false;
        }
        builder.append("|");

        // 3. Serialize jobHiddenBones
        boolean firstJobHidden = true;
        for (Map.Entry<ResourceLocation, Set<String>> entry : this.jobHiddenBones.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            if (!firstJobHidden) builder.append("#");

            builder.append(entry.getKey().toString()).append("@");
            appendBoneSet(builder, entry.getValue());
            firstJobHidden = false;
        }
        builder.append("|");

        // 4. Serialize jobShownBones
        boolean firstJobShown = true;
        for (Map.Entry<ResourceLocation, Set<String>> entry : this.jobShownBones.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            if (!firstJobShown) builder.append("#");

            builder.append(entry.getKey().toString()).append("@");
            appendBoneSet(builder, entry.getValue());
            firstJobShown = false;
        }

        return builder.toString();
    }

    private static <K> Map<K, Set<String>> safeCopyMap(Map<K, Set<String>> inputMap) {
        return Optional.ofNullable(inputMap)
                .stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new HashSet<>(entry.getValue()),
                        (existing, replacement) -> existing, // Merge function to handle potential duplicate keys safely
                        HashMap::new
                ));
    }

    private static void appendBoneSet(StringBuilder builder, Set<String> bones) {
        boolean first = true;
        for (String bone : bones) {
            if (bone == null || bone.isBlank()) continue;
            if (!first) builder.append(",");

            builder.append(bone);
            first = false;
        }
    }

    private void parseRegularBones(String rawMapData) {
        if (rawMapData.isBlank()) return;

        this.alwaysVisibleBones.addAll(parseBoneSet(rawMapData));
    }

    private void parseArmorHiddenBones(String rawMapData) {
        if (rawMapData.isBlank()) return;

        for (String entry : rawMapData.split("#")) {
            if (entry.isBlank()) continue;

            String[] data = entry.split("@", 2);
            if (data.length < 2 || data[0].isBlank() || data[1].isBlank()) continue;

            EquipmentSlot slot = getSlotFromName(data[0]);
            if (slot == null || !slot.isArmor()) continue;

            Set<String> bones = parseBoneSet(data[1]);
            this.armorHiddenBones.put(slot, bones);
        }
    }

    private void parseJobBones(String rawMapData, Map<ResourceLocation, Set<String>> targetMap) {
        if (rawMapData.isBlank()) return;

        for (String entry : rawMapData.split("#")) {
            if (entry.isBlank()) continue;

            String[] data = entry.split("@", 2);
            if (data.length < 2 || data[0].isBlank() || data[1].isBlank()) continue;

            // ResourceLocation.tryParse returning null safely bypasses the strict parser exception
            ResourceLocation id = ResourceLocation.tryParse(data[0]);
            if (id == null) {
                LOGGER.error("Failed to parse ResourceLocation registry ID from bone data entry: {}", data[0]);
                continue;
            }

            Set<String> bones = parseBoneSet(data[1]);
            targetMap.put(id, bones);
        }
    }

    private static Set<String> parseBoneSet(String rawBones) {
        String[] bonesArray = rawBones.split(",");
        Set<String> set = new HashSet<>(bonesArray.length);
        Collections.addAll(set, bonesArray);
        return set;
    }

    private static EquipmentSlot getSlotFromName(String name) {
        for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            if (equipmentslot.getName().equals(name)) {
                return equipmentslot;
            }
        }

        return null;
    }

    public Set<String> getAlwaysVisibleBones() { return Collections.unmodifiableSet(alwaysVisibleBones); }
    public Map<EquipmentSlot, Set<String>> getArmorHiddenBones() { return Collections.unmodifiableMap(armorHiddenBones); }
    public Map<ResourceLocation, Set<String>> getJobHiddenBones() { return Collections.unmodifiableMap(jobHiddenBones); }
    public Map<ResourceLocation, Set<String>> getJobShownBones() { return Collections.unmodifiableMap(jobShownBones); }
}
