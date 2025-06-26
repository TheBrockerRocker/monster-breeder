package net.brocker.monster_breeder.item.custom;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.components.ModComponents;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.util.AdvancementUtil;
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
     * Creates an item stack with the specified blood type and purity with a count of 1.
     * @param identifier The identifier of the entity type, should be registered.
     * @param purity The purity of this blood sample.
     */
    public static ItemStack createItemStack(Identifier identifier, int purity) {
        return createItemStack(identifier, purity, 1);
    }

    /**
     * Creates an item stack with the specified blood type, purity, and count.
     * @param identifier The identifier of the entity type, should be registered.
     * @param purity The purity of this blood sample.
     * @param count The amount of samples in the item stack.
     */
    public static ItemStack createItemStack(Identifier identifier, int purity, int count) {
        ItemStack stack = new ItemStack(ModItems.USED_SYRINGE, count);
        stack.set(ModComponents.BLOOD_COMPONENT, identifier);
        DnaUtil.setPurity(stack, purity);
        return stack;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity livingEntity, Hand hand) {
        if (player.getWorld().isClient || hand != Hand.MAIN_HAND) return ActionResult.PASS;
        boolean isUsed = stack.isOf(ModItems.USED_SYRINGE);

        if (livingEntity instanceof MobEntity mobEntity) {
            EntityType<?> mobType = mobEntity.getType();
            String mobName = mobType.getName().getString();

            if (DnaUtil.getDnaIdentifier(mobType) == null) {
                player.sendMessage(Text.translatable("monster_breeder.unsupported_mob").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            } else if (isUsed && DnaUtil.getBloodType(stack) != mobType) {
                player.sendMessage(Text.translatable("monster_breeder.cross_contamination").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }

            List<String> extractedFrom = new ArrayList<>(stack.getOrDefault(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, new ArrayList<>()));
            String mobId = mobEntity.getUuidAsString();
            if (extractedFrom.contains(mobId)) {
                player.sendMessage(Text.translatable("monster_breeder.already_extracted").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }
            extractedFrom.add(mobId);
            stack.set(ModComponents.BLOOD_EXTRACTED_FROM_COMPONENT, extractedFrom);

            DnaUtil.setBloodType(stack, mobType);

            int purity = DnaUtil.getPurity(stack);
            if (purity >= 100) {
                player.sendMessage(Text.translatable("monster_breeder.max_purity").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }
            DnaUtil.setPurity(stack, purity + 10);

            player.setStackInHand(hand, stack.withItem(ModItems.USED_SYRINGE));
            player.sendMessage(Text.translatable("monster_breeder.extracted_from", mobName), false);

            if (player instanceof ServerPlayerEntity serverPlayer) {
                if (mobType == EntityType.ZOGLIN) {
                    AdvancementUtil.grant(serverPlayer, AdvancementUtil.get(serverPlayer.server, MonsterBreeder.identifier("extract_blood_from_zoglin")));
                }
                if (purity + 10 >= 100) {
                    AdvancementUtil.grant(serverPlayer, AdvancementUtil.get(serverPlayer.server, MonsterBreeder.identifier("extract_pure_blood")));
                }
            }

            return ActionResult.SUCCESS;
        } else {
            player.sendMessage(Text.translatable("monster_breeder.mobs_only").formatted(Formatting.RED), false);
            return ActionResult.FAIL;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.isOf(ModItems.USED_SYRINGE)) {
            tooltip.add(Text.translatable("monster_breeder.purity", DnaUtil.getPurity(stack)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("monster_breeder.blood", DnaUtil.getBloodType(stack).getName()).formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("monster_breeder.click_to_extract").formatted(Formatting.GRAY));
        }
    }
}