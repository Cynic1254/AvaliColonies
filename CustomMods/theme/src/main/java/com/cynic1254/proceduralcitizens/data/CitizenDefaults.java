package com.cynic1254.proceduralcitizens.data;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class CitizenDefaults {
    //Missingno texture resource location, this location should not actually exist so that minecraft returns the default "missingno" texture
    public static final ResourceLocation MISSINGNO_TEXTURE = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "missingno");

    //Default model for indicating an errored state
    public static final ResourceLocation MISSING_MODEL_ID = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "missing");
    //Empty model for if the model data isn't available immediately, use this model if we aren't errored but need a temporary placeholder model
    public static final ResourceLocation EMPTY_MODEL_ID = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "empty");

    public static final ResourceLocation DEFAULT_CITIZEN_DEFINITION_ID = ResourceLocation.fromNamespaceAndPath(ProceduralCitizens.MODID, "default");

    //Default TextureDefinition, the definition is just a missingno texture with no tint or blending applied
    public static final TextureIdentifierDefinition PLACEHOLDER_TEXTURE_DEFINITION = TextureIdentifierDefinition.fromObjects(
            List.of(new TextureIdentifierDefinition.TextureIdentifierObject(MISSINGNO_TEXTURE, 0xFFFFFF, TextureIdentifierDefinition.BlendMode.NORMAL))
            );
}
