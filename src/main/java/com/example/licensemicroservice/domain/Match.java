package com.example.licensemicroservice.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/*
summary: An optional parameter called summaryType can be set to any of:
AvB - return string "<playerA> vs <playerB>"
AvBTime - use start time to return string
    "<playerA> vs <playerB>, starts in x minutes" for future start times, OR
    "<playerA> vs <playerB>, started x minutes ago" for start times in the past.
 */

public class Match {
    private int matchId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Player playerA;
    private Player playerB;
    private Boolean hasSummary;


    public Match(int matchId, Timestamp startTime, Timestamp endTime, Player playerA, Player playerB, Boolean hasSummary) {
        this.matchId = matchId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerA = playerA;
        this.playerB = playerB;
        this.hasSummary = hasSummary;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public Boolean getHasSummary() {
        return hasSummary;
    }

    public void setHasSummary(Boolean hasSummary) {
        this.hasSummary = hasSummary;
    }
}
