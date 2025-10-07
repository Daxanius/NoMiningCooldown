package me.daxanius.nmc;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = Constants.MOD_ID)
public class ForgeNoMiningCooldown {
    public ForgeNoMiningCooldown(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        FMLClientSetupEvent.getBus(modBusGroup).addListener(this::onClientSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        NoMiningCooldownClient.init();
    }
}