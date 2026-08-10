package com.cynic1254.proceduralcitizens.client.Listeners;

import com.cynic1254.proceduralcitizens.ProceduralCitizens;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenAttachmentModel;
import com.cynic1254.proceduralcitizens.client.rendering.model.GeoCitizenModel;
import com.cynic1254.proceduralcitizens.client.rendering.textures.TextureManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = ProceduralCitizens.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRenderCacheReloadListener extends SimplePreparableReloadListener<Void> {
    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new ClientRenderCacheReloadListener());
    }

    @Override
    protected @NotNull Void prepare(@NotNull ResourceManager pResourceManager, @NotNull ProfilerFiller pProfiler) {
        return null;
    }

    @Override
    protected void apply(@NotNull Void pObject, @NotNull ResourceManager pResourceManager, @NotNull ProfilerFiller pProfiler) {
        TextureManager.clearTextureCache();
        GeoCitizenModel.clearCache();
        GeoCitizenAttachmentModel.clearCache();
    }
}
