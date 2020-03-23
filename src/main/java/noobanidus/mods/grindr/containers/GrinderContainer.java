package noobanidus.mods.grindr.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.init.ModContainers;
import noobanidus.mods.grindr.init.ModRecipes;
import noobanidus.mods.grindr.tiles.GrinderTile;

public class GrinderContainer extends Container {
  public GrinderContainer(int id, PlayerInventory playerInventoryIn) {
    this(ModContainers.GRINDER_CONTAINER.get(), ModRecipes.GRINDER_TYPE, id, playerInventoryIn, new Inventory(3), new IntArray(5));
  }

  public GrinderContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
    this(ModContainers.GRINDER_CONTAINER.get(), ModRecipes.GRINDER_TYPE, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);
  }

  private final IInventory furnaceInventory;
  private final IIntArray array;
  protected final World world;
  private final IRecipeType<? extends AbstractCookingRecipe> recipeType;

  protected GrinderContainer(ContainerType<?> containerTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn, int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray array) {
    super(containerTypeIn, id);
    this.recipeType = recipeTypeIn;
    assertInventorySize(furnaceInventoryIn, 3);
    assertIntArraySize(array, 4);
    this.furnaceInventory = furnaceInventoryIn;
    this.array = array;
    this.world = playerInventoryIn.player.world;
    this.addSlot(new Slot(furnaceInventoryIn, 0, 56, 17));
    this.addSlot(new FurnaceFuelSlot(this, furnaceInventoryIn, 1, 56, 53));
    this.addSlot(new FurnaceResultSlot(playerInventoryIn.player, furnaceInventoryIn, 2, 116, 35));

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    for (int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
    }

    this.trackIntArray(array);
  }

  @OnlyIn(Dist.CLIENT)
  public int getSize() {
    return 3;
  }

  /**
   * Determines whether supplied player can use this container
   */
  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return this.furnaceInventory.isUsableByPlayer(playerIn);
  }

  /**
   * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
   * inventory and the other inventory(s).
   */
  @Override
  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.inventorySlots.get(index);
    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();
      if (index == 2) {
        if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
          return ItemStack.EMPTY;
        }

        slot.onSlotChange(itemstack1, itemstack);
      } else if (index != 1 && index != 0) {
        if (this.func_217057_a(itemstack1)) {
          if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
            return ItemStack.EMPTY;
          }
        } else if (this.isFuel(itemstack1)) {
          if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index >= 3 && index < 30) {
          if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
            return ItemStack.EMPTY;
          }
        } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
        return ItemStack.EMPTY;
      }

      if (itemstack1.isEmpty()) {
        slot.putStack(ItemStack.EMPTY);
      } else {
        slot.onSlotChanged();
      }

      if (itemstack1.getCount() == itemstack.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTake(playerIn, itemstack1);
    }

    if (furnaceInventory instanceof GrinderTile) {
      ((GrinderTile) furnaceInventory).resetRecipe();
    }

    return itemstack;
  }

  // TODO: WTB Fast Furnace
  protected boolean func_217057_a(ItemStack p_217057_1_) {
    return this.world.getRecipeManager().getRecipe((IRecipeType) this.recipeType, new Inventory(p_217057_1_), this.world).isPresent();
  }

  protected boolean isFuel(ItemStack p_217058_1_) {
    return AbstractFurnaceTileEntity.isFuel(p_217058_1_);
  }

  @OnlyIn(Dist.CLIENT)
  public int getCookProgressionScaled() {
    int i = this.array.get(2);
    int j = this.array.get(3);
    return j != 0 && i != 0 ? i * 24 / j : 0;
  }

  @OnlyIn(Dist.CLIENT)
  public int getBurnLeftScaled() {
    int i = this.array.get(1);
    if (i == 0) {
      i = 200;
    }

    return this.array.get(0) * 13 / i;
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isBurning() {
    return this.array.get(0) > 0;
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isEmpty() {
    GrindstoneType type = GrindstoneType.fromOrdinal(this.array.get(4));
    return type == GrindstoneType.EMPTY;
  }
}

