package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingStoneCobble implements IOreRecipeRegistrator {

	public ProcessingStoneCobble() {
		OrePrefixes.stoneCobble.add(this);
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry)))
				try {
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(400).input(OrePrefixes.stick, Materials.Wood)
							.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
							.output(new ItemStack(Blocks.lever, 1)).buildAndRegister();
				} catch (GT_RecipeException e) {
					GT_Log.log.warn("Failed to register a recipe for Oredict entry " + entry.oreDictName);
				}
		}
	}
}
