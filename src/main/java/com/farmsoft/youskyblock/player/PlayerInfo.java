package com.farmsoft.youskyblock.player;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfo implements Serializable {

    private BlockPos islandCenter;
    private BlockPos islandHome;
    private Map<String, Integer> challengeMap;  //String challenge ID, Integer: times completed
    private int deaths;
    private EntityPlayerMP player;


    public PlayerInfo(EntityPlayerMP playerMP) {
        if (isNew()) {
            player = playerMP;
            deaths = 0;
            challengeMap = new HashMap<>();
            islandCenter = new BlockPos(0,255, 0);
            islandHome = player.getPosition();
            save();
        } else {
            //read player data from file
        }
    }

    public void save() {
        //Save PlayerInfo to file

    }

    public void onDeath () {
        deaths++;
        save();
    }

    private boolean isNew() {
        //check if file exists for player
        return true;
    }
}
