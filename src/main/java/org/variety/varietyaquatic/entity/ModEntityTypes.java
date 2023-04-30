package org.variety.varietyaquatic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.*;

public class ModEntityTypes   {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VarietyAquatic.MODID);

        public static final RegistryObject<EntityType<SunfishEntity>> SUNFISH =
                ENTITY_TYPES.register("sunfish",
                        () -> EntityType.Builder.of(SunfishEntity::new, MobCategory.WATER_CREATURE)
                                .sized(1.5f, 2.3f)
                                .build(new ResourceLocation(VarietyAquatic.MODID, "sunfish").toString()));
    public static final RegistryObject<EntityType<WhaleSharkEntity>> WHALESHARK =
            ENTITY_TYPES.register("whaleshark",
                    () -> EntityType.Builder.of(WhaleSharkEntity::new, MobCategory.WATER_CREATURE)
                            .sized(5f, 2f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "whaleshark").toString()));
    public static final RegistryObject<EntityType<AnglerfishEntity>> ANGLERFISH =
            ENTITY_TYPES.register("anglerfish",
                    () -> EntityType.Builder.of(AnglerfishEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "anglerfish").toString()));
    public static final RegistryObject<EntityType<BettaEntity>> BETTAFISH =
            ENTITY_TYPES.register("bettafish",
                    () -> EntityType.Builder.of(BettaEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.6f, 0.6f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "bettafish").toString()));

    public static final RegistryObject<EntityType<ClownfishEntity>> CLOWNFISH =
            ENTITY_TYPES.register("clownfish",
                    () -> EntityType.Builder.of(ClownfishEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1f, 0.7f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "clownfish").toString()));
    public static final RegistryObject<EntityType<SharkEntity>> SHARK =
            ENTITY_TYPES.register("shark",
                    () -> EntityType.Builder.of(SharkEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1.6f, 1.6f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "shark").toString()));
    public static final RegistryObject<EntityType<CuttlefishEntity>> CUTTLEFISH =
            ENTITY_TYPES.register("cuttlefish",
                    () -> EntityType.Builder.of(CuttlefishEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1F, 0.8F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "cuttlefish").toString()));
    public static final RegistryObject<EntityType<YellowFinTuna>> YELLOWFIN =
            ENTITY_TYPES.register("yellowfin",
                    () -> EntityType.Builder.of(YellowFinTuna::new, MobCategory.WATER_CREATURE)
                            .sized(1.4F, 1.7F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "yellowfin").toString()));
    public static final RegistryObject<EntityType<TetraEntity>> TETRA =
            ENTITY_TYPES.register("tetra",
                    () -> EntityType.Builder.of(TetraEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "tetra").toString()));
    public static final RegistryObject<EntityType<HermitcrabEntity>> HERMITCRAB =
            ENTITY_TYPES.register("hermitcrab",
                    () -> EntityType.Builder.of(HermitcrabEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.7F, 0.7F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "hermitcrab").toString()));

    public static final RegistryObject<EntityType<CrabEntity>> CRAB =
            ENTITY_TYPES.register("crab",
                    () -> EntityType.Builder.of(CrabEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1f, 0.8f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "crab").toString()));
    public static final RegistryObject<EntityType<LionfishEntity>> LIONFISH =
            ENTITY_TYPES.register("lionfish",
                    () -> EntityType.Builder.of(LionfishEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.5f, 0.5F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "lionfish").toString()));

    public static final RegistryObject<EntityType<MoonJelly>> MOONJELLY =
            ENTITY_TYPES.register("moonjelly",
                    () -> EntityType.Builder.of(MoonJelly::new, MobCategory.WATER_CREATURE)
                            .sized(0.9f, 0.9f)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "moonjelly").toString()));
    public static final RegistryObject<EntityType<Jellyfish>> JELLYFISH =
            ENTITY_TYPES.register("jellyfish",
                    () -> EntityType.Builder.of(Jellyfish::new, MobCategory.WATER_CREATURE)
                            .sized(1F, 1F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "jellyfish").toString()));
    public static final RegistryObject<EntityType<OpahEntity>> OPAH =
            ENTITY_TYPES.register("opah",
                    () -> EntityType.Builder.of(OpahEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1F, 1.3F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "opah").toString()));

    public static final RegistryObject<EntityType<Piranha>> PIRANHA =
            ENTITY_TYPES.register("piranha",
                    () -> EntityType.Builder.of(Piranha::new, MobCategory.WATER_CREATURE)
                            .sized(0.9F, 0.7F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "piranha").toString()));
    public static final RegistryObject<EntityType<SeahorseEntity>> SEAHORSE =
            ENTITY_TYPES.register("seahorse",
                    () -> EntityType.Builder.of(SeahorseEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.7F, 0.7F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "seahorse").toString()));
    public static final RegistryObject<EntityType<SpottedStingrayEntity>> STINGRAY =
            ENTITY_TYPES.register("stingray",
                    () -> EntityType.Builder.of(SpottedStingrayEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.35F, 0.6F)
                            .build(new ResourceLocation(VarietyAquatic.MODID, "stingray").toString()));

        public static void register(IEventBus eventBus) {
            ENTITY_TYPES.register(eventBus);
        }
}
