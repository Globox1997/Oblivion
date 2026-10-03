package net.oblivion.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.oblivion.OblivionMain;
import net.oblivion.entity.MossyCow;
import net.oblivion.entity.model.MossyCowModel;
import net.oblivion.init.RenderInit;

@Environment(EnvType.CLIENT)
public class MossyCowRenderer extends MobEntityRenderer<MossyCow, MossyCowModel<MossyCow>> {
    private static final Identifier TEXTURE = OblivionMain.identifierOf("textures/entity/mossy_cow.png");
    private static final Identifier CHERRY = OblivionMain.identifierOf("textures/entity/mossy_cow_cherry.png");
    private static final Identifier CORNFLOWER = OblivionMain.identifierOf("textures/entity/mossy_cow_cornflower.png");
    private static final Identifier DANDELION = OblivionMain.identifierOf("textures/entity/mossy_cow_dandelion.png");
    private static final Identifier MOSS = OblivionMain.identifierOf("textures/entity/mossy_cow_moss.png");
    private static final Identifier ROSE = OblivionMain.identifierOf("textures/entity/mossy_cow_rose.png");

    public MossyCowRenderer(EntityRendererFactory.Context context) {
        super(context, new MossyCowModel<>(context.getPart(RenderInit.MOSSY_COW_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTexture(MossyCow mossyCow) {
        return switch (mossyCow.getDataTracker().get(MossyCow.VARIANT)) {
            case 0 -> CHERRY;
            case 1 -> CORNFLOWER;
            case 2 -> DANDELION;
            case 3 -> MOSS;
            case 4 -> ROSE;
            default -> TEXTURE;
        };
    }
}
