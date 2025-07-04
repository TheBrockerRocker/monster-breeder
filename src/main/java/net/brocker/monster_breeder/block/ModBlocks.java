package net.brocker.monster_breeder.block;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.block.custom.BioReactionChamberBlock;
import net.brocker.monster_breeder.block.custom.CentrifugeBlock;
import net.brocker.monster_breeder.block.custom.DnaAltarBlock;
import net.brocker.monster_breeder.block.custom.GrowthChamberBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DNA_ALTAR = registerBlock("dna_altar",
            new DnaAltarBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque().requiresTool()));

    public static final Block CENTRIFUGE = registerBlock("centrifuge",
            new CentrifugeBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque().requiresTool()));

    public static final Block BIO_REACTION_CHAMBER = registerBlock("bio_reaction_chamber",
            new BioReactionChamberBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque().requiresTool()));

    public static final Block GROWTH_CHAMBER = registerBlock("growth_chamber",
            new GrowthChamberBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).sounds(BlockSoundGroup.METAL).requiresTool()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MonsterBreeder.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID + ":" + name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {}
}
