package net.brocker.monsterbreeder.item.custom;

import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.*;

public class DnaExtractorItem extends Item {
    public DnaExtractorItem() {
        super(new Settings()
                .maxCount(1)
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
                player.sendMessage(Text.translatable("monsterbreeder.unsupported_mob").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }

            player.getInventory().offerOrDrop(DnaSampleItem.createItemStack(dnaIdentifier));
            return ActionResult.SUCCESS;
        } else {
            player.sendMessage(Text.translatable("monsterbreeder.mobs_only").formatted(Formatting.RED), false);
            return ActionResult.FAIL;
        }
    }
}