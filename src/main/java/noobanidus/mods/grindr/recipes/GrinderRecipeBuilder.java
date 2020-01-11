package noobanidus.mods.grindr.recipes;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger.Instance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
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
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.init.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class GrinderRecipeBuilder {
  protected final Item result;
  protected final Ingredient ingredient;
  protected final float experience;
  protected String group;
  private final int cookingTime;
  private final Builder advancementBuilder = Builder.builder();
  private final boolean staticOutput;
  private Tag<Item> tag;

  protected GrinderRecipeBuilder(IItemProvider result, Tag<Item> tag, float xp, int cookTime, boolean staticOutput) {
    this.result = result.asItem();
    this.experience = xp;
    this.cookingTime = cookTime;
    this.tag = tag;
    this.ingredient = Ingredient.fromTag(tag);
    this.addOre(tag);
    this.staticOutput = staticOutput;
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, Tag<Item> tag) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, false);
  }

  public static GrinderRecipeBuilder builder (IItemProvider result, Tag<Item> tag, boolean staticOutput) {
    return new GrinderRecipeBuilder(result, tag, 0.125f, 100, staticOutput);
  }

  private void addOre(Tag<Item> ore) {
    this.advancementBuilder.withCriterion("has_ore", new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, new ItemPredicate[]{ItemPredicate.Builder.create().tag(ore).build()}));
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
    ConditionalRecipe.builder().addCondition(new NotCondition(new TagEmptyCondition(tag.getId()))).addRecipe(new GrinderRecipeBuilder.Result(resource, this.group == null ? "" : this.group, this.ingredient, this.result, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + resource.getPath()), staticOutput)).setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/" + resource.getPath()), ConditionalAdvancement.builder().addCondition(new NotCondition(new TagEmptyCondition(tag.getId()))).addAdvancement(this.advancementBuilder)).build(consumer, resource);
  }

  private void validate(ResourceLocation resource) {
    if (this.advancementBuilder.getCriteria().isEmpty()) {
      throw new IllegalStateException("No way of obtaining recipe " + resource);
    }
  }

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

    public Result(ResourceLocation resource, String group, Ingredient ingredient, Item output, float xp, int cookTime, Builder advBuilder, ResourceLocation advResource, boolean staticOutput) {
      this.id = resource;
      this.group = group;
      this.ingredient = ingredient;
      this.result = output;
      this.experience = xp;
      this.cookingTime = cookTime;
      this.advancementBuilder = advBuilder;
      this.advancementId = advResource;
      this.staticOutput = staticOutput;
    }

    @Override
    public void serialize(JsonObject json) {
      if (!this.group.isEmpty()) {
        json.addProperty("group", this.group);
      }

      json.add("ingredient", this.ingredient.serialize());
      json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
      json.addProperty("experience", this.experience);
      json.addProperty("cookingtime", this.cookingTime);
      if (staticOutput) {
        json.addProperty("static", staticOutput);
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

