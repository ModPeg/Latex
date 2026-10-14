package com.modpeg.mixin;
import com.modpeg.LatexFluid;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.environment.WaterFogEnvironment;
import net.minecraft.world.level.material.FluidState;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(WaterFogEnvironment.class)
public class LatexFogColorMixin {
    @Inject(method = "getBaseColor", at = @At("HEAD"), cancellable = true)
    private void latex$whiteFog(ClientLevel level, Camera camera, int renderDistance, float partialTicks, CallbackInfoReturnable<Vector3fc> cir) {
        FluidState fluidState = level.getFluidState(camera.blockPosition());
        if (fluidState.getType() instanceof LatexFluid) {
            cir.setReturnValue(new Vector3f(1.0F, 1.0F, 1.0F));
        }
    }
}
