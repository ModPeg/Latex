package com.modpeg;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
public final class ModWoodTypes {
    public static final BlockSetType RUBBER_BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK)
            .register(Identifier.fromNamespaceAndPath("latex", "rubber"));
    public static final WoodType RUBBER_WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.OAK)
            .register(Identifier.fromNamespaceAndPath("latex", "rubber"), RUBBER_BLOCK_SET_TYPE);
    private ModWoodTypes() {
    }
    public static void register() {
    }
}