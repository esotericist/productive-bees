package cy.jdkdigital.productivebees.client.render.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cy.jdkdigital.productivebees.common.item.WoodChip;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WoodChipRenderer extends ItemStackTileEntityRenderer
{
    private static HashMap<String, TextureAtlasSprite> blockTextureLocations = new HashMap<>();

    protected void add(IVertexBuilder builder, MatrixStack matrixStack, float x, float y, float z, float u, float v) {
        builder.vertex(matrixStack.last().pose(), x, y, z)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .uv(u, v)
                .uv2(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    protected void addBox(IVertexBuilder builder, MatrixStack matrixStack, TextureAtlasSprite sprite, int fromX, int fromY, int toX, int toY) {
        float startX = fromX / 16f;
        float startY = fromY / 16f;
        float endX = toX / 16f;
        float endY = toY / 16f;

        add(builder, matrixStack, startX, startY, 1, sprite.getU(fromX), sprite.getV(fromY));
        add(builder, matrixStack, endX, startY, 1, sprite.getU(toX), sprite.getV(fromY));
        add(builder, matrixStack, endX, endY, 1, sprite.getU(toX), sprite.getV(toY));
        add(builder, matrixStack, startX, endY, 1, sprite.getU(fromX), sprite.getV(toY));

        add(builder, matrixStack, startX, endY, 1, sprite.getU(fromX), sprite.getV(toY));
        add(builder, matrixStack, endX, endY, 1, sprite.getU(toX), sprite.getV(toY));
        add(builder, matrixStack, endX, startY, 1, sprite.getU(toX), sprite.getV(fromY));
        add(builder, matrixStack, startX, startY, 1, sprite.getU(fromX), sprite.getV(fromY));
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLightIn, int packedUV) {
        Item item = itemStack.getItem();

        if (item instanceof WoodChip) {
            IVertexBuilder builder = renderTypeBuffer.getBuffer(RenderType.cutout());

            Block woodBlock = WoodChip.getBlock(itemStack);
            if (woodBlock != null && woodBlock != Blocks.AIR) {
                TextureAtlasSprite sprite = getBlockSprite(woodBlock);

                if (sprite != null) {
                    matrixStack.pushPose();
                    matrixStack.translate(0.5, 0.5, 0.5);
                    if (!itemStack.isFramed()) {
                        matrixStack.scale(.5f, .5f, .5f);
                    }
                    matrixStack.translate(-.5, -.5, -1.0f);

                    // Hello visitor, welcome to render hell. If you have an idea for a better way to render a blocks texture
                    // onto an item by using a model instead of this mess, let me know.
                    addBox(builder, matrixStack, sprite, 5, 1, 9, 12);
                    addBox(builder, matrixStack, sprite, 6, 0, 8, 1);
                    addBox(builder, matrixStack, sprite, 4, 2, 5, 8);
                    addBox(builder, matrixStack, sprite, 3, 3, 4, 5);
                    addBox(builder, matrixStack, sprite, 9, 3, 10, 10);
                    addBox(builder, matrixStack, sprite, 10, 4, 11, 7);
                    addBox(builder, matrixStack, sprite, 5, 12, 8, 13);
                    addBox(builder, matrixStack, sprite, 6, 13, 7, 14);

                    matrixStack.popPose();
                }
            }
        }
    }

    protected static TextureAtlasSprite getBlockSprite(@Nonnull Block block) {
        String woodName = block.getRegistryName().toString();
        if (blockTextureLocations.get(woodName) != null) {
            return blockTextureLocations.get(woodName);
        }

        BlockRendererDispatcher manager = Minecraft.getInstance().getBlockRenderer();
        IBakedModel model = manager.getBlockModel(block.defaultBlockState());

        List<BakedQuad> quads = model.getQuads(block.defaultBlockState(), Direction.NORTH, new Random());
        if (quads.isEmpty()) {
            return null;
        }

        TextureAtlasSprite sprite = quads.iterator().next().getSprite();

        blockTextureLocations.put(woodName, sprite);

        return blockTextureLocations.get(woodName);
    }
}
