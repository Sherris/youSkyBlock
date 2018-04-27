package com.farmsoft.youskyblock.commands;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class SetHomeCommand {

    public static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {
        player.sendMessage((new TextComponentString("\u00a7cTemp Msg - opening SetHome Command")));

    }
}
