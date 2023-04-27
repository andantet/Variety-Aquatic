package org.variety.variety_aquatic.Items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModItemgroup {
    public static final ItemGroup VARIETY_AQUATIC = FabricItemGroupBuilder.build(
            new Identifier(Variety_Aquatic.MOD_ID, "variety_aquatic"), () -> new ItemStack(ModItems.PIRANHA_BUCKET));
}

