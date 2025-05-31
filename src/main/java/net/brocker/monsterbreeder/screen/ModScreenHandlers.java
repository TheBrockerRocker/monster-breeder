package net.brocker.monsterbreeder.screen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<DnaAltarScreenHandler> DNA_ALTAR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MonsterBreeder.MOD_ID, "dna_altar_screen_handeler"),
                    new ExtendedScreenHandlerType<>(DnaAltarScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandelers(){}
}
