package net.oblivion.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;
import net.oblivion.entity.MossyCow;

@Environment(EnvType.CLIENT)
public class MossyCowModel<T extends MossyCow> extends AnimalModel<T> {
    private final ModelPart body_moss;
    private final ModelPart head;
    private final ModelPart antler;
    private final ModelPart left_front_leg;
    private final ModelPart left_back_leg;
    private final ModelPart right_back_leg;
    private final ModelPart right_front_leg;

    public MossyCowModel(ModelPart root) {
        super(false, 6.0F, 6.0F, 2.0F, 2.0F, 24);
        this.body_moss = root.getChild("body_moss");
        this.head = root.getChild("head");
        this.antler = head.getChild("antler");
        this.left_front_leg = root.getChild("left_front_leg");
        this.left_back_leg = root.getChild("left_back_leg");
        this.right_back_leg = root.getChild("right_back_leg");
        this.right_front_leg = root.getChild("right_front_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body_moss = modelPartData.addChild("body_moss", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -9.0F, -12.0F, 16.0F, 15.0F, 24.0F, new Dilation(0.1F))
                .uv(38, 90).cuboid(-8.0F, -9.0F, -12.0F, 16.0F, 14.0F, 24.0F, new Dilation(0.2F))
                .uv(0, 39).cuboid(-8.0F, -6.0F, -12.0F, 16.0F, 12.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 75).cuboid(-6.0F, -5.0F, -7.0F, 12.0F, 12.0F, 7.0F, new Dilation(0.1F))
                .uv(0, 112).cuboid(-6.0F, -4.0F, -7.0F, 12.0F, 9.0F, 7.0F, new Dilation(0.0F))
                .uv(1, 101).cuboid(-3.0F, 1.0F, -9.0F, 6.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 55).cuboid(-2.6F, -13.0F, -3.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, -12.0F));

        ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(16, 55).cuboid(-3.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -3.6F, 0.0F, 1.5272F, 0.0F));

        ModelPartData antler = head.addChild("antler", ModelPartBuilder.create().uv(38, 75).mirrored().cuboid(6.0F, -5.0F, -5.0F, 14.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(80, 11).mirrored().cuboid(18.0F, -9.0F, -5.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(80, 0).mirrored().cuboid(14.0F, -13.0F, -5.0F, 2.0F, 8.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(80, 0).cuboid(-16.0F, -13.0F, -5.0F, 2.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(80, 11).cuboid(-20.0F, -9.0F, -5.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 75).cuboid(-20.0F, -5.0F, -5.0F, 14.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_front_leg = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(72, 75).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, -7.0F));

        ModelPartData left_back_leg = modelPartData.addChild("left_back_leg", ModelPartBuilder.create().uv(72, 75).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, 7.0F));

        ModelPartData right_back_leg = modelPartData.addChild("right_back_leg", ModelPartBuilder.create().uv(72, 75).mirrored().cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 15.0F, 7.0F));

        ModelPartData right_front_leg = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(72, 75).mirrored().cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 15.0F, -7.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body_moss, this.left_front_leg, this.right_front_leg, this.left_back_leg, this.right_back_leg);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.010453292F;
        this.head.yaw = headYaw * 0.010453292F;

        this.left_front_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.right_front_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.left_back_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.right_back_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.antler.visible = !entity.isBaby();
    }

}
