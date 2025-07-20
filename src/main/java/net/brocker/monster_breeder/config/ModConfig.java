package net.brocker.monster_breeder.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
	public static final String GAMEPLAY = "gameplay";

	@Comment(category = GAMEPLAY) public static Comment enablePurityUnimplemented;
	@Entry(category = GAMEPLAY) public static boolean enablePurity = true;
	@SuppressWarnings("CanBeFinal")
	@Entry(category = GAMEPLAY, min = 0.1, max = 2, isSlider = true) public static float bloodPurityModifier = 1;

	@Entry(category = GAMEPLAY) public static boolean shouldZombieCreepersDropTntOnDeath = true;
	@Entry(category = GAMEPLAY, min = 10, max = 100, isSlider = true) public static int zombieCreeperDropChance = 50;
}
