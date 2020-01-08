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
  IRON("iron", Tags.Items.STORAGE_BLOCKS_IRON),
  GOLD("gold", Tags.Items.STORAGE_BLOCKS_GOLD),
  DIAMOND("diamond", Tags.Items.STORAGE_BLOCKS_DIAMOND),
  EMERALD("emerald", Tags.Items.STORAGE_BLOCKS_EMERALD);

  private String name;
  private Tag<Item> itemType = null;
  private IItemProvider item = Items.AIR;
  private float resultModifier = -100;
  private float speedModifier = -100;

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

  public float getResultModifier() {
    if (resultModifier == -100) {
      resultModifier = ConfigManager.RESULT_MODIFIER.get(this.name).get();
    }
    return resultModifier;
  }

  public float getSpeedModifier() {
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
