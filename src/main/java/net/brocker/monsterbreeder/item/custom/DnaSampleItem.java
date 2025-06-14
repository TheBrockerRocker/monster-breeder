package net.brocker.monsterbreeder.item.custom;

import net.brocker.monsterbreeder.component.ModDataComponentTypes;
import net.brocker.monsterbreeder.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;                           // NEW
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DnaSampleItem extends Item {
    public DnaSampleItem(Settings settings) { super(settings); }

    /* Factory helper ─ give mob + colour, receive ready-made stack */
    public static ItemStack create(EntityType<?> mob, int argb) {
        ItemStack stack = new ItemStack(ModItems.DNA_SAMPLE);
        stack.set(ModDataComponentTypes.MONSTER_ID, Registries.ENTITY_TYPE.getId(mob));
        stack.set(ModDataComponentTypes.FLUID_COLOR, argb);
        return stack;
    }

    /* Dynamic display name */
    @Override
    public Text getName(ItemStack stack) {
        Identifier id = stack.getOrDefault(
                ModDataComponentTypes.MONSTER_ID,
                Registries.ENTITY_TYPE.getId(EntityType.PIG));     // fallback

        Text mobName = Registries.ENTITY_TYPE.get(id).getName();   // "Zombie"
        return Text.translatable("item.monsterbreeder.dna_sample", mobName);
    }
}
