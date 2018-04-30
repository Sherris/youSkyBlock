package com.farmsoft.youskyblock.challenge;

import java.io.Serializable;
import java.util.Map;

public class Reward implements Serializable {
    public String text;
    public Map<String,String> items; //String1=ID/Var, String2=Qty;Probability
    public int currency;
    public int xp;
    public String message;
    public String[] permissions;
}
