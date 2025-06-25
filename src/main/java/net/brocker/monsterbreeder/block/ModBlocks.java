package net.brocker.monsterbreeder.block;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.block.custom.BioReactionChamberBlock;
import net.brocker.monsterbreeder.block.custom.CentrifugeBlock;
import net.brocker.monsterbreeder.block.custom.DnaAltarBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DNA_ALTAR = registerBlock("dna_altar",
            new DnaAltarBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static final Block CENTRIFUGE = registerBlock("centrifuge",
            new CentrifugeBlock(AbstractBlock.Settings.create()));

    public static final Block BIO_REACTION_CHAMBER = registerBlock("bio_reaction_chamber",
            new BioReactionChamberBlock(AbstractBlock.Settings.create().nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MonsterBreeder.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID + ":" + name),
                new BlockItem(block, new Item.Settings()));
        return item;
    }

    public static void registerModBlocks() {}
}
