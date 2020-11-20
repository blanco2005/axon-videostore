package com.fb.videostore.adapter;

import com.fb.api.CreateCustomerCommand;
import com.fb.api.GetCustomerQuery;
import com.fb.videostore.service.CustomerService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AxonCustomerService implements CustomerService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @Override
    public void create(String customerName) {
        commandGateway.send(new CreateCustomerCommand(customerName));
    }

    @Override
    public List<String> getCustomers() {
        try {
            return queryGateway.query(new GetCustomerQuery(), ResponseTypes.multipleInstancesOf(String.class)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
