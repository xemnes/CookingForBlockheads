package net.blay09.mods.cookingforblockheads.client;

import net.blay09.mods.cookingforblockheads.CommonProxy;
import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.blay09.mods.cookingforblockheads.block.*;
import net.blay09.mods.cookingforblockheads.client.render.*;
import net.blay09.mods.cookingforblockheads.tile.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {

    public static final TextureAtlasSprite[] ovenToolIcons = new TextureAtlasSprite[4];

    public static final TextureAtlasSprite[] ovenToolTextures = new TextureAtlasSprite[8];

    private final DefaultStateMapper dummyStateMapper = new DefaultStateMapper();

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomStateMapper(ModBlocks.fridge, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                if (state.getValue(BlockFridge.TYPE) == BlockFridge.FridgeType.LARGE) {
                    return new ModelResourceLocation(CookingForBlockheads.MOD_ID + ":fridge_large", getPropertyString(state.getProperties()));
                } else if (state.getValue(BlockFridge.TYPE) == BlockFridge.FridgeType.INVISIBLE) {
                    return new ModelResourceLocation(CookingForBlockheads.MOD_ID + ":fridge_invisible", getPropertyString(state.getProperties()));
                }
                return super.getModelResourceLocation(state);
            }
        });

        DefaultStateMapper ignorePropertiesStateMapper = new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                ResourceLocation location = state.getBlock().getRegistryName();
                return new ModelResourceLocation(location != null ? location.toString() : "", "normal");
            }
        };

        ClientRegistry.bindTileEntitySpecialRenderer(TileToolRack.class, new ToolRackRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCookingTable.class, new CookingTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileOven.class, new OvenRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFridge.class, new FridgeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMilkJar.class, new MilkJarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCowJar.class, new CowJarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileToaster.class, new ToasterRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpiceRack.class, new SpiceRackRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounter.class, new CounterRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterOak.class, new CounterRendererOak());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterSpruce.class, new CounterRendererSpruce());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterBirch.class, new CounterRendererBirch());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterJungle.class, new CounterRendererJungle());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterAcacia.class, new CounterRendererAcacia());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCounterDarkOak.class, new CounterRendererDarkOak());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinet.class, new CabinetRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetOak.class, new CabinetRendererOak());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetSpruce.class, new CabinetRendererSpruce());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetBirch.class, new CabinetRendererBirch());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetJungle.class, new CabinetRendererJungle());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetAcacia.class, new CabinetRendererAcacia());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCabinetDarkOak.class, new CabinetRendererDarkOak());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSink.class, new SinkRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFruitBasket.class, new FruitBasketRenderer());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileFridge) {
                    TileFridge baseFridge = ((TileFridge) tileEntity).getBaseFridge();
                    return baseFridge.getFridgeColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.fridge);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileCookingTable) {
                    TileCookingTable tileBase = ((TileCookingTable) tileEntity).getBaseTile();
                    return tileBase.getDyedColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.cookingTable);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileCounter) {
                    TileCounter tileBase = ((TileCounter) tileEntity).getBaseTile();
                    return tileBase.getDyedColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.counter);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileCabinet) {
                    TileCabinet tileBase = ((TileCabinet) tileEntity).getBaseTile();
                    return tileBase.getDyedColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.cabinet);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileCorner) {
                    TileCorner tileBase = ((TileCorner) tileEntity).getBaseTile();
                    return tileBase.getDyedColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.corner);

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileSink) {
                    TileSink tileBase = ((TileSink) tileEntity).getBaseTile();
                    return tileBase.getDyedColor().getColorValue();
                }
            }
            return 0xFFFFFFFF;
        }, ModBlocks.sink);
    }

    @Override
    public List<String> getItemTooltip(ItemStack itemStack, EntityPlayer player) {
        return itemStack.getTooltip(player, ITooltipFlag.TooltipFlags.NORMAL);
    }

    @SubscribeEvent
    public void registerIcons(TextureStitchEvent.Pre event) {
        ovenToolIcons[0] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_bakeware"));
        ovenToolIcons[1] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_pot"));
        ovenToolIcons[2] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_saucepan"));
        ovenToolIcons[3] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_skillet"));

        ovenToolTextures[0] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/cooking_pot_bottom"));
        ovenToolTextures[1] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/cooking_pot_parts"));
        ovenToolTextures[2] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/cooking_pot_side"));
        ovenToolTextures[3] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/cooking_pot_top"));

        ovenToolTextures[4] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/skillet_top"));
        ovenToolTextures[5] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/skillet_side"));
        ovenToolTextures[6] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/skillet_bottom"));

        ovenToolTextures[7] = event.getMap().registerSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "blocks/bakeware"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        try {
            IModel model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/milk_jar_liquid"));
            MilkJarRenderer.modelMilkLiquid = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/sink_liquid"));
            SinkRenderer.modelSinkLiquid = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_door"));
            OvenRenderer.modelDoor = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_door_active"));
            OvenRenderer.modelDoorActive = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_cooking_pot"));
            OvenRenderer.modelCookingPot = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_cooking_skillet"));
            OvenRenderer.modelCookingSkillet = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_cooking_saucepan"));
            OvenRenderer.modelCookingSaucepan = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_cooking_bakeware"));
            OvenRenderer.modelCookingBakeware = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door"));
            FridgeRenderer.modelDoor = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door_flipped"));
            FridgeRenderer.modelDoorFlipped = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door"));
            FridgeRenderer.modelDoorLarge = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door_flipped"));
            FridgeRenderer.modelDoorLargeFlipped = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door_handle"));
            FridgeRenderer.modelHandle = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door_handle"));
            FridgeRenderer.modelHandleLarge = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

        } catch (Exception e) {
            e.printStackTrace();
        }

        CounterRenderer.models = new IBakedModel[4];
        CounterRenderer.modelsFlipped = new IBakedModel[4];
        CabinetRenderer.models = new IBakedModel[4];
        CabinetRenderer.modelsFlipped = new IBakedModel[4];
        IBlockState defaultState = ModBlocks.counter.getDefaultState();
        IBlockState state = defaultState.withProperty(BlockCounter.PASS, BlockCounter.ModelPass.DOOR);
        IBlockState flippedState = defaultState.withProperty(BlockCounter.PASS, BlockCounter.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
                CounterRenderer.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounter.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounter.FACING, facing).getProperties())));
                CounterRenderer.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounter.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounter.FACING, facing).getProperties())));

                CabinetRenderer.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinet.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounter.FACING, facing).getProperties())));
                CabinetRenderer.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinet.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounter.FACING, facing).getProperties())));
        }

        CounterRendererOak.models = new IBakedModel[4];
        CounterRendererOak.modelsFlipped = new IBakedModel[4];
        CabinetRendererOak.models = new IBakedModel[4];
        CabinetRendererOak.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterOak.getDefaultState();
        state = defaultState.withProperty(BlockCounterOak.PASS, BlockCounterOak.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterOak.PASS, BlockCounterOak.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererOak.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterOak.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterOak.FACING, facing).getProperties())));
            CounterRendererOak.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterOak.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterOak.FACING, facing).getProperties())));

            CabinetRendererOak.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetOak.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterOak.FACING, facing).getProperties())));
            CabinetRendererOak.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetOak.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterOak.FACING, facing).getProperties())));
        }

        CounterRendererSpruce.models = new IBakedModel[4];
        CounterRendererSpruce.modelsFlipped = new IBakedModel[4];
        CabinetRendererSpruce.models = new IBakedModel[4];
        CabinetRendererSpruce.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterSpruce.getDefaultState();
        state = defaultState.withProperty(BlockCounterSpruce.PASS, BlockCounterSpruce.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterSpruce.PASS, BlockCounterSpruce.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererSpruce.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterSpruce.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterSpruce.FACING, facing).getProperties())));
            CounterRendererSpruce.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterSpruce.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterSpruce.FACING, facing).getProperties())));

            CabinetRendererSpruce.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetSpruce.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterSpruce.FACING, facing).getProperties())));
            CabinetRendererSpruce.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetSpruce.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterSpruce.FACING, facing).getProperties())));
        }

        CounterRendererBirch.models = new IBakedModel[4];
        CounterRendererBirch.modelsFlipped = new IBakedModel[4];
        CabinetRendererBirch.models = new IBakedModel[4];
        CabinetRendererBirch.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterBirch.getDefaultState();
        state = defaultState.withProperty(BlockCounterBirch.PASS, BlockCounterBirch.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterBirch.PASS, BlockCounterBirch.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererBirch.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterBirch.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterBirch.FACING, facing).getProperties())));
            CounterRendererBirch.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterBirch.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterBirch.FACING, facing).getProperties())));

            CabinetRendererBirch.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetBirch.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterBirch.FACING, facing).getProperties())));
            CabinetRendererBirch.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetBirch.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterBirch.FACING, facing).getProperties())));
        }

        CounterRendererJungle.models = new IBakedModel[4];
        CounterRendererJungle.modelsFlipped = new IBakedModel[4];
        CabinetRendererJungle.models = new IBakedModel[4];
        CabinetRendererJungle.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterJungle.getDefaultState();
        state = defaultState.withProperty(BlockCounterJungle.PASS, BlockCounterJungle.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterJungle.PASS, BlockCounterJungle.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererJungle.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterJungle.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterJungle.FACING, facing).getProperties())));
            CounterRendererJungle.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterJungle.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterJungle.FACING, facing).getProperties())));

            CabinetRendererJungle.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetJungle.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterJungle.FACING, facing).getProperties())));
            CabinetRendererJungle.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetJungle.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterJungle.FACING, facing).getProperties())));
        }

        CounterRendererAcacia.models = new IBakedModel[4];
        CounterRendererAcacia.modelsFlipped = new IBakedModel[4];
        CabinetRendererAcacia.models = new IBakedModel[4];
        CabinetRendererAcacia.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterAcacia.getDefaultState();
        state = defaultState.withProperty(BlockCounterAcacia.PASS, BlockCounterAcacia.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterAcacia.PASS, BlockCounterAcacia.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererAcacia.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterAcacia.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterAcacia.FACING, facing).getProperties())));
            CounterRendererAcacia.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterAcacia.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterAcacia.FACING, facing).getProperties())));

            CabinetRendererAcacia.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetAcacia.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterAcacia.FACING, facing).getProperties())));
            CabinetRendererAcacia.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetAcacia.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterAcacia.FACING, facing).getProperties())));
        }

        CounterRendererDarkOak.models = new IBakedModel[4];
        CounterRendererDarkOak.modelsFlipped = new IBakedModel[4];
        CabinetRendererDarkOak.models = new IBakedModel[4];
        CabinetRendererDarkOak.modelsFlipped = new IBakedModel[4];
        defaultState = ModBlocks.counterDarkOak.getDefaultState();
        state = defaultState.withProperty(BlockCounterDarkOak.PASS, BlockCounterDarkOak.ModelPass.DOOR);
        flippedState = defaultState.withProperty(BlockCounterDarkOak.PASS, BlockCounterDarkOak.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            CounterRendererDarkOak.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterDarkOak.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterDarkOak.FACING, facing).getProperties())));
            CounterRendererDarkOak.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCounterDarkOak.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterDarkOak.FACING, facing).getProperties())));

            CabinetRendererDarkOak.models[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetDarkOak.registryName, dummyStateMapper.getPropertyString(state.withProperty(BlockCounterDarkOak.FACING, facing).getProperties())));
            CabinetRendererDarkOak.modelsFlipped[i] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinetDarkOak.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(BlockCounterDarkOak.FACING, facing).getProperties())));
        }
    }

}
