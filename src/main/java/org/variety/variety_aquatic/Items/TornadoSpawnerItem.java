package org.variety.variety_aquatic.Items;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.TornadoEntity;
import org.variety.variety_aquatic.Variety_Aquatic;

public class TornadoSpawnerItem extends Item {
    public TornadoSpawnerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            TornadoEntity tornado = new TornadoEntity(ModEntities.TORNADO, context.getWorld());
            tornado.refreshPositionAndAngles(context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 1, context.getBlockPos().getZ() + 0.5, 0, 0);
            context.getWorld().spawnEntity(tornado);
        }

        return ActionResult.SUCCESS;
    }
}