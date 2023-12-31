package net.dain.hongozmod.networking;

import net.dain.hongozmod.HongozMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
/*
public class ModNetworking {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){ return packetId++; };

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(HongozMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(FungiEggC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FungiEggC2SPacket::new)
                .encoder(FungiEggC2SPacket::toBytes)
                .consumerMainThread(FungiEggC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
*/