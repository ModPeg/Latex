package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.redstone.Orientation;
public class RubberLogBlock extends RotatedPillarBlock {
    private static final int TICK_DELAY = 20;
    private static final int MAX_SCAN = 16;
    public RubberLogBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide()
                && state.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y
                && hasVanillaCauldronBelow(level, pos)) {
            level.scheduleTick(pos, this, TICK_DELAY);
        }
    }
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide()
                && state.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y
                && hasVanillaCauldronBelow(level, pos)) {
            level.scheduleTick(pos, this, TICK_DELAY);
        }
    }
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(BlockStateProperties.AXIS) != Direction.Axis.Y) {
            return;
        }
        BlockPos below = pos.below();
        int scanned = 0;
        while (scanned < MAX_SCAN && level.getBlockState(below).isAir()) {
            below = below.below();
            scanned++;
        }
        BlockState belowState = level.getBlockState(below);
        if (belowState.getBlock() instanceof CauldronBlock) {
            Latex.LOGGER.debug("RubberLog converting cauldron at {}", below);
            level.setBlock(below, ModBlocks.LATEX_CAULDRON.defaultBlockState()
                    .setValue(LatexCauldronBlock.LEVEL, 0), 3);
        }
    }
    private static boolean hasVanillaCauldronBelow(Level level, BlockPos pos) {
        BlockPos below = pos.below();
        int scanned = 0;
        while (scanned < MAX_SCAN && level.getBlockState(below).isAir()) {
            below = below.below();
            scanned++;
        }
        return level.getBlockState(below).getBlock() instanceof CauldronBlock;
    }
}