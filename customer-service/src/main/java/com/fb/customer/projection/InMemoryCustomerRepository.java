package com.fb.customer.projection;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryCustomerRepository implements CustomerRepository {

    private final List<String> customers;

    public InMemoryCustomerRepository() {
        customers = new ArrayList<String>();
    }


    public void add(String customerName) {
        customers.add(customerName);
    }

    public List<String> getCustomers() {
        return customers;
    }
}
