package noobanidus.mods.grindr.integration.jei;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.init.ModBlocks;
import noobanidus.mods.grindr.recipes.GrinderRecipe;

public class GrinderCategory extends AbstractCookingCategory<GrinderRecipe> {
  public static ResourceLocation UID = new ResourceLocation(Grindr.MODID, "grinder_category");

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
