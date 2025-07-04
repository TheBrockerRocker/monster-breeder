package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
	ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		addDrop(ModBlocks.DNA_ALTAR);
		addDrop(ModBlocks.CENTRIFUGE);
		addDrop(ModBlocks.BIO_REACTION_CHAMBER);
		addDrop(ModBlocks.GROWTH_CHAMBER);
	}
}
