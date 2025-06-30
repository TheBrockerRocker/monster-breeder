package net.brocker.monster_breeder.entity.client;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ZombieCreeperEntityRenderer<T extends ZombieCreeperEntity> extends MobEntityRenderer<T, ZombieCreeperModel<T>> {
	private static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID, "textures/entity/zombie_creeper/zombie_creeper.png");

	protected ZombieCreeperEntityRenderer(EntityRendererFactory.Context context, ZombieCreeperModel<T> entityModel, float shadowRadius) {
		super(context, entityModel, shadowRadius);
	}

	public ZombieCreeperEntityRenderer(EntityRendererFactory.Context context) {
		this(context, new ZombieCreeperModel<>(context.getPart(ZombieCreeperModel.LAYER)), 0.5F);
	}

	@Override
	public Identifier getTexture(ZombieCreeperEntity entity) {
		return TEXTURE;
	}
}
