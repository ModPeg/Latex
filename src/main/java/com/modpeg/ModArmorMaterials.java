package com.modpeg;
import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
public final class ModArmorMaterials {
    private ModArmorMaterials() {}
    public static final ArmorMaterial RUBBER = new ArmorMaterial(
            15,
            Map.of(ArmorType.BOOTS, 1, ArmorType.LEGGINGS, 4, ArmorType.CHESTPLATE, 5, ArmorType.HELMET, 2, ArmorType.BODY, 4),
            12,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F,
            0.0F,
            ModTiers.RUBBER_TOOL_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath("latex", "rubber")));
    public static final ArmorMaterial RUBBER_IRON = new ArmorMaterial(
            15,
            Map.of(ArmorType.BOOTS, 2, ArmorType.LEGGINGS, 5, ArmorType.CHESTPLATE, 6, ArmorType.HELMET, 2, ArmorType.BODY, 4),
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            ModTiers.RUBBER_TOOL_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath("latex", "rubber")));
}
