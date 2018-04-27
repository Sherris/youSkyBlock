package com.farmsoft.youskyblock.player;

import net.minecraft.util.math.BlockPos;

import java.util.Map;

public interface IPlayerInfo {

    public void setHome(BlockPos homePos);

    public BlockPos getHome();

    public void completeChallenge(String challengeName);

    public Map<String, Integer> getChallenges();

    public void die();

    public int getDeaths();

}
