package com.modpeg;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
public final class ModTiers {
    private ModTiers() {}
    public static final TagKey<Item> RUBBER_TOOL_MATERIALS = TagKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("latex", "rubber_tool_materials"));
    public static final ToolMaterial RUBBER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            6248,
            4.0f,
            1.0f,
            1,
            RUBBER_TOOL_MATERIALS
    );
    public static final ToolMaterial RUBBER_AXE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            6248,
            4.0f,
            2.0f,
            14,
            RUBBER_TOOL_MATERIALS
    );
    public static final ToolMaterial RUBBER_SHOVEL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            6248,
            4.0f,
            2.0f,
            14,
            RUBBER_TOOL_MATERIALS
    );
    public static final ToolMaterial RUBBER_HOE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            6248,
            4.0f,
            2.0f,
            14,
            RUBBER_TOOL_MATERIALS
    );
    public static final ToolMaterial RUBBER_SWORD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            6248,
            4.0f,
            2.0f,
            14,
            RUBBER_TOOL_MATERIALS
    );
    public static final ToolMaterial RUBBER_SPEAR = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,
            9.0f,
            4.0f,
            15,
            RUBBER_TOOL_MATERIALS
    );
}