package noobanidus.mods.grindr.init;

import com.tterrag.registrate.providers.ProviderType;
import noobanidus.mods.grindr.GrindrTags;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ctx -> {
      ctx.getBuilder(GrindrTags.Items.QUICKSILVER_DUST).add(ModItems.MERCURY_DUST.get());
      ctx.getBuilder(GrindrTags.Items.MERCURY_DUST).add(ModItems.MERCURY_DUST.get());
    });
  }

  public static void load () {

  }
}
