package com.cynic1254.avalitheme.data;

import com.cynic1254.avalitheme.AvaliTheme;
import com.cynic1254.avalitheme.data.records.GeoCitizenDefinition;
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
import org.slf4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = AvaliTheme.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GeoCitizenDefinitionCache extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .create();

    private static Map<ResourceLocation, GeoCitizenDefinition> REGISTRY = new HashMap<>();

    public GeoCitizenDefinitionCache() {
        super(GSON, "citizens");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        Map<ResourceLocation, GeoCitizenDefinition> newRegistry = new HashMap<>();

        pObject.forEach((location, jsonElement) -> {
            try {
                GeoCitizenDefinition definition = GSON.fromJson(jsonElement, GeoCitizenDefinition.class);
                newRegistry.put(location, definition);
                LOGGER.info("Loaded Citizen Definition: {}", location);
            } catch (Exception e) {
                LOGGER.error("Failed to parse Citizen Definition JSON at {}", location, e);
            }
        });

        REGISTRY = Collections.unmodifiableMap(newRegistry);
        LOGGER.info("Loaded {} citizen definitions.", REGISTRY.size());
    }

    public static Optional<GeoCitizenDefinition> getDefinition(ResourceLocation id) {
        return Optional.ofNullable(
                REGISTRY.get(id)
        );
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new GeoCitizenDefinitionCache());
    }
}
