package noobanidus.mods.grindr.init;

import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.inventory.container.ContainerType;
import noobanidus.mods.grindr.containers.GrinderContainer;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModContainers {
  public static final RegistryEntry<ContainerType<GrinderContainer>> GRINDER_CONTAINER = REGISTRATE.object("grinder").containerType(GrinderContainer::new).register();

  public static void load() {
  }
}
