package org.variety.variety_aquatic.Common.Items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Common.Block.ModBlock;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModItemGroup {


    public static ItemGroup VARIETYAQUATIC = Registry.register(Registries.ITEM_GROUP, new Identifier(Variety_Aquatic.MOD_ID, "varietyaquatic"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.varietyaquatic"))
                    .icon(() -> new ItemStack(ModItems.RAW_PIRANHA)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SUNFISH_EGG);
                        entries.add(ModItems.SQUIDLING_EGG);
                        entries.add(ModItems.SHARK_EGG);
                        entries.add(ModItems.SEAHORSE_EGG);
                        entries.add(ModItems.SEAANGEL_EGG);
                        entries.add(ModItems.LEVIATHAN_EGG);
                        entries.add(ModItems.WHALESHARK_EGG);
                        entries.add(ModItems.BETTA_EGG);
                        entries.add(ModItems.BARRELEYE_EGG);
                        entries.add(ModItems.TETRA_EGG);
                        entries.add(ModItems.RAW_BETTA);
                        entries.add(ModItems.GIANTSQUIDBEAK);
                        entries.add(ModItems.GIANTSQUIDEYE);
                        entries.add(ModItems.RAW_TETRA);
                        entries.add(ModItems.YELLOWFIN_EGG);
                        entries.add(ModItems.STINGRAY_EGG);
                        entries.add(ModItems.FLASHLIGHTFISH_EGG);
                        entries.add(ModItems.OARFISH_EGG);
                        entries.add(ModItems.MOONJELLY_EGG);
                        entries.add(ModItems.ANGLER_BULB);
                        entries.add(ModItems.CLOWNFISH_EGG);
                        entries.add(ModItems.JELLYFISH_EGG);
                        entries.add(ModItems.CUTTLEFISH_EGG);
                        entries.add(ModItems.ANGLER_EGG);
                        entries.add(ModItems.ANGLER_TORCH);
                        entries.add(ModItems.OPAH_EGG);
                        entries.add(ModItems.VAMPIRESQUID_EGG);
                        entries.add(ModItems.GIANTGLOWINGSQUID_EGG);
                        entries.add(ModItems.LIONFISH_EGG);
                        entries.add(ModItems.CRAB_EGG);
                        entries.add(ModItems.RAW_LIONFISH);
                        entries.add(ModItems.LIONFISH_COOKED);
                        entries.add(ModItems.RAW_TUNA);
                        entries.add(ModItems.COOKED_TUNA);
                        entries.add(ModItems.PIRANHA_EGG);
                        entries.add(ModItems.RAW_PIRANHA);
                        entries.add(ModItems.CRAB_RAVE_MUSIC_DISC);
                        entries.add(ModItems.GIANTSQUID_TROPHY_BLOCK_ITEM);
                        entries.add(ModItems.LEVIATHAN_TROPHY_BLOCK_ITEM);
                        entries.add(ModBlock.BEHOLDER);
                        entries.add(ModBlock.ANEMONE_BLOCK);
                        entries.add(ModBlock.SEA_URCHIN_BLOCK);
                    }).build());

    public static void registerItemGroups() {

    }

}