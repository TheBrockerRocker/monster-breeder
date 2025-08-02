package net.brocker.monster_breeder.entity.custom;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.advancement.ModCriteria;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.config.ModConfig;
import net.brocker.monster_breeder.entity.ModEntities;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.util.AdvancementUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SyringeArrowEntity extends PersistentProjectileEntity {
	public static SyringeArrowEntity create(EntityType<? extends SyringeArrowEntity> entityType, World world) { return new SyringeArrowEntity(entityType, world); }

	public SyringeArrowEntity(EntityType<? extends SyringeArrowEntity> entityType, World world) {
		super(entityType, world);
		setDamage(1);
	}

	public SyringeArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(ModEntities.SYRINGE_ARROW, owner, world, stack, shotFrom);
		setDamage(1);
	}

	public SyringeArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(ModEntities.SYRINGE_ARROW, x, y, z, world, stack, shotFrom);
		setDamage(1);
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		Entity entity = entityHitResult.getEntity() instanceof EnderDragonPart part ? part.owner : entityHitResult.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			entityHitResult.getEntity().damage(this.getDamageSources().arrow(this, getOwner() != null ? getOwner() : this), 1);
			onHit(livingEntity);
		}
		else super.onEntityHit(entityHitResult);
	}

	protected void onHit(LivingEntity livingEntity) {
		ItemStack stack = getItemStack();
		boolean isUsed = stack.isOf(ModItems.USED_SYRINGE_ARROW);

		Optional<PlayerEntity> player = Optional.ofNullable(
				getOwner() instanceof PlayerEntity
						? (PlayerEntity) getOwner()
						: null
		);

		if (livingEntity instanceof MobEntity mobEntity) {
			EntityType<?> mobType = mobEntity.getType();
			String mobName = mobType.getName().getString();

			if (DnaUtil.getDnaIdentifier(mobType) == null) {
				player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.unsupported_mob").formatted(Formatting.RED), false));
				dropAsItem();
				return;
			} else if (isUsed && DnaUtil.getBloodType(stack) != mobType) {
				player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.cross_contamination").formatted(Formatting.RED), false));
				dropAsItem();
				return;
			}

			List<String> extractedFrom = new ArrayList<>(stack.getOrDefault(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, new ArrayList<>()));
			String mobId = mobEntity.getUuidAsString();
			if (extractedFrom.contains(mobId)) {
				player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.already_extracted").formatted(Formatting.RED), false));
				dropAsItem();
				return;
			}
			extractedFrom.add(mobId);

			int purity = DnaUtil.getPurity(stack);
			if (purity >= 100) {
				player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.max_purity").formatted(Formatting.RED), false));
				dropAsItem();
				return;
			}
			purity += 10 * ModConfig.bloodPurityModifier;

			stack.set(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, extractedFrom);
			DnaUtil.setBloodType(stack, mobType);
			DnaUtil.setPurity(stack, purity);

			setStack(stack.withItem(ModItems.USED_SYRINGE_ARROW));
			player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.extracted_from", mobName), false));

			if (player.isPresent() && player.get() instanceof ServerPlayerEntity serverPlayer) {
				ModCriteria.EXTRACTED_BLOOD.trigger(serverPlayer, mobType);
				if (purity >= 100) {
					AdvancementUtil.grant(serverPlayer, AdvancementUtil.get(serverPlayer.server, MonsterBreeder.identifier("extract_pure_blood")));
				}
			}
		} else {
			player.ifPresent(p -> p.sendMessage(Text.translatable("monster_breeder.mobs_only").formatted(Formatting.RED), false));
		}
		dropAsItem();
	}

	protected void dropAsItem() {
		this.dropStack(this.asItemStack(), 0.1F);
		this.setStack(ItemStack.EMPTY);
		this.discard();
	}

	protected ItemStack getDefaultItemStack() {
		return new ItemStack(ModItems.SYRINGE_ARROW);
	}
}
