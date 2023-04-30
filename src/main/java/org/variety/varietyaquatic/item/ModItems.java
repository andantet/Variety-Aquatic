package org.variety.varietyaquatic.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.ModSound.ModSound;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

import java.awt.*;

import static org.variety.varietyaquatic.Block.ModBlock.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VarietyAquatic.MODID);


    public static final RegistryObject<Item> SUNFISH_EGG = ITEMS.register("sunfish_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SUNFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> SHARK_EGG = ITEMS.register("shark_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SHARK, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));

    public static final RegistryObject<Item> ANGLER_EGG = ITEMS.register("angler_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ANGLERFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> BETTA_EGG = ITEMS.register("betta_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.BETTAFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> CLOWNFISH_EGG = ITEMS.register("clownfish_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CLOWNFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> TETRA_EGG = ITEMS.register("tetra_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TETRA, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> HERMITCRAB_EGG = ITEMS.register("hermitcrab_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.HERMITCRAB, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> CRAB_EGG = ITEMS.register("crab_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CRAB, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> LIONFISH_EGG = ITEMS.register("lionfish_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.LIONFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> MOONJELLY_EGG = ITEMS.register("moonjelly_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.MOONJELLY, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> JELLYFISH_EGG = ITEMS.register("jellyfish_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.JELLYFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> OPAH_EGG = ITEMS.register("opah_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.OPAH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> PIRANHA_EGG = ITEMS.register("piranha_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.PIRANHA, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> SEAHORSE_EGG = ITEMS.register("seahorse_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SEAHORSE, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> WHALESHARK_EGG = ITEMS.register("whaleshark_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.WHALESHARK, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> STINGRAY_EGG = ITEMS.register("stingray_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.STINGRAY, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<StandingAndWallBlockItem> GLOWTORCH_ITEM = ITEMS.register("glowtorch", () -> new StandingAndWallBlockItem(GLOWTORCH.get(), WALL_GLOWTORCH.get(), new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));



    public static final RegistryObject<Item> RAW_TUNA = ITEMS.register("raw_tuna",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.RAW_TUNA)));

    public static final RegistryObject<Item> RAW_TETRA = ITEMS.register("raw_tetra",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.RAW_TETRA)));
    public static final RegistryObject<Item> RAW_PIRANHA = ITEMS.register("raw_piranha",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.RAW_PIRANHA)));

    public static final RegistryObject<Item> RAW_BETTA = ITEMS.register("raw_betta",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.RAW_BETTA)));
    public static final RegistryObject<Item> COOKED_TUNA = ITEMS.register("cooked_tuna",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.COOKED_TUNA)));
    public static final RegistryObject<Item> LIONFISH_COOKED = ITEMS.register("lionfish_cooked",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.LIONFISH_COOKED)));
    public static final RegistryObject<Item> RAW_LIONFISH = ITEMS.register("raw_lionfish",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).food(ModFoods.RAW_LIONFISH)));
    public static final RegistryObject<Item> ANGLER_BULB = ITEMS.register("angler_bulb",
            () -> new Item(new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));


    public static final RegistryObject<Item> CRAB_RAVE_MUSIC_DISC = ITEMS.register("crab_rave_music_disc",
            () -> new RecordItem(4,ModSound.CRAB_RAVE,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB).stacksTo(1),30));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}