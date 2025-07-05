package net.brocker.monster_breeder;

import eu.midnightdust.lib.config.MidnightConfig;
import net.brocker.monster_breeder.api.registry.MonsterBreederRegistries;
import net.brocker.monster_breeder.block.ModBlocks;
import net.brocker.monster_breeder.block.dispenser.DnaExtractorDispenserBehavior;
import net.brocker.monster_breeder.block.dispenser.FlintAndSteelDispenserBehavior;
import net.brocker.monster_breeder.block.dispenser.SyringeDispenserBehavior;
import net.brocker.monster_breeder.blockentity.ModBlockEntities;
import net.brocker.monster_breeder.command.FusionTestCommand;
import net.brocker.monster_breeder.command.argument.DnaIdentifierArgumentType;
import net.brocker.monster_breeder.component.ModComponents;
import net.brocker.monster_breeder.config.ModConfig;
import net.brocker.monster_breeder.dna.ModDna;
import net.brocker.monster_breeder.dna.VanillaDna;
import net.brocker.monster_breeder.entity.ModEntities;
import net.brocker.monster_breeder.entity.custom.ZombieCreeperEntity;
import net.brocker.monster_breeder.item.ModItems;
import net.brocker.monster_breeder.recipe.ModRecipes;
import net.brocker.monster_breeder.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonsterBreeder implements ModInitializer{
    public static final String MOD_ID = "monster_breeder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static @Nullable MinecraftServer server = null;

    @Override
    public void onInitialize() {
        MonsterBreederRegistries.initialize();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();
        ModComponents.registerModComponents();
        ModEntities.registerModEntities();
        ModRecipes.registerRecipes();
        ModDna.registerModDna();

        VanillaDna.registerVanillaDna();

        addItemsToItemGroups();
        addMobSpawns();
        addDefaultAttributes();
        addCommandArgumentTypes();
        addCommands();
        addDispenserBehavior();

        listenToServerLifecycleEvents();
        listenToEntityEvents();

        MidnightConfig.init(MOD_ID, ModConfig.class);
    }

    private void addItemsToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.DNA_ALTAR);
            entries.add(ModBlocks.CENTRIFUGE);
            entries.add(ModBlocks.BIO_REACTION_CHAMBER);
            entries.add(ModBlocks.GROWTH_CHAMBER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.DNA_EXTRACTOR);
            entries.add(ModItems.SYRINGE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ModItems.ENDER_CREEPER_SPAWN_EGG);
            entries.add(ModItems.ZOMBIE_CREEPER_SPAWN_EGG);
        });
    }

    private void addMobSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.MONSTER, ModEntities.ENDER_CREEPER, 3, 1, 2);
    }

    private void addDefaultAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.ENDER_CREEPER, CreeperEntity.createCreeperAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIE_CREEPER, ZombieCreeperEntity.createZombieCreeperAttributes());
    }

    private void addCommandArgumentTypes() {
        ArgumentTypeRegistry.registerArgumentType(
                identifier("dna_identifier"),
                DnaIdentifierArgumentType.class,
                ConstantArgumentSerializer.of(DnaIdentifierArgumentType::new)
        );
    }

    private void addCommands() {
        CommandRegistrationCallback.EVENT.register(FusionTestCommand::register);
    }

    private void addDispenserBehavior() {
        DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new FlintAndSteelDispenserBehavior());
        DispenserBlock.registerBehavior(ModItems.DNA_EXTRACTOR, new DnaExtractorDispenserBehavior());
        DispenserBlock.registerBehavior(ModItems.SYRINGE, new SyringeDispenserBehavior());
        DispenserBlock.registerBehavior(ModItems.USED_SYRINGE, new SyringeDispenserBehavior());
    }

    private void listenToServerLifecycleEvents() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            MonsterBreeder.server = server;

            server.getRegistryManager()
                    .get(MonsterBreederRegistries.DNA_REGISTRY_KEY)
                    .getKeys()
                    .forEach(key -> LOGGER.info("DNA of type {} registered!", key.getValue()));
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> MonsterBreeder.server = null);
    }

    private void listenToEntityEvents() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            boolean isValidItem = stack.isOf(ModItems.SYRINGE) || stack.isOf(ModItems.USED_SYRINGE) || stack.isOf(ModItems.DNA_EXTRACTOR);
            boolean isLiving = entity instanceof LivingEntity;
            boolean isServer = !world.isClient;

            return isValidItem && isLiving && isServer && hitResult == null
                    ? stack.getItem().useOnEntity(stack, player, (LivingEntity) entity, hand)
                    : ActionResult.PASS;
        });
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}