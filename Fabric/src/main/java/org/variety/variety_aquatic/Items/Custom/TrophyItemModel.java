package org.variety.variety_aquatic.Common.Items.Custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class TrophyItemModel extends GeoModel<TrophyItem> {
  public String mobid;

  public TrophyItemModel(String mobid) {
    this.mobid = mobid;
  }

  @Override
  public Identifier getModelResource(TrophyItem object) {
    return new Identifier(Variety_Aquatic.MOD_ID, "geo/" + mobid + "trophy.geo.json");
  }

  @Override
  public Identifier getTextureResource(TrophyItem object) {
    Difficulty worldDifficulty = MinecraftClient.getInstance().world.getDifficulty();
    String difficultySuffix;

    // Determine the texture suffix based on the world's difficulty
    switch (worldDifficulty) {
      case EASY:
        difficultySuffix = "_copper";
        break;
      case NORMAL:
        difficultySuffix = "_iron";
        break;
      case HARD:
        difficultySuffix = "_gold";
        break;
      default:
        difficultySuffix = "_copper"; // Default texture if difficulty is not recognized
        break;
    }

    return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/" + mobid + "_trophy" + difficultySuffix + ".png");
  }

  @Override
  public Identifier getAnimationResource(TrophyItem animatable) {
    return new Identifier(Variety_Aquatic.MOD_ID, "animations/" + mobid + "trophy.animation.json");
  }
}
