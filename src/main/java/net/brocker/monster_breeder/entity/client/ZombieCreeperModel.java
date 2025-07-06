package net.brocker.monster_breeder.entity.client;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class ZombieCreeperModel<T extends ZombieCreeperEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer LAYER = new EntityModelLayer(MonsterBreeder.identifier("zombie_creeper"), "main");

	private final ModelPart root;
	private final ModelPart head;

	public ZombieCreeperModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(48, 42).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, 0.0F, 0.0F));
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(32, 12).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));
		ModelPartData bagPack = body.addChild("bagPack", ModelPartBuilder.create().uv(32, 28).cuboid(-4.0F, -4.0F, 2.0F, 8.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData tnt = bagPack.addChild("tnt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		tnt.addChild("tnt2_r1", ModelPartBuilder.create().uv(38, 6).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 3.0F, -0.4871F, -0.3502F, 0.4964F));
		tnt.addChild("tnt1_r1", ModelPartBuilder.create().uv(38, 0).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, 3.0F, -0.4724F, 0.4828F, 0.1019F));
		modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(16, 39).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
		modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(0, 39).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(32, 42).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 0.0F, 0.0F));
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 11.0F, new Dilation(0.0F)).uv(0, 28).cuboid(-4.0F, -3.0F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(ZombieCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ZombieCreeperAnimations.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
		// this.updateAnimation(entity.idleAnimationState, ZombieCreeperAnimations.IDLE, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30F, 30F);
		headPitch = MathHelper.clamp(headPitch, -25F, 45F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		root.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}
