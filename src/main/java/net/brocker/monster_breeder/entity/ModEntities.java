package net.brocker.monster_breeder.entity;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.entity.custom.EnderCreeperEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
	public static final EntityType<EnderCreeperEntity> ENDER_CREEPER = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "ender_creeper"),
			EntityType.Builder.create(EnderCreeperEntity::new, SpawnGroup.MONSTER)
					.maxTrackingRange(8).build());


	public static void registerModEntities() {}
}