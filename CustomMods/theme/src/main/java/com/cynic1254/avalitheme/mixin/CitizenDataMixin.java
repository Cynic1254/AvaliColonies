package com.cynic1254.avalitheme.mixin;

import com.cynic1254.avalitheme.GeoAbstractEntityCitizen;
import com.cynic1254.avalitheme.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.avalitheme.data.AvaliPackMetaCache;
import com.cynic1254.avalitheme.data.CitizenDefaults;
import com.cynic1254.avalitheme.data.GeoCitizenDefinitionCache;
import com.cynic1254.avalitheme.data.encoders.CitizenAttachmentEncoding;
import com.cynic1254.avalitheme.data.records.GeoCitizenDefinition;
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
    private static final String TAG_AVALI_TEXTURE = "avaliTexture";
    @Unique
    private static final String TAG_AVALI_MODEL = "avaliModel";
    @Unique
    private static final String TAG_AVALI_ATTACHMENTS = "avaliAttachments";

    @Unique
    private TextureIdentifierDefinition avali$texture = new TextureIdentifierDefinition("");

    @Unique
    private ResourceLocation avali$modelId = CitizenDefaults.PLACEHOLDER_MODEL_ID;

    @Unique
    private Map<String, ResourceLocation> avali$attachments = new HashMap<>();

    @Inject(method = "initForNewCivilian", at = @At("TAIL"), remap = false)
    private void avali$rollAppearance(CallbackInfo ci) {
        ResourceLocation defId = avali$pickDefinitionId();
        GeoCitizenDefinition citizenDefinition = GeoCitizenDefinitionCache.getDefinition(defId).orElse(null);

        if (citizenDefinition == null)
        {
            this.avali$modelId = CitizenDefaults.PLACEHOLDER_MODEL_ID;
            this.avali$texture = new TextureIdentifierDefinition(CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION.toString());
            this.avali$attachments = new HashMap<>();
            return;
        }

        this.avali$modelId = citizenDefinition.model();
        this.avali$texture = citizenDefinition.rollTextureDefinition(getRandom());
        this.avali$attachments = citizenDefinition.rollAttachments(getRandom());
    }

    @Unique
    private ResourceLocation avali$pickDefinitionId() {
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
    private void avali$serialize(CallbackInfoReturnable<CompoundTag> cir, CompoundTag nbtTagCompound) {
        nbtTagCompound.putString(TAG_AVALI_TEXTURE, avali$texture.textureID());
        nbtTagCompound.putString(TAG_AVALI_MODEL, avali$modelId == null ? "" : avali$modelId.toString());
        nbtTagCompound.putString(TAG_AVALI_ATTACHMENTS, CitizenAttachmentEncoding.encode(avali$attachments));
    }

    @Inject(method = "deserializeNBT(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), remap = false)
    private void avali$deserialize(CompoundTag nbtTagCompound, CallbackInfo ci) {
        avali$texture = new TextureIdentifierDefinition(nbtTagCompound.getString(TAG_AVALI_TEXTURE));

        String modelStr = nbtTagCompound.getString(TAG_AVALI_MODEL);
        ResourceLocation parsedModel = modelStr.isEmpty() ? null : ResourceLocation.tryParse(modelStr);
        avali$modelId = parsedModel != null ? parsedModel : CitizenDefaults.PLACEHOLDER_MODEL_ID;

        avali$attachments = CitizenAttachmentEncoding.decode(nbtTagCompound.getString(TAG_AVALI_ATTACHMENTS));
    }

    @Inject(method = "initEntityValues", at = @At("TAIL"), remap = false)
    private void avali$pushToEntity(CallbackInfo ci) {
        getEntity().ifPresent(citizen -> {
            var mutable = (AbstractEntityCitizen & GeoAbstractEntityCitizen) citizen;
            mutable.setRenderData(
                    avali$texture.textureID(),
                    avali$modelId == null ? "" : avali$modelId.toString(),
                    CitizenAttachmentEncoding.encode(avali$attachments)
            );
        });
    }
}
