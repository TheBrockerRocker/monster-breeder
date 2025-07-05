package net.brocker.monster_breeder.block.dispenser;

import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class DnaExtractorDispenserBehavior extends FallibleItemDispenserBehavior {
	@Override
	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		ServerWorld serverWorld = pointer.world();
		if (!serverWorld.isClient()) {
			BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
			this.setSuccess(tryExtractDNAFromEntity(serverWorld, blockPos));
			if (this.isSuccess()) {
				stack.damage(1, serverWorld, null, (item) -> {});
			}
		}

		return stack;
	}

	private static boolean tryExtractDNAFromEntity(ServerWorld world, BlockPos pos) {
		List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR);
		for (LivingEntity livingEntity : list) {
			if (livingEntity instanceof MobEntity mobEntity) {
				EntityType<?> mobType = mobEntity.getType();
				Identifier dnaIdentifier = DnaUtil.getDnaIdentifier(mobType);

				if (dnaIdentifier == null) continue;

				DefaultedList<ItemStack> items = DefaultedList.of();
				items.add(DnaSampleItem.createItemStack(dnaIdentifier));
				ItemScatterer.spawn(world, pos, items);
				return true;
			}
		}

		return false;
	}
}
