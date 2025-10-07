package me.daxanius.nmc;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class NoMiningCooldownClient implements ClientModInitializer {
    // Keybindings
    private static KeyMapping toggleCooldownKeyBinding;

    // Variables
    public static boolean cooldownFixEnabled = true;

    @Override
    public void onInitializeClient() {
        // Register keybinds
        toggleCooldownKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.nmc.toggle",     // Keybinding translation
                InputConstants.Type.KEYSYM, // Input type
                GLFW.GLFW_KEY_G,            // Keycode
                KeyMapping.CATEGORY_MISC    // Category translation
        ));

        // Add key listener
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleCooldownKeyBinding.consumeClick()) {
                if (client.player == null) {
                    continue;
                }

                // Toggle the cool-down fix
                cooldownFixEnabled = !cooldownFixEnabled;
                client.player.displayClientMessage(Component.translatable(cooldownFixEnabled ? "message.nmc.cooldown_disabled" : "message.nmc.cooldown_enabled"), true);
            }
        });
    }
}