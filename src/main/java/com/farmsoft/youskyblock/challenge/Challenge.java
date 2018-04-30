package com.farmsoft.youskyblock.challenge;

import java.io.Serializable;
import java.util.Map;

public class Challenge implements Serializable {
    public String name;
    public String description;
    public String type;
    public int radius;
    public Map<String,String> requiredItems; //String1= ID/Var; String2=Qty;Increment within reset
    public String displayItem;
    public String lockedDisplayItem;
    public int resetInHours;
    public Reward firstReward;
    public Reward repeatReward;
    public String[] requiredChallenges;
    public Map<String, Integer> requiredEntities;  //ID, Qty

    public void Challenge() {
    }
}
