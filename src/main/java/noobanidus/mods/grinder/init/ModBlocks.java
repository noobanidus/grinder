package noobanidus.mods.grinder.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
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
import noobanidus.mods.grinder.Grinder;
import noobanidus.mods.grinder.blocks.GrinderBlock;
import noobanidus.mods.grinder.blocks.GrindstoneType;

import static noobanidus.mods.grinder.Grinder.REGISTRATE;

public class ModBlocks {
  public static final NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.hardnessAndResistance(3f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).setLightLevel(d -> d.get(GrinderBlock.LIT) ? 15 : 0);

  public static final RegistryEntry<GrinderBlock> GRINDER = REGISTRATE.block("grinder", GrinderBlock::new)
      .properties(STONE_PROPS)
      .item().model((ctx, p) -> p.withExistingParent("grinder", new ResourceLocation(Grinder.MODID, "block/grinder_no_grindstone"))).build()
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry())
          .forAllStates((state) -> {
            GrindstoneType type = state.get(GrinderBlock.GRINDSTONE);
            boolean active = state.get(GrinderBlock.ACTIVE);

            ModelFile existing = type == GrindstoneType.EMPTY ? p.models().getExistingFile(new ResourceLocation(Grinder.MODID, "block/grinder_no_grindstone")) : p.models().getExistingFile(new ResourceLocation(Grinder.MODID, "block/grinder_template"));

            String texture = type == GrindstoneType.EMPTY ? "granite" : type.toString().toLowerCase();

            ModelFile model = p.models().getBuilder(type.toString() + (active ? "_hot" : "_cold"))
                .parent(existing)
                .texture("front", new ResourceLocation(Grinder.MODID, active ? "block/grinder_front_hot" : "block/grinder_front_cold"))
                .texture("grindstone", new ResourceLocation(Grinder.MODID, "block/" + texture));

            return ConfiguredModel.builder()
                .modelFile(model)
                .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
                .build();
          }))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
          .patternLine("XIX")
          .patternLine("XFX")
          .patternLine("XXX")
          .key('I', Tags.Items.INGOTS_IRON)
          .key('F', Items.FURNACE)
          .key('X', Items.POLISHED_ANDESITE)
          .addCriterion("has_iron", RegistrateRecipeProvider.hasItem(Tags.Items.INGOTS_IRON))
          .addCriterion("has_andesite", RegistrateRecipeProvider.hasItem(Items.ANDESITE))
          .addCriterion("has_furnace", RegistrateRecipeProvider.hasItem(Items.FURNACE))
          .build(p))
      .register();

  public static void load() {

  }
}
