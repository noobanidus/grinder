package noobanidus.mods.grindr;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GrindrTags {
  public static class Items extends GrindrTags {
    public static Tag<Item> GOLD_ORE = compatTag("ores/gold");
    public static Tag<Item> IRON_ORE = compatTag("ores/iron");
    public static Tag<Item> SILVER_ORE = compatTag("ores/silver");
    public static Tag<Item> COPPER_ORE = compatTag("ores/copper");
    public static Tag<Item> TIN_ORE = compatTag("ores/tin");
    public static Tag<Item> NICKEL_ORE = compatTag("ores/nickel");
    public static Tag<Item> LEAD_ORE = compatTag("ores/lead");
    public static Tag<Item> ALUMINUM_ORE = compatTag("ores/aluminum");
    public static Tag<Item> URANIUM_ORE = compatTag("ores/uranium");
    public static Tag<Item> ZINC_ORE = compatTag("ores/zinc");
    public static Tag<Item> PLATINUM_ORE = compatTag("ores/platinum");
    public static Tag<Item> MERCURY_ORE = compatTag("ores/mercury");
    public static Tag<Item> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static Tag<Item> BISMUTH_ORE = compatTag("ores/bismuth");
    public static Tag<Item> NEPTUNIUM_ORE = compatTag("ores/neptunium");
    public static Tag<Item> OSMIUM_ORE = compatTag("ores/osmium");
    public static Tag<Item> ARDITE_ORE = compatTag("ores/ardite");
    public static Tag<Item> COBALT_ORE = compatTag("ores/cobalt");
    public static Tag<Item> ZITRITE_ORE = compatTag("ores/zitrite");
    public static Tag<Item> RAINBOW_ORE = compatTag("ores/rainbow");
    public static Tag<Item> STARMETAL_ORE = compatTag("ores/starmetal");
    public static Tag<Item> TUNGSTEN_ORE = compatTag("ores/tungsten");
    public static Tag<Item> IESNIUM_ORE = compatTag("ores/iesnium");

    public static Tag<Item> GOLD_INGOT = compatTag("ingots/gold");
    public static Tag<Item> IRON_INGOT = compatTag("ingots/iron");
    public static Tag<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static Tag<Item> COPPER_INGOT = compatTag("ingots/copper");
    public static Tag<Item> TIN_INGOT = compatTag("ingots/tin");
    public static Tag<Item> NICKEL_INGOT = compatTag("ingots/nickel");
    public static Tag<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static Tag<Item> ALUMINUM_INGOT = compatTag("ingots/aluminum");
    public static Tag<Item> URANIUM_INGOT = compatTag("ingots/uranium");
    public static Tag<Item> ZINC_INGOT = compatTag("ingots/zinc");
    public static Tag<Item> PLATINUM_INGOT = compatTag("ingots/platinum");
    public static Tag<Item> MERCURY_INGOT = compatTag("ingots/mercury");
    public static Tag<Item> QUICKSILVER_INGOT = compatTag("ingots/quicksilver");
    public static Tag<Item> BISMUTH_INGOT = compatTag("ingots/bismuth");
    public static Tag<Item> NEPTUNIUM_INGOT = compatTag("ingots/neptunium");
    public static Tag<Item> OSMIUM_INGOT = compatTag("ingots/osmium");
    public static Tag<Item> ARDITE_INGOT = compatTag("ingots/ardite");
    public static Tag<Item> COBALT_INGOT = compatTag("ingots/cobalt");
    public static Tag<Item> ZITRITE_INGOT = compatTag("ingots/zitrite");
    public static Tag<Item> RAINBOW_INGOT = compatTag("ingots/rainbow");
    public static Tag<Item> STARMETAL_INGOT = compatTag("ingots/starmetal");
    public static Tag<Item> TUNGSTEN_INGOT = compatTag("ingots/tungsten");
    public static Tag<Item> IESNIUM_INGOT = compatTag("ingots/iesnium");

    public static Tag<Item> GOLD_DUST = compatTag("dusts/gold");
    public static Tag<Item> IRON_DUST = compatTag("dusts/iron");
    public static Tag<Item> SILVER_DUST = compatTag("dusts/silver");
    public static Tag<Item> COPPER_DUST = compatTag("dusts/copper");
    public static Tag<Item> TIN_DUST = compatTag("dusts/tin");
    public static Tag<Item> NICKEL_DUST = compatTag("dusts/nickel");
    public static Tag<Item> LEAD_DUST = compatTag("dusts/lead");
    public static Tag<Item> ALUMINUM_DUST = compatTag("dusts/aluminum");
    public static Tag<Item> URANIUM_DUST = compatTag("dusts/uranium");
    public static Tag<Item> ZINC_DUST = compatTag("dusts/zinc");
    public static Tag<Item> PLATINUM_DUST = compatTag("dusts/platinum");
    public static Tag<Item> MERCURY_DUST = compatTag("dusts/mercury");
    public static Tag<Item> QUICKSILVER_DUST = compatTag("dusts/quicksilver");
    public static Tag<Item> BISMUTH_DUST = compatTag("dusts/bismuth");
    public static Tag<Item> NEPTUNIUM_DUST = compatTag("dusts/neptunium");
    public static Tag<Item> OSMIUM_DUST = compatTag("dusts/osmium");
    public static Tag<Item> COBALT_DUST = compatTag("dusts/cobalt");
    public static Tag<Item> ARDITE_DUST = compatTag("dusts/ardite");
    public static Tag<Item> ZITRITE_DUST = compatTag("dusts/zitrite");
    public static Tag<Item> RAINBOW_DUST = compatTag("dusts/rainbow");
    public static Tag<Item> STARMETAL_DUST = compatTag("dusts/starmetal");
    public static Tag<Item> TUNGSTEN_DUST = compatTag("dusts/tungsten");
    public static Tag<Item> IESNIUM_DUST = compatTag("dusts/iesnium");

    static Tag<Item> tag(String modid, String name) {
      return tag(ItemTags.Wrapper::new, modid, name);
    }

    static Tag<Item> modTag(String name) {
      return tag(Grindr.MODID, name);
    }

    static Tag<Item> compatTag(String name) {
      return tag("forge", name);
    }
  }

  static <T extends Tag<?>> T tag(Function<ResourceLocation, T> creator, String modid, String name) {
    return creator.apply(new ResourceLocation(modid, name));
  }

  static <T extends Tag<?>> T modTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, Grindr.MODID, name);
  }

  static <T extends Tag<?>> T compatTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, "forge", name);
  }
}
