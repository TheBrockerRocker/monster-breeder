package net.brocker.monster_breeder.api.registry;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.dna.ModDna;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.HashSet;

public class MonsterBreederRegistries {
	public static final RegistryKey<Registry<Dna>> DNA_REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.of(MonsterBreeder.MOD_ID, "dna"));
	public static final SimpleDefaultedRegistry<Dna> DNA = FabricRegistryBuilder
			.createDefaulted(DNA_REGISTRY_KEY, ModDna.UNKNOWN)
			.attribute(RegistryAttribute.SYNCED)
			.buildAndRegister();

	public static void initialize() {
		Registry.register(DNA, ModDna.UNKNOWN, new Dna(
				"monster_breeder.unknown",
				Rarity.COMMON,
				Dna.Color.create("#ffffff", "#ffffff", "#ffffff", "#ffffff"),
				new HashSet<>(),
				null
		));
	}
}
