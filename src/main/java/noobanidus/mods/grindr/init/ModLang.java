package noobanidus.mods.grindr.init;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModLang {
  static {
    REGISTRATE.addDataGenerator(ProviderType.LANG, ctx -> {
      ctx.add("grinder.container.grinder", "Grinder");
      ctx.add("grinder.jei.grinder", "Grinding");
      ctx.add("grinder.jei.grindstone", "Grindstone Installation");
      ctx.add("grinder.tooltip.grindstone.desc1", "Speed bonus: %s");
      ctx.add("grinder.tooltip.grindstone.desc2", "Output bonus: %s");
      ctx.add("grinder.tooltip.grinder.desc", "Requires a grindstone to function.");
      ctx.add("grinder.hud.right_click", "Right-Click");
      ctx.add("grinder.hud.sneak", "Sneak-");
      ctx.add("grinder.hud.requires_empty", "Requires empty hand");
      ctx.add("grinder.hud.remove", "to remove");
      ctx.add("grinder.hud.install", "to install");
      ctx.add("itemGroup.grindr", "Grindr");
      ctx.add("grinder.tooltip.speed_desc", "Positive values in red mean increased processing time. Negative values in green denote decreased processing time.");
      ctx.add("grinder.tooltip.result_desc", "Result values in red or green indicate percentage chance of additional output (or reduction of output).");
      ctx.add("grinder.tooltip.shift_for_more", "[Hold Shift for more]");
    });
  }

  public static void load () {
  }
}
