package net.brocker.monster_breeder.datagen;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

abstract class EntityLootTableProvider extends SimpleFabricLootTableProvider {
	protected final Map<RegistryKey<LootTable>, LootTable.Builder> lootTables = new HashMap<>();
	private final Set<Identifier> excludedFromStrictValidation = new HashSet<>();
	private final CompletableFuture<RegistryWrapper.WrapperLookup> futureRegistryLookup;
	private RegistryWrapper.WrapperLookup registryLookup;

	public EntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup, LootContextTypes.ENTITY);
		this.futureRegistryLookup = registryLookup;
	}

	public void excludeFromStrictValidation(EntityType<? extends Entity> entityType) {
		excludedFromStrictValidation.add(Registries.ENTITY_TYPE.getId(entityType));
	}

	public LootTable.Builder drops(ItemConvertible drop, LootNumberProvider count) {
		return LootTable.builder().pool(LootPool.builder()
				.rolls(ConstantLootNumberProvider.create(1.0F))
				.with(
						ItemEntry.builder(drop)
								.apply(SetCountLootFunction.builder(count))
				)
		);
	}

	public LootTable.Builder drops(ItemConvertible drop, LootNumberProvider count, LootNumberProvider lootingBonus) {
		return LootTable.builder().pool(LootPool.builder()
				.rolls(ConstantLootNumberProvider.create(1.0F))
				.with(
						ItemEntry.builder(drop)
								.apply(SetCountLootFunction.builder(count))
								.apply(EnchantedCountIncreaseLootFunction.builder(registryLookup, lootingBonus))
				)
		);
	}

	public void addDrop(EntityType<? extends Entity> entityType, ItemConvertible drop) {
		addDrop(entityType, drop, ConstantLootNumberProvider.create(1.0f));
	}

	public void addDrop(EntityType<? extends Entity> entityType, ItemConvertible drop, LootNumberProvider count) {
		this.lootTables.put(entityType.getLootTableId(), drops(drop, count));
	}

	public void addDropWithLooting(EntityType<? extends Entity> entityType, ItemConvertible drop) {
		addDropWithLooting(entityType, drop, ConstantLootNumberProvider.create(1.0f));
	}

	public void addDropWithLooting(EntityType<? extends Entity> entityType, ItemConvertible drop, LootNumberProvider count) {
		addDropWithLooting(entityType, drop, count, UniformLootNumberProvider.create(0, 1));
	}

	public void addDropWithLooting(EntityType<? extends Entity> entityType, ItemConvertible drop, LootNumberProvider count, LootNumberProvider lootingBonus) {
		this.lootTables.put(entityType.getLootTableId(), drops(drop, count, lootingBonus));
	}

	public abstract void generate(RegistryWrapper.WrapperLookup registryLookup);

	@Override
	public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer) {
		try {
			registryLookup = futureRegistryLookup.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
		generate(registryLookup);

		for (Map.Entry<RegistryKey<LootTable>, LootTable.Builder> entry : lootTables.entrySet()) {
			RegistryKey<LootTable> registryKey = entry.getKey();

			if (registryKey == LootTables.EMPTY) {
				continue;
			}

			biConsumer.accept(registryKey, entry.getValue());
		}

		if (output.isStrictValidationEnabled()) {
			Set<Identifier> missing = Sets.newHashSet();

			for (Identifier entityTypeId : Registries.ENTITY_TYPE.getIds()) {
				if (entityTypeId.getNamespace().equals(output.getModId())) {
					RegistryKey<LootTable> entityLootTableId = Registries.ENTITY_TYPE.get(entityTypeId).getLootTableId();

					if (entityLootTableId.getValue().getNamespace().equals(output.getModId())) {
						if (!lootTables.containsKey(entityLootTableId)) {
							missing.add(entityTypeId);
						}
					}
				}
			}

			missing.removeAll(excludedFromStrictValidation);

			if (!missing.isEmpty()) {
				throw new IllegalStateException("Missing loot table(s) for %s".formatted(missing));
			}
		}
	}

	@Override
	public String getName() {
		return "Entity Loot Tables";
	}
}
