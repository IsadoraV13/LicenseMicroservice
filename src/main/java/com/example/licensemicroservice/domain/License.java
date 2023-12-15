package com.example.licensemicroservice.domain;

import java.sql.Timestamp;
import java.util.List;

/*
A customer may license either an individual match or a whole tournament.
Every match is part of a tournament.
The service should support multiple customers with different license agreements.
 */

public class License {
    private int licenseId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String licenseName;
    private int singleMatchId;
    private Tournament tournament;

    public License(int licenseId, Timestamp startDate, Timestamp endDate, Tournament tournament) {
        this.licenseId = licenseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.licenseName = tournament.getTournamentName(); // licenseName is the same as TournamentName
        this.tournament = tournament;
    }

    // Overloaded constructor where license is for a single match id
    public License(int licenseId, Timestamp startDate, Timestamp endDate, int singleMatchId, Tournament tournament) {
        this.licenseId = licenseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.licenseName = tournament.getTournamentName(); // licenseName is the same as TournamentName
        this.singleMatchId = singleMatchId;
        this.tournament = tournament;
    }

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public int getSingleMatchId() {
        return singleMatchId;
    }

    public void setSingleMatchId(int singleMatchId) {
        this.singleMatchId = singleMatchId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}


