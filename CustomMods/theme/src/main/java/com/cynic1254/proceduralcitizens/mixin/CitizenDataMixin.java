package com.cynic1254.proceduralcitizens.mixin;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.AvaliPackMetaCache;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.GeoCitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.data.encoders.CitizenAttachmentEncoding;
import com.cynic1254.proceduralcitizens.data.records.GeoCitizenDefinition;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.core.colony.CitizenData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.*;

@Mixin(CitizenData.class)
public abstract class CitizenDataMixin {
    @Shadow
    public abstract Random getRandom();

    @Shadow
    @NotNull
    public abstract Optional<AbstractEntityCitizen> getEntity();

    @Unique
    private static final String TAG_PROCEDURAL_TEXTURE = "proceduralTexture";
    @Unique
    private static final String TAG_PROCEDURAL_MODEL = "proceduralModel";
    @Unique
    private static final String TAG_PROCEDURAL_ATTACHMENTS = "proceduralAttachment";

    @Unique
    private TextureIdentifierDefinition procedural$texture = new TextureIdentifierDefinition("");

    @Unique
    private ResourceLocation procedural$modelId = CitizenDefaults.PLACEHOLDER_MODEL_ID;

    @Unique
    private Map<String, ResourceLocation> procedural$attachments = new HashMap<>();

    @Inject(method = "initForNewCivilian", at = @At("TAIL"), remap = false)
    private void procedural$rollAppearance(CallbackInfo ci) {
        ResourceLocation defId = procedural$pickDefinitionId();
        GeoCitizenDefinition citizenDefinition = GeoCitizenDefinitionCache.getDefinition(defId).orElse(null);

        if (citizenDefinition == null)
        {
            this.procedural$modelId = CitizenDefaults.PLACEHOLDER_MODEL_ID;
            this.procedural$texture = new TextureIdentifierDefinition(CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION.toString());
            this.procedural$attachments = new HashMap<>();
            return;
        }

        this.procedural$modelId = citizenDefinition.model();
        this.procedural$texture = citizenDefinition.rollTextureDefinition(getRandom());
        this.procedural$attachments = citizenDefinition.rollAttachments(getRandom());
    }

    @Unique
    private ResourceLocation procedural$pickDefinitionId() {
        List<AvaliPackMetaCache.WeightedDefinition> pool =
                AvaliPackMetaCache.getDefinitions(((CitizenData)(Object)this).getColony().getStructurePack());

        if (pool.isEmpty()) {
            return CitizenDefaults.PLACEHOLDER_MODEL_ID;
        }

        float totalWeight = 0f;
        for (var d : pool) totalWeight += d.weight();
        float roll = getRandom().nextFloat() * totalWeight;
        float cumulative = 0f;
        for (var d : pool) {
            cumulative += d.weight();
            if (roll <= cumulative) return d.id();
        }
        return pool.get(0).id();
    }

    @Inject(
            method = "serializeNBT()Lnet/minecraft/nbt/CompoundTag;",
            at = @At("RETURN"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private void procedural$serialize(CallbackInfoReturnable<CompoundTag> cir, CompoundTag nbtTagCompound) {
        nbtTagCompound.putString(TAG_PROCEDURAL_TEXTURE, procedural$texture.textureID());
        nbtTagCompound.putString(TAG_PROCEDURAL_MODEL, procedural$modelId == null ? "" : procedural$modelId.toString());
        nbtTagCompound.putString(TAG_PROCEDURAL_ATTACHMENTS, CitizenAttachmentEncoding.encode(procedural$attachments));
    }

    @Inject(method = "deserializeNBT(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), remap = false)
    private void procedural$deserialize(CompoundTag nbtTagCompound, CallbackInfo ci) {
        procedural$texture = new TextureIdentifierDefinition(nbtTagCompound.getString(TAG_PROCEDURAL_TEXTURE));

        String modelStr = nbtTagCompound.getString(TAG_PROCEDURAL_MODEL);
        ResourceLocation parsedModel = modelStr.isEmpty() ? null : ResourceLocation.tryParse(modelStr);
        procedural$modelId = parsedModel != null ? parsedModel : CitizenDefaults.PLACEHOLDER_MODEL_ID;

        procedural$attachments = CitizenAttachmentEncoding.decode(nbtTagCompound.getString(TAG_PROCEDURAL_ATTACHMENTS));
    }

    @Inject(method = "initEntityValues", at = @At("TAIL"), remap = false)
    private void procedural$pushToEntity(CallbackInfo ci) {
        getEntity().ifPresent(citizen -> {
            var mutable = (AbstractEntityCitizen & GeoAbstractEntityCitizen) citizen;
            mutable.setRenderData(
                    procedural$texture.textureID(),
                    procedural$modelId == null ? "" : procedural$modelId.toString(),
                    CitizenAttachmentEncoding.encode(procedural$attachments)
            );
        });
    }
}
