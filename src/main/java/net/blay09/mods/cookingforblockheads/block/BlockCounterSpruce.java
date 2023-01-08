package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.compat.Compat;
import net.blay09.mods.cookingforblockheads.network.handler.GuiHandler;
import net.blay09.mods.cookingforblockheads.tile.IDyeableKitchen;
import net.blay09.mods.cookingforblockheads.tile.TileCounter;
import net.blay09.mods.cookingforblockheads.tile.TileCounterSpruce;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.DyeUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BlockCounterSpruce extends BlockCounter {

    public static final String name = "counter_spruce";
    public static final ResourceLocation registryName = new ResourceLocation(CookingForBlockheads.MOD_ID, name);

    public static final PropertyEnum<ModelPass> PASS = PropertyEnum.create("pass", ModelPass.class);

    public BlockCounterSpruce(Material material) {
        super(material);

        setUnlocalizedName(registryName.toString());
        setSoundType(SoundType.WOOD);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, FLIPPED, PASS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCounterSpruce();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);

        if (facing == state.getValue(FACING)) {
            TileCounter tileCounter = (TileCounter) world.getTileEntity(pos);
            if (tileCounter != null) {
                if (player.isSneaking()) {
                    tileCounter.getDoorAnimator().toggleForcedOpen();
                    return true;
                } else if (!heldItem.isEmpty() && tileCounter.getDoorAnimator().isForcedOpen()) {
                    heldItem = ItemHandlerHelper.insertItemStacked(tileCounter.getItemHandler(), heldItem, false);
                    player.setHeldItem(hand, heldItem);
                    return true;
                }
            }
        }

        if (!world.isRemote) {
            if (facing == EnumFacing.UP && !heldItem.isEmpty() && (heldItem.getItem() instanceof ItemBlock || heldItem.getItem() == Compat.cuttingBoardItem)) {
                return false;
            }

            player.openGui(CookingForBlockheads.instance, GuiHandler.COUNTER, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.YELLOW + I18n.format("tooltip.cookingforblockheads:multiblock_kitchen"));
        for (String s : I18n.format("tooltip." + BlockCounter.registryName + ".description").split("\\\\n")) {
            tooltip.add(TextFormatting.GRAY + s);
        }
    }

}
