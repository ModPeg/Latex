package com.modpeg.mixin;
import com.modpeg.LatexCauldronBlock;
import com.modpeg.ModBlocks;
import com.modpeg.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(AbstractCauldronBlock.class)
public class CauldronPourMixin {
    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void latex$pourLatex(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hitResult,
                                 CallbackInfoReturnable<InteractionResult> cir) {
        if (!stack.is(ModItems.LATEX_BUCKET)) {
            return;
        }
        if (!(state.getBlock() instanceof CauldronBlock)) {
            return;
        }
        if (level.isClientSide()) {
            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
        }
        level.setBlock(pos, ModBlocks.LATEX_CAULDRON.defaultBlockState()
                .setValue(LatexCauldronBlock.LEVEL, 3), 3);
        ItemStack r = ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET));
        player.setItemInHand(hand, r);
        cir.setReturnValue(InteractionResult.SUCCESS);
    }
}