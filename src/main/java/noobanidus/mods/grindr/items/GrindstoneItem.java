package noobanidus.mods.grindr.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import noobanidus.mods.grindr.blocks.GrindstoneType;

import javax.annotation.Nullable;
import java.util.List;

public class GrindstoneItem extends Item {
  private final GrindstoneType type;

  public GrindstoneItem(Properties properties, GrindstoneType grindstone) {
    super(properties);
    this.type = grindstone;
  }

  public GrindstoneType getType() {
    return type;
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);

    if (type != GrindstoneType.EMPTY) {
      float speed = type.getSpeedModifier() * 100;
      boolean neg = false;
      if (speed > 100) {
        speed = -(speed - 100);
        neg = true;
      }

      float multiplier = type.getResultModifier() * 100;
      boolean neg2 = false;
      if (multiplier < 100) {
        multiplier = -(multiplier);
        neg2 = true;
      }

      tooltip.add(new TranslationTextComponent("grinder.tooltip.grindstone.desc1", (neg ? TextFormatting.RED : TextFormatting.GREEN) + ((int) speed + "%")));
      tooltip.add(new TranslationTextComponent("grinder.tooltip.grindstone.desc2", (neg2 ? TextFormatting.RED : TextFormatting.GREEN) + ((int) multiplier + "%")));
    }
  }
}
