package org.variety.variety_aquatic.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import org.variety.variety_aquatic.Block.Custom.AnglerTorchBlock;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Entities.ModEntities;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

import static org.variety.variety_aquatic.Block.ModBlock.WALL_ANGLER_TORCH;


public class ModItems {

    public static final Item SUNFISH_EGG = registerItem("sunfish_egg",
            new SpawnEggItem(ModEntities.SUNFISH,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item SHARK_EGG = registerItem("shark_egg",
            new SpawnEggItem(ModEntities.SHARK,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item LEVIATHAN_EGG = registerItem("leviathan_egg",
            new SpawnEggItem(ModEntities.LEVIATHAN,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item WHALESHARK_EGG = registerItem("whaleshark_egg",
            new SpawnEggItem(ModEntities.WHALESHARK,0x424c6e, 0xffffff,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item BETTA_EGG = registerItem("betta_egg",
            new SpawnEggItem(ModEntities.BETTA,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));


    public static final Item TETRA_EGG = registerItem("tetra_egg",
            new SpawnEggItem(ModEntities.TETRA,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item TETRA_BUCKET = registerItem("tetra_bucket",
            new EntityBucketItem(ModEntities.TETRA, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item BETTA_BUCKET = registerItem("betta_bucket",
            new EntityBucketItem(ModEntities.BETTA, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item RAW_BETTA = registerItem("raw_betta",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));
    public static final Item RAW_TETRA= registerItem("raw_tetra",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));
    public static final Item YELLOWFIN_EGG = registerItem("yellowfin_egg",
            new SpawnEggItem(ModEntities.YELLOWFIN,0x00396d, 0xffc825,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item STINGRAY_EGG = registerItem("stingray_egg",
            new SpawnEggItem(ModEntities.SPOTTEDSTINGRAY,0xffc825, 0x0069aa,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item MOONJELLY_EGG = registerItem("moonjelly_egg",
            new SpawnEggItem(ModEntities.MOONJELLY,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item ANGLER_BULB = registerItem("angler_bulb",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item CLOWNFISH_EGG = registerItem("clownfish_egg",
            new SpawnEggItem(ModEntities.CLOWNFISH,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item CLOWNFISH_BUCKET = registerItem("clownfish_bucket",
            new EntityBucketItem(ModEntities.CLOWNFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item JELLYFISH_EGG = registerItem("jellyfish_egg",
            new SpawnEggItem(ModEntities.JELLYFISH,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item HERMITCRAB_EGG = registerItem("hermitcrab_egg",
            new SpawnEggItem(ModEntities.HERMITCRAB,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item CUTTLEFISH_EGG = registerItem("cuttlefish_egg",
            new SpawnEggItem(ModEntities.CUTTLEFISH,0x391f21, 0xf6ca9f,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item ANGLER_EGG = registerItem("angler_egg",
            new SpawnEggItem(ModEntities.ANGLERFISH,0x3d3939, 0x89dfff,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item ANGLER_TORCH = registerItem("angler_torch",
            new WallStandingBlockItem(ModBlock.ANGLER_TORCH, ModBlock.WALL_ANGLER_TORCH, (new Item.Settings()).group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item OPAH_EGG = registerItem("opah_egg",
            new SpawnEggItem(ModEntities.OPAH,0x687596, 0x9e2a38,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));


    public static final Item GIANTGLOWINGSQUID_EGG = registerItem("giantglowingsquid_egg",
            new SpawnEggItem(ModEntities.GIANTGLOWINGSQUID,0x22b341, 0x19732e,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item LIONFISH_EGG = registerItem("lionfish_egg",
            new SpawnEggItem(ModEntities.LIONFISH,0xf6ca9f, 0xe07438,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item LIONFISH_BUCKET = registerItem("lionfish_bucket",
            new EntityBucketItem(ModEntities.LIONFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ModItemgroup.VARIETY_AQUATIC)));

    public static final Item RAW_LIONFISH = registerItem("raw_lionfish",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0), 0.8F).meat().build())));


    public static final Item LIONFISH_COOKED = registerItem("lionfish_cooked",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));



    public static final Item PIRANHA_EGG = registerItem("piranha_egg",
            new SpawnEggItem(ModEntities.PIRANHA,0x5e6989, 0x864e3b,
                    new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)));
    public static final Item RAW_PIRANHA = registerItem("raw_piranha",
            new Item(new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));
    public static final Item PIRANHA_BUCKET = registerItem("piranha_bucket",
            new EntityBucketItem(ModEntities.PIRANHA, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ModItemgroup.VARIETY_AQUATIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Variety_Aquatic.LOGGER.debug("Registering Mod Items for " + Variety_Aquatic.MOD_ID);
    }
}






