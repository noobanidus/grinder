package noobanidus.mods.grindr.integration.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;

@ZenRegister
@ZenCodeType.Name("mods.grindr.Grinder")
public class Grinder {

  @ZenCodeType.Method
  public static void addRecipe (RecipeBuilder builder) {
    CraftTweakerAPI.apply(builder.build());
  }

  @ZenRegister
  @ZenCodeType.Name("mods.grindr.RecipeBuilder")
  public static class RecipeBuilder {
    final private String name;
    private String group = "grindr";
    private IIngredient ingredient = null;
    private IIngredient result = null;
    private float experience = 0.125f;
    private int cookTime = 100;
    private boolean staticOutput = false;

    @ZenCodeType.Constructor
    public RecipeBuilder(String name) {
      this.name = name;
    }

    @ZenCodeType.Method
    public RecipeBuilder input(IIngredient item) {
      this.ingredient = item;
      return this;
    }

    @ZenCodeType.Method
    public RecipeBuilder output(IIngredient output) {
      this.result = output;
      return this;
    }

    @ZenCodeType.Method
    public RecipeBuilder experience(float experience) {
      this.experience = experience;
      return this;
    }

    @ZenCodeType.Method
    public RecipeBuilder cook(int cookTime) {
      this.cookTime = cookTime;
      return this;
    }

    @ZenCodeType.Method
    public RecipeBuilder staticOutput() {
      this.staticOutput = true;
      return this;
    }

    @ZenCodeType.Method
    public RecipeBuilder group(String group) {
      this.group = group;
      return this;
    }

    @Nullable
    public AddRecipe build () {
      if (ingredient == null) {
        CraftTweakerAPI.logger.error("Invalid ingredient for Grinder recipe: " + name);
        return null;
      }
      if (result == null) {
        CraftTweakerAPI.logger.error("Invalid result for Grinder recipe: " + name);
        return null;
      }
      return new AddRecipe(name, group, ingredient.asVanillaIngredient(), result.asVanillaIngredient(), experience, cookTime, staticOutput);
    }
  }
}
