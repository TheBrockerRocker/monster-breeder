package net.brocker.monster_breeder.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record GrowthRecipeInput(ItemStack input) implements RecipeInput {
	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot == 0) {
			return input;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getSize() {
		return 1;
	}
}
