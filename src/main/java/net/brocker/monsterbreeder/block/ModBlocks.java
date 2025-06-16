package net.brocker.monsterbreeder.block;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.block.custom.BioReactionChamberBlock;
import net.brocker.monsterbreeder.block.custom.CentrifugeBlock;
import net.brocker.monsterbreeder.block.custom.DnaAltarBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DNA_ALTAR = registerBlock("dna_altar",
            new DnaAltarBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static final Block CENTRIFUGE = registerBlock("centrifuge",
            new CentrifugeBlock(AbstractBlock.Settings.create()));

    public static final Block BIO_REACTION_CHAMBER = registerBlock("bio_reaction_chamber",
            new BioReactionChamberBlock(AbstractBlock.Settings.create()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MonsterBreeder.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        // Using Item.Settings in place of FabricItemSettings.
        Item item = Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID + ":" + name),
                new BlockItem(block, new Item.Settings()));
        return item;
    }

    public static void registerModBlocks() {
        MonsterBreeder.LOGGER.info("Registering ModBlocks for " + MonsterBreeder.MOD_ID);
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(DNA_ALTAR);
        });
    }
}
