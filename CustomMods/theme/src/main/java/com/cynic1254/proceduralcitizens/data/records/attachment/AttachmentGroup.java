package com.cynic1254.proceduralcitizens.data.records.attachment;

import com.cynic1254.proceduralcitizens.data.records.VisibilityRules;
import com.cynic1254.proceduralcitizens.helper.WeightedRandom;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public record AttachmentGroup(
        String name,
        Float chance,
        Boolean exclusive,
        @SerializedName("visibility_rules") VisibilityRules visibilityRules,
        List<AttachmentOption> options
) {
    public AttachmentGroup {
        name = Objects.requireNonNullElse(name, "unnamed_group");

        exclusive = Objects.requireNonNullElse(exclusive, true);
        visibilityRules = Objects.requireNonNullElse(visibilityRules, new VisibilityRules(null, null, null));
        options = Objects.requireNonNullElse(options, List.of());
    }

    public List<AttachmentOption> roll(Random random) {
        if (options.isEmpty() || random.nextFloat() > chance) {
            return List.of();
        }

        if (!exclusive) {
            return options;
        }

        return WeightedRandom.roll(options, random)
                .map(List::of)
                .orElseGet(List::of);
    }
}