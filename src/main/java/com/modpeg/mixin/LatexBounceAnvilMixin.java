package com.modpeg.mixin;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(AnvilMenu.class)
public abstract class LatexBounceAnvilMixin extends AbstractContainerMenu {
    protected LatexBounceAnvilMixin(MenuType<?> type, int containerId) {
        super(type, containerId);
    }
    @Redirect(
            method = "createResult()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/ItemEnchantments$Mutable;set(Lnet/minecraft/core/Holder;I)V"
            )
    )
    private void latex$capBounceBookMerge(ItemEnchantments.Mutable mutable, Holder<Enchantment> holder, int newLevel) {
        if (newLevel <= 1) {
            mutable.set(holder, newLevel);
            return;
        }
        if (!holder.is(ResourceKey.create(Registries.ENCHANTMENT,
                Identifier.fromNamespaceAndPath("latex", "bounce")))) {
            mutable.set(holder, newLevel);
            return;
        }
        ItemStack sacrifice = this.getSlot(1).getItem();
        int sacrificeLevel = sacrifice.isEmpty() ? 0
                : EnchantmentHelper.getEnchantmentsForCrafting(sacrifice).getLevel(holder);
        if (sacrificeLevel >= 2) {
            mutable.set(holder, newLevel);
            return;
        }
        ItemStack target = this.getSlot(0).getItem();
        int targetLevel = target.isEmpty() ? 0
                : EnchantmentHelper.getEnchantmentsForCrafting(target).getLevel(holder);
        if (targetLevel >= newLevel) {
            mutable.set(holder, newLevel);
            return;
        }
        mutable.set(holder, 1);
    }
}
