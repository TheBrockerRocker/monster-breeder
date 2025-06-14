package net.brocker.monsterbreeder.item;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.item.custom.SyringeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SYRINGE = registerItem("syringe",
            new SyringeItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MonsterBreeder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterBreeder.LOGGER.info("Registering ModItems for " + MonsterBreeder.MOD_ID);
    }
}
