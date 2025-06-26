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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BioReactionRecipeCategory implements IRecipeCategory<BioReactionRecipe> {
	public static final RecipeType<BioReactionRecipe> RECIPE_TYPE = new RecipeType<>(
			Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction"),
			BioReactionRecipe.class
	);

	public static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID,
			"textures/gui/bio_reaction_chamber/jei.png");

	private final IDrawable ICON;
	private final IDrawable BACKGROUND;

	public BioReactionRecipeCategory(IGuiHelper guiHelper) {
		ICON = guiHelper.createDrawableItemLike(ModBlocks.BIO_REACTION_CHAMBER);
		BACKGROUND = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 83)
				.setTextureSize(176, 83)
				.build();
	}

	@Override
	public @NotNull RecipeType<BioReactionRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public @NotNull Text getTitle() {
		return Text.translatable("monsterbreeder.bio_reaction");
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
	public void setRecipe(IRecipeLayoutBuilder builder, BioReactionRecipe recipe, IFocusGroup focuses) {
		builder.addInputSlot(15, 16).addItemStack(DnaSampleItem.createItemStack(recipe.inputItem1()));
		builder.addInputSlot(15, 52).addItemStack(DnaSampleItem.createItemStack(recipe.inputItem2()));
		builder.addOutputSlot(146, 34).addItemStack(DnaSampleItem.createItemStack(recipe.output()));
	}
}
