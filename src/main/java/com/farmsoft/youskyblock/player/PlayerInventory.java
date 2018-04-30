package com.farmsoft.youskyblock.player;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerInventory extends HashMap<String,Integer> {

    public PlayerInventory (EntityPlayerMP player) {
        String itemID;
        List<ItemStack> fullInventory = new ArrayList<>(player.inventory.mainInventory);
        fullInventory.addAll(player.inventory.armorInventory);
        fullInventory.addAll(player.inventory.offHandInventory);
        for (ItemStack stack : fullInventory) {
            if (stack.getItem() != Item.getItemById(0)) {
                itemID = Item.getIdFromItem(stack.getItem()) + "/" + stack.getItemDamage();
                this.put(itemID, stack.getCount() + this.getOrDefault(itemID,0));
            }
        }
    }
}
