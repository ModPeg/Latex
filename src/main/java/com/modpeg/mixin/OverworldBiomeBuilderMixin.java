package com.modpeg.mixin;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.Consumer;
@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {
    private static final ResourceKey<Biome> RAINFOREST_KEY = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("latex", "rainforest"));
    @Inject(method = "addBiomes", at = @At("HEAD"))
    private void latex$addRainforest(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        consumer.accept(Pair.of(
            new Climate.ParameterPoint(
                Climate.Parameter.span(0.2f, 0.55f),
                Climate.Parameter.span(0.1f, 1.0f),
                Climate.Parameter.span(-0.11f, 0.3f),
                Climate.Parameter.span(0.0f, 0.45f),
                Climate.Parameter.point(0.0f),
                Climate.Parameter.span(-1.0f, 1.0f),
                0L
            ),
            RAINFOREST_KEY
        ));
    }
}
