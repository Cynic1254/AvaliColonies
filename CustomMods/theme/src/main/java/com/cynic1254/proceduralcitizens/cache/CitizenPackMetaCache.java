package com.cynic1254.proceduralcitizens.cache;

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

/// Cache class for caching the WeightedDefinition field stored in a minecolonies pack.json file
public class CitizenPackMetaCache {
    private static final Gson GSON = new Gson();
    private static final Map<String, List<WeightedDefinition>> CACHE = new ConcurrentHashMap<>();

    /// Single WeightedDefinition entry which describes a single model and the weight it has in relation to other definitions
    /// @param modelID the ResourceLocation of the model
    /// @param weight the weight to use when rolling for a model
    public record WeightedDefinition(ResourceLocation modelID, float weight) {}

    /// Get the weighted definitions for a specific pack file
    /// @param packName the name of the pack file to get the definitions from
    /// @return a list of `WeightedDefinition's`
    public static List<WeightedDefinition> getDefinitions(String packName)
    {
        return CACHE.computeIfAbsent(packName, CitizenPackMetaCache::load);
    }

    public static void clearMetaCache() {
        CACHE.clear();
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
            JsonArray arr = root.getAsJsonArray("models"); // your new key
            if (arr == null) return List.of();

            List<WeightedDefinition> out = new ArrayList<>();
            for (JsonElement e : arr)
            {
                JsonObject o = e.getAsJsonObject();
                out.add(new WeightedDefinition(
                        ResourceLocation.parse(o.get("model").getAsString()),
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
