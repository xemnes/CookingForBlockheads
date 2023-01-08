package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.tile.TileCabinet;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCabinet extends BlockCounter {
    public static final String name = "cabinet";
    public static final ResourceLocation registryName = new ResourceLocation(CookingForBlockheads.MOD_ID, name);

    private static final AxisAlignedBB BOUNDING_BOX_NORTH = new AxisAlignedBB(1, 0.9375, 1, 0, 0.0625, 0.5);
    private static final AxisAlignedBB BOUNDING_BOX_EAST = new AxisAlignedBB(0.5, 0.9375, 1, 0, 0.0625, 0);
    private static final AxisAlignedBB BOUNDING_BOX_SOUTH = new AxisAlignedBB(1, 0.9375, 0.5, 0, 0.0625, 0);
    private static final AxisAlignedBB BOUNDING_BOX_WEST = new AxisAlignedBB(1, 0.9375, 1, 0.5, 0.0625, 0);

    public BlockCabinet(Material material) {
        super(material);

        setUnlocalizedName(registryName.toString());
        setSoundType(SoundType.STONE);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCabinet();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.YELLOW + I18n.format("tooltip.cookingforblockheads:multiblock_kitchen"));
        for (String s : I18n.format("tooltip." + registryName + ".description").split("\\\\n")) {
            tooltip.add(TextFormatting.GRAY + s);
        }
        tooltip.add(TextFormatting.AQUA + I18n.format("tooltip.cookingforblockheads:dyeable"));
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch(state.getValue(FACING)) {
            case EAST:
                return BOUNDING_BOX_EAST;
            case WEST:
                return BOUNDING_BOX_WEST;
            case SOUTH:
                return BOUNDING_BOX_SOUTH;
            case NORTH:
            default:
                return BOUNDING_BOX_NORTH;
        }
    }
}
