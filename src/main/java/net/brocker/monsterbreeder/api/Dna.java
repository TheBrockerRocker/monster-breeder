package net.brocker.monsterbreeder.api;

import net.minecraft.entity.EntityType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

import java.util.List;

public class Dna {
	protected final String name;
	protected final Rarity rarity;
	protected final Color color;
	protected final List<EntityType<?>> sourceMobs;

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param special Should the item look enchanted?
	 * @param sourceMob The mob that this DNA can be extracted from.
	 */
	public Dna(String name, Rarity rarity, Color color, List<EntityType<?>> sourceMobs) {
		this.name = name;
		this.rarity = rarity;
		this.color = color;
		this.sourceMobs = sourceMobs;
	}

	public MutableText getName() {
		return Text.translatable(name);
	}
	public Rarity getRarity() {
		return rarity;
	}
	public Color getColor() {
		return color;
	}
	public List<EntityType<?>> getSourceMobs() {
		return sourceMobs;
	}

	public record Color(int color1, int color2, int color3, int color4) {
		private static Color create(int color1, int color2, int color3, int color4) {
			return new Color(color1, color2, color3, color4);
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

		private static int hexCode(String hexColor) {
			int color = Integer.parseInt(hexColor.substring(1), 16);

			if (hexColor.length() == 7) {
				color |= 0xFF000000;
			}

			return color;
		}
	}
}
