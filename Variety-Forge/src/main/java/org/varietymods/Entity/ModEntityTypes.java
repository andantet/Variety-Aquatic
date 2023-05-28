package org.varietymods.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.varietymods.Entity.Custom.YellowfinTunaEntity;
import org.varietymods.Varietyaquatic;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Varietyaquatic.MODID);

    public static final RegistryObject<EntityType<YellowfinTunaEntity>> YELLOWFIN =
            ENTITY_TYPES.register("yellowfin",
                    () -> EntityType.Builder.of(YellowfinTunaEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1.4F, 1.7F)
                            .build(new ResourceLocation(Varietyaquatic.MODID, "yellowfin").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
