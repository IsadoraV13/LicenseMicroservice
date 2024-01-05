package com.example.licensemicroservice.domain;

import com.example.licensemicroservice.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

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
    private int tournamentId;
    private int singleMatchId;

    public License(int licenseId, Timestamp startDate, Timestamp endDate, String licenseName, int tournamentId) {
        this.licenseId = licenseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tournamentId = tournamentId;
        this.licenseName = licenseName; //ToDo wanted licenseName to be the same as tournament name
    }

    // Overloaded constructor where license is for a single match id
    public License(int licenseId, Timestamp startDate, Timestamp endDate, String licenseName, int tournamentId, int singleMatchId) {
        this.licenseId = licenseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tournamentId = tournamentId;
        this.licenseName = licenseName; //ToDo wanted licenseName to be the same as tournament name
        this.singleMatchId = singleMatchId;
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

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getSingleMatchId() {
        return singleMatchId;
    }

    public void setSingleMatchId(int singleMatchId) {
        this.singleMatchId = singleMatchId;
    }

}


