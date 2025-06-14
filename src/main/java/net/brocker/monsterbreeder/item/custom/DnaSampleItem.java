package net.brocker.monsterbreeder.item.custom;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
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
        return stack;
    }

    /* Dynamic display name */
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.monsterbreeder.dna_sample", DnaUtil.getDna(stack).getName());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return DnaUtil.getDna(stack).isSpecial();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Dna dna = DnaUtil.getDna(stack);
        if (dna.getSourceMob() != null) tooltip.add(Text.translatable("monsterbreeder.dna_source", dna.getSourceMob().getName()).formatted(Formatting.GRAY));
    }
}
