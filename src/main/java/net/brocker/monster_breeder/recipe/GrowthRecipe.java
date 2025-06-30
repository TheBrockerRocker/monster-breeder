package net.brocker.monster_breeder.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.brocker.monster_breeder.api.util.DnaUtil;
import net.brocker.monster_breeder.item.custom.DnaSampleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record GrowthRecipe(Identifier inputItem, Identifier output) implements Recipe<GrowthRecipeInput> {
	@Override
	public DefaultedList<Ingredient> getIngredients() {
		return DefaultedList.of();
	}

	@Override
	public boolean matches(GrowthRecipeInput input, World world) {
		if(world.isClient()) {
			return false;
		}

		return DnaUtil.getDnaIdentifier(input.getStackInSlot(0)).equals(inputItem);
	}

	@Override
	public ItemStack craft(GrowthRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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
		return ModRecipes.GROWTH_SERIALIZER;
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipes.GROWTH_TYPE;
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String getGroup() {
		return "";
	}

	public static class Serializer implements RecipeSerializer<GrowthRecipe> {
		public static final MapCodec<GrowthRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
				Identifier.CODEC.fieldOf("ingredient").forGetter(GrowthRecipe::inputItem),
				Identifier.CODEC.fieldOf("result").forGetter(GrowthRecipe::output)
		).apply(inst, GrowthRecipe::new));

		public static final PacketCodec<RegistryByteBuf, GrowthRecipe> STREAM_CODEC =
				PacketCodec.tuple(
						Identifier.PACKET_CODEC, GrowthRecipe::inputItem,
						Identifier.PACKET_CODEC, GrowthRecipe::output,
						GrowthRecipe::new);

		@Override
		public MapCodec<GrowthRecipe> codec() {
			return CODEC;
		}

		@Override
		public PacketCodec<RegistryByteBuf, GrowthRecipe> packetCodec() {
			return STREAM_CODEC;
		}
	}
}