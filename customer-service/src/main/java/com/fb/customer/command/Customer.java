package com.fb.customer.command;

import com.fb.api.CreateCustomerCommand;
import com.fb.api.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Customer {

    @AggregateIdentifier
    private String name;

    public Customer() {
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        apply(new CustomerCreatedEvent(command.getCustomerName()));
    }

    @EventHandler
    public void handle(CustomerCreatedEvent event) {
        this.name = event.getCustomerName();
    }

}
