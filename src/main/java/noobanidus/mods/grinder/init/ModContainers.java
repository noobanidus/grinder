package noobanidus.mods.grinder.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.inventory.container.ContainerType;
import noobanidus.mods.grinder.containers.GrinderContainer;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModContainers {
  public static final RegistryEntry<ContainerType<GrinderContainer>> GRINDER_CONTAINER = REGISTRATE.object("grinder").containerType(GrinderContainer::new).register();

  public static void load() {
  }
}
