package net.brocker.monsterbreeder.datagen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.datagen.BioReactionRecipeJsonBuilder;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SYRINGE)
				.pattern("  I")
				.pattern(" G ")
				.pattern("N  ")
				.input('I', Items.IRON_INGOT)
				.input('G', Items.GLASS)
				.input('N', Items.IRON_NUGGET)
				.criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
				.offerTo(exporter, Identifier.of(MonsterBreeder.MOD_ID, "syringe"));

		BioReactionRecipeJsonBuilder.create(ModDna.MULE, ModDna.HORSE, ModDna.DONKEY)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, Identifier.of(MonsterBreeder.MOD_ID, "mule_dna"));

		BioReactionRecipeJsonBuilder.create(ModDna.SKELETON_HORSE, ModDna.HORSE, ModDna.SKELETON)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, Identifier.of(MonsterBreeder.MOD_ID, "skeleton_horse_dna"));
	}
}
