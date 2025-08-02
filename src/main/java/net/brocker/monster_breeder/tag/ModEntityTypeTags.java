package net.brocker.monster_breeder.tag;

import net.brocker.monster_breeder.MonsterBreeder;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ModEntityTypeTags {
	TagKey<EntityType<?>> BOSSES = of("bosses");

	private static TagKey<EntityType<?>> of(String id) {
		return TagKey.of(RegistryKeys.ENTITY_TYPE, MonsterBreeder.identifier(id));
	}
}
