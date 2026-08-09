package com.cynic1254.avalitheme.data.encoders;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CitizenAttachmentEncoding {
    private CitizenAttachmentEncoding() {}

    public static String encode(Map<String, ResourceLocation> attachments) {
        if (attachments == null || attachments.isEmpty()) {
            return "";
        }
        return attachments.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("|"));
    }

    public static Map<String, ResourceLocation> decode(String encoded) {
        Map<String, ResourceLocation> result = new HashMap<>();
        if (encoded == null || encoded.isBlank()) {
            return result;
        }

        for (String entry : encoded.split("\\|")) {
            int eq = entry.indexOf('=');
            if (eq <= 0) {
                continue; // malformed segment, skip rather than throw
            }
            String bone = entry.substring(0, eq);
            ResourceLocation mesh = ResourceLocation.tryParse(entry.substring(eq + 1));
            if (mesh != null) {
                result.put(bone, mesh);
            }
        }
        return result;
    }
}
