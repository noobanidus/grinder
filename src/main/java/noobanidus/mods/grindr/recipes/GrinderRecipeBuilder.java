package noobanidus.mods.grindr.recipes;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger.Instance;
import net.minecraft.client.util.JSONException;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.ForgeRegistries;
import netscape.javascript.JSException;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.init.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@SuppressWarnings("Duplicates")
public class GrinderRecipeBuilder {
  protected final Item result;
  protected final Ingredient resultIngredient;
  protected final Item input;
  protected final Ingredient ingredient;
  protected final float experience;
  protected String group;
  private final int cookingTime;
  private final Builder advancementBuilder = Builder.builder();
  private final int count;
  private final boolean staticOutput;
  private Tag<Item> tag;

  protected GrinderRecipeBuilder(IItemProvider result, Tag<Item> tag, float xp, int cookTime, int count, boolean staticOutput) {
    this.result = result.asItem();
    this.experience = xp;
    this.cookingTime = cookTime;
    this.tag = tag;
    this.ingredient = Ingredient.fromTag(tag);
    this.addOre(tag);
    this.count = count;
    this.staticOutput = staticOutput;
    this.input = null;
    this.resultIngredient = null;
  }

  protected GrinderRecipeBuilder(Tag<Item> result, IItemProvider input, float xp, int cookTime, int count, boolean staticOutput) {
    this.result = Items.AIR;
    this.experience = xp;
    this.cookingTime = cookTime;
    this.tag = null;
    this.ingredient = Ingredient.fromItems(input);
    this.addInput(input.asItem());
    this.count = count;
    this.staticOutput = staticOutput;
    this.input = input.asItem();
    this.resultIngredient = Ingredient.fromTag(result);
  }

  protected GrinderRecipeBuilder(IItemProvider result, IItemProvider input, float xp, int cookTime, int count, boolean staticOutput) {
    this.result = result.asItem();
    this.experience = xp;
    this.cookingTime = cookTime;
    this.tag = null;
    this.input = input.asItem();
    this.ingredient = Ingredient.fromItems(this.input);
    this.staticOutput = staticOutput;
    this.count = count;
    this.addInput(this.input);
    this.resultIngredient = null;
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, Tag<Item> tag) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, 1, false);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, IItemProvider input) {
    return new GrinderRecipeBuilder(result, input, 0.125f, 100, 1, false);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, Tag<Item> tag, int count) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, count, false);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, IItemProvider input, int count) {
    return new GrinderRecipeBuilder(result, input, 0.125f, 100, count, false);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, Tag<Item> tag, int count, boolean staticOutput) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, count, staticOutput);
  }

  public static GrinderRecipeBuilder builder (Tag<Item> result, IItemProvider tag, int count, boolean staticOutput) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, count, staticOutput);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, IItemProvider tag, int count, boolean staticOutput) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, count, staticOutput);
  }

  private void addOre(Tag<Item> ore) {
    this.advancementBuilder.withCriterion("has_ore", new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, new ItemPredicate[]{ItemPredicate.Builder.create().tag(ore).build()}));
  }

  private void addInput (Item input) {
    this.advancementBuilder.withCriterion("has_input", new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, new ItemPredicate[]{ItemPredicate.Builder.create().item(input).build()}));
  }

  public void build(Consumer<IFinishedRecipe> consumer) {
    this.build(consumer, new ResourceLocation(Grindr.MODID, "grinding/" + ForgeRegistries.ITEMS.getKey(this.result).getPath()));
  }

  public void build(Consumer<IFinishedRecipe> consumer, String name) {
    ResourceLocation resultName = ForgeRegistries.ITEMS.getKey(this.result);
    ResourceLocation recipeName = new ResourceLocation(Grindr.MODID, "grinding/" + name);
    if (recipeName.equals(resultName)) {
      throw new IllegalStateException("Recipe " + recipeName + " should remove its 'save' argument");
    } else {
      this.build(consumer, recipeName);
    }
  }

  public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation resource) {
    this.validate(resource);
    this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new Instance(resource)).withRewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(resource)).withRequirementsStrategy(IRequirementsStrategy.OR);
    if (tag != null) {
      ConditionalRecipe.builder().addCondition(new NotCondition(new TagEmptyCondition(tag.getId()))).addRecipe(new GrinderRecipeBuilder.Result(resource, this.group == null ? "" : this.group, this.ingredient, this.result, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + resource.getPath()), resultIngredient, count, staticOutput)).setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/" + resource.getPath()), ConditionalAdvancement.builder().addCondition(new NotCondition(new TagEmptyCondition(tag.getId()))).addAdvancement(this.advancementBuilder)).build(consumer, resource);
    } else {
      consumer.accept(new GrinderRecipeBuilder.Result(resource, this.group == null ? "" : this.group, this.ingredient, this.result, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + resource.getPath()), resultIngredient, count, staticOutput));
    }
  }

  private void validate(ResourceLocation resource) {
    if (this.advancementBuilder.getCriteria().isEmpty()) {
      throw new IllegalStateException("No way of obtaining recipe " + resource);
    }
  }

  @SuppressWarnings("Duplicates")
  public static class Result implements IFinishedRecipe {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final Item result;
    private final float experience;
    private final int cookingTime;
    private final Builder advancementBuilder;
    private final ResourceLocation advancementId;
    private final boolean staticOutput;
    private final int count;
    private final Ingredient resultIngredient;

    public Result(ResourceLocation resource, String group, Ingredient ingredient, Item output, float xp, int cookTime, Builder advBuilder, ResourceLocation advResource, Ingredient resultIngredient, int count, boolean staticOutput) {
      this.id = resource;
      this.group = group;
      this.ingredient = ingredient;
      this.result = output;
      this.experience = xp;
      this.cookingTime = cookTime;
      this.advancementBuilder = advBuilder;
      this.advancementId = advResource;
      this.staticOutput = staticOutput;
      this.count = count;
      this.resultIngredient = resultIngredient;
    }

    @Override
    public void serialize(JsonObject json) {
      if (!this.group.isEmpty()) {
        json.addProperty("group", this.group);
      }

      json.add("ingredient", this.ingredient.serialize());
      if (result == Items.AIR && resultIngredient != null) {
        json.addProperty("tagResult", true);
        json.add("result", this.resultIngredient.serialize());
      } else if (result != Items.AIR) {
        json.addProperty("tagResult", false);
        json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
      } else {
        throw new IllegalArgumentException("Invalid recipe: must have a result of some kind.");
      }
      json.addProperty("experience", this.experience);
      json.addProperty("cookingtime", this.cookingTime);
      if (staticOutput) {
        json.addProperty("static", true);
      }
      if (count > 1) {
        json.addProperty("count", count);
      }
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
      return ModRecipes.GRINDER_SERIALIZER.get();
    }

    @Override
    public ResourceLocation getID() {
      return this.id;
    }

    @Override
    @Nullable
    public JsonObject getAdvancementJson() {
      return this.advancementBuilder.serialize();
    }

    @Override
    @Nullable
    public ResourceLocation getAdvancementID() {
      return this.advancementId;
    }
  }
}

