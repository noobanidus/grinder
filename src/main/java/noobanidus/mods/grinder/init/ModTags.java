package noobanidus.mods.grinder.init;

import com.tterrag.registrate.providers.ProviderType;
import noobanidus.mods.grinder.GrinderTags;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ctx -> {
      ctx.getOrCreateBuilder(GrinderTags.Items.QUICKSILVER_DUST).add(ModItems.MERCURY_DUST.get());
      ctx.getOrCreateBuilder(GrinderTags.Items.MERCURY_DUST).add(ModItems.MERCURY_DUST.get());
    });
  }

  public static void load () {

  }
}
