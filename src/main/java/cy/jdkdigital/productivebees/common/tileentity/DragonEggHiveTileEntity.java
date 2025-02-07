package cy.jdkdigital.productivebees.common.tileentity;

import cy.jdkdigital.productivebees.common.block.AdvancedBeehive;
import cy.jdkdigital.productivebees.init.ModTileEntityTypes;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class DragonEggHiveTileEntity extends AdvancedBeehiveTileEntity
{
    public DragonEggHiveTileEntity() {
        super(ModTileEntityTypes.DRACONIC_BEEHIVE.get());
        MAX_BEES = 3;
    }

    @Override
    public void tick() {
        final World world = level;
        if (world == null || level.isClientSide()) {
            return;
        }

        if (tickCounter % 23 == 0) {
            BlockState blockState = this.getBlockState();

            if (blockState.getBlock() instanceof AdvancedBeehive) {
                int honeyLevel = blockState.getValue(BeehiveBlock.HONEY_LEVEL);

                // Auto harvest if empty bottles are in
                if (honeyLevel >= 5) {
                    this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> {
                        ItemStack bottles = inv.getStackInSlot(InventoryHandlerHelper.BOTTLE_SLOT);
                        if (!bottles.isEmpty()) {
                            final ItemStack filledBottle = level.dimension() == World.END ? new ItemStack(Items.DRAGON_BREATH) : new ItemStack(Items.HONEY_BOTTLE);
                            boolean addedBottle = ((InventoryHandlerHelper.ItemHandler) inv).addOutput(filledBottle);
                            if (addedBottle) {
                                bottles.shrink(1);
                                level.setBlockAndUpdate(worldPosition, blockState.setValue(BeehiveBlock.HONEY_LEVEL, honeyLevel - 5));
                            }
                        }
                    });
                }
            }
        }

        hasTicked = true;

        super.tick();
    }
}
