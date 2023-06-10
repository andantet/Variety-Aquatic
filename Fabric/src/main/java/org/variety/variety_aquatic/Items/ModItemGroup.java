package org.variety.variety_aquatic.Items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModItemGroup {
    public static ItemGroup VARIETYAQUATIC;

    public static void registerItemGroups() {
        VARIETYAQUATIC = FabricItemGroup.builder(new Identifier(Variety_Aquatic.MOD_ID, "varietyaquatic"))
                .displayName(Text.translatable("itemgroup.varietyaquatic"))
                .icon(() -> new ItemStack(ModItems.RAW_PIRANHA)).build();
    }
}