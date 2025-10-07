package me.daxanius.nmc.mixin;

import me.daxanius.nmc.NoMiningCooldownClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
    private int MiningCooldownFix(int value) {
        return NoMiningCooldownClient.cooldownFixEnabled ? 0 : value;
    }
}