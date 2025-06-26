package net.brocker.monsterbreeder;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.blockentity.ModBlockEntities;
import net.brocker.monsterbreeder.blockentity.renderer.DnaAltarBlockEntityRenderer;
import net.brocker.monsterbreeder.entity.ModEntities;
import net.brocker.monsterbreeder.entity.client.EnderCreeperEntityRenderer;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.screen.ModScreenHandlers;
import net.brocker.monsterbreeder.screen.custom.BioReactorChamberScreen;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MonsterBreederClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        addScreenHandlers();
        addBlockEntityRenderers();
        addEntityRenderers();
        addColorProviders();
    }

    private void addScreenHandlers() {
        HandledScreens.register(ModScreenHandlers.DNA_ALTAR_SCREEN_HANDLER, DnaAltarScreen::new);
        HandledScreens.register(ModScreenHandlers.BIO_REACTOR_CHAMBER_SCREEN_HANDLER, BioReactorChamberScreen::new);
    }

    private void addBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntities.DNA_ALTAR, DnaAltarBlockEntityRenderer::new);
    }

    private void addEntityRenderers() {
        EntityRendererRegistry.register(ModEntities.ENDER_CREEPER, EnderCreeperEntityRenderer::new);
    }

    private void addColorProviders() {
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