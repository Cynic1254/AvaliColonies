package com.cynic1254.proceduralcitizens.cache;

import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib.cache.GeckoLibCache;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/// Cache class for storing a map of all Armor bones that are part of a specific model.
/// This can be used to prevent having to loop over all the bones every render pass.
/// Each instance of this class represents the bones of a single BakedGeoModel.
public class CitizenArmorCache {
    private static final Map<ResourceLocation, CitizenArmorCache> MODELS = new ConcurrentHashMap<>();

    private final Map<EquipmentSlot, Set<GeoBone>> bones = new ConcurrentHashMap<>();

    private static final Map<String, EquipmentSlot> PREFIX_TO_SLOT = Map.of(
            "armor_helmet_", EquipmentSlot.HEAD,
            "armor_chestplate_", EquipmentSlot.CHEST,
            "armor_leggings_", EquipmentSlot.LEGS,
            "armor_boots_", EquipmentSlot.FEET
    );

    private CitizenArmorCache() {}
    private CitizenArmorCache(BakedGeoModel bakedModel) {
        for (GeoBone bone : bakedModel.topLevelBones()) {
            parseBone(bone);
        }
    }

    /// Get a specific cache for a given model, model and animatable are used to calculate the resource location using `model.getModelResource(animatable)`
    /// @param model the model to get the cache for
    /// @param animatable the animatable to get the cache for
    /// @return an instance of CitizenArmorCache for the specific Model, an empty instance will be returned if the cache failed to bake the model, in this case no cache entry will be generated to allow future lookups to properly generate the entry
    public static CitizenArmorCache getCacheForModel(GeoCitizenModel model, GeoCitizenAnimatable animatable) {
        CitizenArmorCache armorCache = MODELS.computeIfAbsent(model.getModelResource(animatable), loc -> {
            var bakedModel = GeckoLibCache.getBakedModels().get(loc);
            return bakedModel != null ? new CitizenArmorCache(bakedModel) : null;
        });

        return armorCache != null ? armorCache : new CitizenArmorCache();
    }

    /// Get all the bones for a specific armor slot
    /// @param slot the slot to get the bones for
    /// @return a Set of GeoBones where each bone of the set is the root of a chain for rendering a piece of the armor
    public Set<GeoBone> GetBonesForSlot(EquipmentSlot slot) {
        return bones.getOrDefault(slot, Collections.emptySet());
    }

    /// Get all the bones cached for a specific model
    /// @return a flattened set of GeoBones where each bone of the set is the root of a chain for rendering a piece of the armor
    public Set<GeoBone> GetAllBones() {
        return bones.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static void ClearCache() {
        MODELS.clear();
    }

    private void parseBone(GeoBone bone) {
        for (GeoBone child : bone.getChildBones()) {
            EquipmentSlot slot = getEquipmentSlotForBone(child);
            if (slot != null) {
                bones.computeIfAbsent(slot, s -> ConcurrentHashMap.newKeySet()).add(child);
                continue;
            }

            parseBone(child);
        }
    }

    private EquipmentSlot getEquipmentSlotForBone(GeoBone bone) {
        GeoBone parentBone = bone.getParent();
        if (parentBone == null) {
            return null;
        }

        String boneName = bone.getName();
        String parentSuffix = "_" + parentBone.getName();

        if (boneName.endsWith(parentSuffix)) {
            String prefix = boneName.substring(0, boneName.length() - parentSuffix.length() + 1);
            return PREFIX_TO_SLOT.get(prefix);
        }

        return null;
    }
}
