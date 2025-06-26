package net.brocker.monster_breeder.entity.client;

import net.brocker.monster_breeder.MonsterBreeder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EnderCreeperEntityRenderer extends CreeperEntityRenderer {
	private static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID, "textures/entity/ender_creeper/ender_creeper.png");

	public EnderCreeperEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(CreeperEntity creeperEntity) {
		return TEXTURE;
	}
}