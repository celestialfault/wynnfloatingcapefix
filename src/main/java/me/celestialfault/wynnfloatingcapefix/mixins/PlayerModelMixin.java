package me.celestialfault.wynnfloatingcapefix.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerModel.class)
public class PlayerModelMixin {
	@ModifyExpressionValue(
		method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"
		)
	)
	public boolean removeHiddenArmorCapeAngle(boolean original, @Local(argsOnly = true) LivingEntity entity) {
		ItemStack chestplate = entity.getItemBySlot(EquipmentSlot.CHEST);
		return (!chestplate.isEmpty() && chestplate.getItem().equals(Items.STONE_SHOVEL) && chestplate.getDamageValue() == 36) || original;
	}
}
