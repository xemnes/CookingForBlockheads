package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(CookingForBlockheads.MOD_ID)
public class ModBlocks {

    @GameRegistry.ObjectHolder(BlockCookingTable.name)
    public static final Block cookingTable = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableOak.name)
    public static final Block cookingTableOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableSpruce.name)
    public static final Block cookingTableSpruce = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableBirch.name)
    public static final Block cookingTableBirch = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableJungle.name)
    public static final Block cookingTableJungle = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableAcacia.name)
    public static final Block cookingTableAcacia = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCookingTableDarkOak.name)
    public static final Block cookingTableDarkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockOven.name)
    public static final Block oven = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockFridge.name)
    public static final Block fridge = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSink.name)
    public static final Block sink = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkOak.name)
    public static final Block sinkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkSpruce.name)
    public static final Block sinkSpruce = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkBirch.name)
    public static final Block sinkBirch = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkJungle.name)
    public static final Block sinkJungle = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkAcacia.name)
    public static final Block sinkAcacia = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSinkDarkOak.name)
    public static final Block sinkDarkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounter.name)
    public static final Block counter = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounterOak.name)
    public static final Block counterOak = Blocks.AIR;
    @GameRegistry.ObjectHolder(BlockCounterSpruce.name)
    public static final Block counterSpruce = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounterBirch.name)
    public static final Block counterBirch = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounterJungle.name)
    public static final Block counterJungle = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounterAcacia.name)
    public static final Block counterAcacia = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCounterDarkOak.name)
    public static final Block counterDarkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinet.name)
    public static final Block cabinet = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetOak.name)
    public static final Block cabinetOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetSpruce.name)
    public static final Block cabinetSpruce = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetBirch.name)
    public static final Block cabinetBirch = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetJungle.name)
    public static final Block cabinetJungle = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetAcacia.name)
    public static final Block cabinetAcacia = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCabinetDarkOak.name)
    public static final Block cabinetDarkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCorner.name)
    public static final Block corner = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerOak.name)
    public static final Block cornerOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerSpruce.name)
    public static final Block cornerSpruce = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerBirch.name)
    public static final Block cornerBirch = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerJungle.name)
    public static final Block cornerJungle = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerAcacia.name)
    public static final Block cornerAcacia = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCornerDarkOak.name)
    public static final Block cornerDarkOak = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockToolRack.name)
    public static final Block toolRack = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockToaster.name)
    public static final Block toaster = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockMilkJar.name)
    public static final Block milkJar = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCowJar.name)
    public static final Block cowJar = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockSpiceRack.name)
    public static final Block spiceRack = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockFruitBasket.name)
    public static final Block fruitBasket = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockCuttingBoard.name)
    public static final Block cuttingBoard = Blocks.AIR;

    @GameRegistry.ObjectHolder(BlockKitchenFloor.name)
    public static final Block kitchenFloor = Blocks.AIR;

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                new BlockCookingTable(Material.ROCK).setRegistryName(BlockCookingTable.name),
                new BlockCookingTableOak(Material.WOOD).setRegistryName(BlockCookingTableOak.name),
                new BlockCookingTableSpruce(Material.WOOD).setRegistryName(BlockCookingTableSpruce.name),
                new BlockCookingTableBirch(Material.WOOD).setRegistryName(BlockCookingTableBirch.name),
                new BlockCookingTableJungle(Material.WOOD).setRegistryName(BlockCookingTableJungle.name),
                new BlockCookingTableAcacia(Material.WOOD).setRegistryName(BlockCookingTableAcacia.name),
                new BlockCookingTableDarkOak(Material.WOOD).setRegistryName(BlockCookingTableDarkOak.name),

                new BlockOven().setRegistryName(BlockOven.name),
                new BlockFridge().setRegistryName(BlockFridge.name),

                new BlockSink(Material.ROCK).setRegistryName(BlockSink.name),
                new BlockSinkOak(Material.WOOD).setRegistryName(BlockSinkOak.name),
                new BlockSinkSpruce(Material.WOOD).setRegistryName(BlockSinkSpruce.name),
                new BlockSinkBirch(Material.WOOD).setRegistryName(BlockSinkBirch.name),
                new BlockSinkJungle(Material.WOOD).setRegistryName(BlockSinkJungle.name),
                new BlockSinkAcacia(Material.WOOD).setRegistryName(BlockSinkAcacia.name),
                new BlockSinkDarkOak(Material.WOOD).setRegistryName(BlockSinkDarkOak.name),

                new BlockCounter(Material.ROCK).setRegistryName(BlockCounter.name),
                new BlockCounterOak(Material.WOOD).setRegistryName(BlockCounterOak.name),
                new BlockCounterSpruce(Material.WOOD).setRegistryName(BlockCounterSpruce.name),
                new BlockCounterBirch(Material.WOOD).setRegistryName(BlockCounterBirch.name),
                new BlockCounterJungle(Material.WOOD).setRegistryName(BlockCounterJungle.name),
                new BlockCounterAcacia(Material.WOOD).setRegistryName(BlockCounterAcacia.name),
                new BlockCounterDarkOak(Material.WOOD).setRegistryName(BlockCounterDarkOak.name),

                new BlockCabinet(Material.ROCK).setRegistryName(BlockCabinet.name),
                new BlockCabinetOak(Material.WOOD).setRegistryName(BlockCabinetOak.name),
                new BlockCabinetSpruce(Material.WOOD).setRegistryName(BlockCabinetSpruce.name),
                new BlockCabinetBirch(Material.WOOD).setRegistryName(BlockCabinetBirch.name),
                new BlockCabinetJungle(Material.WOOD).setRegistryName(BlockCabinetJungle.name),
                new BlockCabinetAcacia(Material.WOOD).setRegistryName(BlockCabinetAcacia.name),
                new BlockCabinetDarkOak(Material.WOOD).setRegistryName(BlockCabinetDarkOak.name),

                new BlockCorner(Material.ROCK).setRegistryName(BlockCorner.name),
                new BlockCornerOak(Material.WOOD).setRegistryName(BlockCornerOak.name),
                new BlockCornerSpruce(Material.WOOD).setRegistryName(BlockCornerSpruce.name),
                new BlockCornerBirch(Material.WOOD).setRegistryName(BlockCornerBirch.name),
                new BlockCornerJungle(Material.WOOD).setRegistryName(BlockCornerJungle.name),
                new BlockCornerAcacia(Material.WOOD).setRegistryName(BlockCornerAcacia.name),
                new BlockCornerDarkOak(Material.WOOD).setRegistryName(BlockCornerDarkOak.name),

                new BlockToolRack().setRegistryName(BlockToolRack.name),
                new BlockToaster().setRegistryName(BlockToaster.name),
                new BlockMilkJar().setRegistryName(BlockMilkJar.name),
                new BlockCowJar().setRegistryName(BlockCowJar.name),
                new BlockSpiceRack().setRegistryName(BlockSpiceRack.name),
                new BlockFruitBasket().setRegistryName(BlockFruitBasket.name),
                new BlockCuttingBoard().setRegistryName(BlockCuttingBoard.name),

                new BlockKitchenFloor().setRegistryName(BlockKitchenFloor.name)
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                new ItemBlock(ModBlocks.cookingTable).setRegistryName(BlockCookingTable.name),
                new ItemBlock(ModBlocks.cookingTableOak).setRegistryName(BlockCookingTableOak.name),
                new ItemBlock(ModBlocks.cookingTableSpruce).setRegistryName(BlockCookingTableSpruce.name),
                new ItemBlock(ModBlocks.cookingTableBirch).setRegistryName(BlockCookingTableBirch.name),
                new ItemBlock(ModBlocks.cookingTableJungle).setRegistryName(BlockCookingTableJungle.name),
                new ItemBlock(ModBlocks.cookingTableAcacia).setRegistryName(BlockCookingTableAcacia.name),
                new ItemBlock(ModBlocks.cookingTableDarkOak).setRegistryName(BlockCookingTableDarkOak.name),

                new ItemBlock(ModBlocks.oven).setRegistryName(BlockOven.name),
                new ItemBlock(ModBlocks.fridge).setRegistryName(BlockFridge.name),

                new ItemBlock(ModBlocks.sink).setRegistryName(BlockSink.name),
                new ItemBlock(ModBlocks.sinkOak).setRegistryName(BlockSinkOak.name),
                new ItemBlock(ModBlocks.sinkSpruce).setRegistryName(BlockSinkSpruce.name),
                new ItemBlock(ModBlocks.sinkBirch).setRegistryName(BlockSinkBirch.name),
                new ItemBlock(ModBlocks.sinkJungle).setRegistryName(BlockSinkJungle.name),
                new ItemBlock(ModBlocks.sinkAcacia).setRegistryName(BlockSinkAcacia.name),
                new ItemBlock(ModBlocks.sinkDarkOak).setRegistryName(BlockSinkDarkOak.name),

                new ItemBlock(ModBlocks.counter).setRegistryName(BlockCounter.name),
                new ItemBlock(ModBlocks.counterOak).setRegistryName(BlockCounterOak.name),
                new ItemBlock(ModBlocks.counterSpruce).setRegistryName(BlockCounterSpruce.name),
                new ItemBlock(ModBlocks.counterBirch).setRegistryName(BlockCounterBirch.name),
                new ItemBlock(ModBlocks.counterJungle).setRegistryName(BlockCounterJungle.name),
                new ItemBlock(ModBlocks.counterAcacia).setRegistryName(BlockCounterAcacia.name),
                new ItemBlock(ModBlocks.counterDarkOak).setRegistryName(BlockCounterDarkOak.name),

                new ItemBlock(ModBlocks.cabinet).setRegistryName(BlockCabinet.name),
                new ItemBlock(ModBlocks.cabinetOak).setRegistryName(BlockCabinetOak.name),
                new ItemBlock(ModBlocks.cabinetSpruce).setRegistryName(BlockCabinetSpruce.name),
                new ItemBlock(ModBlocks.cabinetBirch).setRegistryName(BlockCabinetBirch.name),
                new ItemBlock(ModBlocks.cabinetJungle).setRegistryName(BlockCabinetJungle.name),
                new ItemBlock(ModBlocks.cabinetAcacia).setRegistryName(BlockCabinetAcacia.name),
                new ItemBlock(ModBlocks.cabinetDarkOak).setRegistryName(BlockCabinetDarkOak.name),

                new ItemBlock(ModBlocks.corner).setRegistryName(BlockCorner.name),
                new ItemBlock(ModBlocks.cornerOak).setRegistryName(BlockCornerOak.name),
                new ItemBlock(ModBlocks.cornerSpruce).setRegistryName(BlockCornerSpruce.name),
                new ItemBlock(ModBlocks.cornerBirch).setRegistryName(BlockCornerBirch.name),
                new ItemBlock(ModBlocks.cornerJungle).setRegistryName(BlockCornerJungle.name),
                new ItemBlock(ModBlocks.cornerAcacia).setRegistryName(BlockCornerAcacia.name),
                new ItemBlock(ModBlocks.cornerDarkOak).setRegistryName(BlockCornerDarkOak.name),

                new ItemBlock(ModBlocks.toolRack).setRegistryName(BlockToolRack.name),
                new ItemBlock(ModBlocks.toaster).setRegistryName(BlockToaster.name),
                new ItemBlock(ModBlocks.milkJar).setRegistryName(BlockMilkJar.name),
                new ItemBlock(ModBlocks.cowJar).setRegistryName(BlockCowJar.name),
                new ItemBlock(ModBlocks.spiceRack).setRegistryName(BlockSpiceRack.name),
                new ItemBlock(ModBlocks.fruitBasket).setRegistryName(BlockFruitBasket.name),
                new ItemBlock(ModBlocks.cuttingBoard).setRegistryName(BlockCuttingBoard.name),

                new ItemBlock(ModBlocks.kitchenFloor).setRegistryName(BlockKitchenFloor.name)

        );
    }

    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTable), 0, new ModelResourceLocation(BlockCookingTable.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableOak), 0, new ModelResourceLocation(BlockCookingTableOak.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableSpruce), 0, new ModelResourceLocation(BlockCookingTableSpruce.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableBirch), 0, new ModelResourceLocation(BlockCookingTableBirch.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableJungle), 0, new ModelResourceLocation(BlockCookingTableJungle.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableAcacia), 0, new ModelResourceLocation(BlockCookingTableAcacia.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cookingTableDarkOak), 0, new ModelResourceLocation(BlockCookingTableDarkOak.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(oven), 0, new ModelResourceLocation(BlockOven.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(fridge), 0, new ModelResourceLocation(BlockFridge.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sink), 0, new ModelResourceLocation(BlockSink.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkOak), 0, new ModelResourceLocation(BlockSinkOak.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkSpruce), 0, new ModelResourceLocation(BlockSinkSpruce.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkBirch), 0, new ModelResourceLocation(BlockSinkBirch.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkJungle), 0, new ModelResourceLocation(BlockSinkJungle.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkAcacia), 0, new ModelResourceLocation(BlockSinkAcacia.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sinkDarkOak), 0, new ModelResourceLocation(BlockSinkDarkOak.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counter), 0, new ModelResourceLocation(BlockCounter.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterOak), 0, new ModelResourceLocation(BlockCounterOak.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterSpruce), 0, new ModelResourceLocation(BlockCounterSpruce.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterBirch), 0, new ModelResourceLocation(BlockCounterBirch.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterJungle), 0, new ModelResourceLocation(BlockCounterJungle.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterAcacia), 0, new ModelResourceLocation(BlockCounterAcacia.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(counterDarkOak), 0, new ModelResourceLocation(BlockCounterDarkOak.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinet), 0, new ModelResourceLocation(BlockCabinet.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetOak), 0, new ModelResourceLocation(BlockCabinetOak.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetSpruce), 0, new ModelResourceLocation(BlockCabinetSpruce.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetBirch), 0, new ModelResourceLocation(BlockCabinetBirch.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetJungle), 0, new ModelResourceLocation(BlockCabinetJungle.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetAcacia), 0, new ModelResourceLocation(BlockCabinetAcacia.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cabinetDarkOak), 0, new ModelResourceLocation(BlockCabinetDarkOak.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(corner), 0, new ModelResourceLocation(BlockCorner.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerOak), 0, new ModelResourceLocation(BlockCornerOak.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerSpruce), 0, new ModelResourceLocation(BlockCornerSpruce.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerBirch), 0, new ModelResourceLocation(BlockCornerBirch.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerJungle), 0, new ModelResourceLocation(BlockCornerJungle.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerAcacia), 0, new ModelResourceLocation(BlockCornerAcacia.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cornerDarkOak), 0, new ModelResourceLocation(BlockCornerDarkOak.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(toolRack), 0, new ModelResourceLocation(BlockToolRack.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(toaster), 0, new ModelResourceLocation(BlockToaster.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(milkJar), 0, new ModelResourceLocation(BlockMilkJar.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cowJar), 0, new ModelResourceLocation(BlockCowJar.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(spiceRack), 0, new ModelResourceLocation(BlockSpiceRack.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(fruitBasket), 0, new ModelResourceLocation(BlockFruitBasket.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cuttingBoard), 0, new ModelResourceLocation(BlockCuttingBoard.registryName, "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(kitchenFloor), 0, new ModelResourceLocation(BlockKitchenFloor.registryName, "inventory"));
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileCookingTable.class, BlockCookingTable.registryName);
        GameRegistry.registerTileEntity(TileOven.class, BlockOven.registryName);
        GameRegistry.registerTileEntity(TileFridge.class, BlockFridge.registryName);
        GameRegistry.registerTileEntity(TileSink.class, BlockSink.registryName);
        GameRegistry.registerTileEntity(TileCounter.class, BlockCounter.registryName);
        GameRegistry.registerTileEntity(TileCounterOak.class, BlockCounterOak.registryName);
        GameRegistry.registerTileEntity(TileCounterSpruce.class, BlockCounterSpruce.registryName);
        GameRegistry.registerTileEntity(TileCounterBirch.class, BlockCounterBirch.registryName);
        GameRegistry.registerTileEntity(TileCounterJungle.class, BlockCounterJungle.registryName);
        GameRegistry.registerTileEntity(TileCounterAcacia.class, BlockCounterAcacia.registryName);
        GameRegistry.registerTileEntity(TileCounterDarkOak.class, BlockCounterDarkOak.registryName);
        GameRegistry.registerTileEntity(TileCabinet.class, BlockCabinet.registryName);
        GameRegistry.registerTileEntity(TileCabinetOak.class, BlockCabinetOak.registryName);
        GameRegistry.registerTileEntity(TileCabinetSpruce.class, BlockCabinetSpruce.registryName);
        GameRegistry.registerTileEntity(TileCabinetBirch.class, BlockCabinetBirch.registryName);
        GameRegistry.registerTileEntity(TileCabinetJungle.class, BlockCabinetJungle.registryName);
        GameRegistry.registerTileEntity(TileCabinetAcacia.class, BlockCabinetAcacia.registryName);
        GameRegistry.registerTileEntity(TileCabinetDarkOak.class, BlockCabinetDarkOak.registryName);
        GameRegistry.registerTileEntity(TileCorner.class, BlockCorner.registryName);
        GameRegistry.registerTileEntity(TileToolRack.class, BlockToolRack.registryName);
        GameRegistry.registerTileEntity(TileToaster.class, BlockToaster.registryName);
        GameRegistry.registerTileEntity(TileMilkJar.class, BlockMilkJar.registryName);
        GameRegistry.registerTileEntity(TileCowJar.class, BlockCowJar.registryName);
        GameRegistry.registerTileEntity(TileSpiceRack.class, BlockSpiceRack.registryName);
        GameRegistry.registerTileEntity(TileFruitBasket.class, BlockFruitBasket.registryName);
        GameRegistry.registerTileEntity(TileCuttingBoard.class, BlockCuttingBoard.registryName);
    }
}
