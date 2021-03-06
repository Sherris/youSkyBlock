package com.farmsoft.youskyblock.commands;

import com.farmsoft.youskyblock.Color;
import com.farmsoft.youskyblock.player.PlayerInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

class SetHomeCommand {

    static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {
        String msg;
        if (new PlayerInfo(player).setHome(player)) {
            msg = Color.DARKGREEN + "Home warp successfully set";
        } else {
            msg = Color.RED + "You are not allowed to set your home warp here!";
        }
        player.sendMessage((new TextComponentString(msg)));

    }
}
