package com.cynic1254.proceduralcitizens.client.rendering.textures;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition.BlendMode;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition.TextureIdentifierObject;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.FastColor;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TextureManager {
    private static final Map<TextureIdentifierDefinition, ResourceLocation> textureCache = new HashMap<>();
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation defaultResource = ResourceLocation.fromNamespaceAndPath("minecraft", "missingno");

    public static ResourceLocation getTextureResource(AbstractEntityCitizen citizen) {
        var trueCitizen = (AbstractEntityCitizen & GeoAbstractEntityCitizen) citizen;
        TextureIdentifierDefinition textureID = trueCitizen.getTextureID();

        if (textureID == null) {
            return defaultResource;
        }

        return textureCache.computeIfAbsent(textureID, TextureManager::computeTextureResource);
    }

    public static void clearTextureCache() {
        textureCache.values().forEach(location ->
                Minecraft.getInstance().getTextureManager().release(location)
        );
        textureCache.clear();
    }

    private static ResourceLocation computeTextureResource(TextureIdentifierDefinition definition) {
        NativeImage bakedImage = bakeTexture(definition);
        DynamicTexture dynamicTexture = new DynamicTexture(bakedImage);

        ResourceLocation dynamicLocation = ResourceLocation.fromNamespaceAndPath(
                ProceduralCitizens.MODID,
                "procedural/citizen/" + Math.abs(definition.hashCode())
        );

        Minecraft.getInstance().getTextureManager().register(dynamicLocation, dynamicTexture);
        return dynamicLocation;
    }

    private static NativeImage bakeTexture(TextureIdentifierDefinition definition) {
        List<TextureIdentifierObject> layers = definition.toObjects();

        if (layers.isEmpty()) {
            return createFallbackTexture();
        }

        // Base Layer
        TextureIdentifierObject baseLayer = layers.get(0);
        ResourceLocation basePath = resolveTexturePath(baseLayer.identifier());
        NativeImage canvas = loadNativeImage(basePath);

        if (canvas == null) {
            LOGGER.error("Failed to load base texture: {}. Returning missing texture fallback.", basePath);
            return createFallbackTexture();
        }

        if (baseLayer.color() != 0xFFFFFFFF) {
            tintNativeImage(canvas, baseLayer.color());
        }

        // Overlay Layers (skip base layer index 0)
        for (int i = 1; i < layers.size(); i++) {
            TextureIdentifierObject overlayLayer = layers.get(i);
            ResourceLocation overlayPath = resolveTexturePath(overlayLayer.identifier());
            NativeImage overlayImage = loadNativeImage(overlayPath);

            if (overlayImage == null) {
                LOGGER.warn("Could not load overlay texture: {}, skipping layer.", overlayPath);
                continue;
            }

            compositeLayer(canvas, overlayImage, overlayLayer.color(), overlayLayer.blendMode());

            // Clean up off-heap STBImage buffer immediately
            overlayImage.close();
        }

        return canvas;
    }

    // --- Helper Compositing Pipeline Methods ---

    private static void compositeLayer(NativeImage canvas, NativeImage overlay, int tintColor, BlendMode blendMode) {
        int width = Math.min(canvas.getWidth(), overlay.getWidth());
        int height = Math.min(canvas.getHeight(), overlay.getHeight());

        boolean isNormalMode = blendMode == BlendMode.NORMAL;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int fgPixel = overlay.getPixelRGBA(x, y);
                int fgAlpha = FastColor.ABGR32.alpha(fgPixel);

                if (fgAlpha == 0) {
                    continue; // Skip invisible overlay pixels
                }

                // Apply tint if needed
                if (tintColor != 0xFFFFFFFF) {
                    fgPixel = multiplyColors(fgPixel, tintColor);
                    fgAlpha = FastColor.ABGR32.alpha(fgPixel);
                }

                // Optimization: Delegate directly to NativeImage's built-in blendPixel for Normal mode
                if (isNormalMode) {
                    canvas.blendPixel(x, y, fgPixel);
                } else {
                    int bgPixel = canvas.getPixelRGBA(x, y);
                    int blendedPixel = blendCustomPixels(bgPixel, fgPixel, blendMode, fgAlpha);
                    canvas.setPixelRGBA(x, y, blendedPixel);
                }
            }
        }
    }

    private static int blendCustomPixels(int bg, int fg, BlendMode blendMode, int fgAlpha) {
        int bgAlpha = FastColor.ABGR32.alpha(bg);

        int rBg = FastColor.ABGR32.red(bg);
        int gBg = FastColor.ABGR32.green(bg);
        int bBg = FastColor.ABGR32.blue(bg);

        int rFg = FastColor.ABGR32.red(fg);
        int gFg = FastColor.ABGR32.green(fg);
        int bFg = FastColor.ABGR32.blue(fg);

        // Custom channel mode math
        int rBlended = blendChannel(rBg, rFg, blendMode);
        int gBlended = blendChannel(gBg, gFg, blendMode);
        int bBlended = blendChannel(bBg, bFg, blendMode);

        // Alpha Compositing
        float alpha = fgAlpha / 255.0f;
        int finalR = (int) (rBlended * alpha + rBg * (1.0f - alpha));
        int finalG = (int) (gBlended * alpha + gBg * (1.0f - alpha));
        int finalB = (int) (bBlended * alpha + bBg * (1.0f - alpha));
        int finalA = Math.max(bgAlpha, fgAlpha);

        return FastColor.ABGR32.color(finalA, finalB, finalG, finalR);
    }

    /**
     * Tints an entire NativeImage in a single contiguous memory pass using applyToAllPixels.
     */
    private static void tintNativeImage(NativeImage image, int tintColor) {
        image.applyToAllPixels(pixel -> multiplyColors(pixel, tintColor));
    }

    private static int blendChannel(int bg, int fg, BlendMode blendMode) {
        return switch (blendMode) {
            case MULTIPLY -> (bg * fg) / 255;
            case ADD -> Math.min(255, bg + fg);
            case OVERLAY -> (bg < 128)
                    ? (2 * bg * fg) / 255
                    : 255 - (2 * (255 - bg) * (255 - fg)) / 255;
            case NORMAL -> fg;
        };
    }

    private static int multiplyColors(int pixel, int color) {
        int a = FastColor.ABGR32.alpha(pixel);
        int b = FastColor.ABGR32.blue(pixel);
        int g = FastColor.ABGR32.green(pixel);
        int r = FastColor.ABGR32.red(pixel);

        int cA = FastColor.ABGR32.alpha(color);
        int cR = FastColor.ABGR32.red(color);
        int cG = FastColor.ABGR32.green(color);
        int cB = FastColor.ABGR32.blue(color);

        int outR = (r * cR) / 255;
        int outG = (g * cG) / 255;
        int outB = (b * cB) / 255;
        int outA = (a * cA) / 255;

        return FastColor.ABGR32.color(outA, outB, outG, outR);
    }

    private static ResourceLocation resolveTexturePath(ResourceLocation id) {
        if (id.getPath().startsWith("textures/")) {
            return id;
        }
        return ResourceLocation.fromNamespaceAndPath(
                id.getNamespace(),
                "textures/citizen/" + id.getPath() + ".png"
        );
    }

    private static NativeImage loadNativeImage(ResourceLocation location) {
        try {
            Optional<Resource> resource = Minecraft.getInstance().getResourceManager().getResource(location);
            if (resource.isPresent()) {
                try (InputStream is = resource.get().open()) {
                    return NativeImage.read(is);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load NativeImage from asset path: {}", location, e);
        }
        return null;
    }

    private static NativeImage createFallbackTexture() {
        NativeImage fallback = new NativeImage(64, 64, true);
        // Fill checkerboard pattern using FastColor
        for (int y = 0; y < fallback.getHeight(); y++) {
            for (int x = 0; x < fallback.getWidth(); x++) {
                boolean magenta = ((x / 8) + (y / 8)) % 2 == 0;
                int color = magenta
                        ? FastColor.ABGR32.color(255, 255, 0, 255)
                        : FastColor.ABGR32.color(255, 0, 0, 0);
                fallback.setPixelRGBA(x, y, color);
            }
        }
        return fallback;
    }
}