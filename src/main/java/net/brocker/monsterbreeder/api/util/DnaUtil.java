package net.brocker.monsterbreeder.api.util;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.MonsterBreederRegistries;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DnaUtil {
	public static Registry<Dna> getRegistry() {
		return getRegistry(MonsterBreeder.server);
	}
	public static Registry<Dna> getRegistry(MinecraftServer server) {
		return server.getRegistryManager().get(MonsterBreederRegistries.DNA_REGISTRY_KEY);
	}

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
		return getRegistry().get(getDnaIdentifier(stack));
	}

	public static @Nullable Identifier getDnaIdentifier(EntityType<?> type) {
		Registry<Dna> dnaRegistry = getRegistry();
		return dnaRegistry
				.getKeys()
				.stream()
				.filter(identifier -> dnaRegistry.get(identifier).getSourceMobs().contains(type))
				.findFirst()
				.map(RegistryKey::getValue)
				.orElse(null);
	}
	public static @Nullable Dna getDna(EntityType<?> type) {
		return getRegistry().get(getDnaIdentifier(type));
	}
}
