package net.brocker.monsterbreeder.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.item.custom.DnaSampleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

public record BioReactionRecipe(Identifier inputItem1, Identifier inputItem2, Identifier output, boolean showNotification, String group) implements Recipe<BioReactionRecipeInput> {
	@Override
	public DefaultedList<Ingredient> getIngredients() {
		return DefaultedList.of();
	}

	@Override
	public boolean matches(BioReactionRecipeInput input, World world) {
		if(world.isClient()) {
			return false;
		}

		return (DnaUtil.getDnaIdentifier(input.getStackInSlot(0)).equals(inputItem1) && DnaUtil.getDnaIdentifier(input.getStackInSlot(1)).equals(inputItem2))
				|| (DnaUtil.getDnaIdentifier(input.getStackInSlot(1)).equals(inputItem1) && DnaUtil.getDnaIdentifier(input.getStackInSlot(0)).equals(inputItem2));
	}

	@Override
	public ItemStack craft(BioReactionRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
		return DnaSampleItem.createItemStack(output);
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
		return DnaSampleItem.createItemStack(output);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipes.BIO_REACTION_SERIALIZER;
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipes.BIO_REACTION_TYPE;
	}

	@Override
	public boolean showNotification() {
		return showNotification;
	}

	@Override
	public String getGroup() {
		return Objects.requireNonNullElse(group, "");
	}

	public static class Serializer implements RecipeSerializer<BioReactionRecipe> {
		public static final MapCodec<BioReactionRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
				Identifier.CODEC.fieldOf("ingredient1").forGetter(BioReactionRecipe::inputItem1),
				Identifier.CODEC.fieldOf("ingredient2").forGetter(BioReactionRecipe::inputItem2),
				Identifier.CODEC.fieldOf("result").forGetter(BioReactionRecipe::output),
				Codec.BOOL.fieldOf("showNotification").forGetter(BioReactionRecipe::showNotification),
				Codec.STRING.fieldOf("group").forGetter(BioReactionRecipe::getGroup)
		).apply(inst, BioReactionRecipe::new));

		public static final PacketCodec<RegistryByteBuf, BioReactionRecipe> STREAM_CODEC =
				PacketCodec.tuple(
						Identifier.PACKET_CODEC, BioReactionRecipe::inputItem1,
						Identifier.PACKET_CODEC, BioReactionRecipe::inputItem2,
						Identifier.PACKET_CODEC, BioReactionRecipe::output,
						PacketCodecs.BOOL, BioReactionRecipe::showNotification,
						PacketCodecs.STRING, BioReactionRecipe::getGroup,
						BioReactionRecipe::new);

		@Override
		public MapCodec<BioReactionRecipe> codec() {
			return CODEC;
		}

		@Override
		public PacketCodec<RegistryByteBuf, BioReactionRecipe> packetCodec() {
			return STREAM_CODEC;
		}
	}
}