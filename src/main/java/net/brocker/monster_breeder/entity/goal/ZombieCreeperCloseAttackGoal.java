package net.brocker.monster_breeder.entity.goal;

import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class ZombieCreeperCloseAttackGoal extends MeleeAttackGoal {
	private final ZombieCreeperEntity zombieCreeper;
	private int ticks;

	public ZombieCreeperCloseAttackGoal(ZombieCreeperEntity zombieCreeper, double speed, boolean pauseWhenMobIdle) {
		super(zombieCreeper, speed, pauseWhenMobIdle);
		this.zombieCreeper = zombieCreeper;
	}

	public void start() {
		super.start();
		this.ticks = 0;
	}

	public void stop() {
		super.stop();
		this.zombieCreeper.setAttacking(false);
	}

	public void tick() {
		super.tick();
		++this.ticks;
		this.zombieCreeper.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
	}
}
