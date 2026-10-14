package com.modpeg;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.function.Function;
import net.minecraft.util.random.WeightedList;
public final class ModBlocks {
    private static final ResourceKey<Feature> RUBBER_TREE_KEY = ResourceKey.create(
            Registries.FEATURE, Identifier.fromNamespaceAndPath("latex", "rubber"));
    private static final ResourceKey<Feature> MEGA_RUBBER_TREE_KEY = ResourceKey.create(
            Registries.FEATURE, Identifier.fromNamespaceAndPath("latex", "mega_rubber"));
    private static final TreeGrower RUBBER_TREE_GROWER = new TreeGrower("rubber",
            WeightedList.of(RUBBER_TREE_KEY), WeightedList.of(MEGA_RUBBER_TREE_KEY), WeightedList.of(), RUBBER_TREE_KEY);
    public static final Block RUBBER_LOG = register("rubber_log", RubberLogBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final Block RUBBER_WOOD = register("rubber_wood", RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final Block STRIPPED_RUBBER_LOG = register("stripped_rubber_log", RubberLogBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_RUBBER_WOOD = register("stripped_rubber_wood", RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));
    public static final Block RUBBER_PLANKS = register("rubber_planks", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final Block RUBBER_LEAVES = register("rubber_leaves",
            props -> new TintedParticleLeavesBlock(0.1F, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final Block RUBBER_SAPLING = register("rubber_sapling",
            props -> new SaplingBlock(RUBBER_TREE_GROWER, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final Block RUBBER_DOOR = register("rubber_door",
            props -> new DoorBlock(ModWoodTypes.RUBBER_BLOCK_SET_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final Block RUBBER_TRAPDOOR = register("rubber_trapdoor",
            props -> new TrapDoorBlock(ModWoodTypes.RUBBER_BLOCK_SET_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final Block RUBBER_FENCE = register("rubber_fence", FenceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final Block RUBBER_FENCE_GATE = register("rubber_fence_gate",
            props -> new FenceGateBlock(ModWoodTypes.RUBBER_WOOD_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final Block RUBBER_PRESSURE_PLATE = register("rubber_pressure_plate",
            props -> new PressurePlateBlock(ModWoodTypes.RUBBER_BLOCK_SET_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final Block RUBBER_BUTTON = register("rubber_button",
            props -> new ButtonBlock(ModWoodTypes.RUBBER_BLOCK_SET_TYPE, 30, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));
    public static final Block RUBBER_SLAB = register("rubber_slab", SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
    public static final Block RUBBER_STAIRS = register("rubber_stairs",
            props -> new RubberStairBlock(RUBBER_PLANKS.defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final Block RUBBER_SIGN = register("rubber_sign",
            props -> new StandingSignBlock(ModWoodTypes.RUBBER_WOOD_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));
    public static final Block RUBBER_WALL_SIGN = register("rubber_wall_sign",
            props -> new WallSignBlock(ModWoodTypes.RUBBER_WOOD_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN));
    public static final Block RUBBER_HANGING_SIGN = register("rubber_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodTypes.RUBBER_WOOD_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));
    public static final Block RUBBER_WALL_HANGING_SIGN = register("rubber_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodTypes.RUBBER_WOOD_TYPE, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN));
    public static final Block RUBBER_SHELF = register("rubber_shelf", ShelfBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF));
    public static final Block LATEX_CAULDRON = register("latex_cauldron", LatexCauldronBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
    public static final Block LATEX = register("latex",
            props -> new LatexLiquidBlock(ModFluids.STILL_LATEX, props),
            BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollision()
                    .strength(100.0f).pushReaction(PushReaction.POPPED).noLootTable().liquid().sound(SoundType.EMPTY));
    public static final Block BLOCK_OF_RUBBER = register("block_of_rubber", SlimeBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK));
    private ModBlocks() {
    }
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory,
                                  BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("latex", name));
        Block block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
    public static void register() {
        BlockTransformerHelper.registerStripping(RUBBER_LOG, STRIPPED_RUBBER_LOG);
        BlockTransformerHelper.registerStripping(RUBBER_WOOD, STRIPPED_RUBBER_WOOD);
    }
    public static class RubberStairBlock extends StairBlock {
        public RubberStairBlock(BlockState baseState, BlockBehaviour.Properties properties) {
            super(baseState, properties);
        }
    }
}