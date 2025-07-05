package net.brocker.monster_breeder.screen;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.screen.custom.BioReactorChamberScreenHandler;
import net.brocker.monster_breeder.screen.custom.CentrifugeScreenHandler;
import net.brocker.monster_breeder.screen.custom.DnaAltarScreenHandler;
import net.brocker.monster_breeder.screen.custom.GrowthChamberScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<DnaAltarScreenHandler> DNA_ALTAR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, MonsterBreeder.identifier("dna_altar_screen_handler"),
                    new ExtendedScreenHandlerType<>(DnaAltarScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CentrifugeScreenHandler> CENTRIFUGE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, MonsterBreeder.identifier("centrifuge_screen_handler"),
                    new ExtendedScreenHandlerType<>(CentrifugeScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<BioReactorChamberScreenHandler> BIO_REACTOR_CHAMBER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, MonsterBreeder.identifier("bio_reactor_screen_handler"),
                    new ExtendedScreenHandlerType<>(BioReactorChamberScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<GrowthChamberScreenHandler> GROWTH_CHAMBER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, MonsterBreeder.identifier("growth_screen_handler"),
                    new ExtendedScreenHandlerType<>(GrowthChamberScreenHandler::new, BlockPos.PACKET_CODEC));

    @SuppressWarnings("EmptyMethod")
    public static void registerScreenHandlers() {}
}
