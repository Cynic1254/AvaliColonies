package com.cynic1254.proceduralcitizens.cache;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CitizenSoundCache {
    private static final Map<ResourceLocation, SoundEvent> SOUND_CACHE = new ConcurrentHashMap<>();

    public static SoundEvent GetSoundResource(ResourceLocation location) {
        return SOUND_CACHE.computeIfAbsent(location, SoundEvent::createVariableRangeEvent);
    }

    public static void ClearCache() {
        SOUND_CACHE.clear();
    }
}
