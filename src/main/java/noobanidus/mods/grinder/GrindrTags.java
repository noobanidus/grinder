package noobanidus.mods.grinder;

import net.minecraft.item.Item;
import static net.minecraft.tags.ITag.INamedTag;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class GrindrTags {
  public static class Items extends GrindrTags {
    public static INamedTag<Item> GOLD_ORE = compatTag("ores/gold");
    public static INamedTag<Item> IRON_ORE = compatTag("ores/iron");
    public static INamedTag<Item> SILVER_ORE = compatTag("ores/silver");
    public static INamedTag<Item> COPPER_ORE = compatTag("ores/copper");
    public static INamedTag<Item> TIN_ORE = compatTag("ores/tin");
    public static INamedTag<Item> NICKEL_ORE = compatTag("ores/nickel");
    public static INamedTag<Item> LEAD_ORE = compatTag("ores/lead");
    public static INamedTag<Item> ALUMINUM_ORE = compatTag("ores/aluminum");
    public static INamedTag<Item> URANIUM_ORE = compatTag("ores/uranium");
    public static INamedTag<Item> ZINC_ORE = compatTag("ores/zinc");
    public static INamedTag<Item> PLATINUM_ORE = compatTag("ores/platinum");
    public static INamedTag<Item> MERCURY_ORE = compatTag("ores/mercury");
    public static INamedTag<Item> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static INamedTag<Item> BISMUTH_ORE = compatTag("ores/bismuth");
    public static INamedTag<Item> NEPTUNIUM_ORE = compatTag("ores/neptunium");
    public static INamedTag<Item> OSMIUM_ORE = compatTag("ores/osmium");
    public static INamedTag<Item> ARDITE_ORE = compatTag("ores/ardite");
    public static INamedTag<Item> COBALT_ORE = compatTag("ores/cobalt");
    public static INamedTag<Item> ZITRITE_ORE = compatTag("ores/zitrite");
    public static INamedTag<Item> RAINBOW_ORE = compatTag("ores/rainbow");
    public static INamedTag<Item> STARMETAL_ORE = compatTag("ores/starmetal");
    public static INamedTag<Item> TUNGSTEN_ORE = compatTag("ores/tungsten");
    public static INamedTag<Item> IESNIUM_ORE = compatTag("ores/iesnium");

    public static INamedTag<Item> GOLD_INGOT = compatTag("ingots/gold");
    public static INamedTag<Item> IRON_INGOT = compatTag("ingots/iron");
    public static INamedTag<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static INamedTag<Item> COPPER_INGOT = compatTag("ingots/copper");
    public static INamedTag<Item> TIN_INGOT = compatTag("ingots/tin");
    public static INamedTag<Item> NICKEL_INGOT = compatTag("ingots/nickel");
    public static INamedTag<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static INamedTag<Item> ALUMINUM_INGOT = compatTag("ingots/aluminum");
    public static INamedTag<Item> URANIUM_INGOT = compatTag("ingots/uranium");
    public static INamedTag<Item> ZINC_INGOT = compatTag("ingots/zinc");
    public static INamedTag<Item> PLATINUM_INGOT = compatTag("ingots/platinum");
    public static INamedTag<Item> MERCURY_INGOT = compatTag("ingots/mercury");
    public static INamedTag<Item> QUICKSILVER_INGOT = compatTag("ingots/quicksilver");
    public static INamedTag<Item> BISMUTH_INGOT = compatTag("ingots/bismuth");
    public static INamedTag<Item> NEPTUNIUM_INGOT = compatTag("ingots/neptunium");
    public static INamedTag<Item> OSMIUM_INGOT = compatTag("ingots/osmium");
    public static INamedTag<Item> ARDITE_INGOT = compatTag("ingots/ardite");
    public static INamedTag<Item> COBALT_INGOT = compatTag("ingots/cobalt");
    public static INamedTag<Item> ZITRITE_INGOT = compatTag("ingots/zitrite");
    public static INamedTag<Item> RAINBOW_INGOT = compatTag("ingots/rainbow");
    public static INamedTag<Item> STARMETAL_INGOT = compatTag("ingots/starmetal");
    public static INamedTag<Item> TUNGSTEN_INGOT = compatTag("ingots/tungsten");
    public static INamedTag<Item> IESNIUM_INGOT = compatTag("ingots/iesnium");

    public static INamedTag<Item> GOLD_DUST = compatTag("dusts/gold");
    public static INamedTag<Item> IRON_DUST = compatTag("dusts/iron");
    public static INamedTag<Item> SILVER_DUST = compatTag("dusts/silver");
    public static INamedTag<Item> COPPER_DUST = compatTag("dusts/copper");
    public static INamedTag<Item> TIN_DUST = compatTag("dusts/tin");
    public static INamedTag<Item> NICKEL_DUST = compatTag("dusts/nickel");
    public static INamedTag<Item> LEAD_DUST = compatTag("dusts/lead");
    public static INamedTag<Item> ALUMINUM_DUST = compatTag("dusts/aluminum");
    public static INamedTag<Item> URANIUM_DUST = compatTag("dusts/uranium");
    public static INamedTag<Item> ZINC_DUST = compatTag("dusts/zinc");
    public static INamedTag<Item> PLATINUM_DUST = compatTag("dusts/platinum");
    public static INamedTag<Item> MERCURY_DUST = compatTag("dusts/mercury");
    public static INamedTag<Item> QUICKSILVER_DUST = compatTag("dusts/quicksilver");
    public static INamedTag<Item> BISMUTH_DUST = compatTag("dusts/bismuth");
    public static INamedTag<Item> NEPTUNIUM_DUST = compatTag("dusts/neptunium");
    public static INamedTag<Item> OSMIUM_DUST = compatTag("dusts/osmium");
    public static INamedTag<Item> COBALT_DUST = compatTag("dusts/cobalt");
    public static INamedTag<Item> ARDITE_DUST = compatTag("dusts/ardite");
    public static INamedTag<Item> ZITRITE_DUST = compatTag("dusts/zitrite");
    public static INamedTag<Item> RAINBOW_DUST = compatTag("dusts/rainbow");
    public static INamedTag<Item> STARMETAL_DUST = compatTag("dusts/starmetal");
    public static INamedTag<Item> TUNGSTEN_DUST = compatTag("dusts/tungsten");
    public static INamedTag<Item> IESNIUM_DUST = compatTag("dusts/iesnium");

    static INamedTag<Item> modTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation(Grinder.MODID, name));
    }

    static INamedTag<Item> compatTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", name));
    }
  }
}
