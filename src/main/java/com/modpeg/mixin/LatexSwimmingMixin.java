package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Entity.class)
public abstract class LatexSwimmingMixin {
    @Shadow
    protected boolean wasTouchingWater;
    @Inject(method = "updateFluidInteraction", at = @At("TAIL"))
    private void latex$handleLatexSwimming(CallbackInfoReturnable<Boolean> cir) {
        if (!this.wasTouchingWater) {
            Entity self = (Entity) (Object) this;
            AABB box = self.getBoundingBox();
            boolean inLatex = false;
            for (BlockPos pos : BlockPos.betweenClosed(
                    BlockPos.containing(box.minX, box.minY, box.minZ),
                    BlockPos.containing(box.maxX, box.maxY, box.maxZ))) {
                FluidState state = self.level().getFluidState(pos);
                if (state.getType() instanceof LatexFluid) {
                    inLatex = true;
                    break;
                }
            }
            if (inLatex) {
                this.wasTouchingWater = true;
                self.resetFallDistance();
            }
        }
    }
}
