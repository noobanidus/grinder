package noobanidus.mods.grindr.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.math.MathHelper;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.tiles.GrinderTile;

public class GrinderTileRenderer extends TileEntityRenderer<GrinderTile> {
  private final ModelGrindstone model = new ModelGrindstone();

  @Override
  public void render(GrinderTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
    GrindstoneType type = tile.getGrindstone();
    if (type == GrindstoneType.EMPTY) {
      return;
    }

    tile.previousRotation = tile.rotation;
    /*if (tile.rotation >= tile.desiredRotation) {
      tile.rotationIndex++;
    }
    if (tile.tickPause > 0) {
      tile.tickPause--;
      if (tile.tickPause == 0) {
        tile.lastRotationIndex = tile.rotationIndex;
      }
    } else if (tile.rotationIndex > tile.lastRotationIndex) {
      tile.tickPause = 45;
      tile.desiredRotation += 30;
    } else {
      tile.rotation += 30 / 20f;
      if (tile.rotation > 360) {
        tile.rotation -= 360;
      }
    }*/

    tile.rotation += 1f;
    if (tile.rotation > 360) {
      tile.rotation -= 360;
    }

    //float angle = MathHelper.lerp(partialTicks, tile.previousRotation, tile.rotation);
    float timeRandom = MathHelper.getPositionRandom(tile.getPos()) % 360;
    float time = (tile.getWorld().getGameTime() + timeRandom + partialTicks) * 0.025f;

    GlStateManager.pushMatrix();
    GlStateManager.enableRescaleNormal();
    GlStateManager.translated(x + 0.5, y + 0.8, z + 0.5);
    GlStateManager.scaled(1.15, 1.15, 1.15);

    this.bindTexture(type.getTexture());

    model.rotate(time);
    model.render();

    GlStateManager.disableRescaleNormal();
    GlStateManager.popMatrix();
    GlStateManager.color4f(1, 1, 1, 1);
  }

  public class ModelGrindstone extends Model {
    private final RendererModel grindstone;
    /*    private final RendererModel pivot;*/

    public ModelGrindstone() {
      textureWidth = 32;
      textureHeight = 19;

      grindstone = new RendererModel(this, 0, 0);
      grindstone.setRotationPoint(0.0F, 0.0f, 0.0F);
      grindstone.addBox(-4.0F, -3.0F, -4.0F, 8, 6, 8);

/*      pivot = new RendererModel(this, 0, 14);
      pivot.setRotationPoint(0.0F, 0.0F, 0.0F);
      pivot.addBox(-1.5F, -8.0F, -1.5F, 3, 2, 3);

      grindstone.addChild(pivot);*/
    }

    public void render() {
      grindstone.render(0.05f);
    }

    public void rotate(float angle) {
      this.grindstone.rotateAngleY = angle;
    }
  }
}
