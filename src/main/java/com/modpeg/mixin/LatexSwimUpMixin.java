package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Entity.class)
public abstract class LatexSwimUpMixin {
    @Inject(method = "getFluidHeight", at = @At("HEAD"), cancellable = true)
    private void latex$fluidHeight(TagKey<Fluid> tag, CallbackInfoReturnable<Double> cir) {
        if (tag == FluidTags.WATER) {
            Entity self = (Entity) (Object) this;
            BlockPos pos = self.blockPosition();
            FluidState fluid = self.level().getFluidState(pos);
            if (fluid.getType() instanceof LatexFluid) {
                cir.setReturnValue((double) fluid.getHeight(self.level(), pos));
            } else {
                BlockPos below = pos.below();
                FluidState belowFluid = self.level().getFluidState(below);
                if (belowFluid.getType() instanceof LatexFluid) {
                    cir.setReturnValue((double) belowFluid.getHeight(self.level(), below));
                }
            }
        }
    }
}
