package noobanidus.mods.grindr.client.screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.containers.GrinderContainer;

@OnlyIn(Dist.CLIENT)
public class GrinderScreen extends ContainerScreen<GrinderContainer> {
  private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(Grindr.MODID, "textures/gui/grinder.png");
  private static final ResourceLocation GRINDSTONE_TEXTURE = new ResourceLocation(Grindr.MODID, "textures/gui/grindstone.png");

  public GrinderScreen(GrinderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    String s = this.title.getFormattedText();
    this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
    this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    this.minecraft.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
    int i = this.guiLeft;
    int j = this.guiTop;
    this.blit(i, j, 0, 0, this.xSize, this.ySize);
    if (this.container.isBurning()) {
      int k = this.container.getBurnLeftScaled();
      this.blit(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
    }

    if (!this.container.isEmpty()) {
      int l = this.container.getCookProgressionScaled();
      this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
    } else {
      this.blit(i + 79, j + 34, 176, 31, 18, 16);
    }
  }
}

