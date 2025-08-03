package net.brocker.monster_breeder.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SummonedEntityCriterion extends AbstractCriterion<SummonedEntityCriterion.Conditions> {
	public SummonedEntityCriterion() {
	}

	public Codec<Conditions> getConditionsCodec() {
		return Conditions.CODEC;
	}

	public void trigger(ServerPlayerEntity player, EntityType<?> entityType) {
		this.trigger(player, (conditions) -> conditions.test(entityType));
	}

	public record Conditions(Optional<LootContextPredicate> player, Optional<EntityTypePredicate> entityType) implements AbstractCriterion.Conditions {
		public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(
				(instance) -> instance
						.group(
								EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC
										.optionalFieldOf("player")
										.forGetter(Conditions::player),
								EntityTypePredicate.CODEC
										.optionalFieldOf("entityType")
										.forGetter(Conditions::entityType)
						)
						.apply(instance, Conditions::new)
		);

		public static AdvancementCriterion<Conditions> create(@Nullable EntityTypePredicate entityType) {
			return ModCriteria.SUMMONED_ENTITY.create(new Conditions(Optional.empty(), Optional.ofNullable(entityType)));
		}

		public boolean test(EntityType<?> entityType) {
			return this.entityType.isEmpty() || this.entityType.get().matches(entityType);
		}

		public Optional<LootContextPredicate> player() {
			return this.player;
		}

		public Optional<EntityTypePredicate> entityType() {
			return this.entityType;
		}
	}
}
