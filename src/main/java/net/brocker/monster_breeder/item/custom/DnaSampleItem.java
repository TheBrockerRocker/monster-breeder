package net.brocker.monster_breeder.item.custom;

import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.components.ModComponents;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class DnaSampleItem extends Item {
    public DnaSampleItem() {
        super(new Settings()
                .maxCount(16)
        );
    }

    /**
     * Creates an item stack with a certain dna and count of 1.
     * @param identifier The identifier of the dna type, should be registered.
     */
    public static ItemStack createItemStack(Identifier identifier) {
        return createItemStack(identifier, 1);
    }

    /**
     * Creates an item stack with a certain dna and count.
     * @param identifier The identifier of the dna type, should be registered.
     * @param count The amount of samples in the item stack.
     */
    public static ItemStack createItemStack(Identifier identifier, int count) {
        ItemStack stack = new ItemStack(ModItems.DNA_SAMPLE, count);
        stack.set(ModComponents.DNA_COMPONENT, identifier);
        stack.set(DataComponentTypes.RARITY, DnaUtil.getRegistry().get(identifier).getRarity());
        return stack;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.monster_breeder.dna_sample", DnaUtil.getDna(stack).getName());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Dna dna = DnaUtil.getDna(stack);

        for (EntityType<?> entityType : dna.getSourceMobs()) {
            tooltip.add(Text.translatable("monster_breeder.dna_source", entityType.getName()).formatted(Formatting.GRAY));
        }
    }
}
