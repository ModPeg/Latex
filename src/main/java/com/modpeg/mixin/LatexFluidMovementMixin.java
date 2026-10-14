package com.modpeg.mixin;
import com.modpeg.LatexFluidHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)
public abstract class LatexFluidMovementMixin {
    @Inject(method = "travel", at = @At("TAIL"))
    private void latex$fluidEffects(Vec3 travelVector, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        double surfaceY = LatexFluidHelper.getLatexSurfaceY(self);
        if (surfaceY == Double.NEGATIVE_INFINITY) return;
        double feetY = self.getY();
        double prevFeetY = self.yo;
        Vec3 delta = self.getDeltaMovement();
        if (delta.y < 0 && prevFeetY > surfaceY && feetY <= surfaceY && !self.isSteppingCarefully()) {
            double vy = Math.abs(delta.y);
            double bounce = 0.4 + 0.2 * vy;
            self.setDeltaMovement(delta.multiply(1, bounce, 1));
            self.resetFallDistance();
        }
        else if (feetY < surfaceY - 0.5) {
            self.setDeltaMovement(delta.multiply(0.5, 0.7, 0.5));
        }
    }
}
