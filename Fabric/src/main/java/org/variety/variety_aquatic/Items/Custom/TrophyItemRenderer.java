package org.variety.variety_aquatic.Items.Custom;


import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TrophyItemRenderer extends GeoItemRenderer<TrophyItem> {
  public TrophyItemRenderer(String mobid) {
    super(new TrophyItemModel(mobid));
  }
}
