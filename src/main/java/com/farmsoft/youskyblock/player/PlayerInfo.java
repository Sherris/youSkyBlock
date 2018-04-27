package com.farmsoft.youskyblock.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfo implements Serializable, IPlayerInfo {

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
            load();

        }
    }

    public void setHome(BlockPos homePos) {

    }

    public BlockPos getHome() {
        return islandHome;
    }

    public void completeChallenge(String challengeName){
        challengeMap.put(challengeName, challengeMap.getOrDefault(challengeName,0)+1);
    };

    public Map<String, Integer> getChallenges(){
        return challengeMap;
    }

    public void die () {
        deaths++;
        save();
    }

    public int getDeaths(){
        return deaths;
    };


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

    private void load() {
        PlayerInfo pi = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {

            fin = new FileInputStream("C:\\Work\\MC_Modding\\apr-21\\run\\config\\PlayerInfo.ser");
            ois = new ObjectInputStream(fin);
            pi = (PlayerInfo) ois.readObject();
            islandCenter = pi.islandCenter;
            islandHome = pi.islandHome;
            challengeMap = pi.challengeMap;
            deaths = pi.deaths;
            player = pi.player;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private boolean isNew() {
        //check if file exists for player
        return false;
    }
}
