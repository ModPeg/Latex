package com.modpeg.mixin;
import com.modpeg.ModItems;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Player.class)
public abstract class LatexRubberBootsMixin {
    @Unique
    private double latex$anchorX;
    @Unique
    private double latex$anchorY;
    @Unique
    private double latex$anchorZ;
    @Unique
    private boolean latex$wasGrounded = true;
    @Unique
    private boolean latex$armed = false;
    @Unique
    private boolean latex$bouncing = false;
    @Unique
    private static final double MIN_UP = 0.55;
    @Unique
    private static final double MAX_UP = 1.25;
    @Unique
    private static final double VY_BASE = 0.25;
    @Unique
    private static final double VY_GAIN = 1.2;
    @Unique
    private static final double K_H = 0.35;
    @Unique
    private static final double MAX_H = 0.8;
    @Unique
    private static final double MIN_KICK = 0.2;
    @Inject(method = "tick()V", at = @At("TAIL"))
    private void latex$rebound(CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self.level().isClientSide()) {
            return;
        }
        boolean g = self.onGround();
        boolean excluded = self.isSpectator() || self.isCreative() || self.isFallFlying() || self.isSwimming() || self.onClimbable() || self.isPassenger() || self.isInWater() || self.isInLava() || !self.isAlive();
        boolean wearing = !excluded && self.getItemBySlot(EquipmentSlot.FEET).is(ModItems.RUBBER_BOOTS);
        if (!wearing) {
            this.latex$anchorX = self.getX();
            this.latex$anchorY = self.getY();
            this.latex$anchorZ = self.getZ();
            this.latex$wasGrounded = g;
            this.latex$armed = false;
            this.latex$bouncing = false;
            return;
        }
        self.resetFallDistance();
        if (!g) {
            if (this.latex$wasGrounded && !this.latex$bouncing) {
                this.latex$armed = true;
            }
            this.latex$wasGrounded = false;
            return;
        }
        if (!this.latex$wasGrounded) {
            if (this.latex$bouncing) {
                this.latex$bouncing = false;
                this.latex$armed = false;
                this.latex$anchorX = self.getX();
                this.latex$anchorY = self.getY();
                this.latex$anchorZ = self.getZ();
                this.latex$wasGrounded = true;
                return;
            }
            if (this.latex$armed) {
                double dx = this.latex$anchorX - self.getX();
                double dy = this.latex$anchorY - self.getY();
                double dz = this.latex$anchorZ - self.getZ();
                double vy = VY_BASE + VY_GAIN * Math.sqrt(0.16 * Math.max(0.0, dy));
                if (vy < MIN_UP) {
                    vy = MIN_UP;
                } else if (vy > MAX_UP) {
                    vy = MAX_UP;
                }
                double vx = dx * K_H;
                double vz = dz * K_H;
                double h2 = dx * dx + dz * dz;
                double h = Math.sqrt(vx * vx + vz * vz);
                if (h > MAX_H && h > 1.0E-6) {
                    double s = MAX_H / h;
                    vx *= s;
                    vz *= s;
                } else if (h2 > 0.25) {
                    if (h < MIN_KICK && h > 1.0E-6) {
                        double s = MIN_KICK / h;
                        vx *= s;
                        vz *= s;
                    }
                } else {
                    vx = 0.0;
                    vz = 0.0;
                }
                self.setDeltaMovement(vx, vy, vz);
                this.latex$bouncing = true;
                this.latex$armed = false;
                this.latex$wasGrounded = true;
                if (self instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(self));
                }
                return;
            }
            this.latex$anchorX = self.getX();
            this.latex$anchorY = self.getY();
            this.latex$anchorZ = self.getZ();
            this.latex$wasGrounded = true;
            return;
        }
        this.latex$anchorX = self.getX();
        this.latex$anchorY = self.getY();
        this.latex$anchorZ = self.getZ();
    }
}
