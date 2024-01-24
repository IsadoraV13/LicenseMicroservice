package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.service.CustomerLicensingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customerlicense")
public class CustomerLicensingController {

    final CustomerLicensingService customerLicensingService;

    public CustomerLicensingController(CustomerLicensingService customerLicensingService) {
        this.customerLicensingService = customerLicensingService;
    }


    @GetMapping("/{customerId}")
    public List<Integer> viewLicenseIdsByCustomerId(@PathVariable int customerId) {
        return customerLicensingService.getLicenseIdsByCustomerId(customerId);
    }

//    @GetMapping("match/{customerId}")
//    public List<Match> viewMatchesByCustomerId(@PathVariable int customerId) {
//        return customerLicensingService.getMatchesByCustomerId(customerId);
//    }
}
