package net.brocker.monsterbreeder.api.util;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DnaUtil {
	public static void setPurity(ItemStack stack, int purity) {
		stack.set(ModComponents.PURITY_COMPONENT, Math.clamp(purity, 0, 100));
	}
	public static int getPurity(ItemStack stack) {
		return stack.getOrDefault(ModComponents.PURITY_COMPONENT, 0);
	}

	public static void setDna(ItemStack stack, Identifier identifier) {
		stack.set(ModComponents.DNA_COMPONENT, identifier);
	}

	public static Identifier getDnaIdentifier(ItemStack stack) {
		return stack.getOrDefault(ModComponents.DNA_COMPONENT, ModDna.UNKNOWN);
	}
	public static @Nullable Dna getDna(ItemStack stack) {
		return DnaRegistry.INSTANCE.getValue(getDnaIdentifier(stack));
	}

	public static @Nullable Identifier getDnaIdentifier(EntityType<?> type) {
		return DnaRegistry.INSTANCE
				.getKeySet()
				.stream()
				.filter(identifier -> DnaRegistry.INSTANCE.getValue(identifier).getSourceMobs().contains(type))
				.findFirst()
				.orElse(null);
	}
	public static @Nullable Dna getDna(EntityType<?> type) {
		return DnaRegistry.INSTANCE.getValue(getDnaIdentifier(type));
	}
}
