package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.repository.CustomerRepository;
import com.example.licensemicroservice.repository.LicenseRepository;
import com.example.licensemicroservice.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public License getLicenseById(int licenseId){
        return licenseRepository.findLicenseById(licenseId);
    }


}
