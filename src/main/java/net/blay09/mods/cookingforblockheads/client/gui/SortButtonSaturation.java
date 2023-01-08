package net.blay09.mods.cookingforblockheads.client.gui;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.api.FoodRecipeWithStatus;
import net.blay09.mods.cookingforblockheads.api.ISortButton;
import net.blay09.mods.cookingforblockheads.container.comparator.ComparatorSaturation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.Comparator;

public class SortButtonSaturation implements ISortButton {

    private static final ResourceLocation icon = new ResourceLocation(CookingForBlockheads.MOD_ID, "textures/gui/gui.png");

    @Override
    public ResourceLocation getIcon() {
        return icon;
    }

    @Override
    public String getTooltip() {
        return "tooltip.cookingforblockheads:sort_by_saturation";
    }

    @Override
    public Comparator<FoodRecipeWithStatus> getComparator(EntityPlayer player) {
        return new ComparatorSaturation(player);
    }

    @Override
    public int getIconTextureX() {
        return 236;
    }

    @Override
    public int getIconTextureY() {
        return 196;
    }

}
