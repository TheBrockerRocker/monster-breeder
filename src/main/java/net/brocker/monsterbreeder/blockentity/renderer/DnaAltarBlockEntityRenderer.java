package net.brocker.monsterbreeder.blockentity.renderer;

import net.brocker.monsterbreeder.blockentity.custom.DnaAltarBlockEntity;
import net.brocker.monsterbreeder.util.AnimationUtil;
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

public class DnaAltarBlockEntityRenderer implements BlockEntityRenderer<DnaAltarBlockEntity> {
	protected final BlockEntityRendererFactory.Context context;
	protected float tick = 0;

	public DnaAltarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
		this.context = context;
	}

	@Override
	public void render(DnaAltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (!MinecraftClient.getInstance().isPaused()) tick += tickDelta;

		matrices.push();
		matrices.translate(0.5, 1.3, 0.5);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(AnimationUtil.getAnimationFrame(tick, 10) * 360));
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
