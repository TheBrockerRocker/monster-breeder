package net.brocker.monster_breeder.datagen;

import net.brocker.monster_breeder.entity.ModEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Items;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends EntityLootTableProvider {
	protected ModEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generate(RegistryWrapper.WrapperLookup registryLookup) {
		addDropWithLooting(ModEntities.ZOMBIE_CREEPER, Items.ROTTEN_FLESH, UniformLootNumberProvider.create(0, 2));
	}
}
