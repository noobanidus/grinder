package noobanidus.mods.grinder.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

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
