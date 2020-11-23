package com.fb.order.command;

import com.fb.api.ConfirmOrderCommand;
import com.fb.api.CreateOrderCommand;
import com.fb.api.OrderConfirmedEvent;
import com.fb.api.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static com.fb.order.command.Order.OrderStatus.*;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Order {

  @AggregateIdentifier private String orderId;

  private OrderStatus orderStatus;

  public Order() {}

  @CommandHandler
  public Order(CreateOrderCommand command) {
    apply(new OrderCreatedEvent(command.getOrderId(), command.getSerialNumber(), command.getCustomer()));
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand command) {
    apply(new OrderConfirmedEvent(command.getOrderId()));
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent event) {
    this.orderId = event.getOrderId();
    this.orderStatus = CREATED;
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    this.orderStatus = APPROVED;
  }

  enum OrderStatus {
    CREATED,
    APPROVED,
    REJECTED
  }
}
