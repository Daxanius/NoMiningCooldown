package me.daxanius.nmc;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(value = Constants.MOD_ID)
public class ForgeNoMiningCooldown {
    private boolean initialized = false;

    @SubscribeEvent
    private void onClientTick(TickEvent.ClientTickEvent.Post event) {
        if (!initialized) {
            NoMiningCooldownClient.init();
            initialized = true;
        }
    }
}