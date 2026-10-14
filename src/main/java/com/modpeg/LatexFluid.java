package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import java.util.Optional;
public abstract class LatexFluid extends FlowingFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LATEX;
    }
    @Override
    public Fluid getSource() {
        return ModFluids.STILL_LATEX;
    }
    @Override
    public Item getBucket() {
        return ModItems.LATEX_BUCKET;
    }
    @Override
    protected boolean canConvertToSource(ServerLevel level) {
        return (Boolean) level.getGameRules().get(GameRules.WATER_SOURCE_CONVERSION);
    }
    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        Block.dropResources(state, level, pos, level.getBlockEntity(pos));
    }
    @Override
    protected int getSlopeFindDistance(LevelReader level) {
        return 7;
    }
    @Override
    protected int getDropOff(LevelReader level) {
        return 1;
    }
    @Override
    public int getTickDelay(LevelReader level) {
        return 5;
    }
    @Override
    protected float getExplosionResistance() {
        return 100.0f;
    }
    @Override
    public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER);
    }
    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return ModBlocks.LATEX.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }
    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == ModFluids.STILL_LATEX || fluid == ModFluids.FLOWING_LATEX;
    }
    public static class Source extends LatexFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
    public static class Flowing extends LatexFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(BlockStateProperties.LEVEL_FLOWING);
        }
        @Override
        public int getAmount(FluidState state) {
            return state.getValue(BlockStateProperties.LEVEL_FLOWING);
        }
        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }
}