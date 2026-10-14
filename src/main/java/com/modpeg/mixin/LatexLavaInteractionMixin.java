package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import com.modpeg.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LiquidBlock.class)
public class LatexLavaInteractionMixin {
    @Shadow
    @Final
    protected FlowingFluid fluid;
    @Inject(method = "shouldSpreadLiquid", at = @At("HEAD"), cancellable = true)
    private void latex$lavaToRubber(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!this.fluid.is(FluidTags.LAVA)) return;
        if (!level.getFluidState(pos).isSource()) return;
        for (Direction dir : Direction.values()) {
            FluidState neighborFluid = level.getFluidState(pos.relative(dir));
            if (neighborFluid.getType() instanceof LatexFluid) {
                level.setBlockAndUpdate(pos, ModBlocks.BLOCK_OF_RUBBER.defaultBlockState());
                cir.setReturnValue(false);
                return;
            }
        }
    }
}