# Monster Breeder – Core Mod (Fabric 1.21.1)

## 🎯 Objective
Combine two DNA items in a GUI to create a hybrid mob with fixed properties.

---

## 📦 Structure
- Minecraft version: **1.21.1**
- Modding framework: **Fabric**
- IDE: **IntelliJ IDEA**

---

## ✅ Step-by-Step Plan

### 🧩 Step 1 – Fabric Setup
- Create a new Fabric mod project using Loom in IntelliJ
- Configure:
  - `build.gradle`
  - `settings.gradle`
  - `fabric.mod.json`
  - `ModMain.java`
- Test with a registered item and block

**Goal:** Your mod loads into Minecraft without errors.

---

### 🧬 Step 2 – DNA Items
- Create a `DnaItem` class (optionally extend `Item`)
- Register DNA items for ~10 mobs (e.g., `ZOMBIE_DNA`, `SKELETON_DNA`)
- Add mob drops via `LivingEntityDropCallback`

**Goal:** DNA items drop from mobs.

**Recommended Mobs for DNA Items:**

| Mob           | DNA Item ID     | Why It’s a Great Pick                           | Suggested Effect When Fused                    |
|---------------|------------------|--------------------------------------------------|------------------------------------------------|
| **Zombie**    | `zombie_dna`     | Common, base hostile mob                        | Slight regeneration or melee boost             |
| **Skeleton**  | `skeleton_dna`   | Ranged attacker                                 | Adds projectile or accuracy trait              |
| **Creeper**   | `creeper_dna`    | Iconic explosive mob                            | Chance to explode or deal AoE damage           |
| **Spider**    | `spider_dna`     | Climber and fast mover                          | Grants climbing ability or speed               |
| **Enderman**  | `enderman_dna`   | Teleporting, high attack                        | Blink/teleport short-range ability             |
| **Slime**     | `slime_dna`      | Unique split behavior, bouncy                   | Resistance to fall damage, knockback effects   |
| **Blaze**     | `blaze_dna`      | Fire/air-based enemy                            | Grants fireballs or fire immunity              |
| **Witch**     | `witch_dna`      | Uses potions                                    | Applies random potion effects when hit         |
| **Guardian**  | `guardian_dna`   | Water-based laser attacker                      | Adds thorns or ranged beam attack              |
| **Phantom**   | `phantom_dna`    | Flying undead mob, night-based                  | Adds slow gliding/flying behavior              |

**How to Change Loot Tables:**
To inject custom drops into vanilla mob loot tables:

```java
LootTableEvents.MODIFY.register((resourceManager, manager, id, tableBuilder, source) -> {
    if (id.equals(EntityType.ZOMBIE.getLootTableId())) {
        LootPool pool = LootPool.builder()
            .with(ItemEntry.builder(ModItems.ZOMBIE_DNA))
            .rolls(ConstantLootNumberProvider.create(1))
            .build();
        tableBuilder.pool(pool);
    }
});
```

Make sure your item is registered in `ModItems` and your event is hooked in `onInitialize()`.

---

### 🧪 Step 3 – DNA Altar (Block + GUI)
- Register a custom block `DnaAltarBlock`
- Add a `BlockEntity` with 2 input slots
- Create a `HandledScreen` + `ScreenHandler`
- Add a "Fuse" button to the GUI

**Goal:** A working GUI with 2 input slots and interaction.

#### 🖼 GUI Concept
- **Top bar:** Title "DNA Altar"
- **Left slot:** DNA Sample #1 (slot with mob symbol overlay)
- **Right slot:** DNA Sample #2 (slot with mob symbol overlay)
- **Center:** Fusion animation or particles
- **Bottom slot:** Output hybrid mob egg (grayed until valid)
- **Bottom buttons:**
  - "Fuse" button (activates only with 2 valid samples)
  - Tooltip area for fusion result name, effects, purity & instability

**Visual Cues:**
- Show DNA purity and fusion instability next to each DNA item slot
- Output shows hybrid with fusion symbol, stats and result glow
- Tooltip when hovering over DNA: **Mob type**, **Purity**, **Instability**
- Tooltip when hovering over fusion result: **Hybrid type**, **Abilities**, **Fusion quality**

---

### 🧠 Step 4 – Fusion Logic
- On "Fuse" click:
  - Read the two DNA items
  - Lookup combination in a `Map<Pair<Item, Item>, HybridDefinition>`
  - Spawn a hybrid mob near the altar

**Goal:** Correct hybrid mob appears.

**Helper Methods:**
```java
// FusionRegistry.java
public static Optional<HybridDefinition> getHybrid(Item dna1, Item dna2) {
    Pair<Item, Item> key = dna1.getTranslationKey().compareTo(dna2.getTranslationKey()) < 0
        ? Pair.of(dna1, dna2)
        : Pair.of(dna2, dna1);
    return Optional.ofNullable(HYBRID_MAP.get(key));
}
```

```java
// FusionUtils.java
public static void applyFusionEffect(World world, BlockPos pos, HybridDefinition hybrid) {
    world.playSound(null, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    ((ServerWorld) world).spawnParticles(ParticleTypes.ENCHANT, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
        30, 0.5, 0.5, 0.5, 0.1);
}

public static Text getFusionTooltip(HybridDefinition def) {
    return Text.literal("Result: " + def.getName())
        .append("\nAbilities: " + def.getAbilities())
        .append("\nPurity Bonus: " + def.getPurityModifier());
}
```

---

### 👾 Step 5 – Hybrid Mobs
- Register ~10 custom `EntityType`s
- Add modified attributes or AI
- Reuse existing models/textures to start

**Goal:** Each DNA combination results in a unique mob with abilities.

---

### 🧪 Step 6 – Additional Mechanics
- DNA Purity (NBT tag on DNA items)
- Fusion Instability (chance of failed mob or explosion)
- Visual effects (particles, sounds)
- DNA Tooltip includes source mob, purity, instability
- Fusion Result Tooltip previews resulting mob before fusing
- Particle visuals differ per mob type
- NBT helper methods for reading/writing DNA data:

```java
public static float getPurity(ItemStack stack) {
    NbtCompound tag = stack.getOrCreateNbt();
    return tag.getFloat("Purity");
}

public static void setPurity(ItemStack stack, float purity) {
    stack.getOrCreateNbt().putFloat("Purity", purity);
}
```

---

### 🧪 Step 7 – Debug Tools
- Add `/fusiontest zombie_dna skeleton_dna` command to manually test fusion
- Use `/give` with pre-filled NBT for custom DNA (`/give @p monsterbreeder:zombie_dna{Purity:0.9f}`)
- Output logs to console or chat for each fusion attempt
- Include test-only "debug DNA" items with guaranteed purity and fusion effects

---

### 🛠 Step 8 – Advanced Features To Add
- 🎨 **Custom Models/Textures** for hybrids
- 🔊 **Sound Design** for fusion, hybrids, and UI
- 🛠 **Config System**: enable/disable features or tweak probabilities
- 🌐 **Multiplayer Sync** for altar interaction and mob spawns
- ⚙️ **Performance Tweaks** if many hybrids/fusions active
- 🌍 **Localization**: support multiple languages via `.lang` files

---

## 🛠 Suggested Project Structure
```
src/main/java/
├─ net/monsterbreeder/
│  ├─ MonsterBreederMod.java
│  ├─ block/
│  │  └─ DnaAltarBlock.java
│  ├─ entity/
│  │  └─ HybridZombieEntity.java
│  ├─ gui/
│  │  ├─ DnaAltarScreen.java
│  │  └─ DnaAltarScreenHandler.java
│  ├─ item/
│  │  └─ DnaItem.java
│  └─ util/
│     └─ FusionRegistry.java
```

Let me know if you'd like example code for each step!
