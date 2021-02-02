package noobanidus.mods.grinder.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.grinder.client.screen.GrinderScreen;
import noobanidus.mods.grinder.init.ModContainers;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ScreenManager.registerFactory(ModContainers.GRINDER_CONTAINER.get(), GrinderScreen::new);
  }
}
