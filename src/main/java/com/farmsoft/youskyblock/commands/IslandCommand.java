package com.farmsoft.youskyblock.commands;

import com.farmsoft.youskyblock.Color;
import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class IslandCommand extends CommandBase {
    @Override
    public String getName() {
        return "island";
    }

    @Override
    public List<String> getAliases() {
        ArrayList aliases = new ArrayList();
        aliases.add("is");
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {

        sender.sendMessage((new TextComponentString("Need usage info for /hi?")));
        return "To get more info: /mycommand help (?)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote && args.length>=1) {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            if (args[0].equals("biome")) {
                Logger.logMsg(0,"/is biome detected");
                BiomeCommand.execute(server, getCommandSenderAsPlayer(sender), args);
            } else if (args[0].equals("score")) {
                ScoreCommand.execute(server, getCommandSenderAsPlayer(sender), args);
            } else if (args[0].equals("home")) {
                HomeCommand.execute(server, getCommandSenderAsPlayer(sender), args);
            } else if (args[0].equals("sethome")) {
                SetHomeCommand.execute(server, getCommandSenderAsPlayer(sender), args);
            } else if (args[0].matches("challenge|c")) {
                ChallengeCommand.execute(server, getCommandSenderAsPlayer(sender), args);
            } else  {
                sender.sendMessage((new TextComponentString(Color.RED + "wOops - I don't recognize that command!")));
            }
        }

    }





}
