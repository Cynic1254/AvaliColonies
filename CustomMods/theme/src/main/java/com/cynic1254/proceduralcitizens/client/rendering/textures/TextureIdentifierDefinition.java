package com.cynic1254.proceduralcitizens.client.rendering.textures;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record TextureIdentifierDefinition(String textureID) {
    public List<TextureIdentifierObject> toObjects() {
        if (textureID == null || textureID.isBlank()) {
            return List.of();
        }

        return Arrays.stream(textureID.split("\\|"))
                .filter(s -> !s.isBlank())
                .map(TextureIdentifierObject::new)
                .toList();
    }

    public static TextureIdentifierDefinition fromObjects(List<TextureIdentifierObject> objects) {
        if (objects == null || objects.isEmpty()) {
            return new TextureIdentifierDefinition("");
        }

        String joined = objects.stream()
                .map(TextureIdentifierObject::toString)
                .collect(Collectors.joining("|"));

        return new TextureIdentifierDefinition(joined);
    }

    public enum BlendMode {
        NORMAL,
        MULTIPLY,
        ADD,
        OVERLAY;

        public static BlendMode fromString(String raw) {
            if (raw == null || raw.isBlank()) {
                return NORMAL;
            }

            try {
                return valueOf(raw.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return NORMAL;
            }
        }
    }

    /// Format: [identifier]#[color]$[blendMode]
    /// The first entry is always the base layer. Separated by '|'.
    /// color will be parsed as an RGB value, the Alpha channel will be discarded/set to FF
    public record TextureIdentifierObject(ResourceLocation identifier, int color, BlendMode blendMode) {

        TextureIdentifierObject(String encodedString) {
            this(parseIdentifier(encodedString), parseColor(encodedString), parseBlendMode(encodedString));
        }

        @Override
        public @NotNull String toString() {
            String hexColor = String.format("%06X", color & 0xFFFFFFL);
            return identifier.toString() + "#" + hexColor + "$" + blendMode.name().toLowerCase();
        }

        // --- Parsing methods ---
        private static ResourceLocation parseIdentifier(String input) {
            if (input == null || input.isEmpty()) {
                return ResourceLocation.fromNamespaceAndPath("minecraft", "missingno");
            }

            String rawPath = input.split("#")[0];
            ResourceLocation resource = ResourceLocation.tryParse(rawPath);

            return resource != null ? resource : ResourceLocation.fromNamespaceAndPath("minecraft", "missingno");
        }

        private static int parseColor(String input) {
            if (input == null || !input.contains("#")) {
                return 0xFFFFFFFF;
            }
            try {
                String hex = input.substring(input.indexOf("#") + 1).split("\\$")[0];
                long parsed = Long.parseLong(hex, 16);
                parsed |= 0xFF000000L;
                return (int) parsed;
            } catch (NumberFormatException e) {
                return 0xFFFFFFFF;
            }
        }

        private static BlendMode parseBlendMode(String input) {
            if (input == null || !input.contains("$")) {
                return BlendMode.NORMAL;
            }
            String[] parts = input.split("\\$");
            return parts.length > 1 ? BlendMode.fromString(parts[1]) : BlendMode.NORMAL;
        }
    }
}
