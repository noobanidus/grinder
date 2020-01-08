package noobanidus.mods.grindr.init;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.recipes.GrinderRecipe;
import noobanidus.mods.grindr.recipes.TagBlastingRecipe;
import noobanidus.mods.grindr.recipes.TagFurnaceRecipe;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModRecipes {
  public static IRecipeType<GrinderRecipe> GRINDER_TYPE = IRecipeType.register(new ResourceLocation(Grindr.MODID, "grinder").toString());

  public static RegistryObject<GrinderRecipe.Serializer> GRINDER_SERIALIZER = REGISTRATE.object("grinder").recipeSerializer(GrinderRecipe.Serializer::new).register();

  public static RegistryObject<TagFurnaceRecipe.Serializer> TAG_FURNACE_SERIALIZER = REGISTRATE.object("furnace").recipeSerializer(TagFurnaceRecipe.Serializer::new).register();
  public static RegistryObject<TagBlastingRecipe.Serializer> TAG_BLASTING_SERIALIZER = REGISTRATE.object("blasting").recipeSerializer(TagBlastingRecipe.Serializer::new).register();

  public static void load() {

  }
}
