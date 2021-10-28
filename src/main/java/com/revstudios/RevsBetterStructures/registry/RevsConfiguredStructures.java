package com.revstudios.revsbetterstructures.registry;

import com.revstudios.revsbetterstructures.RevsBetterStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class RevsConfiguredStructures {

    public static StructureFeature<?, ?> CONFIGURED_WINDMILL = RevsStructures.WINDMILL.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(revsbetterstructures.MODID, "configured_windmill"), CONFIGURED_WINDMILL);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(RevsStructures.WINDMILL.get(), CONFIGURED_WINDMILL);
    }
}