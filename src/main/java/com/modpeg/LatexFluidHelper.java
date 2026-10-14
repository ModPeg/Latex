package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FluidState;
public final class LatexFluidHelper {
    private LatexFluidHelper() {}
    public static boolean isInLatex(Entity entity) {
        BlockPos pos = entity.blockPosition();
        return entity.level().getFluidState(pos).getType() instanceof LatexFluid
            || entity.level().getFluidState(pos.below()).getType() instanceof LatexFluid;
    }
    public static double getLatexSurfaceY(Entity entity) {
        BlockPos pos = entity.blockPosition();
        FluidState fluid = entity.level().getFluidState(pos);
        if (fluid.getType() instanceof LatexFluid) {
            return pos.getY() + fluid.getHeight(entity.level(), pos);
        }
        BlockPos below = pos.below();
        fluid = entity.level().getFluidState(below);
        if (fluid.getType() instanceof LatexFluid) {
            return below.getY() + fluid.getHeight(entity.level(), below);
        }
        return Double.NEGATIVE_INFINITY;
    }
}
