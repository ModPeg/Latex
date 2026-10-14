package com.modpeg.mixin;
import com.modpeg.LatexCauldronBlock;
import com.modpeg.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.redstone.Orientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(BlockBehaviour.class)
public class CauldronBlockMixin {
    @Inject(method = "onPlace", at = @At("HEAD"))
    private void latex$onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if (!(state.getBlock() instanceof CauldronBlock)) {
            return;
        }
        latex$tryConvert(level, pos);
    }
    @Inject(method = "neighborChanged", at = @At("HEAD"))
    private void latex$neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, Orientation orientation, boolean movedByPiston, CallbackInfo ci) {
        if (!(state.getBlock() instanceof CauldronBlock)) {
            return;
        }
        latex$tryConvert(level, pos);
    }
    private static final int MAX_SCAN = 16;
    private static void latex$tryConvert(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            return;
        }
        BlockPos above = pos.above();
        for (int i = 0; i < MAX_SCAN; i++) {
            BlockState aboveState = level.getBlockState(above);
            if ((aboveState.is(ModBlocks.RUBBER_LOG) || aboveState.is(ModBlocks.STRIPPED_RUBBER_LOG))
                    && aboveState.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y) {
                level.setBlock(pos, ModBlocks.LATEX_CAULDRON.defaultBlockState()
                        .setValue(LatexCauldronBlock.LEVEL, 0), 3);
                return;
            }
            if (!aboveState.isAir()) {
                return;
            }
            above = above.above();
        }
    }
}