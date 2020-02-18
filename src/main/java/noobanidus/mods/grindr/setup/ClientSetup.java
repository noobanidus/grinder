package noobanidus.mods.grindr.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.animation.TileEntityRendererAnimation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.grindr.client.hud.GrindstoneHUD;
import noobanidus.mods.grindr.client.hud.ManageHUD;
import noobanidus.mods.grindr.client.render.GrinderTileRenderer;
import noobanidus.mods.grindr.client.screen.GrinderScreen;
import noobanidus.mods.grindr.config.ConfigManager;
import noobanidus.mods.grindr.init.ModContainers;
import noobanidus.mods.grindr.tiles.GrinderTile;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    if (ConfigManager.SHOW_HUD.get()) {
      ManageHUD.register(new GrindstoneHUD());
    }
    ScreenManager.registerFactory(ModContainers.GRINDER_CONTAINER.get(), GrinderScreen::new);
    ClientRegistry.bindTileEntitySpecialRenderer(GrinderTile.class, new GrinderTileRenderer());
  }
}
