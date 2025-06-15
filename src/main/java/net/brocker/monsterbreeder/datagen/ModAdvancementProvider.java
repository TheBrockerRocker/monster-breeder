package net.brocker.monsterbreeder.datagen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

// TODO: Add l10n
public class ModAdvancementProvider extends FabricAdvancementProvider {
	public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
		AdvancementEntry core = Advancement.Builder.create()
				.display(
						ModBlocks.DNA_ALTAR,
						Text.translatable("itemGroup.monsterbreeder"),
						Text.literal("Start breeding monsters!"),
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
						ModItems.USED_SYRINGE,
						Text.literal("Extract Blood"),
						Text.literal("Use a syringe to extract blood from any mob"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.TASK,
						true,
						true,
						false
				)
				.criterion("got_used_syringe", InventoryChangedCriterion.Conditions.items(ModItems.USED_SYRINGE))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood");
		AdvancementEntry extractPureBlood = Advancement.Builder.create()
				.parent(extractBlood)
				.display(
						DnaSampleItem.createItemStack(ModDna.UNKNOWN),
						Text.literal("Pure Blood"),
						Text.literal("Use a syringe to extract 100% pure blood from any mob"),
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
						DnaSampleItem.createItemStack(ModDna.ZOGLIN),
						Text.literal("Zoglin Blood"),
						Text.literal("Use a syringe to extract blood from a Zoglin"),
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementFrame.CHALLENGE,
						true,
						true,
						false
				)
				.criterion("got_used_syringe_with_zoglin_blood", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood_from_zoglin");
	}
}
