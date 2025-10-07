package me.daxanius.nmc;

import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class FabricNoMiningCooldownClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NoMiningCooldownClient.init();
    }
}