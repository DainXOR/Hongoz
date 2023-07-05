package net.dain.hongozmod;

import com.mojang.logging.LogUtils;
import net.dain.hongozmod.entity.ModEntityTypes;
import net.dain.hongozmod.entity.client.ZhongoRenderer;
import net.dain.hongozmod.entity.custom.ZhongoEntity;
import net.dain.hongozmod.item.ModItems;
import net.dain.hongozmod.sound.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HongozMod.MOD_ID)
public class HongozMod {
    public static final String MOD_ID = "hongoz";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HongozMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSounds.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModItems.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntityTypes.ZHONGO.get(), ZhongoRenderer::new);
        }
    }
}
