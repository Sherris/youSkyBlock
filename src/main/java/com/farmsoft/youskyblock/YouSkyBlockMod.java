package com.farmsoft.youskyblock;

import com.farmsoft.youskyblock.commands.IslandCommand;
import com.farmsoft.youskyblock.island.LevelData;
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
    public static File CONFIGPATH;
    public static LevelData LEVELDATA;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        CONFIGPATH = event.getModConfigurationDirectory();
        logger.info("Ending PreInit", "now");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Starting Init for youSkyBlockMod", "now");
        LEVELDATA = (LevelData)loadData.readFile("levelConfig.yml");
        logger.info("Ending Init for youSkyBlockMod", "now");
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new MyCommand());
        event.registerServerCommand(new IslandCommand());

    }
    }
