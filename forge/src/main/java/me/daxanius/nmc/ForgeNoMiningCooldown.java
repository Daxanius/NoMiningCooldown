package me.daxanius.nmc;

import me.daxanius.nmc.platform.ForgeClientInputHelper;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(value = Constants.MOD_ID)
public class ForgeNoMiningCooldown {
    public ForgeNoMiningCooldown() {
        RegisterKeyMappingsEvent.BUS.addListener(ForgeClientInputHelper::registerBindings);
        TickEvent.ClientTickEvent.Post.BUS.addListener(ForgeClientInputHelper::onClientTick);
        NoMiningCooldown.initClient();
    }
}
