package com.cynic1254.avalitheme.data.records;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record GeoCitizenDefinition(
        ResourceLocation modelData,
        List<AttachmentGroup> attachments,
        TexturePipeline texture
) {
    public record AttachmentGroup(
            // Can be parsed from either a String or List<String>
            List<String> bone,
            float chance,
            List<AttachmentMesh> meshes
    ) {}

    public record AttachmentMesh(
            ResourceLocation mesh,
            float weight
    ) {}

    public record TexturePipeline(
            BaseTexture base,
            List<OverlayGroup> overlays
    ) {}

    public record BaseTexture(
            ResourceLocation texture,
            List<ColorEntry> colors
    ) {}

    public record OverlayGroup(
            // Flexible textures list (handles string, string[], or object[])
            List<WeightedTexture> textures,
            float chance,
            @SerializedName("blend_mode") String blendMode,
            List<ColorEntry> colors
    ) {}

    public record WeightedTexture(
            ResourceLocation texture,
            float weight
    ) {}

    public record ColorEntry(
            String min,
            String max,
            String color // Exact hex if not a min/max range
    ) {}
}