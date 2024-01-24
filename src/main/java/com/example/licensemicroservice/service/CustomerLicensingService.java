package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Tournament;
import com.example.licensemicroservice.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerLicensingService {

    final CustomerLicensingRepository customerLicensingRepository;

    public CustomerLicensingService(CustomerLicensingRepository customerLicensingRepository) {
        this.customerLicensingRepository = customerLicensingRepository;
    }

    public List<Integer> getLicenseIdsByCustomerId(int customerId) {
        return customerLicensingRepository.findLicenseIdsByCustomerId(customerId);
    }

}
