package net.brocker.monster_breeder.api.util;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.registry.MonsterBreederRegistries;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.dna.ModDna;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DnaUtil {
	public static Registry<Dna> getRegistry() {
		return getRegistry(MonsterBreeder.server);
	}
	public static Registry<Dna> getRegistry(@Nullable MinecraftServer server) {
		return server != null ? server.getRegistryManager().get(MonsterBreederRegistries.DNA_REGISTRY_KEY)
				: MonsterBreederRegistries.DNA;
	}

	public static Registry<EntityType<?>> getEntityRegistry() {
		return MonsterBreeder.server != null ? MonsterBreeder.server.getRegistryManager().get(RegistryKeys.ENTITY_TYPE)
				: Registries.ENTITY_TYPE;
	}

	public static void setPurity(ItemStack stack, int purity) {
		stack.set(ModComponents.PURITY_COMPONENT, Math.clamp(purity, 0, 100));
	}

	public static int getPurity(ItemStack stack) {
		return stack.getOrDefault(ModComponents.PURITY_COMPONENT, 0);
	}

	public static void setBloodType(ItemStack stack, EntityType<?> entityType) {
		stack.set(ModComponents.BLOOD_COMPONENT, getEntityRegistry().getId(entityType));
	}

	public static @Nullable Identifier getBloodTypeIdentifier(ItemStack stack) {
		return stack.get(ModComponents.BLOOD_COMPONENT);
	}

	public static @Nullable EntityType<?> getBloodType(ItemStack stack) {
		Identifier identifier = getBloodTypeIdentifier(stack);
		return identifier != null ? getEntityRegistry().get(identifier) : null;
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
