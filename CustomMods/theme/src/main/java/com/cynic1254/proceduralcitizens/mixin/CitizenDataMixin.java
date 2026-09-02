package com.cynic1254.proceduralcitizens.mixin;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.cache.CitizenPackMetaCache;
import com.cynic1254.proceduralcitizens.data.BoneData;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.data.records.CitizenDefinition;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.core.colony.CitizenData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.*;

@Mixin(CitizenData.class)
public abstract class CitizenDataMixin {

    @Unique
    private static final String TAG_PROCEDURAL_TEXTURE = "proceduralTexture";
    @Unique
    private static final String TAG_PROCEDURAL_MODEL = "proceduralModel";
    @Unique
    private static final String TAG_PROCEDURAL_ATTACHMENTS = "proceduralAttachment";

    @Unique
    private TextureIdentifierDefinition procedural$texture = CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION;

    @Unique
    private ResourceLocation procedural$modelId = CitizenDefaults.EMPTY_MODEL_ID;

    @Unique
    private BoneData procedural$attachments = null;

    @Inject(method = "initForNewCivilian", at = @At("TAIL"), remap = false)
    private void procedural$rollAppearance(CallbackInfo ci) {
        CitizenData mixinThis = (CitizenData)(Object)this;

        ResourceLocation defId = procedural$pickDefinitionId();
        CitizenDefinition citizenDefinition = CitizenDefinitionCache.getDefinition(defId).orElse(null);

        if (citizenDefinition == null)
        {
            // Set render data to a clear error state
            this.procedural$modelId = CitizenDefaults.MISSING_MODEL_ID;
            this.procedural$texture = CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION;
            this.procedural$attachments = null;
            return;
        }

        this.procedural$modelId = citizenDefinition.model();
        this.procedural$texture = citizenDefinition.rollTextureDefinition(mixinThis.getRandom());

        this.procedural$attachments = citizenDefinition.rollAttachments(mixinThis.getRandom());
    }

    @Unique
    private ResourceLocation procedural$pickDefinitionId() {
        CitizenData mixinThis = (CitizenData)(Object)this;

        List<CitizenPackMetaCache.WeightedDefinition> pool =
                CitizenPackMetaCache.getDefinitions(mixinThis.getColony().getStructurePack());

        if (pool.isEmpty()) {
            return CitizenDefaults.DEFAULT_CITIZEN_DEFINITION_ID;
        }

        float totalWeight = 0f;
        for (var d : pool) totalWeight += d.weight();
        float roll = mixinThis.getRandom().nextFloat() * totalWeight;
        float cumulative = 0f;
        for (var d : pool) {
            cumulative += d.weight();
            if (roll <= cumulative)
                return d.modelID();
        }
        return pool.get(0).modelID();
    }

    @Inject(
            method = "serializeNBT()Lnet/minecraft/nbt/CompoundTag;",
            at = @At("RETURN"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private void procedural$serialize(CallbackInfoReturnable<CompoundTag> cir, CompoundTag nbtTagCompound) {
        nbtTagCompound.putString(TAG_PROCEDURAL_TEXTURE, procedural$texture.textureID());
        nbtTagCompound.putString(TAG_PROCEDURAL_MODEL, procedural$modelId == null ?
                CitizenDefaults.MISSING_MODEL_ID.toString() : procedural$modelId.toString());
        nbtTagCompound.putString(TAG_PROCEDURAL_ATTACHMENTS, procedural$attachments.toString());
    }

    @Inject(method = "deserializeNBT(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), remap = false)
    private void procedural$deserialize(CompoundTag nbtTagCompound, CallbackInfo ci) {
        procedural$texture = new TextureIdentifierDefinition(nbtTagCompound.getString(TAG_PROCEDURAL_TEXTURE));

        String modelStr = nbtTagCompound.getString(TAG_PROCEDURAL_MODEL);
        ResourceLocation parsedModel = modelStr.isEmpty() ? null : ResourceLocation.tryParse(modelStr);
        procedural$modelId = parsedModel != null ? parsedModel : CitizenDefaults.MISSING_MODEL_ID;

        procedural$attachments = new BoneData(nbtTagCompound.getString(TAG_PROCEDURAL_ATTACHMENTS));
    }

    @Inject(method = "initEntityValues", at = @At("TAIL"), remap = false)
    private void procedural$pushToEntity(CallbackInfo ci) {
        CitizenData mixinThis = (CitizenData)(Object)this;

        mixinThis.getEntity().ifPresent(citizen -> {
            var mutable = (AbstractEntityCitizen & GeoAbstractEntityCitizen) citizen;
            mutable.setRenderData(
                    procedural$texture.textureID(),
                    procedural$modelId == null ?
                            CitizenDefaults.MISSING_MODEL_ID.toString() : procedural$modelId.toString(),
                    procedural$attachments.toString()
            );
        });
    }
}
