package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.block.entity.ModBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "monsterbreeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
    }


    @Override
    public void onInitializeClient() {
        net.brocker.monsterbreeder.client.ModScreens.registerScreens();

    }
}