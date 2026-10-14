package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
public class LatexCauldronBlock extends AbstractCauldronBlock implements EntityBlock {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
    public LatexCauldronBlock(Properties properties) {
        super(properties, new CauldronInteraction.Dispatcher());
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) == 3;
    }
    @Override
    protected double getContentHeight(BlockState state) {
        return (6.0 + 3.0 * state.getValue(LEVEL)) / 16.0;
    }
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }
    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return state.getValue(LEVEL);
    }
    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(Items.BUCKET) && state.getValue(LEVEL) == 3) {
            if (!level.isClientSide()) {
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                ItemStack r = ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.LATEX_BUCKET));
                player.setItemInHand(hand, r);
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(ModItems.LATEX_BUCKET) && state.getValue(LEVEL) < 3) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(LEVEL, 3), 3);
                ItemStack r = ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET));
                player.setItemInHand(hand, r);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LatexCauldronBlockEntity(pos, state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModBlockEntityTypes.LATEX_CAULDRON
                ? (BlockEntityTicker<T>) (l, p, s, be) -> LatexCauldronBlockEntity.tick(l, p, s, (LatexCauldronBlockEntity) be)
                : null;
    }
}