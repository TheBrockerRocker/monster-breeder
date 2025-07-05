package net.brocker.monster_breeder.blockentity;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.blockentity.custom.BioReactionChamberBlockEntity;
import net.brocker.monster_breeder.blockentity.custom.CentrifugeBlockEntity;
import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.brocker.monster_breeder.blockentity.custom.GrowthChamberBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<DnaAltarBlockEntity> DNA_ALTAR =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "dna_altar"),
                    BlockEntityType.Builder.create(DnaAltarBlockEntity::new, ModBlocks.DNA_ALTAR).build(null));

    public static final BlockEntityType<CentrifugeBlockEntity> CENTRIFUGE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "centrifuge"),
                    BlockEntityType.Builder.create(CentrifugeBlockEntity::new, ModBlocks.CENTRIFUGE).build(null));

    public static final BlockEntityType<BioReactionChamberBlockEntity> BIO_REACTION_CHAMBER =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "bio_reaction_chamber"),
                    BlockEntityType.Builder.create(BioReactionChamberBlockEntity::new, ModBlocks.BIO_REACTION_CHAMBER).build(null));

    public static final BlockEntityType<GrowthChamberBlockEntity> GROWTH_CHAMBER =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MonsterBreeder.MOD_ID, "growth_chamber"),
                    BlockEntityType.Builder.create(GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER).build(null));

    @SuppressWarnings("EmptyMethod")
    public static void registerBlockEntities() {}
}
