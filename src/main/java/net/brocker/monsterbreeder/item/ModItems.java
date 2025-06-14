package net.brocker.monsterbreeder.item;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterBreeder.LOGGER.info("Registering ModItems for " + MonsterBreeder.MOD_ID);
    }
}
