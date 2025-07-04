package net.brocker.monster_breeder.api.util;

import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.registry.MonsterBreederRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@ApiStatus.AvailableSince("1.0.0")
public class DnaBuilder {
	protected final String name;
	protected Rarity rarity = Rarity.COMMON;
	protected Dna.Color color = Dna.Color.create("#ffffff");
	protected Supplier<Dna.Color> colorSupplier = null;
	protected final Set<EntityType<?>> sourceMobs = new HashSet<>();
	protected @Nullable EntityType<?> summonResult = null;

	protected DnaBuilder(String name) {
		this.name = name;
	}

	/**
	 * Creates a new DNA builder.
	 * @param name A translatable string
	 */
	public static DnaBuilder create(String name) {
		return new DnaBuilder(name);
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

	/**
	 * Sets a new DNA color
	 * @param color A hex code (including the hashtag)
	 * @return The current DNA builder
	 */
	public DnaBuilder setColor(String color) {
		this.color = Dna.Color.create(color);
		return this;
	}

	/**
	 * Sets a new DNA color
	 * @param color A supplier that return the color of this DNA
	 * @return The current DNA builder
	 */
	public DnaBuilder setColorSupplier(Supplier<Dna.Color> color) {
		this.colorSupplier = color;
		return this;
	}

	/**
	 * Add a new source mob for this DNA type.
	 * @param sourceMob The mob to get this DNA from
	 * @return The current DNA builder
	 */
	public DnaBuilder addSourceMob(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		return this;
	}

	/**
	 * Add a new source mob for this DNA type. Also sets what mob is summoned when using the DNA Altar.
	 * @param sourceMob The mob to get this DNA from, and the mob to spawn with the DNA Altar
	 * @return The current DNA builder
	 */
	public DnaBuilder addSourceMobAsSummonResult(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		this.summonResult = sourceMob;
		return this;
	}

	/**
	 * Sets what mob is summoned when using the DNA Altar.
	 * @param summonResult The mob to spawn with the DNA altar
	 * @return The current DNA builder
	 */
	public DnaBuilder setSummonResult(EntityType<?> summonResult) {
		this.summonResult = summonResult;
		return this;
	}

	public Dna build() {
		return colorSupplier != null
				? new Dna(name, rarity, colorSupplier, sourceMobs, summonResult)
				: new Dna(name, rarity, color, sourceMobs, summonResult);
	}

	public void buildAndRegister(Identifier identifier) {
		Registry.register(MonsterBreederRegistries.DNA, identifier, build());
	}
}
