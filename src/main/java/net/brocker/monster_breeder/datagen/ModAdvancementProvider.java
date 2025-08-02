package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.advancement.ExtractedBloodCriterion;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.dna.VanillaDna;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.item.custom.SyringeItem;
import net.brocker.monster_breeder.tag.ModEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

// TODO: Add more advancements for existing features
class ModAdvancementProvider extends FabricAdvancementProvider {
	ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
		AdvancementEntry core = Advancement.Builder.create()
				.display(
						ModBlocks.DNA_ALTAR,
						Text.translatable("advancements.monster_breeder.core"),
						Text.translatable("advancements.monster_breeder.core.description"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.TASK,
						false,
						false,
						true
				)
				.criterion("play_the_game", TickCriterion.Conditions.createTick())
				.build(consumer, MonsterBreeder.MOD_ID + ":core");
		AdvancementEntry extractBlood = Advancement.Builder.create()
				.parent(core)
				.display(
						ModItems.SYRINGE,
						Text.translatable("advancements.monster_breeder.extract_blood"),
						Text.translatable("advancements.monster_breeder.extract_blood.description"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.TASK,
						true,
						true,
						false
				)
				.criterion("extracted_blood", ExtractedBloodCriterion.Conditions.create(null))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood");
		AdvancementEntry extractPureBlood = Advancement.Builder.create()
				.parent(extractBlood)
				.display(
						SyringeItem.createItemStack(ModDna.UNKNOWN, 100),
						Text.translatable("advancements.monster_breeder.extract_pure_blood"),
						Text.translatable("advancements.monster_breeder.extract_pure_blood.description"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.GOAL,
						true,
						true,
						false
				)
				.criterion("got_used_syringe_with_max_purity", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_pure_blood");
		AdvancementEntry extractBloodFromZoglin = Advancement.Builder.create()
				.parent(extractBlood)
				.display(
						DnaSampleItem.createItemStack(VanillaDna.ZOGLIN),
						Text.translatable("advancements.monster_breeder.extract_blood_from_zoglin"),
						Text.translatable("advancements.monster_breeder.extract_blood_from_zoglin.description"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.CHALLENGE,
						true,
						true,
						false
				)
				.criterion("extracted_zoglin_blood", ExtractedBloodCriterion.Conditions.create(EntityTypePredicate.create(EntityType.ZOGLIN)))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood_from_zoglin");
		AdvancementEntry extractBloodFromBoss = Advancement.Builder.create()
				.parent(extractBlood)
				.display(
						DnaSampleItem.createItemStack(VanillaDna.ENDER_DRAGON),
						Text.translatable("advancements.monster_breeder.extract_blood_from_boss"),
						Text.translatable("advancements.monster_breeder.extract_blood_from_boss.description"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.CHALLENGE,
						true,
						true,
						false
				)
				.criterion("extracted_boss_blood", ExtractedBloodCriterion.Conditions.create(EntityTypePredicate.create(ModEntityTypeTags.BOSSES)))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood_from_boss");
	}
}
