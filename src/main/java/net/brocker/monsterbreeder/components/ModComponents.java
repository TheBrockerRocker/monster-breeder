package net.brocker.monsterbreeder.components;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
	public static final ComponentType<Identifier> DNA_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "dna"),
			ComponentType.<Identifier>builder().codec(Identifier.CODEC).build()
	);

	public static void registerModComponets() {
		MonsterBreeder.LOGGER.info("Registering ModComponents for " + MonsterBreeder.MOD_ID);
	}
}
