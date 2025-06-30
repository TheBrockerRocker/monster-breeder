package net.brocker.monster_breeder.api.datagen;

import net.brocker.monster_breeder.recipe.GrowthRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class GrowthRecipeJsonBuilder {
	private final Identifier output;
	private final Identifier input;
	private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();

	private GrowthRecipeJsonBuilder(Identifier output, Identifier input) {
		this.output = output;
		this.input = input;
	}

	public static GrowthRecipeJsonBuilder create(Identifier output, Identifier input) {
		return new GrowthRecipeJsonBuilder(output, input);
	}

	public GrowthRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
		this.criteria.put(string, advancementCriterion);
		return this;
	}

	public void offerTo(RecipeExporter exporter, Identifier recipeId) {
		this.validate(recipeId);
		Advancement.Builder builder = exporter.getAdvancementBuilder()
				.criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId))
				.criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
		this.criteria.forEach(builder::criterion);
		GrowthRecipe recipe = new GrowthRecipe(input, output);
		exporter.accept(recipeId, recipe, builder.build(recipeId.withPrefixedPath("recipes/dna_growth/")));
	}

	private void validate(Identifier recipeId) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + recipeId);
		}
	}
}
