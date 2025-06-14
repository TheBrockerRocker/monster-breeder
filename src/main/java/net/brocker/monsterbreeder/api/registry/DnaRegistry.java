package net.brocker.monsterbreeder.api.registry;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.Dna;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DnaRegistry {
	public static DnaRegistry INSTANCE = new DnaRegistry();

	private final Map<Identifier, Dna> registries = new HashMap<>();

	private DnaRegistry() {}

	public void register(@NotNull Identifier identifier, @NotNull Dna registryObject) {
		if (registries.containsKey(identifier)) throw new RuntimeException(String.format("Duplicate DNA type of %s!", identifier));

		MonsterBreeder.LOGGER.info("Registering DNA type of {}", identifier);
		registries.put(Objects.requireNonNull(identifier), Objects.requireNonNull(registryObject));
	}

	public @Nullable Dna getValue(Identifier identifier) {
		return registries.get(identifier);
	}
	public @Nullable Identifier getKey(Dna registryObject) {
		Map.Entry<Identifier, Dna> entry = registries.entrySet().stream().filter((entry2) -> entry2.getValue() == registryObject).findFirst().orElse(null);
		return entry == null ? null : entry.getKey();
	}

	public Set<Identifier> getKeySet() {
		return registries.keySet();
	}
}
