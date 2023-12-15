package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.Customer;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CustomerRepository {

    private final List<Customer> customerList;

    public CustomerRepository() {
        this.customerList = createCustomerList();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    private List<Customer> createCustomerList() {
        Customer tennisLovers = new Customer("Tennis Lovers", 123);
        Customer athleticLovers = new Customer("Athletic Lovers", 456);
        Customer allSportsLovers = new Customer("All Sports Lovers", 789);

        return List.of(tennisLovers, athleticLovers, allSportsLovers);
    }

    public Customer findCustomerById(int customerId) {
        CustomerRepository customerRepo = new CustomerRepository();
        Customer customer = customerRepo.customerList.stream().filter(s -> s.getCustomerId() == customerId).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Customer ID %s not found".formatted(customerId)));
        return customer;
    }


}
