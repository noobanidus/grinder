package noobanidus.mods.grindr.tiles;

import com.google.common.collect.Maps;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import noobanidus.mods.grindr.blocks.GrinderBlock;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.containers.GrinderContainer;
import noobanidus.mods.grindr.init.ModItems;
import noobanidus.mods.grindr.init.ModRecipes;
import noobanidus.mods.grindr.init.ModTiles;
import noobanidus.mods.grindr.items.GrindstoneItem;
import noobanidus.mods.grindr.recipes.GrinderRecipe;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

@SuppressWarnings({"Duplicates", "NullableProblems"})
public class GrinderTile extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
  private static final int INPUT = 0;
  private static final int FUEL = 1;
  private static final int OUTPUT = 2;
  private static final int[] SLOTS_UP = new int[]{0};
  private static final int[] SLOTS_DOWN = new int[]{2, 1};
  private static final int[] SLOTS_HORIZONTAL = new int[]{1};
  protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
  private int burnTime;
  private int recipesUsed;
  private int cookTime;
  private int cookTimeTotal;
  private final IIntArray furnaceData = new IIntArray() {
    @Override
    public int get(int index) {
      switch (index) {
        case 0:
          return GrinderTile.this.burnTime;
        case 1:
          return GrinderTile.this.recipesUsed;
        case 2:
          return GrinderTile.this.cookTime;
        case 3:
          return GrinderTile.this.cookTimeTotal;
        default:
          return 0;
      }
    }

    @Override
    public void set(int index, int value) {
      switch (index) {
        case 0:
          GrinderTile.this.burnTime = value;
          break;
        case 1:
          GrinderTile.this.recipesUsed = value;
          break;
        case 2:
          GrinderTile.this.cookTime = value;
          break;
        case 3:
          GrinderTile.this.cookTimeTotal = value;
      }

    }

    @Override
    public int size() {
      return 4;
    }
  };
  private final Map<ResourceLocation, Integer> recipeMap = Maps.newHashMap();
  private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

  private static Random random = new Random();
  private boolean isValid = false;
  private int count = -1;

  @SuppressWarnings("ConstantConditions")
  public GrinderTile() {
    super(ModTiles.GRINDER.get());
  }

  @Override
  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("grinder.container.grinder");
  }

  @Override
  protected Container createMenu(int id, PlayerInventory player) {
    return new GrinderContainer(id, player, this, this.furnaceData);
  }

  public void removeGrindstone() {
    if (world == null || world.isRemote()) {
      return;
    }

    BlockState state = world.getBlockState(pos);
    if (state.get(GrinderBlock.GRINDSTONE) == GrindstoneType.EMPTY) {
      return;
    }

    RegistryObject<GrindstoneItem> item = ModItems.GRINDSTONE_MAP.get(state.get(GrinderBlock.GRINDSTONE));
    if (item == null) {
      return;
    }

    ItemStack stack = new ItemStack(item.get());
    ItemEntity entity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, stack);
    world.addEntity(entity);
    world.setBlockState(pos, state.with(GrinderBlock.GRINDSTONE, GrindstoneType.EMPTY), 3);
    isValid = false;
    count = -1;
  }

  public void addGrindstone(PlayerEntity player, ItemStack grindstone) {
    if (world == null || world.isRemote()) {
      return;
    }

    BlockState state = world.getBlockState(pos);
    if (state.get(GrinderBlock.GRINDSTONE) != GrindstoneType.EMPTY) {
      return;
    }

    if (!(grindstone.getItem() instanceof GrindstoneItem)) {
      return;
    }

    GrindstoneType type = ((GrindstoneItem) grindstone.getItem()).getType();
    grindstone.shrink(1);
    player.setHeldItem(Hand.MAIN_HAND, grindstone);

    world.setBlockState(pos, state.with(GrinderBlock.GRINDSTONE, type), 3);
    isValid = true;
    count = -1;
  }

  private int getCount() {
    if (this.count == -1) {
      GrindstoneType type = getGrindstone();
      if (type == GrindstoneType.EMPTY) {
        this.count = -1;
      }

      float modifier = type.getResultModifier() + 1;
      this.count = 0;
      for (; modifier > 1; modifier--) {
        if (random.nextFloat() < modifier) {
          this.count++;
        }
      }
    }

    return this.count;
  }

  private GrindstoneType getGrindstone() {
    return world == null ? GrindstoneType.EMPTY : world.getBlockState(pos).get(GrinderBlock.GRINDSTONE);
  }

  @Override
  public void read(CompoundNBT compound) {
    this.isValid = compound.getBoolean("isValid");
    this.count = compound.getInt("count");
    super.read(compound);
    this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    ItemStackHelper.loadAllItems(compound, this.items);
    this.burnTime = compound.getInt("BurnTime");
    this.cookTime = compound.getInt("CookTime");
    this.cookTimeTotal = compound.getInt("CookTimeTotal");
    this.recipesUsed = this.getBurnTime(this.items.get(1));
    int i = compound.getShort("RecipesUsedSize");

    for (int j = 0; j < i; ++j) {
      ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
      int k = compound.getInt("RecipeAmount" + j);
      this.recipeMap.put(resourcelocation, k);
    }
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    CompoundNBT result = super.write(compound);
    result.putBoolean("isValid", isValid);
    result.putInt("count", count);
    result.putInt("BurnTime", this.burnTime);
    result.putInt("CookTime", this.cookTime);
    result.putInt("CookTimeTotal", this.cookTimeTotal);
    ItemStackHelper.saveAllItems(result, this.items);
    result.putShort("RecipesUsedSize", (short) this.recipeMap.size());
    int i = 0;

    for (Map.Entry<ResourceLocation, Integer> entry : this.recipeMap.entrySet()) {
      result.putString("RecipeLocation" + i, entry.getKey().toString());
      result.putInt("RecipeAmount" + i, entry.getValue());
      ++i;
    }
    return result;
  }

  // -----------------

  @Override
  public void tick() {
    if (!isValid && world != null) {
      BlockState state = world.getBlockState(pos);
      if (state.get(GrinderBlock.GRINDSTONE) != GrindstoneType.EMPTY) {
        isValid = true;
      }
    }
    boolean wasBurning = this.isBurning();
    boolean dirty = false;
    if (this.isBurning()) {
      --this.burnTime;
    }

    if (world != null && !this.world.isRemote) {
      ItemStack fuel = this.items.get(FUEL);
      if (this.isBurning() || !fuel.isEmpty() && !this.items.get(INPUT).isEmpty()) {
        AbstractCookingRecipe irecipe = getRecipe();
        boolean valid = this.canSmelt(irecipe);
        if (!this.isBurning() && valid && isValid) {
          this.burnTime = this.getBurnTime(fuel);
          this.recipesUsed = this.burnTime;
          if (this.isBurning()) {
            dirty = true;
            if (fuel.hasContainerItem()) this.items.set(1, fuel.getContainerItem());
            else if (!fuel.isEmpty()) {
              fuel.shrink(1);
              if (fuel.isEmpty()) {
                this.items.set(1, fuel.getContainerItem());
              }
            }
          }
        }

        if (this.isBurning() && valid && isValid) {
          ++this.cookTime;
          if (this.cookTime == this.cookTimeTotal) {
            this.cookTime = 0;
            this.cookTimeTotal = this.getCookTime();
            this.smeltItem(irecipe);
            dirty = true;
          }
        } else {
          this.cookTime = 0;
        }
      } else if (this.cookTime > 0) {
        this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
      }

      if (wasBurning != this.isBurning()) {
        dirty = true;
        this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
      }
    }

    if (dirty) {
      this.markDirty();
    }
  }

  private boolean isBurning() {
    return this.burnTime > 0;
  }

  private int getBurnTime(ItemStack stack) {
    if (stack.isEmpty()) {
      return 0;
    } else {
      return ForgeHooks.getBurnTime(stack);
    }
  }

  @Override
  public int[] getSlotsForFace(Direction side) {
    if (side == Direction.DOWN) {
      return SLOTS_DOWN;
    } else {
      return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
    }
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
    return this.isItemValidForSlot(index, itemStackIn);
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
    if (direction == Direction.DOWN && index == 1) {
      Item item = stack.getItem();
      return item == Items.WATER_BUCKET || item == Items.BUCKET;
    }

    return true;
  }

  @Override
  public int getSizeInventory() {
    return this.items.size();
  }

  @Override
  public boolean isEmpty() {
    for (ItemStack itemstack : this.items) {
      if (!itemstack.isEmpty()) {
        return false;
      }
    }

    return true;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    return this.items.get(index);
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    return ItemStackHelper.getAndSplit(this.items, index, count);
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    return ItemStackHelper.getAndRemove(this.items, index);
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {
    ItemStack itemstack = this.items.get(index);
    boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
    this.items.set(index, stack);
    if (stack.getCount() > this.getInventoryStackLimit()) {
      stack.setCount(this.getInventoryStackLimit());
    }

    if (index == 0 && !flag) {
      this.cookTimeTotal = this.getCookTime();
      this.cookTime = 0;
      this.markDirty();
    }
  }

  @Override
  public boolean isUsableByPlayer(PlayerEntity player) {
    if (world != null && this.world.getTileEntity(this.pos) != this) {
      return false;
    } else {
      return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    if (index == 2) {
      return false;
    } else if (index != 1) {
      return true;
    } else {
      ItemStack itemstack = this.items.get(1);
      return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
    }
  }

  @Override
  public void clear() {
    this.items.clear();
  }

  @Override
  public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
    if (recipe != null) {
      this.recipeMap.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> 1 + (p_214004_1_ == null ? 0 : p_214004_1_));
    }
  }

  @Override
  @Nullable
  public IRecipe<?> getRecipeUsed() {
    return curRecipe;
  }

  @Override
  public void fillStackedContents(RecipeItemHelper helper) {
    for (ItemStack itemstack : this.items) {
      helper.accountStack(itemstack);
    }
  }

  @Override
  public <T> LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
    if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      if (facing == Direction.UP)
        return handlers[0].cast();
      else if (facing == Direction.DOWN)
        return handlers[1].cast();
      else
        return handlers[2].cast();
    }
    return super.getCapability(capability, facing);
  }

  @Override
  public void remove() {
    super.remove();
    for (LazyOptional<? extends IItemHandler> handler : handlers) {
      handler.invalidate();
    }
  }

  // ------------------

  private GrinderRecipe curRecipe;
  private ItemStack failedMatch = ItemStack.EMPTY;

  private boolean canSmelt(@Nullable IRecipe<?> recipe) {
    if (!this.items.get(0).isEmpty() && recipe != null) {
      ItemStack recipeOutput = recipe.getRecipeOutput();
      if (!recipeOutput.isEmpty()) {
        ItemStack output = this.items.get(OUTPUT);
        if (output.isEmpty()) return true;
        else if (!output.isItemEqual(recipeOutput)) return false;
        else return output.getCount() + getCount() <= output.getMaxStackSize();
      }
    }
    count = -1;
    return false;
  }

  private void smeltItem(@Nullable IRecipe<?> recipe) {
    if (recipe != null && this.canSmelt(recipe)) {
      ItemStack itemstack = this.items.get(0);
      ItemStack itemstack1 = recipe.getRecipeOutput();
      ItemStack itemstack2 = this.items.get(2);
      if (itemstack2.isEmpty()) {
        itemstack1 = itemstack1.copy();
        itemstack1.setCount(getCount());
        this.items.set(2, itemstack1.copy());
      } else if (itemstack2.getItem() == itemstack1.getItem()) {
        itemstack2.grow(getCount());
      }

      if (world != null && !this.world.isRemote) {
        this.setRecipeUsed(recipe);
      }

      itemstack.shrink(1);
      count = -1;
    }
  }

  private int getCookTime() {
    AbstractCookingRecipe rec = getRecipe();
    if (rec == null) return 200;
    int cookTime = rec.getCookTime();
    GrindstoneType type = getGrindstone();
    return (int) ((float) cookTime * type.getSpeedModifier());
  }

  protected AbstractCookingRecipe getRecipe() {
    ItemStack input = this.getStackInSlot(INPUT);
    if (input.isEmpty() || input == failedMatch) return null;
    if (world == null) return null;
    if (curRecipe != null && curRecipe.matches(this, world)) return curRecipe;
    else {
      GrinderRecipe rec = world.getRecipeManager().getRecipe(ModRecipes.GRINDER_TYPE, this, this.world).orElse(null);
      if (rec == null) failedMatch = input;
      else failedMatch = ItemStack.EMPTY;
      return curRecipe = rec;
    }
  }
}

