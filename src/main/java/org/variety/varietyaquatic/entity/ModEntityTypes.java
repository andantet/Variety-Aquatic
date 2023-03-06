package org.variety.varietyaquatic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import org.variety.varietyaquatic.entity.custom.SunfishEntity;

public class ModEntityTypes   {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VarietyAquatic.MODID);

        public static final RegistryObject<EntityType<SunfishEntity>> SUNFISH =
                ENTITY_TYPES.register("sunfish",
                        () -> EntityType.Builder.of(SunfishEntity::new, MobCategory.WATER_CREATURE)
                                .sized(0.4f, 1.5f)
                                .build(new ResourceLocation(VarietyAquatic.MODID, "sunfish").toString()));
    public static final RegistryObject<EntityType<SharkEntity>> SHARK =
            ENTITY_TYPES.register("shark",
                    () -> EntityType.Builder.of(SharkEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.4f, 1.5f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "shark").toString()));


        public static void register(IEventBus eventBus) {
            ENTITY_TYPES.register(eventBus);
        }
}
