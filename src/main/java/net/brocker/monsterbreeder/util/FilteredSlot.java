package net.brocker.monsterbreeder.util;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class FilteredSlot extends Slot {
	private final Filter filter;

	public FilteredSlot(Inventory inventory, int index, int x, int y, Filter filter) {
		super(inventory, index, x, y);
		this.filter = filter;
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return super.canInsert(stack) && filter.test(stack, this);
	}

	@FunctionalInterface
	public interface Filter {
		boolean test(ItemStack stack, FilteredSlot slot);
	}
}
