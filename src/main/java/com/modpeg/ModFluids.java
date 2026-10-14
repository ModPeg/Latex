package com.modpeg;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
public final class ModFluids {
    public static final FlowingFluid STILL_LATEX = Registry.register(BuiltInRegistries.FLUID,
            ResourceKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("latex", "latex")),
            new LatexFluid.Source());
    public static final FlowingFluid FLOWING_LATEX = Registry.register(BuiltInRegistries.FLUID,
            ResourceKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("latex", "flowing_latex")),
            new LatexFluid.Flowing());
    private ModFluids() {
    }
    public static void register() {
        for (Fluid fluid : new Fluid[]{STILL_LATEX, FLOWING_LATEX}) {
            fluid.getStateDefinition().getPossibleStates().forEach(Fluid.FLUID_STATE_REGISTRY::add);
        }
    }
}