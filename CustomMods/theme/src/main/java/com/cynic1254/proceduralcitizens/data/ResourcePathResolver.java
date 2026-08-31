package com.cynic1254.proceduralcitizens.data;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import net.minecraft.resources.ResourceLocation;

public class ResourcePathResolver {
    public static ResourceLocation GetBakedTexturePath(TextureIdentifierDefinition definition) {
        return ResourceLocation.fromNamespaceAndPath(
                ProceduralCitizens.MODID,
                "procedural/citizen/" + Math.abs(definition.hashCode())
        );
    }

    public static ResourceLocation GetClothingTextureFolderPath(ResourceLocation id) {
        if (id.getPath().startsWith("textures/")) {
            return id;
        }
        return ResourceLocation.fromNamespaceAndPath(
                id.getNamespace(),
                "textures/entity/citizen/job/" + id.getPath() + "/"
        );
    }

    public static ResourceLocation GetCitizenTexturePath(ResourceLocation id) {
        if (id.getPath().startsWith("textures/") || id.getPath().endsWith(".png")) {
            return id;
        }
        return ResourceLocation.fromNamespaceAndPath(
                id.getNamespace(),
                "textures/entity/citizen/base/" + id.getPath() + ".png"
        );
    }
}
