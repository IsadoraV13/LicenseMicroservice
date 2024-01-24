package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.service.LicenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    final
    LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{licenseId}")
    public License viewLicenseById(@PathVariable int licenseId) {
        return licenseService.getLicenseById(licenseId);
    }
}
