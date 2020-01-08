package noobanidus.mods.grindr.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.items.GroundItem;

import java.awt.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
  private static ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  public static ForgeConfigSpec CLIENT_CONFIG;
  private static Map<String, ForgeConfigSpec.ConfigValue<Boolean>> CONFIG_MAP = new HashMap<>();
  public static Map<String, ForgeConfigSpec.ConfigValue<Float>> RESULT_MODIFIER = new HashMap<>();
  public static Map<String, ForgeConfigSpec.ConfigValue<Float>> SPEED_MODIFIER = new HashMap<>();

  public static ForgeConfigSpec.ConfigValue<Boolean> SHOW_HUD;

  private static List<String> dusts = Arrays.asList("gold_dust", "iron_dust", "silver_dust", "copper_dust", "tin_dust", "nickel_dust", "lead_dust", "aluminum_dust", "uranium_dust", "zinc_dust", "platinum_dust", "mercury_dust", "bismuth_dust", "neptunium_dust");

  static {
    COMMON_BUILDER.push("dust_settings");
    for (String d : dusts) {
      String dust = d.toLowerCase();
      CONFIG_MAP.put(dust, COMMON_BUILDER.comment("whether or not " + dust.replace("_", " ") + " should be hidden").define(dust, true));
    }
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("grindstone_settings");
    COMMON_BUILDER.push("stone");
    RESULT_MODIFIER.put("stone", COMMON_BUILDER.comment("result modifier for stone grindstone").define("stone_result_modifier", 1.1f));
    SPEED_MODIFIER.put("stone", COMMON_BUILDER.comment("speed modifier for stone grindstone").define("stone_speed_modifier", 1.3f));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("granite");
    RESULT_MODIFIER.put("granite", COMMON_BUILDER.comment("result modifier for granite grindstone").define("granite_result_modifier", 1.1f));
    SPEED_MODIFIER.put("granite", COMMON_BUILDER.comment("speed modifier for granite grindstone").define("granite_speed_modifier", 1.3f));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("diorite");
    RESULT_MODIFIER.put("diorite", COMMON_BUILDER.comment("result modifier for diorite grindstone").define("diorite_result_modifier", 1.1f));
    SPEED_MODIFIER.put("diorite", COMMON_BUILDER.comment("speed modifier for diorite grindstone").define("diorite_speed_modifier", 1.3f));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("andesite");
    RESULT_MODIFIER.put("andesite", COMMON_BUILDER.comment("result modifier for andesite grindstone").define("andesite_result_modifier", 1.1f));
    SPEED_MODIFIER.put("andesite", COMMON_BUILDER.comment("speed modifier for andesite grindstone").define("andesite_speed_modifier", 1.3f));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("iron");
    RESULT_MODIFIER.put("iron", COMMON_BUILDER.comment("result modifier for iron grindstone").define("iron_result_modifier", 1.7f));
    SPEED_MODIFIER.put("iron", COMMON_BUILDER.comment("speed modifier for iron grindstone").define("iron_speed_modifier", 1f));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("gold");
    RESULT_MODIFIER.put("gold", COMMON_BUILDER.comment("result modifier for gold grindstone").define("gold_result_modifier", 1.4f));
    SPEED_MODIFIER.put("gold", COMMON_BUILDER.comment("speed modifier for gold grindstone").define("gold_speed_modifier", 0.5f));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("diamond");
    RESULT_MODIFIER.put("diamond", COMMON_BUILDER.comment("result modifier for diamond grindstone").define("diamond_result_modifier", 2.0f));
    SPEED_MODIFIER.put("diamond", COMMON_BUILDER.comment("speed modifier for diamond grindstone").define("diamond_speed_modifier", 0.7f));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("emerald");
    RESULT_MODIFIER.put("emerald", COMMON_BUILDER.comment("result modifier for emerald grindstone").define("emerald_result_modifier", 2.5f));
    SPEED_MODIFIER.put("emerald", COMMON_BUILDER.comment("speed modifier for emerald grindstone").define("emerald_speed_modifier", 1f));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.pop();

    COMMON_CONFIG = COMMON_BUILDER.build();

    SHOW_HUD = CLIENT_BUILDER.comment("whether or not the HUD should display on the grinder").define("show_grinder_hud", false);
    CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  @SuppressWarnings("ConstantConditions")
  public static NonNullUnaryOperator<Item.Properties> getProperty (String dust) {
    ForgeConfigSpec.ConfigValue<Boolean> val = CONFIG_MAP.get(dust);
    if (val == null || !val.get()) {
      return (o) -> o.group(null);
    } else {
      return (o) -> o.group(Grindr.ITEM_GROUP);
    }
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }
}
