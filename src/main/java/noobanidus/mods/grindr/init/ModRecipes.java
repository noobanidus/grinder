package noobanidus.mods.grindr.init;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.recipes.*;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModRecipes {
  public static IRecipeType<GrinderRecipe> GRINDER_TYPE = IRecipeType.register(new ResourceLocation(Grindr.MODID, "grinder").toString());

  public static RegistryEntry<GrinderRecipeSerializer> GRINDER_SERIALIZER = REGISTRATE.object("grinder").recipeSerializer(GrinderRecipeSerializer::new).register();

  public static RegistryEntry<TagFurnaceRecipe.Serializer> TAG_FURNACE_SERIALIZER = REGISTRATE.object("furnace").recipeSerializer(TagFurnaceRecipe.Serializer::new).register();
  public static RegistryEntry<TagBlastingRecipe.Serializer> TAG_BLASTING_SERIALIZER = REGISTRATE.object("blasting").recipeSerializer(TagBlastingRecipe.Serializer::new).register();

  static {
    REGISTRATE.addDataGenerator(ProviderType.RECIPE, (ctx) -> {
      GrinderRecipeBuilder.builder(Items.REDSTONE, Tags.Items.ORES_REDSTONE).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/redstone_dust"));
      GrinderRecipeBuilder.builder(Items.LAPIS_LAZULI, Tags.Items.ORES_LAPIS).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/lapis_lazuli"));
      GrinderRecipeBuilder.builder(Items.COAL, Tags.Items.ORES_COAL).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/coal"));
      GrinderRecipeBuilder.builder(Items.DIAMOND, Tags.Items.ORES_DIAMOND).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/diamond"));
      GrinderRecipeBuilder.builder(Items.EMERALD, Tags.Items.ORES_EMERALD).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/emerald"));
      GrinderRecipeBuilder.builder(Items.QUARTZ, Tags.Items.ORES_QUARTZ).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/quartz"));
      GrinderRecipeBuilder.builder(Items.BONE_MEAL, Tags.Items.BONES).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/bone_meal"));
      GrinderRecipeBuilder.builder(Items.SUGAR, Items.SUGAR_CANE).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/sugar"));
      GrinderRecipeBuilder.builder(Items.SAND, Tags.Items.SANDSTONE, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/sandstone"));
      GrinderRecipeBuilder.builder(Items.GRAVEL, Tags.Items.STONE).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/gravel"));
      GrinderRecipeBuilder.builder(Items.FLINT, Tags.Items.GRAVEL).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/flint"));
      GrinderRecipeBuilder.builder(Items.GLOWSTONE_DUST, Items.GLOWSTONE, 4, true).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/glowstone_dust"));
    });
  }

  public static void load() {

  }
}
