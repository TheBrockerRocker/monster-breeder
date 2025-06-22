package net.brocker.monsterbreeder.item.custom;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.components.ModComponents;
import net.brocker.monsterbreeder.dna.ModDna;
import net.brocker.monsterbreeder.dna.VanillaDna;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.util.AdvancementUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SyringeItem extends Item {
    public SyringeItem() {
        super(new Settings()
                .maxCount(1)
        );
    }

    /**
     * Creates an item stack with a certain dna and count of 1.
     * @param identifier The identifier of the dna type, should be registered.
     */
    public static ItemStack createItemStack(Identifier identifier, int purity) {
        return createItemStack(identifier, purity, 1);
    }

    /**
     * Creates an item stack with a certain dna and count.
     * @param identifier The identifier of the dna type, should be registered.
     * @param count The amount of samples in the item stack.
     */
    public static ItemStack createItemStack(Identifier identifier, int purity, int count) {
        ItemStack stack = new ItemStack(ModItems.USED_SYRINGE, count);
        stack.set(ModComponents.DNA_COMPONENT, identifier);
        DnaUtil.setPurity(stack, purity);
        stack.set(DataComponentTypes.RARITY, DnaRegistry.INSTANCE.getValue(identifier).getRarity());
        return stack;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity livingEntity, Hand hand) {
        if (player.getWorld().isClient || hand != Hand.MAIN_HAND) return ActionResult.PASS;

        if (livingEntity instanceof MobEntity mobEntity) {
            EntityType<?> mobType = mobEntity.getType();
            String mobName = mobType.getName().getString();
            Identifier dnaIdentifier = DnaUtil.getDnaIdentifier(mobType);

            if (dnaIdentifier == null) {
                player.sendMessage(Text.translatable("monsterbreeder.unsupported_mob").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            } else if (!DnaUtil.getDnaIdentifier(stack).equals(VanillaDna.UNKNOWN) && !DnaUtil.getDnaIdentifier(stack).equals(dnaIdentifier)) {
                player.sendMessage(Text.translatable("monsterbreeder.cross_contamination").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }

            List<String> extractedFrom = new ArrayList<>(stack.getOrDefault(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, new ArrayList<>()));
            String mobId = mobEntity.getUuidAsString();
            if (extractedFrom.contains(mobId)) {
                player.sendMessage(Text.translatable("monsterbreeder.already_extracted").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }
            extractedFrom.add(mobId);
            stack.set(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, extractedFrom);

            DnaUtil.setDna(stack, dnaIdentifier);

            int purity = DnaUtil.getPurity(stack);
            if (purity >= 100) {
                player.sendMessage(Text.translatable("monsterbreeder.max_purity").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }
            DnaUtil.setPurity(stack, purity + 10);

            player.setStackInHand(hand, stack.withItem(ModItems.USED_SYRINGE));
            player.sendMessage(Text.translatable("monsterbreeder.extracted_from", mobName), false);

            if (player instanceof ServerPlayerEntity serverPlayer) {
                if (mobType == EntityType.ZOGLIN) {
                    AdvancementUtil.grant(serverPlayer, AdvancementUtil.get(serverPlayer.server, Identifier.of(MonsterBreeder.MOD_ID, "extract_blood_from_zoglin")));
                }
                if (purity + 10 >= 100) {
                    AdvancementUtil.grant(serverPlayer, AdvancementUtil.get(serverPlayer.server, Identifier.of(MonsterBreeder.MOD_ID, "extract_pure_blood")));
                }
            }

            return ActionResult.SUCCESS;
        } else {
            player.sendMessage(Text.translatable("monsterbreeder.mobs_only").formatted(Formatting.RED), false);
            return ActionResult.FAIL;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.isOf(ModItems.USED_SYRINGE)) {
            tooltip.add(Text.translatable("monsterbreeder.purity", DnaUtil.getPurity(stack)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("monsterbreeder.blood", DnaUtil.getDna(stack).getSourceMobs().get(0).getName()).formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("monsterbreeder.click_to_extract").formatted(Formatting.GRAY));
        }
    }
}