package net.blay09.mods.cookingforblockheads.compat;

import com.google.common.base.Function;
import mcjty.theoneprobe.api.*;
import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.block.BlockFridge;
import net.blay09.mods.cookingforblockheads.block.BlockMilkJar;
import net.blay09.mods.cookingforblockheads.block.BlockOven;
import net.blay09.mods.cookingforblockheads.block.BlockToaster;
import net.blay09.mods.cookingforblockheads.tile.TileFridge;
import net.blay09.mods.cookingforblockheads.tile.TileMilkJar;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.blay09.mods.cookingforblockheads.tile.TileToaster;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TheOneProbeAddon implements Function<ITheOneProbe, Void> {

    @Nullable
    @Override
    public Void apply(@Nullable ITheOneProbe top) {
        if (top != null) {
            top.registerProvider(new ProbeInfoProvider());
        }
        return null;
    }

    public static class ProbeInfoProvider implements IProbeInfoProvider {

        @Override
        public String getID() {
            return CookingForBlockheads.MOD_ID;
        }

        @Override
        public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world, IBlockState state, IProbeHitData data) {
            // NOTE no lang support in The One Probe atm...
            if (state.getBlock() instanceof BlockToaster) {
                TileToaster tileEntity = tryGetTileEntity(world, data.getPos(), TileToaster.class);
                if (tileEntity != null) {
                    addToasterInfo(tileEntity, mode, info);
                }
            } else if (state.getBlock() instanceof BlockOven) {
                TileOven tileEntity = tryGetTileEntity(world, data.getPos(), TileOven.class);
                if (tileEntity != null && tileEntity.hasPowerUpgrade()) {
                    info.text(TextFormatting.YELLOW + "Upgrade:" + TextFormatting.GOLD + " Heating Unit");
                }
            } else if (state.getBlock() instanceof BlockFridge) {
                TileFridge tileEntity = tryGetTileEntity(world, data.getPos(), TileFridge.class);
                if (tileEntity != null && tileEntity.getBaseFridge().hasIceUpgrade()) {
                    info.text(TextFormatting.YELLOW + "Upgrade:" + TextFormatting.AQUA + "Ice Unit");
                }

                if (tileEntity != null && tileEntity.getBaseFridge().hasPreservationUpgrade()) {
                    info.text(TextFormatting.YELLOW + "Upgrade:" + TextFormatting.LIGHT_PURPLE + "Preservation Chamber");
                }
            }
        }

        private void addToasterInfo(TileToaster tileEntity, ProbeMode mode, IProbeInfo info) {
            if (tileEntity.isActive()) {
                info.progress((int) (tileEntity.getToastProgress() * 100), 100,
                        info.defaultProgressStyle()
                                .prefix("Toasting: ")
                                .suffix("%")
                                .filledColor((tileEntity.getToastProgress() * 100) <= 30 ? 0xffc4ac33 : (tileEntity.getToastProgress() * 100) > 30 && (tileEntity.getToastProgress() * 100) <= 70 ? 0xffbd871c : 0xff8f5d1d)
                );
            }
        }

    }

    @Nullable
    @SuppressWarnings("unchecked")
    private static <T extends TileEntity> T tryGetTileEntity(World world, BlockPos pos, Class<T> tileClass) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null && tileClass.isAssignableFrom(tileEntity.getClass())) {
            return (T) tileEntity;
        }
        return null;
    }
}
