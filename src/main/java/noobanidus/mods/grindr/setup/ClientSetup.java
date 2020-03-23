package noobanidus.mods.grindr.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.grindr.client.screen.GrinderScreen;
import noobanidus.mods.grindr.init.ModContainers;
import noobanidus.mods.shoulders.common.bootstrap.Bootstrap;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ScreenManager.registerFactory(ModContainers.GRINDER_CONTAINER.get(), GrinderScreen::new);
    Bootstrap.init(event.getMinecraftSupplier().get().getRenderManager().getSkinMap().values());
  }
}
