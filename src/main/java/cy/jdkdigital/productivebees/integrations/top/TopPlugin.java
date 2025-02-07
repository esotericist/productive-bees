package cy.jdkdigital.productivebees.integrations.top;

import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.ProductiveBeesConfig;
import cy.jdkdigital.productivebees.common.tileentity.CentrifugeTileEntity;
import cy.jdkdigital.productivebees.common.tileentity.SolitaryNestTileEntity;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TopPlugin implements Function<ITheOneProbe, Void>
{
    ITextComponent formattedName = new StringTextComponent("Productive Bees").withStyle(TextFormatting.BLUE).withStyle(TextFormatting.ITALIC);

    @Nullable
    @Override
    public Void apply(ITheOneProbe theOneProbe) {
        theOneProbe.registerBlockDisplayOverride((mode, probeInfo, player, world, blockState, data) -> {
            TileEntity tileEntity = world.getBlockEntity(data.getPos());
            if (tileEntity instanceof SolitaryNestTileEntity) {
                SolitaryNestTileEntity nest = (SolitaryNestTileEntity) tileEntity;
                if (mode.equals(ProbeMode.EXTENDED)) {
                    probeInfo.horizontal()
                            .item(new ItemStack(blockState.getBlock().asItem()))
                            .vertical()
                            .itemLabel(new ItemStack(blockState.getBlock().asItem()))
                            .text(formattedName);
                    if (nest.getOccupantCount() > 0) {
                        probeInfo.text(new TranslationTextComponent("productivebees.top.solitary.bee", nest.getBeeList().get(0).localizedName));
                        probeInfo.progress(nest.getBeeList().get(0).minOccupationTicks - nest.getBeeList().get(0).ticksInHive, nest.getBeeList().get(0).minOccupationTicks);
                    }
                    else {
                        if (nest.getNestTickCooldown() > 0) {
                            probeInfo.text(new TranslationTextComponent("productivebees.top.solitary.repopulation_countdown"));
                            probeInfo.progress(nest.getNestTickCooldown() / 20, ProductiveBeesConfig.GENERAL.nestSpawnCooldown.get() / 20);
                        }
                        else {
                            probeInfo.text(new TranslationTextComponent("productivebees.top.solitary.repopulation_countdown_inactive"));
                            if (nest.canRepopulate()) {
                                probeInfo.text(new TranslationTextComponent("productivebees.top.solitary.can_repopulate_true"));
                            }
                            else {
                                probeInfo.text(new TranslationTextComponent("productivebees.top.solitary.can_repopulate_false"));
                            }
                        }
                    }
                    return true;
                }
            }

            // Centrifuge
            if (tileEntity instanceof CentrifugeTileEntity) {
                CentrifugeTileEntity centrifugeTileEntity = (CentrifugeTileEntity) tileEntity;

                if (centrifugeTileEntity.recipeProgress > 0) {
                    probeInfo.horizontal()
                            .item(new ItemStack(blockState.getBlock().asItem()))
                            .vertical()
                            .itemLabel(new ItemStack(blockState.getBlock().asItem()))
                            .progress((int) Math.floor(centrifugeTileEntity.recipeProgress), centrifugeTileEntity.getProcessingTime())
                            .text(formattedName);
                    return true;
                }
            }

            ResourceLocation registryName = blockState.getBlock().getRegistryName();
            if (registryName != null && registryName.getNamespace().equals(ProductiveBees.MODID)) {
                probeInfo.horizontal()
                        .item(new ItemStack(blockState.getBlock().asItem()))
                        .vertical()
                        .itemLabel(new ItemStack(blockState.getBlock().asItem()))
                        .text(formattedName);
                return true;
            }
            return false;
        });

        return null;
    }


}