package me.daxanius.nmc;

import me.daxanius.nmc.platform.Services;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

public class NoMiningCooldown {
    private static Object toggleKey;
    public static boolean cooldownFixEnabled = true;

    public static void initClient() {
        toggleKey = Services.CLIENT_INPUT.registerKeyBinding(
                "key.nmc.toggle",
                10, // G key
                KeyMapping.Category.MISC
        );

        Services.CLIENT_INPUT.onClientTick(() -> {
            if (Services.CLIENT_INPUT.wasPressed(toggleKey)) {
                var client = net.minecraft.client.Minecraft.getInstance();
                if (client.player != null) {
                    cooldownFixEnabled = !cooldownFixEnabled;
                    client.player.sendOverlayMessage(Component.translatable(
                            cooldownFixEnabled ? "message.nmc.cooldown_disabled" : "message.nmc.cooldown_enabled"
                    ));
                }
            }
        });
    }
}
