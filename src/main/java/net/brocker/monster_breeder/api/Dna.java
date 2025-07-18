package net.brocker.monster_breeder.api;

import net.minecraft.entity.EntityType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

@ApiStatus.AvailableSince("1.0.0")
public class Dna {
	protected final String name;
	protected final Rarity rarity;
	protected final Color color;
	protected final Supplier<Color> colorSupplier;
	protected final Set<EntityType<?>> sourceMobs;
	protected final @Nullable EntityType<?> summonResult;

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param rarity How rare is this DNA
	 * @param color What color is the DNA item?
	 * @param sourceMobs The mobs that this DNA can be extracted from.
	 * @param summonResult What should the DNA altar summon
	 */
	public Dna(@NotNull String name, @NotNull Rarity rarity, @NotNull Color color, @NotNull Set<EntityType<?>> sourceMobs, @Nullable EntityType<?> summonResult) {
		this.name = name;
		this.rarity = rarity;
		this.color = color;
		this.colorSupplier = null;
		this.sourceMobs = Collections.unmodifiableSet(sourceMobs);
		this.summonResult = summonResult;
	}

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param rarity How rare is this DNA
	 * @param color What color is the DNA item?
	 * @param sourceMobs The mobs that this DNA can be extracted from.
	 * @param summonResult What should the DNA altar summon
	 */
	public Dna(@NotNull String name, @NotNull Rarity rarity, @NotNull Supplier<@NotNull Color> color, @NotNull Set<EntityType<?>> sourceMobs, @Nullable EntityType<?> summonResult) {
		this.name = name;
		this.rarity = rarity;
		this.color = null;
		this.colorSupplier = color;
		this.sourceMobs = Collections.unmodifiableSet(sourceMobs);
		this.summonResult = summonResult;
	}

	public @NotNull MutableText getName() {
		return Text.translatable(name);
	}
	public @NotNull Rarity getRarity() {
		return rarity;
	}
	public @NotNull Color getColor() {
		return colorSupplier != null ? colorSupplier.get() : color;
	}
	public @NotNull Set<EntityType<?>> getSourceMobs() {
		return sourceMobs;
	}
	public @Nullable EntityType<?> getSummonResult() {
		return summonResult;
	}

	public record Color(int color1, int color2, int color3, int color4) {
		public static Color create(int color1, int color2, int color3, int color4) {
			return new Color(color1, color2, color3, color4);
		}
		public static Color create(int color) {
			return create(color, color, color, color);
		}

		/**
		 * Create a new DNA color
		 * @param color1 A hex code (including the hashtag)
		 * @param color2 A hex code (including the hashtag)
		 * @param color3 A hex code (including the hashtag)
		 * @param color4 A hex code (including the hashtag)
		 * @return A new DNA color
		 */
		public static Color create(String color1, String color2, String color3, String color4) {
			return create(hexCode(color1), hexCode(color2), hexCode(color3), hexCode(color4));
		}
		/**
		 * Create a new DNA color
		 * @param color A hex code (including the hashtag)
		 * @return A new DNA color
		 */
		public static Color create(String color) {
			return create(hexCode(color));
		}

		private static int hexCode(String hexColor) {
			int color = Integer.parseInt(hexColor.substring(1), 16);

			if (hexColor.length() == 7) {
				color |= 0xFF000000;
			}

			return color;
		}
	}
}
