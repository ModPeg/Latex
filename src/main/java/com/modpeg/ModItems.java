package com.modpeg;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.component.KineticWeapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import java.util.Optional;
public final class ModItems {
    public static final Item RUBBER_LOG = blockItem("rubber_log", ModBlocks.RUBBER_LOG);
    public static final Item RUBBER_WOOD = blockItem("rubber_wood", ModBlocks.RUBBER_WOOD);
    public static final Item STRIPPED_RUBBER_LOG = blockItem("stripped_rubber_log", ModBlocks.STRIPPED_RUBBER_LOG);
    public static final Item STRIPPED_RUBBER_WOOD = blockItem("stripped_rubber_wood", ModBlocks.STRIPPED_RUBBER_WOOD);
    public static final Item RUBBER_PLANKS = blockItem("rubber_planks", ModBlocks.RUBBER_PLANKS);
    public static final Item RUBBER_LEAVES = blockItem("rubber_leaves", ModBlocks.RUBBER_LEAVES);
    public static final Item RUBBER_SAPLING = blockItem("rubber_sapling", ModBlocks.RUBBER_SAPLING);
    public static final Item RUBBER_DOOR = blockItem("rubber_door", ModBlocks.RUBBER_DOOR);
    public static final Item RUBBER_TRAPDOOR = blockItem("rubber_trapdoor", ModBlocks.RUBBER_TRAPDOOR);
    public static final Item RUBBER_FENCE = blockItem("rubber_fence", ModBlocks.RUBBER_FENCE);
    public static final Item RUBBER_FENCE_GATE = blockItem("rubber_fence_gate", ModBlocks.RUBBER_FENCE_GATE);
    public static final Item RUBBER_PRESSURE_PLATE = blockItem("rubber_pressure_plate", ModBlocks.RUBBER_PRESSURE_PLATE);
    public static final Item RUBBER_BUTTON = blockItem("rubber_button", ModBlocks.RUBBER_BUTTON);
    public static final Item RUBBER_SLAB = blockItem("rubber_slab", ModBlocks.RUBBER_SLAB);
    public static final Item RUBBER_STAIRS = blockItem("rubber_stairs", ModBlocks.RUBBER_STAIRS);
    public static final Item RUBBER_SIGN = register("rubber_sign",
            new StandingAndWallBlockItem(ModBlocks.RUBBER_SIGN, ModBlocks.RUBBER_WALL_SIGN, Direction.DOWN,
                    new Item.Properties().useBlockDescriptionPrefix().setId(itemKey("rubber_sign")).signText()));
    public static final Item RUBBER_HANGING_SIGN = register("rubber_hanging_sign",
            new HangingSignItem(ModBlocks.RUBBER_HANGING_SIGN, ModBlocks.RUBBER_WALL_HANGING_SIGN,
                    new Item.Properties().useBlockDescriptionPrefix().setId(itemKey("rubber_hanging_sign"))));
    public static final Item RUBBER_BOAT = register("rubber_boat",
            new BoatItem(ModBoatTypes.RUBBER_BOAT_TYPE,
                    new Item.Properties().stacksTo(1).setId(itemKey("rubber_boat"))));
    public static final Item RUBBER_CHEST_BOAT = register("rubber_chest_boat",
            new BoatItem(ModBoatTypes.RUBBER_CHEST_BOAT_TYPE,
                    new Item.Properties().stacksTo(1).setId(itemKey("rubber_chest_boat"))));
    public static final Item RUBBER_SHELF = blockItem("rubber_shelf", ModBlocks.RUBBER_SHELF);
    public static final Item LATEX_BUCKET = register("latex_bucket",
            new BucketItem(ModFluids.STILL_LATEX, new Item.Properties().stacksTo(1).setId(itemKey("latex_bucket"))));
    public static final Item LOOSE_RUBBER = register("loose_rubber",
            new Item(new Item.Properties().setId(itemKey("loose_rubber"))));
    public static final Item BLOCK_OF_RUBBER = blockItem("block_of_rubber", ModBlocks.BLOCK_OF_RUBBER);
    public static final Item RUBBER_PICKAXE = register("rubber_pickaxe",
            new RubberPickaxeItem(new Item.Properties().setId(itemKey("rubber_pickaxe")).pickaxe(ModTiers.RUBBER, 1.0f, -2.8f).component(DataComponents.ENCHANTABLE, null)));
    public static final Item RUBBER_AXE = register("rubber_axe",
            new RubberAxeItem(new Item.Properties().setId(itemKey("rubber_axe")).axe(ModTiers.RUBBER_AXE, 6.0F, -3.1F)));
    public static final Item RUBBER_SHOVEL = register("rubber_shovel",
            new RubberShovelItem(new Item.Properties().setId(itemKey("rubber_shovel")).shovel(ModTiers.RUBBER_SHOVEL, 1.5F, -3.0F)));
    public static final Item RUBBER_HOE = register("rubber_hoe",
            new RubberHoeItem(new Item.Properties().setId(itemKey("rubber_hoe")).hoe(ModTiers.RUBBER_HOE, -2.0F, -1.0F)));
    public static final Item RUBBER_SWORD = register("rubber_sword",
            new RubberSwordItem(new Item.Properties().setId(itemKey("rubber_sword")).sword(ModTiers.RUBBER_SWORD, 3.0F, -2.4F)));
    public static final Item RUBBER_SPEAR = register("rubber_spear",
            new RubberSpearItem(new Item.Properties().setId(itemKey("rubber_spear")).spear(ModTiers.RUBBER_SPEAR, 1.15F, 1.2F, 0.4F, 2.5F, 9.0F, 5.5F, 5.1F, 8.75F, 4.6F).component(DataComponents.KINETIC_WEAPON, new KineticWeapon(10, 8, KineticWeapon.Condition.ofAttackerSpeed(1_000_000, 9.0F), KineticWeapon.Condition.ofAttackerSpeed(1_000_000, 5.1F), KineticWeapon.Condition.ofRelativeSpeed(1_000_000, 4.6F), 0.38F, 1.2F, Optional.of(SoundEvents.SPEAR_USE), Optional.of(SoundEvents.SPEAR_HIT))).fireResistant()));
    public static final Item RUBBER_BOOTS = register("rubber_boots",
            new Item(new Item.Properties().setId(itemKey("rubber_boots")).humanoidArmor(ModArmorMaterials.RUBBER, ArmorType.BOOTS).rarity(Rarity.UNCOMMON)));
    public static final Item RUBBER_LEGGINGS = register("rubber_leggings",
            new Item(new Item.Properties().setId(itemKey("rubber_leggings")).humanoidArmor(ModArmorMaterials.RUBBER_IRON, ArmorType.LEGGINGS).rarity(Rarity.UNCOMMON)));
    public static final Item RUBBER_CHESTPLATE = register("rubber_chestplate",
            new Item(new Item.Properties().setId(itemKey("rubber_chestplate")).humanoidArmor(ModArmorMaterials.RUBBER_IRON, ArmorType.CHESTPLATE).rarity(Rarity.UNCOMMON)));
    public static final Item RUBBER_HELMET = register("rubber_helmet",
            new Item(new Item.Properties().setId(itemKey("rubber_helmet")).humanoidArmor(ModArmorMaterials.RUBBER_IRON, ArmorType.HELMET).rarity(Rarity.UNCOMMON)));
    public static final Item RUBBERPEARL = register("rubberpearl",
            new RubberpearlItem(new Item.Properties().setId(itemKey("rubberpearl")).stacksTo(16).useCooldown(1.0F)));
    private static final ResourceKey<CreativeModeTab> BUILDING_BLOCKS = tabKey("building_blocks");
    private static final ResourceKey<CreativeModeTab> COMBAT = tabKey("combat");
    private static final ResourceKey<CreativeModeTab> NATURAL_BLOCKS = tabKey("natural_blocks");
    private static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES = tabKey("tools_and_utilities");
    private static final ResourceKey<CreativeModeTab> INGREDIENTS = tabKey("ingredients");
    private ModItems() {
    }
    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("latex", name));
    }
    private static ResourceKey<CreativeModeTab> tabKey(String name) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath("minecraft", name));
    }
    private static Item blockItem(String name, Block block) {
        ResourceKey<Item> key = itemKey(name);
        Item item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, itemKey(name), item);
    }
    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(RUBBER_PLANKS, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_LOG, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_WOOD, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(STRIPPED_RUBBER_LOG, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(STRIPPED_RUBBER_WOOD, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_STAIRS, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_FENCE, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_FENCE_GATE, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_PRESSURE_PLATE, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_DOOR, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_TRAPDOOR, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_SHELF, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_SLAB, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_BUTTON, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_SAPLING, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_SIGN, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_HANGING_SIGN, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_BOAT, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_BOATS, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
            context.modify(RUBBER_CHEST_BOAT, builder -> builder.set(DataComponents.COOKING_FUEL,
                    new CookingFuel(ContextIntProviders.COOKING_TIME_BOATS, ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)));
        });
        CreativeModeTabEvents.modifyOutputEvent(BUILDING_BLOCKS).register(output -> {
            output.accept(new ItemStack(RUBBER_LOG), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_WOOD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(STRIPPED_RUBBER_LOG), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(STRIPPED_RUBBER_WOOD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_PLANKS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_STAIRS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SLAB), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_FENCE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_FENCE_GATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_DOOR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_TRAPDOOR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_PRESSURE_PLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_BUTTON), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SIGN), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_HANGING_SIGN), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SHELF), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(BLOCK_OF_RUBBER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
        CreativeModeTabEvents.modifyOutputEvent(NATURAL_BLOCKS).register(output -> {
            output.accept(new ItemStack(RUBBER_LOG), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_LEAVES), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SAPLING), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
        CreativeModeTabEvents.modifyOutputEvent(TOOLS_AND_UTILITIES).register(output -> {
            output.accept(new ItemStack(RUBBER_BOAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_CHEST_BOAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SHOVEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_HOE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_SPEAR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
        CreativeModeTabEvents.modifyOutputEvent(INGREDIENTS).register(output -> {
            output.accept(new ItemStack(LATEX_BUCKET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(LOOSE_RUBBER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBERPEARL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
        CreativeModeTabEvents.modifyOutputEvent(COMBAT).register(output -> {
            output.accept(new ItemStack(RUBBER_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            output.accept(new ItemStack(RUBBER_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
    }
}