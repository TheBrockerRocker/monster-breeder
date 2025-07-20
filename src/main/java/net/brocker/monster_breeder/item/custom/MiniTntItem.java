package net.brocker.monster_breeder.item.custom;

import net.brocker.monster_breeder.entity.custom.MiniTntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class MiniTntItem extends Item implements ProjectileItem {
	public MiniTntItem() {
		super(new Item.Settings().maxCount(16));
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		if (!world.isClient) {
			MiniTntEntity miniTntEntity = new MiniTntEntity(world, user);
			miniTntEntity.setItem(itemStack);
			miniTntEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
			world.spawnEntity(miniTntEntity);
		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		itemStack.decrementUnlessCreative(1, user);
		return TypedActionResult.success(itemStack, world.isClient());
	}

	@Override
	public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
		MiniTntEntity miniTntEntity = new MiniTntEntity(world, pos.getX(), pos.getY(), pos.getZ());
		miniTntEntity.setItem(stack);
		return miniTntEntity;
	}
}
