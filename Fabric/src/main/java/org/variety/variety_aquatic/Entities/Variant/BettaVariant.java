package org.variety.variety_aquatic.Common.Entities.Variant;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import org.varietymods.varietyapi.API.IVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum BettaVariant implements IVariant {
    DEFAULT(0, "red_betta"),
    PINK_BETTA(1, "pink_betta"),
    BLUE_BETTA(2, "blue_betta"),
    GREEN_BETTA(3, "green_betta"),
    RED_BLUE_BETTA(4, "red_blue_betta"),
    BLUE_YELLOW_BETTA(5, "blue_yellow_betta"),
    WHITE_BETTA(6, "white_betta"),
    BLACK_BETTA(7, "black_betta");

    private static final BettaVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BettaVariant::getId)).toArray(BettaVariant[]::new);
    private final int id;

    private final String textureName;

    BettaVariant(int id, String textureName) {
        this.id = id;
        this.textureName = textureName;
    }

    public int getId() {
        return this.id;
    }

    public Identifier getTextureLocation() {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/" + textureName + ".png");
    }

    public static BettaVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
