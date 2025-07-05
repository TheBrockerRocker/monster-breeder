package net.brocker.monster_breeder.integration.rei;

import me.shedaniel.rei.api.common.entry.comparison.EntryComparator;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.component.ComponentMap;

public class ReiServerPlugin implements REIServerPlugin {
	@Override
	public void registerItemComparators(ItemComparatorRegistry registry) {
		registry.registerComponents(ModItems.DNA_SAMPLE);

		EntryComparator<ComponentMap> componentHasher = EntryComparator.component(ModComponents.PURITY_COMPONENT);
		registry.register((context, stack) -> componentHasher.hash(context, stack.getComponents()), ModItems.USED_SYRINGE);
	}
}
