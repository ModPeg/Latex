package com.modpeg.mixin;
import com.modpeg.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(EnchantmentHelper.class)
public class LatexWindBurstMixin {
    @Inject(
            method = "doPostAttackEffectsWithItemSource(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void latex$hiddenWindBurst(ServerLevel level, Entity victim, DamageSource damageSource,
            ItemStack stack, CallbackInfo ci) {
        if (!stack.is(ModItems.RUBBER_AXE)) {
            return;
        }
        if (!(damageSource.getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        Holder<Enchantment> windBurst = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.WIND_BURST);
        int visible = EnchantmentHelper.getItemEnchantmentLevel(windBurst, stack);
        int effective = visible + 3;
        if (effective <= 0) {
            return;
        }
        ci.cancel();
        windBurst.value().doPostAttack(level, effective,
                new EnchantedItemInUse(stack, EquipmentSlot.MAINHAND, attacker),
                EnchantmentTarget.ATTACKER, victim, damageSource);
    }
    @Inject(
            method = "doPostAttackEffectsWithItemSource(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("TAIL")
    )
    private static void latex$hiddenBounceFire(ServerLevel level, Entity victim, DamageSource damageSource,
            ItemStack stack, CallbackInfo ci) {
        if (!(victim instanceof LivingEntity livingVictim)) {
            return;
        }
        if (!(damageSource.getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        ItemStack chest = livingVictim.getItemBySlot(EquipmentSlot.CHEST);
        double strength;
        if (chest.is(ModItems.RUBBER_CHESTPLATE)) {
            strength = 16.0;
        } else {
            Holder<Enchantment> bounce = level.registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
                            Identifier.fromNamespaceAndPath("latex", "bounce")));
            int visible = EnchantmentHelper.getItemEnchantmentLevel(bounce, chest);
            if (visible <= 0) {
                return;
            }
            strength = visible >= 2 ? 16.0 : 3.0;
        }
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE,
                Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = level.registryAccess()
                .lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        DamageSource rubberSource = new DamageSource(rubberHolder, livingVictim);
        double dx = attacker.getX() - livingVictim.getX();
        double dz = attacker.getZ() - livingVictim.getZ();
        attacker.knockback(strength, dx, dz, rubberSource, 1.0f);
    }
}
