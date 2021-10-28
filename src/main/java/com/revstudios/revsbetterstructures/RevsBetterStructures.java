package com.revstudios.RevsBetterStructures;

import com.revstudios.revsbetterstructures.events.CommonSetupEvent;
import com.revstudios.revsbetterstructures.registry.RevsStructures;
import com.revstudios.revsbetterstructures.worldgen.BiomeLoadEventSubscriber;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("revsbetterstructures")
public class revsbetterstructures{

    public static final String MODID = "revsbetterstructures";
    public static final String MODNAME = "Revs Better Structures";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LogManager.getLogger();
    public static revsbetterstructures INSTANCE;

    public revsbetterstructures() {
        INSTANCE = this;

        LOGGER.debug(MODNAME + " Version is: " + VERSION);
        LOGGER.debug("Mod ID for " + MODNAME + " is: " + MODID);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Register to the mod event bus
        eventBus.addListener(CommonSetupEvent::onFMLCommonSetupEvent);

        RevsStructures.STRUCTURES.register(eventBus);

        //Register to the forge event bus
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, CommonSetupEvent::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BiomeLoadEventSubscriber::onBiomeLoadingEvent);
        MinecraftForge.EVENT_BUS.register(this);
    }
}