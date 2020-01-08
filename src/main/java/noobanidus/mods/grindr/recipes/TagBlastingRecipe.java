package noobanidus.mods.grindr.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grindr.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class TagBlastingRecipe extends BlastingRecipe {
  public TagBlastingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.TAG_BLASTING_SERIALIZER.get();
  }

  public static class Serializer extends TagCookingRecipeSerializer<TagBlastingRecipe> {
    public Serializer() {
      super(TagBlastingRecipe::new, 100);
    }
  }
}

