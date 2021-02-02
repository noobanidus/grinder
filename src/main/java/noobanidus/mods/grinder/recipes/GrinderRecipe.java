package noobanidus.mods.grinder.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grinder.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class GrinderRecipe extends AbstractCookingRecipe {
  private boolean staticOutput;
  private Ingredient result;

  public GrinderRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, Ingredient result, float experienceIn, int cookTimeIn, boolean staticOutput) {
    super(ModRecipes.GRINDER_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
    this.staticOutput = staticOutput;
    this.result = result;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.GRINDER_SERIALIZER.get();
  }

  public boolean hasTagResult () {
    return this.result != null && this.result != Ingredient.EMPTY;
  }

  public Ingredient getResultIngredient() {
    return result;
  }

  public boolean hasStaticOutput() {
    return staticOutput;
  }
}

