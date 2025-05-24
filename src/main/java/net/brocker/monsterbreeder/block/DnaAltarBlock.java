package net.brocker.monsterbreeder.block;

import net.brocker.monsterbreeder.screen.DnaAltarScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DnaAltarBlock extends Block {
    private static final Text TITLE = Text.translatable("container.monsterbreeder.dna_altar");

    public DnaAltarBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = createScreenHandlerFactory(state, world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.SUCCESS;
    }

    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerEntity) ->
                new DnaAltarScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos)),
                TITLE
        );
    }
}