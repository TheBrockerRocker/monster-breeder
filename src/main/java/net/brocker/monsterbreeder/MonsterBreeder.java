package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "monsterbreeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        try {
            ModBlocks.registerModBlocks();
            LOGGER.info("Monster Breeder initialized!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Monster Breeder", e);
            throw e;
        }
    }

    @Override
    public void onInitializeClient() {
        // Client-side initialization
        try {
            net.brocker.monsterbreeder.client.ModScreens.registerScreens();
            LOGGER.info("Monster Breeder client initialized!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Monster Breeder client", e);
            throw e;
        }
    }
}