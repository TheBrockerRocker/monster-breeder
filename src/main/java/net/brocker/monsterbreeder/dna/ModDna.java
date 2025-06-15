package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;

public class ModDna {
	public static Identifier UNKNOWN = Identifier.of(MonsterBreeder.MOD_ID, "unknown");
	public static Identifier ZOMBIE = Identifier.of(MonsterBreeder.MOD_ID, "zombie");
	public static Identifier SKELETON = Identifier.of(MonsterBreeder.MOD_ID, "skeleton");
	public static Identifier CREEPER = Identifier.of(MonsterBreeder.MOD_ID, "creeper");
	public static Identifier BOGGED = Identifier.of(MonsterBreeder.MOD_ID, "bogged");
	public static Identifier BAT = Identifier.of(MonsterBreeder.MOD_ID, "bat");
	public static Identifier FROG = Identifier.of(MonsterBreeder.MOD_ID, "frog");
	public static Identifier SQUID = Identifier.of(MonsterBreeder.MOD_ID, "squid");
	public static Identifier GLOW_SQUID = Identifier.of(MonsterBreeder.MOD_ID, "glow_squid");
	public static Identifier SNOW_GOLEM = Identifier.of(MonsterBreeder.MOD_ID, "snow_golem");
	public static Identifier OCELOT = Identifier.of(MonsterBreeder.MOD_ID, "ocelot");
	public static Identifier SNIFFER = Identifier.of(MonsterBreeder.MOD_ID, "sniffer");
	public static Identifier SKELETON_HORSE = Identifier.of(MonsterBreeder.MOD_ID, "skeleton_horse");
	public static Identifier ARMADILLO = Identifier.of(MonsterBreeder.MOD_ID, "armadillo");
	public static Identifier VILLAGER = Identifier.of(MonsterBreeder.MOD_ID, "villager");
	public static Identifier AXOLOTL = Identifier.of(MonsterBreeder.MOD_ID, "axolotl");
	public static Identifier CAMEL = Identifier.of(MonsterBreeder.MOD_ID, "camel");
	public static Identifier CAT = Identifier.of(MonsterBreeder.MOD_ID, "cat");
	public static Identifier CHICKEN = Identifier.of(MonsterBreeder.MOD_ID, "chicken");
	public static Identifier COD = Identifier.of(MonsterBreeder.MOD_ID, "cod");
	public static Identifier COW = Identifier.of(MonsterBreeder.MOD_ID, "cow");
	public static Identifier DONKEY = Identifier.of(MonsterBreeder.MOD_ID, "donkey");
	public static Identifier MOOSHROOM = Identifier.of(MonsterBreeder.MOD_ID, "mooshroom");
	public static Identifier MULE = Identifier.of(MonsterBreeder.MOD_ID, "mule");
	public static Identifier PARROT = Identifier.of(MonsterBreeder.MOD_ID, "parrot");
	public static Identifier PIG = Identifier.of(MonsterBreeder.MOD_ID, "pig");
	public static Identifier PUFFERFISH = Identifier.of(MonsterBreeder.MOD_ID, "pufferfish");
	public static Identifier RABBIT = Identifier.of(MonsterBreeder.MOD_ID, "rabbit");
	public static Identifier SALMON = Identifier.of(MonsterBreeder.MOD_ID, "salmon");
	public static Identifier SHEEP = Identifier.of(MonsterBreeder.MOD_ID, "sheep");
	public static Identifier STRIDER = Identifier.of(MonsterBreeder.MOD_ID, "strider");
	public static Identifier TADPOLE = Identifier.of(MonsterBreeder.MOD_ID, "tadpole");
	public static Identifier TROPICAL_FISH = Identifier.of(MonsterBreeder.MOD_ID, "tropical_fish");
	public static Identifier TURTLE = Identifier.of(MonsterBreeder.MOD_ID, "turtle");
	public static Identifier WANDERING_TRADER = Identifier.of(MonsterBreeder.MOD_ID, "wandering_trader");
	public static Identifier BEE = Identifier.of(MonsterBreeder.MOD_ID, "bee");
	public static Identifier CAVE_SPIDER = Identifier.of(MonsterBreeder.MOD_ID, "cave_spider");
	public static Identifier DOLPHIN = Identifier.of(MonsterBreeder.MOD_ID, "dolphin");
	public static Identifier DROWNED = Identifier.of(MonsterBreeder.MOD_ID, "drowned");
	public static Identifier ENDERMAN = Identifier.of(MonsterBreeder.MOD_ID, "enderman");
	public static Identifier FOX = Identifier.of(MonsterBreeder.MOD_ID, "fox");
	public static Identifier GOAT = Identifier.of(MonsterBreeder.MOD_ID, "goat");
	public static Identifier IRON_GOLEM = Identifier.of(MonsterBreeder.MOD_ID, "iron_golem");
	public static Identifier LLAMA = Identifier.of(MonsterBreeder.MOD_ID, "llama");
	public static Identifier PANDA = Identifier.of(MonsterBreeder.MOD_ID, "panda");
	public static Identifier POLAR_BEAR = Identifier.of(MonsterBreeder.MOD_ID, "polar_bear");
	public static Identifier TRADER_LLAMA = Identifier.of(MonsterBreeder.MOD_ID, "trader_llama");
	public static Identifier WOLF = Identifier.of(MonsterBreeder.MOD_ID, "wolf");
	public static Identifier ZOMBIFIED_PIGLIN = Identifier.of(MonsterBreeder.MOD_ID, "zombified_piglin");
	public static Identifier BLAZE = Identifier.of(MonsterBreeder.MOD_ID, "blaze");
	public static Identifier BREEZE = Identifier.of(MonsterBreeder.MOD_ID, "breeze");
	public static Identifier ELDER_GUARDIAN = Identifier.of(MonsterBreeder.MOD_ID, "elder_guardian");
	public static Identifier ENDERMITE = Identifier.of(MonsterBreeder.MOD_ID, "endermite");
	public static Identifier EVOKER = Identifier.of(MonsterBreeder.MOD_ID, "evoker");
	public static Identifier GHAST = Identifier.of(MonsterBreeder.MOD_ID, "ghast");
	public static Identifier GUARDIAN = Identifier.of(MonsterBreeder.MOD_ID, "guardian");
	public static Identifier HOGLIN = Identifier.of(MonsterBreeder.MOD_ID, "hoglin");
	public static Identifier HUSK = Identifier.of(MonsterBreeder.MOD_ID, "husk");
	public static Identifier MAGMA_CUBE = Identifier.of(MonsterBreeder.MOD_ID, "magma_cube");
	public static Identifier PHANTOM = Identifier.of(MonsterBreeder.MOD_ID, "phantom");
	public static Identifier PIGLIN_BRUTE = Identifier.of(MonsterBreeder.MOD_ID, "piglin_brute");
	public static Identifier PILLAGER = Identifier.of(MonsterBreeder.MOD_ID, "pillager");
	public static Identifier RAVAGER = Identifier.of(MonsterBreeder.MOD_ID, "ravager");
	public static Identifier SHULKER = Identifier.of(MonsterBreeder.MOD_ID, "shulker");
	public static Identifier SILVERFISH = Identifier.of(MonsterBreeder.MOD_ID, "silverfish");
	public static Identifier SLIME = Identifier.of(MonsterBreeder.MOD_ID, "slime");
	public static Identifier STRAY = Identifier.of(MonsterBreeder.MOD_ID, "stray");
	public static Identifier VEX = Identifier.of(MonsterBreeder.MOD_ID, "vex");
	public static Identifier VINDICATOR = Identifier.of(MonsterBreeder.MOD_ID, "vindicator");
	public static Identifier WARDEN = Identifier.of(MonsterBreeder.MOD_ID, "warden");
	public static Identifier WITCH = Identifier.of(MonsterBreeder.MOD_ID, "witch");
	public static Identifier WITHER_SKELETON = Identifier.of(MonsterBreeder.MOD_ID, "wither_skeleton");
	public static Identifier ZOGLIN = Identifier.of(MonsterBreeder.MOD_ID, "zoglin");
	public static Identifier ZOMBIE_VILLAGER = Identifier.of(MonsterBreeder.MOD_ID, "zombie_villager");


	public static void registerModDna() {
		MonsterBreeder.LOGGER.info("Registering ModDna for " + MonsterBreeder.MOD_ID);

		DnaRegistry registry = DnaRegistry.INSTANCE;
		registry.register(UNKNOWN, new Dna("dna.monsterbreeder.unknown", false, Rarity.COMMON, new ArrayList<>()));
		registry.register(ZOMBIE, DnaBuilder
				.create(EntityType.ZOMBIE.getTranslationKey())
				.addSourceMob(EntityType.ZOMBIE)
				.build()
		);
		registry.register(SKELETON, DnaBuilder
				.create(EntityType.SKELETON.getTranslationKey())
				.addSourceMob(EntityType.SKELETON)
				.build()
		);
		registry.register(CREEPER, DnaBuilder
				.create(EntityType.CREEPER.getTranslationKey())
				.addSourceMob(EntityType.CREEPER)
				.build()
		);
		registry.register(BAT, DnaBuilder
				.create(EntityType.BAT.getTranslationKey())
				.addSourceMob(EntityType.BAT)
				.build()
		);
		registry.register(FROG, DnaBuilder
				.create(EntityType.FROG.getTranslationKey())
				.addSourceMob(EntityType.FROG)
				.build()
		);
		registry.register(SQUID, DnaBuilder
				.create(EntityType.SQUID.getTranslationKey())
				.addSourceMob(EntityType.SQUID)
				.build()
		);
		registry.register(GLOW_SQUID, DnaBuilder
				.create(EntityType.GLOW_SQUID.getTranslationKey())
				.addSourceMob(EntityType.GLOW_SQUID)
				.build()
		);
		registry.register(BOGGED, DnaBuilder
				.create(EntityType.BOGGED.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.BOGGED)
				.build()
		);
		registry.register(SNOW_GOLEM, DnaBuilder
				.create(EntityType.SNOW_GOLEM.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.SNOW_GOLEM)
				.build()
		);
		registry.register(OCELOT, DnaBuilder
				.create(EntityType.OCELOT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.OCELOT)
				.build()
		);
		registry.register(SNIFFER, DnaBuilder
				.create(EntityType.SNIFFER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.SNIFFER)
				.build()
		);
		registry.register(SKELETON_HORSE, DnaBuilder
				.create(EntityType.SKELETON_HORSE.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.SKELETON_HORSE)
				.build()
		);
		registry.register(ARMADILLO, DnaBuilder
				.create(EntityType.ARMADILLO.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.ARMADILLO)
				.build()
		);
		registry.register(VILLAGER, DnaBuilder
				.create(EntityType.VILLAGER.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.VILLAGER)
				.build()
		);
		registry.register(AXOLOTL, DnaBuilder
				.create(EntityType.AXOLOTL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMob(EntityType.AXOLOTL)
				.build()
		);
		registry.register(CAMEL, DnaBuilder
				.create(EntityType.CAMEL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.CAMEL)
				.build()
		);
		registry.register(CAT, DnaBuilder
				.create(EntityType.CAT.getTranslationKey())
				.addSourceMob(EntityType.CAT)
				.build()
		);
		registry.register(CHICKEN, DnaBuilder
				.create(EntityType.CHICKEN.getTranslationKey())
				.addSourceMob(EntityType.CHICKEN)
				.build()
		);
		registry.register(COD, DnaBuilder
				.create(EntityType.COD.getTranslationKey())
				.addSourceMob(EntityType.COD)
				.build()
		);
		registry.register(COW, DnaBuilder
				.create(EntityType.COW.getTranslationKey())
				.addSourceMob(EntityType.COW)
				.build()
		);
		registry.register(DONKEY, DnaBuilder
				.create(EntityType.DONKEY.getTranslationKey())
				.addSourceMob(EntityType.DONKEY)
				.build()
		);
		registry.register(MOOSHROOM, DnaBuilder
				.create(EntityType.MOOSHROOM.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.MOOSHROOM)
				.build()
		);
		registry.register(MULE, DnaBuilder
				.create(EntityType.MULE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.MULE)
				.build()
		);
		registry.register(PARROT, DnaBuilder
				.create(EntityType.PARROT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.PARROT)
				.build()
		);
		registry.register(PIG, DnaBuilder
				.create(EntityType.PIG.getTranslationKey())
				.addSourceMob(EntityType.PIG)
				.build()
		);
		registry.register(PUFFERFISH, DnaBuilder
				.create(EntityType.PUFFERFISH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.PUFFERFISH)
				.build()
		);
		registry.register(RABBIT, DnaBuilder
				.create(EntityType.RABBIT.getTranslationKey())
				.addSourceMob(EntityType.RABBIT)
				.build()
		);
		registry.register(SALMON, DnaBuilder
				.create(EntityType.SALMON.getTranslationKey())
				.addSourceMob(EntityType.SALMON)
				.build()
		);
		registry.register(SHEEP, DnaBuilder
				.create(EntityType.SHEEP.getTranslationKey())
				.addSourceMob(EntityType.SHEEP)
				.build()
		);
		registry.register(STRIDER, DnaBuilder
				.create(EntityType.STRIDER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.STRIDER)
				.build()
		);
		registry.register(TADPOLE, DnaBuilder
				.create(EntityType.TADPOLE.getTranslationKey())
				.addSourceMob(EntityType.TADPOLE)
				.build()
		);
		registry.register(TROPICAL_FISH, DnaBuilder
				.create(EntityType.TROPICAL_FISH.getTranslationKey())
				.addSourceMob(EntityType.TROPICAL_FISH)
				.build()
		);
		registry.register(TURTLE, DnaBuilder
				.create(EntityType.TURTLE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.TURTLE)
				.build()
		);
		// FIXME: Can't extract blood from wandering trader, opens trading GUI instead
		registry.register(WANDERING_TRADER, DnaBuilder
				.create(EntityType.WANDERING_TRADER.getTranslationKey())
				.addSourceMob(EntityType.WANDERING_TRADER)
				.build()
		);
		registry.register(BEE, DnaBuilder
				.create(EntityType.BEE.getTranslationKey())
				.addSourceMob(EntityType.BEE)
				.build()
		);
		registry.register(CAVE_SPIDER, DnaBuilder
				.create(EntityType.CAVE_SPIDER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.CAVE_SPIDER)
				.build()
		);
		registry.register(DOLPHIN, DnaBuilder
				.create(EntityType.DOLPHIN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.DOLPHIN)
				.build()
		);
		registry.register(DROWNED, DnaBuilder
				.create(EntityType.DROWNED.getTranslationKey())
				.addSourceMob(EntityType.DROWNED)
				.build()
		);
		registry.register(ENDERMAN, DnaBuilder
				.create(EntityType.ENDERMAN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMob(EntityType.ENDERMAN)
				.build()
		);
		registry.register(FOX, DnaBuilder
				.create(EntityType.FOX.getTranslationKey())
				.addSourceMob(EntityType.FOX)
				.build()
		);
		registry.register(GOAT, DnaBuilder
				.create(EntityType.GOAT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.setSpecial()
				.addSourceMob(EntityType.GOAT)
				.build()
		);
		registry.register(IRON_GOLEM, DnaBuilder
				.create(EntityType.IRON_GOLEM.getTranslationKey())
				.setSpecial()
				.addSourceMob(EntityType.IRON_GOLEM)
				.build()
		);
		registry.register(LLAMA, DnaBuilder
				.create(EntityType.LLAMA.getTranslationKey())
				.addSourceMob(EntityType.LLAMA)
				.addSourceMob(EntityType.TRADER_LLAMA)
				.build()
		);
		registry.register(PANDA, DnaBuilder
				.create(EntityType.PANDA.getTranslationKey())
				.setSpecial()
				.addSourceMob(EntityType.PANDA)
				.build()
		);
		registry.register(POLAR_BEAR, DnaBuilder
				.create(EntityType.POLAR_BEAR.getTranslationKey())
				.addSourceMob(EntityType.POLAR_BEAR)
				.build()
		);
		registry.register(WOLF, DnaBuilder
				.create(EntityType.WOLF.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.setSpecial()
				.addSourceMob(EntityType.WOLF)
				.build()
		);
		registry.register(ZOMBIFIED_PIGLIN, DnaBuilder
				.create(EntityType.ZOMBIFIED_PIGLIN.getTranslationKey())
				.addSourceMob(EntityType.ZOMBIFIED_PIGLIN)
				.build()
		);
		registry.register(BLAZE, DnaBuilder
				.create(EntityType.BLAZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.BLAZE)
				.build()
		);
		registry.register(BREEZE, DnaBuilder
				.create(EntityType.BREEZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.BREEZE)
				.build()
		);
		registry.register(ELDER_GUARDIAN, DnaBuilder
				.create(EntityType.ELDER_GUARDIAN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.ELDER_GUARDIAN)
				.build()
		);
		registry.register(ENDERMITE, DnaBuilder
				.create(EntityType.ENDERMITE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.ENDERMITE)
				.build()
		);
		registry.register(EVOKER, DnaBuilder
				.create(EntityType.EVOKER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.EVOKER)
				.build()
		);
		registry.register(GHAST, DnaBuilder
				.create(EntityType.GHAST.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.GHAST)
				.build()
		);
		registry.register(GUARDIAN, DnaBuilder
				.create(EntityType.GUARDIAN.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.GUARDIAN)
				.build()
		);
		registry.register(HOGLIN, DnaBuilder
				.create(EntityType.HOGLIN.getTranslationKey())
				.addSourceMob(EntityType.HOGLIN)
				.build()
		);
		registry.register(HUSK, DnaBuilder
				.create(EntityType.HUSK.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.HUSK)
				.build()
		);
		registry.register(MAGMA_CUBE, DnaBuilder
				.create(EntityType.MAGMA_CUBE.getTranslationKey())
				.addSourceMob(EntityType.MAGMA_CUBE)
				.build()
		);
		registry.register(PHANTOM, DnaBuilder
				.create(EntityType.PHANTOM.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.setSpecial()
				.addSourceMob(EntityType.PHANTOM)
				.build()
		);
		registry.register(PIGLIN_BRUTE, DnaBuilder
				.create(EntityType.PIGLIN_BRUTE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.PIGLIN_BRUTE)
				.build()
		);
		registry.register(PILLAGER, DnaBuilder
				.create(EntityType.PILLAGER.getTranslationKey())
				.addSourceMob(EntityType.PILLAGER)
				.build()
		);
		registry.register(RAVAGER, DnaBuilder
				.create(EntityType.RAVAGER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.RAVAGER)
				.build()
		);
		registry.register(SHULKER, DnaBuilder
				.create(EntityType.SHULKER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.SHULKER)
				.build()
		);
		registry.register(SILVERFISH, DnaBuilder
				.create(EntityType.SILVERFISH.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.SILVERFISH)
				.build()
		);
		registry.register(SLIME, DnaBuilder
				.create(EntityType.SLIME.getTranslationKey())
				.addSourceMob(EntityType.SLIME)
				.build()
		);
		registry.register(STRAY, DnaBuilder
				.create(EntityType.STRAY.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.STRAY)
				.build()
		);
		registry.register(VEX, DnaBuilder
				.create(EntityType.VEX.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.VEX)
				.build()
		);
		registry.register(VINDICATOR, DnaBuilder
				.create(EntityType.VINDICATOR.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMob(EntityType.VINDICATOR)
				.build()
		);
		registry.register(WARDEN, DnaBuilder
				.create(EntityType.WARDEN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.WANDERING_TRADER)
				.build()
		);
		registry.register(WITCH, DnaBuilder
				.create(EntityType.WITCH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.setSpecial()
				.addSourceMob(EntityType.WITCH)
				.build()
		);
		registry.register(WITHER_SKELETON, DnaBuilder
				.create(EntityType.WITHER_SKELETON.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.setSpecial()
				.addSourceMob(EntityType.WITHER_SKELETON)
				.build()
		);
		registry.register(ZOGLIN, DnaBuilder
				.create(EntityType.ZOGLIN.getTranslationKey())
				.addSourceMob(EntityType.ZOGLIN)
				.build()
		);
		registry.register(ZOMBIE_VILLAGER, DnaBuilder
				.create(EntityType.ZOMBIE_VILLAGER.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMob(EntityType.ZOMBIE_VILLAGER)
				.build()
		);
	}
}
