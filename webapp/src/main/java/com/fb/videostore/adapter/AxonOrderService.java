package com.fb.videostore.adapter;

import com.fb.api.CreateOrderCommand;
import com.fb.videostore.service.OrderService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class AxonOrderService implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(AxonOrderService.class);

    @Autowired
    private CommandGateway commandGateway;


    @Override
    public void createOrder(String serialNumber, String customer) {
        String orderId = String.valueOf(UUID.randomUUID());
        try {
            commandGateway.send(new CreateOrderCommand(orderId, serialNumber, customer)).get();
            logger.info("Create order completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
