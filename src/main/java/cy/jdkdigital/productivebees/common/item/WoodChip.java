package cy.jdkdigital.productivebees.common.item;

import cy.jdkdigital.productivebees.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WoodChip extends Item
{
    protected static final String KEY = "productivebees_woodtype";

    public WoodChip(Properties properties) {
        super(properties);
    }

    public static ItemStack getStack(Block block) {
        return getStack(block, 1);
    }

    public static ItemStack getStack(Block block, int count) {
        return getStack(block.getRegistryName().toString(), count);
    }

    public static ItemStack getStack(String blockName, int count) {
        ItemStack result = new ItemStack(ModItems.WOOD_CHIP.get(), count);
        setBlock(result, blockName);
        return result;
    }

    public static void setBlock(ItemStack stack, String blockName) {
        stack.getOrCreateTag().putString(KEY, blockName);
    }

    @Nullable
    public static Block getBlock(ItemStack stack) {
        String blockType = getBlockType(stack);
        if (blockType != null && !blockType.isEmpty()) {
            return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockType));
        }
        return null;
    }

    public static String getBlockType(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        return tag != null ? tag.getString(KEY) : null;
    }

    @Override
    @Nonnull
    public ITextComponent getName(@Nonnull ItemStack stack) {
        Block block = getBlock(stack);
        if (block != null) {
            return new TranslationTextComponent(this.getDescriptionId() + ".named", new TranslationTextComponent(block.getDescriptionId()));
        }
        return super.getName(stack);
    }

    @Override
    public void fillItemCategory(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            try {
                BlockTags.LOGS.getValues().forEach(block -> {
                    if (block.getRegistryName() != null && block.getRegistryName().getPath().contains("log") && !block.getRegistryName().getPath().contains("stripped")) {
                        items.add(getStack(block));
                    }
                });
            } catch (IllegalStateException ise) {
                // tag not initialized yet
            }
        }
    }
}
