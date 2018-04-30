package com.farmsoft.youskyblock.commands;

import com.farmsoft.youskyblock.Color;
import com.farmsoft.youskyblock.player.PlayerInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class HomeCommand {

    public static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {
        BlockPos home = new PlayerInfo(player).getHome();
        //player.sendMessage((new TextComponentString(Color.DARKGREEN + "Teleporting Home in 2 seconds.... Don't Move!")));
        //TODO How to delay teleport 2 seconds??
        player.setPositionAndUpdate(home.getX(), home.getY(), home.getZ());
        player.sendMessage((new TextComponentString(Color.DARKGREEN + "Welcome Home!")));

    }
}
