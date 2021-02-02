package noobanidus.mods.grinder.integration.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.actions.IRuntimeAction;
import com.blamejared.crafttweaker.impl.managers.CTCraftingTableManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.init.ModRecipes;
import noobanidus.mods.grinder.recipes.GrinderRecipe;

import java.util.Collections;

public class AddRecipe implements IRuntimeAction {
  final private String name;
  final private String group;
  final private Ingredient ingredient;
  final private Ingredient result;
  final private float experience;
  final private int cookTime;
  final private boolean staticOutput;

  public AddRecipe(String name, String group, Ingredient ingredient, Ingredient result, float experience, int cookTime, boolean staticOutput) {
    this.name = name;
    this.group = group;
    this.ingredient = ingredient;
    this.result = result;
    this.experience = experience;
    this.cookTime = cookTime;
    this.staticOutput = staticOutput;
  }

  @Override
  public void apply() {
    ResourceLocation name = new ResourceLocation(Grinder.MODID, this.name);
    ItemStack[] stacks = result.getMatchingStacks();
    if (stacks.length == 0) {
      CraftTweakerAPI.logger.error("Invalid output item for recipe " + this.name + ", no matching output.");
    }
    GrinderRecipe recipe = new GrinderRecipe(name, group, ingredient, stacks[0], result, experience, cookTime, staticOutput);
    CTCraftingTableManager.recipeManager.recipes.getOrDefault(ModRecipes.GRINDER_TYPE, Collections.emptyMap()).put(name, recipe);
  }

  @Override
  public String describe() {
    return "Adding a Grindr recipe named " + this.name;
  }
}
