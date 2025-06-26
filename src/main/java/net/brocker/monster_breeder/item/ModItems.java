package net.brocker.monster_breeder.item;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.entity.ModEntities;
import net.brocker.monster_breeder.item.custom.DnaExtractorItem;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.item.custom.SyringeItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DNA_SAMPLE = registerItem("dna_sample", new DnaSampleItem());
    public static final Item DNA_EXTRACTOR = registerItem("dna_extractor", new DnaExtractorItem());
    public static final Item SYRINGE = registerItem("syringe",
            new SyringeItem());
    public static final Item USED_SYRINGE = registerItem("used_syringe",
            new SyringeItem());
    public static final Item ENDER_CREEPER_SPAWN_EGG = registerItem("ender_creeper_spawn_egg",
            new SpawnEggItem(ModEntities.ENDER_CREEPER, hexCode("a521bc"), hexCode("290063"), new Item.Settings()));

    public static final RegistryKey<ItemGroup> GROUP_ITEMS_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MonsterBreeder.MOD_ID, "items"));
    public static final ItemGroup GROUP_ITEMS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.DNA_ALTAR))
            .displayName(Text.translatable("itemGroup.monster_breeder.items"))
            .entries((displayContext, entries) -> {
                entries.add(new ItemStack(ModBlocks.DNA_ALTAR));
                entries.add(new ItemStack(ModBlocks.CENTRIFUGE));
                entries.add(new ItemStack(ModBlocks.BIO_REACTION_CHAMBER));
                entries.add(new ItemStack(DNA_EXTRACTOR));
                entries.add(new ItemStack(SYRINGE));
            })
            .build();

    public static final RegistryKey<ItemGroup> GROUP_EGGS_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MonsterBreeder.MOD_ID, "eggs"));
    public static final ItemGroup GROUP_EGGS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ENDER_CREEPER_SPAWN_EGG))
            .displayName(Text.translatable("itemGroup.monster_breeder.eggs"))
            .entries((displayContext, entries) -> {
                entries.add(new ItemStack(ENDER_CREEPER_SPAWN_EGG));
            })
            .build();

    public static final RegistryKey<ItemGroup> GROUP_DNA_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MonsterBreeder.MOD_ID, "dna"));
    public static final ItemGroup GROUP_DNA = FabricItemGroup.builder()
            .icon(() -> DnaSampleItem.createItemStack(ModDna.UNKNOWN))
            .displayName(Text.translatable("itemGroup.monster_breeder.dna"))
            .entries((displayContext, entries) -> DnaUtil.getRegistry().getKeys().stream().map(RegistryKey::getValue).filter(identifier -> !identifier.equals(ModDna.UNKNOWN)).forEach(identifier -> entries.add(DnaSampleItem.createItemStack(identifier))))
            .build();

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Registry.register(Registries.ITEM_GROUP, GROUP_ITEMS_KEY, GROUP_ITEMS);
        Registry.register(Registries.ITEM_GROUP, GROUP_EGGS_KEY, GROUP_EGGS);
        Registry.register(Registries.ITEM_GROUP, GROUP_DNA_KEY, GROUP_DNA);
    }

    private static int hexCode(String hexColor) {
        int color = Integer.parseInt(hexColor.substring(1), 16);

        if (hexColor.length() == 7) {
            color |= 0xFF000000;
        }

        return color;
    }
}
