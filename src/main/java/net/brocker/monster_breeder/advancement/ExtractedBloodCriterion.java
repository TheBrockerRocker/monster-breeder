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

public class ExtractedBloodCriterion extends AbstractCriterion<ExtractedBloodCriterion.Conditions> {
	public ExtractedBloodCriterion() {
	}

	public Codec<Conditions> getConditionsCodec() {
		return Conditions.CODEC;
	}

	public void trigger(ServerPlayerEntity player, EntityType<?> bloodType) {
		this.trigger(player, (conditions) -> conditions.test(bloodType));
	}

	public record Conditions(Optional<LootContextPredicate> player, Optional<EntityTypePredicate> bloodType) implements AbstractCriterion.Conditions {
		public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(
				(instance) -> instance
						.group(
								EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC
										.optionalFieldOf("player")
										.forGetter(Conditions::player),
								EntityTypePredicate.CODEC
										.optionalFieldOf("bloodType")
										.forGetter(Conditions::bloodType)
						)
						.apply(instance, Conditions::new)
		);

		public static AdvancementCriterion<Conditions> create(@Nullable EntityTypePredicate bloodType) {
			return ModCriteria.EXTRACTED_BLOOD.create(new Conditions(Optional.empty(), Optional.ofNullable(bloodType)));
		}

		public boolean test(EntityType<?> bloodType) {
			return this.bloodType.isEmpty() || this.bloodType.get().matches(bloodType);
		}

		public Optional<LootContextPredicate> player() {
			return this.player;
		}

		public Optional<EntityTypePredicate> bloodType() {
			return this.bloodType;
		}
	}
}
