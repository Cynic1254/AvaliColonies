package com.cynic1254.proceduralcitizens.data.records.texture;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.records.texture.color.ColorBase;
import com.cynic1254.proceduralcitizens.data.records.texture.color.SingleHex;
import com.cynic1254.proceduralcitizens.helper.WeightedRandom;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Mth;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public record OverlayGroup(
        List<WeightedTexture> textures,
        Float chance,
        @SerializedName("blend_mode") String blendMode,
        List<ColorBase> colors
) {
    public OverlayGroup {
        textures = Objects.requireNonNullElse(textures, List.of());
        chance = Mth.clamp(Objects.requireNonNullElse(chance, 1.0f), 0.0f, 1.0f);
        blendMode = Objects.requireNonNullElse(blendMode, "normal");
        blendMode = blendMode.isBlank() ? "normal" : blendMode;
        colors = Objects.requireNonNullElse(colors, List.of());
    }

    public Optional<TextureIdentifierDefinition.TextureIdentifierObject> roll(Random random) {
        if (textures.isEmpty() || random.nextFloat() > chance) {
            return Optional.empty();
        }

        ColorBase color = WeightedRandom.roll(colors, random).orElse(new SingleHex("#FFFFFF", 1.0f));
        TextureIdentifierDefinition.BlendMode parsedBlendMode = TextureIdentifierDefinition.BlendMode.fromString(blendMode);

        return Optional.of(
                new TextureIdentifierDefinition.TextureIdentifierObject(
                        WeightedRandom.roll(textures, random).orElse(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f)).texture(),
                        color.rollColor(random),
                        parsedBlendMode
                )
        );
    }
}
