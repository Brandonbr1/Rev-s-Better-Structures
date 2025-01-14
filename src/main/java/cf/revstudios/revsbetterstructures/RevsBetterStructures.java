package cf.revstudios.revsbetterstructures;

import cf.revstudios.revsbetterstructures.events.CommonSetupEvent;
import cf.revstudios.revsbetterstructures.registry.RevsStructures;
import cf.revstudios.revsbetterstructures.worldgen.BiomeLoadEventSubscriber;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ArtifactVersion;

import java.util.Optional;

@Mod("revsbetterstructures")
public class RevsBetterStructures {
    public static final String MODID = "revsbetterstructures";
    public static final String MODNAME = "Revs Better Structures";
    public static ArtifactVersion VERSION = null;
    public static final Logger LOGGER = LogManager.getLogger();

    public RevsBetterStructures() {
        Optional<? extends ModContainer> opt = ModList.get().getModContainerById(MODID);
        if (opt.isPresent()) {
            IModInfo modInfo = opt.get().getModInfo();
            VERSION = modInfo.getVersion();
        } else {
            LOGGER.warn("Cannot get version from mod info");
        }

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
