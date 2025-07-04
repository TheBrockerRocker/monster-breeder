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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.AvailableSince("1.0.0")
public class DnaUtil {
	@ApiStatus.AvailableSince("1.0.0")
	public static Registry<Dna> getRegistry() {
		return getRegistry(MonsterBreeder.server);
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static Registry<Dna> getRegistry(@Nullable MinecraftServer server) {
		return server != null ? server.getRegistryManager().get(MonsterBreederRegistries.DNA_REGISTRY_KEY)
				: MonsterBreederRegistries.DNA;
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static Registry<EntityType<?>> getEntityRegistry() {
		return MonsterBreeder.server != null ? MonsterBreeder.server.getRegistryManager().get(RegistryKeys.ENTITY_TYPE)
				: Registries.ENTITY_TYPE;
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static void setPurity(ItemStack stack, int purity) {
		stack.set(ModComponents.PURITY_COMPONENT, Math.clamp(purity, 0, 100));
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static int getPurity(ItemStack stack) {
		return stack.getOrDefault(ModComponents.PURITY_COMPONENT, 0);
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static void setBloodType(ItemStack stack, EntityType<?> entityType) {
		stack.set(ModComponents.BLOOD_COMPONENT, getEntityRegistry().getId(entityType));
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static @Nullable Identifier getBloodTypeIdentifier(ItemStack stack) {
		return stack.get(ModComponents.BLOOD_COMPONENT);
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static @Nullable EntityType<?> getBloodType(ItemStack stack) {
		Identifier identifier = getBloodTypeIdentifier(stack);
		return identifier != null ? getEntityRegistry().get(identifier) : null;
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static void setDna(ItemStack stack, Identifier identifier) {
		stack.set(ModComponents.DNA_COMPONENT, identifier);
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static Identifier getDnaIdentifier(ItemStack stack) {
		return stack.getOrDefault(ModComponents.DNA_COMPONENT, ModDna.UNKNOWN);
	}

	@ApiStatus.AvailableSince("1.0.0")
	public static @Nullable Dna getDna(ItemStack stack) {
		return getRegistry().get(getDnaIdentifier(stack));
	}

	@ApiStatus.AvailableSince("1.0.0")
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

	@ApiStatus.AvailableSince("1.0.0")
	public static @Nullable Dna getDna(EntityType<?> type) {
		return getRegistry().get(getDnaIdentifier(type));
	}
}
