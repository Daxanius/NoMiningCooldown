package me.daxanius.nmc.mixin;

import me.daxanius.nmc.NoMiningCooldown;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @ModifyConstant(method = "continueDestroyBlock", constant = @Constant(intValue = 5))
    private int MiningCooldownFix(int value) {
        return NoMiningCooldown.cooldownFixEnabled ? 0 : value;
    }
}