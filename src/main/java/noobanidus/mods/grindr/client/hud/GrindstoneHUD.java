package noobanidus.mods.grindr.client.hud;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.blocks.GrinderBlock;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.init.ModTiles;
import noobanidus.mods.grindr.items.GrindstoneItem;
import noobanidus.mods.grindr.tiles.GrinderTile;

@OnlyIn(Dist.CLIENT)
public class GrindstoneHUD extends TileEntityHUD<GrinderTile> {
  public static ResourceLocation RIGHT_CLICK = new ResourceLocation(Grindr.MODID, "textures/gui/right_click_icon.png");
  public static final int ICON_WIDTH = 9, ICON_HEIGHT = 11;

  public GrindstoneHUD() {
    super(ModTiles.GRINDER.get());
  }

  @Override
  public void render(Minecraft mc, BlockPos pos, BlockState state, RenderGameOverlayEvent.Post event, GrinderTile tile) {
    if (!state.has(GrinderBlock.GRINDSTONE)) {
      Grindr.LOG.error("Invalid block state passed to GrinderTile renderer.");
      return;
    }

    int x = mc.mainWindow.getScaledWidth() / 2;
    int y = mc.mainWindow.getScaledHeight() / 2;

    GrindstoneType type = state.get(GrinderBlock.GRINDSTONE);
    ItemStack inHand = mc.player.getHeldItemMainhand();
    boolean hasGrindstone = !(inHand.getItem() instanceof GrindstoneItem);
    boolean emptyHand = inHand.isEmpty();
    String text1;
    String text2;
    if (type == GrindstoneType.EMPTY && !hasGrindstone) {
      text1 = I18n.format("grinder.hud.right_click");
      text2 = I18n.format("grinder.hud.install");
    } else if (type != GrindstoneType.EMPTY) {
      text2 = I18n.format("grinder.hud.remove");
      if (emptyHand) {
        text1 = (!mc.player.isSneaking() ? TextFormatting.RED : TextFormatting.GREEN) + I18n.format("grinder.hud.sneak") + TextFormatting.RESET + I18n.format("grinder.hud.right_click");
      } else {
        text1 = I18n.format("grinder.hud.requires_empty");
      }
    } else {
      return;
    }

    mc.fontRenderer.drawStringWithShadow(text1, x + 10, y + 10, 16777215);
    mc.fontRenderer.drawStringWithShadow(text2, x + 10, y + 20, 16777215);
    mc.getTextureManager().bindTexture(RIGHT_CLICK);
    int u = 0;
    int v = 0;
    int height = ICON_HEIGHT;
    int width = ICON_WIDTH;
    float textureWidth = ICON_WIDTH;
    float textureHeight = ICON_HEIGHT;

    x += 10;
    y -= 5;

    float f = 1.0F / textureWidth;
    float f1 = 1.0F / textureHeight;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
    bufferbuilder.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
    bufferbuilder.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
    bufferbuilder.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
    tessellator.draw();
  }
}
