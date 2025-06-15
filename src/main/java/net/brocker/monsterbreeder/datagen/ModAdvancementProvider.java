package net.brocker.monsterbreeder.datagen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
	public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
		AdvancementEntry extractBlood = Advancement.Builder.create()
				.display(
						ModItems.USED_SYRINGE, // The display icon
						Text.literal("Extract Blood"), // The title
						Text.literal("Use a syringe to extract blood from any mob"), // The description
						Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
						AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
						true, // Show the toast when completing it
						true, // Announce it to chat
						true // Hide it in the advancement tab until it's achieved
				)
				.criterion("got_used_syringe", InventoryChangedCriterion.Conditions.items(ModItems.USED_SYRINGE))
				.build(consumer, MonsterBreeder.MOD_ID + ":extract_blood");
		consumer.accept(extractBlood);
	}
}
