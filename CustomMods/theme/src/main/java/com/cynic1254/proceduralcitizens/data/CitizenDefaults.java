package com.cynic1254.proceduralcitizens.data;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class CitizenDefaults {

    public static final ResourceLocation MISSINGNO_TEXTURE = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "missingno");
    public static final ResourceLocation PLACEHOLDER_MODEL_ID = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "default");
    public static final TextureIdentifierDefinition PLACEHOLDER_TEXTURE_DEFINITION = TextureIdentifierDefinition.fromObjects(
            List.of(new TextureIdentifierDefinition.TextureIdentifierObject(MISSINGNO_TEXTURE, 0xFFFFFF, TextureIdentifierDefinition.BlendMode.NORMAL))
            );
}
