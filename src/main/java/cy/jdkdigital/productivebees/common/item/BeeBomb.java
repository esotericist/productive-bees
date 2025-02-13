package cy.jdkdigital.productivebees.common.item;

import cy.jdkdigital.productivebees.common.entity.BeeBombEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BeeBomb extends Item
{
    private static final String BEES_KEY = "productivebees_beebomb_bees";

    public BeeBomb(Properties properties) {
        super(properties);
    }

    public static boolean isLoaded(ItemStack itemStack) {
        return getBees(itemStack).size() > 0;
    }

    public static void addBee(ItemStack stack, ItemStack cage) {
        ListNBT bees = getBees(stack);

        bees.add(cage.getTag());

        stack.getOrCreateTag().put(BEES_KEY, bees);
    }

    public static ListNBT getBees(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        INBT bees = new ListNBT();
        if (tag != null) {
            if (tag.get(BEES_KEY) instanceof ListNBT) {
                bees = tag.get(BEES_KEY);
            }
        }
        return (ListNBT) bees;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack item = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            BeeBombEntity bombEntity = new BeeBombEntity(world, player);
            bombEntity.setItem(item);
            bombEntity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(bombEntity);
        }

        player.inventory.removeItem(item);

        return ActionResult.success(item);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);

        ListNBT beeList = BeeBomb.getBees(stack);
        if (!beeList.isEmpty()) {
            if (Screen.hasShiftDown()) {
                list.add(new TranslationTextComponent("productivebees.hive.tooltip.bees").withStyle(TextFormatting.DARK_AQUA));
                for (INBT bee : beeList) {
                    String beeType = ((CompoundNBT) bee).getString("entity");
                    list.add(new StringTextComponent(beeType).withStyle(TextFormatting.GOLD));
                }
            } else {
                list.add(new TranslationTextComponent("productivebees.information.hold_shift").withStyle(TextFormatting.WHITE));
            }
        }
    }
}
