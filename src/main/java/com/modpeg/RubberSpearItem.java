package com.modpeg;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
public class RubberSpearItem extends Item {
    public RubberSpearItem(Properties properties) {
        super(properties);
    }
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 2_000_000;
    }
}
