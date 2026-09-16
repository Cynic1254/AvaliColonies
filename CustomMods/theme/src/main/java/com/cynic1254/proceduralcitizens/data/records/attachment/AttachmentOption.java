package com.cynic1254.proceduralcitizens.data.records.attachment;

import com.cynic1254.proceduralcitizens.data.records.VisibilityRules;
import com.cynic1254.proceduralcitizens.helper.WeightedRandom;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public record AttachmentOption(
        String name,
        @SerializedName("bone") List<String> bones,
        Float weight,
        @SerializedName("visibility_rules") VisibilityRules visibilityRules
) implements WeightedRandom{
    public AttachmentOption {
        name = Objects.requireNonNullElse(name, "unnamed_option");
        bones = Objects.requireNonNullElse(bones, List.of());
        weight = Objects.requireNonNullElse(weight, 1.0f);
        weight = weight <= 0.0f ? 1.0f : weight;
        visibilityRules = Objects.requireNonNullElse(visibilityRules, new VisibilityRules(null, null, null));
    }
}