package me.daxanius.nmc.platform;

import me.daxanius.nmc.Constants;
import me.daxanius.nmc.platform.services.IClientInputHelper;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NeoForgeClientInputHelper implements IClientInputHelper {
    private static final List<Runnable> tickCallbacks = new ArrayList<>();
    private static final List<KeyMapping> keyMappings = new ArrayList<>();

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping key : keyMappings) {
            event.register(key);
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        tickCallbacks.forEach(Runnable::run);
    }

    @Override
    public Object registerKeyBinding(String translationKey, int defaultKeyCode, KeyMapping.Category category) {
        KeyMapping key = new KeyMapping(translationKey, defaultKeyCode, category);
        keyMappings.add(key);
        return key;
    }

    @Override
    public void onClientTick(Runnable callback) {
        tickCallbacks.add(callback);
    }

    @Override
    public boolean wasPressed(Object keyBinding) {
        if (keyBinding instanceof KeyMapping key) {
            return key.consumeClick();
        }
        return false;
    }
}
