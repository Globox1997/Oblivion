package net.oblivion.entity;

import java.util.EnumSet;
import java.util.UUID;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.oblivion.init.EntityInit;
import org.jetbrains.annotations.Nullable;

public class ElysianElk extends AnimalEntity implements Angerable {

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(ElysianElk.class, TrackedDataHandlerRegistry.INTEGER);

    @Nullable
    private UUID angryAt;

    public ElysianElk(EntityType<? extends ElysianElk> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createElysianElkAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.22D, Ingredient.ofItems(Items.SHORT_GRASS, Items.TALL_GRASS, Items.FERN), true));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.add(5, new EscapePlayerGoal(this, 2.0D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

//    @Override
//    protected SoundEvent getAmbientSound() {
//        return this.isBaby() ? SoundInit.BABY_DEER_IDLE_EVENT : SoundInit.DEER_IDLE_EVENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return this.isBaby() ? SoundInit.BABY_DEER_HURT_EVENT : SoundInit.DEER_HURT_EVENT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return this.isBaby() ? SoundInit.BABY_DEER_HURT_EVENT : SoundInit.DEER_DEATH_EVENT;
//    }

    @Override
    protected void initDataTracker(net.minecraft.entity.data.DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ANGER_TIME, 0);
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    public ElysianElk createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return EntityInit.ELYSIAN_ELK.create(serverWorld);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.SHORT_GRASS) || stack.isOf(Items.FERN) || stack.isOf(Items.TALL_GRASS);
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(this.random.nextInt(300) + 600);
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    private class EscapePlayerGoal extends Goal {
        protected final ElysianElk deerEntity;
        protected final double speed;
        protected double targetX;
        protected double targetY;
        protected double targetZ;

        public EscapePlayerGoal(ElysianElk deerEntity, double speed) {
            this.deerEntity = deerEntity;
            this.speed = speed;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            if (this.deerEntity.getWorld().getClosestPlayer(this.deerEntity, 4.0D) == null) {
                return false;
            } else {
                return this.findTarget();
            }
        }

        private boolean findTarget() {
            Vec3d vec3d = NoPenaltyTargeting.find(this.deerEntity, 12, 4);
            if (vec3d == null) {
                return false;
            } else {
                this.targetX = vec3d.x;
                this.targetY = vec3d.y;
                this.targetZ = vec3d.z;
                return true;
            }
        }

        @Override
        public void start() {
            this.deerEntity.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
        }

        @Override
        public boolean shouldContinue() {
            return !this.deerEntity.getNavigation().isIdle();
        }

    }

}
