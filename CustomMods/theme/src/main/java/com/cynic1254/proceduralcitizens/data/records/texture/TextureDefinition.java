package com.cynic1254.proceduralcitizens.data.records.texture;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public record TextureDefinition(
        @SerializedName("armor_override_path") ResourceLocation armorOverridePath,
        @SerializedName("clothing_override_path") ResourceLocation clothingOverridePath,
        BaseTexture base,
        List<OverlayGroup> overlays
) {
    public TextureDefinition {
        base = Objects.requireNonNullElse(base, new BaseTexture(List.of(), List.of()));
        overlays = Objects.requireNonNullElse(overlays, List.of());
    }

    public ResourceLocation getTextureForMaterialAndSlot(ItemStack stack) {
        if (!(stack.getItem() instanceof ArmorItem armorItem)) {
            // Default fallback texture if stack isn't valid armor
            return CitizenDefaults.MISSINGNO_TEXTURE;
        }

        return getTextureForMaterialAndSlot(armorItem.getMaterial(), armorItem.getEquipmentSlot());
    }

    public ResourceLocation getTextureForMaterialAndSlot(ArmorMaterial material, EquipmentSlot slot) {
        int layer = (slot == EquipmentSlot.LEGS) ? 2 : 1;
        String materialName = material.getName();

        if (armorOverridePath != null) {
            materialName = materialName.replace(':', '_');
            return ResourceLocation.fromNamespaceAndPath(
                    armorOverridePath.getNamespace(),
                    armorOverridePath.getPath() + "/" + materialName + "_layer_" + layer + ".png"
            );
        }

        if (materialName.contains(":")) {
            String[] parts = materialName.split(":", 2);
            return formatArmorPath(parts[0], parts[1], layer);
        }

        return formatArmorPath("minecraft", materialName, layer);
    }

    public List<TextureIdentifierDefinition.TextureIdentifierObject> roll(Random random) {
        List<TextureIdentifierDefinition.TextureIdentifierObject> layers = new ArrayList<>();

        layers.add(base.roll(random));

        for (OverlayGroup overlay : overlays) {
            overlay.roll(random).ifPresent(layers::add);
        }

        return layers;
    }

    private ResourceLocation formatArmorPath(String namespace, String path, int layer) {
        return ResourceLocation.fromNamespaceAndPath(namespace, "textures/models/armor/" + path + "_layer_" + layer + ".png");
    }
}
