package net.brocker.monster_breeder;

import net.brocker.monster_breeder.datagen.ModAdvancementProvider;
import net.brocker.monster_breeder.datagen.ModModelProvider;
import net.brocker.monster_breeder.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MonsterBreederDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
