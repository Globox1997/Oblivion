package net.oblivion.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.oblivion.OblivionMain;
import net.oblivion.block.render.DrillBlockEntityRenderer;
import net.oblivion.entity.model.*;
import net.oblivion.entity.render.*;

@Environment(EnvType.CLIENT)
public class RenderInit {

    public static final EntityModelLayer SHLAMA_LAYER = new EntityModelLayer(OblivionMain.identifierOf("shlama_render_layer"), "shlama_render_layer");
    public static final EntityModelLayer ELYSIAN_ELK_LAYER = new EntityModelLayer(OblivionMain.identifierOf("elysian_elk_render_layer"), "elysian_elk_render_layer");
    public static final EntityModelLayer ELYSIAN_SHAMAN_LAYER = new EntityModelLayer(OblivionMain.identifierOf("elysian_shaman_render_layer"), "elysian_shaman_render_layer");
    public static final EntityModelLayer ELYSIAN_WOLF_LAYER = new EntityModelLayer(OblivionMain.identifierOf("elysian_wolf_render_layer"), "elysian_wolf_render_layer");
    public static final EntityModelLayer GOBLIN_LAYER = new EntityModelLayer(OblivionMain.identifierOf("goblin_render_layer"), "goblin_render_layer");
    public static final EntityModelLayer TREEDER_LAYER = new EntityModelLayer(OblivionMain.identifierOf("treeder_render_layer"), "treeder_render_layer");

    public static final EntityModelLayer DRILL_LAYER = new EntityModelLayer(OblivionMain.identifierOf("drill_render_layer"), "drill_render_layer");

    public static void init(){
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_WOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_WOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_WOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.POTTED_IRON_WOOD_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_WOOD_LEAVES, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SILVER_BIRCH_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SILVER_BIRCH_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SILVER_BIRCH_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.POTTED_SILVER_BIRCH_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SILVER_BIRCH_LEAVES, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.RUNE_WOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.RUNE_WOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.RUNE_WOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.POTTED_RUNE_WOOD_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.RUNE_WOOD_LEAVES, RenderLayer.getCutoutMipped());

        EntityModelLayerRegistry.registerModelLayer(DRILL_LAYER, DrillBlockEntityRenderer::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DRILL, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(BlockInit.DRILL_BLOCK_ENTITY, DrillBlockEntityRenderer::new);

        // Entity Renderer
        EntityRendererRegistry.register(EntityInit.SHLAMA, ShlamaRenderer::new);
        EntityRendererRegistry.register(EntityInit.ELYSIAN_ELK, ElysianElkRenderer::new);
        EntityRendererRegistry.register(EntityInit.ELYSIAN_SHAMAN, ElysianShamanRenderer::new);
        EntityRendererRegistry.register(EntityInit.ELYSIAN_WOLF, ElysianWolfRenderer::new);
        EntityRendererRegistry.register(EntityInit.GOBLIN, GoblinRenderer::new);
        EntityRendererRegistry.register(EntityInit.TREEDER, TreederRenderer::new);
        // Entity Model
        EntityModelLayerRegistry.registerModelLayer(SHLAMA_LAYER, ShlamaModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ELYSIAN_ELK_LAYER, ElysianElkModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ELYSIAN_SHAMAN_LAYER, ElysianShamanModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ELYSIAN_WOLF_LAYER, ElysianWolfModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GOBLIN_LAYER, GoblinModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TREEDER_LAYER, TreederModel::getTexturedModelData);
    }
}
