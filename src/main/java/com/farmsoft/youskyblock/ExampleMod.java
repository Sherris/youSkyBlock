package com.farmsoft.youskyblock;

import com.farmsoft.youskyblock.commands.IslandCommand;
import com.farmsoft.youskyblock.player.PlayerInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "youskyblock";
    public static final String NAME = "You Sky Block";
    public static final String VERSION = "0.1";

    private static Logger logger;
    public static PlayerInfo player;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Starting the youSkyBlockMod", "now");
        //player = new PlayerInfo(Minecraft.getMinecraft().player);
        Blocks.OBSIDIAN.setResistance(10.0F);
        Blocks.ICE.setResistance(3000.0F);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new MyCommand());
        event.registerServerCommand(new IslandCommand());

    }
    }
