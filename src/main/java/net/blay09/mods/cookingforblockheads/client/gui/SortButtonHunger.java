package net.blay09.mods.cookingforblockheads.client.gui;

import java.util.Comparator;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.api.FoodRecipeWithStatus;
import net.blay09.mods.cookingforblockheads.api.ISortButton;
import net.blay09.mods.cookingforblockheads.container.comparator.ComparatorHunger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SortButtonHunger implements ISortButton {

    private static final ResourceLocation icon = new ResourceLocation(CookingForBlockheads.MOD_ID, "textures/gui/gui.png");
	
    @Override
    public ResourceLocation getIcon() {
        return icon;
    }
    
    @Override
    public String getTooltip() {
        return "tooltip." + CookingForBlockheads.MOD_ID + ":sort_by_hunger";
    }
    
    @Override
    public Comparator<FoodRecipeWithStatus> getComparator(EntityPlayer player) {
        return new ComparatorHunger(player);
    }
    
    @Override
    public int getIconTextureX() {
        return 216;
    }
    
    @Override
    public int getIconTextureY() {
        return 0;
    }

}
