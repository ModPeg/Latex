package com.modpeg;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
public class LatexLiquidBlock extends LiquidBlock {
    public LatexLiquidBlock(FlowingFluid fluid, BlockBehaviour.Properties properties) {
        super(fluid, properties);
    }
}