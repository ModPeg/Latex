package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {
    @Inject(method = "spreadTo", at = @At("HEAD"), cancellable = true)
    private void latex$preserveFire(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if ((Object) this instanceof LatexFluid && state.getBlock() instanceof FireBlock) {
            ci.cancel();
        }
    }
}