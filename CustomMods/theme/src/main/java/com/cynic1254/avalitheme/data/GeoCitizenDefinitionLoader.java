package com.cynic1254.avalitheme.data;

import com.cynic1254.avalitheme.data.records.GeoCitizenDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GeoCitizenDefinitionLoader {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .create();

    public static Optional<GeoCitizenDefinition> loadDefinition(ResourceManager resourceManager, ResourceLocation id) {
        ResourceLocation fullPath = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "citizens/" + id.getPath() + ".json");

        Optional<Resource> resource = resourceManager.getResource(fullPath);
        if (resource.isEmpty()) {
            LOGGER.error("Could not find Citizen Definition file at: {}", fullPath);
            return Optional.empty();
        }

        try (Reader reader = new InputStreamReader(resource.get().open(), StandardCharsets.UTF_8)) {
            GeoCitizenDefinition definition = GSON.fromJson(reader, GeoCitizenDefinition.class);
            return Optional.ofNullable(definition);
        } catch (Exception e) {
            LOGGER.error("Failed to parse Citizen Definition JSON: {}", fullPath, e);
            return Optional.empty();
        }
    }
}
