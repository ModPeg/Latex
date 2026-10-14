package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Camera.class)
public class LatexCameraMixin {
    @Inject(method = "getFluidInCamera", at = @At("RETURN"), cancellable = true)
    private void latex$fogType(CallbackInfoReturnable<FogType> cir) {
        if (cir.getReturnValue() == FogType.NONE) {
            Camera camera = (Camera) (Object) this;
            Entity entity = camera.entity();
            if (entity != null) {
                BlockPos pos = camera.blockPosition();
                Level level = entity.level();
                FluidState fluidState = level.getFluidState(pos);
                if (fluidState.getType() instanceof LatexFluid) {
                    Vec3 camPos = camera.position();
                    double fluidTop = pos.getY() + fluidState.getHeight(level, pos);
                    if (camPos.y < fluidTop) {
                        cir.setReturnValue(FogType.WATER);
                    }
                }
            }
        }
    }
}