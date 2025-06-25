package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.util.DnaBuilder;
import net.brocker.monsterbreeder.entity.ModEntities;
import net.minecraft.util.Identifier;

public class ModDna {
	public static Identifier UNKNOWN = MonsterBreeder.identifier("unknown");
	public static Identifier ENDER_CREEPER = MonsterBreeder.identifier("ender_creeper");

	public static void registerModDna() {
		DnaBuilder.create(ModEntities.ENDER_CREEPER.getTranslationKey())
				.addSourceMobAsSummonResult(ModEntities.ENDER_CREEPER)
				.buildAndRegister(ENDER_CREEPER);
	}
}
