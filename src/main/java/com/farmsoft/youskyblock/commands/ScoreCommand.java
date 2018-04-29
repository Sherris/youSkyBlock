package com.farmsoft.youskyblock.commands;


import com.farmsoft.youskyblock.BlockName;
import net.minecraft.block.*;
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
        Map<String,Integer> chunkBlocks = new HashMap<>();

        for (int cx=-6; cx<6; cx++) {
            for (int cz=-6; cz<6; cz++) {
                Chunk chunk = server.getWorld(0).getChunkFromChunkCoords(cx, cz);

                for (int x = 0; x < 16; x++) {

                    for (int z = 0; z < 16; z++) {

                        for (int y = 0; y < 255; y++) {

                            //chunkBlocks.getValue("BlockID/Var")=chunkBlocks.getValue("BlockID/Var")+1

                            IBlockState block = chunk.getBlockState(x + chunk.x * 16, y, z + chunk.z * 16);
                            String blockId = Block.getIdFromBlock(block.getBlock()) + "/" + (Block.getStateId(block) >> 12);

                            String blockFullName = BlockName.full(blockId);

                            ItemStack itemStack = new ItemStack(block.getBlock());
                            //itemStack.getItem().getCreatorModId(itemStack);
                            //StatCollector.translateToLocal(block.getUnlocalizedName())
                            //TextComponentTranslation tct = new TextComponentTranslation("minecraft:stonebrick");

                            //new TextComponentTranslation(block.getBlock().getUnlocalizedName() + (blockVariant.equals("") || blockVariant.equals(block.getBlock().getLocalizedName().toLowerCase()) ? ""  : "." + blockVariant) + ".name").getUnformattedText();

                            chunkBlocks.put(blockFullName, 1 + chunkBlocks.getOrDefault(blockFullName, 0));
                        }
                    }
                }

            }
        }





        player.sendMessage(new TextComponentString("retrieved tile entities"));

    }



}
