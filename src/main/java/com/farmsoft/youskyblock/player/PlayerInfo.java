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
import java.util.UUID;

public class PlayerInfo implements Serializable {//}, IPlayerInfo {

    private final String PATH = "C:\\Work\\MC_Modding\\apr-21\\run\\config\\PlayerInfo.ser";
    private PlayerSave playerSave = new PlayerSave();

    private class BlockLoc implements Serializable {
        int dimension;
        int x;
        int y;
        int z;
        BlockLoc(int dim, BlockPos blockPos) {
            dimension = dim;
            x = blockPos.getX();
            y = blockPos.getY();
            z = blockPos.getZ();
        }
    }
    private class PlayerSave implements Serializable {
        String playerID;
        String playerName;
        BlockLoc islandCenter;
        BlockLoc islandHome;
        Map<String, Integer> challengeMap;
        int deaths;
    }


    public PlayerInfo(EntityPlayerMP playerMP) {
        if (isNew()) {
            playerSave.playerID = playerMP.getCachedUniqueIdString();
            playerSave.playerName = playerMP.getDisplayNameString();
            playerSave.islandCenter=new BlockLoc(0,new BlockPos(0,255,0));
            playerSave.islandHome=new BlockLoc(playerMP.dimension,playerMP.getPosition());
            playerSave.challengeMap = new HashMap<>();
            playerSave.deaths=0;
            save(playerSave);
        } else {
            //read player data from file
            playerSave = load();

        }
    }


    public void setHome(BlockPos homePos) {

    }

    public BlockPos getHome() {
        return null;
        //return islandHome;
    }

    public void completeChallenge(String challengeName){
        playerSave.challengeMap.put(challengeName, playerSave.challengeMap.getOrDefault(challengeName,0)+1);
        save(playerSave);
    };

    public Map<String, Integer> getChallenges(){
        return playerSave.challengeMap;
    }

    public void die () {
        playerSave.deaths++;
        playerSave.challengeMap.put("cactusfarmer",17);
        save(playerSave);
    }

    public int getDeaths(){
        return playerSave.deaths;
    };


    public void save(PlayerSave ps) {
        //Save PlayerInfo to file


        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream(PATH);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(ps);

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

    private PlayerSave load() {
        PlayerSave ps = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {

            fin = new FileInputStream(PATH);
            ois = new ObjectInputStream(fin);
            ps = (PlayerSave) ois.readObject();


        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause().toString();
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
            return ps;
        }

    }


    private boolean isNew() {
        //check if file exists for player
        File f = new File(PATH);
        return !(f.exists() && !f.isDirectory());
    }
}
