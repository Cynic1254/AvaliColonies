package com.cynic1254.proceduralcitizens.data.records.texture.color;

public class ColorUtils {
    public static int parseHexColor(String hex) {
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

    public static int parseHSL(float hue, float saturation, float lightness) {
        float h = (hue % 360.0f + 360.0f) % 360.0f;
        float s = Math.max(0.0f, Math.min(1.0f, saturation));
        float l = Math.max(0.0f, Math.min(1.0f, lightness));

        float chroma = (1.0f - Math.abs(2.0f * l - 1.0f)) * s;
        float face = h / 60.0f;
        float x = chroma * (1.0f - Math.abs(face % 2.0f - 1.0f));
        float m = l - (chroma / 2.0f);

        float r = 0, g = 0, b = 0;

        switch ((int) face) {
            case 0 -> { r = chroma; g = x; }
            case 1 -> { r = x; g = chroma; }
            case 2 -> { g = chroma; b = x; }
            case 3 -> { g = x; b = chroma; }
            case 4 -> { r = x; b = chroma; }
            default -> { r = chroma; b = x; }
        }

        int red   = Math.min(255, Math.max(0, Math.round((r + m) * 255.0f)));
        int green = Math.min(255, Math.max(0, Math.round((g + m) * 255.0f)));
        int blue  = Math.min(255, Math.max(0, Math.round((b + m) * 255.0f)));

        return 0xFF000000 | (red << 16) | (green << 8) | blue;
    }

    public static int parseHSL(HSLValue hsl) {
        return parseHSL(hsl.hue(), hsl.saturation(), hsl.lightness());
    }
}
