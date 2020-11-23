package com.fb.order.saga;

import com.fb.api.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class CreateOrderSaga {

    private static Logger logger = LoggerFactory.getLogger(CreateOrderSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;
    private String serialNumber;
    private String orderId;

    public CreateOrderSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent event) {
        logger.info("Saga invoked on order created event!");
        this.serialNumber = event.getSerialNumber();
        this.orderId = event.getOrderId();

        SagaLifecycle.associateWith("customer", event.getCustomer());
        SagaLifecycle.associateWith("serialNumber", event.getSerialNumber());

        commandGateway.send(new RequestRentalCommand(event.getCustomer()));
    }

    @SagaEventHandler(associationProperty = "customer")
    public void on(RentalApprovedEvent event) {
        logger.info("Rental approved...asking to rent");

        commandGateway.send(new RentMovieCommand(serialNumber, event.getCustomer()));
    }

    @SagaEventHandler(associationProperty = "serialNumber")
    @EndSaga
    public void on(MovieRentedEvent event) {
        logger.info("Movie available and rented...terminating saga succesfully");

        commandGateway.send(new ConfirmOrderCommand(orderId));
    }


    @SagaEventHandler(associationProperty = "customer")
    @EndSaga
    public void on(RentalRejectedEvent event) {
        logger.info("Rental rejected for customer " + event.getCustomer() + "...terminating saga");
    }

}
