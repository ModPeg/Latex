package com.modpeg;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
public final class ModBoatTypes {
    public static final EntityType<Boat> RUBBER_BOAT_TYPE = register("rubber_boat",
            EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, () -> ModItems.RUBBER_BOAT), MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .noLootTable()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath("latex", "rubber_boat"))));
    public static final EntityType<ChestBoat> RUBBER_CHEST_BOAT_TYPE = register("rubber_chest_boat",
            EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, () -> ModItems.RUBBER_CHEST_BOAT), MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .noLootTable()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath("latex", "rubber_chest_boat"))));
    private ModBoatTypes() {
    }
    private static <T extends EntityType<?>> T register(String name, T type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("latex", name)), type);
    }
    public static void register() {
    }
}