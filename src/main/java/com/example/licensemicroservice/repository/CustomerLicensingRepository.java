package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.Customer;
import com.example.licensemicroservice.domain.CustomerLicensing;
import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerLicensingRepository {
    private final List<CustomerLicensing> customerLicensingList;

    public CustomerLicensingRepository() {
        this.customerLicensingList = createCustomerLicensingList();
    }

    public List<CustomerLicensing> getCustomerLicensingList() {
        return customerLicensingList;
    }

    public List<CustomerLicensing> createCustomerLicensingList() {
        CustomerLicensing tennisLoversATP2023 = new CustomerLicensing(123, 1);
        CustomerLicensing tennisLoversWimbledon2023 = new CustomerLicensing(123, 2);
        CustomerLicensing tennisLoversWimbledonWomen2023 = new CustomerLicensing(123, 3);
        CustomerLicensing tennisLoversWimbledonMen2023 = new CustomerLicensing(123, 4);
        CustomerLicensing tennisLoversWimbledonMen2024 = new CustomerLicensing(123, 5);
        CustomerLicensing allSportsLoversWimbledon2023 = new CustomerLicensing(789, 5);

        return List.of(tennisLoversATP2023, tennisLoversWimbledon2023, tennisLoversWimbledonWomen2023,
                tennisLoversWimbledonMen2023, tennisLoversWimbledonMen2024, allSportsLoversWimbledon2023);
    }


    /*
    Not refactored
     */
    public List<Integer> findLicenseIdsByCustomerId(int customerId) {
        return getCustomerLicensingList().stream().filter(
                s -> s.getCustomerId() == customerId).map(
                        s -> s.getLicensingId()).collect(Collectors.toList());
    }


}
