package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
public class RubberPickaxeItem extends Item {
    public RubberPickaxeItem(Properties properties) {
        super(properties);
    }
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level().isClientSide()) return;
        if (!(attacker instanceof Player player)) return;
        if (player.isCreative()) return;
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        player.hurt(new DamageSource(rubberHolder, player), 1.0f);
    }
    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        boolean r = super.mineBlock(stack, level, state, pos, miner);
        if (miner.level().isClientSide()) return r;
        if (!(miner instanceof Player player)) return r;
        if (player.isCreative()) return r;
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        player.hurt(new DamageSource(rubberHolder, player), 1.0f);
        return r;
    }
}
