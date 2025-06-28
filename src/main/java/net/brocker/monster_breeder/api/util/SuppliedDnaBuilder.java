package net.brocker.monster_breeder.api.util;

import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.SuppliedDna;
import net.brocker.monster_breeder.api.registry.MonsterBreederRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class SuppliedDnaBuilder {
	protected final String name;
	protected Rarity rarity = Rarity.COMMON;
	protected Supplier<Dna.Color> colorSupplier = () -> Dna.Color.create("#ffffff");
	protected final Set<EntityType<?>> sourceMobs = new HashSet<>();
	protected Supplier<@Nullable EntityType<?>> summonResultSupplier = () -> null;

	protected SuppliedDnaBuilder(String name) {
		this.name = name;
	}

	/**
	 * Creates a new supplied DNA builder.
	 * @param name A translatable string
	 */
	public static SuppliedDnaBuilder create(String name) {
		return new SuppliedDnaBuilder(name);
	}

	/**
	 * Sets the DNA's rarity
	 * @param rarity The rarity of this DNA
	 * @return The current DNA builder
	 */
	public SuppliedDnaBuilder setRarity(Rarity rarity) {
		this.rarity = rarity;
		return this;
	}

	/**
	 * Sets a new DNA color
	 * @param colorSupplier A callback that returns a color
	 * @return The current DNA builder
	 */
	public SuppliedDnaBuilder setColorSupplier(Supplier<Dna.Color> colorSupplier) {
		this.colorSupplier = colorSupplier;
		return this;
	}

	/**
	 * Add a new source mob for this DNA type.
	 * @param sourceMob The mob to get this DNA from
	 * @return The current DNA builder
	 */
	public SuppliedDnaBuilder addSourceMob(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		return this;
	}

	/**
	 * Add a new source mob for this DNA type. Also sets what mob is summoned when using the DNA Altar.
	 * @param sourceMob The mob to get this DNA from, and the mob to spawn with the DNA Altar
	 * @return The current DNA builder
	 */
	public SuppliedDnaBuilder addSourceMobAsSummonResult(EntityType<?> sourceMob) {
		this.sourceMobs.add(sourceMob);
		this.summonResultSupplier = () -> sourceMob;
		return this;
	}

	/**
	 * Sets what mob is summoned when using the DNA Altar.
	 * @param summonResultSupplier A callback that returns the mob to spawn with the DNA altar
	 * @return The current DNA builder
	 */
	public SuppliedDnaBuilder setSummonResultSupplier(Supplier<@Nullable EntityType<?>> summonResultSupplier) {
		this.summonResultSupplier = summonResultSupplier;
		return this;
	}

	public Dna build() {
		return new SuppliedDna(name, rarity, colorSupplier, sourceMobs, summonResultSupplier);
	}
	public void buildAndRegister(Identifier identifier) {
		Registry.register(MonsterBreederRegistries.DNA, identifier, build());
	}
}
