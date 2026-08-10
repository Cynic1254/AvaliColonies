package com.cynic1254.proceduralcitizens;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here MUST match the exact modId specified in your mods.toml
@Mod(ProceduralCitizens.MODID)
public class ProceduralCitizens {

    public static final String MODID = "procedural_citizens";
    private static final Logger LOGGER = LogManager.getLogger();

    public ProceduralCitizens(FMLJavaModLoadingContext context) {
        // Fetch the mod event bus for lifecycle events
        var modEventBus = context.getModEventBus();

        // Register the commonSetup method for initialization
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game interface events on the global Forge bus
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Procedural citizens mod initialization phase complete.");
    }
}