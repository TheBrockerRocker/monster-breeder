package net.brocker.monsterbreeder.item.custom;

import net.brocker.monsterbreeder.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class SyringeItem extends Item {
    public SyringeItem() {
        super(new Settings()
                .maxCount(1)
        );
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity livingEntity, Hand hand) {
        if (!player.getWorld().isClient) {
            if (stack.getItem() == ModItems.SYRINGE && hand == Hand.MAIN_HAND) {
                if (livingEntity instanceof MobEntity mobEntity) {
                    String mobName = mobEntity.getType().getName().getString();

                    player.sendMessage(Text.literal("Syringe extracted data from: " + mobName), false);
                    return ActionResult.SUCCESS;
                } else {
                    player.sendMessage(Text.literal("This syringe only works on mobs!"), false);
                    return ActionResult.FAIL;
                }
            }
        }
        return ActionResult.PASS;
    }
}