package net.brocker.monster_breeder;

import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.blockentity.ModBlockEntities;
import net.brocker.monster_breeder.blockentity.renderer.DnaAltarBlockEntityRenderer;
import net.brocker.monster_breeder.entity.ModEntities;
import net.brocker.monster_breeder.entity.client.EnderCreeperEntityRenderer;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.screen.ModScreenHandlers;
import net.brocker.monster_breeder.screen.custom.BioReactorChamberScreen;
import net.brocker.monster_breeder.screen.custom.DnaAltarScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MonsterBreederClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        addScreenHandlers();
        addBlockEntityRenderers();
        addEntityRenderers();
        addColorProviders();
        addModelPredicates();
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

    private void addModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.USED_SYRINGE, MonsterBreeder.identifier("blood_level"), (stack, world, entity, seed) -> {
                    float purity = DnaUtil.getPurity(stack);
                    return (float) Math.ceil(purity / 12.5f) / 8f;
                });
    }
}