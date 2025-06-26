package net.brocker.monsterbreeder.dna;

import net.brocker.monsterbreeder.api.util.DnaBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class VanillaDna {
	public static final Identifier ZOMBIE = Identifier.ofVanilla("zombie");
	public static final Identifier SKELETON = Identifier.ofVanilla("skeleton");
	public static final Identifier CREEPER = Identifier.ofVanilla("creeper");
	public static final Identifier SPIDER = Identifier.ofVanilla("spider");
	public static final Identifier BOGGED = Identifier.ofVanilla("bogged");
	public static final Identifier BAT = Identifier.ofVanilla("bat");
	public static final Identifier FROG = Identifier.ofVanilla("frog");
	public static final Identifier SQUID = Identifier.ofVanilla("squid");
	public static final Identifier GLOW_SQUID = Identifier.ofVanilla("glow_squid");
	public static final Identifier SNOW_GOLEM = Identifier.ofVanilla("snow_golem");
	public static final Identifier OCELOT = Identifier.ofVanilla("ocelot");
	public static final Identifier SNIFFER = Identifier.ofVanilla("sniffer");
	public static final Identifier HORSE = Identifier.ofVanilla("horse");
	public static final Identifier SKELETON_HORSE = Identifier.ofVanilla("skeleton_horse");
	public static final Identifier ARMADILLO = Identifier.ofVanilla("armadillo");
	public static final Identifier VILLAGER = Identifier.ofVanilla("villager");
	public static final Identifier AXOLOTL = Identifier.ofVanilla("axolotl");
	public static final Identifier CAMEL = Identifier.ofVanilla("camel");
	public static final Identifier CAT = Identifier.ofVanilla("cat");
	public static final Identifier CHICKEN = Identifier.ofVanilla("chicken");
	public static final Identifier COD = Identifier.ofVanilla("cod");
	public static final Identifier COW = Identifier.ofVanilla("cow");
	public static final Identifier DONKEY = Identifier.ofVanilla("donkey");
	public static final Identifier MOOSHROOM = Identifier.ofVanilla("mooshroom");
	public static final Identifier MULE = Identifier.ofVanilla("mule");
	public static final Identifier PARROT = Identifier.ofVanilla("parrot");
	public static final Identifier PIG = Identifier.ofVanilla("pig");
	public static final Identifier PUFFERFISH = Identifier.ofVanilla("pufferfish");
	public static final Identifier RABBIT = Identifier.ofVanilla("rabbit");
	public static final Identifier SALMON = Identifier.ofVanilla("salmon");
	public static final Identifier SHEEP = Identifier.ofVanilla("sheep");
	public static final Identifier STRIDER = Identifier.ofVanilla("strider");
	public static final Identifier TADPOLE = Identifier.ofVanilla("tadpole");
	public static final Identifier TROPICAL_FISH = Identifier.ofVanilla("tropical_fish");
	public static final Identifier TURTLE = Identifier.ofVanilla("turtle");
	public static final Identifier WANDERING_TRADER = Identifier.ofVanilla("wandering_trader");
	public static final Identifier BEE = Identifier.ofVanilla("bee");
	public static final Identifier CAVE_SPIDER = Identifier.ofVanilla("cave_spider");
	public static final Identifier DOLPHIN = Identifier.ofVanilla("dolphin");
	public static final Identifier DROWNED = Identifier.ofVanilla("drowned");
	public static final Identifier ENDERMAN = Identifier.ofVanilla("enderman");
	public static final Identifier FOX = Identifier.ofVanilla("fox");
	public static final Identifier GOAT = Identifier.ofVanilla("goat");
	public static final Identifier IRON_GOLEM = Identifier.ofVanilla("iron_golem");
	public static final Identifier LLAMA = Identifier.ofVanilla("llama");
	public static final Identifier PANDA = Identifier.ofVanilla("panda");
	public static final Identifier POLAR_BEAR = Identifier.ofVanilla("polar_bear");
	public static final Identifier WOLF = Identifier.ofVanilla("wolf");
	public static final Identifier ZOMBIFIED_PIGLIN = Identifier.ofVanilla("zombified_piglin");
	public static final Identifier BLAZE = Identifier.ofVanilla("blaze");
	public static final Identifier BREEZE = Identifier.ofVanilla("breeze");
	public static final Identifier ELDER_GUARDIAN = Identifier.ofVanilla("elder_guardian");
	public static final Identifier ENDERMITE = Identifier.ofVanilla("endermite");
	public static final Identifier EVOKER = Identifier.ofVanilla("evoker");
	public static final Identifier GHAST = Identifier.ofVanilla("ghast");
	public static final Identifier GUARDIAN = Identifier.ofVanilla("guardian");
	public static final Identifier HOGLIN = Identifier.ofVanilla("hoglin");
	public static final Identifier HUSK = Identifier.ofVanilla("husk");
	public static final Identifier MAGMA_CUBE = Identifier.ofVanilla("magma_cube");
	public static final Identifier PHANTOM = Identifier.ofVanilla("phantom");
	public static final Identifier PIGLIN_BRUTE = Identifier.ofVanilla("piglin_brute");
	public static final Identifier PILLAGER = Identifier.ofVanilla("pillager");
	public static final Identifier RAVAGER = Identifier.ofVanilla("ravager");
	public static final Identifier SHULKER = Identifier.ofVanilla("shulker");
	public static final Identifier SILVERFISH = Identifier.ofVanilla("silverfish");
	public static final Identifier SLIME = Identifier.ofVanilla("slime");
	public static final Identifier STRAY = Identifier.ofVanilla("stray");
	public static final Identifier VEX = Identifier.ofVanilla("vex");
	public static final Identifier VINDICATOR = Identifier.ofVanilla("vindicator");
	public static final Identifier WARDEN = Identifier.ofVanilla("warden");
	public static final Identifier WITCH = Identifier.ofVanilla("witch");
	public static final Identifier WITHER_SKELETON = Identifier.ofVanilla("wither_skeleton");
	public static final Identifier ZOGLIN = Identifier.ofVanilla("zoglin");
	public static final Identifier ZOMBIE_VILLAGER = Identifier.ofVanilla("zombie_villager");

	public static void registerVanillaDna() {
		DnaBuilder
				.create(EntityType.ZOMBIE.getTranslationKey())
				.setColor("#06891b", "#06891b", "#2493bc", "#2493bc")
				.addSourceMobAsSummonResult(EntityType.ZOMBIE)
				.buildAndRegister(ZOMBIE);
		DnaBuilder
				.create(EntityType.SKELETON.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SKELETON)
				.buildAndRegister(SKELETON);
		DnaBuilder
				.create(EntityType.CREEPER.getTranslationKey())
				.setColor("#43da1b", "#43da1b", "#439f2b", "#439f2b")
				.addSourceMobAsSummonResult(EntityType.CREEPER)
				.buildAndRegister(CREEPER);
		DnaBuilder
				.create(EntityType.SPIDER.getTranslationKey())
				.setColor("#d20303", "#d20303", "#3d3636", "#3d3636")
				.addSourceMobAsSummonResult(EntityType.SPIDER)
				.buildAndRegister(SPIDER);
		DnaBuilder
				.create(EntityType.BAT.getTranslationKey())
				.setColor("#a37402", "#a37402", "#6d500a", "#6d500a")
				.addSourceMobAsSummonResult(EntityType.BAT)
				.buildAndRegister(BAT);
		DnaBuilder
				.create(EntityType.FROG.getTranslationKey())
				.setColor("#d79c51", "#d79c51", "#bc843b", "#bc843b")
				.addSourceMobAsSummonResult(EntityType.FROG)
				.buildAndRegister(FROG);
		DnaBuilder
				.create(EntityType.SQUID.getTranslationKey())
				.setColor("#6c8ca4", "#6c8ca4", "#496071", "#496071")
				.addSourceMobAsSummonResult(EntityType.SQUID)
				.buildAndRegister(SQUID);
		DnaBuilder
				.create(EntityType.GLOW_SQUID.getTranslationKey())
				.setColor("#6c8ca4", "#fffff2", "#7efcbe", "#496071")
				.addSourceMobAsSummonResult(EntityType.GLOW_SQUID)
				.buildAndRegister(GLOW_SQUID);
		DnaBuilder
				.create(EntityType.BOGGED.getTranslationKey())
				.setColor("#dadada", "#1ac644", "#129e34", "#dadada")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BOGGED)
				.buildAndRegister(BOGGED);
		DnaBuilder
				.create(EntityType.SNOW_GOLEM.getTranslationKey())
				.setColor("#ff8114","#ffffff","#ffffff","#ff8114")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.SNOW_GOLEM)
				.buildAndRegister(SNOW_GOLEM);
		DnaBuilder
				.create(EntityType.OCELOT.getTranslationKey())
				.setColor("#ffdd00","#000000","#ffdd00","#000000")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.OCELOT)
				.buildAndRegister(OCELOT);
		DnaBuilder
				.create(EntityType.SNIFFER.getTranslationKey())
				.setColor("#266800","#266800","#a51c25","#a51c25")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.SNIFFER)
				.buildAndRegister(SNIFFER);
		DnaBuilder
				.create(EntityType.HORSE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.HORSE)
				.buildAndRegister(HORSE);
		DnaBuilder
				.create(EntityType.SKELETON_HORSE.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.SKELETON_HORSE)
				.buildAndRegister(SKELETON_HORSE);
		DnaBuilder
				.create(EntityType.ARMADILLO.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.ARMADILLO)
				.buildAndRegister(ARMADILLO);
		DnaBuilder
				.create(EntityType.VILLAGER.getTranslationKey())
				.setColor("#b75c38","#1d960d","#702a02","#703310")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.VILLAGER)
				.buildAndRegister(VILLAGER);
		DnaBuilder
				.create(EntityType.AXOLOTL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.AXOLOTL)
				.buildAndRegister(AXOLOTL);
		DnaBuilder
				.create(EntityType.CAMEL.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.CAMEL)
				.buildAndRegister(CAMEL);
		DnaBuilder
				.create(EntityType.CAT.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.CAT)
				.buildAndRegister(CAT);
		DnaBuilder
				.create(EntityType.CHICKEN.getTranslationKey())
				.setColor("#ff001d","#f0f0f0","#ffffff","#dfdfdf")
				.addSourceMobAsSummonResult(EntityType.CHICKEN)
				.buildAndRegister(CHICKEN);
		DnaBuilder
				.create(EntityType.COD.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.COD)
				.buildAndRegister(COD);
		DnaBuilder
				.create(EntityType.COW.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.COW)
				.buildAndRegister(COW);
		DnaBuilder
				.create(EntityType.DONKEY.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.DONKEY)
				.buildAndRegister(DONKEY);
		DnaBuilder
				.create(EntityType.MOOSHROOM.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.MOOSHROOM)
				.buildAndRegister(MOOSHROOM);
		DnaBuilder
				.create(EntityType.MULE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.MULE)
				.buildAndRegister(MULE);
		DnaBuilder
				.create(EntityType.PARROT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.PARROT)
				.buildAndRegister(PARROT);
		DnaBuilder
				.create(EntityType.PIG.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PIG)
				.buildAndRegister(PIG);
		DnaBuilder
				.create(EntityType.PUFFERFISH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.PUFFERFISH)
				.buildAndRegister(PUFFERFISH);
		DnaBuilder
				.create(EntityType.RABBIT.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.RABBIT)
				.buildAndRegister(RABBIT);
		DnaBuilder
				.create(EntityType.SALMON.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SALMON)
				.buildAndRegister(SALMON);
		DnaBuilder
				.create(EntityType.SHEEP.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SHEEP)
				.buildAndRegister(SHEEP);
		DnaBuilder
				.create(EntityType.STRIDER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.STRIDER)
				.buildAndRegister(STRIDER);
		DnaBuilder
				.create(EntityType.TADPOLE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.TADPOLE)
				.buildAndRegister(TADPOLE);
		DnaBuilder
				.create(EntityType.TROPICAL_FISH.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.TROPICAL_FISH)
				.buildAndRegister(TROPICAL_FISH);
		DnaBuilder
				.create(EntityType.TURTLE.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.TURTLE)
				.buildAndRegister(TURTLE);
		// FIXME: Can't extract blood from wandering trader, opens trading GUI instead
		DnaBuilder
				.create(EntityType.WANDERING_TRADER.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.WANDERING_TRADER)
				.buildAndRegister(WANDERING_TRADER);
		DnaBuilder
				.create(EntityType.BEE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.BEE)
				.buildAndRegister(BEE);
		DnaBuilder
				.create(EntityType.CAVE_SPIDER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.CAVE_SPIDER)
				.buildAndRegister(CAVE_SPIDER);
		DnaBuilder
				.create(EntityType.DOLPHIN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.DOLPHIN)
				.buildAndRegister(DOLPHIN);
		DnaBuilder
				.create(EntityType.DROWNED.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.DROWNED)
				.buildAndRegister(DROWNED);
		DnaBuilder
				.create(EntityType.ENDERMAN.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.ENDERMAN)
				.buildAndRegister(ENDERMAN);
		DnaBuilder
				.create(EntityType.FOX.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.FOX)
				.buildAndRegister(FOX);
		DnaBuilder
				.create(EntityType.GOAT.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.GOAT)
				.buildAndRegister(GOAT);
		DnaBuilder
				.create(EntityType.IRON_GOLEM.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.IRON_GOLEM)
				.buildAndRegister(IRON_GOLEM);
		DnaBuilder
				.create(EntityType.LLAMA.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.LLAMA)
				.addSourceMob(EntityType.TRADER_LLAMA)
				.buildAndRegister(LLAMA);
		DnaBuilder
				.create(EntityType.PANDA.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PANDA)
				.buildAndRegister(PANDA);
		DnaBuilder
				.create(EntityType.POLAR_BEAR.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.POLAR_BEAR)
				.buildAndRegister(POLAR_BEAR);
		DnaBuilder
				.create(EntityType.WOLF.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.WOLF)
				.buildAndRegister(WOLF);
		DnaBuilder
				.create(EntityType.ZOMBIFIED_PIGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.ZOMBIFIED_PIGLIN)
				.buildAndRegister(ZOMBIFIED_PIGLIN);
		DnaBuilder
				.create(EntityType.BLAZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BLAZE)
				.buildAndRegister(BLAZE);
		DnaBuilder
				.create(EntityType.BREEZE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BREEZE)
				.buildAndRegister(BREEZE);
		DnaBuilder
				.create(EntityType.ELDER_GUARDIAN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.ELDER_GUARDIAN)
				.buildAndRegister(ELDER_GUARDIAN);
		DnaBuilder
				.create(EntityType.ENDERMITE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.ENDERMITE)
				.buildAndRegister(ENDERMITE);
		DnaBuilder
				.create(EntityType.EVOKER.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.EVOKER)
				.buildAndRegister(EVOKER);
		DnaBuilder
				.create(EntityType.GHAST.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.GHAST)
				.buildAndRegister(GHAST);
		DnaBuilder
				.create(EntityType.GUARDIAN.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.GUARDIAN)
				.buildAndRegister(GUARDIAN);
		DnaBuilder
				.create(EntityType.HOGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.HOGLIN)
				.buildAndRegister(HOGLIN);
		DnaBuilder
				.create(EntityType.HUSK.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.HUSK)
				.buildAndRegister(HUSK);
		DnaBuilder
				.create(EntityType.MAGMA_CUBE.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.MAGMA_CUBE)
				.buildAndRegister(MAGMA_CUBE);
		DnaBuilder
				.create(EntityType.PHANTOM.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.PHANTOM)
				.buildAndRegister(PHANTOM);
		DnaBuilder
				.create(EntityType.PIGLIN_BRUTE.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.PIGLIN_BRUTE)
				.buildAndRegister(PIGLIN_BRUTE);
		DnaBuilder
				.create(EntityType.PILLAGER.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.PILLAGER)
				.buildAndRegister(PILLAGER);
		DnaBuilder
				.create(EntityType.RAVAGER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.RAVAGER)
				.buildAndRegister(RAVAGER);
		DnaBuilder
				.create(EntityType.SHULKER.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.SHULKER)
				.buildAndRegister(SHULKER);
		DnaBuilder
				.create(EntityType.SILVERFISH.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.SILVERFISH)
				.buildAndRegister(SILVERFISH);
		DnaBuilder
				.create(EntityType.SLIME.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.SLIME)
				.buildAndRegister(SLIME);
		DnaBuilder
				.create(EntityType.STRAY.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.STRAY)
				.buildAndRegister(STRAY);
		DnaBuilder
				.create(EntityType.VEX.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.VEX)
				.buildAndRegister(VEX);
		DnaBuilder
				.create(EntityType.VINDICATOR.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.VINDICATOR)
				.buildAndRegister(VINDICATOR);
		DnaBuilder
				.create(EntityType.WARDEN.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.WANDERING_TRADER)
				.buildAndRegister(WARDEN);
		DnaBuilder
				.create(EntityType.WITCH.getTranslationKey())
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.WITCH)
				.buildAndRegister(WITCH);
		DnaBuilder
				.create(EntityType.WITHER_SKELETON.getTranslationKey())
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.WITHER_SKELETON)
				.buildAndRegister(WITHER_SKELETON);
		DnaBuilder
				.create(EntityType.ZOGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.ZOGLIN)
				.buildAndRegister(ZOGLIN);
		DnaBuilder
				.create(EntityType.ZOMBIE_VILLAGER.getTranslationKey())
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.ZOMBIE_VILLAGER)
				.buildAndRegister(ZOMBIE_VILLAGER);
	}
}
