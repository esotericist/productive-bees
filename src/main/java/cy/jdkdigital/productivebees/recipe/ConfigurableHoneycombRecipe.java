package cy.jdkdigital.productivebees.recipe;

import com.google.gson.JsonObject;
import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.init.ModItems;
import cy.jdkdigital.productivebees.init.ModRecipeTypes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ConfigurableHoneycombRecipe implements ICraftingRecipe
{
    public final ResourceLocation id;
    public final Integer count;

    public ConfigurableHoneycombRecipe(ResourceLocation id, Integer count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        List<ItemStack> stacks = getItemsInInventory(inv);

        // Honeycombs must match the defined number in the prototype recipe and have the same NBT data
        CompoundNBT type = null;
        if (stacks.size() == count) {
            for (ItemStack itemstack : stacks) {
                if (!itemstack.isEmpty() && itemstack.getItem().equals(ModItems.CONFIGURABLE_HONEYCOMB.get()) && itemstack.hasTag()) {
                    if (type == null) {
                        type = itemstack.getTag();
                    }
                    if (type != null && !type.equals(itemstack.getTag())) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Nonnull
    @Override
    public ItemStack assemble(CraftingInventory inv) {
        List<ItemStack> stacks = getItemsInInventory(inv);

        if (stacks.size() > 0) {
            ItemStack inStack = stacks.get(0);

            ItemStack outStack = new ItemStack(ModItems.CONFIGURABLE_COMB_BLOCK.get());

            outStack.setTag(inStack.getTag());

            return outStack;
        }
        return ItemStack.EMPTY;
    }

    private List<ItemStack> getItemsInInventory(CraftingInventory inv) {
        List<ItemStack> stacks = new ArrayList<>();
        for (int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                stacks.add(itemstack);
            }
        }
        return stacks;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= count;
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return new ItemStack(ModItems.CONFIGURABLE_COMB_BLOCK.get());
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        for (int i = 0; i < count; i++) {
            nonnulllist.add(Ingredient.of(new ItemStack(ModItems.CONFIGURABLE_HONEYCOMB.get())));
        }
        return nonnulllist;
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.CONFIGURABLE_HONEYCOMB.get();
    }

    public static class Serializer<T extends ConfigurableHoneycombRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>
    {
        final ConfigurableHoneycombRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(ConfigurableHoneycombRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T fromJson(ResourceLocation id, JsonObject json) {
            Integer count = JSONUtils.getAsInt(json, "count", 4);

            return this.factory.create(id, count);
        }

        public T fromNetwork(@Nonnull ResourceLocation id, @Nonnull PacketBuffer buffer) {
            try {
                return this.factory.create(id, buffer.readInt());
            } catch (Exception e) {
                ProductiveBees.LOGGER.error("Error reading config honeycomb recipe from packet. " + id, e);
                throw e;
            }
        }

        public void toNetwork(@Nonnull PacketBuffer buffer, T recipe) {
            try {
                buffer.writeInt(recipe.count);
            } catch (Exception e) {
                ProductiveBees.LOGGER.error("Error writing config honeycomb recipe to packet. " + recipe.getId(), e);
                throw e;
            }
        }

        public interface IRecipeFactory<T extends ConfigurableHoneycombRecipe>
        {
            T create(ResourceLocation id, Integer count);
        }
    }
}