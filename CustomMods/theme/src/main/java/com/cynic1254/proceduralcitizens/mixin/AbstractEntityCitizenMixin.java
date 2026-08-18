package com.cynic1254.proceduralcitizens.mixin;

import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.proceduralcitizens.data.CitizenDefaults;
import com.cynic1254.proceduralcitizens.data.encoders.CitizenAttachmentEncoding;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(AbstractEntityCitizen.class)
public class AbstractEntityCitizenMixin implements GeoAbstractEntityCitizen {

    @Unique
    private static final EntityDataAccessor<String> DATA_PROCEDURAL_TEXTURE =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);
    @Unique
    private static final EntityDataAccessor<String> DATA_PROCEDURAL_MODEL =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);
    @Unique
    private static final EntityDataAccessor<String> DATA_PROCEDURAL_ATTACHMENTS =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void procedural$defineData(CallbackInfo ci) {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        truethis.getEntityData().define(DATA_PROCEDURAL_TEXTURE, CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION.toString());
        truethis.getEntityData().define(DATA_PROCEDURAL_MODEL, CitizenDefaults.EMPTY_MODEL_ID.toString());
        truethis.getEntityData().define(DATA_PROCEDURAL_ATTACHMENTS, "");
    }

    @Unique(silent = true)
    @Override
    public TextureIdentifierDefinition getTextureID() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return new TextureIdentifierDefinition(truethis.getEntityData().get(DATA_PROCEDURAL_TEXTURE));
    }

    @Unique(silent = true)
    @Override
    public ResourceLocation getModelId() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return ResourceLocation.tryParse(truethis.getEntityData().get(DATA_PROCEDURAL_MODEL));
    }

    @Unique(silent = true)
    @Override
    public Map<String, ResourceLocation> getAttachments() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return CitizenAttachmentEncoding.decode(truethis.getEntityData().get(DATA_PROCEDURAL_ATTACHMENTS));
    }

    @Unique(silent = true)
    @Override
    public void setRenderData(String texture, String model, String attachments) {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        truethis.getEntityData().set(DATA_PROCEDURAL_TEXTURE, texture);
        truethis.getEntityData().set(DATA_PROCEDURAL_MODEL, model);
        truethis.getEntityData().set(DATA_PROCEDURAL_ATTACHMENTS, attachments);
    }
}
