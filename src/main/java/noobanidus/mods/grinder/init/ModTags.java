package noobanidus.mods.grinder.init;

import com.tterrag.registrate.providers.ProviderType;
import noobanidus.mods.grinder.GrindrTags;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ctx -> {
      ctx.getOrCreateBuilder(GrindrTags.Items.QUICKSILVER_DUST).add(ModItems.MERCURY_DUST.get());
      ctx.getOrCreateBuilder(GrindrTags.Items.MERCURY_DUST).add(ModItems.MERCURY_DUST.get());
    });
  }

  public static void load () {

  }
}
