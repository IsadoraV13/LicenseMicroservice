package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.domain.Tournament;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicenseRetrievalRepository {


    Tournament wimbledon;
    Map<Integer, License> createLicenseMap;


    public LicenseRetrievalRepository(Tournament wimbledon, Map<Integer, License> createLicenseMap) {
        this.wimbledon = new Tournament(1, "Wimbledon 2023");
        this.createLicenseMap = createLicenseMap();
    }

    Map<Integer, License> createLicenseMap() {
        Map<Integer, License> licenseMap = new HashMap<>();
        License license1 = new License();
        license1.setLicenseId(1);
        license1.setLicenseName("Operator1-Tennis");
        license1.setTournamentList(List.of(new Tournament[]{wimbledon}));

        License license2 = new License();
        license1.setLicenseId(2);
        license1.setLicenseName("Operator2-Tennis");
        license1.setTournamentList(List.of(new Tournament[]{wimbledon}));

        licenseMap.put(100, license1);
        licenseMap.put(200, license2);

        return licenseMap;
    }

    License findLicenseById(int licenseId) {


    }

}
