package net.oblivion.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.oblivion.OblivionMain;
import net.oblivion.entity.Skeletal;
import net.oblivion.entity.model.SkeletalModel;
import net.oblivion.init.RenderInit;

@Environment(EnvType.CLIENT)
public class SkeletalRenderer<T extends Skeletal> extends BipedEntityRenderer<T, SkeletalModel<T>> {
    private static final Identifier TEXTURE = OblivionMain.identifierOf("textures/entity/skeletal.png");

    public SkeletalRenderer(EntityRendererFactory.Context context) {
        super(context, new SkeletalModel<>(context.getPart(RenderInit.SKELETAL_LAYER)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(
                this, new SkeletalModel(context.getPart(EntityModelLayers.SKELETON_INNER_ARMOR)), new SkeletalModel(context.getPart(EntityModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
    }

    @Override
    public void scale(Skeletal entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
    }

    @Override
    public Identifier getTexture(Skeletal skeletal) {
        return TEXTURE;
    }
}

