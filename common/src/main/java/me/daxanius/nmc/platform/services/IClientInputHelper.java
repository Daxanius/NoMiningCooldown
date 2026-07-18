package me.daxanius.nmc.platform.services;

import net.minecraft.client.KeyMapping;

public interface IClientInputHelper {
    /**
     * Register a key binding.
     * @param translationKey  The translation key (e.g. "key.nmc.toggle")
     * @param defaultKeyCode  The default GLFW key code (e.g. GLFW.GLFW_KEY_G)
     * @param category        The keybinding category translation key.
     * @return An opaque handle (could be any platform-specific object)
     */
    Object registerKeyBinding(String translationKey, int defaultKeyCode, KeyMapping.Category category);

    /**
     * Register a callback to run each client tick.
     */
    void onClientTick(Runnable callback);

    /**
     * Whether the given keybinding was pressed this tick.
     */
    boolean wasPressed(Object keyBinding);
}
