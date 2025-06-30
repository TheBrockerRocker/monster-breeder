package net.brocker.monster_breeder.dna;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.util.DnaBuilder;
import net.brocker.monster_breeder.entity.ModEntities;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModDna {
	public static final Identifier UNKNOWN = MonsterBreeder.identifier("unknown");
	public static final Identifier ENDER_CREEPER = MonsterBreeder.identifier("ender_creeper");
	public static final Identifier ZOMBIE_CREEPER = MonsterBreeder.identifier("zombie_creeper");

	public static void registerModDna() {
		DnaBuilder.create(ModEntities.ENDER_CREEPER.getTranslationKey())
				.setColor("#000000","#27006c","#3c2369","#670881")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(ModEntities.ENDER_CREEPER)
				.buildAndRegister(ENDER_CREEPER);

		DnaBuilder.create(ModEntities.ZOMBIE_CREEPER.getTranslationKey())
				.setColor("#06891b","#43da1b","#2493bc","#439f2b")
				.addSourceMobAsSummonResult(ModEntities.ZOMBIE_CREEPER)
				.buildAndRegister(ZOMBIE_CREEPER);
	}
}
