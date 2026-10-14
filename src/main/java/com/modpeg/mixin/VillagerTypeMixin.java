package com.modpeg.mixin;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(VillagerType.class)
public class VillagerTypeMixin {
    private static final ResourceKey<Biome> RAINFOREST_KEY = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("latex", "rainforest"));
    @Inject(method = "byBiome", at = @At("HEAD"), cancellable = true)
    private static void latex$rainforestSwampOutfit(Holder<Biome> biome, CallbackInfoReturnable<ResourceKey<VillagerType>> cir) {
        if (biome.is(RAINFOREST_KEY)) {
            cir.setReturnValue(VillagerType.SWAMP);
        }
    }
}
