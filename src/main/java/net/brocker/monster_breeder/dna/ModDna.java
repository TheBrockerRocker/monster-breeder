package net.brocker.monster_breeder.dna;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.util.DnaBuilder;
import net.brocker.monster_breeder.entity.ModEntities;
import net.minecraft.util.Identifier;

public class ModDna {
	public static final Identifier UNKNOWN = MonsterBreeder.identifier("unknown");
	public static final Identifier ENDER_CREEPER = MonsterBreeder.identifier("ender_creeper");

	public static void registerModDna() {
		DnaBuilder.create(ModEntities.ENDER_CREEPER.getTranslationKey())
				.addSourceMobAsSummonResult(ModEntities.ENDER_CREEPER)
				.buildAndRegister(ENDER_CREEPER);
	}
}
