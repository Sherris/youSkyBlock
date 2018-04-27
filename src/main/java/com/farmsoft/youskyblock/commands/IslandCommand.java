package com.farmsoft.youskyblock.commands;

import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            } else  {
                sender.sendMessage((new TextComponentString("\u00a7cOops - I don't recognize that command!")));
            }
        }

    }

    public static final Map<String, Biome> BIOMES = new HashMap<String, Biome>() {
        {
            put("ocean", Biomes.OCEAN);
            put("jungle", Biomes.JUNGLE);
            put("hell", Biomes.HELL);
            put("sky", Biomes.SKY);
            put("mushroom", Biomes.MUSHROOM_ISLAND);
            put("swampland", Biomes.SWAMPLAND);
            put("taiga", Biomes.TAIGA);
            put("desert", Biomes.DESERT);
            put("forest", Biomes.FOREST);
            put("plains", Biomes.PLAINS);
            put("extreme_hills", Biomes.EXTREME_HILLS);
            put("deep_ocean", Biomes.DEEP_OCEAN);
            put("ice_plains", Biomes.ICE_PLAINS);
            put("flower_forest", Biomes.MUTATED_FOREST);
        }
    };




}
