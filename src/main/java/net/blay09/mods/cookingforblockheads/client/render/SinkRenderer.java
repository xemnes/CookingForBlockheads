package net.blay09.mods.cookingforblockheads.client.render;

import net.blay09.mods.cookingforblockheads.ModConfig;
import net.blay09.mods.cookingforblockheads.block.BlockSink;
import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.blay09.mods.cookingforblockheads.tile.TileSink;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class SinkRenderer extends TileEntitySpecialRenderer<TileSink> {

    public static IBakedModel modelSinkLiquid;

    @Override
    public void render(TileSink tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tileEntity.hasWorld()) {
            return;
        }

        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();


        if (tileEntity.getWaterAmount() > 0) {
            GlStateManager.enableBlend();
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y + 0.5f, z);
            float filledPercentage = tileEntity.getWaterAmount() / (float) tileEntity.getWaterCapacity();
            GlStateManager.scale(1f, filledPercentage, 1f);
            GlStateManager.translate(0f, -0.5f, 0f);
            bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getMinecraft().getRenderItem().renderModel(modelSinkLiquid, 0xFFFFFFFF);
            GlStateManager.popMatrix();
        }

        RenderHelper.enableStandardItemLighting();
    }

}
