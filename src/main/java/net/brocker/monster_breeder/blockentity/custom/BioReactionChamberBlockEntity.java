package net.brocker.monster_breeder.blockentity.custom;

import net.brocker.monster_breeder.util.ImplementedInventory;
import net.brocker.monster_breeder.blockentity.ModBlockEntities;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.recipe.BioReactionRecipe;
import net.brocker.monster_breeder.recipe.BioReactionRecipeInput;
import net.brocker.monster_breeder.recipe.ModRecipes;
import net.brocker.monster_breeder.screen.custom.BioReactorChamberScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BioReactionChamberBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;

    public BioReactionChamberBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BIO_REACTION_CHAMBER, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BioReactionChamberBlockEntity.this.progress;
                    case 1 -> BioReactionChamberBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: BioReactionChamberBlockEntity.this.progress = value;
                    case 1: BioReactionChamberBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.monster_breeder.bio_reaction_chamber");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BioReactorChamberScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("bio_reaction_chamber.progress", progress);
        nbt.putInt("bio_reaction_chamber.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("bio_reaction_chamber.progress");
        maxProgress = nbt.getInt("bio_reaction_chamber.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 100;
    }

    private void craftItem() {
        Optional<RecipeEntry<BioReactionRecipe>> recipe = getCurrentRecipe();
        ItemStack output = DnaSampleItem.createItemStack(recipe.get().value().output());

        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);

        if (this.getStack(OUTPUT_SLOT).isEmpty()) this.setStack(OUTPUT_SLOT, output);
            else this.getStack(OUTPUT_SLOT).setCount(this.getStack(OUTPUT_SLOT).getCount() + 1);
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<BioReactionRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        ItemStack output = DnaSampleItem.createItemStack(recipe.get().value().output());
        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    private Optional<RecipeEntry<BioReactionRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager().getFirstMatch(
                ModRecipes.BIO_REACTION_TYPE,
                new BioReactionRecipeInput(
                        this.getStack(INPUT_SLOT_1), this.getStack(INPUT_SLOT_2)
                ),
                this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = ModItems.DNA_SAMPLE.getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
