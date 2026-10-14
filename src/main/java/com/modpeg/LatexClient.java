package com.modpeg;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
public class LatexClient implements ClientModInitializer {
    public static final ModelLayerLocation RUBBER_BOAT_LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("latex", "boat/rubber"), "main");
    public static final ModelLayerLocation RUBBER_CHEST_BOAT_LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("latex", "chest_boat/rubber"), "main");
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(RUBBER_BOAT_LAYER, () -> BoatModel.createBoatModel());
        ModelLayerRegistry.registerModelLayer(RUBBER_CHEST_BOAT_LAYER, () -> BoatModel.createChestBoatModel());
        EntityRendererRegistry.register(ModBoatTypes.RUBBER_BOAT_TYPE, ctx -> new BoatRenderer(ctx, RUBBER_BOAT_LAYER));
        EntityRendererRegistry.register(ModBoatTypes.RUBBER_CHEST_BOAT_TYPE, ctx -> new BoatRenderer(ctx, RUBBER_CHEST_BOAT_LAYER));
        EntityRendererRegistry.register(ModEntityTypes.RUBBERPEARL_TYPE, ctx -> new ThrownItemRenderer(ctx));
        FluidRenderingRegistry.register(ModFluids.STILL_LATEX, ModFluids.FLOWING_LATEX,
                new FluidModel.Unbaked(
                        new Material(Identifier.fromNamespaceAndPath("latex", "block/latex_still")),
                        new Material(Identifier.fromNamespaceAndPath("latex", "block/latex_flow")),
                        null,
                        BlockTintSources.constant(0xFFFFFFFF)));
    }
}