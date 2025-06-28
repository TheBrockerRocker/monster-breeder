package net.brocker.monster_breeder.api;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

public class SuppliedDna extends Dna {
	protected final Supplier<Color> colorSupplier;
	protected final Supplier<@Nullable EntityType<?>> summonResultSupplier;

	/**
	 * Creates a new DNA type.
	 * @param name A translatable string
	 * @param rarity How rare is this DNA
	 * @param colorSupplier What color is the DNA item?
	 * @param sourceMobs The mobs that this DNA can be extracted from.
	 * @param summonResultSupplier What should the DNA altar summon
	 */
	public SuppliedDna(String name, Rarity rarity, @NotNull Supplier<Color> colorSupplier, Set<EntityType<?>> sourceMobs, @NotNull Supplier<@Nullable EntityType<?>> summonResultSupplier) {
		super(name, rarity, null, sourceMobs, null);
		this.colorSupplier = colorSupplier;
		this.summonResultSupplier = summonResultSupplier;
	}

	@Override
	public Color getColor() {
		return colorSupplier.get();
	}

	@Override
	public @Nullable EntityType<?> getSummonResult() {
		return summonResultSupplier.get();
	}
}
