package com.cynic1254.proceduralcitizens.data.records;

import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.BoneData;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.ResourcePathResolver;
import com.cynic1254.proceduralcitizens.data.records.attachment.AttachmentGroup;
import com.cynic1254.proceduralcitizens.data.records.attachment.AttachmentOption;
import com.cynic1254.proceduralcitizens.data.records.bones.BoneDefinitions;
import com.cynic1254.proceduralcitizens.data.records.texture.TextureDefinition;
import com.minecolonies.api.colony.jobs.IJob;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.*;
import java.util.stream.Collectors;

public record CitizenDefinition(
        ResourceLocation model,
        BoneDefinitions bones,
        TextureDefinition textures,
        List<AttachmentGroup> attachments
) {
    public CitizenDefinition {
        model = Objects.requireNonNullElse(model, CitizenDefaults.MISSING_MODEL_ID);
        bones = Objects.requireNonNullElse(bones, new BoneDefinitions(null, null, null, null));
        textures = Objects.requireNonNullElse(textures, new TextureDefinition(null, null, null, null));
        attachments = Objects.requireNonNullElse(attachments, List.of());
    }

    public ResourceLocation getClothingTextureForJob(IJob<?> job) {
        ResourceLocation textureLocation = ResourcePathResolver.GetClothingTextureFolderPath(textures.clothingOverridePath() == null ? model : textures.clothingOverridePath());
        ResourceLocation fallbackLocation = ResourceLocation.fromNamespaceAndPath(textureLocation.getNamespace(), textureLocation.getPath() + "default.png");
        fallbackLocation = Minecraft.getInstance().getResourceManager().getResource(fallbackLocation).isPresent() ? fallbackLocation : null;

        if (job == null) {
            return fallbackLocation;
        }

        String relativePath = job.getJobRegistryEntry().getKey().toString().replace(':', '_') + ".png";
        ResourceLocation finalLocation = ResourceLocation.fromNamespaceAndPath(textureLocation.getNamespace(), textureLocation.getPath() + relativePath);

        boolean textureExists = Minecraft.getInstance().getResourceManager().getResource(finalLocation).isPresent();

        return textureExists ? finalLocation : fallbackLocation;
    }

    public TextureIdentifierDefinition rollTextureDefinition(Random random) {
        List<TextureIdentifierDefinition.TextureIdentifierObject> objects = textures.roll(random);
        return TextureIdentifierDefinition.fromObjects(objects);
    }

    public BoneData rollAttachments(Random random) {
        Set<String> alwaysVisible = new HashSet<>();
        Map<EquipmentSlot, Set<String>> armorHidden = new HashMap<>();
        Map<ResourceLocation, Set<String>> jobHidden = new HashMap<>();
        Map<ResourceLocation, Set<String>> jobShown = new HashMap<>();

        for (AttachmentGroup group : attachments) {
            List<AttachmentOption> selectedOptions = group.roll(random);

            for (AttachmentOption option : selectedOptions) {
                if (option.bones().isEmpty()) {
                    continue;
                }

                // Merge rules from both group and option levels
                VisibilityRules mergedRules = VisibilityRules.merge(group.visibilityRules(), option.visibilityRules());

                // Route visibility states based on merged rules
                mergedRules.applyToBoneData(option.bones(), alwaysVisible, armorHidden, jobHidden, jobShown);
            }
        }

        return new BoneData(alwaysVisible, armorHidden, jobHidden, jobShown);
    }

    public Set<String> getAllAttachmentBones() {
        return attachments.stream()
                .flatMap(group -> group.options().stream())
                .flatMap(option -> option.bones().stream())
                .collect(Collectors.toSet());
    }
}
