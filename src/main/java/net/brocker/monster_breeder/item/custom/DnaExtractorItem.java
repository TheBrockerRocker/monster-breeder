package net.brocker.monster_breeder.item.custom;

import net.brocker.monster_breeder.api.util.DnaUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.*;

public class DnaExtractorItem extends Item {
    public DnaExtractorItem() {
        super(new Settings()
                .maxCount(1)
                .maxDamage(8)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity livingEntity, Hand hand) {
        if (player.getWorld().isClient || hand != Hand.MAIN_HAND) return ActionResult.PASS;

        if (livingEntity instanceof MobEntity mobEntity) {
            EntityType<?> mobType = mobEntity.getType();
            Identifier dnaIdentifier = DnaUtil.getDnaIdentifier(mobType);

            if (dnaIdentifier == null) {
                player.sendMessage(Text.translatable("monster_breeder.unsupported_mob").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }

            player.getInventory().offerOrDrop(DnaSampleItem.createItemStack(dnaIdentifier));
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            return ActionResult.SUCCESS;
        } else {
            player.sendMessage(Text.translatable("monster_breeder.mobs_only").formatted(Formatting.RED), false);
            return ActionResult.FAIL;
        }
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return stack != null && !stack.isEmpty() && ingredient.isOf(Items.DIAMOND);
    }
}