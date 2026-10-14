package com.modpeg.mixin;
import com.modpeg.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(PistonStructureResolver.class)
public class PistonStructureResolverMixin {
    @Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
    private static void latex$isSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(ModBlocks.BLOCK_OF_RUBBER)) {
            cir.setReturnValue(true);
        }
    }
}