package net.brocker.monster_breeder.item.custom;

import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.entity.custom.SyringeArrowEntity;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SyringeArrowItem extends ArrowItem {
	public SyringeArrowItem() {
		super(new Item.Settings().maxCount(1));
	}

	/**
	 * Creates an item stack with the specified blood type and purity with a count of 1.
	 * @param identifier The identifier of the entity type, should be registered.
	 * @param purity The purity of this blood sample.
	 */
	public static ItemStack createItemStack(Identifier identifier, int purity) {
		return createItemStack(identifier, purity, 1);
	}

	/**
	 * Creates an item stack with the specified blood type, purity, and count.
	 * @param identifier The identifier of the entity type, should be registered.
	 * @param purity The purity of this blood sample.
	 * @param count The amount of samples in the item stack.
	 */
	public static ItemStack createItemStack(Identifier identifier, int purity, int count) {
		ItemStack stack = new ItemStack(ModItems.USED_SYRINGE_ARROW, count);
		stack.set(ModComponents.BLOOD_COMPONENT, identifier);
		DnaUtil.setPurity(stack, purity);
		return stack;
	}

	public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
		return new SyringeArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
	}

	public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
		SyringeArrowEntity syringeArrowEntity = new SyringeArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
		syringeArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
		return syringeArrowEntity;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		if (stack.isOf(ModItems.USED_SYRINGE_ARROW)) {
			tooltip.add(Text.translatable("monster_breeder.purity", DnaUtil.getPurity(stack)).formatted(Formatting.GRAY));
			tooltip.add(Text.translatable("monster_breeder.blood", DnaUtil.getBloodType(stack).getName()).formatted(Formatting.GRAY));
		} else {
			tooltip.add(Text.translatable("monster_breeder.shoot_to_extract").formatted(Formatting.GRAY));
		}
	}
}
