package net.brocker.monster_breeder.block.dispenser;

import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class FlintAndSteelDispenserBehavior extends FallibleItemDispenserBehavior {
	@Override
	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		ServerWorld serverWorld = pointer.world();
		Direction direction = pointer.state().get(DispenserBlock.FACING);
		BlockPos blockPos = pointer.pos().offset(direction);
		BlockState blockState = serverWorld.getBlockState(blockPos);

		if (!serverWorld.isClient && blockState.isOf(ModBlocks.DNA_ALTAR)) {
			DnaAltarBlockEntity blockEntity = (DnaAltarBlockEntity) serverWorld.getBlockEntity(blockPos);
			if (blockEntity != null && !blockEntity.isSummoning()) {
				blockEntity.startSummon();
				setSuccess(true);
			}
		}

		if (this.isSuccess()) stack.damage(1, serverWorld, null, (item) -> {});
		return stack;
	}
}
