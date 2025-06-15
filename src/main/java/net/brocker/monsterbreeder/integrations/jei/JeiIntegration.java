package net.brocker.monsterbreeder.integrations.jei;

public class JeiIntegration {
	private static boolean enabled = false;

	static {
		try {
			Class.forName("mezz.jei.api.JeiPlugin");
			enabled = true;
		} catch (Exception ignored) {}
	}

	public static boolean isEnabled() {
		return enabled;
	}
	public static void initialize() {

	}
}
