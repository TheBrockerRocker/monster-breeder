package net.brocker.monsterbreeder.api;

import net.minecraft.entity.EntityType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Dna {
	protected final String name;
	protected final boolean special;
	protected final Rarity rarity;
	protected final List<EntityType<?>> sourceMobs;

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param special Should the item look enchanted?
	 * @param sourceMob The mob that this DNA can be extracted from.
	 */
	public Dna(String name, boolean special, Rarity rarity, List<EntityType<?>> sourceMobs) {
		this.name = name;
		this.special = special;
		this.rarity = rarity;
		this.sourceMobs = sourceMobs;
	}

	public MutableText getName() {
		return Text.translatable(name);
	}
	public boolean isSpecial() {
		return special;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public List<EntityType<?>> getSourceMobs() {
		return sourceMobs;
	}
}
