package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.ItemUtils;
import net.blay09.mods.cookingforblockheads.item.ModItems;
import net.blay09.mods.cookingforblockheads.network.handler.GuiHandler;
import net.blay09.mods.cookingforblockheads.tile.TileCookingTable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.DyeUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class BlockCookingTable extends BlockKitchen {

    public static final String name = "cooking_table";
    public static final ResourceLocation registryName = new ResourceLocation(CookingForBlockheads.MOD_ID, name);

    public BlockCookingTable(Material material) {
        super(material);

        setUnlocalizedName(registryName.toString());
        setSoundType(SoundType.STONE);
        setHardness(2.5f);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (!heldItem.isEmpty()) {
            TileCookingTable tileEntity = (TileCookingTable) world.getTileEntity(pos);
            if (tileEntity != null) {
                if (DyeUtils.isDye(heldItem)) {
                    Optional<EnumDyeColor> dyeColor = DyeUtils.colorFromStack(heldItem);
                    if (dyeColor.isPresent() && recolorBlock(world, pos, facing, dyeColor.get())) {
                        if (!player.capabilities.isCreativeMode) {
                            heldItem.shrink(1);
                        }
                    }
                    return true;
                }
                if (!tileEntity.hasNoFilterBook() && heldItem.getItem() == ModItems.recipeBook && heldItem.getItemDamage() == 0) {
                    tileEntity.setNoFilterBook(heldItem.splitStack(1));
                    return true;
                }
            }
        } else if (player.isSneaking()) {
            TileCookingTable tileEntity = (TileCookingTable) world.getTileEntity(pos);
            if (tileEntity != null) {
                ItemStack noFilterBook = tileEntity.getNoFilterBook();
                if (!noFilterBook.isEmpty()) {
                    if (!player.inventory.addItemStackToInventory(noFilterBook)) {
                        player.dropItem(noFilterBook, false);
                    }
                    tileEntity.setNoFilterBook(ItemStack.EMPTY);
                    return true;
                }
            }
        }

        if (!world.isRemote) {
            player.openGui(CookingForBlockheads.instance, GuiHandler.COOKING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileCookingTable tileEntity = (TileCookingTable) world.getTileEntity(pos);
        if (tileEntity != null) {
            ItemUtils.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, tileEntity.getNoFilterBook());
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCookingTable();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);

        for (String s : I18n.format("tooltip." + registryName + ".description").split("\\\\n")) {
            tooltip.add(TextFormatting.GRAY + s);
        }
        for (String s : I18n.format("tooltip." + BlockCookingTable.registryName + ".filter").split("\\\\n")) {
            tooltip.add(TextFormatting.DARK_PURPLE + s);
        }
        tooltip.add(TextFormatting.AQUA + I18n.format("tooltip.cookingforblockheads:dyeable"));
    }

}
