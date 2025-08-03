package net.brocker.monster_breeder.advancement;

import net.brocker.monster_breeder.MonsterBreeder;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModCriteria {
	public static final ExtractedBloodCriterion EXTRACTED_BLOOD = register("extracted_blood", new ExtractedBloodCriterion());
	public static final SummonedEntityCriterion SUMMONED_ENTITY = register("summoned_entity", new SummonedEntityCriterion());

	public static <T extends Criterion<?>> T register(String id, T criterion) {
		return Registry.register(Registries.CRITERION, MonsterBreeder.identifier(id), criterion);
	}

	@SuppressWarnings("EmptyMethod")
	public static void registerModCriteria() {}
}
