package net.oblivion.entity.model;

import com.google.common.collect.ImmutableList;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.oblivion.entity.Skeletal;

@Environment(EnvType.CLIENT)
public class SkeletalModel<T extends MobEntity & RangedAttackMob> extends BipedEntityModel<T> {

    //    private final ModelPart head;
//    private final ModelPart spear;
//    private final ModelPart body;
//    private final ModelPart leftArm;
//    private final ModelPart rightArm;
//    private final ModelPart leftLeg;
//    private final ModelPart rightLeg;
//    private final ModelPart quiver;
//    private final ModelPart arrow_left;
//    private final ModelPart arrow_right;

    public SkeletalModel(ModelPart root) {
        super(root);
//        this.quiver = this.body.getChild("quiver");
//        this.arrow_left = this.quiver.getChild("arrow_left");
//        this.arrow_right = this.quiver.getChild("arrow_right");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();
        addParts(modelPartData);
        return TexturedModelData.of(modelData, 64, 64);
    }

    private static void addParts(ModelPartData data) {
        data.addChild(
                EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
        );
        data.addChild(
                EntityModelPartNames.LEFT_ARM,
                ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F)
        );
        data.addChild(
                EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(-2.0F, 12.0F, 0.0F)
        );
        data.addChild(
                EntityModelPartNames.LEFT_LEG,
                ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(2.0F, 12.0F, 0.0F)
        );
        ModelPartData quiver = data.getChild("body").addChild("quiver", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -4.0F, -1.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 4.0F, 3.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData arrow_left = quiver.addChild("arrow_left", ModelPartBuilder.create().uv(12, 34).cuboid(-1.5F, -5.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.01F))
                .uv(12, 31).cuboid(0.0F, -5.0F, -1.5F, 0.0F, 6.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(0.5F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        ModelPartData arrow_right = quiver.addChild("arrow_right", ModelPartBuilder.create().uv(12, 34).cuboid(-1.5F, -5.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.01F))
                .uv(12, 31).cuboid(0.0F, -5.0F, -1.5F, 0.0F, 6.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(-0.5F, -4.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

    }

//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData modelPartData = modelData.getRoot();
//        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData headwear = modelPartData.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData quiver = body.addChild("quiver", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -4.0F, -1.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 4.0F, 3.0F, 0.0F, 0.0F, -0.7854F));
//
//        ModelPartData arrow_left = quiver.addChild("arrow_left", ModelPartBuilder.create().uv(12, 34).cuboid(-1.5F, -5.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.01F))
//                .uv(12, 31).cuboid(0.0F, -5.0F, -1.5F, 0.0F, 6.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(0.5F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1309F));
//
//        ModelPartData arrow_right = quiver.addChild("arrow_right", ModelPartBuilder.create().uv(12, 34).cuboid(-1.5F, -5.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.01F))
//                .uv(12, 31).cuboid(0.0F, -5.0F, -1.5F, 0.0F, 6.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(-0.5F, -4.0F, 0.0F, 0.0F, 0.0F, -0.0436F));
//
//        ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
//
//        ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
//
//        ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.1F));
//
//        ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.1F));
//        return TexturedModelData.of(modelData, 64, 64);
//    }

//    @Override
//    public Iterable<ModelPart> getParts() {
//        return ImmutableList.of(this.head, this.body, this.rightLeg, this.leftLeg, this.rightArm, this.leftArm);
//    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        float f = arm == Arm.RIGHT ? 1.0F : -1.0F;
        ModelPart modelPart = this.getArm(arm);
        modelPart.pivotX += f;
        modelPart.rotate(matrices);
        modelPart.pivotX -= f;
    }

    @Override
    public void animateModel(T mobEntity, float f, float g, float h) {
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        ItemStack itemStack = mobEntity.getStackInHand(Hand.MAIN_HAND);
        if (itemStack.isOf(Items.BOW) && mobEntity.isAttacking()) {
            if (mobEntity.getMainArm() == Arm.RIGHT) {
                this.rightArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
            }
        }

        super.animateModel(mobEntity, f, g, h);
    }

//    @Override
//    public void setAngles(T vanguard, float f, float g, float h, float i, float j) {
////        this.head.yaw = i * 0.0119453292F;
////        this.head.pitch = j * 0.0061453292F;
////        this.spear.pitch = -0.7854F;
////        this.rightArm.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 2.0F * g * 0.4F;
////        this.rightArm.yaw = 0.0F;
////        this.rightArm.roll = 0.0F;
////        this.leftArm.pitch = (MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F) * 0.2F - 1.5708F;
////        this.leftArm.yaw = 0.0F;
////        this.leftArm.roll = 0.0F;
////        this.rightLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g * 0.5F;
////        this.rightLeg.yaw = 0.0F;
////        this.rightLeg.roll = 0.0F;
////        this.leftLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g * 0.5F;
////        this.leftLeg.yaw = 0.0F;
////        this.leftLeg.roll = 0.0F;
////        float k = MathHelper.sin(this.handSwingProgress * 3.1415927F);
////        if (k > 0.0F) {
////            this.rightArm.pitch = -k * 1.5F;
////            this.rightArm.roll = -k * 0.4F;
////        }
//    }

}
