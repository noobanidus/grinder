package noobanidus.mods.grindr.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.mods.grindr.Grindr;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
  private static ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  private static Map<String, ForgeConfigSpec.BooleanValue> CONFIG_MAP = new HashMap<>();
  public static Map<String, ForgeConfigSpec.DoubleValue> RESULT_MODIFIER = new HashMap<>();
  public static Map<String, ForgeConfigSpec.DoubleValue> SPEED_MODIFIER = new HashMap<>();

  public static ForgeConfigSpec.ConfigValue<Boolean> SHOW_HUD;

  private static List<String> dusts = Arrays.asList("gold_dust", "iron_dust", "silver_dust", "copper_dust", "tin_dust", "nickel_dust", "lead_dust", "aluminum_dust", "uranium_dust", "zinc_dust", "platinum_dust", "mercury_dust", "bismuth_dust", "neptunium_dust");

  static {
    COMMON_BUILDER.push("dust_settings");
    for (String d : dusts) {
      String dust = d.toLowerCase();
      CONFIG_MAP.put(dust, COMMON_BUILDER.define("hide_" + dust, true));
    }
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("grindstone_settings");
    COMMON_BUILDER.push("stone");
    RESULT_MODIFIER.put("stone", COMMON_BUILDER.defineInRange("stone_result_modifier", 1.1, 0, 10));
    SPEED_MODIFIER.put("stone", COMMON_BUILDER.defineInRange("stone_speed_modifier", 1.3, 0, 10));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("granite");
    RESULT_MODIFIER.put("granite", COMMON_BUILDER.defineInRange("granite_result_modifier", 1.1, 0, 10));
    SPEED_MODIFIER.put("granite", COMMON_BUILDER.defineInRange("granite_speed_modifier", 1.3, 0, 10));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("diorite");
    RESULT_MODIFIER.put("diorite", COMMON_BUILDER.defineInRange("diorite_result_modifier", 1.1, 0, 10));
    SPEED_MODIFIER.put("diorite", COMMON_BUILDER.defineInRange("diorite_speed_modifier", 1.3, 0, 10));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("andesite");
    RESULT_MODIFIER.put("andesite", COMMON_BUILDER.defineInRange("andesite_result_modifier", 1.1, 0, 10));
    SPEED_MODIFIER.put("andesite", COMMON_BUILDER.defineInRange("andesite_speed_modifier", 1.3, 0, 10));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("iron");
    RESULT_MODIFIER.put("iron", COMMON_BUILDER.defineInRange("iron_result_modifier", 1.7, 0, 10));
    SPEED_MODIFIER.put("iron", COMMON_BUILDER.defineInRange("iron_speed_modifier", 1.0, 0, 10));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("gold");
    RESULT_MODIFIER.put("gold", COMMON_BUILDER.defineInRange("gold_result_modifier", 1.4, 0, 10));
    SPEED_MODIFIER.put("gold", COMMON_BUILDER.defineInRange("gold_speed_modifier", 0.5, 0, 10));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("diamond");
    RESULT_MODIFIER.put("diamond", COMMON_BUILDER.defineInRange("diamond_result_modifier", 2.0, 0, 10));
    SPEED_MODIFIER.put("diamond", COMMON_BUILDER.defineInRange("diamond_speed_modifier", 0.7, 0, 10));
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("emerald");
    RESULT_MODIFIER.put("emerald", COMMON_BUILDER.defineInRange("emerald_result_modifier", 2.5, 0, 10));
    SPEED_MODIFIER.put("emerald", COMMON_BUILDER.defineInRange("emerald_speed_modifier", 1.0, 0, 10));
    COMMON_BUILDER.pop();
    COMMON_BUILDER.pop();

    COMMON_BUILDER.push("client").comment("whether or not a helpful (but annoying) hud should be displayed when targeting the grinder");
    SHOW_HUD = COMMON_BUILDER.define("show_grinder_hud", false);
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  @SuppressWarnings("ConstantConditions")
  public static NonNullUnaryOperator<Item.Properties> getProperty(String dust) {
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
