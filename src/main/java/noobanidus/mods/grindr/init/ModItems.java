package noobanidus.mods.grindr.init;

import com.tterrag.registrate.util.RegistryEntry;
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
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.GrindrTags;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.items.GrindstoneItem;
import noobanidus.mods.grindr.items.GroundItem;
import noobanidus.mods.grindr.recipes.GrinderRecipeBuilder;
import noobanidus.mods.grindr.recipes.TagCookingRecipeBuilder;

import java.util.*;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModItems {
  public static NonNullUnaryOperator<Item.Properties> ITEM_PROPERTIES = (o) -> o.group(Grindr.ITEM_GROUP);

  public static final RegistryEntry<GroundItem> GOLD_DUST = REGISTRATE.item("gold_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.GOLD_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.GOLD_INGOT, p);
  }).tag(GrindrTags.Items.GOLD_DUST).register();
  public static final RegistryEntry<GroundItem> IRON_DUST = REGISTRATE.item("iron_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.IRON_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.IRON_INGOT, p);
  }).tag(GrindrTags.Items.IRON_DUST).register();
  public static final RegistryEntry<GroundItem> SILVER_DUST = REGISTRATE.item("silver_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.SILVER_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.SILVER_INGOT, p);
  }).tag(GrindrTags.Items.SILVER_DUST).register();
  public static final RegistryEntry<GroundItem> COPPER_DUST = REGISTRATE.item("copper_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.COPPER_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.COPPER_INGOT, p);
  }).tag(GrindrTags.Items.COPPER_DUST).register();
  public static final RegistryEntry<GroundItem> TIN_DUST = REGISTRATE.item("tin_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.TIN_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.TIN_INGOT, p);
  }).tag(GrindrTags.Items.TIN_DUST).register();
  public static final RegistryEntry<GroundItem> NICKEL_DUST = REGISTRATE.item("nickel_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.NICKEL_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.NICKEL_INGOT, p);
  }).tag(GrindrTags.Items.NICKEL_DUST).register();
  public static final RegistryEntry<GroundItem> LEAD_DUST = REGISTRATE.item("lead_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.LEAD_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.LEAD_INGOT, p);
  }).tag(GrindrTags.Items.LEAD_DUST).register();
  public static final RegistryEntry<GroundItem> ALUMINUM_DUST = REGISTRATE.item("aluminum_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.ALUMINUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.ALUMINUM_INGOT, p);
  }).tag(GrindrTags.Items.ALUMINUM_DUST).register();
  public static final RegistryEntry<GroundItem> URANIUM_DUST = REGISTRATE.item("uranium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.URANIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.URANIUM_INGOT, p);
  }).tag(GrindrTags.Items.URANIUM_DUST).register();
  public static final RegistryEntry<GroundItem> ZINC_DUST = REGISTRATE.item("zinc_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.ZINC_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.ZINC_INGOT, p);
  }).tag(GrindrTags.Items.ZINC_DUST).register();
  public static final RegistryEntry<GroundItem> PLATINUM_DUST = REGISTRATE.item("platinum_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.PLATINUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.PLATINUM_INGOT, p);
  }).tag(GrindrTags.Items.PLATINUM_DUST).register();
  public static final RegistryEntry<GroundItem> MERCURY_DUST = REGISTRATE.item("mercury_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.MERCURY_ORE).build(p, new ResourceLocation("grindr:grinding/mercury_dust_from_mercury_ore"));
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.MERCURY_INGOT, p);
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.QUICKSILVER_ORE).build(p, new ResourceLocation("grindr:grinding/mercury_dust_from_quicksilver_ore"));
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.QUICKSILVER_INGOT, p);
  }).register();
  public static final RegistryEntry<GroundItem> BISMUTH_DUST = REGISTRATE.item("bismuth_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.BISMUTH_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.BISMUTH_INGOT, p);
  }).tag(GrindrTags.Items.BISMUTH_DUST).register();
  public static final RegistryEntry<GroundItem> NEPTUNIUM_DUST = REGISTRATE.item("neptunium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.NEPTUNIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.NEPTUNIUM_INGOT, p);
  }).tag(GrindrTags.Items.NEPTUNIUM_DUST).register();
  public static final RegistryEntry<GroundItem> OSMIUM_DUST = REGISTRATE.item("osmium_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.OSMIUM_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.OSMIUM_INGOT, p);
  }).tag(GrindrTags.Items.OSMIUM_DUST).register();
  public static final RegistryEntry<GroundItem> COBALT_DUST = REGISTRATE.item("cobalt_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.COBALT_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.COBALT_INGOT, p);
  }).tag(GrindrTags.Items.COBALT_DUST).register();
  public static final RegistryEntry<GroundItem> ARDITE_DUST = REGISTRATE.item("ardite_dust", GroundItem::new).properties(ITEM_PROPERTIES).recipe((ctx, p) -> {
    GrinderRecipeBuilder.builder(ctx.getEntry(), GrindrTags.Items.ARDITE_ORE).build(p);
    TagCookingRecipeBuilder.ingot(ctx.getEntry(), GrindrTags.Items.ARDITE_INGOT, p);
  }).tag(GrindrTags.Items.ARDITE_DUST).register();

  public static final List<RegistryEntry<GroundItem>> DUST_ITEMS = Arrays.asList(GOLD_DUST, IRON_DUST, SILVER_DUST, COPPER_DUST, TIN_DUST, NICKEL_DUST, LEAD_DUST, ALUMINUM_DUST, URANIUM_DUST, ZINC_DUST, PLATINUM_DUST, MERCURY_DUST, BISMUTH_DUST, NEPTUNIUM_DUST, OSMIUM_DUST, ARDITE_DUST, COBALT_DUST);

  public static Map<GrindstoneType, RegistryEntry<GrindstoneItem>> GRINDSTONE_MAP = new HashMap<>();

  static {
    for (GrindstoneType type : GrindstoneType.values()) {
      if (type == GrindstoneType.EMPTY) {
        continue;
      }

      GRINDSTONE_MAP.put(type, REGISTRATE.item("grindstone_" + type.toString(), (b) -> new GrindstoneItem(b, type)).properties(ITEM_PROPERTIES).model((ctx, p) -> {
        p.withExistingParent("grindstone_" + type.toString(), new ResourceLocation(Grindr.MODID, "item/grindstone_template"))
            .texture("grindstone", new ResourceLocation(Grindr.MODID, "block/" + type.toString()));
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
                  .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
                  .addRecipe(result::build)
                  .setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/" + type.name().toLowerCase()), ConditionalAdvancement.builder()
                      .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
                      .addAdvancement(Advancement.Builder.builder()))
                  .build(p, new ResourceLocation(Grindr.MODID, Objects.requireNonNull(ctx.getEntry().getRegistryName()).getPath()));
              if (type == GrindstoneType.MERCURY) {
                ShapedRecipeBuilder quicksilver = ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
                    .patternLine(" I ")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('I', Tags.Items.INGOTS_IRON)
                    .key('X', GrindrTags.Items.QUICKSILVER_ORE)
                    .addCriterion("has_quicksilver", p.hasItem(GrindrTags.Items.QUICKSILVER_INGOT));
                ConditionalRecipe.builder()
                    .addCondition(new NotCondition(new TagEmptyCondition(GrindrTags.Items.QUICKSILVER_INGOT.getId())))
                    .addRecipe(quicksilver::build)
                    .setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/quicksilver"), ConditionalAdvancement.builder()
                        .addCondition(new NotCondition(new TagEmptyCondition(GrindrTags.Items.QUICKSILVER_INGOT.getId())))
                        .addAdvancement(Advancement.Builder.builder()))
                    .build(p, new ResourceLocation(Grindr.MODID, "grindstone_quicksilver"));
              }
              ConditionalRecipe.builder()
                  .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
                  .addRecipe(GrinderRecipeBuilder.builder(type.getRecycleItem(), GRINDSTONE_MAP.get(type).get(), 6, true)::build)
                  .setAdvancement(new ResourceLocation(Grindr.MODID, "recipes/recycle/" + type.name().toLowerCase()), ConditionalAdvancement.builder()
                      .addCondition(new NotCondition(new TagEmptyCondition(type.getTag().getId())))
                      .addAdvancement(Advancement.Builder.builder()))
                  .build(p, new ResourceLocation(Grindr.MODID, "recycle/" + type.name().toLowerCase()));
            } else {
              builder.build(p);
              GrinderRecipeBuilder.builder(type.getRecycleItem(), GRINDSTONE_MAP.get(type).get(), 6, true).build(p, new ResourceLocation(Grindr.MODID, "recycle/" + type.name().toLowerCase()));
            }

          })
          .lang("Grindstone (" + StringUtil.capitalize(type.toString()) + ")")
          .register());
    }
  }

  public static void load() {
  }
}

