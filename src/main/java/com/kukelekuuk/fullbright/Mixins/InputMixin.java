package com.kukelekuuk.fullbright.Mixins;

import com.kukelekuuk.fullbright.FullBright;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( MinecraftClient.class )
public class InputMixin
{

    @Inject( method = "handleInputEvents", at = @At( "HEAD" ), cancellable = true )
    private void handleInput( CallbackInfo callbackInfo )
    {
        if ( FullBright.keyBinding.wasPressed() )
        {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            player.world.playSound( player, player.getBlockPos(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1f, FullBright.isEnabled ? .5f : 1f );
            FullBright.isEnabled = !FullBright.isEnabled;
            MinecraftClient.getInstance().options.gamma = FullBright.isEnabled ? 15 : 1;
        }
    }
}
