package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.block.entity.ModBlockEntities;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer{
    public static final String MOD_ID = "monsterbreeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandelers();
    }


}