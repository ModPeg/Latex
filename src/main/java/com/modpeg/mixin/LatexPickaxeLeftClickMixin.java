package com.modpeg.mixin;
import com.modpeg.RubberPickaxeItem;
import com.modpeg.RubberShovelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.level.block.state.BlockState;
@Mixin(ServerPlayerGameMode.class)
public class LatexPickaxeLeftClickMixin {
    @Shadow
    @Final
    protected ServerPlayer player;
    @Shadow
    protected ServerLevel level;
    @Shadow
    private boolean isDestroyingBlock;
    @Shadow
    private int destroyProgressStart;
    @Shadow
    private int gameTicks;
    @Shadow
    private BlockPos destroyPos;
    @Inject(
            method = "handleBlockBreakAction(Lnet/minecraft/core/BlockPos;Lnet/minecraft/network/protocol/game/ServerboundPlayerActionPacket$Action;Lnet/minecraft/core/Direction;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;attack(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = false
    )
    private void latex$recoilOnLeftClick(BlockPos pos, ServerboundPlayerActionPacket.Action action, Direction direction, int worldHeight, int i, CallbackInfo ci) {
        if (action != ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK) return;
        if (!(player.getMainHandItem().getItem() instanceof RubberPickaxeItem)
                && !(player.getMainHandItem().getItem() instanceof RubberShovelItem)) return;
        if (player.isCreative()) return;
        if (level.getBlockState(pos).getDestroyProgress(player, level, pos) >= 1.0F) return;
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        player.hurt(new DamageSource(rubberHolder, player), 1.0f);
    }
    @Inject(
            method = "incrementDestroyProgress(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;I)F",
            at = @At("TAIL"),
            cancellable = false
    )
    private void latex$recoilPerSwing(BlockState state, BlockPos pos, int i, CallbackInfoReturnable<Float> cir) {
        if (!isDestroyingBlock) return;
        if (!pos.equals(destroyPos)) return;
        if (!(player.getMainHandItem().getItem() instanceof RubberPickaxeItem)
                && !(player.getMainHandItem().getItem() instanceof RubberShovelItem)) return;
        if (player.isCreative()) return;
        if (level.getBlockState(pos).isAir()) return;
        float progress = cir.getReturnValueF();
        if (progress <= 0.0F || progress >= 1.0F) return;
        int base = player.getMainHandItem().getAttackAnimation().duration();
        if (base <= 0) base = 6;
        if ((gameTicks - destroyProgressStart) % base != 0) return;
        ResourceKey<DamageType> rubberKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("latex", "rubber"));
        Holder<DamageType> rubberHolder = player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(rubberKey);
        player.hurt(new DamageSource(rubberHolder, player), 1.0f);
    }
}
