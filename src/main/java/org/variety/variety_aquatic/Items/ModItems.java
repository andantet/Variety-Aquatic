package org.variety.variety_aquatic.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import org.variety.variety_aquatic.Entities.ModEntities;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

import static software.bernie.example.registry.ItemRegistry.registerItem;

public class ModItems {
    public static final Item SUNFISH_EGG = registerItem("sunfish_egg",
            new SpawnEggItem(ModEntities.SUNFISH,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item SHARK_EGG = registerItem("shark_egg",
            new SpawnEggItem(ModEntities.SHARK,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item WHALESHARK_EGG = registerItem("whaleshark_egg",
            new SpawnEggItem(ModEntities.WHALESHARK,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item BLUEFIN_EGG = registerItem("bluefin_egg",
            new SpawnEggItem(ModEntities.BLUEFIN,0x22b341, 0x19732e,
                    new FabricItemSettings()));

    public static final Item MOONJELLY_EGG = registerItem("moonjelly_egg",
            new SpawnEggItem(ModEntities.MOONJELLY,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item JELLYFISH_EGG = registerItem("jellyfish_egg",
            new SpawnEggItem(ModEntities.JELLYFISH,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item HERMITCRAB_EGG = registerItem("hermitcrab_egg",
            new SpawnEggItem(ModEntities.HERMITCRAB,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item CUTTLEFISH_EGG = registerItem("cuttlefish_egg",
            new SpawnEggItem(ModEntities.CUTTLEFISH,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item OPAH_EGG = registerItem("opah_egg",
            new SpawnEggItem(ModEntities.OPAH,0x22b341, 0x19732e,
                    new FabricItemSettings()));
    public static final Item LIONFISH_EGG = registerItem("lionfish_egg",
            new SpawnEggItem(ModEntities.LIONFISH,0x22b341, 0x19732e,
                    new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name), item);
    }

    public static void addItemsToItemGroups() {
        addToItemGroup(ItemGroups.SPAWN_EGGS, SUNFISH_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, SHARK_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, CUTTLEFISH_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, OPAH_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, LIONFISH_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, JELLYFISH_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, WHALESHARK_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, MOONJELLY_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, BLUEFIN_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, HERMITCRAB_EGG);


    }

    public static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    public static void registerModItems() {
        Variety_Aquatic.LOGGER.debug("Registering Mod Items for " + Variety_Aquatic.MOD_ID);

        addItemsToItemGroups();
    }
}
