package com.farmsoft.youskyblock.commands;


import com.farmsoft.youskyblock.BlockInfo;
import com.farmsoft.youskyblock.Color;
import com.farmsoft.youskyblock.YouSkyBlockMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;

import java.util.HashMap;
import java.util.Map;

class ScoreCommand {




    static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {

        BlockPos position = player.getPosition();
        Map<String,Integer> islandBlocks = new HashMap<>();

        //Standard island is 12 chunks by 12 chunks - worlds with 1 island should be from -6 to 5
        for (int cx=-6; cx<6; cx++) {
            for (int cz=-6; cz<6; cz++) {
                Chunk chunk = server.getWorld(0).getChunkFromChunkCoords(cx, cz);

                for (int x = 0; x < 16; x++) {

                    for (int z = 0; z < 16; z++) {

                        for (int y = 0; y < 256; y++) {

                            //chunkBlocks.getValue("BlockID/Var")=chunkBlocks.getValue("BlockID/Var")+1

                            IBlockState block = chunk.getBlockState(x + (chunk.x <<4), y, z + (chunk.z <<4));
                            String blockId = BlockInfo.getId(block);
                            if (blockId.equals("41/0")) {
                                int r=4;
                            }
                            //String blockFullName = BlockInfo.getFullName(blockId);
                            islandBlocks.put(blockId, 1 + islandBlocks.getOrDefault(blockId, 0));
                        }
                    }
                }

            }
        }

        Double islandScore = 0D;
        for(Map.Entry<String, Integer> entry : islandBlocks.entrySet()) {
            Double pointsPer = YouSkyBlockMod.LEVELDATA.blockValues.get(entry.getKey());
            if (entry.getKey().equals("0/0")) {
                pointsPer = 0D;  //No points for air ever (hardcoded)
            }
            if (pointsPer == null) {
                pointsPer = YouSkyBlockMod.LEVELDATA.blockValues.get(entry.getKey().split("/")[0]);
            }
            if (pointsPer == null) {
                pointsPer = YouSkyBlockMod.LEVELDATA.defaultValue+0D;
            }
            Integer maxBlocks = YouSkyBlockMod.LEVELDATA.blockLimits.get(entry.getKey());
            Integer dimBlocks = YouSkyBlockMod.LEVELDATA.diminishingReturns.get(entry.getKey());
            if (YouSkyBlockMod.LEVELDATA.useDiminishingReturns && (dimBlocks == null || YouSkyBlockMod.LEVELDATA.defaultScale < dimBlocks)) {
                dimBlocks = YouSkyBlockMod.LEVELDATA.defaultScale;
            }
            Integer negBlocks = YouSkyBlockMod.LEVELDATA.negativeReturns.get(entry.getKey());
            Integer countBlocks = entry.getValue();
            String color = Color.WHITE;

            if (maxBlocks != null && maxBlocks < countBlocks) {
                countBlocks = maxBlocks;
                color = Color.RED;
            }
            if (dimBlocks == null) {
                dimBlocks = countBlocks;
            } else if (dimBlocks < countBlocks) {
                color = Color.YELLOW;
            }

            Double finalpts = (Math.sqrt(8*Math.max(countBlocks/dimBlocks*1D,1)+1)-1)/2*Math.min(countBlocks,dimBlocks)*pointsPer/YouSkyBlockMod.LEVELDATA.pointsPerLevel;
            islandScore = islandScore + finalpts;

        }





        player.sendMessage(new TextComponentString(Color.DARKGREEN + "Your island score is: " + Color.DARKAQUA + islandScore));

    }



}
