package net.brocker.monster_breeder.integration.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.comparison.EntryComparator;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.item.custom.SyringeItem;
import net.brocker.monster_breeder.recipe.BioReactionRecipe;
import net.brocker.monster_breeder.recipe.GrowthRecipe;
import net.brocker.monster_breeder.recipe.ModRecipes;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class ReiClientPlugin implements REIClientPlugin {
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(new BioReactionRecipeCategory());
		registry.add(new GrowthRecipeCategory());

		registry.addWorkstations(BioReactionRecipeCategory.CATEGORY_IDENTIFIER, EntryStacks.of(ModBlocks.BIO_REACTION_CHAMBER));
		registry.addWorkstations(GrowthRecipeCategory.CATEGORY_IDENTIFIER, EntryStacks.of(ModBlocks.GROWTH_CHAMBER));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.registerRecipeFiller(BioReactionRecipe.class, ModRecipes.BIO_REACTION_TYPE,
				BioReactionRecipeCategory.Display::new);
		registry.registerRecipeFiller(GrowthRecipe.class, ModRecipes.GROWTH_TYPE,
				GrowthRecipeCategory.Display::new);
	}

	@Override
	public void registerEntries(EntryRegistry registry) {
		registry.removeEntry(EntryStacks.of(new ItemStack(ModItems.USED_SYRINGE)));

		Collection<EntryStack<ItemStack>> usedSyringes = new HashSet<>();
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
						.forEach(identifier -> usedSyringes.add(EntryStacks.of(SyringeItem.createItemStack(identifier, 100))))
				);
		registry.addEntries(usedSyringes);
	}

	@Override
	public void registerItemComparators(ItemComparatorRegistry registry) {
		registry.registerComponents(ModItems.DNA_SAMPLE);

		EntryComparator<ComponentMap> componentHasher = EntryComparator.component(ModComponents.PURITY_COMPONENT);
		registry.register((context, stack) -> componentHasher.hash(context, stack.getComponents()), ModItems.USED_SYRINGE);
	}
}
