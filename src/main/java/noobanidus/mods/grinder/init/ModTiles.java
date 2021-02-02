package noobanidus.mods.grinder.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tileentity.TileEntityType;
import noobanidus.mods.grinder.tiles.GrinderTile;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModTiles {
  public static final RegistryEntry<TileEntityType<GrinderTile>> GRINDER = REGISTRATE.tileEntity("grinder", GrinderTile::new).validBlock(NonNullSupplier.of(ModBlocks.GRINDER)).register();

  public static void load() {

  }
}

