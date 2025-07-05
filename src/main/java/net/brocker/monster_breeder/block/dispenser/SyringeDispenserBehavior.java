package net.brocker.monster_breeder.block.dispenser;

import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.config.ModConfig;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

public class SyringeDispenserBehavior extends FallibleItemDispenserBehavior {
	@Override
	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		Direction direction = pointer.state().get(DispenserBlock.FACING);
		Position position = DispenserBlock.getOutputLocation(pointer);
		ServerWorld serverWorld = pointer.world();

		setSuccess(false);
		if (stack.isOf(ModItems.USED_SYRINGE) && DnaUtil.getPurity(stack) >= 100) {
			spawnItem(pointer.world(), stack.withItem(ModItems.USED_SYRINGE), 6, direction, position);
			setSuccess(true);
			return ItemStack.EMPTY;
		} else if (tryExtractBloodFromEntity(serverWorld, pointer.pos().offset(pointer.state().get(DispenserBlock.FACING)), stack)) {
			setSuccess(true);
			return stack.withItem(ModItems.USED_SYRINGE);
		} else return stack;
	}

	private static boolean tryExtractBloodFromEntity(ServerWorld world, BlockPos pos, ItemStack stack) {
		if (world.isClient) return false;
		boolean isUsed = stack.isOf(ModItems.USED_SYRINGE);

		List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR);
		for (LivingEntity livingEntity : list) {
			if (livingEntity instanceof MobEntity mobEntity) {
				EntityType<?> mobType = mobEntity.getType();
				Identifier dnaIdentifier = DnaUtil.getDnaIdentifier(mobType);

				if (dnaIdentifier == null || (isUsed && DnaUtil.getBloodType(stack) != mobType)) continue;

				List<String> extractedFrom = new ArrayList<>(stack.getOrDefault(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, new ArrayList<>()));
				String mobId = mobEntity.getUuidAsString();

				if (extractedFrom.contains(mobId)) continue;
				extractedFrom.add(mobId);

				int purity = DnaUtil.getPurity(stack);
				if (purity >= 100) continue;

				stack.set(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, extractedFrom);
				DnaUtil.setBloodType(stack, mobType);
				DnaUtil.setPurity(stack, (int) (purity + (10 * ModConfig.bloodPurityModifier)));

				return true;
			}
		}

		return false;
	}
}
