package me.daxanius.nmc;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeNoMiningCooldownClient {
    public NeoForgeNoMiningCooldownClient(FMLModContainer container, IEventBus modBus, Dist dis) {
        NoMiningCooldown.initClient();
    }
}