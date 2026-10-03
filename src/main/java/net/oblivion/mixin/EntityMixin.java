package net.oblivion.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.oblivion.access.EntityAccess;
import net.oblivion.init.BlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin implements EntityAccess {

    @Unique
    private boolean shimmeringInvisible = false;

    @Shadow
    public boolean noClip;

    @Shadow
    private EntityDimensions dimensions;

    @Shadow
    public void setInvisible(boolean invisible) {
    }

    @Shadow
    public World getWorld() {
        return null;
    }

    @Shadow
    public final Vec3d getEyePos() {
        return null;
    }

    @Inject(method = "baseTick", at = @At("TAIL"))
    private void baseTickMixin(CallbackInfo info) {
        if (this.shimmeringInvisible && !this.getWorld().isClient() && !this.isInsideShimmeringTallGrass()) {
            this.oblivion$setShimmeringInvisible(false);
        }
    }

    @Unique
    private boolean isInsideShimmeringTallGrass() {
        if (this.noClip) {
            return false;
        } else {
            float f = this.dimensions.width() * 0.8F;
            Box box = Box.of(this.getEyePos(), f, 1.0E-6, f);
            return BlockPos.stream(box).anyMatch(pos -> {
                        BlockState blockState = this.getWorld().getBlockState(pos);
                        return !blockState.isAir() && blockState.isOf(BlockInit.SHIMMERING_TALL_GRASS);
                    }
            );
        }
    }

    @Override
    public void oblivion$setShimmeringInvisible(boolean invisible) {
        this.shimmeringInvisible = invisible;
        if (!invisible && (Object) this instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            invisible = true;
        }
        this.setInvisible(invisible);
    }

    @Override
    public boolean oblivion$getShimmeringInvisible() {
        return this.shimmeringInvisible;
    }
}
