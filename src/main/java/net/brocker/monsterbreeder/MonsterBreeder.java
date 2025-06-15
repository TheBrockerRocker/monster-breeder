package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.blockentity.ModBlockEntities;
import net.brocker.monsterbreeder.command.FusionTestCommand;
import net.brocker.monsterbreeder.command.argument.DnaIdentifierArgumentType;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.recipe.ModRecipes;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer{
    public static final String MOD_ID = "monsterbreeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandelers();
        ModComponents.registerModComponets();
        ModRecipes.registerRecipes();
        ModDna.registerModDna();

        ArgumentTypeRegistry.registerArgumentType(
                Identifier.of(MOD_ID, "dna_identifier"),
                DnaIdentifierArgumentType.class,
                ConstantArgumentSerializer.of(DnaIdentifierArgumentType::new)
        );
        CommandRegistrationCallback.EVENT.register(FusionTestCommand::register);
    }
}