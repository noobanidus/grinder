package noobanidus.mods.grinder;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.mods.grinder.blocks.GrinderBlock;
import noobanidus.mods.grinder.blocks.GrindstoneType;
import noobanidus.mods.grinder.config.ConfigManager;
import noobanidus.mods.grinder.init.*;
import noobanidus.mods.grinder.registrate.CustomRegistrate;
import noobanidus.mods.grinder.setup.ClientInit;
import noobanidus.mods.grinder.setup.ClientSetup;
import noobanidus.mods.grinder.setup.CommonSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("grinder")
public class Grinder {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "grinder";

  public static final ItemGroup ITEM_GROUP = new ItemGroup("grinder") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.GRINDSTONE_MAP.get(GrindstoneType.DIAMOND).get());
    }
  };

  public static CustomRegistrate REGISTRATE;

  public Grinder() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Grinder.MODID + "-common.toml"));
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(CommonSetup::init);

    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientInit::init);

    MinecraftForge.EVENT_BUS.addListener(GrinderBlock::onInteractWith);
    modBus.addListener(ConfigManager::configLoaded);
    modBus.addListener(ConfigManager::configReloaded);

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(NonNullSupplier.of(() -> ITEM_GROUP));
    ModItems.load();
    ModBlocks.load();
    ModTiles.load();
    ModRecipes.load();
    ModContainers.load();
    ModLang.load();
    ModTags.load();
  }
}
