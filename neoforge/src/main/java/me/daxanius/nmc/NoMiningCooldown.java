package me.daxanius.nmc;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NoMiningCooldown {
    public NoMiningCooldown(IEventBus eventBus) {
        NoMiningCooldownClient.initClient();
    }
}