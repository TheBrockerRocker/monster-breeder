package net.brocker.monsterbreeder.util;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class AdvancementUtil {
	public static AdvancementEntry get(MinecraftServer server, Identifier identifier) {
		return server.getAdvancementLoader().get(identifier);
	}

	public static void grant(ServerPlayerEntity player, AdvancementEntry advancement) {
		AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
		if (progress.isDone()) return;

		for (String criteria: progress.getUnobtainedCriteria()) {
			player.getAdvancementTracker().grantCriterion(advancement, criteria);
		}
	}
}
