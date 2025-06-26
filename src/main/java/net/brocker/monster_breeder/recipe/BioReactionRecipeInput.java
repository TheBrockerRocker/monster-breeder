package net.brocker.monster_breeder.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record BioReactionRecipeInput (ItemStack input1, ItemStack input2) implements RecipeInput {
	@Override
	public ItemStack getStackInSlot(int slot) {
		switch (slot) {
			case 0 -> { return input1; }
			case 1 -> { return input2; }
			default -> {
				return ItemStack.EMPTY;
			}
		}
	}

	@Override
	public int getSize() {
		return 2;
	}
}
