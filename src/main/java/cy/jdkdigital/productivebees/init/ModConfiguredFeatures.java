package cy.jdkdigital.productivebees.init;

import cy.jdkdigital.productivebees.ProductiveBees;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProductiveBees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfiguredFeatures
{
    public static ConfiguredFeature<?, ?> SAND_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> COARSE_DIRT_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> SPRUCE_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> ACACIA_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> JUNGLE_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> OAK_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> DARK_OAK_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> BIRCH_WOOD_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> STONE_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> SNOW_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> SNOW_NEST_BLOCK_FEATURE;
    public static ConfiguredFeature<?, ?> SLIMY_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> GLOWSTONE_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> NETHER_QUARTZ_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> NETHER_QUARTZ_NEST_HIGH_FEATURE;
    public static ConfiguredFeature<?, ?> NETHER_FORTRESS_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> SOUL_SAND_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> GRAVEL_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> OBSIDIAN_PILLAR_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> END_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> SUGAR_CANE_NEST_FEATURE;
    public static ConfiguredFeature<?, ?> BUMBLE_BEE_NEST_FEATURE;

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        SAND_NEST_FEATURE = Registry.register(registry, rLoc("sand_nest_feature"), ModFeatures.SAND_NEST.get().configured(new ReplaceBlockConfig(Blocks.SAND.defaultBlockState(), ModBlocks.SAND_NEST.get().defaultBlockState())));
        COARSE_DIRT_NEST_FEATURE = Registry.register(registry, rLoc("coarse_dirt_nest_feature"), ModFeatures.COARSE_DIRT_NEST.get().configured(new ReplaceBlockConfig(Blocks.COARSE_DIRT.defaultBlockState(), ModBlocks.COARSE_DIRT_NEST.get().defaultBlockState())));
        SPRUCE_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("spruce_wood_nest_feature"), ModFeatures.SPRUCE_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.SPRUCE_LOG.defaultBlockState(), ModBlocks.SPRUCE_WOOD_NEST.get().defaultBlockState())));
        ACACIA_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("acacia_wood_nest_feature"), ModFeatures.ACACIA_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.ACACIA_LOG.defaultBlockState(), ModBlocks.ACACIA_WOOD_NEST.get().defaultBlockState())));
        JUNGLE_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("jungle_wood_nest_feature"), ModFeatures.JUNGLE_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.JUNGLE_LOG.defaultBlockState(), ModBlocks.JUNGLE_WOOD_NEST.get().defaultBlockState())));
        OAK_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("oak_wood_nest_feature"), ModFeatures.OAK_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.OAK_LOG.defaultBlockState(), ModBlocks.OAK_WOOD_NEST.get().defaultBlockState())));
        DARK_OAK_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("dark_oak_wood_nest_feature"), ModFeatures.DARK_OAK_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.DARK_OAK_LOG.defaultBlockState(), ModBlocks.DARK_OAK_WOOD_NEST.get().defaultBlockState())));
        BIRCH_WOOD_NEST_FEATURE = Registry.register(registry, rLoc("birch_wood_nest_feature"), ModFeatures.BIRCH_WOOD_NEST_FEATURE.get().configured(new ReplaceBlockConfig(Blocks.BIRCH_LOG.defaultBlockState(), ModBlocks.BIRCH_WOOD_NEST.get().defaultBlockState())));
        STONE_NEST_FEATURE = Registry.register(registry, rLoc("stone_nest_feature"), ModFeatures.STONE_NEST.get().configured(new ReplaceBlockConfig(Blocks.STONE.defaultBlockState(), ModBlocks.STONE_NEST.get().defaultBlockState())));
        SNOW_NEST_FEATURE = Registry.register(registry, rLoc("snow_nest_feature"), ModFeatures.SNOW_NEST.get().configured(new ReplaceBlockConfig(Blocks.SNOW.defaultBlockState(), ModBlocks.SNOW_NEST.get().defaultBlockState())));
        SNOW_NEST_BLOCK_FEATURE = Registry.register(registry, rLoc("snow_nest_block_feature"), ModFeatures.SNOW_NEST.get().configured(new ReplaceBlockConfig(Blocks.SNOW_BLOCK.defaultBlockState(), ModBlocks.SNOW_NEST.get().defaultBlockState())));
        SLIMY_NEST_FEATURE = Registry.register(registry, rLoc("slimy_nest_feature"), ModFeatures.SLIMY_NEST.get().configured(new ReplaceBlockConfig(Blocks.GRASS_BLOCK.defaultBlockState(), ModBlocks.SLIMY_NEST.get().defaultBlockState())));
        GLOWSTONE_NEST_FEATURE = Registry.register(registry, rLoc("glowstone_nest_feature"), ModFeatures.GLOWSTONE_NEST.get().configured(new ReplaceBlockConfig(Blocks.GLOWSTONE.defaultBlockState(), ModBlocks.GLOWSTONE_NEST.get().defaultBlockState())));
        NETHER_QUARTZ_NEST_FEATURE = Registry.register(registry, rLoc("nether_quartz_nest_feature"), ModFeatures.NETHER_QUARTZ_NEST.get().configured(new ReplaceBlockConfig(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), ModBlocks.NETHER_QUARTZ_NEST.get().defaultBlockState())));
        NETHER_QUARTZ_NEST_HIGH_FEATURE = Registry.register(registry, rLoc("nether_quartz_nest_high_feature"), ModFeatures.NETHER_QUARTZ_NEST_HIGH.get().configured(new ReplaceBlockConfig(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), ModBlocks.NETHER_QUARTZ_NEST.get().defaultBlockState())));
        NETHER_FORTRESS_NEST_FEATURE = Registry.register(registry, rLoc("nether_fortress_nest_feature"), ModFeatures.NETHER_FORTRESS_NEST.get().configured(new ReplaceBlockConfig(Blocks.NETHER_BRICKS.defaultBlockState(), ModBlocks.NETHER_BRICK_NEST.get().defaultBlockState())));
        SOUL_SAND_NEST_FEATURE = Registry.register(registry, rLoc("soul_sand_nest_feature"), ModFeatures.SOUL_SAND_NEST.get().configured(new ReplaceBlockConfig(Blocks.SOUL_SAND.defaultBlockState(), ModBlocks.SOUL_SAND_NEST.get().defaultBlockState())));
        GRAVEL_NEST_FEATURE = Registry.register(registry, rLoc("gravel_nest_feature"), ModFeatures.GRAVEL_NEST.get().configured(new ReplaceBlockConfig(Blocks.GRAVEL.defaultBlockState(), ModBlocks.GRAVEL_NEST.get().defaultBlockState())));
        OBSIDIAN_PILLAR_NEST_FEATURE = Registry.register(registry, rLoc("obsidian_pillar_nest_feature"), ModFeatures.OBSIDIAN_PILLAR_NEST.get().configured(new ReplaceBlockConfig(Blocks.OBSIDIAN.defaultBlockState(), ModBlocks.OBSIDIAN_PILLAR_NEST.get().defaultBlockState())));
        END_NEST_FEATURE = Registry.register(registry, rLoc("end_nest_feature"), ModFeatures.END_NEST.get().configured(new ReplaceBlockConfig(Blocks.END_STONE.defaultBlockState(), ModBlocks.END_NEST.get().defaultBlockState())));
        SUGAR_CANE_NEST_FEATURE = Registry.register(registry, rLoc("sugar_cane_nest_feature"), ModFeatures.SUGAR_CANE_NEST.get().configured(new ReplaceBlockConfig(Blocks.SUGAR_CANE.defaultBlockState(), ModBlocks.SUGAR_CANE_NEST.get().defaultBlockState())));
        BUMBLE_BEE_NEST_FEATURE = Registry.register(registry, rLoc("bumble_bee_nest_feature"), ModFeatures.BUMBLE_BEE_NEST.get().configured(new ReplaceBlockConfig(Blocks.GRASS_BLOCK.defaultBlockState(), ModBlocks.BUMBLE_BEE_NEST.get().defaultBlockState())));
    }

    private static ResourceLocation rLoc(String name) {
        return new ResourceLocation(ProductiveBees.MODID, name);
    }
}
