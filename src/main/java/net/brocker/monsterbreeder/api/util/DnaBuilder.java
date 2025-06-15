package net.brocker.monsterbreeder.api.util;

import net.brocker.monsterbreeder.api.Dna;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class DnaBuilder {
	protected String name;
	protected boolean special = false;
	protected Rarity rarity = Rarity.COMMON;
	protected Dna.Color color = Dna.Color.create("#ffffff", "#ffffff", "#ffffff", "#ffffff");
	protected List<EntityType<?>> sourceMobs = new ArrayList<>();

	/**
	 * Creates a new DNA builder.
	 * @param name A translatable string
	 */
	public DnaBuilder(String name) {
		this.name = name;
	}

	public static DnaBuilder create(String name) {
		return new DnaBuilder(name);
	}

	/**
	 * Makes the DNA special (look enchanted)
	 * @return The current DNA builder
	 */
	public DnaBuilder setSpecial() {
		this.special = true;
		return this;
	}

	/**
	 * Sets the DNA's rarity
	 * @param rarity The rarity of this DNA
	 * @return The current DNA builder
	 */
	public DnaBuilder setRarity(Rarity rarity) {
		this.rarity = rarity;
		return this;
	}

	/**
	 * Sets a new DNA color
	 * @param color1 A hex code (including the hashtag)
	 * @param color2 A hex code (including the hashtag)
	 * @param color3 A hex code (including the hashtag)
	 * @param color4 A hex code (including the hashtag)
	 * @return The current DNA builder
	 */
	public DnaBuilder setColor(String color1, String color2, String color3, String color4) {
		this.color = Dna.Color.create(color1, color2, color3, color4);
		return this;
	}

	public DnaBuilder addSourceMob(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		return this;
	}

	public Dna build() {
		return new Dna(name, special, rarity, color, sourceMobs);
	}
}
