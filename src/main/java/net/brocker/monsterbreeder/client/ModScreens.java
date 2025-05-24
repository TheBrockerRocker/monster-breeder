package net.brocker.monsterbreeder.client;

import net.brocker.monsterbreeder.screen.DnaAltarScreen;
import net.brocker.monsterbreeder.screen.DnaAltarScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class ModScreens {
    public static void registerScreens() {
        HandledScreens.register(DnaAltarScreenHandler.TYPE, DnaAltarScreen::new);
    }
}
