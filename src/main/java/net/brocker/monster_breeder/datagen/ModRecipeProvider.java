package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.datagen.BioReactionRecipeJsonBuilder;
import net.brocker.monster_breeder.api.datagen.GrowthRecipeJsonBuilder;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.dna.VanillaDna;
import net.brocker.monster_breeder.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

class ModRecipeProvider extends FabricRecipeProvider {
	ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.MINI_TNT)
				.pattern("#X")
				.pattern("X#")
				.input('#', Ingredient.ofItems(Items.SAND, Items.RED_SAND))
				.input('X', Items.GUNPOWDER)
				.criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
				.offerTo(exporter, MonsterBreeder.identifier("mini_tnt"));

		ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.MINI_TNT)
				.pattern("X#")
				.pattern("#X")
				.input('#', Ingredient.ofItems(Items.SAND, Items.RED_SAND))
				.input('X', Items.GUNPOWDER)
				.criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
				.offerTo(exporter, MonsterBreeder.identifier("mini_tnt_2"));

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GROWTH_CHAMBER)
				.pattern("IBI")
				.pattern("BEB")
				.pattern("IBI")
				.input('I', Items.IRON_BLOCK)
				.input('B', Items.IRON_BARS)
				.input('E', Items.EMERALD_BLOCK)
				.criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
				.offerTo(exporter, MonsterBreeder.identifier("growth_chamber"));

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

		BioReactionRecipeJsonBuilder.create(VanillaDna.ZOGLIN, VanillaDna.HOGLIN, VanillaDna.ZOMBIE)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zoglin_dna"));

		BioReactionRecipeJsonBuilder.create(VanillaDna.ZOMBIE_VILLAGER, VanillaDna.VILLAGER, VanillaDna.ZOMBIE)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zombie_villager_dna"));

		BioReactionRecipeJsonBuilder.create(VanillaDna.ZOMBIFIED_PIGLIN, VanillaDna.PIGLIN, VanillaDna.ZOMBIE)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zombified_piglin_dna"));

		BioReactionRecipeJsonBuilder.create(VanillaDna.SHULKER, VanillaDna.PHANTOM, VanillaDna.ENDERMAN)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("shulker_dna"));

		BioReactionRecipeJsonBuilder.create(ModDna.ENDER_CREEPER, VanillaDna.CREEPER, VanillaDna.ENDERMAN)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("ender_creeper_dna"));

		BioReactionRecipeJsonBuilder.create(ModDna.ZOMBIE_CREEPER, VanillaDna.CREEPER, VanillaDna.ZOMBIE)
				.criterion(hasItem(ModBlocks.BIO_REACTION_CHAMBER), conditionsFromItem(ModBlocks.BIO_REACTION_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zombie_creeper_dna"));

		GrowthRecipeJsonBuilder.create(VanillaDna.ENDERMAN, VanillaDna.ENDERMITE)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("enderman_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.ZOMBIE, VanillaDna.DROWNED)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("zombie_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.MOOSHROOM, VanillaDna.COW)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("mooshroom_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.HORSE, VanillaDna.DONKEY)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("horse_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.ELDER_GUARDIAN, VanillaDna.GUARDIAN)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("elder_guardian_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.EVOKER, VanillaDna.VINDICATOR)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("evoker_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.VINDICATOR, VanillaDna.PILLAGER)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("vindicator_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.PILLAGER, VanillaDna.VILLAGER)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("pillager_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.VILLAGER, VanillaDna.ZOMBIE_VILLAGER)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("villager_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.PIGLIN_BRUTE, VanillaDna.PIGLIN)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("piglin_brute_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.PIGLIN, VanillaDna.ZOMBIFIED_PIGLIN)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("piglin_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.PIGLIN, VanillaDna.PIG)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("piglin_dna_from_pig_dna_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.GLOW_SQUID, VanillaDna.SQUID)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("glow_squid_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.FROG, VanillaDna.TADPOLE)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("frog_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.BOGGED, VanillaDna.SKELETON)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("bogged_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.BLAZE, VanillaDna.BREEZE)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("blaze_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.CAVE_SPIDER, VanillaDna.SPIDER)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("cave_spider_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.HOGLIN, VanillaDna.ZOGLIN)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("hoglin_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.OCELOT, VanillaDna.CAT)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("ocelot_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.LLAMA, VanillaDna.SHEEP)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("llama_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.GUARDIAN, VanillaDna.PUFFERFISH)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("guardian_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.PARROT, VanillaDna.CHICKEN)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("parrot_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.DOLPHIN, VanillaDna.COD)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("dolphin_dna_from_growth"));

		GrowthRecipeJsonBuilder.create(VanillaDna.AXOLOTL, VanillaDna.TROPICAL_FISH)
				.criterion(hasItem(ModBlocks.GROWTH_CHAMBER), conditionsFromItem(ModBlocks.GROWTH_CHAMBER))
				.offerTo(exporter, MonsterBreeder.identifier("axolotl_dna_from_growth"));
	}
}
