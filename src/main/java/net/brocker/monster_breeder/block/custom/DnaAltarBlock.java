package net.brocker.monster_breeder.block.custom;

import com.mojang.serialization.MapCodec;
import net.brocker.monster_breeder.blockentity.ModBlockEntities;
import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.brocker.monster_breeder.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DnaAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE =
            Block.createCuboidShape(1, 0, 1, 15, 15, 15);
    public static final MapCodec<DnaAltarBlock> CODEC = DnaAltarBlock.createCodec(DnaAltarBlock::new);

    public DnaAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DnaAltarBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock() && world.getBlockEntity(pos) instanceof DnaAltarBlockEntity blockEntity) {
            ItemScatterer.spawn(world, pos, blockEntity);
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stackInHand, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!(world.getBlockEntity(pos) instanceof DnaAltarBlockEntity blockEntity)) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        ItemStack stackOnAltar = blockEntity.getStack(0);
        boolean isTopOfBlock = hit.getSide() == Direction.UP;
        boolean isHandEmpty = stackInHand.isEmpty();
        boolean isAltarEmpty = stackOnAltar.isEmpty();

        if(isTopOfBlock && isAltarEmpty && !isHandEmpty && stackInHand.isOf(ModItems.DNA_SAMPLE)) {
            putItem(stackInHand, state, pos, world, player, blockEntity);
        } else if(isTopOfBlock && !isAltarEmpty && isHandEmpty && !player.isSneaking()) {
            takeItem(stackOnAltar, state, pos, world, player, hand, blockEntity);
        } else if (isTopOfBlock && !isAltarEmpty && !isHandEmpty && stackInHand.isOf(Items.FLINT_AND_STEEL) && blockEntity.isIdle()) {
            stackInHand.damage(1, player, LivingEntity.getSlotForHand(hand));
            blockEntity.startSummon();
        } else if(player.isSneaking() && !world.isClient()) {
            player.openHandledScreen(blockEntity);
        } else
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        return ItemActionResult.SUCCESS;
    }

    protected void putItem(ItemStack stack, BlockState state, BlockPos pos, World world, PlayerEntity player, DnaAltarBlockEntity blockEntity) {
        blockEntity.setStack(0, stack.copyWithCount(1));
        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1f, 2f);
        stack.decrement(1);

        blockEntity.markDirty();
        world.updateListeners(pos, state, state, 0);
    }

    protected void takeItem(ItemStack stack, BlockState state, BlockPos pos, World world, PlayerEntity player, Hand hand, DnaAltarBlockEntity blockEntity) {
        player.setStackInHand(hand, stack);
        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);

        blockEntity.stopSummon();
        blockEntity.clear();
        blockEntity.markDirty();

        world.updateListeners(pos, state, state, 0);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.DNA_ALTAR,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}