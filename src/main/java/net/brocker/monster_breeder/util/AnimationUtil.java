package net.brocker.monster_breeder.util;

public class AnimationUtil {
	public static float getAnimationFrame(float tick, float duration) {
		float seconds = tick / 20f;
		return (seconds % duration) / duration;
	}

	public static float getCycledAnimationFrame(float tick, float cycleDuration) {
		float totalDuration = cycleDuration * 2;
		float seconds = tick / 20f;
		float totalProgress = (seconds % totalDuration) / totalDuration;
		float progress = (seconds % cycleDuration) / cycleDuration;
		return totalProgress > 0.5 ? 1 - progress : progress;
	}
}
