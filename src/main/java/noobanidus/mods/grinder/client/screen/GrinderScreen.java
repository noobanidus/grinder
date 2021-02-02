package noobanidus.mods.grinder.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.containers.GrinderContainer;

@OnlyIn(Dist.CLIENT)
public class GrinderScreen extends ContainerScreen<GrinderContainer> {
  private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(Grinder.MODID, "textures/gui/grinder.png");
  private static final ResourceLocation GRINDSTONE_TEXTURE = new ResourceLocation(Grinder.MODID, "textures/gui/grindstone.png");

  public GrinderScreen(GrinderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  public void render(MatrixStack stack, int x, int y, float partialTicks) {
    this.renderBackground(stack);
    super.render(stack, x, y, partialTicks);
    this.renderHoveredTooltip(stack, x, y);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
      this.font.func_243248_b(stack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
      this.font.func_243248_b(stack, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY, 4210752);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
    this.minecraft.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
    int i = this.guiLeft;
    int j = this.guiTop;
    this.blit(stack, i, j, 0, 0, this.xSize, this.ySize);
    if (this.container.isBurning()) {
      int k = this.container.getBurnLeftScaled();
      this.blit(stack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
    }

    if (!this.container.isEmpty()) {
      int l = this.container.getCookProgressionScaled();
      this.blit(stack, i + 79, j + 34, 176, 14, l + 1, 16);
    } else {
      this.blit(stack, i + 79, j + 34, 176, 31, 18, 16);
    }
  }
}

