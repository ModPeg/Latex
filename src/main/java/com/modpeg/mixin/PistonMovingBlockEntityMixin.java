package com.modpeg.mixin;
import com.modpeg.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(PistonMovingBlockEntity.class)
public class PistonMovingBlockEntityMixin {
    @Redirect(method = "moveCollidedEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
    private static boolean latex$isSlimeBlock(BlockState state, Object block) {
        return state.is(Blocks.SLIME_BLOCK) || state.is(ModBlocks.BLOCK_OF_RUBBER);
    }
}