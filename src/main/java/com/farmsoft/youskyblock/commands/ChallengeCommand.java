package com.farmsoft.youskyblock.commands;

import com.farmsoft.youskyblock.player.PlayerInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class ChallengeCommand {

    public static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {
        player.sendMessage((new TextComponentString("\u00a7cTemp Msg - opening Challenges Command")));
        PlayerInfo pi = new PlayerInfo(player);
        pi.die();
        player.sendMessage((new TextComponentString("dead")));

    }
}
