package com.modpeg;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
public class ThrownRubberpearl extends ThrownEnderpearl {
    private static final int MAX_BOUNCES = 2;
    private static final double DAMP_REFLECT = 0.65;
    private static final double DAMP_TANGENT = 0.85;
    private static final double MIN_SPEED = 0.15;
    private int bouncesLeft = MAX_BOUNCES;
    private Direction lastBounceFace = null;
    private int lastBounceTick = -10;
    public ThrownRubberpearl(EntityType<? extends ThrownEnderpearl> type, Level level) {
        super(type, level);
    }
    public ThrownRubberpearl(Level level, LivingEntity shooter, ItemStack stack) {
        super(ModEntityTypes.RUBBERPEARL_TYPE, level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.10000000149, shooter.getZ());
        this.setItem(stack);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.RUBBERPEARL;
    }
    @Override
    protected void onHit(HitResult hit) {
        if (hit.getType() == HitResult.Type.BLOCK && this.bouncesLeft > 0) {
            Vec3 motion = this.getDeltaMovement();
            double speed = motion.length();
            if (speed >= MIN_SPEED) {
                this.bouncesLeft--;
                BlockHitResult blockHit = (BlockHitResult) hit;
                Direction direction = blockHit.getDirection();
                if (direction == this.lastBounceFace && this.tickCount - this.lastBounceTick <= 2) {
                    super.onHit(hit);
                    return;
                }
                double nx = direction.getStepX();
                double ny = direction.getStepY();
                double nz = direction.getStepZ();
                double dot = motion.x * nx + motion.y * ny + motion.z * nz;
                double vnx = dot * nx;
                double vny = dot * ny;
                double vnz = dot * nz;
                double vtx = motion.x - vnx;
                double vty = motion.y - vny;
                double vtz = motion.z - vnz;
                double nvx = DAMP_TANGENT * vtx - DAMP_REFLECT * vnx;
                double nvy = DAMP_TANGENT * vty - DAMP_REFLECT * vny;
                double nvz = DAMP_TANGENT * vtz - DAMP_REFLECT * vnz;
                if (nvx * nx + nvy * ny + nvz * nz <= 0) {
                    super.onHit(hit);
                    return;
                }
                this.setDeltaMovement(nvx, nvy, nvz);
                this.needsSync = true;
                Vec3 location = blockHit.getLocation();
                this.setPos(location.x + nx * 0.2, location.y + ny * 0.2, location.z + nz * 0.2);
                this.lastBounceFace = direction;
                this.lastBounceTick = this.tickCount;
                this.updateRotation();
                return;
            }
        }
        super.onHit(hit);
    }
    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("BouncesLeft", this.bouncesLeft);
    }
    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.bouncesLeft = input.getIntOr("BouncesLeft", MAX_BOUNCES);
    }
}
