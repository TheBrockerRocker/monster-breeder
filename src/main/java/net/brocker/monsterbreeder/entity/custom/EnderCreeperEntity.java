package net.brocker.monsterbreeder.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Predicate;

public class EnderCreeperEntity extends CreeperEntity implements Angerable {
	private static final Identifier ATTACKING_SPEED_MODIFIER_ID = Identifier.ofVanilla("attacking");
	private static final EntityAttributeModifier ATTACKING_SPEED_BOOST = new EntityAttributeModifier(ATTACKING_SPEED_MODIFIER_ID, 0.10000000596046448, EntityAttributeModifier.Operation.ADD_VALUE);
	private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);;
	private int ageWhenTargetSet = 0;
	private int angerTime = 0;
	@Nullable
	private UUID angryAt;

	public EnderCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
		this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.targetSelector.add(1, new TeleportTowardsPlayerGoal(this, this::shouldAngerAt));
	}

	@Override
	public void tickMovement() {
		if (this.getWorld().isClient) {
			for(int i = 0; i < 2; ++i) {
				this.getWorld().addParticle(ParticleTypes.PORTAL, this.getParticleX(0.5), this.getRandomBodyY() - 0.25, this.getParticleZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
			}
		}

		if (!this.getWorld().isClient) {
			this.tickAngerLogic((ServerWorld)this.getWorld(), true);
		}

		super.tickMovement();
	}

	protected void mobTick() {
		if (this.getWorld().isDay() && this.age >= this.ageWhenTargetSet + 600) {
			float f = this.getBrightnessAtEyes();
			if (f > 0.5F && this.getWorld().isSkyVisible(this.getBlockPos()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
				this.setTarget(null);
				this.teleportRandomly();
			}
		}

		super.mobTick();
	}

	boolean isPlayerStaring(PlayerEntity player) {
		ItemStack itemStack = player.getInventory().armor.get(3);
		if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
			return false;
		} else {
			Vec3d vec3d = player.getRotationVec(1.0F).normalize();
			Vec3d vec3d2 = new Vec3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
			double d = vec3d2.length();
			vec3d2 = vec3d2.normalize();
			double e = vec3d.dotProduct(vec3d2);
			return e > 1.0 - 0.025 / d && player.canSee(this);
		}
	}

	@Override
	public boolean shouldAngerAt(LivingEntity entity) {
		return (entity.squaredDistanceTo(this) < 6 && getTarget() == entity) || Angerable.super.shouldAngerAt(entity);
	}

	protected boolean teleportRandomly() {
		if (!this.getWorld().isClient() && this.isAlive()) {
			double d = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
			double e = this.getY() + (double)(this.random.nextInt(64) - 32);
			double f = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
			return this.teleportTo(d, e, f);
		} else {
			return false;
		}
	}

	boolean teleportTo(Entity entity) {
		Vec3d vec3d = new Vec3d(this.getX() - entity.getX(), this.getBodyY(0.5) - entity.getEyeY(), this.getZ() - entity.getZ());
		vec3d = vec3d.normalize();
		double e = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.x * 16.0;
		double f = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3d.y * 16.0;
		double g = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.z * 16.0;
		return this.teleportTo(e, f, g);
	}

	private boolean teleportTo(double x, double y, double z) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		while(mutable.getY() > this.getWorld().getBottomY() && !this.getWorld().getBlockState(mutable).blocksMovement()) {
			mutable.move(Direction.DOWN);
		}

		BlockState blockState = this.getWorld().getBlockState(mutable);
		boolean bl = blockState.blocksMovement();
		boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
		if (bl && !bl2) {
			Vec3d vec3d = this.getPos();
			boolean bl3 = this.teleport(x, y, z, true);
			if (bl3) {
				this.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
				if (!this.isSilent()) {
					this.getWorld().playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
					this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
				}
			}

			return bl3;
		} else {
			return false;
		}
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		if (source.getAttacker() instanceof LivingEntity livingEntity && !source.isSourceCreativePlayer()) this.setTarget(livingEntity);
		if (source.isOf(DamageTypes.DROWN)) this.teleportRandomly();
		return super.damage(source, amount);
	}

	@Override
	public boolean hurtByWater() {
		return true;
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		super.setTarget(target);
		this.ageWhenTargetSet = target != null ? this.age : 0;

		EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
		if (target == null) entityAttributeInstance.removeModifier(ATTACKING_SPEED_MODIFIER_ID);
			else if (!entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) entityAttributeInstance.addTemporaryModifier(ATTACKING_SPEED_BOOST);
	}

	@Override
	public void chooseRandomAngerTime() {
		this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
	}

	@Override
	public void setAngerTime(int angerTime) {
		this.angerTime = angerTime;
	}

	@Override
	public int getAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setAngryAt(@Nullable UUID angryAt) {
		this.angryAt = angryAt;
	}

	@Override
	@Nullable
	public UUID getAngryAt() {
		return this.angryAt;
	}

	static class TeleportTowardsPlayerGoal extends ActiveTargetGoal<PlayerEntity> {
		private final EnderCreeperEntity enderCreeper;
		@Nullable
		private PlayerEntity targetPlayer;
		private int lookAtPlayerWarmup;
		private int ticksSinceUnseenTeleport;
		private final TargetPredicate staringPlayerPredicate;
		private final TargetPredicate validTargetPredicate = TargetPredicate.createAttackable().ignoreVisibility();
		private final Predicate<LivingEntity> angerPredicate;

		public TeleportTowardsPlayerGoal(EnderCreeperEntity enderCreeper, @Nullable Predicate<LivingEntity> targetPredicate) {
			super(enderCreeper, PlayerEntity.class, 10, false, false, targetPredicate);
			this.enderCreeper = enderCreeper;
			this.angerPredicate = (playerEntity) -> (enderCreeper.isPlayerStaring((PlayerEntity)playerEntity) || enderCreeper.shouldAngerAt(playerEntity)) && !enderCreeper.hasPassengerDeep(playerEntity);
			this.staringPlayerPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange() * 2).setPredicate(this.angerPredicate);
		}

		public boolean canStart() {
			this.targetPlayer = this.enderCreeper.getWorld().getClosestPlayer(this.staringPlayerPredicate, this.enderCreeper);
			return this.targetPlayer != null;
		}

		public void start() {
			this.lookAtPlayerWarmup = this.getTickCount(5);
			this.ticksSinceUnseenTeleport = 0;
			this.enderCreeper.setTarget(this.targetPlayer);
		}

		public void stop() {
			this.targetPlayer = null;
			this.enderCreeper.setTarget(null);
			this.enderCreeper.teleportRandomly();
			super.stop();
		}

		public boolean shouldContinue() {
			if (this.targetPlayer != null) {
				if (!this.angerPredicate.test(this.targetPlayer)) {
					return false;
				} else {
					this.enderCreeper.lookAtEntity(this.targetPlayer, 10.0F, 10.0F);
					return true;
				}
			} else {
				if (this.targetEntity != null) {
					if (this.enderCreeper.hasPassengerDeep(this.targetEntity)) {
						return false;
					}

					if (this.validTargetPredicate.test(this.enderCreeper, this.targetEntity)) {
						return true;
					}
				}

				return super.shouldContinue();
			}
		}

		public void tick() {
			if (this.enderCreeper.getTarget() == null) {
				super.setTargetEntity(null);
			}

			if (this.targetPlayer != null) {
				if (--this.lookAtPlayerWarmup <= 0) {
					this.targetEntity = this.targetPlayer;
					this.targetPlayer = null;
					super.start();
				}
			} else {
				if (this.targetEntity != null && !this.enderCreeper.hasVehicle()) {
					if (this.enderCreeper.isPlayerStaring((PlayerEntity)this.targetEntity)) {
						if (this.targetEntity.squaredDistanceTo(this.enderCreeper) < 16.0) {
							this.enderCreeper.teleportRandomly();
						}

						this.ticksSinceUnseenTeleport = 0;
					} else if (this.targetEntity.squaredDistanceTo(this.enderCreeper) > 128.0 && this.ticksSinceUnseenTeleport++ >= this.getTickCount(30) && this.enderCreeper.teleportTo(this.targetEntity)) {
						this.ticksSinceUnseenTeleport = 0;
					}
				}

				super.tick();
			}

		}
	}
}