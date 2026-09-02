package com.cynic1254.proceduralcitizens.data.records;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.BoneData;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.ResourcePathResolver;
import com.google.gson.annotations.SerializedName;
import com.minecolonies.api.colony.jobs.IJob;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public record CitizenDefinition(
        ResourceLocation model,
        BoneDefinitions bones,
        TextureDefinitions textures,
        List<AttachmentGroup> attachments
) {
    public CitizenDefinition {
        if (model == null) model = CitizenDefaults.MISSING_MODEL_ID;
        if (bones == null) bones = new BoneDefinitions(null, null, null, null);
        if (textures == null) textures = new TextureDefinitions(null, null, null, null);
        if (attachments == null) attachments = List.of();
    }

    public ResourceLocation getClothingTextureForJob(IJob<?> job) {
        ResourceLocation textureLocation = ResourcePathResolver.GetClothingTextureFolderPath(textures.clothingOverridePath == null ? model : textures.clothingOverridePath);
        ResourceLocation fallbackLocation = ResourceLocation.fromNamespaceAndPath(textureLocation.getNamespace(), textureLocation.getPath() + "default.png");
        fallbackLocation = Minecraft.getInstance().getResourceManager().getResource(fallbackLocation).isPresent() ? fallbackLocation : null;

        if (job == null) {
            return fallbackLocation;
        }

        String relativePath = job.getJobRegistryEntry().getKey().toString().replace(':', '_') + ".png";
        ResourceLocation finalLocation = ResourceLocation.fromNamespaceAndPath(textureLocation.getNamespace(), textureLocation.getPath() + relativePath);

        boolean textureExists = Minecraft.getInstance().getResourceManager().getResource(finalLocation).isPresent();

        return textureExists ? finalLocation : fallbackLocation;
    }

    public TextureIdentifierDefinition rollTextureDefinition(Random random) {
        List<TextureIdentifierDefinition.TextureIdentifierObject> objects = textures.roll(random);
        return TextureIdentifierDefinition.fromObjects(objects);
    }

    public BoneData rollAttachments(Random random) {
        Set<String> alwaysVisible = new HashSet<>();
        Map<EquipmentSlot, Set<String>> armorHidden = new HashMap<>();
        Map<ResourceLocation, Set<String>> jobHidden = new HashMap<>();
        Map<ResourceLocation, Set<String>> jobShown = new HashMap<>();

        for (AttachmentGroup group : attachments) {
            List<AttachmentOption> selectedOptions = group.roll(random);

            for (AttachmentOption option : selectedOptions) {
                if (option.bones().isEmpty()) {
                    continue;
                }

                // Merge rules from both group and option levels
                VisibilityRules mergedRules = VisibilityRules.merge(group.visibilityRules(), option.visibilityRules());

                // Route visibility states based on merged rules
                mergedRules.applyToBoneData(option.bones(), alwaysVisible, armorHidden, jobHidden, jobShown);
            }
        }

        return new BoneData(alwaysVisible, armorHidden, jobHidden, jobShown);
    }

    public Set<String> getAllAttachmentBones() {
        Set<String> allBones = new HashSet<>();
        for (AttachmentGroup group : attachments) {
            for (AttachmentOption option : group.options()) {
                allBones.addAll(option.bones());
            }
        }
        return allBones;
    }

    public record BoneDefinitions(
            String head,
            @SerializedName("left_hand") String leftHand,
            @SerializedName("right_hand") String rightHand,
            ArmorBones armor
    ) {
        public BoneDefinitions {
            if (head == null) head = "b_head";
            if (leftHand == null) leftHand = "b_left_hand";
            if (rightHand == null) rightHand = "b_right_hand";
            if (armor == null) armor = new ArmorBones(null, null, null, null);
        }
    }

    public record ArmorBones(
            @SerializedName("head") List<String> headBones,
            @SerializedName("chest") List<String> chestBones,
            @SerializedName("legs") List<String> legBones,
            @SerializedName("feet") List<String> footBones
    ) {
        public ArmorBones {
            if (headBones == null) headBones = List.of();
            if (chestBones == null) chestBones = List.of();
            if (legBones == null) legBones = List.of();
            if (footBones == null) footBones = List.of();
        }

        public Set<String> getAllBones() {
            Set<String> allBones = new HashSet<>();
            allBones.addAll(headBones);
            allBones.addAll(chestBones);
            allBones.addAll(legBones);
            allBones.addAll(footBones);

            return allBones;
        }

        public Set<String> getBonesForSlot(EquipmentSlot slot) {
            Set<String> bones = new HashSet<>();

            switch (slot) {
                case HEAD -> bones.addAll(headBones);
                case CHEST -> bones.addAll(chestBones);
                case LEGS -> bones.addAll(legBones);
                case FEET -> bones.addAll(footBones);
            }

            return bones;
        }
    }

    public record TextureDefinitions(
            @SerializedName("armor_override_path") ResourceLocation armorOverridePath,
            @SerializedName("clothing_override_path") ResourceLocation clothingOverridePath,
            BaseTexture base,
            List<OverlayGroup> overlays
    ) {
        public TextureDefinitions {
            if (base == null) base = new BaseTexture(List.of(), List.of());
            if (overlays == null) overlays = List.of();
        }

        public ResourceLocation getTextureForMaterialAndSlot(ItemStack stack) {
            if (!(stack.getItem() instanceof ArmorItem armorItem)) {
                // Default fallback texture if stack isn't valid armor
                return CitizenDefaults.MISSINGNO_TEXTURE;
            }

            return getTextureForMaterialAndSlot(armorItem.getMaterial(), armorItem.getEquipmentSlot());
        }

        public ResourceLocation getTextureForMaterialAndSlot(ArmorMaterial material, EquipmentSlot slot) {
            int layer = (slot == EquipmentSlot.LEGS) ? 2 : 1;
            String materialName = material.getName();

            if (armorOverridePath != null) {
                materialName = materialName.replace(':', '_');
                return ResourceLocation.fromNamespaceAndPath(
                        armorOverridePath.getNamespace(),
                        armorOverridePath.getPath() + "/" + materialName + "_layer_" + layer + ".png"
                );
            }

            if (materialName.contains(":")) {
                String[] parts = materialName.split(":", 2);
                return ResourceLocation.fromNamespaceAndPath(
                        parts[0],
                        "textures/models/armor/" + parts[1] + "_layer_" + layer + ".png"
                );
            }

            return ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/models/armor/" + materialName + "_layer_" + layer + ".png"
            );
        }

        public List<TextureIdentifierDefinition.TextureIdentifierObject> roll(Random random) {
            List<TextureIdentifierDefinition.TextureIdentifierObject> layers = new ArrayList<>();

            // 1. Roll Base Layer
            layers.add(base.roll(random));

            // 2. Roll Overlay Layers
            for (CitizenDefinition.OverlayGroup overlay : overlays) {
                overlay.roll(random).ifPresent(layers::add);
            }

            return layers;
        }
    }

    public record BaseTexture(
            List<WeightedTexture> textures,
            List<ColorEntry> colors
    ) {
        public BaseTexture {
            if (textures == null || textures.isEmpty()) {
                textures = List.of(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f));
            }
            if (colors == null) colors = List.of();
        }

        public TextureIdentifierDefinition.TextureIdentifierObject roll(Random random) {
            int rolledColor = CitizenDefinition.ColorEntry.rollColorList(colors, random);
            return new TextureIdentifierDefinition.TextureIdentifierObject(CitizenDefinition.WeightedTexture.roll(textures, random), rolledColor, TextureIdentifierDefinition.BlendMode.NORMAL);
        }
    }

    public record OverlayGroup(
            List<WeightedTexture> textures,
            Float chance,
            @SerializedName("blend_mode") String blendMode,
            List<ColorEntry> colors
    ) {
        public OverlayGroup {
            if (textures == null) textures = List.of();
            if (chance == null) chance = 1.0f;
            if (chance < 0.0f) chance = 0.0f;
            if (chance > 1.0f) chance = 1.0f;
            if (blendMode == null || blendMode.isBlank()) blendMode = "normal";
            if (colors == null) colors = List.of();
        }

        public Optional<TextureIdentifierDefinition.TextureIdentifierObject> roll(Random random) {
            if (textures.isEmpty() || random.nextFloat() > chance) {
                return Optional.empty();
            }

            int rolledColor = CitizenDefinition.ColorEntry.rollColorList(colors, random);
            TextureIdentifierDefinition.BlendMode parsedBlendMode = TextureIdentifierDefinition.BlendMode.fromString(blendMode);

            return Optional.of(new TextureIdentifierDefinition.TextureIdentifierObject(CitizenDefinition.WeightedTexture.roll(textures, random), rolledColor, parsedBlendMode));
        }
    }

    public record WeightedTexture(
            ResourceLocation texture,
            Float weight
    ) {
        public WeightedTexture {
            if (texture == null) texture = CitizenDefaults.MISSINGNO_TEXTURE;
            if (weight == null || weight <= 0.0f) weight = 1.0f;
        }

        public static ResourceLocation roll(List<CitizenDefinition.WeightedTexture> textures, Random random) {
            // Weighted Texture Selection
            float totalWeight = 0.0f;
            for (CitizenDefinition.WeightedTexture wt : textures) {
                totalWeight += wt.weight();
            }

            ResourceLocation selectedTexture = null;
            if (totalWeight > 0.0f) {
                float roll = random.nextFloat() * totalWeight;
                float cumulative = 0.0f;
                for (CitizenDefinition.WeightedTexture wt : textures) {
                    cumulative += wt.weight();
                    if (roll <= cumulative) {
                        selectedTexture = wt.texture();
                        break;
                    }
                }
            }

            if (selectedTexture == null) {
                selectedTexture = textures.get(0).texture();
            }
            return selectedTexture;
        }
    }

    public record ColorEntry(
            String min,
            String max,
            String color
    ) {
        public ColorEntry {
            if (color == null && min == null && max == null) {
                color = "#FFFFFF";
            }
        }

        public int roll(Random random) {
            if (color != null && !color.isBlank()) {
                return parseHexColor(color);
            }

            int minColor = parseHexColor(min != null ? min : "#FFFFFF");
            int maxColor = parseHexColor(max != null ? max : "#FFFFFF");

            int r = lerpChannel((minColor >> 16) & 0xFF, (maxColor >> 16) & 0xFF, random.nextFloat());
            int g = lerpChannel((minColor >> 8) & 0xFF, (maxColor >> 8) & 0xFF, random.nextFloat());
            int b = lerpChannel(minColor & 0xFF, maxColor & 0xFF, random.nextFloat());

            return 0xFF000000 | (r << 16) | (g << 8) | b;
        }

        public static int rollColorList(List<CitizenDefinition.ColorEntry> colors, Random random) {
            if (colors.isEmpty()) {
                return 0xFFFFFFFF;
            }
            CitizenDefinition.ColorEntry chosen = colors.get(random.nextInt(colors.size()));
            return chosen.roll(random);
        }

        private static int lerpChannel(int min, int max, float delta) {
            return Math.min(255, Math.max(0, Math.round(min + delta * (max - min))));
        }

        private static int parseHexColor(String hex) {
            if (hex == null || hex.isBlank()) {
                return 0xFFFFFFFF;
            }
            try {
                String cleanHex = hex.startsWith("#") ? hex.substring(1) : hex;
                long parsed = Long.parseLong(cleanHex, 16);
                parsed |= 0xFF000000L;
                return (int) parsed;
            } catch (NumberFormatException e) {
                return 0xFFFFFFFF;
            }
        }
    }

    public record AttachmentGroup(
            String name,
            Float chance,
            Boolean exclusive,
            @SerializedName("visibility_rules") VisibilityRules visibilityRules,
            List<AttachmentOption> options
    ) {
        public AttachmentGroup {
            if (name == null) name = "unnamed_group";
            if (chance == null) chance = 1.0f;
            if (chance < 0.0f) chance = 0.0f;
            if (chance > 1.0f) chance = 1.0f;
            if (exclusive == null) exclusive = true;
            if (visibilityRules == null) visibilityRules = new VisibilityRules(null, null, null);
            if (options == null) options = List.of();
        }

        public List<AttachmentOption> roll(Random random) {
            if (options.isEmpty() || (chance < 1.0f && random.nextFloat() > chance)) {
                return List.of();
            }

            if (!exclusive) {
                return options;
            }

            return AttachmentOption.rollWeighted(options, random)
                    .map(List::of)
                    .orElseGet(List::of);
        }
    }

    public record AttachmentOption(
            String name,
            @SerializedName("bone") List<String> bones,
            Float weight,
            @SerializedName("visibility_rules") VisibilityRules visibilityRules
    ) {
        public AttachmentOption {
            if (name == null) name = "unnamed_option";
            if (bones == null) bones = List.of();
            if (weight == null || weight <= 0.0f) weight = 1.0f;
            if (visibilityRules == null) visibilityRules = new VisibilityRules(null, null, null);
        }

        public static Optional<AttachmentOption> rollWeighted(List<AttachmentOption> options, Random random) {
            if (options.isEmpty()) return Optional.empty();

            float totalWeight = 0.0f;
            for (AttachmentOption opt : options) {
                totalWeight += opt.weight();
            }

            if (totalWeight <= 0.0f) {
                return Optional.of(options.get(0));
            }

            float roll = random.nextFloat() * totalWeight;
            float cumulative = 0.0f;

            for (AttachmentOption opt : options) {
                cumulative += opt.weight();
                if (roll <= cumulative) {
                    return Optional.of(opt);
                }
            }

            return Optional.of(options.get(0));
        }
    }

    public record VisibilityRules(
            @SerializedName("hide_on_armor_slots") List<String> hideOnArmorSlots,
            @SerializedName("hide_on_jobs") List<String> hideOnJobs,
            @SerializedName("show_on_jobs") List<String> showOnJobs
    ) {
        public VisibilityRules {
            if (hideOnArmorSlots == null) hideOnArmorSlots = List.of();
            if (hideOnJobs == null) hideOnJobs = List.of();
            if (showOnJobs == null) showOnJobs = List.of();
        }

        public static VisibilityRules merge(VisibilityRules parent, VisibilityRules child) {
            List<String> armor = new ArrayList<>(parent.hideOnArmorSlots());
            armor.addAll(child.hideOnArmorSlots());

            List<String> hideJob = new ArrayList<>(parent.hideOnJobs());
            hideJob.addAll(child.hideOnJobs());

            List<String> showJob = new ArrayList<>(parent.showOnJobs());
            showJob.addAll(child.showOnJobs());

            return new VisibilityRules(armor, hideJob, showJob);
        }

        public void applyToBoneData(
                List<String> bones,
                Set<String> alwaysVisible,
                Map<EquipmentSlot, Set<String>> armorHidden,
                Map<ResourceLocation, Set<String>> jobHidden,
                Map<ResourceLocation, Set<String>> jobShown
        ) {
            // Apply Base Visibility
            if (showOnJobs.isEmpty()) {
                alwaysVisible.addAll(bones);
            } else {
                for (String job : showOnJobs) {
                    ResourceLocation loc = ResourceLocation.tryParse(job);
                    if (loc != null) {
                        jobShown.computeIfAbsent(loc, k -> new HashSet<>()).addAll(bones);
                    }
                }
            }

            // Apply Armor Hiding Rules
            for (String slotName : hideOnArmorSlots) {
                EquipmentSlot slot = getSlotFromName(slotName);
                if (slot != null) {
                    armorHidden.computeIfAbsent(slot, k -> new HashSet<>()).addAll(bones);
                }
            }

            // Apply Job Hiding Rules
            for (String job : hideOnJobs) {
                ResourceLocation loc = ResourceLocation.tryParse(job);
                if (loc != null) {
                    jobHidden.computeIfAbsent(loc, k -> new HashSet<>()).addAll(bones);
                }
            }
        }

        private static EquipmentSlot getSlotFromName(String name) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getName().equals(name)) {
                    return slot;
                }
            }
            return null;
        }
    }
}
