package net.blay09.mods.cookingforblockheads.network.handler;

import net.blay09.mods.cookingforblockheads.KitchenMultiBlock;
import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.blay09.mods.cookingforblockheads.client.gui.*;
import net.blay09.mods.cookingforblockheads.container.*;
import net.blay09.mods.cookingforblockheads.tile.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int ITEM_RECIPE_BOOK = 1;
    public static final int COOKING_TABLE = 2;
    public static final int COOKING_OVEN = 3;
    public static final int FRIDGE = 4;
    public static final int SPICE_RACK = 5;
    public static final int COUNTER = 6;
    public static final int FRUIT_BASKET = 7;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == ITEM_RECIPE_BOOK) { // x: EnumHand
            if (x < 0 || x >= EnumHand.values().length) {
                return null;
            }

            ItemStack heldItem = player.getHeldItem(EnumHand.values()[x]);
            if (!heldItem.isEmpty()) {
                switch (heldItem.getItemDamage()) {
                    case 0:
                        return new ContainerRecipeBook(player).setNoFilter();
                    case 1:
                        return new ContainerRecipeBook(player);
                    case 2:
                        return new ContainerRecipeBook(player).allowCrafting();
                }
            }
        } else {
            BlockPos pos = new BlockPos(x, y, z);
            TileEntity tileEntity = world.getTileEntity(pos);
            switch (id) {
                case COOKING_TABLE:
                    if (world.getBlockState(pos).getBlock() == ModBlocks.cookingTable
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableOak
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableSpruce
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableBirch
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableJungle
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableAcacia
                            || world.getBlockState(pos).getBlock() == ModBlocks.cookingTableDarkOak
                    ) {
                        if (tileEntity instanceof TileCookingTable) {
                            if (((TileCookingTable) tileEntity).hasNoFilterBook()) {
                                return new ContainerRecipeBook(player).setNoFilter().allowCrafting().setKitchenMultiBlock(new KitchenMultiBlock(world, pos));
                            } else {
                                return new ContainerRecipeBook(player).allowCrafting().setKitchenMultiBlock(new KitchenMultiBlock(world, pos));
                            }
                        }
                    }
                    break;
                case COOKING_OVEN:
                    if (tileEntity instanceof TileOven) {
                        return new ContainerOven(player, (TileOven) tileEntity);
                    }
                    break;
                case FRIDGE:
                    if (tileEntity instanceof TileFridge) {
                        return new ContainerFridge(player, (TileFridge) tileEntity);
                    }
                    break;
                case SPICE_RACK:
                    if (tileEntity instanceof TileSpiceRack) {
                        return new ContainerSpiceRack(player, (TileSpiceRack) tileEntity);
                    }
                    break;
                case COUNTER:
                    if (tileEntity instanceof TileCounter) {
                        return new ContainerCounter(player, (TileCounter) tileEntity);
                    }
                    break;
                case FRUIT_BASKET:
                    if (tileEntity instanceof TileFruitBasket) {
                        return new ContainerFruitBasket(player, (TileFruitBasket) tileEntity);
                    }
                    break;
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == ITEM_RECIPE_BOOK) { // x: EnumHand
            ItemStack heldItem = player.getHeldItem(EnumHand.values()[x]);
            if (!heldItem.isEmpty()) {
                switch (heldItem.getItemDamage()) {
                    case 0:
                        return new GuiRecipeBook(new ContainerRecipeBook(player).setNoFilter());
                    case 1:
                        return new GuiRecipeBook(new ContainerRecipeBook(player));
                    case 2:
                        return new GuiRecipeBook(new ContainerRecipeBook(player).allowCrafting());
                }
            }
        } else {
            BlockPos pos = new BlockPos(x, y, z);
            TileEntity tileEntity = world.getTileEntity(pos);
            switch (id) {
                case COOKING_TABLE:
                    return new GuiRecipeBook(new ContainerRecipeBook(player).allowCrafting());
                case COOKING_OVEN:
                    if (tileEntity instanceof TileOven) {
                        return new GuiOven(player, (TileOven) tileEntity);
                    }
                    break;
                case FRIDGE:
                    if (tileEntity instanceof TileFridge) {
                        return new GuiFridge(player, (TileFridge) tileEntity);
                    }
                    break;
                case SPICE_RACK:
                    if (tileEntity instanceof TileSpiceRack) {
                        return new GuiSpiceRack(player, (TileSpiceRack) tileEntity);
                    }
                    break;
                case COUNTER:
                    if (tileEntity instanceof TileCounter) {
                        return new GuiCounter(player, (TileCounter) tileEntity);
                    }
                    break;
                case FRUIT_BASKET:
                    if (tileEntity instanceof TileFruitBasket) {
                        return new GuiFruitBasket(player, (TileFruitBasket) tileEntity);
                    }
                    break;
            }
        }

        return null;
    }

}
