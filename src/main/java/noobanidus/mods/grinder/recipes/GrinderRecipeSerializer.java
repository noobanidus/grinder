package noobanidus.mods.grinder.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

@SuppressWarnings("ALL")
public class GrinderRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrinderRecipe> {
  protected final int defaultCookTime = 100;

  public GrinderRecipeSerializer() {
  }

  @Override
  public GrinderRecipe read(ResourceLocation recipeId, JsonObject json) {
    String s = JSONUtils.getString(json, "group", "");
    JsonElement jsonelement = (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
    Ingredient ingredient = Ingredient.deserialize(jsonelement);
    //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
    if (!json.has("result"))
      throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
    ItemStack itemstack = ItemStack.EMPTY;
    Ingredient result = Ingredient.EMPTY;
    if (json.get("result").isJsonObject())
      if (json.has("tagResult") && json.get("tagResult").getAsBoolean()) {
        jsonelement = (JSONUtils.isJsonArray(json, "result") ? JSONUtils.getJsonArray(json, "result") : JSONUtils.getJsonObject(json, "result"));
        result = Ingredient.deserialize(jsonelement);
      } else {
        itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
      }
    else {
      String s1 = JSONUtils.getString(json, "result");
      ResourceLocation resourcelocation = new ResourceLocation(s1);
      Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
      if (item == null) {
        throw new IllegalStateException("Item: " + s1 + " does not exist");
      }
      int count = JSONUtils.getInt(json, "count", 1);
      itemstack = new ItemStack(item, count);
    }
    boolean staticOutput = JSONUtils.getBoolean(json, "static", false);
    float f = JSONUtils.getFloat(json, "experience", 0.0F);
    int i = JSONUtils.getInt(json, "cookingtime", this.defaultCookTime);
    return new GrinderRecipe(recipeId, s, ingredient, itemstack, result, f, i, staticOutput);
  }

  @Override
  public GrinderRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
    String s = buffer.readString(32767);
    Ingredient ingredient = Ingredient.read(buffer);
    ItemStack itemstack = ItemStack.EMPTY;
    Ingredient result = Ingredient.EMPTY;
    if (buffer.readBoolean()) {
      itemstack = buffer.readItemStack();
    } else {
      result = Ingredient.read(buffer);
    }
    float f = buffer.readFloat();
    int i = buffer.readVarInt();
    boolean st = buffer.readBoolean();
    return new GrinderRecipe(recipeId, s, ingredient, itemstack, result, f, i, st);
  }

  @Override
  public void write(PacketBuffer buffer, GrinderRecipe recipe) {
    buffer.writeString(recipe.getGroup());
    recipe.getIngredients().forEach(o -> o.write(buffer));
    if (recipe.hasTagResult()) {
      buffer.writeBoolean(false);
      recipe.getResultIngredient().write(buffer);
    } else {
      buffer.writeBoolean(true);
      buffer.writeItemStack(recipe.getRecipeOutput());
    }
    buffer.writeFloat(recipe.getExperience());
    buffer.writeVarInt(recipe.getCookTime());
    buffer.writeBoolean(recipe.hasStaticOutput());
  }
}
