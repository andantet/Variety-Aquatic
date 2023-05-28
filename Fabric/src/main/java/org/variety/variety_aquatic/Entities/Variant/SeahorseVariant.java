package org.variety.variety_aquatic.Entities.Variant;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import org.varietymods.varietyapi.API.IVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum SeahorseVariant implements IVariant {
    DEFAULT(0,"blue_seahorse"),
    GREEN_SEAHORSE(1,"green_seahorse"),
    ORANGE_SEAHORSE(2,"orange_seahorse"),
    PINK_SEAHORSE(3,"pink_seahorse"),
    PURPLE_SEAHORSE(4,"purple_seahorse"),
    YELLOW_SEAHORSE(5,"yellow_seahorse");





    private static final SeahorseVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SeahorseVariant::getId)).toArray(SeahorseVariant[]::new);
    private final int id;
    private final String textureName;


    SeahorseVariant(int id,String textureName) {
        this.id = id;
        this.textureName = textureName;

    }

    public int getId() {
        return this.id;
    }

    public static SeahorseVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }


    public Identifier getTextureResource() {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/" + textureName +"_texture"+ ".png");
    }
}


