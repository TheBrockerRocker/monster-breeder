package net.brocker.monster_breeder.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.recipe.GrowthRecipe;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GrowthRecipeCategory implements IRecipeCategory<GrowthRecipe> {
	public static final RecipeType<GrowthRecipe> RECIPE_TYPE = new RecipeType<>(
			Identifier.of(MonsterBreeder.MOD_ID, "growth"),
			GrowthRecipe.class
	);

	public static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID,
			"textures/gui/growth_chamber/jei.png");

	private final IDrawable ICON;
	private final IDrawable BACKGROUND;

	public GrowthRecipeCategory(IGuiHelper guiHelper) {
		ICON = guiHelper.createDrawableItemLike(ModBlocks.GROWTH_CHAMBER);
		BACKGROUND = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 83)
				.setTextureSize(176, 83)
				.build();
	}

	@Override
	public @NotNull RecipeType<GrowthRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public @NotNull Text getTitle() {
		return Text.translatable("monster_breeder.growth");
	}

	@Override
	public @Nullable IDrawable getIcon() {
		return ICON;
	}

	@Override
	public @Nullable IDrawable getBackground() {
		return BACKGROUND;
	}

	@Override
	public int getWidth() {
		return 176;
	}

	@Override
	public int getHeight() {
		return 83;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, GrowthRecipe recipe, IFocusGroup focuses) {
		builder.addInputSlot(15, 34).addItemStack(DnaSampleItem.createItemStack(recipe.inputItem()));
		builder.addOutputSlot(146, 34).addItemStack(DnaSampleItem.createItemStack(recipe.output()));
	}
}
