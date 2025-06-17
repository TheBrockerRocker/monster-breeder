package net.brocker.monsterbreeder.integrations.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.brocker.monsterbreeder.recipe.BioReactionRecipe;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BioReactionRecipeCategory implements IRecipeCategory<BioReactionRecipe> {
	public static final RecipeType<BioReactionRecipe> RECIPE_TYPE = new RecipeType<>(
			Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction"),
			BioReactionRecipe.class
	);

	private final IDrawable ICON;

	public BioReactionRecipeCategory(IGuiHelper guiHelper) {
		ICON = guiHelper.createDrawableItemLike(ModBlocks.BIO_REACTION_CHAMBER);
	}

	/**
	 * @return the type of recipe that this category handles.
	 * @since 9.5.0
	 */
	@Override
	public RecipeType<BioReactionRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	/**
	 * Returns a text component representing the name of this recipe type.
	 * Drawn at the top of the recipe GUI pages for this category.
	 *
	 * @since 7.6.4
	 */
	@Override
	public Text getTitle() {
		return Text.translatable("monsterbreeder.jei.bio_reaction");
	}

	/**
	 * Icon for the category tab.
	 * You can use {@link IGuiHelper#createDrawableIngredient(IIngredientType, Object)}
	 * to create a drawable from an ingredient.
	 * <p>
	 * If null is returned here, JEI will try to use the first recipe catalyst as the icon.
	 *
	 * @return icon to draw on the category tab, max size is 16x16 pixels.
	 */
	@Override
	public @Nullable IDrawable getIcon() {
		return ICON;
	}

	@Override
	public int getWidth() {
		return 160;
	}

	@Override
	public int getHeight() {
		return 80;
	}

	/**
	 * Sets all the recipe's ingredients by filling out an instance of {@link IRecipeLayoutBuilder}.
	 * This is used by JEI for lookups, to figure out what ingredients are inputs and outputs for a recipe.
	 *
	 * @param builder
	 * @param recipe
	 * @param focuses
	 * @since 9.4.0
	 */
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BioReactionRecipe recipe, IFocusGroup focuses) {
		builder.addInputSlot(40, 20).addItemStack(DnaSampleItem.createItemStack(recipe.inputItem1()));
		builder.addInputSlot(40, 60).addItemStack(DnaSampleItem.createItemStack(recipe.inputItem2()));
		builder.addOutputSlot(120, 40).addItemStack(DnaSampleItem.createItemStack(recipe.output()));
	}
}
