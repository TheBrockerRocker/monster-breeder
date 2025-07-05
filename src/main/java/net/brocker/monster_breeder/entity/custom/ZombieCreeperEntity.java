package net.brocker.monster_breeder.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ZombieCreeperEntity extends HostileEntity {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	public ZombieCreeperEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createZombieCreeperAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513)
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 40);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));

		this.goalSelector.add(4, new WanderAroundFarGoal(this, 1));
		this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
		this.goalSelector.add(6, new LookAroundGoal(this));

		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	private void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 40;
			this.idleAnimationState.start(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getWorld().isClient) {
			// this.setupAnimationStates();
		}
	}
}
