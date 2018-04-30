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

}
