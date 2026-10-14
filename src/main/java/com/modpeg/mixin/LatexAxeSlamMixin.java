package com.modpeg.mixin;
import com.modpeg.RubberAxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)
public abstract class LatexAxeSlamMixin {
    @Unique
    private boolean latex$inSlam;
    @Shadow
    protected abstract float getKnockback(Entity entity, DamageSource damageSource);
    @Inject(method = "checkFallDamage(DZLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"))
    private void latex$axeSlam(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition, CallbackInfo ci) {
        if (latex$inSlam) {
            return;
        }
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.level().isClientSide()) {
            return;
        }
        if (!(self instanceof Player player)) {
            return;
        }
        if (player.isCreative()) {
            return;
        }
        if (!onGround) {
            return;
        }
        if (!(self.getWeaponItem().getItem() instanceof RubberAxeItem)) {
            return;
        }
        if (!(self.fallDistance > MaceItem.SMASH_ATTACK_FALL_THRESHOLD)) {
            return;
        }
        if (self.isFallFlying()) {
            return;
        }
        ServerLevel level = (ServerLevel) self.level();
        ItemStack axe = self.getWeaponItem();
        DamageSource src = level.damageSources().playerAttack(player);
        float base = (float) self.getAttributeValue(Attributes.ATTACK_DAMAGE);
        latex$inSlam = true;
        try {
            for (Entity e : level.getEntities(self, self.getBoundingBox().inflate(3.0),
                    e -> e instanceof LivingEntity le && le.isAlive() && !le.isRemoved() && e != self
                            && !(e instanceof Player pl && (pl.isCreative() || pl.isSpectator()))
                            && !self.isAlliedTo(e))) {
                LivingEntity victim = (LivingEntity) e;
                float dmg = EnchantmentHelper.modifyDamage(level, axe, victim, src, base);
                Vec3 old = victim.getDeltaMovement();
                if (victim.hurtOrSimulate(src, dmg)) {
                    self.causeExtraKnockback(victim, this.getKnockback(victim, src), old, src, dmg, false);
                }
            }
            self.setDeltaMovement(self.getDeltaMovement().with(net.minecraft.core.Direction.Axis.Y, 0.01));
            self.setIgnoreFallDamageFromCurrentImpulse(true, self.position());
            Holder<Enchantment> windBurst = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.WIND_BURST);
            int effective = EnchantmentHelper.getItemEnchantmentLevel(windBurst, axe) + 3;
            if (effective > 0) {
                windBurst.value().doPostAttack(level, effective,
                        new EnchantedItemInUse(axe, EquipmentSlot.MAINHAND, player),
                        EnchantmentTarget.ATTACKER, self, src);
            }
            if (self instanceof ServerPlayer sp) {
                sp.connection.send(new ClientboundSetEntityMotionPacket(sp));
                sp.setSpawnExtraParticlesOnFall(true);
                Vec3 pos = self.position();
                level.playSound(null, pos.x(), pos.y(), pos.z(),
                        self.fallDistance > 5.0 ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND,
                        self.getSoundSource(), 1.0F, 1.0F);
            }
        } finally {
            latex$inSlam = false;
        }
    }
}
