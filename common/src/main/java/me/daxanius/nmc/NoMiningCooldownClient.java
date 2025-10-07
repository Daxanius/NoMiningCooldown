package me.daxanius.nmc;

import me.daxanius.nmc.platform.Services;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class NoMiningCooldownClient {
    private static Object toggleKey;
    public static boolean cooldownFixEnabled = true;

    public static void init() {
        toggleKey = Services.CLIENT_INPUT.registerKeyBinding(
                "key.nmc.toggle",
                GLFW.GLFW_KEY_G,
                KeyMapping.Category.MISC
        );

        Services.CLIENT_INPUT.onClientTick(() -> {
            if (Services.CLIENT_INPUT.wasPressed(toggleKey)) {
                var client = net.minecraft.client.Minecraft.getInstance();
                if (client.player != null) {
                    cooldownFixEnabled = !cooldownFixEnabled;
                    client.player.displayClientMessage(Component.translatable(
                            cooldownFixEnabled ? "message.nmc.cooldown_disabled" : "message.nmc.cooldown_enabled"
                    ), true);
                }
            }
        });
    }
}
