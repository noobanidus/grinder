package noobanidus.mods.grindr.init;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.recipes.GrinderRecipe;
import noobanidus.mods.grindr.recipes.GrinderRecipeSerializer;
import noobanidus.mods.grindr.recipes.TagBlastingRecipe;
import noobanidus.mods.grindr.recipes.TagFurnaceRecipe;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModRecipes {
  public static IRecipeType<GrinderRecipe> GRINDER_TYPE = IRecipeType.register(new ResourceLocation(Grindr.MODID, "grinder").toString());

  public static RegistryEntry<GrinderRecipeSerializer> GRINDER_SERIALIZER = REGISTRATE.object("grinder").recipeSerializer(GrinderRecipeSerializer::new).register();

  public static RegistryEntry<TagFurnaceRecipe.Serializer> TAG_FURNACE_SERIALIZER = REGISTRATE.object("furnace").recipeSerializer(TagFurnaceRecipe.Serializer::new).register();
  public static RegistryEntry<TagBlastingRecipe.Serializer> TAG_BLASTING_SERIALIZER = REGISTRATE.object("blasting").recipeSerializer(TagBlastingRecipe.Serializer::new).register();

  static {
    REGISTRATE.addDataGenerator(ProviderType.RECIPE, (ctx) -> {

    });
  }

  public static void load() {

  }
}
