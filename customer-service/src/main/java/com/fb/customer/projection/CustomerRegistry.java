package com.fb.customer.projection;

import com.fb.api.CustomerCreatedEvent;
import com.fb.api.GetCustomerQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRegistry {

    private final CustomerRepository customerRepository;

    public CustomerRegistry(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        customerRepository.add(event.getCustomerName());
    }

    @QueryHandler
    public List<String> on(GetCustomerQuery query) {
        return customerRepository.getCustomers();
    }
}
