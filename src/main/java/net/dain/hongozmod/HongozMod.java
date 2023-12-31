package net.dain.hongozmod;

import com.mojang.logging.LogUtils;
import net.dain.hongozmod.block.ModBlocks;
import net.dain.hongozmod.entity.ModEntityTypes;
import net.dain.hongozmod.entity.client.*;
import net.dain.hongozmod.entity.custom.hunter.HunterEntity;
import net.dain.hongozmod.item.ModItems;
import net.dain.hongozmod.sound.ModSounds;
import net.dain.hongozmod.world.feature.ModConfiguredFeatures;
import net.dain.hongozmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(HongozMod.MOD_ID)
public class HongozMod {
    public static final String MOD_ID = "hongoz";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HongozMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntityTypes.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.ZHONGO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.HORDEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CROAKTAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.HONZIADE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.HUNTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    HunterEntity::checkHunterSpawnRules);

            SpawnPlacements.register(ModEntityTypes.EVO_CROAKTAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.BEACON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

        });

        //ModNetworking.register();
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntityTypes.FUNGI_EGG.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.MAGGOT.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.BEACON.get(), InfectedRenderer::new);

            EntityRenderers.register(ModEntityTypes.ZHONGO.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.HORDEN.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.CROAKTAR.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.HONZIADE.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.HUNTER.get(), InfectedRenderer::new);

            EntityRenderers.register(ModEntityTypes.EVO_CROAKTAR.get(), InfectedRenderer::new);
            EntityRenderers.register(ModEntityTypes.HONZIADE_QUEEN.get(), InfectedRenderer::new);
        }
    }
}
