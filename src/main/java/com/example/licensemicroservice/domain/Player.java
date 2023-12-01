package com.example.licensemicroservice.domain;

import org.springframework.stereotype.Component;

public class Player {
    private int playerId;
    private String playerFirstName;
    private String playerLastName;
    private String playerRank;

    // ToDo what is the right way (given we are not connecting to a DB to have a version with Rank as null?)
    public Player(int playerId, String playerFirstName, String playerLastName) {
        this.playerId = playerId;
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.playerRank = null;
    }

    public Player(int playerId, String playerFirstName, String playerLastName, String playerRank) {
        this.playerId = playerId;
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.playerRank = playerRank;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }
}
