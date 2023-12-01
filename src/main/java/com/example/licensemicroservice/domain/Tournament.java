package com.example.licensemicroservice.domain;

import org.springframework.stereotype.Component;

public class Tournament {
    private int tournamentId;
    private String tournamentName;

    // ToDo: do I need a collection of matches here?


    public Tournament(int tournamentId, String tournamentName) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
