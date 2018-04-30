package com.farmsoft.youskyblock.challenge;

import java.util.Calendar;

public class ChallengeStatus {
    String name;
    int timesCompleted=0;
    int timesCompletedInCooldown=0;
    long cooldownBegun =0;

    public ChallengeStatus(String iname) {
        name=iname;
    }

    public String[] Complete(String iname) {
        long cooldownDuration = 64800; //64800=18 hrs //TODO Look up cooldown in Config
        long now =Calendar.getInstance().getTimeInMillis();

        if (now-cooldownBegun > cooldownDuration) {  //reset cooldown
            cooldownBegun = now;
            timesCompletedInCooldown=0;
        }
        return null;
    }
}
