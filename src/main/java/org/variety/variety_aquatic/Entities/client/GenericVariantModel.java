package org.variety.variety_aquatic.Entities.client;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericVariantModel<T extends LivingEntity & IVariantEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final String modelResourcePath;
    private final String animationResourcePath;

    public GenericVariantModel(String modelResourcePath, String animationResourcePath) {
        this.modelResourcePath = modelResourcePath;
        this.animationResourcePath = animationResourcePath;
    }

    @Override
    public Identifier getModelResource(T object) {
        return new Identifier(Variety_Aquatic.MOD_ID, modelResourcePath);
    }

    @Override
    public Identifier getTextureResource(T object) {
        return object.getVariant().getTextureResource();
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, animationResourcePath);
    }
}
