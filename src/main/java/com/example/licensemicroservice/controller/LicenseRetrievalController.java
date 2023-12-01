package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.repository.LicenseRetrievalRepository;
import com.example.licensemicroservice.service.LicenseRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LicenseRetrievalController {

    @Autowired
    LicenseRetrievalService licenseRetrievalService;

    @Autowired
    LicenseRetrievalRepository licenseRetrievalRepository;

    @GetMapping("license/{licenseId}")
    public License getLicenseById(int id) {
        licenseRetrievalRepository.
    }
}
