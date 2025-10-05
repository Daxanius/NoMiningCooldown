package me.daxanius.nmc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class NoMiningCooldownClient implements ClientModInitializer {
    // Keybindings
    private static KeyBinding toggleCooldownKeyBinding;

    // Variables
    public static boolean cooldownFixEnabled = true;

    @Override
    public void onInitializeClient() {
        // Register keybinds
        toggleCooldownKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nmc.toggle",    // Keybinding translation
                InputUtil.Type.KEYSYM,   // Input type
                GLFW.GLFW_KEY_G,         // Keycode
                KeyBinding.Category.MISC // Category translation
        ));

        // Add key listener
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleCooldownKeyBinding.wasPressed()) {
                if (client.player == null) {
                    continue;
                }

                // Toggle the cool-down fix
                cooldownFixEnabled = !cooldownFixEnabled;
                client.player.sendMessage(Text.translatable(cooldownFixEnabled ? "message.nmc.cooldown_disabled" : "message.nmc.cooldown_enabled"), true);
            }
        });
    }
}