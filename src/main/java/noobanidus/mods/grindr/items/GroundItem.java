package noobanidus.mods.grindr.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import noobanidus.mods.grindr.config.ConfigManager;

public class GroundItem extends Item {
  public GroundItem(Properties properties) {
    super(properties);
  }

  @Override
  protected boolean isInGroup(ItemGroup group) {
/*    if (ConfigManager.isDustHidden(getRegistryName().getPath())) {
      return false;
    }*/
    return super.isInGroup(group);
  }
}
