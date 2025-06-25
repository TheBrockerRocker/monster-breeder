package net.brocker.monsterbreeder.components;

import com.mojang.serialization.Codec;
import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModComponents {
	public static final ComponentType<Identifier> DNA_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "dna"),
			ComponentType.<Identifier>builder().codec(Identifier.CODEC).build()
	);

	public static final ComponentType<Integer> PURITY_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "purity"),
			ComponentType.<Integer>builder().codec(Codec.INT).build()
	);

	public static final ComponentType<List<String>> BLOOD_EXTRACTED_FROM_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "blood_extracted_from"),
			ComponentType.<List<String>>builder().codec(Codec.STRING.listOf()).build()
	);

	public static void registerModComponets() {}
}
