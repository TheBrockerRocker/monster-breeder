package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.datagen.BioReactionRecipeJsonBuilder;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.dna.VanillaDna;
import net.brocker.monster_breeder.item.ModItems;
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
				.offerTo(exporter, MonsterBreeder.identifier("syringe"));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DNA_EXTRACTOR)
				.pattern("GAG")
				.pattern("DSD")
				.pattern("GAG")
				.input('S', ModItems.SYRINGE)
				.input('G', Items.GOLD_INGOT)
				.input('D', Items.DIAMOND_BLOCK)
				.input('A', Items.AMETHYST_SHARD)
				.criterion(hasItem(ModItems.SYRINGE), conditionsFromItem(ModItems.SYRINGE))
				.offerTo(exporter, MonsterBreeder.identifier("dna_extractor"));

		BioReactionRecipeJsonBuilder.create(VanillaDna.MULE, VanillaDna.HORSE, VanillaDna.DONKEY)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, Identifier.ofVanilla("mule_dna"));

		BioReactionRecipeJsonBuilder.create(VanillaDna.SKELETON_HORSE, VanillaDna.HORSE, VanillaDna.SKELETON)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, Identifier.ofVanilla("skeleton_horse_dna"));

		BioReactionRecipeJsonBuilder.create(ModDna.ENDER_CREEPER, VanillaDna.CREEPER, VanillaDna.ENDERMAN)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("ender_creeper_dna"));

		BioReactionRecipeJsonBuilder.create(ModDna.ZOMBIE_CREEPER, VanillaDna.CREEPER, VanillaDna.ZOMBIE)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zombie_creeper_dna"));
	}
}
