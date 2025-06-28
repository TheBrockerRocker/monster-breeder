package net.brocker.monster_breeder.dna;

import net.brocker.monster_breeder.MonsterBreeder;
import net.brocker.monster_breeder.api.Dna;
import net.brocker.monster_breeder.api.util.DnaBuilder;
import net.brocker.monster_breeder.api.util.SuppliedDnaBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.awt.*;

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
		/*
		.setColor template

			.setColor("#","#","#","#")
		 or
			.setColor("#")
		 */
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
				.setColor("#ffdd00","#ffdd00","#ffdd00","#000000")
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
				.setColor("#991c00","#8d4600","#8d3800","#eeeeee")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.HORSE)
				.buildAndRegister(HORSE);
		DnaBuilder
				.create(EntityType.SKELETON_HORSE.getTranslationKey())
				.setColor("#e1e1e1","#c5c5c5","#ffffff","#d2d2d2")
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.SKELETON_HORSE)
				.buildAndRegister(SKELETON_HORSE);
		DnaBuilder
				.create(EntityType.ARMADILLO.getTranslationKey())
				.setColor("#ae736f","#ae736f","#4d2a28","#4d2a28")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.ARMADILLO)
				.buildAndRegister(ARMADILLO);
		DnaBuilder
				.create(EntityType.VILLAGER.getTranslationKey())
				.setColor("#b75c38","#59ff40","#702a02","#703310")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.VILLAGER)
				.buildAndRegister(VILLAGER);
		DnaBuilder
				.create(EntityType.AXOLOTL.getTranslationKey())
				.setColor("#be009e","#be00b1","#d900c7","#bd00d9")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.AXOLOTL)
				.buildAndRegister(AXOLOTL);
		DnaBuilder
				.create(EntityType.CAMEL.getTranslationKey())
				.setColor("#f4bf00","#f4bf11","#f4bf22","#f4bf33")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.CAMEL)
				.buildAndRegister(CAMEL);
		DnaBuilder
				.create(EntityType.CAT.getTranslationKey())
				.setColor("#202020","#202020","#cccccc","#cccccc")
				.addSourceMobAsSummonResult(EntityType.CAT)
				.buildAndRegister(CAT);
		DnaBuilder
				.create(EntityType.CHICKEN.getTranslationKey())
				.setColor("#ff001d","#f0f0f0","#ffffff","#dfdfdf")
				.addSourceMobAsSummonResult(EntityType.CHICKEN)
				.buildAndRegister(CHICKEN);
		DnaBuilder
				.create(EntityType.COD.getTranslationKey())
				.setColor("#a36a62","#b67067","#b68567","#8b7060")
				.addSourceMobAsSummonResult(EntityType.COD)
				.buildAndRegister(COD);
		DnaBuilder
				.create(EntityType.COW.getTranslationKey())
				.setColor("#5b2700","#f0f0f0","#5b2700","#f0f0f0")
				.addSourceMobAsSummonResult(EntityType.COW)
				.buildAndRegister(COW);
		DnaBuilder
				.create(EntityType.DONKEY.getTranslationKey())
				.setColor("#74391a","#74451a","#74391a","#74451a")
				.addSourceMobAsSummonResult(EntityType.DONKEY)
				.buildAndRegister(DONKEY);
		DnaBuilder
				.create(EntityType.MOOSHROOM.getTranslationKey())
				.setColor("#c9002b","#f0f0f0","#c9002b","#f0f0f0")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.MOOSHROOM)
				.buildAndRegister(MOOSHROOM);
		DnaBuilder
				.create(EntityType.MULE.getTranslationKey())
				.setColor("#502d1a","#502d1a","#24130c","#24130c")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.MULE)
				.buildAndRegister(MULE);
		DnaBuilder
				.create(EntityType.PARROT.getTranslationKey())
				.setColor("#000000","#0080ff","#ff0000","#bfbf00")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.PARROT)
				.buildAndRegister(PARROT);
		DnaBuilder
				.create(EntityType.PIG.getTranslationKey())
				.setColor("#ff72e5","#ff729e","#ff72ec","#ff47a0")
				.addSourceMobAsSummonResult(EntityType.PIG)
				.buildAndRegister(PIG);
		DnaBuilder
				.create(EntityType.PUFFERFISH.getTranslationKey())
				.setColor("#ebff6e","#8dffe4","#ffe15f","#ffef8d")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.PUFFERFISH)
				.buildAndRegister(PUFFERFISH);
		DnaBuilder
				.create(EntityType.RABBIT.getTranslationKey())
				.setColor("#948162","#524839","#524839","#948162")
				.addSourceMobAsSummonResult(EntityType.RABBIT)
				.buildAndRegister(RABBIT);
		DnaBuilder
				.create(EntityType.SALMON.getTranslationKey())
				.setColor("#e81e0b","#2eaa74","#e81e0b","#2eaa74")
				.addSourceMobAsSummonResult(EntityType.SALMON)
				.buildAndRegister(SALMON);
		SuppliedDnaBuilder
				.create(EntityType.SHEEP.getTranslationKey())
				.setColorSupplier(() -> {
					int tick = MonsterBreeder.server.getTicks();
					int color = Color.HSBtoRGB((tick % 300) / 300.0f, 1.0f, 1.0f);
					color |= 0xFF000000;
					return Dna.Color.create(color);
				})
				.addSourceMobAsSummonResult(EntityType.SHEEP)
				.buildAndRegister(SHEEP);
		DnaBuilder
				.create(EntityType.STRIDER.getTranslationKey())
				.setColor("#870000","#8e587f","#870000","#8e587f")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.STRIDER)
				.buildAndRegister(STRIDER);
		DnaBuilder
				.create(EntityType.TADPOLE.getTranslationKey())
				.setColor("#000000","#000000","#291400","#291400")
				.addSourceMobAsSummonResult(EntityType.TADPOLE)
				.buildAndRegister(TADPOLE);
		DnaBuilder
				.create(EntityType.TROPICAL_FISH.getTranslationKey())
				.setColor("#ff7f00","#f5f5f5","#ff7f00","#f5f5f5")
				.addSourceMobAsSummonResult(EntityType.TROPICAL_FISH)
				.buildAndRegister(TROPICAL_FISH);
		DnaBuilder
				.create(EntityType.TURTLE.getTranslationKey())
				.setColor("#1a8932","#bdb081","#00881d","#a3a47c")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.TURTLE)
				.buildAndRegister(TURTLE);
		DnaBuilder
				.create(EntityType.WANDERING_TRADER.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.WANDERING_TRADER)
				.buildAndRegister(WANDERING_TRADER);
		DnaBuilder
				.create(EntityType.BEE.getTranslationKey())
				.setColor("#000000","#ffd000","#000000","#ffd000")
				.addSourceMobAsSummonResult(EntityType.BEE)
				.buildAndRegister(BEE);
		DnaBuilder
				.create(EntityType.CAVE_SPIDER.getTranslationKey())
				.setColor("#ce000f","#ce000f","#000a2c","#00102c")
				.setRarity(Rarity.EPIC)
				.addSourceMobAsSummonResult(EntityType.CAVE_SPIDER)
				.buildAndRegister(CAVE_SPIDER);
		DnaBuilder
				.create(EntityType.DOLPHIN.getTranslationKey())
				.setColor("#68aad9","#68aad9","#729cba","#647b8b")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.DOLPHIN)
				.buildAndRegister(DOLPHIN);
		DnaBuilder
				.create(EntityType.DROWNED.getTranslationKey())
				.setColor("#005c5f","#5f4600","#38ecce","#38ec98")
				.addSourceMobAsSummonResult(EntityType.DROWNED)
				.buildAndRegister(DROWNED);
		DnaBuilder
				.create(EntityType.ENDERMAN.getTranslationKey())
				.setColor("#a800c2","#a800c2","#170029","#170029")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.ENDERMAN)
				.buildAndRegister(ENDERMAN);
		DnaBuilder
				.create(EntityType.FOX.getTranslationKey())
				.setColor("#e6e6e6","#e6e6e6","#f08a1c","#f08a1c")
				.addSourceMobAsSummonResult(EntityType.FOX)
				.buildAndRegister(FOX);
		DnaBuilder
				.create(EntityType.GOAT.getTranslationKey())
				.setColor("#585858","#474747","#d4d4d4","#e8e8e8")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.GOAT)
				.buildAndRegister(GOAT);
		DnaBuilder
				.create(EntityType.IRON_GOLEM.getTranslationKey())
				.setColor("#f12020","#e2e2e2","#d2d2d2","#c2c2c2")
				.addSourceMobAsSummonResult(EntityType.IRON_GOLEM)
				.buildAndRegister(IRON_GOLEM);
		DnaBuilder
				.create(EntityType.LLAMA.getTranslationKey())
				.setColor("#cdc091","#cdc091","#a8551a","#a86d1a")
				.addSourceMobAsSummonResult(EntityType.LLAMA)
				.addSourceMob(EntityType.TRADER_LLAMA)
				.buildAndRegister(LLAMA);
		DnaBuilder
				.create(EntityType.PANDA.getTranslationKey())
				.setColor("#000000","#ffffff","#000000","#ffffff")
				.addSourceMobAsSummonResult(EntityType.PANDA)
				.buildAndRegister(PANDA);
		DnaBuilder
				.create(EntityType.POLAR_BEAR.getTranslationKey())
				.setColor("#000000","#ffffff","#fff4ce","#fff7da")
				.addSourceMobAsSummonResult(EntityType.POLAR_BEAR)
				.buildAndRegister(POLAR_BEAR);
		DnaBuilder
				.create(EntityType.WOLF.getTranslationKey())
				.setColor("#1c1c1c","#9c9898","#9c9894","#6e6b6c")
				.setRarity(Rarity.UNCOMMON)
				.addSourceMobAsSummonResult(EntityType.WOLF)
				.buildAndRegister(WOLF);
		DnaBuilder
				.create(EntityType.ZOMBIFIED_PIGLIN.getTranslationKey())
				.addSourceMobAsSummonResult(EntityType.ZOMBIFIED_PIGLIN)
				.buildAndRegister(ZOMBIFIED_PIGLIN);
		DnaBuilder
				.create(EntityType.BLAZE.getTranslationKey())
				.setColor("#e29922","#e2a646","#ee9506","#e2a644")
				.setRarity(Rarity.RARE)
				.addSourceMobAsSummonResult(EntityType.BLAZE)
				.buildAndRegister(BLAZE);
		DnaBuilder
				.create(EntityType.BREEZE.getTranslationKey())
				.setColor("#73abdf","#4f9feb","#a4d3ff","#93b9dd")
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
