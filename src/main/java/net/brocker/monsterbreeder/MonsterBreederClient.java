package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MonsterBreederClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.DNA_ALTAR_SCREEN_HANDLER, DnaAltarScreen::new);


    }
}