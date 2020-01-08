package noobanidus.mods.grindr.client.screen;

import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.grindr.containers.GrinderContainer;

@OnlyIn(Dist.CLIENT)
public class GrinderScreen extends AbstractFurnaceScreen<GrinderContainer> {
  private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/furnace.png");

  public GrinderScreen(GrinderContainer p_i51089_1_, PlayerInventory p_i51089_2_, ITextComponent p_i51089_3_) {
    super(p_i51089_1_, new GrinderRecipeGui(), p_i51089_2_, p_i51089_3_, FURNACE_GUI_TEXTURES);
  }

  @Override
  public void init() {
    super.init();
    this.buttons.removeIf(o -> o instanceof ImageButton);
    this.children.removeIf(o -> o instanceof ImageButton);
  }
}

