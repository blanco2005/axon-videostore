package com.fb.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val customer: String)


data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class MovieRentedEvent(val serialNumber: String, val customer: String)