package com.fb.videostore.controller;

import com.fb.videostore.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping("/customers/{customerName}")
    public ResponseEntity<String> createCustomer(@PathVariable("customerName") String customerName) {
        customerService.create(customerName);
        return ResponseEntity.ok("Customer " + customerName + " created");
    }

    @GetMapping("/customers")
    public ResponseEntity<String> getCustomers() {
        String result = String.join("\n", customerService.getCustomers());
        return ResponseEntity.ok(result);
    }
}
