package com.cynic1254.proceduralcitizens.client;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenAnimatable;
import com.cynic1254.proceduralcitizens.client.rendering.GeoCitizenRenderer;
import com.minecolonies.api.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProceduralCitizens.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.CITIZEN,
                ctx -> new GeoCitizenRenderer(ctx, GeoCitizenAnimatable.CITIZEN)
        );
        event.registerEntityRenderer(
                ModEntities.VISITOR,
                ctx -> new GeoCitizenRenderer(ctx, GeoCitizenAnimatable.VISITOR)
        );
    }
}