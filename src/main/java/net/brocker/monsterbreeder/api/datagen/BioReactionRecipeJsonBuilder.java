package net.brocker.monsterbreeder.api.datagen;

import net.brocker.monsterbreeder.recipe.BioReactionRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class BioReactionRecipeJsonBuilder {
	private final Identifier output;
	private final Identifier input1;
	private final Identifier input2;

	private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();
	@Nullable
	private String group;
	private boolean showNotification = true;

	private BioReactionRecipeJsonBuilder(Identifier output, Identifier input1, Identifier input2) {
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
	}

	public static BioReactionRecipeJsonBuilder create(Identifier output, Identifier input1, Identifier input2) {
		return new BioReactionRecipeJsonBuilder(output, input1, input2);
	}

	public BioReactionRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
		this.criteria.put(string, advancementCriterion);
		return this;
	}

	public BioReactionRecipeJsonBuilder group(@Nullable String string) {
		this.group = string;
		return this;
	}

	public BioReactionRecipeJsonBuilder showNotification(boolean showNotification) {
		this.showNotification = showNotification;
		return this;
	}

	public void offerTo(RecipeExporter exporter, Identifier recipeId) {
		this.validate(recipeId);
		Advancement.Builder builder = exporter.getAdvancementBuilder()
				.criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId))
				.criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
		this.criteria.forEach(builder::criterion);
		BioReactionRecipe recipe = new BioReactionRecipe(input1, input2, output, showNotification, group);
		exporter.accept(recipeId, recipe, builder.build(recipeId.withPrefixedPath("recipes/dna/")));
	}

	private void validate(Identifier recipeId) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + recipeId);
		}
	}
}
