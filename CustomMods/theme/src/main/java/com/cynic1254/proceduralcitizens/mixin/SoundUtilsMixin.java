package com.cynic1254.proceduralcitizens.mixin;


import com.cynic1254.proceduralcitizens.GeoAbstractEntityCitizen;
import com.cynic1254.proceduralcitizens.cache.CitizenDefinitionCache;
import com.cynic1254.proceduralcitizens.cache.CitizenSoundCache;
import com.cynic1254.proceduralcitizens.data.records.CitizenDefinition;
import com.minecolonies.api.colony.ICivilianData;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.sounds.EventType;
import com.minecolonies.api.util.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SoundUtils.class)
public class SoundUtilsMixin {

    @ModifyVariable(
            method = "playSoundAtCitizenWith(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lcom/minecolonies/api/sounds/EventType;Lcom/minecolonies/api/colony/ICivilianData;DD)V",
            at = @At(value = "STORE", ordinal = 0),
            remap = false,
            name = "event")
    private static SoundEvent setCitizenSoundEvent(
            SoundEvent event,
            Level worldIn,
            BlockPos position,
            EventType type,
            ICivilianData citizenData,
            double chance,
            double volume
    ) {
        if (citizenData.getEntity().isEmpty()) {
            return event;
        }

        var entity = (AbstractEntityCitizen & GeoAbstractEntityCitizen) citizenData.getEntity().get();
        CitizenDefinition definition = CitizenDefinitionCache.getDefinition(entity.getModelId()).orElse(null);

        if (definition == null) {
            return event;
        }

        ResourceLocation soundEvent = ResourceLocation.fromNamespaceAndPath(definition.sound().getNamespace(), definition.sound().getPath() + "." + type.getId());

        return CitizenSoundCache.GetSoundResource(soundEvent);
    }
}
