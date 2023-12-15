package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.repository.CustomerRepository;
import com.example.licensemicroservice.repository.LicenseRepository;
import com.example.licensemicroservice.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    final LicenseRepository licenseRepository;

    final TournamentRepository tournamentRepository;

    final CustomerRepository customerRepository;

    public LicenseService(LicenseRepository licenseRepository, TournamentRepository tournamentRepository, CustomerRepository customerRepository) {
        this.licenseRepository = licenseRepository;
        this.tournamentRepository = tournamentRepository;
        this.customerRepository = customerRepository;
    }

    public License getLicenseById(int licenseId){
        return licenseRepository.findLicenseById(licenseId);
    }


}
