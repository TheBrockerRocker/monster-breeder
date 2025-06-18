package net.brocker.monsterbreeder.integrations.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.*;
import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.brocker.monsterbreeder.item.custom.SyringeItem;
import net.brocker.monsterbreeder.recipe.BioReactionRecipe;
import net.brocker.monsterbreeder.recipe.ModRecipes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class JeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
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
		DnaRegistry.INSTANCE.getKeySet().stream().filter(identifier -> !identifier.equals(ModDna.UNKNOWN)).forEach(identifier -> usedSyringes.add(SyringeItem.createItemStack(identifier, 100)));
		registration.addExtraItemStacks(usedSyringes);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		IModPlugin.super.registerItemSubtypes(registration);

		registration.registerSubtypeInterpreter(ModItems.USED_SYRINGE, new DnaSubtypeInterpreter());
		registration.registerSubtypeInterpreter(ModItems.DNA_SAMPLE, new DnaSubtypeInterpreter());
	}

	private static class DnaSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
		@Override
		public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
			return getLegacyStringSubtypeInfo(ingredient, context);
		}

		@Override
		public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
			return DnaUtil.getDnaIdentifier(ingredient).toString();
		}
	}
}
