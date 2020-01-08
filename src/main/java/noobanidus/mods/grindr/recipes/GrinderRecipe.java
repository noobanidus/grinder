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
  public GrinderRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(ModRecipes.GRINDER_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.GRINDER_SERIALIZER.get();
  }

  public static class Serializer extends AbstractCookingRecipeSerializer<GrinderRecipe> {
    public Serializer() {
      super(GrinderRecipe::new, 100);
    }
  }

  @Override
  public ItemStack getCraftingResult(IInventory inv) {
    return super.getCraftingResult(inv);
  }
}

