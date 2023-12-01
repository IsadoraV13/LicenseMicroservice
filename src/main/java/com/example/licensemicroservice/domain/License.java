package com.example.licensemicroservice.domain;

import org.springframework.stereotype.Component;

import java.util.List;

public class License {
    private int licenseId;
    private String licenseName;
    private List<Tournament> tournamentList;
    // even if there is only one match, it will be associated to a tournament, so that we can scale


    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }


    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }
}
