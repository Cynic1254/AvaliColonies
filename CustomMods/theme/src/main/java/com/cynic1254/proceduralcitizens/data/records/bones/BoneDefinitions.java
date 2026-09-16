package com.cynic1254.proceduralcitizens.data.records.bones;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public record BoneDefinitions(
        String head,
        @SerializedName("left_hand") String leftHand,
        @SerializedName("right_hand") String rightHand,
        ArmorBones armor
) {
    public BoneDefinitions {
        head = Objects.requireNonNullElse(head, "b_head");
        leftHand = Objects.requireNonNullElse(leftHand, "b_left_hand");
        rightHand = Objects.requireNonNullElse(rightHand, "b_right_hand");
        armor = Objects.requireNonNullElse(armor, new ArmorBones(null, null, null, null));
    }
}
