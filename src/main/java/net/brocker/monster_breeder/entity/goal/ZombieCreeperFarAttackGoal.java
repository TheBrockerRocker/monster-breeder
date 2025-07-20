package net.brocker.monster_breeder.entity.goal;

import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;

public class ZombieCreeperFarAttackGoal extends Goal {
	private final ZombieCreeperEntity zombieCreeper;
	private final double speed;
	private int attackInterval;
	private final float squaredRange;
	private int cooldown = -1;
	private int targetSeeingTicker;
	private boolean movingToLeft;
	private boolean backward;
	private int combatTicks = -1;

	public ZombieCreeperFarAttackGoal(ZombieCreeperEntity zombieCreeper, double speed, int attackInterval, float range) {
		super();
		this.zombieCreeper = zombieCreeper;
		this.speed = speed;
		this.attackInterval = attackInterval;
		this.squaredRange = range * range;
	}

	public void setAttackInterval(int attackInterval) {
		this.attackInterval = attackInterval;
	}

	public boolean hasMiniTnt() {
		return this.zombieCreeper.getEquippedStack(EquipmentSlot.MAINHAND).isOf(ModItems.MINI_TNT);
	}

	@Override
	public boolean canStart() {
		return this.zombieCreeper.getTarget() != null && this.hasMiniTnt();
	}

	@Override
	public boolean shouldContinue() {
		return (this.canStart() || !this.zombieCreeper.getNavigation().isIdle()) && this.hasMiniTnt();
	}

	public void start() {
		super.start();
		this.zombieCreeper.setAttacking(true);
	}

	public void stop() {
		super.stop();
		this.zombieCreeper.setAttacking(false);
		this.targetSeeingTicker = 0;
		this.cooldown = -1;
		this.zombieCreeper.clearActiveItem();
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	public void tick() {
		LivingEntity livingEntity = this.zombieCreeper.getTarget();
		if (livingEntity != null) {
			double d = this.zombieCreeper.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
			boolean bl = this.zombieCreeper.getVisibilityCache().canSee(livingEntity);
			boolean bl2 = this.targetSeeingTicker > 0;
			if (bl != bl2) {
				this.targetSeeingTicker = 0;
			}

			if (bl) {
				++this.targetSeeingTicker;
			} else {
				--this.targetSeeingTicker;
			}

			if (!(d > (double)this.squaredRange) && this.targetSeeingTicker >= 20) {
				this.zombieCreeper.getNavigation().stop();
				++this.combatTicks;
			} else {
				this.zombieCreeper.getNavigation().startMovingTo(livingEntity, this.speed);
				this.combatTicks = -1;
			}

			if (this.combatTicks >= 20) {
				if ((double)this.zombieCreeper.getRandom().nextFloat() < 0.3) {
					this.movingToLeft = !this.movingToLeft;
				}

				if ((double)this.zombieCreeper.getRandom().nextFloat() < 0.3) {
					this.backward = !this.backward;
				}

				this.combatTicks = 0;
			}

			if (this.combatTicks > -1) {
				if (d > (double)(this.squaredRange * 0.75F)) {
					this.backward = false;
				} else if (d < (double)(this.squaredRange * 0.25F)) {
					this.backward = true;
				}

				this.zombieCreeper.getMoveControl().strafeTo(this.backward ? -0.5F : 0.5F, this.movingToLeft ? 0.5F : -0.5F);
				Entity var7 = this.zombieCreeper.getControllingVehicle();
				if (var7 instanceof MobEntity mobEntity) {
					mobEntity.lookAtEntity(livingEntity, 30.0F, 30.0F);
				}

				this.zombieCreeper.lookAtEntity(livingEntity, 30.0F, 30.0F);
			} else {
				this.zombieCreeper.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
			}

			if (this.zombieCreeper.isUsingItem()) {
				if (!bl && this.targetSeeingTicker < -60) {
					this.zombieCreeper.clearActiveItem();
				} else if (bl) {
					int i = this.zombieCreeper.getItemUseTime();
					if (i >= 20) {
						this.zombieCreeper.clearActiveItem();
						this.zombieCreeper.shootAt(livingEntity);
						this.cooldown = this.attackInterval;
					}
				}
			} else if (--this.cooldown <= 0 && this.targetSeeingTicker >= -60) {
				this.zombieCreeper.setCurrentHand(Hand.MAIN_HAND);
			}
		}
	}
}
