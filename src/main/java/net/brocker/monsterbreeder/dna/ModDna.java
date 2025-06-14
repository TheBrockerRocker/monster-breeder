package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class ModDna {
	public static Identifier UNKNOWN = Identifier.of(MonsterBreeder.MOD_ID, "unknown");
	public static Identifier ZOMBIE = Identifier.of(MonsterBreeder.MOD_ID, "zombie");
	public static Identifier SKELETON = Identifier.of(MonsterBreeder.MOD_ID, "skeleton");
	public static Identifier CREEPER = Identifier.of(MonsterBreeder.MOD_ID, "creeper");

	public static void registerModDna() {
		MonsterBreeder.LOGGER.info("Registering ModDna for " + MonsterBreeder.MOD_ID);

		DnaRegistry registry = DnaRegistry.INSTANCE;
		registry.register(UNKNOWN, new Dna("dna.monsterbreeder.unknown", false));
		registry.register(ZOMBIE, new Dna(EntityType.ZOMBIE.getTranslationKey(), false, EntityType.ZOMBIE));
		registry.register(SKELETON, new Dna(EntityType.SKELETON.getTranslationKey(), false, EntityType.SKELETON));
		registry.register(CREEPER, new Dna(EntityType.CREEPER.getTranslationKey(), false, EntityType.CREEPER));
	}
}
