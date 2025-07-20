package net.brocker.monster_breeder.entity;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.entity.custom.EnderCreeperEntity;
import net.brocker.monster_breeder.entity.custom.MiniTntEntity;
import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
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

	public static final EntityType<ZombieCreeperEntity> ZOMBIE_CREEPER = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "zombie_creeper"),
			EntityType.Builder.create(ZombieCreeperEntity::new, SpawnGroup.MONSTER)
					.maxTrackingRange(8).dimensions(0.6f, 2.25f).eyeHeight(1.8125f).build());

	public static final EntityType<MiniTntEntity> MINI_TNT = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MonsterBreeder.MOD_ID, "mini_tnt"),
			EntityType.Builder.create(MiniTntEntity::create, SpawnGroup.MISC)
					.maxTrackingRange(4).trackingTickInterval(10).dimensions(0.25F, 0.25F).build());


	@SuppressWarnings("EmptyMethod")
	public static void registerModEntities() {}
}