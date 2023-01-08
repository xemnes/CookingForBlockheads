package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.ItemUtils;
import net.blay09.mods.cookingforblockheads.item.ModItems;
import net.blay09.mods.cookingforblockheads.network.handler.GuiHandler;
import net.blay09.mods.cookingforblockheads.registry.CookingRegistry;
import net.blay09.mods.cookingforblockheads.tile.TileFridge;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockOven extends BlockKitchen {

    public static PropertyBool POWERED = PropertyBool.create("powered");
    public static PropertyBool ACTIVE = PropertyBool.create("active");

    public static final String name = "oven";
    public static final ResourceLocation registryName = new ResourceLocation(CookingForBlockheads.MOD_ID, name);
    private static final Random random = new Random();

    private static final AxisAlignedBB BOUNDING_BOX_NORTH = new AxisAlignedBB(1, 1, 1, 0, 0, 0.0625);
    private static final AxisAlignedBB BOUNDING_BOX_EAST = new AxisAlignedBB(0.9375, 1, 1, 0, 0, 0);
    private static final AxisAlignedBB BOUNDING_BOX_SOUTH = new AxisAlignedBB(1, 1, 0.9375, 0, 0, 0);
    private static final AxisAlignedBB BOUNDING_BOX_WEST = new AxisAlignedBB(1, 1, 1, 0.0625, 0, 0);


    public BlockOven() {
        super(Material.IRON);

        setUnlocalizedName(registryName.toString());
        setSoundType(SoundType.METAL);
        setHardness(5f);
        setResistance(10f);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, POWERED, ACTIVE);
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

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem.getItem() == Items.BLAZE_ROD && player.capabilities.isCreativeMode) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileOven) {
                IEnergyStorage energyStorage = tileEntity.getCapability(CapabilityEnergy.ENERGY, null);
                Objects.requireNonNull(energyStorage).receiveEnergy(5000, false);
            }
            return true;
        }
        /*if (!heldItem.isEmpty() && DyeUtils.isDye(heldItem)) {
            Optional<EnumDyeColor> dyeColor = DyeUtils.colorFromStack(heldItem);
            if (dyeColor.isPresent() && recolorBlock(world, pos, facing, dyeColor.get())) {
                if (!player.capabilities.isCreativeMode) {
                    heldItem.shrink(1);
                }
            }
            return true;
        }*/

        if (facing == EnumFacing.UP) {
            if (CookingRegistry.isToolItem(heldItem)) {
                EnumFacing stateFacing = state.getValue(FACING);
                float hx = hitX;
                float hz = hitZ;
                switch (stateFacing) {
                    case NORTH:
                        hx = 1f - hitX;
                        hz = 1f - hitZ;
                        break;
//                    case SOUTH: hx = hitX; hz = hitZ; break;
                    case WEST:
                        hz = 1f - hitX;
                        hx = hitZ;
                        break;
                    case EAST:
                        hz = hitX;
                        hx = 1f - hitZ;
                        break;
                }
                int index = -1;
                if (hx < 0.5f && hz < 0.5f) {
                    index = 1;
                } else if (hx >= 0.5f && hz < 0.5f) {
                    index = 0;
                } else if (hx < 0.5f && hz >= 0.5f) {
                    index = 3;
                } else if (hx >= 0.5f && hz >= 0.5f) {
                    index = 2;
                }
                if (index != -1) {
                    TileOven tileOven = (TileOven) world.getTileEntity(pos);
                    if (tileOven != null && tileOven.getToolItem(index).isEmpty()) {
                        ItemStack toolItem = heldItem.splitStack(1);
                        tileOven.setToolItem(index, toolItem);
                    }
                }
                return true;
            }
        }

        if (facing == state.getValue(FACING)) {
            TileOven tileOven = (TileOven) world.getTileEntity(pos);
            if (tileOven != null) {
                if (player.isSneaking()) {
                    tileOven.getDoorAnimator().toggleForcedOpen();
                    return true;
                } else if (!heldItem.isEmpty() && tileOven.getDoorAnimator().isForcedOpen()) {
                    heldItem = ItemHandlerHelper.insertItemStacked(tileOven.getInputHandler(), heldItem, false);
                    if (!heldItem.isEmpty()) {
                        heldItem = ItemHandlerHelper.insertItemStacked(tileOven.getItemHandlerFuel(), heldItem, false);
                    }
                    player.setHeldItem(hand, heldItem);
                    return true;
                }
            }
        }

        if (!world.isRemote) {
            player.openGui(CookingForBlockheads.instance, GuiHandler.COOKING_OVEN, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        boolean hasPowerUpgrade = tileEntity instanceof TileOven && ((TileOven) tileEntity).hasPowerUpgrade();
        state = state.withProperty(POWERED, hasPowerUpgrade);

        boolean isBurning = tileEntity instanceof TileOven && ((TileOven) tileEntity).isBurning();
        state = state.withProperty(ACTIVE, isBurning);

        return state;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileOven();
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        TileOven tileEntity = (TileOven) world.getTileEntity(pos);
        if (tileEntity != null && tileEntity.isBurning()) {
            EnumFacing facing = state.getValue(FACING);
            float x = (float) pos.getX() + 0.5f;
            float y = (float) pos.getY() + 0f + random.nextFloat() * 6f / 16f;
            float z = (float) pos.getZ() + 0.5f;
            float f3 = 0.52f;
            float f4 = random.nextFloat() * 0.6f - 0.3f;

            if (rand.nextDouble() < 0.1D)
            {
                world.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            if (facing == EnumFacing.WEST) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x - f3), (double) y, (double) (z + f4), 0, 0, 0);
            } else if (facing == EnumFacing.EAST) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x + f3), (double) y, (double) (z + f4), 0, 0, 0);
            } else if (facing == EnumFacing.NORTH) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x + f4), (double) y, (double) (z - f3), 0, 0, 0);
            } else if (facing == EnumFacing.SOUTH) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x + f4), (double) y, (double) (z + f3), 0, 0, 0);
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileOven) {
            if (((TileOven) tileEntity).hasPowerUpgrade()) {
                ItemUtils.spawnItemStack(world, pos.getX() + 0.5f, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.heatingUnit));
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);

        for (String s : I18n.format("tooltip." + registryName + ".description").split("\\\\n")) {
            tooltip.add(TextFormatting.GRAY + s);
        }
    }

}
