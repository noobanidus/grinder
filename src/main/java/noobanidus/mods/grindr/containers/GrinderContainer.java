package noobanidus.mods.grindr.containers;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.util.IIntArray;
import noobanidus.mods.grindr.init.ModContainers;
import noobanidus.mods.grindr.init.ModRecipes;

public class GrinderContainer extends AbstractFurnaceContainer {
  public GrinderContainer(int id, PlayerInventory playerInventoryIn) {
    super(ModContainers.GRINDER_CONTAINER.get(), ModRecipes.GRINDER_TYPE, id, playerInventoryIn);
  }

  public GrinderContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
    super(ModContainers.GRINDER_CONTAINER.get(), ModRecipes.GRINDER_TYPE, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);
  }
}

