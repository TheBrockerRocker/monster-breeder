package net.brocker.monsterbreeder.component;

import com.mojang.serialization.Codec;
import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModDataComponentTypes {
    public static final ComponentType<Identifier> MONSTER_ID;
    public static final ComponentType<Integer> FLUID_COLOR;
    public static final ComponentType<EntityType<?>> MONSTER_TYPE;

    static {
        MONSTER_ID = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(MonsterBreeder.MOD_ID, "monster_id"),
                ComponentType.<Identifier>builder().codec(Identifier.CODEC).build()
        );

        FLUID_COLOR = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(MonsterBreeder.MOD_ID, "fluid_color"),
                ComponentType.<Integer>builder().codec(Codec.INT).build()
        );

        MONSTER_TYPE = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(MonsterBreeder.MOD_ID, "monster_type"),
                ComponentType.<EntityType<?>>builder().codec(Registries.ENTITY_TYPE.getCodec()).build()
        );
    }

    public static void init() {
        // This method is called during mod initialization to ensure static initialization
        MonsterBreeder.LOGGER.info("Initializing Data Component Types for " + MonsterBreeder.MOD_ID);
    }
}