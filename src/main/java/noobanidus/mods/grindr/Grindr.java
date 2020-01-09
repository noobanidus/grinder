package noobanidus.mods.grindr;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.client.hud.ManageHUD;
import noobanidus.mods.grindr.config.ConfigManager;
import noobanidus.mods.grindr.init.*;
import noobanidus.mods.grindr.registrate.CustomRegistrate;
import noobanidus.mods.grindr.setup.ClientSetup;
import noobanidus.mods.grindr.setup.CommonSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("grindr")
public class Grindr {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "grindr";

  public static final ItemGroup ITEM_GROUP = new ItemGroup("grindr") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.GRINDSTONE_MAP.get(GrindstoneType.DIAMOND).get());
    }
  };

  public static CustomRegistrate REGISTRATE;

  public Grindr() {
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Grindr.MODID + "-common.toml"));
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(CommonSetup::init);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
      modBus.addListener(ClientSetup::init);
      MinecraftForge.EVENT_BUS.addListener(ManageHUD::onRender);
    });

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(NonNullSupplier.of(() -> ITEM_GROUP));
    ModItems.load();
    ModBlocks.load();
    ModTiles.load();
    ModRecipes.load();
    ModContainers.load();
    ModLang.load();
  }
}
