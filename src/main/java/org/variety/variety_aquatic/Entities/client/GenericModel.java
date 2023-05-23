package org.variety.variety_aquatic.Entities.client;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericModel<T extends LivingEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;

    public GenericModel(String modelPath, String texturePath, String animationPath) {
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.animationPath = animationPath;
    }

    @Override
    public Identifier getModelResource(T object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/"+ modelPath+".json");
    }

    @Override
    public Identifier getTextureResource(T object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/"+texturePath+".png");
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID,"animations/"+animationPath+".json");
    }
}
