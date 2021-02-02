package noobanidus.mods.grinder.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import noobanidus.libs.noobutil.util.StringUtil;
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.GrinderTags;
import noobanidus.mods.grinder.blocks.GrindstoneType;
import noobanidus.mods.grinder.items.GrindstoneItem;
import noobanidus.mods.grinder.items.GroundItem;
import noobanidus.mods.grinder.recipes.GrinderRecipeBuilder;
import noobanidus.mods.grinder.recipes.TagCookingRecipeBuilder;

import java.util.*;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModItems {
  public static NonNullUnaryOperator<Item.Properties> ITEM_PROPERTIES = (o) -> o.group(Grinder.ITEM_GROUP);

  public static final RegistryEntry<GroundItem> GOLD_DUST = REGISTRATE.item("gold_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.GOLD_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.GOLD_INGOT, p);
  }).tag(GrinderTags.Items.GOLD_DUST).register();
  public static final RegistryEntry<GroundItem> IRON_DUST = REGISTRATE.item("iron_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.IRON_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.IRON_INGOT, p);
  }).tag(GrinderTags.Items.IRON_DUST).register();
  public static final RegistryEntry<GroundItem> SILVER_DUST = REGISTRATE.item("silver_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.SILVER_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.SILVER_INGOT, p);
  }).tag(GrinderTags.Items.SILVER_DUST).register();
  public static final RegistryEntry<GroundItem> COPPER_DUST = REGISTRATE.item("copper_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.COPPER_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.COPPER_INGOT, p);
  }).tag(GrinderTags.Items.COPPER_DUST).register();
  public static final RegistryEntry<GroundItem> TIN_DUST = REGISTRATE.item("tin_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.TIN_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.TIN_INGOT, p);
  }).tag(GrinderTags.Items.TIN_DUST).register();
  public static final RegistryEntry<GroundItem> NICKEL_DUST = REGISTRATE.item("nickel_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.NICKEL_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.NICKEL_INGOT, p);
  }).tag(GrinderTags.Items.NICKEL_DUST).register();
  public static final RegistryEntry<GroundItem> LEAD_DUST = REGISTRATE.item("lead_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.LEAD_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.LEAD_INGOT, p);
  }).tag(GrinderTags.Items.LEAD_DUST).register();
  public static final RegistryEntry<GroundItem> ALUMINUM_DUST = REGISTRATE.item("aluminum_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.ALUMINUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.ALUMINUM_INGOT, p);
  }).tag(GrinderTags.Items.ALUMINUM_DUST).register();
  public static final RegistryEntry<GroundItem> URANIUM_DUST = REGISTRATE.item("uranium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.URANIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.URANIUM_INGOT, p);
  }).tag(GrinderTags.Items.URANIUM_DUST).register();
  public static final RegistryEntry<GroundItem> ZINC_DUST = REGISTRATE.item("zinc_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.ZINC_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.ZINC_INGOT, p);
  }).tag(GrinderTags.Items.ZINC_DUST).register();
  public static final RegistryEntry<GroundItem> PLATINUM_DUST = REGISTRATE.item("platinum_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.PLATINUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.PLATINUM_INGOT, p);
  }).tag(GrinderTags.Items.PLATINUM_DUST).register();
  public static final RegistryEntry<GroundItem> MERCURY_DUST = REGISTRATE.item("mercury_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.MERCURY_ORE).build(p, new ResourceLocation("grinder:grinding/mercury_dust_from_mercury_ore"));
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.MERCURY_INGOT, p);
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.QUICKSILVER_ORE).build(p, new ResourceLocation("grinder:grinding/mercury_dust_from_quicksilver_ore"));
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.QUICKSILVER_INGOT, p);
  }).register();
  public static final RegistryEntry<GroundItem> BISMUTH_DUST = REGISTRATE.item("bismuth_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.BISMUTH_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.BISMUTH_INGOT, p);
  }).tag(GrinderTags.Items.BISMUTH_DUST).register();
  public static final RegistryEntry<GroundItem> NEPTUNIUM_DUST = REGISTRATE.item("neptunium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.NEPTUNIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.NEPTUNIUM_INGOT, p);
  }).tag(GrinderTags.Items.NEPTUNIUM_DUST).register();
  public static final RegistryEntry<GroundItem> OSMIUM_DUST = REGISTRATE.item("osmium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.OSMIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.OSMIUM_INGOT, p);
  }).tag(GrinderTags.Items.OSMIUM_DUST).register();
  public static final RegistryEntry<GroundItem> COBALT_DUST = REGISTRATE.item("cobalt_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.COBALT_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.COBALT_INGOT, p);
  }).tag(GrinderTags.Items.COBALT_DUST).register();
  public static final RegistryEntry<GroundItem> ARDITE_DUST = REGISTRATE.item("ardite_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.ARDITE_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.ARDITE_INGOT, p);
  }).tag(GrinderTags.Items.ARDITE_DUST).register();

  public static final RegistryEntry<GroundItem> ZITRITE_DUST = REGISTRATE.item("zitrite_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.ZITRITE_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.ZITRITE_INGOT, p);
  }).tag(GrinderTags.Items.ZITRITE_DUST).register();

  public static final RegistryEntry<GroundItem> RAINBOW_DUST = REGISTRATE.item("rainbow_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.RAINBOW_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.RAINBOW_INGOT, p);
  }).tag(GrinderTags.Items.RAINBOW_DUST).register();

  public static final RegistryEntry<GroundItem> IESNIUM_DUST = REGISTRATE.item("iesnium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.IESNIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.IESNIUM_INGOT, p);
  }).tag(GrinderTags.Items.IESNIUM_DUST).register();

  public static final RegistryEntry<GroundItem> STARMETAL_DUST = REGISTRATE.item("starmetal_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.STARMETAL_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.STARMETAL_INGOT, p);
  }).tag(GrinderTags.Items.STARMETAL_DUST).register();

  public static final RegistryEntry<GroundItem> TUNGSTEN_DUST = REGISTRATE.item("tungsten_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrinderTags.Items.TUNGSTEN_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrinderTags.Items.TUNGSTEN_INGOT, p);
  }).tag(GrinderTags.Items.TUNGSTEN_DUST).register();

  public static Map<GrindstoneType, RegistryEntry<GrindstoneItem>> GRINDSTONE_MAP = new HashMap<>();

  static {
    for (GrindstoneType type : GrindstoneType.values()) {
      if (type == GrindstoneType.EMPTY) {
        continue;
      }

      GRINDSTONE_MAP.put(type, REGISTRATE.item("grindstone_" + type.toString(), (b) -> new GrindstoneItem(b, type)).properties(ITEM_PROPERTIES).model((ctx, p) -> {
        p.withExistingParent("grindstone_" + type.toString(), new ResourceLocation(Grinder.MODID, "item/grindstone_template"))
            .texture("grindstone", new ResourceLocation(Grinder.MODID, "block/" + type.toString()));
      })
          .recipe((ctx, p) -> {
            ShapedRecipeBuilder builder = ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
                .patternLine(" I ")
                .patternLine("XXX")
                .patternLine("XXX")
                .key('I', Tags.Items.INGOTS_IRON);

            ShapedRecipeBuilder result;

            if (type.getTag() != null) {
              result = builder.key('X', type.getTag())
                  .addCriterion("has_" + type.toString(), p.hasItem(type.getTag()));
            } else if (type.getItem() != null) {
              result = builder.key('X', type.getItem())
                  .addCriterion("has_" + type.toString(), p.hasItem(type.getItem()));
            } else {
              throw new IllegalArgumentException("Invalid Grindstone Type " + type.toString() + ": no Tag or associated Item");
            }

            if (GrindstoneType.INGOT_TO_ORE.containsKey(type) && type.getTag() != null) {
              ConditionalRecipe.builder()
                  .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getName())))
                  .addRecipe(result::build)
                  .setAdvancement(new ResourceLocation(Grinder.MODID, "recipes/" + type.name().toLowerCase()), ConditionalAdvancement.builder()
                      .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getName())))
                      .addAdvancement(Advancement.Builder.builder()))
                  .build(p, new ResourceLocation(Grinder.MODID, Objects.requireNonNull(ctx.getEntry().getRegistryName()).getPath()));
              if (type == GrindstoneType.MERCURY) {
                ShapedRecipeBuilder quicksilver = ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
                    .patternLine(" I ")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('I', Tags.Items.INGOTS_IRON)
                    .key('X', GrinderTags.Items.QUICKSILVER_ORE)
                    .addCriterion("has_quicksilver", p.hasItem(GrinderTags.Items.QUICKSILVER_INGOT));
                ConditionalRecipe.builder()
                    .addCondition(new NotCondition(new TagEmptyCondition(GrinderTags.Items.QUICKSILVER_INGOT.getName())))
                    .addRecipe(quicksilver::build)
                    .setAdvancement(new ResourceLocation(Grinder.MODID, "recipes/quicksilver"), ConditionalAdvancement.builder()
                        .addCondition(new NotCondition(new TagEmptyCondition(GrinderTags.Items.QUICKSILVER_INGOT.getName())))
                        .addAdvancement(Advancement.Builder.builder()))
                    .build(p, new ResourceLocation(Grinder.MODID, "grindstone_quicksilver"));
              }
              ConditionalRecipe.builder()
                  .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getName())))
                  .addRecipe(GrinderRecipeBuilder.builder(type.getRecycleItem(), GRINDSTONE_MAP.get(type).get(), 6, true)::build)
                  .setAdvancement(new ResourceLocation(Grinder.MODID, "recipes/recycle/" + type.name().toLowerCase()), ConditionalAdvancement.builder()
                      .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getName())))
                      .addAdvancement(Advancement.Builder.builder()))
                  .build(p, new ResourceLocation(Grinder.MODID, "recycle/" + type.name().toLowerCase()));
            } else {
              builder.build(p);
              GrinderRecipeBuilder.builder(type.getRecycleItem(), GRINDSTONE_MAP.get(type).get(), 6, true).build(p, new ResourceLocation(Grinder.MODID, "recycle/" + type.name().toLowerCase()));
            }

          })
          .lang("Grindstone (" + StringUtil.capitalize(type.toString()) + ")")
          .register());
    }
  }

  public static void load() {
  }
}
