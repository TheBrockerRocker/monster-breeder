package net.brocker.monsterbreeder.item;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.block.ModBlocks;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.brocker.monsterbreeder.item.custom.SyringeItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item DNA_SAMPLE = registerItem("dna_sample", new DnaSampleItem());
    public static final Item SYRINGE = registerItem("syringe",
            new SyringeItem());
    public static final Item USED_SYRINGE = registerItem("used_syringe",
            new SyringeItem());

    public static final RegistryKey<ItemGroup> ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MonsterBreeder.MOD_ID, "item_group"));
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.DNA_ALTAR))
            .displayName(Text.translatable("itemGroup.monsterbreeder"))
            .entries((displayContext, entries) -> {
                entries.add(new ItemStack(ModBlocks.DNA_ALTAR));
                entries.add(new ItemStack(ModBlocks.CENTRIFUGE));
                entries.add(new ItemStack(ModBlocks.BIO_REACTION_CHAMBER));
                entries.add(new ItemStack(SYRINGE));
                DnaRegistry.INSTANCE.getKeySet().forEach(identifier -> entries.add(DnaSampleItem.createItemStack(identifier)));
            })
            .build();

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterBreeder.LOGGER.info("Registering ModItems for " + MonsterBreeder.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP_KEY, ITEM_GROUP);
    }
}
