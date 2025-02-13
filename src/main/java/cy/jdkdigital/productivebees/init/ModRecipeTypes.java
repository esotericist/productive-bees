package cy.jdkdigital.productivebees.init;

import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.recipe.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = ProductiveBees.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModRecipeTypes
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ProductiveBees.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> ADVANCED_BEEHIVE = createRecipeType("advanced_beehive", () -> new AdvancedBeehiveRecipe.Serializer<>(AdvancedBeehiveRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> CENTRIFUGE = createRecipeType("centrifuge", () -> new CentrifugeRecipe.Serializer<>(CentrifugeRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BOTTLER = createRecipeType("bottler", () -> new BottlerRecipe.Serializer<>(BottlerRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> INCUBATION = createRecipeType("incubation", () -> new IncubationRecipe.Serializer<>(IncubationRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_BREEDING = createRecipeType("bee_breeding", () -> new BeeBreedingRecipe.Serializer<>(BeeBreedingRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_CONVERSION = createRecipeType("bee_conversion", () -> new BeeConversionRecipe.Serializer<>(BeeConversionRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_SPAWNING = createRecipeType("bee_spawning", () -> new BeeSpawningRecipe.Serializer<>(BeeSpawningRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_SPAWNING_BIG = createRecipeType("bee_spawning_big", () -> new BeeSpawningBigRecipe.Serializer<>(BeeSpawningBigRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_NBT_CHANGER = createRecipeType("bee_nbt_changer", () -> new BeeNBTChangerRecipe.Serializer<>(BeeNBTChangerRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> WOOD_CHIP = createRecipeType("wood_chip", () -> new WoodChipRecipe.Serializer<>(WoodChipRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> STONE_CHIP = createRecipeType("stone_chip", () -> new StoneChipRecipe.Serializer<>(StoneChipRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> GENE_TREAT = createRecipeType("gene_treat", () -> new HoneyTreatGeneRecipe.Serializer<>(HoneyTreatGeneRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> GENE_GENE = createRecipeType("gene_gene", () -> new CombineGeneRecipe.Serializer<>(CombineGeneRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BEE_CAGE_BOMB = createRecipeType("bee_cage_bomb", () -> new BeeBombBeeCageRecipe.Serializer<>(BeeBombBeeCageRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> CONFIGURABLE_HONEYCOMB = createRecipeType("configurable_honeycomb", () -> new ConfigurableHoneycombRecipe.Serializer<>(ConfigurableHoneycombRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> CONFIGURABLE_COMB_BLOCK = createRecipeType("configurable_comb_block", () -> new ConfigurableCombBlockRecipe.Serializer<>(ConfigurableCombBlockRecipe::new));
    public static final RegistryObject<IRecipeSerializer<?>> BLOCK_CONVERSION = createRecipeType("block_conversion", () -> new BlockConversionRecipe.Serializer<>(BlockConversionRecipe::new));

    public static <B extends IRecipeSerializer<?>> RegistryObject<B> createRecipeType(String name, Supplier<? extends B> supplier) {
        return RECIPE_SERIALIZERS.register(name, supplier);
    }
}
