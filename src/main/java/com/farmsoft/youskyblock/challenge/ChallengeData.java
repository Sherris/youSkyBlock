package com.farmsoft.youskyblock.challenge;

import java.io.Serializable;
import java.util.Map;

public class ChallengeData implements Serializable {
    public boolean allowChallenges;
    public String challengeSharing;
    public boolean broadcastCompletion;
    public String broadcastText;
    public boolean requirePreviousRank;
    public Integer rankLeeway;
    public Integer defaultResetInHours;
    public String challengeColor;
    public String finishedColor;
    public String repeatableColor;
    public boolean enableEconomyPlugin;
    public boolean resetChallengesOnCreate;
    public Map<String, Challenge> challenges;


    public ChallengeData() {

    }

}
