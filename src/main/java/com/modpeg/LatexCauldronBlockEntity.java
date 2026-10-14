package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
public class LatexCauldronBlockEntity extends BlockEntity {
    private static final int FILL_TICKS = 400;
    private static final int MAX_SCAN = 16;
    private int progress = 0;
    public LatexCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.LATEX_CAULDRON, pos, state);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, LatexCauldronBlockEntity blockEntity) {
        if (level.isClientSide()) {
            return;
        }
        BlockPos logPos = null;
        BlockPos above = pos.above();
        for (int i = 0; i < MAX_SCAN; i++) {
            BlockState s = level.getBlockState(above);
            if ((s.is(ModBlocks.RUBBER_LOG) || s.is(ModBlocks.STRIPPED_RUBBER_LOG))
                    && s.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y) {
                logPos = above;
                break;
            }
            if (!s.isAir()) {
                break;
            }
            above = above.above();
        }
        if (logPos == null) {
            if (blockEntity.progress != 0) {
                Latex.LOGGER.debug("Cauldron BE at {} not dripping, progress reset", pos);
            }
            blockEntity.progress = 0;
            return;
        }
        blockEntity.progress++;
        int currentLevel = state.getValue(LatexCauldronBlock.LEVEL);
        if (blockEntity.progress % 20 == 0) {
            Latex.LOGGER.debug("Cauldron BE at {} dripping, progress={} level={}", pos, blockEntity.progress, currentLevel);
            level.addParticle(ParticleTypes.DRIPPING_WATER,
                    logPos.getX() + 0.5, logPos.getY(), logPos.getZ() + 0.5,
                    0.0, 0.0, 0.0);
        }
        if (blockEntity.progress >= FILL_TICKS && currentLevel < 3) {
            blockEntity.progress = 0;
            Latex.LOGGER.debug("Cauldron BE at {} filling to level {}", pos, currentLevel + 1);
            level.setBlock(pos, state.setValue(LatexCauldronBlock.LEVEL, currentLevel + 1), 3);
        }
    }
}