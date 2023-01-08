package net.blay09.mods.cookingforblockheads.client.render;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class CabinetRendererBirch extends CabinetRenderer {
    public static IBakedModel[] models;
    public static IBakedModel[] modelsFlipped;


    @Override
    protected IBakedModel getDoorModel(EnumFacing facing, boolean isFlipped) {
        return isFlipped ? modelsFlipped[facing.getHorizontalIndex()] : models[facing.getHorizontalIndex()];
    }
}
