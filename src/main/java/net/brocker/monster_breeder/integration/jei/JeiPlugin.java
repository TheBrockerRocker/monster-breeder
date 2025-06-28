package net.brocker.monster_breeder.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.*;
import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.item.custom.SyringeItem;
import net.brocker.monster_breeder.recipe.BioReactionRecipe;
import net.brocker.monster_breeder.recipe.ModRecipes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class JeiPlugin implements IModPlugin {
	@Override
	public @NotNull Identifier getPluginUid() {
		return Identifier.of(MonsterBreeder.MOD_ID, "jei_integration");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IModPlugin.super.registerCategories(registration);

		registration.addRecipeCategories(new BioReactionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		IModPlugin.super.registerRecipes(registration);

		RecipeManager manager = MinecraftClient.getInstance().world.getRecipeManager();
		List<BioReactionRecipe> bioReactionRecipes = manager
				.listAllOfType(ModRecipes.BIO_REACTION_TYPE)
				.stream()
				.map(RecipeEntry::value)
				.toList();

		registration.addRecipes(BioReactionRecipeCategory.RECIPE_TYPE, bioReactionRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		IModPlugin.super.registerRecipeCatalysts(registration);

		registration.addRecipeCatalyst(ModBlocks.BIO_REACTION_CHAMBER, BioReactionRecipeCategory.RECIPE_TYPE);
	}

	@Override
	public void registerExtraIngredients(IExtraIngredientRegistration registration) {
		IModPlugin.super.registerExtraIngredients(registration);

		Collection<ItemStack> usedSyringes = new HashSet<>();
		DnaUtil.getRegistry()
				.getKeys()
				.stream()
				.map(RegistryKey::getValue)
				.filter(identifier -> !identifier.equals(ModDna.UNKNOWN))
				.map(identifier -> DnaUtil.getRegistry().get(identifier))
				.filter(Objects::nonNull)
				.map(Dna::getSourceMobs)
				.forEach(sources -> sources
						.stream()
						.map(mob -> DnaUtil.getEntityRegistry().getId(mob))
						.forEach(identifier -> usedSyringes.add(SyringeItem.createItemStack(identifier, 100)))
				);
		registration.addExtraItemStacks(usedSyringes);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		IModPlugin.super.registerItemSubtypes(registration);

		registration.registerSubtypeInterpreter(ModItems.USED_SYRINGE, new BloodTypeSubtypeInterpreter());
		registration.registerSubtypeInterpreter(ModItems.DNA_SAMPLE, new DnaSubtypeInterpreter());
	}

	private static class BloodTypeSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
		@Override
		public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
			return getLegacyStringSubtypeInfo(ingredient, context);
		}

		@Override
		public @NotNull String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
			return DnaUtil.getBloodTypeIdentifier(ingredient).toString();
		}
	}

	private static class DnaSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
		@Override
		public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
			return getLegacyStringSubtypeInfo(ingredient, context);
		}

		@Override
		public @NotNull String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
			return DnaUtil.getDnaIdentifier(ingredient).toString();
		}
	}
}
