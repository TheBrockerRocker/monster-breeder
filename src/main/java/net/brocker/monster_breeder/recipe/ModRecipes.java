package net.brocker.monster_breeder.recipe;

import net.brocker.monster_breeder.MonsterBreeder;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {
	public static final RecipeSerializer<BioReactionRecipe> BIO_REACTION_SERIALIZER = Registry.register(
			Registries.RECIPE_SERIALIZER, MonsterBreeder.identifier("bio_reaction"),
			new BioReactionRecipe.Serializer());
	public static final RecipeType<BioReactionRecipe> BIO_REACTION_TYPE = Registry.register(
			Registries.RECIPE_TYPE, MonsterBreeder.identifier("bio_reaction"), new RecipeType<BioReactionRecipe>() {
				@Override
				public String toString() {
					return "bio_reaction";
				}
			});
	public static final RecipeSerializer<GrowthRecipe> GROWTH_SERIALIZER = Registry.register(
			Registries.RECIPE_SERIALIZER, MonsterBreeder.identifier("growth"),
			new GrowthRecipe.Serializer());
	public static final RecipeType<GrowthRecipe> GROWTH_TYPE = Registry.register(
			Registries.RECIPE_TYPE, MonsterBreeder.identifier("growth"), new RecipeType<GrowthRecipe>() {
				@Override
				public String toString() {
					return "growth";
				}
			});

	@SuppressWarnings("EmptyMethod")
	public static void registerRecipes() {}
}
