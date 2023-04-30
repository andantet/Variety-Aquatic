package org.variety.varietyaquatic.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VarietyAquatic.MODID);


    public static final RegistryObject<Item> SUNFISH_SPAWN_EGG = ITEMS.register("sunfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SUNFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> SHARK_SPAWN_EGG = ITEMS.register("shark_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SHARK, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));

    public static final RegistryObject<Item> ANGLER_SPAWN_EGG = ITEMS.register("angler_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ANGLERFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> BETTA_SPAWN_EGG = ITEMS.register("betta_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.BETTAFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> CLOWNFISH_SPAWN_EGG = ITEMS.register("clownfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CLOWNFISH, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> TETRA_SPAWN_EGG = ITEMS.register("tetra_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TETRA, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));
    public static final RegistryObject<Item> HERMITCRAB_SPAWN_EGG = ITEMS.register("hermitcrab_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.HERMITCRAB, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreative.VARIETY_AQUATIC_TAB)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}