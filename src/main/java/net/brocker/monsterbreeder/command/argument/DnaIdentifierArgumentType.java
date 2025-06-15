package net.brocker.monsterbreeder.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.minecraft.command.argument.IdentifierArgumentType;

import java.util.concurrent.CompletableFuture;

public class DnaIdentifierArgumentType extends IdentifierArgumentType {
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		DnaRegistry.INSTANCE.getKeySet().forEach((id) -> builder.suggest(id.toString()));
		return builder.buildFuture();
	}
}
