package net.brocker.monsterbreeder.integrations.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.brocker.monsterbreeder.MonsterBreeder;
import net.brocker.monsterbreeder.api.util.DnaUtil;
import net.brocker.monsterbreeder.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class JeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return Identifier.of(MonsterBreeder.MOD_ID, "jei_integration");
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		IModPlugin.super.registerItemSubtypes(registration);

		registration.registerSubtypeInterpreter(ModItems.DNA_SAMPLE, new DnaSampleSubtypeInterpreter());
	}

	private static class DnaSampleSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
		@Override
		public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
			return getLegacyStringSubtypeInfo(ingredient, context);
		}

		@Override
		public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
			return DnaUtil.getDnaIdentifier(ingredient).toString();
		}
	}
}
