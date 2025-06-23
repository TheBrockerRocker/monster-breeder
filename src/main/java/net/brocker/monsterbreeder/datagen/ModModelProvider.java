package net.brocker.monsterbreeder.datagen;

import net.brocker.monsterbreeder.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DNA_ALTAR);
        // blockStateModelGenerator.registerItemModel(ModBlocks.DNA_ALTAR);
        // blockStateModelGenerator.registerItemModel(ModBlocks.BIO_REACTION_CHAMBER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Model spawnEggModel = new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty());

        itemModelGenerator.register(ModItems.ENDER_CREEPER_SPAWN_EGG, spawnEggModel);
    }
}
