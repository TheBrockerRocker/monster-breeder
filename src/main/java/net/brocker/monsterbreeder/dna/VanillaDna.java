package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.api.Dna;
import net.brocker.monsterbreeder.api.registry.DnaRegistry;
import net.brocker.monsterbreeder.api.util.DnaBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;

public class VanillaDna {
	public static Identifier UNKNOWN = Identifier.ofVanilla("unknown");
	public static Identifier ZOMBIE = Identifier.ofVanilla("zombie");
	public static Identifier SKELETON = Identifier.ofVanilla("skeleton");
	public static Identifier CREEPER = Identifier.ofVanilla("creeper");
	public static Identifier SPIDER = Identifier.ofVanilla("spider");
	public static Identifier BOGGED = Identifier.ofVanilla("bogged");
	public static Identifier BAT = Identifier.ofVanilla("bat");
	public static Identifier FROG = Identifier.ofVanilla("frog");
	public static Identifier SQUID = Identifier.ofVanilla("squid");
	public static Identifier GLOW_SQUID = Identifier.ofVanilla("glow_squid");
	public static Identifier SNOW_GOLEM = Identifier.ofVanilla("snow_golem");
	public static Identifier OCELOT = Identifier.ofVanilla("ocelot");
	public static Identifier SNIFFER = Identifier.ofVanilla("sniffer");
	public static Identifier HORSE = Identifier.ofVanilla("horse");
	public static Identifier SKELETON_HORSE = Identifier.ofVanilla("skeleton_horse");
	public static Identifier ARMADILLO = Identifier.ofVanilla("armadillo");
	public static Identifier VILLAGER = Identifier.ofVanilla("villager");
	public static Identifier AXOLOTL = Identifier.ofVanilla("axolotl");
	public static Identifier CAMEL = Identifier.ofVanilla("camel");
	public static Identifier CAT = Identifier.ofVanilla("cat");
	public static Identifier CHICKEN = Identifier.ofVanilla("chicken");
	public static Identifier COD = Identifier.ofVanilla("cod");
	public static Identifier COW = Identifier.ofVanilla("cow");
	public static Identifier DONKEY = Identifier.ofVanilla("donkey");
	public static Identifier MOOSHROOM = Identifier.ofVanilla("mooshroom");
	public static Identifier MULE = Identifier.ofVanilla("mule");
	public static Identifier PARROT = Identifier.ofVanilla("parrot");
	public static Identifier PIG = Identifier.ofVanilla("pig");
	public static Identifier PUFFERFISH = Identifier.ofVanilla("pufferfish");
	public static Identifier RABBIT = Identifier.ofVanilla("rabbit");
	public static Identifier SALMON = Identifier.ofVanilla("salmon");
	public static Identifier SHEEP = Identifier.ofVanilla("sheep");
	public static Identifier STRIDER = Identifier.ofVanilla("strider");
	public static Identifier TADPOLE = Identifier.ofVanilla("tadpole");
	public static Identifier TROPICAL_FISH = Identifier.ofVanilla("tropical_fish");
	public static Identifier TURTLE = Identifier.ofVanilla("turtle");
	public static Identifier WANDERING_TRADER = Identifier.ofVanilla("wandering_trader");
	public static Identifier BEE = Identifier.ofVanilla("bee");
	public static Identifier CAVE_SPIDER = Identifier.ofVanilla("cave_spider");
	public static Identifier DOLPHIN = Identifier.ofVanilla("dolphin");
	public static Identifier DROWNED = Identifier.ofVanilla("drowned");
	public static Identifier ENDERMAN = Identifier.ofVanilla("enderman");
	public static Identifier FOX = Identifier.ofVanilla("fox");
	public static Identifier GOAT = Identifier.ofVanilla("goat");
	public static Identifier IRON_GOLEM = Identifier.ofVanilla("iron_golem");
	public static Identifier LLAMA = Identifier.ofVanilla("llama");
	public static Identifier PANDA = Identifier.ofVanilla("panda");
	public static Identifier POLAR_BEAR = Identifier.ofVanilla("polar_bear");
	public static Identifier WOLF = Identifier.ofVanilla("wolf");
	public static Identifier ZOMBIFIED_PIGLIN = Identifier.ofVanilla("zombified_piglin");
	public static Identifier BLAZE = Identifier.ofVanilla("blaze");
	public static Identifier BREEZE = Identifier.ofVanilla("breeze");
	public static Identifier ELDER_GUARDIAN = Identifier.ofVanilla("elder_guardian");
	public static Identifier ENDERMITE = Identifier.ofVanilla("endermite");
	public static Identifier EVOKER = Identifier.ofVanilla("evoker");
	public static Identifier GHAST = Identifier.ofVanilla("ghast");
	public static Identifier GUARDIAN = Identifier.ofVanilla("guardian");
	public static Identifier HOGLIN = Identifier.ofVanilla("hoglin");
	public static Identifier HUSK = Identifier.ofVanilla("husk");
	public static Identifier MAGMA_CUBE = Identifier.ofVanilla("magma_cube");
	public static Identifier PHANTOM = Identifier.ofVanilla("phantom");
	public static Identifier PIGLIN_BRUTE = Identifier.ofVanilla("piglin_brute");
	public static Identifier PILLAGER = Identifier.ofVanilla("pillager");
	public static Identifier RAVAGER = Identifier.ofVanilla("ravager");
	public static Identifier SHULKER = Identifier.ofVanilla("shulker");
	public static Identifier SILVERFISH = Identifier.ofVanilla("silverfish");
	public static Identifier SLIME = Identifier.ofVanilla("slime");
	public static Identifier STRAY = Identifier.ofVanilla("stray");
	public static Identifier VEX = Identifier.ofVanilla("vex");
	public static Identifier VINDICATOR = Identifier.ofVanilla("vindicator");
	public static Identifier WARDEN = Identifier.ofVanilla("warden");
	public static Identifier WITCH = Identifier.ofVanilla("witch");
	public static Identifier WITHER_SKELETON = Identifier.ofVanilla("wither_skeleton");
	public static Identifier ZOGLIN = Identifier.ofVanilla("zoglin");
	public static Identifier ZOMBIE_VILLAGER = Identifier.ofVanilla("zombie_villager");

	public static void registerVanillaDna() {
		DnaRegistry registry = DnaRegistry.INSTANCE;
		registry.register(UNKNOWN, new Dna(
				"dna.minecraft.unknown",
				Rarity.COMMON,
				Dna.Color.create("#ffffff", "#ffffff", "#ffffff", "#ffffff"),
				new ArrayList<>(),
				null
		));
		registry.register(ZOMBIE, DnaBuilder
				.create(EntityType.ZOMBIE.getTranslationKey())
				.setColor("#06891b", "#06891b", "#2493bc", "#2493bc")
				.addSourceMobAsSummonResult(EntityType.ZOMBIE)
				.build()
		);
		registry.register(SKELETON, DnaBuilder
				.create(EntityType.SKELETON.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SKELETON)
				.build()
		);
		registry.register(CREEPER, DnaBuilder
				.create(EntityType.CREEPER.getTranslationKey())
				.setColor("#43da1b", "#43da1b", "#439f2b", "#439f2b")
				.addSourceMobAsSummonResult(EntityType.CREEPER)
				.build()
		);
		registry.register(SPIDER, DnaBuilder
				.create(EntityType.SPIDER.getTranslationKey())
				.setColor("#d20303", "#d20303", "#3d3636", "#3d3636")
				.addSourceMobAsSummonResult(EntityType.SPIDER)
				.build()
		);
		registry.register(BAT, DnaBuilder
				.create(EntityType.BAT.getTranslationKey())
				.setColor("#a37402", "#a37402", "#6d500a", "#6d500a")
				.addSourceMobAsSummonResult(EntityType.BAT)
				.build()
		);
		registry.register(FROG, DnaBuilder
				.create(EntityType.FROG.getTranslationKey())
				.setColor("#d79c51", "#d79c51", "#bc843b", "#bc843b")
				.addSourceMobAsSummonResult(EntityType.FROG)
				.build()
		);
		registry.register(SQUID, DnaBuilder
				.create(EntityType.SQUID.getTranslationKey())
				.setColor("#6c8ca4", "#6c8ca4", "#496071", "#496071")
				.addSourceMobAsSummonResult(EntityType.SQUID)
				.build()
		);
		registry.register(GLOW_SQUID, DnaBuilder
				.create(EntityType.GLOW_SQUID.getTranslationKey())
				.setColor("#6c8ca4", "#fffff2", "#7efcbe", "#496071")
				.addSourceMobAsSummonResult(EntityType.GLOW_SQUID)
				.build()
		);
		registry.register(BOGGED, DnaBuilder
				.create(EntityType.BOGGED.getTranslationKey())
				.setColor("#dadada", "#1ac644", "#129e34", "#dadada")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BOGGED)
				.build()
		);
		registry.register(SNOW_GOLEM, DnaBuilder
				.create(EntityType.SNOW_GOLEM.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.SNOW_GOLEM)
				.build()
		);
		registry.register(OCELOT, DnaBuilder
				.create(EntityType.OCELOT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.OCELOT)
				.build()
		);
		registry.register(SNIFFER, DnaBuilder
				.create(EntityType.SNIFFER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.SNIFFER)
				.build()
		);
		registry.register(HORSE, DnaBuilder
				.create(EntityType.HORSE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.HORSE)
				.build()
		);
		registry.register(SKELETON_HORSE, DnaBuilder
				.create(EntityType.SKELETON_HORSE.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.SKELETON_HORSE)
				.build()
		);
		registry.register(ARMADILLO, DnaBuilder
				.create(EntityType.ARMADILLO.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.ARMADILLO)
				.build()
		);
		registry.register(VILLAGER, DnaBuilder
				.create(EntityType.VILLAGER.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.VILLAGER)
				.build()
		);
		registry.register(AXOLOTL, DnaBuilder
				.create(EntityType.AXOLOTL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.AXOLOTL)
				.build()
		);
		registry.register(CAMEL, DnaBuilder
				.create(EntityType.CAMEL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.CAMEL)
				.build()
		);
		registry.register(CAT, DnaBuilder
				.create(EntityType.CAT.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.CAT)
				.build()
		);
		registry.register(CHICKEN, DnaBuilder
				.create(EntityType.CHICKEN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.CHICKEN)
				.build()
		);
		registry.register(COD, DnaBuilder
				.create(EntityType.COD.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.COD)
				.build()
		);
		registry.register(COW, DnaBuilder
				.create(EntityType.COW.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.COW)
				.build()
		);
		registry.register(DONKEY, DnaBuilder
				.create(EntityType.DONKEY.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.DONKEY)
				.build()
		);
		registry.register(MOOSHROOM, DnaBuilder
				.create(EntityType.MOOSHROOM.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.MOOSHROOM)
				.build()
		);
		registry.register(MULE, DnaBuilder
				.create(EntityType.MULE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.MULE)
				.build()
		);
		registry.register(PARROT, DnaBuilder
				.create(EntityType.PARROT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.PARROT)
				.build()
		);
		registry.register(PIG, DnaBuilder
				.create(EntityType.PIG.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PIG)
				.build()
		);
		registry.register(PUFFERFISH, DnaBuilder
				.create(EntityType.PUFFERFISH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.PUFFERFISH)
				.build()
		);
		registry.register(RABBIT, DnaBuilder
				.create(EntityType.RABBIT.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.RABBIT)
				.build()
		);
		registry.register(SALMON, DnaBuilder
				.create(EntityType.SALMON.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SALMON)
				.build()
		);
		registry.register(SHEEP, DnaBuilder
				.create(EntityType.SHEEP.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SHEEP)
				.build()
		);
		registry.register(STRIDER, DnaBuilder
				.create(EntityType.STRIDER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.STRIDER)
				.build()
		);
		registry.register(TADPOLE, DnaBuilder
				.create(EntityType.TADPOLE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.TADPOLE)
				.build()
		);
		registry.register(TROPICAL_FISH, DnaBuilder
				.create(EntityType.TROPICAL_FISH.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.TROPICAL_FISH)
				.build()
		);
		registry.register(TURTLE, DnaBuilder
				.create(EntityType.TURTLE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.TURTLE)
				.build()
		);
		// FIXME: Can't extract blood from wandering trader, opens trading GUI instead
		registry.register(WANDERING_TRADER, DnaBuilder
				.create(EntityType.WANDERING_TRADER.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.WANDERING_TRADER)
				.build()
		);
		registry.register(BEE, DnaBuilder
				.create(EntityType.BEE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.BEE)
				.build()
		);
		registry.register(CAVE_SPIDER, DnaBuilder
				.create(EntityType.CAVE_SPIDER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.CAVE_SPIDER)
				.build()
		);
		registry.register(DOLPHIN, DnaBuilder
				.create(EntityType.DOLPHIN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.DOLPHIN)
				.build()
		);
		registry.register(DROWNED, DnaBuilder
				.create(EntityType.DROWNED.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.DROWNED)
				.build()
		);
		registry.register(ENDERMAN, DnaBuilder
				.create(EntityType.ENDERMAN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.ENDERMAN)
				.build()
		);
		registry.register(FOX, DnaBuilder
				.create(EntityType.FOX.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.FOX)
				.build()
		);
		registry.register(GOAT, DnaBuilder
				.create(EntityType.GOAT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.GOAT)
				.build()
		);
		registry.register(IRON_GOLEM, DnaBuilder
				.create(EntityType.IRON_GOLEM.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.IRON_GOLEM)
				.build()
		);
		registry.register(LLAMA, DnaBuilder
				.create(EntityType.LLAMA.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.LLAMA)
				.addSourceMob(EntityType.TRADER_LLAMA)
				.build()
		);
		registry.register(PANDA, DnaBuilder
				.create(EntityType.PANDA.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PANDA)
				.build()
		);
		registry.register(POLAR_BEAR, DnaBuilder
				.create(EntityType.POLAR_BEAR.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.POLAR_BEAR)
				.build()
		);
		registry.register(WOLF, DnaBuilder
				.create(EntityType.WOLF.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.WOLF)
				.build()
		);
		registry.register(ZOMBIFIED_PIGLIN, DnaBuilder
				.create(EntityType.ZOMBIFIED_PIGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.ZOMBIFIED_PIGLIN)
				.build()
		);
		registry.register(BLAZE, DnaBuilder
				.create(EntityType.BLAZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BLAZE)
				.build()
		);
		registry.register(BREEZE, DnaBuilder
				.create(EntityType.BREEZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BREEZE)
				.build()
		);
		registry.register(ELDER_GUARDIAN, DnaBuilder
				.create(EntityType.ELDER_GUARDIAN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.ELDER_GUARDIAN)
				.build()
		);
		registry.register(ENDERMITE, DnaBuilder
				.create(EntityType.ENDERMITE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.ENDERMITE)
				.build()
		);
		registry.register(EVOKER, DnaBuilder
				.create(EntityType.EVOKER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.EVOKER)
				.build()
		);
		registry.register(GHAST, DnaBuilder
				.create(EntityType.GHAST.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.GHAST)
				.build()
		);
		registry.register(GUARDIAN, DnaBuilder
				.create(EntityType.GUARDIAN.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.GUARDIAN)
				.build()
		);
		registry.register(HOGLIN, DnaBuilder
				.create(EntityType.HOGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.HOGLIN)
				.build()
		);
		registry.register(HUSK, DnaBuilder
				.create(EntityType.HUSK.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.HUSK)
				.build()
		);
		registry.register(MAGMA_CUBE, DnaBuilder
				.create(EntityType.MAGMA_CUBE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.MAGMA_CUBE)
				.build()
		);
		registry.register(PHANTOM, DnaBuilder
				.create(EntityType.PHANTOM.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.PHANTOM)
				.build()
		);
		registry.register(PIGLIN_BRUTE, DnaBuilder
				.create(EntityType.PIGLIN_BRUTE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.PIGLIN_BRUTE)
				.build()
		);
		registry.register(PILLAGER, DnaBuilder
				.create(EntityType.PILLAGER.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PILLAGER)
				.build()
		);
		registry.register(RAVAGER, DnaBuilder
				.create(EntityType.RAVAGER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.RAVAGER)
				.build()
		);
		registry.register(SHULKER, DnaBuilder
				.create(EntityType.SHULKER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.SHULKER)
				.build()
		);
		registry.register(SILVERFISH, DnaBuilder
				.create(EntityType.SILVERFISH.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.SILVERFISH)
				.build()
		);
		registry.register(SLIME, DnaBuilder
				.create(EntityType.SLIME.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SLIME)
				.build()
		);
		registry.register(STRAY, DnaBuilder
				.create(EntityType.STRAY.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.STRAY)
				.build()
		);
		registry.register(VEX, DnaBuilder
				.create(EntityType.VEX.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.VEX)
				.build()
		);
		registry.register(VINDICATOR, DnaBuilder
				.create(EntityType.VINDICATOR.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.VINDICATOR)
				.build()
		);
		registry.register(WARDEN, DnaBuilder
				.create(EntityType.WARDEN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.WANDERING_TRADER)
				.build()
		);
		registry.register(WITCH, DnaBuilder
				.create(EntityType.WITCH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.WITCH)
				.build()
		);
		registry.register(WITHER_SKELETON, DnaBuilder
				.create(EntityType.WITHER_SKELETON.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.WITHER_SKELETON)
				.build()
		);
		registry.register(ZOGLIN, DnaBuilder
				.create(EntityType.ZOGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.ZOGLIN)
				.build()
		);
		registry.register(ZOMBIE_VILLAGER, DnaBuilder
				.create(EntityType.ZOMBIE_VILLAGER.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.ZOMBIE_VILLAGER)
				.build()
		);
	}
}
