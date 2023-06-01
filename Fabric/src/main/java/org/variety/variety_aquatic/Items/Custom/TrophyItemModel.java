package org.variety.variety_aquatic.Items.Custom;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TrophyItemModel extends AnimatedGeoModel<TrophyItem> {
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
    return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/" + mobid + "_trophy_copper.png");
  }

  @Override
  public Identifier getAnimationResource(TrophyItem animatable) {
    return new Identifier(Variety_Aquatic.MOD_ID, "animations/" + mobid + "trophy.animation.json");
  }
}
