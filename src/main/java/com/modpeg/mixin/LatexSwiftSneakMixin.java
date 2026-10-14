package com.modpeg.mixin;
import com.modpeg.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LivingEntity.class)
public class LatexSwiftSneakMixin {
    @Inject(
            method = "getAttributeValue(Lnet/minecraft/core/Holder;)D",
            at = @At("TAIL"),
            cancellable = true
    )
    private void latex$hiddenSwiftSneak(Holder<Attribute> holder, CallbackInfoReturnable<Double> cir) {
        if (holder != Attributes.SNEAKING_SPEED) {
            return;
        }
        LivingEntity self = (LivingEntity) (Object) this;
        ItemStack legs = self.getItemBySlot(EquipmentSlot.LEGS);
        if (!legs.is(ModItems.RUBBER_LEGGINGS)) {
            return;
        }
        Holder<Enchantment> swiftHolder = self.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.SWIFT_SNEAK);
        int visible = EnchantmentHelper.getItemEnchantmentLevel(swiftHolder, legs);
        double delta = 0.75 - 0.15 * visible;
        if (delta > 1e-6) {
            cir.setReturnValue(cir.getReturnValue() + delta);
        }
    }
}
