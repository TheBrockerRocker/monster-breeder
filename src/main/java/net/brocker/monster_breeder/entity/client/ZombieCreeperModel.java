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
	private final ModelPart rightArm;
	private final ModelPart body;
	private final ModelPart bagPack;
	private final ModelPart tnt;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart leftArm;
	private final ModelPart head;
	private final ModelPart hat;
	public ZombieCreeperModel(ModelPart root) {
		this.root = root;
		this.rightArm = root.getChild("rightArm");
		this.body = root.getChild("body");
		this.bagPack = this.body.getChild("bagPack");
		this.tnt = this.bagPack.getChild("tnt");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
		this.leftArm = root.getChild("leftArm");
		this.head = root.getChild("head");
		this.hat = this.head.getChild("hat");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData RightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(48, 42).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, 0.0F, 1.0F));

		ModelPartData Body = modelPartData.addChild("body", ModelPartBuilder.create().uv(32, 12).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 1.0F));

		ModelPartData BagPack = Body.addChild("bagPack", ModelPartBuilder.create().uv(32, 28).cuboid(-4.0F, -4.0F, 2.0F, 8.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TnT = BagPack.addChild("tnt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Tnt2_r1 = TnT.addChild("Tnt2_r1", ModelPartBuilder.create().uv(38, 6).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 3.0F, -0.4871F, -0.3502F, 0.4964F));

		ModelPartData Tnt1_r1 = TnT.addChild("Tnt1_r1", ModelPartBuilder.create().uv(38, 0).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, 3.0F, -0.4724F, 0.4828F, 0.1019F));

		ModelPartData LeftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(16, 39).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 1.0F));

		ModelPartData RightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(0, 39).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 1.0F));

		ModelPartData LeftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(32, 42).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 0.0F, 1.0F));

		ModelPartData Head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-4.0F, -32.0F, -3.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Hat = Head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 11.0F, new Dilation(0.0F))
				.uv(0, 28).cuboid(-4.0F, -3.0F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -33.0F, 1.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(ZombieCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ZombieCreeperAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
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
		// rightArm	.render(matrices, vertexConsumer, light, overlay, color);
		// body    	.render(matrices, vertexConsumer, light, overlay, color);
		// leftLeg 	.render(matrices, vertexConsumer, light, overlay, color);
		// rightLeg	.render(matrices, vertexConsumer, light, overlay, color);
		// leftArm 	.render(matrices, vertexConsumer, light, overlay, color);
		// head    	.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}
