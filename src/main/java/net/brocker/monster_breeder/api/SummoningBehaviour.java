package net.brocker.monster_breeder.api;

import net.brocker.monster_breeder.blockentity.custom.DnaAltarBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.AvailableSince("1.0.0")
public abstract class SummoningBehaviour<T extends LivingEntity> {
	protected final EntityType<T> entityType;

	public SummoningBehaviour(EntityType<T> entityType) {
		this.entityType = entityType;
	}

	/**
	 * Gets the type of entity this behaviour will summon.
	 * @return The entity type
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public EntityType<T> getEntityType() {
		return entityType;
	}

	/**
	 * Can the summoning process begin?
	 * @param blockEntity The DNA altar block entity that is trying to summon the mob.
	 * @return Should the DNA altar start summoning
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public boolean canSummon(DnaAltarBlockEntity blockEntity) {
		return true;
	}

	/**
	 * Called when the summoning process has started.
	 * @param blockEntity The DNA altar block entity that has started summoning.
	 * @return The summoning data
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public NbtCompound startSummon(DnaAltarBlockEntity blockEntity) {
		return new NbtCompound();
	}

	/**
	 * Called when the summoning process has stopped
	 * @param blockEntity The DNA altar block entity that has stopped summoning.
	 * @param data The summoning data
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public void stopSummon(DnaAltarBlockEntity blockEntity, NbtCompound data) {}

	/**
	 * Called every tick during the summoning process.
	 * @param blockEntity The DNA altar block entity that is trying to summon the mob.
	 * @param data The summoning data
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public abstract void tick(DnaAltarBlockEntity blockEntity, NbtCompound data);

	/**
	 * Called every tick during the summoning process.
	 * @param blockEntity The DNA altar block entity that is trying to summon the mob.
	 * @param data The summoning data
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public void tickClient(DnaAltarBlockEntity blockEntity, NbtCompound data) {}

	/**
	 * Summon the entity for this behaviour,
	 * @param blockEntity The DNA altar block entity that is trying to summon the mob.
	 * @return The entity that was summoned or null if summoning failed
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public abstract @Nullable T summonEntity(DnaAltarBlockEntity blockEntity);

	/**
	 * Called from the block entity renderer
	 * @param blockEntity The DNA altar block entity that is being rendered.
	 * @param data The summoning data, will be null if summoning has not started.
	 * @param context Rendering context
	 * @param tickDelta The fractional progression between ticks, useful for smooth animations.
	 * @param matrices The {@link MatrixStack} for modifying transformation matrices during rendering.
	 * @param vertexConsumers The {@link VertexConsumerProvider} for submitting rendered vertices to the GPU.
	 * @param light Packed light coordinates for lighting calculations, including block and sky light.
	 * @param overlay Overlay texture coordinates used for effects like enchantment glint.
	 */
	@ApiStatus.AvailableSince("1.0.0")
	public abstract void render(DnaAltarBlockEntity blockEntity, @Nullable NbtCompound data, BlockEntityRendererFactory.Context context, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay);
}
