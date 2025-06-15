package net.brocker.monsterbreeder.block.entity;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.block.entity.custom.BioReactionChamberBlockEntity;
import net.brocker.monsterbreeder.block.entity.custom.DnaAltarBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<DnaAltarBlockEntity> DNA_ALTAR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "dna_altar_be"),
                    BlockEntityType.Builder.create(DnaAltarBlockEntity::new, ModBlocks.DNA_ALTAR).build(null));

    public static final BlockEntityType<BioReactionChamberBlockEntity> BIO_REACTION_CHAMBER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction_chamber_be"),
                    BlockEntityType.Builder.create(BioReactionChamberBlockEntity::new, ModBlocks.BIO_REACTION_CHAMBER).build(null));

    public static void registerBlockEntities() {

    }
}
