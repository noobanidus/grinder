/*package noobanidus.mods.grindr.client.hud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ManageHUD {
  private static List<TileEntityHUD<?>> huds = new ArrayList<>();

  public static <T extends TileEntityHUD<?>> void register(T renderer) {
    huds.add(renderer);
  }

  public static void onRender(RenderGameOverlayEvent.Post event) {
    if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
      return;
    }

    Minecraft mc = Minecraft.getInstance();
    boolean isChat = mc.currentScreen instanceof ChatScreen;

    RayTraceResult result = mc.objectMouseOver;
    if (result == null || result.getType() != RayTraceResult.Type.BLOCK) {
      return;
    }

    BlockRayTraceResult blockResult = (BlockRayTraceResult) result;
    BlockPos pos = blockResult.getPos();
    BlockState state = mc.world.getBlockState(pos);
    Block block = state.getBlock();

    for (TileEntityHUD<?> hud : huds) {
      if (isChat && hud.hideInChat()) {
        continue;
      }

      TileEntityType<?> teType = hud.getType();
      if (teType.isValidBlock(block)) {
        TileEntity te = mc.world.getTileEntity(pos);
        if (te != null && te.getType() == teType) {
          hud.renderBase(mc, pos, state, event, te);
        }
      }
    }
  }
}*/
