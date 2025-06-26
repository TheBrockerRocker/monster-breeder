package net.brocker.monster_breeder.recipe;

import net.brocker.monster_breeder.MonsterBreeder;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
	public static final RecipeSerializer<BioReactionRecipe> BIO_REACTION_SERIALIZER = Registry.register(
			Registries.RECIPE_SERIALIZER, Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction"),
			new BioReactionRecipe.Serializer());
	public static final RecipeType<BioReactionRecipe> BIO_REACTION_TYPE = Registry.register(
			Registries.RECIPE_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction"), new RecipeType<BioReactionRecipe>() {
				@Override
				public String toString() {
					return "bio_reaction";
				}
			});

	public static void registerRecipes() {}
}
