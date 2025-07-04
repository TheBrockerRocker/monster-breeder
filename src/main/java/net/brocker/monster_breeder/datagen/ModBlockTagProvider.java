package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider<Block> {
	public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, RegistryKeys.BLOCK, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
				.add(ModBlocks.DNA_ALTAR, ModBlocks.CENTRIFUGE, ModBlocks.BIO_REACTION_CHAMBER, ModBlocks.GROWTH_CHAMBER)
				.setReplace(false);
	}
}
