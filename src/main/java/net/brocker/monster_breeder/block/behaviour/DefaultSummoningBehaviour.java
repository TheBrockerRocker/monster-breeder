package net.brocker.monster_breeder.block.behaviour;

import net.brocker.monster_breeder.advancement.ModCriteria;
import net.brocker.monster_breeder.api.SummoningBehaviour;
import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.brocker.monster_breeder.blockentity.renderer.DnaAltarBlockEntityRenderer;
import net.brocker.monster_breeder.util.AnimationUtil;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DefaultSummoningBehaviour<T extends LivingEntity> extends SummoningBehaviour<T> {
	protected final int maxProgress;

	public DefaultSummoningBehaviour(EntityType<T> entityType) {
		this(entityType, 400);
	}

	public DefaultSummoningBehaviour(EntityType<T> entityType, int maxProgress) {
		super(entityType);
		this.maxProgress = maxProgress;
	}

	@Override
	public NbtCompound startSummon(DnaAltarBlockEntity blockEntity) {
		NbtCompound data = new NbtCompound();
		setProgress(data, 0);
		return data;
	}

	@Override
	public void tick(DnaAltarBlockEntity blockEntity, NbtCompound data) {
		int progress = getProgress(data) + 1;
		setProgress(data, progress);

		if(progress >= maxProgress) {
			summonEntity(blockEntity);
			blockEntity.getStack(0).decrement(1);
			blockEntity.stopSummon();
		}
	}

	@Override
	public void tickClient(DnaAltarBlockEntity blockEntity, NbtCompound data) {
		setProgress(data, getProgress(data) + 1);
	}

	@Override
	public @Nullable T summonEntity(DnaAltarBlockEntity blockEntity) {
		if (blockEntity.getWorld() == null || !(blockEntity.getWorld() instanceof ServerWorld world)) return null;
		grantAchievement(blockEntity);

		BlockPos spawn = findSpawnPosition(blockEntity);
		return entityType.spawn(world, spawn != null ? spawn : blockEntity.getPos().up(), SpawnReason.MOB_SUMMONED);
	}

	@Override
	public void render(DnaAltarBlockEntity blockEntity, @Nullable NbtCompound data, BlockEntityRendererFactory.Context context, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		float progress = data != null ? (float) getProgress(data) / maxProgress : 0;
		float speed = 1 + (progress * 7);

		matrices.push();
		matrices.translate(0.5, 1.3, 0.5);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
				AnimationUtil.getAnimationFrame(DnaAltarBlockEntityRenderer.TICKS.get(blockEntity), 10) * 360 * speed));
		matrices.scale(0.6f, 0.6f, 0.6f);

		renderItem(context, blockEntity.getStack(0), matrices, vertexConsumers, light, overlay);
		matrices.pop();
	}

	protected void renderItem(BlockEntityRendererFactory.Context context, ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		ItemRenderer itemRenderer = context.getItemRenderer();
		BakedModel model = itemRenderer.getModel(stack, null, null, 0);

		itemRenderer.renderItem(
				stack,
				ModelTransformationMode.FIXED,
				false,
				matrices,
				vertexConsumers,
				light,
				overlay,
				model
		);
	}

	protected @Nullable BlockPos findSpawnPosition(DnaAltarBlockEntity blockEntity) {
		World world = blockEntity.getWorld();
		BlockPos pos = blockEntity.getPos();
		if (world == null) return null;

		Random random = world.getRandom();

		for (int i = 1; i <= 10; i++) {
			int x = random.nextBetween(-5, 5);
			int z = random.nextBetween(-5, 5);

			BlockPos.Mutable blockPos = new BlockPos.Mutable(pos.getX() + x, pos.getY() - 2, pos.getZ() + z);
			while (!isSafe(blockPos, world) && blockPos.getY() < pos.getY() + 5) {
				blockPos.move(0, 1, 0);
			}
			if (isSafe(blockPos, world)) return blockPos.toImmutable();
		}

		return null;
	}

	protected boolean isSafe(BlockPos pos, World world) {
		return world.getBlockState(pos.down()).isSolid()
				&& world.getBlockState(pos).isAir()
				&& world.getBlockState(pos.up()).isAir();
	}

	protected void grantAchievement(DnaAltarBlockEntity blockEntity) {
		getNearbyPlayers(blockEntity.getWorld(), blockEntity.getPos(), 20)
				.forEach(player -> ModCriteria.SUMMONED_ENTITY.trigger(player, entityType));
	}

	protected List<ServerPlayerEntity> getNearbyPlayers(World world, BlockPos pos, double radius) {
		if (!(world instanceof ServerWorld serverWorld)) return List.of();
		return serverWorld.getPlayers(player -> player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= radius * radius);
	}

	protected int getProgress(NbtCompound data) {
		return data.getInt("progress");
	}

	protected void setProgress(NbtCompound data, int progress) {
		data.putInt("progress", progress);
	}
}
