package com.fb.customer.projection;

import java.util.List;

public interface CustomerRepository {

    void add(String customerName);

    List<String> getCustomers();
}
