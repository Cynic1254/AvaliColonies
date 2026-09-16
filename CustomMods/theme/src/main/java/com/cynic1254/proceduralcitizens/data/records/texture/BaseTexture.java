package com.cynic1254.proceduralcitizens.data.records.texture;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.records.texture.color.ColorBase;
import com.cynic1254.proceduralcitizens.data.records.texture.color.SingleHex;
import com.cynic1254.proceduralcitizens.helper.WeightedRandom;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public record BaseTexture(
        List<WeightedTexture> textures,
        List<ColorBase> colors
) {
    public BaseTexture {
        textures = Optional.ofNullable(textures)
                .filter(l -> !l.isEmpty())
                .orElse(List.of(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f)));

        colors = Optional.ofNullable(colors)
                .filter(l -> !l.isEmpty())
                .orElse(List.of(new SingleHex("#FFFFFF", 1.0f)));
    }

    public TextureIdentifierDefinition.TextureIdentifierObject roll(Random random) {
        ColorBase color = WeightedRandom.roll(colors, random).orElse(new SingleHex("#FFFFFF", 1.0f));
        return new TextureIdentifierDefinition.TextureIdentifierObject(
                WeightedRandom.roll(textures, random).orElse(new WeightedTexture(CitizenDefaults.MISSINGNO_TEXTURE, 1.0f)).texture(),
                color.rollColor(random),
                TextureIdentifierDefinition.BlendMode.NORMAL
        );
    }
}
