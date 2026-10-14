package com.modpeg;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import java.util.Set;
public final class ModBlockEntityTypes {
    public static final BlockEntityType<LatexCauldronBlockEntity> LATEX_CAULDRON = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Identifier.fromNamespaceAndPath("latex", "latex_cauldron"),
            new BlockEntityType<>(LatexCauldronBlockEntity::new, Set.of(ModBlocks.LATEX_CAULDRON)));
    private ModBlockEntityTypes() {
    }
    public static void register() {
    }
}