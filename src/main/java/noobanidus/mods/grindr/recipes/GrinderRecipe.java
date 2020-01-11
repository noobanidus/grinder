package noobanidus.mods.grindr.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grindr.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class GrinderRecipe extends AbstractCookingRecipe {
  private boolean staticOutput;

  public GrinderRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn, boolean staticOutput) {
    super(ModRecipes.GRINDER_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
    this.staticOutput = staticOutput;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.GRINDER_SERIALIZER.get();
  }

  public boolean hasStaticOutput() {
    return staticOutput;
  }
}

