package com.farmsoft.youskyblock.island;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.chunk.Chunk;

public class IslandScore {

    Chunk chunk;
    Block block;

    void getScore(MinecraftServer server) {
        chunk = server.getWorld(0).getChunkFromChunkCoords(0,0);
        block = chunk.getBlockState(0,63,0).getBlock();

    }
}
