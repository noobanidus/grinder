package noobanidus.mods.grindr.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.grindr.containers.GrinderContainer;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModContainers {
  public static final RegistryObject<ContainerType<GrinderContainer>> GRINDER_CONTAINER = REGISTRATE.object("grinder").containerType(GrinderContainer::new).register();

  public static void load() {
  }
}
