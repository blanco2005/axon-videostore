package com.fb.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val customer: String)

data class ReturnMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val customer: String)



data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class MovieRentedEvent(val serialNumber: String, val title: String,  val customer: String)

data class ReturnedMovieEvent(val serialNumber: String, val title: String,  val customer: String)

class AllMovieAvailabilityQuery()

data class MovieHistoryQuery(val serialNumber: String)