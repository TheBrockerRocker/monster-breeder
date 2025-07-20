package net.brocker.monster_breeder.entity.custom;

import net.brocker.monster_breeder.config.ModConfig;
import net.brocker.monster_breeder.entity.goal.ZombieCreeperCloseAttackGoal;
import net.brocker.monster_breeder.entity.goal.ZombieCreeperFarAttackGoal;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ZombieCreeperEntity extends HostileEntity {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	private final ZombieCreeperCloseAttackGoal closeAttackGoal = new ZombieCreeperCloseAttackGoal(this, 1.0, false);
	private final ZombieCreeperFarAttackGoal farAttackGoal = new ZombieCreeperFarAttackGoal(this, 1.0, 20, 15.0F);

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
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
		this.goalSelector.add(7, new LookAroundGoal(this));

		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, false));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		int count = random.nextInt(3) + 1;
		for (int i = 0; i < 5; i++) {
			if (random.nextFloat() < 0.65F) {
				++count;
			}
		}
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ModItems.MINI_TNT, count));
	}

	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		Random random = world.getRandom();
		EntityData newEntityData = super.initialize(world, difficulty, spawnReason, entityData);

		initEquipment(random, difficulty);
		return newEntityData;
	}

	private boolean isTargetFar() {
		LivingEntity entity = this.getTarget();
		if (entity == null) return false;

		return entity.squaredDistanceTo(this) > 64;
	}

	public void updateAttackType() {
		if (this.getWorld() != null && !this.getWorld().isClient) {
			ItemStack itemStack = this.getStackInHand(Hand.MAIN_HAND);
			if (itemStack.isOf(ModItems.MINI_TNT) && isTargetFar()) {
				int interval = this.getWorld().getDifficulty() == Difficulty.HARD
						? this.getHardAttackInterval()
						: this.getRegularAttackInterval();

				this.farAttackGoal.setAttackInterval(interval);
				this.goalSelector.remove(this.closeAttackGoal);
				if (!hasGoal(this.farAttackGoal)) this.goalSelector.add(4, this.farAttackGoal);
			} else {
				this.goalSelector.remove(this.farAttackGoal);
				if (!hasGoal(this.closeAttackGoal)) this.goalSelector.add(4, this.closeAttackGoal);
			}
		}
	}

	protected boolean hasGoal(Goal goal) {
		for (PrioritizedGoal prioritizedGoal: this.goalSelector.getGoals()) {
			if (prioritizedGoal.getGoal() == goal) {
				return true;
			}
		}
		return false;
	}

	protected int getHardAttackInterval() {
		return 20;
	}

	protected int getRegularAttackInterval() {
		return 40;
	}

	public void shootAt(LivingEntity target) {
		ItemStack itemStack = this.getStackInHand(Hand.MAIN_HAND);
		if (!itemStack.isOf(ModItems.MINI_TNT)) return;
		itemStack.decrement(1);

		MiniTntEntity miniTntEntity = new MiniTntEntity(this.getWorld(), this);
		double d = target.getX() - this.getX();
		double e = target.getBodyY(0.3333333333333333) - miniTntEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);
		miniTntEntity.setVelocity(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
		this.getWorld().spawnEntity(miniTntEntity);
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
		} else {
			this.updateAttackType();
		}
	}

	@Override
	public void onDeath(DamageSource damageSource) {
		super.onDeath(damageSource);

		ItemStack itemStack = this.getStackInHand(Hand.MAIN_HAND);
		if (!itemStack.isOf(ModItems.MINI_TNT)
				|| !ModConfig.shouldZombieCreepersDropTntOnDeath
				|| random.nextFloat() > (float) ModConfig.zombieCreeperDropChance / 100
		) return;

		for (int i = 0; i < itemStack.getCount(); i++) {
			MiniTntEntity miniTntEntity = new MiniTntEntity(this.getWorld(), this);
			miniTntEntity.setVelocity(0, 0.1, 0, 0.5f, 5);
			this.getWorld().spawnEntity(miniTntEntity);
		}
	}
}
