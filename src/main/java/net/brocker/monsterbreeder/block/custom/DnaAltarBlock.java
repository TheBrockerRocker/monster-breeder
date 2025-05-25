package net.brocker.monsterbreeder.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.ItemScatterer;
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
        return
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

}
