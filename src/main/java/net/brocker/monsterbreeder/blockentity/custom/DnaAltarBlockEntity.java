package net.brocker.monsterbreeder.blockentity.custom;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.blockentity.ModBlockEntities;
import net.brocker.monsterbreeder.item.ModItems;
import net.brocker.monsterbreeder.screen.custom.DnaAltarScreenHandler;
import net.brocker.monsterbreeder.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DnaAltarBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    public static final int maxProgress = 500;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private @Nullable Dna dna = null;
    private boolean summoning = false;
    public int progress = 0;

    public DnaAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DNA_ALTAR, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (!summoning) return;

        ItemStack stack = this.getStack(0);
        if (!stack.isOf(ModItems.DNA_SAMPLE) || DnaUtil.getDna(stack).getSummonResult() == null) {
            stopSummon();
            return;
        }

        dna = DnaUtil.getDna(stack);
        progress++;

        if(progress >= maxProgress) {
            if (!world.isClient) {
                summonEntity();
            }
            stack.decrement(1);
            stopSummon();
        }
        markDirty(world, pos, state);
    }

    private void summonEntity() {
        if (this.world instanceof ServerWorld serverWorld) {
            EntityType<?> entityType = dna.getSummonResult();
            BlockPos spawn = findSpawnPosition();
            entityType.spawn(serverWorld, spawn != null ? spawn : pos.up(), SpawnReason.MOB_SUMMONED);
        }
    }

    private @Nullable BlockPos findSpawnPosition() {
        Random random = world.getRandom();

        for (int i = 1; i <= 10; i++) {
            int x = random.nextBetween(-5, 5);
            int z = random.nextBetween(-5, 5);

            BlockPos.Mutable blockPos = new BlockPos.Mutable(pos.getX() + x, pos.getY() - 2, pos.getZ() + z);
            while (!isSafe(blockPos) && blockPos.getY() < pos.getY() + 5) {
                blockPos.move(0, 1, 0);
            }
            if (isSafe(blockPos)) return blockPos.toImmutable();
        }

        return null;
    }

    private boolean isSafe(BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid()
                && world.getBlockState(pos).isAir()
                && world.getBlockState(pos.up()).isAir();
    }

    public void startSummon() {
        summoning = true;
        markDirty();
    }

    public void stopSummon() {
        summoning = false;
        progress = 0;
        dna = null;
        markDirty();
    }

    public boolean isSummoning() {
        return summoning;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("progress", progress);
        nbt.putBoolean("summoning", summoning);
        if (dna != null) nbt.putString("dna", DnaRegistry.INSTANCE.getKey(dna).toString());
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("progress");
        summoning = nbt.getBoolean("summoning");
        dna = nbt.contains("dna") ? DnaRegistry.INSTANCE.getValue(Identifier.of(nbt.getString("dna"))) : null;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.monsterbreeder.dna_altar");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DnaAltarScreenHandler(syncId, playerInventory, this.pos);
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
