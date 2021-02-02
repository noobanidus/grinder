package noobanidus.mods.grinder.integration.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.init.ModBlocks;
import noobanidus.mods.grinder.recipes.GrinderRecipe;

public class GrinderCategory extends AbstractCookingCategory<GrinderRecipe> {
  public static ResourceLocation UID = new ResourceLocation(Grinder.MODID, "grinder_category");

  public GrinderCategory(IGuiHelper guiHelper) {
    super(guiHelper, ModBlocks.GRINDER.get(), "grinder.jei.grinder", 100);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends GrinderRecipe> getRecipeClass() {
    return GrinderRecipe.class;
  }
}
