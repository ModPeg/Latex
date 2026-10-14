package com.modpeg.mixin;
import com.modpeg.RubberPickaxeItem;
import com.modpeg.RubberShovelItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(HumanoidModel.class)
public abstract class LatexPickaxeSwingAnimationMixin {
    @Inject(method = "setupAttackAnimation", at = @At("HEAD"), cancellable = true)
    private void latex$headHitAnimation(HumanoidRenderState state, CallbackInfo ci) {
        if (state.currentSwing == null) {
            return;
        }
        HumanoidArm arm = state.currentSwing.hand().asArm(state.mainArm);
        ItemStack item = arm == HumanoidArm.RIGHT ? state.rightHandItemStack : state.leftHandItemStack;
        if (item.getItem() instanceof RubberPickaxeItem || item.getItem() instanceof RubberShovelItem) {
            if (!state.currentSwing.animation().equals(item.getAttackAnimation())) return;
            HumanoidModel<?> model = (HumanoidModel<?>) (Object) this;
            ModelPart armPart = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
            float attackTime = state.swingAnimation;
            armPart.xRot = -(1.0f - attackTime) * 2.2f;
            armPart.yRot = 0.0f;
            armPart.zRot = 0.0f;
            ci.cancel();
        }
    }
}