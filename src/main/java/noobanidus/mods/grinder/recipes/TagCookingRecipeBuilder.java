package noobanidus.mods.grinder.recipes;

import com.google.gson.JsonObject;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger.Instance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.*;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.init.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class TagCookingRecipeBuilder<T extends AbstractCookingRecipe> {
  protected final ITag.INamedTag<Item> result;
  protected final Ingredient ingredient;
  protected final float experience;
  protected final int cookingTime;
  protected final Builder advancementBuilder = Builder.builder();
  protected String group;
  protected IRecipeSerializer<?> serializer;

  protected TagCookingRecipeBuilder(IRecipeSerializer<?> serializer, ITag.INamedTag<Item> result, Ingredient ingredient, float xp, int cookTime) {
    this.result = result;
    this.ingredient = ingredient;
    this.experience = xp;
    this.cookingTime = cookTime;
    this.serializer = serializer;
  }

  public static TagCookingRecipeBuilder<BlastingRecipe> blastingRecipe (Ingredient ingredient, ITag.INamedTag<Item> result, float xp, int cookTime) {
    return new TagCookingRecipeBuilder<>(ModRecipes.TAG_BLASTING_SERIALIZER.get(), result, ingredient, xp, cookTime);
  }

  public static TagCookingRecipeBuilder<FurnaceRecipe> furnaceRecipe (Ingredient ingredient, ITag.INamedTag<Item> result, float xp, int cookTime) {
    return new TagCookingRecipeBuilder<>(ModRecipes.TAG_FURNACE_SERIALIZER.get(), result, ingredient, xp, cookTime);
  }

  public static void ingot (IItemProvider input, ITag.INamedTag<Item> output, RegistrateRecipeProvider consumer) {
    Ingredient inputIngredient = Ingredient.fromItems(input.asItem());
    String outputPath = output.getName().getPath().replace("ingots/", "");
    furnaceRecipe(inputIngredient, output, 0.125f, 200).addCriterion("has_dust", consumer.hasItem(input)).build(consumer, new ResourceLocation(Grinder.MODID, "smelting/" + outputPath + "_by_smelting"));
    blastingRecipe(inputIngredient, output, 0.125f, 100).addCriterion("has_dust", consumer.hasItem(input)).build(consumer, new ResourceLocation(Grinder.MODID, "blasting/" + outputPath + "_by_blasting"));
  }

  public TagCookingRecipeBuilder<T> addCriterion(String name, ICriterionInstance instance) {
    this.advancementBuilder.withCriterion(name, instance);
    return this;
  }

  public IRecipeSerializer<?> getSerializer() {
    return serializer;
  }

  public void build(Consumer<IFinishedRecipe> consumer) {
    throw new IllegalArgumentException("tag-based recipes require a name or resourcelocation passed to the builder");
  }

  public void build(Consumer<IFinishedRecipe> consumer, String name) {
    ResourceLocation recipeName = new ResourceLocation(name);
    this.build(consumer, recipeName);
  }

  public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation resource) {
    this.validate(resource);
    this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new Instance(EntityPredicate.AndPredicate.ANY_AND, resource)).withRewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(resource)).withRequirementsStrategy(IRequirementsStrategy.OR);
    ConditionalRecipe.builder().addCondition(new NotCondition(new TagEmptyCondition(result.getName()))).addRecipe(new TagCookingRecipeBuilder.Result(resource, this.group == null ? "" : this.group, this.ingredient, Ingredient.fromTag(this.result), this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(resource.getNamespace(), "recipes/" + resource.getNamespace() + "/" + resource.getPath()), getSerializer())).setAdvancement(new ResourceLocation(Grinder.MODID, "recipes/" + resource.getPath()), ConditionalAdvancement.builder().addCondition(new NotCondition(new TagEmptyCondition(result.getName()))).addAdvancement(this.advancementBuilder)).build(consumer, resource);
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
    private final Ingredient result;
    private final float experience;
    private final int cookingTime;
    private final Builder advancementBuilder;
    private final ResourceLocation advancementId;
    private final IRecipeSerializer<?> serializer;

    public Result(ResourceLocation resource, String group, Ingredient ingredient, Ingredient output, float xp, int cookTime, Builder advBuilder, ResourceLocation advResource, IRecipeSerializer<?> serializer) {
      this.id = resource;
      this.group = group;
      this.ingredient = ingredient;
      this.result = output;
      this.experience = xp;
      this.cookingTime = cookTime;
      this.advancementBuilder = advBuilder;
      this.advancementId = advResource;
      this.serializer = serializer;
    }

    @Override
    public void serialize(JsonObject json) {
      if (!this.group.isEmpty()) {
        json.addProperty("group", this.group);
      }

      json.add("ingredient", this.ingredient.serialize());
      json.add("result", this.result.serialize());
      json.addProperty("experience", this.experience);
      json.addProperty("cookingtime", this.cookingTime);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
      return serializer;
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

