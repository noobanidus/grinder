package noobanidus.mods.grindr.init;

import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.grindr.Grindr;
import noobanidus.mods.grindr.blocks.GrinderBlock;
import noobanidus.mods.grindr.blocks.GrindstoneType;

import static noobanidus.mods.grindr.Grindr.REGISTRATE;

public class ModBlocks {
  public static final NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.hardnessAndResistance(3f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).lightValue(15);

  public static final RegistryObject<GrinderBlock> GRINDER = REGISTRATE.block("grinder", GrinderBlock::new)
      .properties(STONE_PROPS)
      .item().model(ctx -> ctx.getProvider().withExistingParent("grinder", new ResourceLocation(Grindr.MODID, "block/grinder_no_grindstone"))).build()
      .blockstate(ctx -> ctx.getProvider().getVariantBuilder(ctx.getEntry())
        .forAllStates((state) -> {
          GrindstoneType type = state.get(GrinderBlock.GRINDSTONE);
          boolean active = state.get(GrinderBlock.ACTIVE);

          ModelFile existing = type == GrindstoneType.EMPTY ? ctx.getProvider().getExistingFile(new ResourceLocation(Grindr.MODID, "block/grinder_no_grindstone")) : ctx.getProvider().getExistingFile(new ResourceLocation(Grindr.MODID, "block/grinder_template"));

          String texture = type == GrindstoneType.EMPTY ? "granite" : type.toString().toLowerCase();

          ModelFile model = ctx.getProvider().getBuilder(type.toString() + (active ? "_hot" : "_cold"))
              .parent(existing)
              .texture("front", new ResourceLocation(Grindr.MODID, active ? "block/grinder_front_hot" : "block/grinder_front_cold"))
              .texture("grindstone_top", new ResourceLocation(Grindr.MODID, "block/" + texture))
              .texture("grindstone_side", new ResourceLocation(Grindr.MODID, "block/" + texture + "_side"));

          return ConfiguredModel.builder()
              .modelFile(model)
              .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
              .build();
      }))
      .recipe(ctx -> ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
          .patternLine("XIX")
          .patternLine("XFX")
          .patternLine("XXX")
          .key('I', Tags.Items.INGOTS_IRON)
          .key('F', Items.FURNACE)
          .key('X', Items.POLISHED_ANDESITE)
          .addCriterion("has_iron", ctx.getProvider().hasItem(Tags.Items.INGOTS_IRON))
          .addCriterion("has_andesite", ctx.getProvider().hasItem(Items.ANDESITE))
          .addCriterion("has_furnace", ctx.getProvider().hasItem(Items.FURNACE))
          .build(ctx.getProvider()))
      .register();

  public static void load() {

  }
}
