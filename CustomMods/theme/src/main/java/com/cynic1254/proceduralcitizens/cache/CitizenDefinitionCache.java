package com.cynic1254.proceduralcitizens.cache;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.data.adapters.ListTypeAdapter;
import com.cynic1254.proceduralcitizens.data.adapters.WeightedTextureDeserializer;
import com.cynic1254.proceduralcitizens.data.records.CitizenDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/// Cache class for loading and caching Citizen Definitions
@Mod.EventBusSubscriber(modid = ProceduralCitizens.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CitizenDefinitionCache extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .registerTypeAdapter(CitizenDefinition.WeightedTexture.class, new WeightedTextureDeserializer())
            //.registerTypeAdapter(CitizenDefinition.AttachmentMesh.class, new AttachmentMeshDeserializer())
            .registerTypeAdapterFactory(new ListTypeAdapter())
            .create();

    private static Map<ResourceLocation, CitizenDefinition> REGISTRY = new HashMap<>();

    public CitizenDefinitionCache() {
        super(GSON, "citizens");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, @NotNull ResourceManager pResourceManager, @NotNull ProfilerFiller pProfiler) {
        Map<ResourceLocation, CitizenDefinition> newRegistry = new HashMap<>();

        pObject.forEach((location, jsonElement) -> {
            try {
                CitizenDefinition definition = GSON.fromJson(jsonElement, CitizenDefinition.class);
                newRegistry.put(location, definition);
                LOGGER.info("Loaded Citizen Definition: {}", location);
            } catch (Exception e) {
                LOGGER.error("Failed to parse Citizen Definition JSON at {}", location, e);
            }
        });

        REGISTRY = Collections.unmodifiableMap(newRegistry);
        LOGGER.info("Loaded {} citizen definitions.", REGISTRY.size());
    }

    public static Optional<CitizenDefinition> getDefinition(ResourceLocation id) {
        return Optional.ofNullable(
                REGISTRY.get(id)
        );
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new CitizenDefinitionCache());
    }
}
