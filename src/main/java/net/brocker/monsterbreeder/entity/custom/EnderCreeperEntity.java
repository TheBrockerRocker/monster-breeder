package net.brocker.monsterbreeder.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class EnderCreeperEntity extends CreeperEntity {
	public EnderCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}
}