package me.daxanius.nmc.platform;

import me.daxanius.nmc.platform.services.IClientInputHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;

import java.util.ArrayList;
import java.util.List;

public class FabricClientInputHelper implements IClientInputHelper {
    private final List<Runnable> tickCallbacks = new ArrayList<>();

    @Override
    public Object registerKeyBinding(String translationKey, int defaultKeyCode, KeyMapping.Category category) {
        KeyMapping keyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                translationKey,
                InputConstants.Type.KEYSYM,
                defaultKeyCode,
                category
        ));
        return keyMapping;
    }

    @Override
    public void onClientTick(Runnable callback) {
        tickCallbacks.add(callback);
        // Only register once globally
        if (tickCallbacks.size() == 1) {
            ClientTickEvents.END_CLIENT_TICK.register(client -> tickCallbacks.forEach(Runnable::run));
        }
    }

    @Override
    public boolean wasPressed(Object keyBinding) {
        if (keyBinding instanceof KeyMapping key) {
            return key.consumeClick();
        }
        return false;
    }
}
