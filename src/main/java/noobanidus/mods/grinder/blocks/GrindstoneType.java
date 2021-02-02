package noobanidus.mods.grinder.blocks;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.Tags;
import noobanidus.libs.noobutil.util.EnumUtil;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.GrinderTags;
import noobanidus.mods.grinder.config.ConfigManager;
import noobanidus.mods.grinder.init.ModItems;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public enum GrindstoneType implements IStringSerializable {
  EMPTY("empty", Items.AIR),
  STONE("stone", Items.STONE),
  GRANITE("granite", Items.GRANITE),
  DIORITE("diorite", Items.DIORITE),
  ANDESITE("andesite", Items.ANDESITE),
  IRON("iron", Tags.Items.INGOTS_IRON, ModItems.IRON_DUST),
  GOLD("gold", Tags.Items.INGOTS_GOLD, ModItems.GOLD_DUST),
  DIAMOND("diamond", Tags.Items.GEMS_DIAMOND, Items.DIAMOND),
  EMERALD("emerald", Tags.Items.GEMS_EMERALD, Items.EMERALD),
  OBSIDIAN("obsidian", Tags.Items.OBSIDIAN, Items.OBSIDIAN),
  ALUMINUM("aluminum", GrinderTags.Items.ALUMINUM_INGOT, ModItems.ALUMINUM_DUST),
  TIN("tin", GrinderTags.Items.TIN_INGOT, ModItems.TIN_DUST),
  COPPER("copper", GrinderTags.Items.COPPER_INGOT, ModItems.COPPER_DUST),
  NICKEL("nickel", GrinderTags.Items.NICKEL_INGOT, ModItems.NICKEL_DUST),
  LEAD("lead", GrinderTags.Items.LEAD_INGOT, ModItems.LEAD_DUST),
  SILVER("silver", GrinderTags.Items.SILVER_INGOT, ModItems.SILVER_DUST),
  PLATINUM("platinum", GrinderTags.Items.PLATINUM_INGOT, ModItems.PLATINUM_DUST),
  MERCURY("mercury", GrinderTags.Items.MERCURY_INGOT, ModItems.MERCURY_DUST),
  ZINC("zinc", GrinderTags.Items.ZINC_INGOT, ModItems.ZINC_DUST),
  BISMUTH("bismuth", GrinderTags.Items.BISMUTH_INGOT, ModItems.BISMUTH_DUST),
  NEPTUNIUM("neptunium", GrinderTags.Items.NEPTUNIUM_INGOT, ModItems.NEPTUNIUM_DUST),
  URANIUM("uranium", GrinderTags.Items.URANIUM_INGOT, ModItems.URANIUM_DUST),
  OSMIUM("osmium", GrinderTags.Items.OSMIUM_INGOT, ModItems.OSMIUM_DUST),
  ARDITE("ardite", GrinderTags.Items.ARDITE_INGOT, ModItems.ARDITE_DUST),
  COBALT("cobalt", GrinderTags.Items.COBALT_INGOT, ModItems.COBALT_DUST),
  ZITRITE("zitrite", GrinderTags.Items.ZITRITE_INGOT, ModItems.ZITRITE_DUST),
  RAINBOW("rainbow", GrinderTags.Items.RAINBOW_INGOT, ModItems.RAINBOW_DUST),
  STARMETAL("starmetal", GrinderTags.Items.STARMETAL_INGOT, ModItems.STARMETAL_DUST),
  TUNGSTEN("tungsten", GrinderTags.Items.TUNGSTEN_INGOT, ModItems.TUNGSTEN_DUST),
  IESNIUM("iesnium", GrinderTags.Items.IESNIUM_INGOT, ModItems.IESNIUM_DUST);

  public static Map<GrindstoneType, ITag.INamedTag<Item>> INGOT_TO_ORE = new HashMap<>();

  static {
    INGOT_TO_ORE.put(ALUMINUM, GrinderTags.Items.ALUMINUM_ORE);
    INGOT_TO_ORE.put(COPPER, GrinderTags.Items.COPPER_ORE);
    INGOT_TO_ORE.put(NICKEL, GrinderTags.Items.NICKEL_ORE);
    INGOT_TO_ORE.put(LEAD, GrinderTags.Items.LEAD_ORE);
    INGOT_TO_ORE.put(SILVER, GrinderTags.Items.SILVER_ORE);
    INGOT_TO_ORE.put(PLATINUM, GrinderTags.Items.PLATINUM_ORE);
    INGOT_TO_ORE.put(MERCURY, GrinderTags.Items.MERCURY_ORE);
    INGOT_TO_ORE.put(ZINC, GrinderTags.Items.ZINC_ORE);
    INGOT_TO_ORE.put(URANIUM, GrinderTags.Items.URANIUM_ORE);
    INGOT_TO_ORE.put(BISMUTH, GrinderTags.Items.BISMUTH_ORE);
    INGOT_TO_ORE.put(NEPTUNIUM, GrinderTags.Items.NEPTUNIUM_ORE);
    INGOT_TO_ORE.put(TIN, GrinderTags.Items.TIN_ORE);
    INGOT_TO_ORE.put(OSMIUM, GrinderTags.Items.OSMIUM_ORE);
    INGOT_TO_ORE.put(COBALT, GrinderTags.Items.COBALT_ORE);
    INGOT_TO_ORE.put(ARDITE, GrinderTags.Items.ARDITE_ORE);
    INGOT_TO_ORE.put(ZITRITE, GrinderTags.Items.ZITRITE_ORE);
    INGOT_TO_ORE.put(RAINBOW, GrinderTags.Items.RAINBOW_ORE);
    INGOT_TO_ORE.put(IESNIUM, GrinderTags.Items.IESNIUM_ORE);
    INGOT_TO_ORE.put(TUNGSTEN, GrinderTags.Items.TUNGSTEN_ORE);
    INGOT_TO_ORE.put(STARMETAL, GrinderTags.Items.STARMETAL_ORE);
  }

  private String name;
  private ITag.INamedTag<Item> itemType = null;
  private IItemProvider item = Items.AIR;

  private IItemProvider recycleItem = null;
  private RegistryEntry<? extends Item> recycleItemEntry = null;

  GrindstoneType(String name, ITag.INamedTag<Item> itemType, RegistryEntry<? extends Item> recycle) {
    this.name = name;
    this.itemType = itemType;
    this.recycleItemEntry = recycle;
  }

  GrindstoneType(String name, ITag.INamedTag<Item> itemType, IItemProvider recycle) {
    this.name = name;
    this.itemType = itemType;
    this.recycleItem = recycle;
  }

  GrindstoneType(String name, IItemProvider item) {
    this.name = name;
    this.item = item;
  }

  @Nullable
  public ITag.INamedTag<Item> getTag() {
    return itemType;
  }

  @Nullable
  public IItemProvider getItem() {
    return item;
  }

  public IItemProvider getRecycleItem() {
    if (itemType == null) {
      return item;
    }
    if (recycleItem != null) {
      return recycleItem;
    } else {
      return recycleItemEntry.get();
    }
  }

  public double getResultModifier() {
    if (this == EMPTY) {
      return 0;
    }

    double result = ConfigManager.resultModifier(this);
    if (result == -100) {
      Grinder.LOG.error("No configuration information found for Grindstone type: " + this.name);
      return 1;
    }
    return result;
  }

  public double getSpeedModifier() {
    if (this == EMPTY) {
      return 0;
    }

    double result = ConfigManager.speedModifier(this);
    if (result == -100) {
      Grinder.LOG.error("No configuration information found for Grindstone type: " + this.name);
      return 1;
    }
    return result;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public String getString() {
    return name;
  }

  public static GrindstoneType fromOrdinal (int ordinal) {
    GrindstoneType result = EnumUtil.fromOrdinal(GrindstoneType.class, ordinal);
    if (result == null) {
      return EMPTY;
    }

    return result;
  }
}
