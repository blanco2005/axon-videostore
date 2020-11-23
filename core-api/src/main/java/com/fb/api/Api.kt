package com.fb.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val customer: String)

data class ReturnMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val customer: String)

data class CreateCustomerCommand(@TargetAggregateIdentifier val customerName: String)

data class CreateOrderCommand(@TargetAggregateIdentifier val orderId: String, val serialNumber: String, val customer: String)

data class RequestRentalCommand(@TargetAggregateIdentifier val customerName: String)

data class ConfirmOrderCommand(@TargetAggregateIdentifier val orderId: String)



data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class MovieRentedEvent(val serialNumber: String, val title: String,  val customer: String)

data class ReturnedMovieEvent(val serialNumber: String, val title: String,  val customer: String)

data class OrderCreatedEvent(val orderId: String, val serialNumber: String, val customer: String)

data class RentalApprovedEvent(val customer: String)

data class RentalRejectedEvent(val customer: String)

data class OrderConfirmedEvent(val orderId: String)

class AllMovieAvailabilityQuery()

data class MovieHistoryQuery(val serialNumber: String)

class GetCustomerQuery()

data class CustomerCreatedEvent(val customerName: String)