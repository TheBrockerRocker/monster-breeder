package net.brocker.monsterbreeder.screen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class DnaAltarScreenHandler extends ScreenHandler {
    public static final ScreenHandlerType<DnaAltarScreenHandler> TYPE = 
        Registry.register(Registries.SCREEN_HANDLER, 
            Identifier.of(MonsterBreeder.MOD_ID, "dna_altar"),
            new ScreenHandlerType<>(DnaAltarScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES));
            
    private final ScreenHandlerContext context;
    private final Inventory inventory;

    public DnaAltarScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public DnaAltarScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(TYPE, syncId);
        this.context = context;
        this.inventory = new SimpleInventory(3) {
            @Override
            public void markDirty() {
                super.markDirty();
                DnaAltarScreenHandler.this.onContentChanged(this);
            }
        };

        // Input slots (2 slots)
        this.addSlot(new Slot(inventory, 0, 44, 35));
        this.addSlot(new Slot(inventory, 1, 80, 35));
        // Output slot (1 slot)
        this.addSlot(new Slot(inventory, 2, 116, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        // Player inventory
        int m;
        int l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, 2, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        // Here you would add your recipe matching logic
        // For now, just copy the first input to the output as an example
        if (!this.inventory.getStack(0).isEmpty() && !this.inventory.getStack(1).isEmpty()) {
            // TODO: Add your recipe matching logic here
            ItemStack result = this.inventory.getStack(0).copy();
            result.setCount(1);
            this.inventory.setStack(2, result);
        } else {
            this.inventory.setStack(2, ItemStack.EMPTY);
        }
    }
}
