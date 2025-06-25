package net.brocker.monsterbreeder.api.registry;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.dna.ModDna;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;

public class MonsterBreederRegistries {
	public static final RegistryKey<Registry<Dna>> DNA_REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.of(MonsterBreeder.MOD_ID, "dna"));
	public static final SimpleDefaultedRegistry<Dna> DNA = FabricRegistryBuilder
			.createDefaulted(DNA_REGISTRY_KEY, ModDna.UNKNOWN)
			.attribute(RegistryAttribute.SYNCED)
			.buildAndRegister();

	public static void initialize() {
		Registry.register(DNA, ModDna.UNKNOWN, new Dna(
				"monsterbreeder.unknown",
				Rarity.COMMON,
				Dna.Color.create("#ffffff", "#ffffff", "#ffffff", "#ffffff"),
				new ArrayList<>(),
				null
		));
	}
}
