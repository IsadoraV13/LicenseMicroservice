package com.example.licensemicroservice.domain;

import org.springframework.stereotype.Component;

/*
A customer may license either an individual match or a whole tournament.
Every match is part of a tournament.
 */
public class Tournament {
    private int tournamentId;
    private String tournamentName;

    // ToDo: do I need a collection of matches here?
    // even if there is only one match, it will be associated to a tournament, so that we can scale

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
