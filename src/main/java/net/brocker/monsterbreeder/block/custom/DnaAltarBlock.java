package net.brocker.monsterbreeder.block.custom;

import com.mojang.serialization.MapCodec;
import net.brocker.monsterbreeder.block.entity.custom.DnaAltarBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;

public class DnaAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE =
            Block.createCuboidShape(2,0,2,14,13,14);
    public static final MapCodec<DnaAltarBlock> CODEC = DnaAltarBlock.createCodec(DnaAltarBlock::new);

    public DnaAltarBlock(Settings settings) {
        super(settings);
    }

    //@Override
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
        return super.getRenderType(state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world,BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof DnaAltarBlockEntity) {
                ItemScatterer.spawn(world, pos, ((DnaAltarBlockEntity) blockEntity));
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world,
                                             BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.getBlockEntity(pos) instanceof DnaAltarBlockEntity DnaAltarBlockEntity) {
            if(DnaAltarBlockEntity.isEmpty() && !stack.isEmpty()) {
                DnaAltarBlockEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                DnaAltarBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackOnPedestal = DnaAltarBlockEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                DnaAltarBlockEntity.clear();

                DnaAltarBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }else if (player.isSneaking() && !world.isClient()) {
                player.openHandledScreen(DnaAltarBlockEntity);
            }
        }
        return ItemActionResult.SUCCESS;
    }
}
