package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaBuilder;
import net.brocker.monsterbreeder.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class ModDna {
	public static Identifier ENDER_CREEPER = Identifier.of(MonsterBreeder.MOD_ID, "ender_creeper");

	public static void registerModDna() {
		MonsterBreeder.LOGGER.info("Registering ModDna for " + MonsterBreeder.MOD_ID);

		DnaRegistry registry = DnaRegistry.INSTANCE;
		registry.register(ENDER_CREEPER, DnaBuilder
				.create(ModEntities.ENDER_CREEPER.getTranslationKey())
				.addSourceMobAsSummonResult(ModEntities.ENDER_CREEPER)
				.build()
		);
	}
}
