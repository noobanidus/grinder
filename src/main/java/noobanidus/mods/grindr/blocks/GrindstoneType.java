package noobanidus.mods.grindr.blocks;

import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.Tags;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.GrindrTags;
import noobanidus.mods.grindr.config.ConfigManager;
import noobanidus.mods.grindr.init.ModItems;

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
  ALUMINUM("aluminum", GrindrTags.Items.ALUMINUM_INGOT, ModItems.ALUMINUM_DUST),
  TIN("tin", GrindrTags.Items.TIN_INGOT, ModItems.TIN_DUST),
  COPPER("copper", GrindrTags.Items.COPPER_INGOT, ModItems.COPPER_DUST),
  NICKEL("nickel", GrindrTags.Items.NICKEL_INGOT, ModItems.NICKEL_DUST),
  LEAD("lead", GrindrTags.Items.LEAD_INGOT, ModItems.LEAD_DUST),
  SILVER("silver", GrindrTags.Items.SILVER_INGOT, ModItems.SILVER_DUST),
  PLATINUM("platinum", GrindrTags.Items.PLATINUM_INGOT, ModItems.PLATINUM_DUST),
  MERCURY("mercury", GrindrTags.Items.MERCURY_INGOT, ModItems.MERCURY_DUST),
  ZINC("zinc", GrindrTags.Items.ZINC_INGOT, ModItems.ZINC_DUST),
  BISMUTH("bismuth", GrindrTags.Items.BISMUTH_INGOT, ModItems.BISMUTH_DUST),
  NEPTUNIUM("neptunium", GrindrTags.Items.NEPTUNIUM_INGOT, ModItems.NEPTUNIUM_DUST),
  URANIUM("uranium", GrindrTags.Items.URANIUM_INGOT, ModItems.URANIUM_DUST);

  public static Map<GrindstoneType, Tag<Item>> INGOT_TO_ORE = new HashMap<>();

  static {
    INGOT_TO_ORE.put(ALUMINUM, GrindrTags.Items.ALUMINUM_ORE);
    INGOT_TO_ORE.put(COPPER, GrindrTags.Items.COPPER_ORE);
    INGOT_TO_ORE.put(NICKEL, GrindrTags.Items.NICKEL_ORE);
    INGOT_TO_ORE.put(LEAD, GrindrTags.Items.LEAD_ORE);
    INGOT_TO_ORE.put(SILVER, GrindrTags.Items.SILVER_ORE);
    INGOT_TO_ORE.put(PLATINUM, GrindrTags.Items.PLATINUM_ORE);
    INGOT_TO_ORE.put(MERCURY, GrindrTags.Items.MERCURY_ORE);
    INGOT_TO_ORE.put(ZINC, GrindrTags.Items.ZINC_ORE);
    INGOT_TO_ORE.put(URANIUM, GrindrTags.Items.URANIUM_ORE);
    INGOT_TO_ORE.put(BISMUTH, GrindrTags.Items.BISMUTH_ORE);
    INGOT_TO_ORE.put(NEPTUNIUM, GrindrTags.Items.NEPTUNIUM_ORE);
    INGOT_TO_ORE.put(TIN, GrindrTags.Items.TIN_ORE);
  }

  private String name;
  private Tag<Item> itemType = null;
  private IItemProvider item = Items.AIR;

  private IItemProvider recycleItem = null;
  private RegistryEntry<? extends Item> recycleItemEntry = null;
  private int enabled = -1;
  private boolean isEnabled = false;

  GrindstoneType(String name, Tag<Item> itemType, RegistryEntry<? extends Item> recycle) {
    this.name = name;
    this.itemType = itemType;
    this.recycleItemEntry = recycle;
  }

  GrindstoneType(String name, Tag<Item> itemType, IItemProvider recycle) {
    this.name = name;
    this.itemType = itemType;
    this.recycleItem = recycle;
  }

  GrindstoneType(String name, IItemProvider item) {
    this.name = name;
    this.item = item;
  }

  @Nullable
  public Tag<Item> getTag() {
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
      Grindr.LOG.error("No configuration information found for Grindstone type: " + this.name);
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
      Grindr.LOG.error("No configuration information found for Grindstone type: " + this.name);
      return 1;
    }
    return result;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public String getName() {
    return name;
  }
}
