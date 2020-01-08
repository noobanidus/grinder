package noobanidus.mods.grindr.integration.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.init.ModBlocks;
import noobanidus.mods.grindr.init.ModItems;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrindstoneCategory implements IRecipeCategory<GrindstoneCategory.GrindstoneRecipe> {
  public static ResourceLocation UID = new ResourceLocation(Grindr.MODID, "grindstone_category");
  public static ResourceLocation BACKGROUND = new ResourceLocation(Grindr.MODID, "textures/gui/grindstone_jei.png");

  public static final int WIDTH = 68, HEIGHT = 26;

  public final IDrawable background, icon;

  public static List<GrindstoneRecipe> RECIPE_LIST = Stream.of(GrindstoneType.values()).filter(o -> o != GrindstoneType.EMPTY).map(GrindstoneRecipe::new).collect(Collectors.toList());

  public GrindstoneCategory(IGuiHelper guiHelper) {
    this.background = guiHelper.drawableBuilder(BACKGROUND, 0, 0, WIDTH, HEIGHT).build();
    this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.GRINDER.get()));
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends GrindstoneRecipe> getRecipeClass() {
    return GrindstoneRecipe.class;
  }

  @Override
  public String getTitle() {
    return I18n.format("grinder.jei.grindstone");
  }

  @Override
  public IDrawable getBackground() {
    return background;
  }

  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public void setIngredients(GrindstoneRecipe grindstoneRecipe, IIngredients iIngredients) {
    iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(ModItems.GRINDSTONE_MAP.get(grindstoneRecipe.getType()).get()));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModBlocks.GRINDER.get()));
  }

  @Override
  public void setRecipe(IRecipeLayout iRecipeLayout, GrindstoneRecipe grindstoneRecipe, IIngredients iIngredients) {
    IGuiItemStackGroup group = iRecipeLayout.getItemStacks();

    group.init(0, true, 0, 4);
    group.set(0, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
    group.init(1, true, 50, 4);
    group.set(1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
  }

  public static class GrindstoneRecipe {
    private GrindstoneType type;

    public GrindstoneRecipe(GrindstoneType type) {
      this.type = type;
    }

    public GrindstoneType getType() {
      return type;
    }
  }
}
