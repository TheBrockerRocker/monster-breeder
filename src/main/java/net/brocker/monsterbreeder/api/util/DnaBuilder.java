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

	public DnaBuilder setSpecial() {
		this.special = true;
		return this;
	}

	public DnaBuilder setRarity(Rarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public DnaBuilder addSourceMob(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		return this;
	}

	public Dna build() {
		return new Dna(name, special, rarity, sourceMobs);
	}
}
