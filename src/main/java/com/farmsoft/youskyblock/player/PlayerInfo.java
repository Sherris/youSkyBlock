package com.farmsoft.youskyblock.player;

import net.minecraft.client.entity.EntityPlayerSP;
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
    private EntityPlayerSP player;


    public PlayerInfo(EntityPlayerSP playerSP) {
        if (isNew()) {
            player = playerSP;
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
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("C:\\Work\\MC_Modding\\apr-21\\run\\config\\PlayerInfo.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
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
