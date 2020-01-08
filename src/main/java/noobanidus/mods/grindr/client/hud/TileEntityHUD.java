package noobanidus.mods.grindr.client.hud;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@OnlyIn(Dist.CLIENT)
public abstract class TileEntityHUD<T extends TileEntity> {
  private TileEntityType<T> type;

  public TileEntityHUD(TileEntityType<T> type) {
    this.type = type;
  }

  public TileEntityType<T> getType() {
    return type;
  }

  public boolean hideInChat () {
    return true;
  }

  @SuppressWarnings("unchecked")
  public void renderBase (Minecraft mc, BlockPos pos, BlockState state, RenderGameOverlayEvent.Post event, TileEntity te) {
    render(mc, pos, state, event, (T) te);
  }

  public abstract void render (Minecraft mc, BlockPos pos, BlockState state, RenderGameOverlayEvent.Post event, T tile);
}
