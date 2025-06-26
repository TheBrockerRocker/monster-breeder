package net.brocker.monster_breeder.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.minecraft.command.argument.IdentifierArgumentType;

import java.util.concurrent.CompletableFuture;

public class DnaIdentifierArgumentType extends IdentifierArgumentType {
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		DnaUtil.getRegistry().getKeys().forEach((id) -> builder.suggest(id.getValue().toString()));
		return builder.buildFuture();
	}
}
