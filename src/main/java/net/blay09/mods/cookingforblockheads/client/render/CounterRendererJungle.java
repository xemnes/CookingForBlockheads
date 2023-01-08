package net.blay09.mods.cookingforblockheads.client.render;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class CounterRendererJungle extends CounterRenderer {

    public static IBakedModel[] models;
    public static IBakedModel[] modelsFlipped;

    protected IBakedModel getDoorModel(EnumFacing facing, boolean isFlipped) {
        return isFlipped ? modelsFlipped[facing.getHorizontalIndex()] : models[facing.getHorizontalIndex()];
    }


}
