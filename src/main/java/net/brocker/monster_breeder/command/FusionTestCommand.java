package net.brocker.monster_breeder.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.brocker.monster_breeder.command.argument.DnaIdentifierArgumentType;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.brocker.monster_breeder.recipe.BioReactionRecipe;
import net.brocker.monster_breeder.recipe.BioReactionRecipeInput;
import net.brocker.monster_breeder.recipe.ModRecipes;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FusionTestCommand {
	/**
	 * Called when the server is registering commands.
	 *
	 * @param dispatcher     the command dispatcher to register commands to
	 * @param registryAccess object exposing access to the game's registries
	 * @param environment    environment the registrations should be done for, used for commands that are dedicated or integrated server only
	 */
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
		dispatcher.register(literal("fusion_test")
				.then(argument("dna1", new DnaIdentifierArgumentType())
								.then(argument("dna2", new DnaIdentifierArgumentType())
										.executes((context) -> {
											Identifier dna1 = IdentifierArgumentType.getIdentifier(context, "dna1");
											Identifier dna2 = IdentifierArgumentType.getIdentifier(context, "dna2");

											Optional<RecipeEntry<BioReactionRecipe>> recipe = getRecipe(context, dna1, dna2);
											if (recipe.isEmpty()) {
												context.getSource().sendError(Text.literal("No possible fusions!"));
											} else {
												context.getSource().sendMessage(
														Text.literal("Possible result of fusion: ")
																.append(DnaSampleItem.createItemStack(recipe.get().value().output()).getName())
												);
											}
											return 1;
										})
								)
				)
		);
	}

	private static Optional<RecipeEntry<BioReactionRecipe>> getRecipe(CommandContext<ServerCommandSource> context, Identifier dna1, Identifier dna2) {
		return context.getSource().getWorld().getRecipeManager().getFirstMatch(
				ModRecipes.BIO_REACTION_TYPE,
				new BioReactionRecipeInput(
						DnaSampleItem.createItemStack(dna1),
						DnaSampleItem.createItemStack(dna2)
				),
				context.getSource().getWorld());
	}
}
