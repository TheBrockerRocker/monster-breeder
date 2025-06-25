package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.api.registry.MonsterBreederRegistries;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.blockentity.ModBlockEntities;
import net.brocker.monsterbreeder.command.FusionTestCommand;
import net.brocker.monsterbreeder.command.argument.DnaIdentifierArgumentType;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.dna.VanillaDna;
import net.brocker.monsterbreeder.entity.ModEntities;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.recipe.ModRecipes;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer{
    public static final String MOD_ID = "monsterbreeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MinecraftServer server = null;

    @Override
    public void onInitialize() {
        MonsterBreederRegistries.initialize();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandelers();
        ModComponents.registerModComponets();
        ModRecipes.registerRecipes();
        ModDna.registerModDna();
        VanillaDna.registerVanillaDna();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.DNA_ALTAR);
            entries.add(ModBlocks.CENTRIFUGE);
            entries.add(ModBlocks.BIO_REACTION_CHAMBER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.SYRINGE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ModItems.ENDER_CREEPER_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(ModItems.DNA_EXTRACTOR);
        });

        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.MONSTER, ModEntities.ENDER_CREEPER, 3, 1, 2);

        FabricDefaultAttributeRegistry.register(ModEntities.ENDER_CREEPER, CreeperEntity.createCreeperAttributes());

        ArgumentTypeRegistry.registerArgumentType(
                identifier("dna_identifier"),
                DnaIdentifierArgumentType.class,
                ConstantArgumentSerializer.of(DnaIdentifierArgumentType::new)
        );
        CommandRegistrationCallback.EVENT.register(FusionTestCommand::register);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            MonsterBreeder.server = server;

            server
                .getRegistryManager()
                .get(MonsterBreederRegistries.DNA_REGISTRY_KEY)
                .getKeys()
                .forEach(key -> LOGGER.info("DNA of type {} registered!", key.getValue()));
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> MonsterBreeder.server = null);
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}