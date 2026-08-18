package com.cynic1254.proceduralcitizens.data.records;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition.TextureIdentifierObject;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition.BlendMode;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

public record GeoCitizenDefinition(
        ResourceLocation model,
        List<AttachmentGroup> attachments,
        TexturePipeline texture
) {
    public GeoCitizenDefinition {
        if (model == null) {
            model = CitizenDefaults.MISSING_MODEL_ID;
        }
        if (attachments == null) {
            attachments = List.of(); // Empty list instead of null
        }
        if (texture == null) {
            texture = new TexturePipeline(
                    new BaseTexture(List.of(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f)), List.of()), List.of()
            );
        }
    }

    public Map<String, ResourceLocation> rollAttachments(Random random) {
        Map<String, ResourceLocation> out = new HashMap<>();

        for (var attachment : attachments) {
            out.putAll(attachment.roll(random));
        }

        return out;
    }

    public TextureIdentifierDefinition rollTextureDefinition(Random random) {
        List<TextureIdentifierObject> objects = texture.rollPipeline(random);
        return TextureIdentifierDefinition.fromObjects(objects);
    }


    public record AttachmentGroup(
            // Can be parsed from either a String or List<String>
            List<String> bone,
            float chance,
            List<AttachmentMesh> meshes
    ) {
        public AttachmentGroup {
            if (bone == null) bone = List.of();
            if (chance <= 0.0f) chance = 1.0f; // Default 100% chance if missing
            if (meshes == null) meshes = List.of();
            if (bone.isEmpty() || meshes.isEmpty()) chance = 0.0f;
        }

        public Map<String, ResourceLocation> roll(Random randomSource) {
            Map<String, ResourceLocation> out = new HashMap<>();

            for (var boneName : bone) {
                var attachment = rollAttachmentMesh(randomSource);
                attachment.ifPresent(resourceLocation -> out.put(boneName, resourceLocation));
            }

            return out;
        }

        public Optional<ResourceLocation> rollAttachmentMesh(Random random) {
            if (meshes.isEmpty() || random.nextFloat() > chance) {
                return Optional.empty();
            }

            float totalWeight = 0.0f;
            for (AttachmentMesh mesh : meshes) {
                totalWeight += mesh.weight();
            }

            if (totalWeight <= 0.0f) {
                return Optional.empty();
            }

            float roll = random.nextFloat() * totalWeight;
            float cumulative = 0.0f;

            for (AttachmentMesh mesh : meshes) {
                cumulative += mesh.weight();
                if (roll <= cumulative) {
                    return Optional.of(mesh.mesh());
                }
            }

            return Optional.of(meshes.get(0).mesh());
        }
    }

    public record AttachmentMesh(
            ResourceLocation mesh,
            float weight
    ) {
        public AttachmentMesh {
            if (mesh == null)
                mesh = CitizenDefaults.MISSING_MODEL_ID;
            if (weight <= 0.0f) weight = 1.0f;
        }
    }

    public record TexturePipeline(
            BaseTexture base,
            List<OverlayGroup> overlays
    ) {
        public TexturePipeline {
            if (base == null) {
                base = new BaseTexture(List.of(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f)), List.of());
            }
            if (overlays == null) overlays = List.of();
        }

        public List<TextureIdentifierObject> rollPipeline(Random random) {
            List<TextureIdentifierObject> layers = new ArrayList<>();

            // 1. Roll Base Layer
            layers.add(base.roll(random));

            // 2. Roll Overlay Layers
            for (OverlayGroup overlay : overlays) {
                overlay.roll(random).ifPresent(layers::add);
            }

            return layers;
        }
    }

    //TODO: textures should also accept a list of textures, similarly to OverlayGroup
    public record BaseTexture(
            List<WeightedTexture> textures,
            List<ColorEntry> colors
    ) {
        public BaseTexture {
            if (textures.isEmpty())
                textures.add(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f));
            if (colors == null) colors = List.of();
        }

        public TextureIdentifierObject roll(Random random) {
            int rolledColor = ColorEntry.rollColorList(colors, random);
            return new TextureIdentifierObject(WeightedTexture.roll(textures, random), rolledColor, BlendMode.NORMAL);
        }
    }

    public record OverlayGroup(
            // Flexible textures list (handles string, string[], or object[])
            List<WeightedTexture> textures,
            float chance,
            @SerializedName("blend_mode") String blendMode,
            List<ColorEntry> colors
    ) {
        public OverlayGroup {
            if (textures == null) textures = List.of();
            if (chance <= 0.0f) chance = 1.0f;
            if (blendMode == null || blendMode.isBlank()) blendMode = "normal";
            if (colors == null) colors = List.of();
        }

        public Optional<TextureIdentifierObject> roll(Random random) {
            if (textures.isEmpty() || random.nextFloat() > chance) {
                return Optional.empty();
            }

            int rolledColor = ColorEntry.rollColorList(colors, random);
            BlendMode parsedBlendMode = BlendMode.fromString(blendMode);

            return Optional.of(new TextureIdentifierObject(WeightedTexture.roll(textures, random), rolledColor, parsedBlendMode));
        }
    }

    public record WeightedTexture(
            ResourceLocation texture,
            float weight
    ) {
        public WeightedTexture {
            if (texture == null)
                texture = CitizenDefaults.MISSINGNO_TEXTURE;
            if (weight <= 0.0f) weight = 1.0f;
        }

        public static ResourceLocation roll(List<WeightedTexture> textures, Random random) {
            // Weighted Texture Selection
            float totalWeight = 0.0f;
            for (WeightedTexture wt : textures) {
                totalWeight += wt.weight();
            }

            ResourceLocation selectedTexture = null;
            if (totalWeight > 0.0f) {
                float roll = random.nextFloat() * totalWeight;
                float cumulative = 0.0f;
                for (WeightedTexture wt : textures) {
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
            String color // Exact hex if not a min/max range
    ) {
        public ColorEntry {
            if (color == null && min == null && max == null) {
                color = "#FFFFFF"; // Default to white tint if omitted
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

        public static int rollColorList(List<ColorEntry> colors, Random random) {
            if (colors.isEmpty()) {
                return 0xFFFFFFFF;
            }
            ColorEntry chosen = colors.get(random.nextInt(colors.size()));
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
}