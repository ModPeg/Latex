package com.modpeg;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
public final class ModEntityTypes {
    public static final EntityType<ThrownRubberpearl> RUBBERPEARL_TYPE = register("rubberpearl",
            EntityType.Builder.<ThrownRubberpearl>of(ThrownRubberpearl::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .noLootTable()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath("latex", "rubberpearl"))));
    private ModEntityTypes() {
    }
    private static <T extends EntityType<?>> T register(String name, T type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("latex", name)), type);
    }
    public static void register() {
    }
}
