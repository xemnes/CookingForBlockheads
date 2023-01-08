package net.blay09.mods.cookingforblockheads.client.render;

import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;

public class OvenRenderer extends TileEntitySpecialRenderer<TileOven> {

    public static IBakedModel modelDoor;
    public static IBakedModel modelDoorActive;

    public static IBakedModel modelCookingPot;
    public static IBakedModel modelCookingSkillet;
    public static IBakedModel modelCookingSaucepan;
    public static IBakedModel modelCookingBakeware;

    @Override
    public void render(TileOven tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tileEntity.hasWorld()) {
            return;
        }

        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

        EnumFacing facing = tileEntity.getFacing();
        float blockAngle = RenderUtils.getFacingAngle(facing);
        float doorAngle = tileEntity.getDoorAnimator().getRenderAngle(partialTicks);

        // Render the oven door
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5f, y, z + 0.5f);
        GlStateManager.rotate(blockAngle, 0f, 1f, 0f);
        GlStateManager.translate(-0.5f, 0f, -0.5f);
        GlStateManager.rotate(-(float) Math.toDegrees(doorAngle), 1f, 0f, 0f);
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        IBakedModel model = doorAngle < 0.3f && tileEntity.isBurning() ? modelDoorActive : modelDoor;
        dispatcher.getBlockModelRenderer().renderModelBrightnessColor(model, 1f, 1f, 1f, 1f);
        GlStateManager.popMatrix();

        // Render the oven tools
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + 1, z);
        GlStateManager.rotate(blockAngle, 0f, 1f, 0f);
        switch(tileEntity.getFacing()) {
            case EAST:
                GlStateManager.translate(1f, 0f, -1f);
            case WEST:
                GlStateManager.translate(0f, 0f, 1f);
            case SOUTH:
                GlStateManager.translate(-1f, 0f, -1f);
            case NORTH:
            default:
                GlStateManager.translate(0f, 0f, 0f);
        }
        IBakedModel toolModel;
        ItemStack itemStack = tileEntity.getToolItem(0);
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (!itemStack.isEmpty()) {
            toolModel = modelCookingBakeware;
            dispatcher.getBlockModelRenderer().renderModelBrightnessColor(toolModel, 1f, 1f, 1f, 1f);
        }
        itemStack = tileEntity.getToolItem(1);
        if (!itemStack.isEmpty()) {
            toolModel = modelCookingPot;
            dispatcher.getBlockModelRenderer().renderModelBrightnessColor(toolModel, 1f, 1f, 1f, 1f);
        }
        itemStack = tileEntity.getToolItem(2);
        if (!itemStack.isEmpty()) {
            toolModel = modelCookingSaucepan;
            dispatcher.getBlockModelRenderer().renderModelBrightnessColor(toolModel, 1f, 1f, 1f, 1f);
        }
        itemStack = tileEntity.getToolItem(3);
        if (!itemStack.isEmpty()) {
            toolModel = modelCookingSkillet;
            dispatcher.getBlockModelRenderer().renderModelBrightnessColor(toolModel, 1f, 1f, 1f, 1f);
        }
        GlStateManager.popMatrix();


//        GlStateManager.pushMatrix();
//        GlStateManager.color(1f, 1f, 1f, 1f);
//        GlStateManager.translate(x + 0.5, y + 1.05, z + 0.5);
//        GlStateManager.rotate(blockAngle, 0f, 1f, 0f);
//        GlStateManager.scale(0.4f, 0.4f, 0.4f);
//        ItemStack itemStack = tileEntity.getToolItem(0);
//        if (!itemStack.isEmpty()) {
//            RenderUtils.renderItem(itemRenderer, itemStack, -0.55f, 0f, 0.5f, 45f, 1f, 0f, 0f);
//        }
//        itemStack = tileEntity.getToolItem(1);
//        if (!itemStack.isEmpty()) {
//            RenderUtils.renderItem(itemRenderer, itemStack, 0.55f, 0f, 0.5f, 45f, 1f, 0f, 0f);
//        }
//        itemStack = tileEntity.getToolItem(2);
//        if (!itemStack.isEmpty()) {
//            RenderUtils.renderItem(itemRenderer, itemStack, -0.55f, 0f, -0.5f, 45f, 1f, 0f, 0f);
//        }
//        itemStack = tileEntity.getToolItem(3);
//        if (!itemStack.isEmpty()) {
//            RenderUtils.renderItem(itemRenderer, itemStack, 0.55f, 0f, -0.5f, 45f, 1f, 0f, 0f);
//        }
//        GlStateManager.popMatrix();

        // Render the oven content when the door is open
        if (doorAngle > 0f) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 0.4, z + 0.5);
            GlStateManager.rotate(blockAngle, 0f, 1f, 0f);
            GlStateManager.scale(0.3f, 0.3f, 0.3f);
            float offsetX = 0.825f;
            float offsetZ = 0.8f;
            for (int i = 0; i < 9; i++) {
                itemStack = tileEntity.getItemHandler().getStackInSlot(7 + i);
                if (!itemStack.isEmpty()) {
                    RenderUtils.renderItem(itemRenderer, itemStack, offsetX, 0f, offsetZ, 90f, 1f, 0f, 0f);
                }
                offsetX -= 0.8f;
                if (offsetX < -0.8f) {
                    offsetX = 0.825f;
                    offsetZ -= 0.8f;
                }
            }
            GlStateManager.popMatrix();
        }
    }

}
