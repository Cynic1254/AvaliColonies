package com.cynic1254.avaliconnector;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here MUST match the exact modId specified in your mods.toml
@Mod(Avaliconnector.MODID)
public class Avaliconnector {

    public static final String MODID = "avaliconnector";
    private static final Logger LOGGER = LogManager.getLogger();

    public Avaliconnector() {
        // Fetch the mod event bus for lifecycle events
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for initialization
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game interface events on the global Forge bus
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Avali theme initialization phase complete.");
    }
}