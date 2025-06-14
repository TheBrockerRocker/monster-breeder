package net.brocker.monsterbreeder.api;

import net.minecraft.entity.EntityType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class Dna {
	protected final String name;
	protected final boolean special;
	protected final @Nullable EntityType<?> sourceMob;

	/**
	 * Creates a new DNA type with no source mob.
	 * @param name A translatable string
	 * @param special Should the item look enchanted?
	 */
	public Dna(String name, boolean special) {
		this.name = name;
		this.special = special;
		this.sourceMob = null;
	}

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param special Should the item look enchanted?
	 * @param sourceMob The mob that this DNA can be extracted from.
	 */
	public Dna(String name, boolean special, @Nullable EntityType<?> sourceMob) {
		this.name = name;
		this.special = special;
		this.sourceMob = sourceMob;
	}

	public MutableText getName() {
		return Text.translatable(name);
	}
	public boolean isSpecial() {
		return special;
	}
	public @Nullable EntityType<?> getSourceMob() {
		return sourceMob;
	}
}
