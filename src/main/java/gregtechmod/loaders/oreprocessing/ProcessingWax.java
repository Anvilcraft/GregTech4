package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;

public class ProcessingWax implements IOreRecipeRegistrator {

	public ProcessingWax() {
		OrePrefixes.wax.add(this);
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))
					&& entry.oreDictName.equals("waxMagical")) {
				try {
					RecipeMaps.MAGIC_FUELS.factory().EUt(6).duration(1).input(RecipeEntry.fromStacks(1, entry.ores))
							.buildAndRegister();
				} catch (GT_RecipeException e) {
					GT_Log.log.warn("Failed to register a recipe for Oredict entry " + entry.oreDictName);
				}
			}
		}
	}
}
