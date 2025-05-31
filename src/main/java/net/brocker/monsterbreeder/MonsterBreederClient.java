package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.block.entity.ModBlockEntities;
import net.brocker.monsterbreeder.block.entity.custom.DnaAltarBlockEntity;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MonsterBreederClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.DNA_ALTAR_SCREEN_HANDLER, DnaAltarScreen::new);
    }
}
