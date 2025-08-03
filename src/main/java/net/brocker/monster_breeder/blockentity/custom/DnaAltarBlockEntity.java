package net.brocker.monster_breeder.blockentity.custom;

import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.SummoningBehaviour;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.blockentity.ModBlockEntities;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.util.ImplementedInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class DnaAltarBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private @Nullable Dna dna = null;
    private boolean summoning = false;
    private NbtCompound summoningData = null;

    public DnaAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DNA_ALTAR, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        ItemStack stack = this.getStack(0);
        Dna dna1 = DnaUtil.getDna(stack);
        if (world.isClient) {
            if (stack.isOf(ModItems.DNA_SAMPLE) && dna1 != null && dna == dna1 && summoningData != null) dna.getSummoningBehaviour().tickClient(this, summoningData);
            return;
        }
        if (!summoning) {
            boolean f = dna != dna1;
            dna = dna1;
            if (f) sendUpdate();
            return;
        }

        if (!stack.isOf(ModItems.DNA_SAMPLE) || dna1 == null || dna != dna1) {
            stopSummon();
            return;
        }

        dna.getSummoningBehaviour().tick(this, summoningData);
    }

    public boolean startSummon() {
        Dna dna1 = DnaUtil.getDna(getStack(0));
        if (summoning || dna1 == null || dna1.getSummoningBehaviour() == null || dna != dna1) return false;

        SummoningBehaviour<?> summoningBehaviour = dna1.getSummoningBehaviour();
        if (!summoningBehaviour.canSummon(this)) return false;

        summoning = true;
        summoningData = summoningBehaviour.startSummon(this);
        sendUpdate();

        return true;
    }

    public void stopSummon() {
        if (!summoning) return;
        dna.getSummoningBehaviour().stopSummon(this, summoningData);

        summoning = false;
        summoningData = null;
        sendUpdate();
    }

    public boolean isIdle() {
        return !summoning;
    }

    public void sendUpdate() {
        sendUpdate(world, pos, world.getBlockState(pos));
    }

    public void sendUpdate(World world, BlockPos pos, BlockState state) {
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putBoolean("summoning", summoning);
        if (summoningData != null) nbt.put("summoningData", summoningData);
        if (dna != null) nbt.putString("dna", DnaUtil.getRegistry().getKey(dna).get().getValue().toString());
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Collections.fill(inventory, ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory, registryLookup);
        summoning = nbt.getBoolean("summoning");
        summoningData = summoning && nbt.contains("summoningData") ? nbt.getCompound("summoningData") : null;
        dna = nbt.contains("dna") ? DnaUtil.getRegistry().get(Identifier.of(nbt.getString("dna"))) : null;
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

    public boolean render(BlockEntityRendererFactory.Context context, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (dna == null) return true;
        SummoningBehaviour<?> behaviour = dna.getSummoningBehaviour();
        if (behaviour == null) return true;

        behaviour.render(this, summoningData, context, tickDelta, matrices, vertexConsumers, light, overlay);
        return false;
    }
}
