package com.farmsoft.youskyblock.island;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LevelData implements Serializable {
    public int pointsPerLevel;
    public int defaultValue;
    public boolean useDiminishingReturns;
    public int defaultScale;
    public Map<String,Double> blockValues = new HashMap<>();
    public Map<String,Integer> blockLimits=new HashMap<>();
    public Map<String,Integer> diminishingReturns=new HashMap<>();
    public Map<String,Integer> negativeReturns=new HashMap<>();

    public LevelData() {}

    public LevelData(boolean samp) {
        if (samp) {
            pointsPerLevel=1000;
            defaultValue=10;
            useDiminishingReturns=false;
            defaultScale=10000;
            blockValues.put("1/0",10.0);
            blockValues.put("1/1",12.5);
            blockValues.put("1/2",15.0);
            blockValues.put("2",20.0);
            blockLimits.put("4",10000);
            blockLimits.put("145/0-2",5);
            diminishingReturns.put("1/0-6",10000);
            diminishingReturns.put("31",100);
            diminishingReturns.put("176",20);
            negativeReturns.put("154",5);
        }
    }
}
