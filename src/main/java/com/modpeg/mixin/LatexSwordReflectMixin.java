package com.modpeg.mixin;
import com.modpeg.RubberSwordItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Player.class)
public class LatexSwordReflectMixin {
    @Unique
    private Entity latex$victim;
    @Unique
    private float latex$preHealth;
    @Unique
    private Vec3 latex$preVelocity;
    @Unique
    private boolean latex$armed;
    @Inject(method = "attack", at = @At("HEAD"))
    private void latex$snapshotVictim(Entity victim, CallbackInfo ci) {
        Player self = (Player) (Object) this;
        latex$armed = false;
        latex$victim = null;
        if (self.level().isClientSide()) {
            return;
        }
        if (self.isCreative()) {
            return;
        }
        if (!(victim instanceof LivingEntity living)) {
            return;
        }
        if (!(self.getWeaponItem().getItem() instanceof RubberSwordItem)) {
            return;
        }
        latex$victim = victim;
        latex$preHealth = living.getHealth();
        latex$preVelocity = victim.getDeltaMovement();
        latex$armed = true;
    }
    @Inject(method = "attack", at = @At("TAIL"))
    private void latex$reflectDamage(Entity victim, CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (!latex$armed) {
            return;
        }
        latex$armed = false;
        if (victim != latex$victim) {
            latex$victim = null;
            return;
        }
        latex$victim = null;
        if (self.level().isClientSide()) {
            return;
        }
        if (self.isCreative()) {
            return;
        }
        if (!(victim instanceof LivingEntity living)) {
            return;
        }
        if (!(self.getWeaponItem().getItem() instanceof RubberSwordItem)) {
            return;
        }
        float dealt = latex$preHealth - living.getHealth();
        if (dealt <= 0.0f) {
            return;
        }
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = self.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        DamageSource rubberSource = new DamageSource(rubberHolder, self);
        self.hurt(rubberSource, dealt * 0.5f);
        Vec3 post = victim.getDeltaMovement();
        double mx = post.x - latex$preVelocity.x;
        double mz = post.z - latex$preVelocity.z;
        double strength = Math.sqrt(mx * mx + mz * mz);
        if (strength > 1.0e-4) {
            self.knockback(strength, -mx / strength, -mz / strength, rubberSource, dealt);
        }
    }
}
