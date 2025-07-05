package net.brocker.monster_breeder.integration.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.recipe.BioReactionRecipe;
import net.brocker.monster_breeder.recipe.GrowthRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BioReactionRecipeCategory implements DisplayCategory<BioReactionRecipeCategory.Display> {
	public static final CategoryIdentifier<Display> CATEGORY_IDENTIFIER = CategoryIdentifier.of(MonsterBreeder.MOD_ID, "bio_reaction");
	public static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID,
			"textures/gui/bio_reaction_chamber/jei.png");

	@Override
	public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
		return CATEGORY_IDENTIFIER;
	}

	@Override
	public Text getTitle() {
		return Text.translatable("monster_breeder.bio_reaction");
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(ModBlocks.BIO_REACTION_CHAMBER);
	}

	@Override
	public List<Widget> setupDisplay(Display display, Rectangle bounds) {
		Point startPoint = new Point(bounds.getMinX(), bounds.getMinY());
		List<Widget> widgets = new ArrayList<>();

		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createTexturedWidget(
				TEXTURE,
				new Rectangle(startPoint.x, startPoint.y, getDisplayWidth(display), getDisplayHeight()),
				0,
				0,
				getDisplayWidth(display),
				getDisplayHeight()
		));

		widgets.add(Widgets.createSlot(new Point(startPoint.x + 15, startPoint.y + 16))
				.entries(display.getInputEntries().get(0)).disableBackground().markInput());

		widgets.add(Widgets.createSlot(new Point(startPoint.x + 15, startPoint.y + 52))
				.entries(display.getInputEntries().get(1)).disableBackground().markInput());

		widgets.add(Widgets.createSlot(new Point(startPoint.x + 146, startPoint.y + 34))
				.entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

		return widgets;
	}

	@Override
	public int getDisplayWidth(Display display) {
		return 176;
	}

	@Override
	public int getDisplayHeight() {
		return 83;
	}

	public static class Display extends BasicDisplay {
		public Display(RecipeEntry<BioReactionRecipe> recipeEntry) {
			super(
					List.of(
							EntryIngredients.of(DnaSampleItem.createItemStack(recipeEntry.value().inputItem1())),
							EntryIngredients.of(DnaSampleItem.createItemStack(recipeEntry.value().inputItem2()))
					),
					Collections.singletonList(
							EntryIngredients.of(DnaSampleItem.createItemStack(recipeEntry.value().output()))
					)
			);
		}

		@Override
		public CategoryIdentifier<?> getCategoryIdentifier() {
			return BioReactionRecipeCategory.CATEGORY_IDENTIFIER;
		}
	}
}
