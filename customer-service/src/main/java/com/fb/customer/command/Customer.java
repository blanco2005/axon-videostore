package com.fb.customer.command;

import com.fb.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Customer {

    private final static Logger logger = LoggerFactory.getLogger(Customer.class);

    private final static int MAX_NUMBER_OF_RENTALS_ALLOWED = 2;

    @AggregateIdentifier
    private String name;

    private int numbreOfOnGoingRental;

    public Customer() {
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        apply(new CustomerCreatedEvent(command.getCustomerName()));
    }

    @CommandHandler
    public void handle(RequestRentalCommand command) {
        logger.info("Received request rental command");
        if(numbreOfOnGoingRental < MAX_NUMBER_OF_RENTALS_ALLOWED) {
            logger.info("Rental approved");
            apply(new RentalApprovedEvent(command.getCustomerName()));
        }
        else {
            logger.info("Rental rejected. Current rentals: " + numbreOfOnGoingRental);
            apply(new RentalRejectedEvent(command.getCustomerName()));
        }
    }

    @EventHandler
    public void handle(CustomerCreatedEvent event) {
        this.name = event.getCustomerName();
        this.numbreOfOnGoingRental = 0;
    }

    @EventHandler
    public void handle(RentalApprovedEvent event) {
        this.numbreOfOnGoingRental = this.numbreOfOnGoingRental + 1;
    }

}
