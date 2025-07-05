package net.brocker.monster_breeder.blockentity.renderer;

import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.brocker.monster_breeder.util.AnimationUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import java.util.HashMap;
import java.util.Map;

public class DnaAltarBlockEntityRenderer implements BlockEntityRenderer<DnaAltarBlockEntity> {
	protected final BlockEntityRendererFactory.Context context;
	protected Map<DnaAltarBlockEntity, Float> ticks = new HashMap<>();

	public DnaAltarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
		this.context = context;
	}

	@Override
	public void render(DnaAltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (entity.getStack(0).isEmpty()) {
			ticks.put(entity, 0f);
			return;
		}
		if (!MinecraftClient.getInstance().isPaused()) ticks.put(entity, ticks.getOrDefault(entity, 0f) + tickDelta);

		float progress = (float) entity.progress / DnaAltarBlockEntity.maxProgress;
		float speed = 1 + (progress * 8);

		matrices.push();
		matrices.translate(0.5, 1.3, 0.5);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(AnimationUtil.getAnimationFrame(ticks.get(entity), 10 / speed) * 360));
		matrices.scale(0.6f, 0.6f, 0.6f);

		renderItem(entity.getStack(0), matrices, vertexConsumers, light, overlay);

		matrices.pop();
	}

	private void renderItem(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
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
}
