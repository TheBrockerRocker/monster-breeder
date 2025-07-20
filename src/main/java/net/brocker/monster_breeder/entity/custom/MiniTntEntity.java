package net.brocker.monster_breeder.entity.custom;

import net.brocker.monster_breeder.entity.ModEntities;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MiniTntEntity extends ThrownItemEntity {
	public static MiniTntEntity create(EntityType<? extends MiniTntEntity> entityType, World world) { return new MiniTntEntity(entityType, world); }

	public MiniTntEntity(EntityType<? extends MiniTntEntity> entityType, World world) {
		super(entityType, world);
	}
	public MiniTntEntity(World world, LivingEntity owner) {
		super(ModEntities.MINI_TNT, owner, world);
	}
	public MiniTntEntity(World world, double x, double y, double z) {
		super(ModEntities.MINI_TNT, x, y, z, world);
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.MINI_TNT;
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		this.getWorld().createExplosion(
				this,
				Explosion.createDamageSource(this.getWorld(), this),
				null,
				this.getX(),
				this.getY(),
				this.getZ(),
				2.0F,
				false,
				World.ExplosionSourceType.TNT
		);
		this.discard();
	}
}
