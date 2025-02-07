package cy.jdkdigital.productivebees.client.render.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import cy.jdkdigital.productivebees.ProductiveBeesConfig;
import cy.jdkdigital.productivebees.common.tileentity.CentrifugeTileEntity;
import cy.jdkdigital.productivebees.common.tileentity.InventoryHandlerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.items.CapabilityItemHandler;

public class CentrifugeTileEntityRenderer extends TileEntityRenderer<CentrifugeTileEntity>
{
    public CentrifugeTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public void render(CentrifugeTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (ProductiveBeesConfig.CLIENT.renderCombsInCentrifuge.get()) {
            tileEntityIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                ItemStack stack = itemHandler.getStackInSlot(InventoryHandlerHelper.INPUT_SLOT);
                if (!stack.isEmpty()) {
                    long time = System.currentTimeMillis();
                    double d = (time / 50) % 360;
                    int stackCount = stack.getCount();
                    double shownItemCount = stackCount < 20 ? stackCount : 20 + Math.ceil((stackCount - 20) / 4F);
                    for (int i = 0; i < shownItemCount; ++i) {
                        double angle = -d + 360D / shownItemCount * i;
                        double dX = Math.sin(Math.toRadians(angle)) * 0.25D;
                        double dZ = Math.cos(Math.toRadians(angle)) * 0.25D;

                        matrixStackIn.pushPose();
                        matrixStackIn.translate(0.5D + dX, 0.6375D, 0.5D + dZ);
                        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((float) angle + 90F));
                        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
                        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        matrixStackIn.popPose();
                    }
                }
            });
        }
    }
}