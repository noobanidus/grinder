package noobanidus.mods.grindr.init;

import com.tterrag.registrate.providers.ProviderType;

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
    });
  }

  public static void load () {
  }
}
