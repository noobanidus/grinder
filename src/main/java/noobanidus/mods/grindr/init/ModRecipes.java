package noobanidus.mods.grindr.init;

import com.google.common.collect.Sets;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.advancements.Advancement;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.recipes.*;

import java.util.Set;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModRecipes {
  public static IRecipeType<GrinderRecipe> GRINDER_TYPE = IRecipeType.register(new ResourceLocation(Grindr.MODID, "grinder").toString());

  public static RegistryEntry<GrinderRecipeSerializer> GRINDER_SERIALIZER = REGISTRATE.object("grinder").recipeSerializer(GrinderRecipeSerializer::new).register();

  public static RegistryEntry<TagFurnaceRecipe.Serializer> TAG_FURNACE_SERIALIZER = REGISTRATE.object("furnace").recipeSerializer(TagFurnaceRecipe.Serializer::new).register();
  public static RegistryEntry<TagBlastingRecipe.Serializer> TAG_BLASTING_SERIALIZER = REGISTRATE.object("blasting").recipeSerializer(TagBlastingRecipe.Serializer::new).register();

  public static Set<GrindstoneType> SKIP = Sets.newHashSet(GrindstoneType.EMPTY, GrindstoneType.STONE, GrindstoneType.GRANITE, GrindstoneType.DIORITE, GrindstoneType.ANDESITE, GrindstoneType.DIAMOND, GrindstoneType.EMERALD, GrindstoneType.OBSIDIAN);

  static {
    REGISTRATE.addDataGenerator(ProviderType.RECIPE, (ctx) -> {
      GrinderRecipeBuilder.builder(Items.REDSTONE, Tags.Items.ORES_REDSTONE).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/redstone_dust"));
      GrinderRecipeBuilder.builder(Items.LAPIS_LAZULI, Tags.Items.ORES_LAPIS).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/lapis_lazuli"));
      GrinderRecipeBuilder.builder(Items.COAL, Tags.Items.ORES_COAL).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/coal"));
      GrinderRecipeBuilder.builder(Items.DIAMOND, Tags.Items.ORES_DIAMOND).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/diamond"));
      GrinderRecipeBuilder.builder(Items.EMERALD, Tags.Items.ORES_EMERALD).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/emerald"));
      GrinderRecipeBuilder.builder(Items.QUARTZ, Tags.Items.ORES_QUARTZ, 2).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/quartz"));
      GrinderRecipeBuilder.builder(Items.BONE_MEAL, Tags.Items.BONES, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/bone_meal"));
      GrinderRecipeBuilder.builder(Items.SUGAR, Items.SUGAR_CANE, 2).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/sugar"));
      GrinderRecipeBuilder.builder(Items.SAND, Tags.Items.SANDSTONE, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/sandstone"));
      GrinderRecipeBuilder.builder(Items.GRAVEL, Tags.Items.STONE).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/gravel"));
      GrinderRecipeBuilder.builder(Items.FLINT, Tags.Items.GRAVEL).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/flint"));
      GrinderRecipeBuilder.builder(Items.GLOWSTONE_DUST, Items.GLOWSTONE, 4, true).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/glowstone_dust"));
      GrinderRecipeBuilder.builder(Items.QUARTZ, Tags.Items.STORAGE_BLOCKS_QUARTZ, 4, true).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/quartz_from_quartz_storage"));
      GrinderRecipeBuilder.builder(Items.BLAZE_POWDER, Items.BLAZE_ROD, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/blaze_powder_from_blaze_rod"));
      GrinderRecipeBuilder.builder(Items.GUNPOWDER, Items.BLAZE_POWDER, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/gunpowder_from_blaze_powder"));
      GrinderRecipeBuilder.builder(Items.GUNPOWDER, Items.GHAST_TEAR, 4).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/gunpowder_from_ghast_tear"));
      GrinderRecipeBuilder.builder(Items.GUNPOWDER, Items.CREEPER_HEAD, 10).build(ctx, new ResourceLocation(Grindr.MODID, "grinding/gunpowder_from_creeper_head"));

      for (GrindstoneType type : GrindstoneType.values()) {
        if (SKIP.contains(type)) {
          continue;
        }
        if (type.getTag() == null) {
          continue;
        }
        ConditionalRecipe.builder()
            .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
            .addRecipe(GrinderRecipeBuilder.builder(type.getRecycleItem(), type.getTag(), 1, true)::build)
            .setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/dusts/" + type.name().toLowerCase()), ConditionalAdvancement.builder()
                .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
                .addAdvancement(Advancement.Builder.builder()))
            .build(ctx, new ResourceLocation(Grindr.MODID, "grinding/" + type.name().toLowerCase() + "_dust_from_ingot"));
      }
    });
  }


  public static void load() {

  }
}
