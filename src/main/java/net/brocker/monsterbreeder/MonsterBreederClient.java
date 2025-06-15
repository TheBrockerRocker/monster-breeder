package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MonsterBreederClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.DNA_ALTAR_SCREEN_HANDLER, DnaAltarScreen::new);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex > 3) return -1;

            Dna.Color color = DnaUtil.getDna(stack).getColor();
            switch (tintIndex) {
                case 0 -> {
                    return color.color1();
                }
                case 1 -> {
                    return color.color2();
                }
                case 2 -> {
                    return color.color3();
                }
                case 3 -> {
                    return color.color4();
                }
                default -> {
                    return -1;
                }
            }
        }, ModItems.DNA_SAMPLE);
    }
}