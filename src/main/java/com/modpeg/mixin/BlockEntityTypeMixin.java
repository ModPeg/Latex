package com.modpeg.mixin;
import com.modpeg.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void latex$allowRubberShelf(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == BlockEntityTypes.SHELF && state.is(ModBlocks.RUBBER_SHELF)) {
            cir.setReturnValue(true);
        }
    }
}