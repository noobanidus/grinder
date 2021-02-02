package noobanidus.mods.grinder.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FurnaceFuelSlot extends Slot {
  private final GrinderContainer container;

  public FurnaceFuelSlot(GrinderContainer container, IInventory inventory, int a, int b, int c) {
    super(inventory, a, b, c);
    this.container = container;
  }

  /**
   * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
   */
  @Override
  public boolean isItemValid(ItemStack stack) {
    return this.container.isFuel(stack) || isBucket(stack);
  }

  @Override
  public int getItemStackLimit(ItemStack stack) {
    return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
  }

  public static boolean isBucket(ItemStack stack) {
    return stack.getItem() == Items.BUCKET;
  }
}

