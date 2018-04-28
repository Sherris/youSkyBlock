package com.farmsoft.youskyblock;

import com.farmsoft.youskyblock.commands.IslandCommand;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = YouSkyBlockMod.MODID, name = YouSkyBlockMod.NAME, version = YouSkyBlockMod.VERSION)
public class YouSkyBlockMod
{
    public static final String MODID = "youskyblock";
    public static final String NAME = "You Sky Block";
    public static final String VERSION = "0.1";

    private static Logger logger;
    public static File ConfigPath;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        ConfigPath = event.getModConfigurationDirectory();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Starting the youSkyBlockMod", "now");
        Blocks.OBSIDIAN.setResistance(10.0F);
        Blocks.ICE.setResistance(3000.0F);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new MyCommand());
        event.registerServerCommand(new IslandCommand());

    }
    }
