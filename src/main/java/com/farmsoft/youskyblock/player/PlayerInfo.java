package com.farmsoft.youskyblock.player;

import com.farmsoft.youskyblock.YouSkyBlockMod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfo implements Serializable {

    private final String PATH = YouSkyBlockMod.ConfigPath.getPath() + "\\PlayerInfo.ser"; //"C:\\Work\\MC_Modding\\apr-21\\run\\config\\PlayerInfo.ser";
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


    public boolean setHome(EntityPlayerMP player) {
        if (ValidHome(player)) {
            playerSave.islandHome.dimension = player.dimension;
            playerSave.islandHome.x = player.getPosition().getX();
            playerSave.islandHome.y = player.getPosition().getY();
            playerSave.islandHome.z = player.getPosition().getZ();
            save(playerSave);
            return true;
        } else {
            return false;
        }
    }

    public BlockPos getHome() {
        return new BlockPos(playerSave.islandHome.x, playerSave.islandHome.y, playerSave.islandHome.z);
        //return islandHome;
    }

    private boolean ValidHome(EntityPlayerMP player) {

        if (player.dimension!=0 || isNetherHomeValid()) {return false;}
        if (player.getPosition().getDistance(playerSave.islandCenter.x, player.getPosition().getY(),playerSave.islandCenter.z) > 96) { return false;}
        //anything else make it invalid?
        return true;

    }

    private boolean isNetherHomeValid() {
        //TODO Can player set a home in the nether?  Default here is no. Set up an option
        return false;    //replace false with check for nether home option
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
        save(playerSave);
    }

    public int getDeaths(){
        return playerSave.deaths;
    };


    private void save(PlayerSave ps) {
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
