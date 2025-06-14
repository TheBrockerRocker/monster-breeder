package net.brocker.monsterbreeder.api.util;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class DnaUtil {
	public static Dna getDna(ItemStack stack) {
		Identifier id = stack.getOrDefault(ModComponents.DNA_COMPONENT, ModDna.UNKNOWN);
		return DnaRegistry.INSTANCE.getValue(id);
	}
}
