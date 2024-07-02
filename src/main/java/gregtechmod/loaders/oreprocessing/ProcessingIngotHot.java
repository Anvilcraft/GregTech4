package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

public class ProcessingIngotHot implements IOreRecipeRegistrator {

	public ProcessingIngotHot() {
		OrePrefixes.ingotHot.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				try {
					RecipeMaps.VACUUM_FREEZER.factory().EUt(120)
							.duration(Math.max(aMaterial.getMass() * 3, 1))
							.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
							.output(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L))
							.buildAndRegister();
				} catch (GT_RecipeException e) {
					GT_Log.log.warn("Failed to register a recipe for Oredict entry " + entry.oreDictName);
				}
			}
		}
	}
}
