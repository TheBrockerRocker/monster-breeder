package net.brocker.monsterbreeder.screen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<DnaAltarScreenHandler> DNA_ALTAR_SCREEN_HANDLER =
    new ScreenHandlerType<>(DnaAltarScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MonsterBreeder.MOD_ID, "dna_altar"), 
            DNA_ALTAR_SCREEN_HANDLER);
    }
}
