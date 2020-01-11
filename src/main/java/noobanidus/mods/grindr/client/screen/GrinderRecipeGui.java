package noobanidus.mods.grindr.client.screen;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.item.Item;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class GrinderRecipeGui extends AbstractRecipeBookGui {
  @Override
  protected boolean func_212962_b() {
    return this.recipeBook.func_216761_f();
  }

  @Override
  protected void func_212959_a(boolean p_212959_1_) {
    this.recipeBook.func_216756_f(p_212959_1_);
  }

  @Override
  protected boolean func_212963_d() {
    return this.recipeBook.func_216758_e();
  }

  @Override
  protected void func_212957_c(boolean p_212957_1_) {
    this.recipeBook.func_216755_e(p_212957_1_);
  }

  @Override
  protected String func_212960_g() {
    return "gui.recipebook.toggleRecipes.blastable";
  }

  @Override
  @SuppressWarnings("deprecation")
  protected Set<Item> func_212958_h() {
    return AbstractFurnaceTileEntity.getBurnTimes().keySet();
  }
}

