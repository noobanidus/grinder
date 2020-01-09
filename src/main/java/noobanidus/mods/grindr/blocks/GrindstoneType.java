package noobanidus.mods.grindr.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.Tags;
import noobanidus.mods.grindr.config.ConfigManager;

import javax.annotation.Nullable;

public enum GrindstoneType implements IStringSerializable {
  EMPTY("empty", Items.AIR),
  STONE("stone", Items.STONE),
  GRANITE("granite", Items.GRANITE),
  DIORITE("diorite", Items.DIORITE),
  ANDESITE("andesite", Items.ANDESITE),
  IRON("iron", Tags.Items.INGOTS_IRON),
  GOLD("gold", Tags.Items.INGOTS_GOLD),
  DIAMOND("diamond", Tags.Items.GEMS_DIAMOND),
  EMERALD("emerald", Tags.Items.GEMS_EMERALD);

  private String name;
  private Tag<Item> itemType = null;
  private IItemProvider item = Items.AIR;
  private double resultModifier = -100;
  private double speedModifier = -100;

  GrindstoneType(String name, Tag<Item> itemType) {
    this.name = name;
    this.itemType = itemType;
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

  public double getResultModifier() {
    if (resultModifier == -100) {
      resultModifier = ConfigManager.RESULT_MODIFIER.get(this.name).get();
    }
    return resultModifier;
  }

  public double getSpeedModifier() {
    if (speedModifier == -100) {
      speedModifier = ConfigManager.SPEED_MODIFIER.get(this.name).get();
    }
    return speedModifier;
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
