package com.farmsoft.youskyblock.commands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BiomeCommand {
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

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("lSkyBlock.mod");

    public static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {
        if (args.length >= 2) {
            final String biome = args[1].toLowerCase();
            if (!biomeExists(biome)) {
                player.sendMessage(new TextComponentString("\u00a7cYou have misspelled the biome name. Must be one of: " + StringUtils.join(BIOMES.keySet(), ", ")));
                return;
            } else {player.sendMessage(new TextComponentString("\u00a7aYou have chosen " + biome));}

            BlockPos position = player.getPosition();

            byte[] chunkArray = server.getWorld(0).getChunkFromBlockCoords(position).getBiomeArray();
            ChunkPos chunkPos = server.getWorld(0).getChunkFromBlockCoords(position).getPos();

            int radius = 192;
            if (args.length==3 && args[2].matches("[0-9]+")) {
                radius = Integer.parseInt(args[2], 10);
            }

            setBiome(server.getWorld(0),position, radius, (byte)Biome.getIdForBiome(BIOMES.get(biome)));

            player.sendMessage(new TextComponentString("chunk found"));


        }
    }

    private static void setBiome (WorldServer world, BlockPos center, int radius, byte biomeID) {
        //TODO Biome change is not syncing with the client, so 1) F3 info is wrong and 2) grass color is wrong until restart
        // First create a list of chunks that include the full radius
        int minX = center.getX() - radius;
        int maxX = center.getX() + radius;
        int minZ = center.getZ() - radius;
        int maxZ = center.getZ() + radius;

        for (int x=(int)Math.floor(minX/16D); x<=(int)Math.floor(maxX/16D); x++) {
            for (int z=(int)Math.floor(minZ/16D); z<=(int)Math.floor(maxZ/16D); z++) {
                Chunk chunk = world.getChunkFromChunkCoords(x,z);
                byte[] biomeArray = chunk.getBiomeArray();
                //Second crawl through the array comparing each one to the inside of the radius
                for (int i = 0; i < biomeArray.length; i++) {
                    int arrX = Math.floorMod(i,16)+16*x;
                    int arrZ = (int)Math.floor(i/16)+16*z;
                      if (minX <= arrX && arrX <=maxX && minZ <= arrZ && arrZ <= maxZ) {
                        //Do the biome change
                          IBlockState state = chunk.getBlockState(arrX, 65, arrZ);
                          world.getChunkFromChunkCoords(x,z).getBiomeArray()[i] = biomeID;
                          //world.notifyBlockUpdate(new BlockPos(arrX, chunk.getHeightValue(Math.floorMod(x,16), Math.floorMod(z,16)), arrZ), state, chunk.getBlockState(arrX, 65, arrZ),0);

                    }
                }
                //chunk.markDirty();  //Not working
            }
        }
    }

    public static boolean biomeExists(String biomeName) {
        if (biomeName == null) {
            return false;
        }
        return BIOMES.containsKey(biomeName.toLowerCase());
    }
}
