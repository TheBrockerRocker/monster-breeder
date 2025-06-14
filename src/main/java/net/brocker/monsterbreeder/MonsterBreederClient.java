package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.component.ModDataComponentTypes;
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

        ColorProviderRegistry.ITEM.register((stack, tint) -> {
            if (tint == 1) { // only layer1 (fluid)
                return stack.getOrDefault(ModDataComponentTypes.FLUID_COLOR, 0xFF00FFAA); // fallback teal
            }
            return 0xFFFFFFFF; // no tint
        }, ModItems.DNA_SAMPLE);
    }
}