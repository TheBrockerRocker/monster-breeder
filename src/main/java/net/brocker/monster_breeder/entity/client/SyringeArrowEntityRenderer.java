package net.brocker.monster_breeder.entity.client;

import net.brocker.monster_breeder.entity.custom.SyringeArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SyringeArrowEntityRenderer extends ProjectileEntityRenderer<SyringeArrowEntity> {
	public static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/arrow.png");

	public SyringeArrowEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	public Identifier getTexture(SyringeArrowEntity syringeArrowEntity) {
		return TEXTURE;
	}
}
