package com.cynic1254.avalitheme.data;

import com.cynic1254.avalitheme.AvaliTheme;
import com.cynic1254.avalitheme.client.rendering.textures.TextureIdentifierDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class CitizenDefaults {

    public static final ResourceLocation MISSINGNO_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "missingno");
    public static final ResourceLocation PLACEHOLDER_MODEL_ID = ResourceLocation.fromNamespaceAndPath(AvaliTheme.MODID, "default");
    public static final TextureIdentifierDefinition PLACEHOLDER_TEXTURE_DEFINITION = TextureIdentifierDefinition.fromObjects(
            List.of(new TextureIdentifierDefinition.TextureIdentifierObject(MISSINGNO_TEXTURE, 0xFFFFFF, TextureIdentifierDefinition.BlendMode.NORMAL))
            );
}
