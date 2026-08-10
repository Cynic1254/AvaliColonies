package com.cynic1254.proceduralcitizens.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ldtteam.structurize.storage.StructurePackMeta;
import com.ldtteam.structurize.storage.StructurePacks;
import net.minecraft.resources.ResourceLocation;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AvaliPackMetaCache {
    private static final Gson GSON = new Gson();
    private static final Map<String, List<WeightedDefinition>> CACHE = new ConcurrentHashMap<>();

    public record WeightedDefinition(ResourceLocation id, float weight) {}

    public static List<WeightedDefinition> getDefinitions(String packName)
    {
        return CACHE.computeIfAbsent(packName, AvaliPackMetaCache::load);
    }

    private static List<WeightedDefinition> load(String packName)
    {
        StructurePacks.waitUntilFinishedLoading();
        StructurePackMeta meta = StructurePacks.getStructurePack(packName);
        if (meta == null) return List.of();

        Path packJson = meta.getPath().resolve("pack.json");
        try (Reader r = Files.newBufferedReader(packJson))
        {
            JsonObject root = GSON.fromJson(r, JsonObject.class);
            JsonArray arr = root.getAsJsonArray("citizen_definitions"); // your new key
            if (arr == null) return List.of();

            List<WeightedDefinition> out = new ArrayList<>();
            for (JsonElement e : arr)
            {
                JsonObject o = e.getAsJsonObject();
                out.add(new WeightedDefinition(
                        ResourceLocation.parse(o.get("id").getAsString()),
                        o.has("weight") ? o.get("weight").getAsFloat() : 1.0f));
            }
            return out;
        }
        catch (Exception ex)
        {
            return List.of();
        }
    }
}
