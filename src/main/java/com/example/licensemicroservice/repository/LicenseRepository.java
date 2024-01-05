package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.License;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class LicenseRepository {

    private final List<License> licenseList;

    public LicenseRepository() {
        this.licenseList = createLicenseList();
    }

    public List<License> getLicenseList() {
        return licenseList;
    }

    /*
        Initially created a one-to-one relationship between license and client, but after NY license discussions, refactored to:
            1. one-to-one relationship between license and tournament (a license is the 'permission' to stream a specific tournament
               as opposed to permission given to a specific client)
            2. added licensing period (i.e. a license is not valid indefinitely)
            3. realised that my license period end dates would have ended at start of day, instead of end of day!

        Additional thinking:
            1. Instead of each license having a license period (thousand of entries of the same thing), I separated periods and licenses


        customerRepo.getCustomerList().stream().map(Customer::getCustomerId).collect(Collectors.toList())
        List.of(customerRepo.getCustomerList().get(0).getCustomerId())
        */
    private List<License> createLicenseList() {
        Timestamp start2023 = Timestamp.valueOf(LocalDateTime.of(2023, 1, 1, 0, 0));
        Timestamp end2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 31, 23, 59));
        Timestamp start2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 1, 0, 0));
        Timestamp end2024 = Timestamp.valueOf(LocalDateTime.of(2024, 12, 31, 23, 59));
        Timestamp startTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 12, 0));
        Timestamp endTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 18, 0));
        License rollandGarros2023 = new License(1, start2023, end2023, "RollandGarros2023", 2);
        License wimbledon2023 = new License(2, start2023, end2023, "Wimbledon2023", 1);
        License wimbledonWomens2023 = new License(3, start2023, end2023, "Wimbledon2023", 1, 2);
        License wimbledonMensFinal2023 = new License(4, start2024, end2024, "Wimbledon2023", 1, 1);
        License matchofTheCentury2024 = new License(5, startTimeDay3_2024, endTimeDay3_2024, "Wimbledon2024", 3, 3);

        return List.of(rollandGarros2023, wimbledon2023, wimbledonWomens2023, wimbledonMensFinal2023, matchofTheCentury2024);
    }

    public License findLicenseById(int licenseId) {
        License license = getLicenseList().stream().filter(s -> s.getLicenseId() == licenseId).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "License ID %s not found".formatted(licenseId)));
        return license;
    }


}
