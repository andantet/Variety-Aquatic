package org.variety.variety_aquatic.Block.Tile;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Screen.BoxScreenHandler;

import java.util.HashSet;
import java.util.Set;




public class AquariumBlockEntity extends LootableContainerBlockEntity implements ExtendedScreenHandlerFactory {
    private Set<BlockPos> aquariumBlocks;
    private DefaultedList<ItemStack> items = DefaultedList.ofSize(size(), ItemStack.EMPTY);

    public AquariumBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntity.AQUARIUM, pos, state);
        aquariumBlocks = new HashSet<>();

    }

    public void addBlock(BlockPos pos) {
        aquariumBlocks.add(pos);
    }


    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);

    }
    public boolean hasAquariumLight() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty() && stack.getItem() == ModItems.AQUARIUM_LIGHT_ITEM) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.items = list;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 3 * 3;
    }
}
