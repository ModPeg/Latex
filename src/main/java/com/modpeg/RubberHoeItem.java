package com.modpeg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
public class RubberHoeItem extends Item {
    public RubberHoeItem(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        InteractionResult center = super.useOn(ctx);
        Level level = ctx.getLevel();
        if (level.isClientSide() || !center.consumesAction()) {
            return center;
        }
        Player player = ctx.getPlayer();
        if (player == null) {
            return center;
        }
        BlockPos centerPos = ctx.getClickedPos();
        Direction face = ctx.getClickedFace();
        InteractionHand hand = ctx.getHand();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }
                    BlockPos pos = centerPos.offset(dx, dy, dz);
                    Vec3 loc = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    BlockHitResult hit = new BlockHitResult(loc, face, pos, false);
                    super.useOn(new UseOnContext(player, hand, hit));
                }
            }
        }
        return center;
    }
}
