package com.cynic1254.avalitheme.mixin;

import com.cynic1254.avalitheme.GeoAbstractEntityCitizen;
import com.cynic1254.avalitheme.client.rendering.textures.TextureIdentifierDefinition;
import com.cynic1254.avalitheme.data.CitizenDefaults;
import com.cynic1254.avalitheme.data.encoders.CitizenAttachmentEncoding;
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
    private static final EntityDataAccessor<String> DATA_AVALI_TEXTURE =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);
    @Unique
    private static final EntityDataAccessor<String> DATA_AVALI_MODEL =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);
    @Unique
    private static final EntityDataAccessor<String> DATA_AVALI_ATTACHMENTS =
            SynchedEntityData.defineId(AbstractEntityCitizen.class, EntityDataSerializers.STRING);

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void avali$defineData(CallbackInfo ci) {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        truethis.getEntityData().define(DATA_AVALI_TEXTURE, CitizenDefaults.PLACEHOLDER_TEXTURE_DEFINITION.toString());
        truethis.getEntityData().define(DATA_AVALI_MODEL, CitizenDefaults.PLACEHOLDER_MODEL_ID.toString());
        truethis.getEntityData().define(DATA_AVALI_ATTACHMENTS, "");
    }

    @Unique(silent = true)
    @Override
    public TextureIdentifierDefinition getTextureID() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return new TextureIdentifierDefinition(truethis.getEntityData().get(DATA_AVALI_TEXTURE));
    }

    @Unique(silent = true)
    @Override
    public ResourceLocation getModelId() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return ResourceLocation.tryParse(truethis.getEntityData().get(DATA_AVALI_MODEL));
    }

    @Unique(silent = true)
    @Override
    public Map<String, ResourceLocation> getAttachments() {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        return CitizenAttachmentEncoding.decode(truethis.getEntityData().get(DATA_AVALI_ATTACHMENTS));
    }

    @Unique(silent = true)
    @Override
    public void setRenderData(String texture, String model, String attachments) {
        var truethis = (AbstractEntityCitizen & GeoAbstractEntityCitizen)(Object)this;
        truethis.getEntityData().set(DATA_AVALI_TEXTURE, texture);
        truethis.getEntityData().set(DATA_AVALI_MODEL, model);
        truethis.getEntityData().set(DATA_AVALI_ATTACHMENTS, attachments);
    }
}
