package noobanidus.mods.grinder.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grinder.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class TagFurnaceRecipe extends FurnaceRecipe {
  public TagFurnaceRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.TAG_FURNACE_SERIALIZER.get();
  }

  public static class Serializer extends TagCookingRecipeSerializer<TagFurnaceRecipe> {
    public Serializer() {
      super(TagFurnaceRecipe::new, 100);
    }
  }
}

